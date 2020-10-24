package edu.aku.hassannaqvi.tmk_el_spotcheck.ui.other;

import android.app.ActivityManager;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

import edu.aku.hassannaqvi.tmk_el_spotcheck.R;
import edu.aku.hassannaqvi.tmk_el_spotcheck.contracts.VillageContract;
import edu.aku.hassannaqvi.tmk_el_spotcheck.core.AndroidDatabaseManager;
import edu.aku.hassannaqvi.tmk_el_spotcheck.core.MainApp;
import edu.aku.hassannaqvi.tmk_el_spotcheck.databinding.ActivityMainBinding;
import edu.aku.hassannaqvi.tmk_el_spotcheck.models.Form;
import edu.aku.hassannaqvi.tmk_el_spotcheck.models.VersionApp;
import edu.aku.hassannaqvi.tmk_el_spotcheck.ui.list_activity.FormsReportDate;
import edu.aku.hassannaqvi.tmk_el_spotcheck.ui.sections.SectionBActivity;
import edu.aku.hassannaqvi.tmk_el_spotcheck.ui.sections.SectionCActivity;
import edu.aku.hassannaqvi.tmk_el_spotcheck.ui.sections.SectionDActivity;
import edu.aku.hassannaqvi.tmk_el_spotcheck.ui.sections.SectionE01Activity;
import edu.aku.hassannaqvi.tmk_el_spotcheck.ui.sections.SectionF01Activity;
import edu.aku.hassannaqvi.tmk_el_spotcheck.ui.sections.SectionG01Activity;
import edu.aku.hassannaqvi.tmk_el_spotcheck.ui.sections.SectionH01Activity;
import edu.aku.hassannaqvi.tmk_el_spotcheck.ui.sections.SectionI01Activity;
import edu.aku.hassannaqvi.tmk_el_spotcheck.ui.sections.SectionJ01Activity;
import edu.aku.hassannaqvi.tmk_el_spotcheck.ui.sections.SectionKActivity;
import edu.aku.hassannaqvi.tmk_el_spotcheck.ui.sections.SectionLActivity;
import edu.aku.hassannaqvi.tmk_el_spotcheck.ui.sections.SectionNActivity;
import edu.aku.hassannaqvi.tmk_el_spotcheck.utils.AndroidUtilityKt;
import edu.aku.hassannaqvi.tmk_el_spotcheck.utils.AppUtilsKt;
import edu.aku.hassannaqvi.tmk_el_spotcheck.utils.CreateTable;
import edu.aku.hassannaqvi.tmk_el_spotcheck.utils.WarningActivityInterface;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static edu.aku.hassannaqvi.tmk_el_spotcheck.CONSTANTS.VILLAGES_DATA;
import static edu.aku.hassannaqvi.tmk_el_spotcheck.core.MainApp.appInfo;

public class MainActivity extends AppCompatActivity implements WarningActivityInterface {

    static File file;
    ActivityMainBinding bi;
    String dtToday = new SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.getDefault()).format(new Date().getTime());
    String sysdateToday = new SimpleDateFormat("dd-MM-yy", Locale.getDefault()).format(new Date());
    SharedPreferences sharedPrefDownload;
    SharedPreferences.Editor editorDownload;
    DownloadManager downloadManager;
    String preVer = "", newVer = "";
    VersionApp versionApp;
    Long refID;
    //Setting Spinner
    List<String> areaName, villageName;
    Map<String, VillageContract> villageMap;
    List<VillageContract> areaList;
    VillageContract village;
    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (DownloadManager.ACTION_DOWNLOAD_COMPLETE.equals(intent.getAction())) {

                DownloadManager.Query query = new DownloadManager.Query();
                query.setFilterById(sharedPrefDownload.getLong("refID", 0));

                downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
                assert downloadManager != null;
                Cursor cursor = downloadManager.query(query);
                if (cursor.moveToFirst()) {
                    int colIndex = cursor.getColumnIndex(DownloadManager.COLUMN_STATUS);
                    if (DownloadManager.STATUS_SUCCESSFUL == cursor.getInt(colIndex)) {

                        editorDownload.putBoolean("flag", true);
                        editorDownload.commit();

                        Toast.makeText(context, "New App downloaded!!", Toast.LENGTH_SHORT).show();
                        bi.lblAppVersion.setText(new StringBuilder(getString(R.string.app_name) + " App New Version ").append(newVer).append("  Downloaded"));

                        ActivityManager am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
                        List<ActivityManager.RunningTaskInfo> taskInfo = am.getRunningTasks(1);

                        if (Objects.requireNonNull(taskInfo.get(0).topActivity).getClassName().equals(MainActivity.class.getName())) {
                            showDialog(newVer, preVer);
                        }
                    }
                }
            }
        }
    };
    private Boolean exit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_main);
        bi.setCallback(this);

        //bi.txtinstalldate.setText(appInfo.getAppInfo());
        Collection<Form> todaysForms = appInfo.getDbHelper().getTodayForms(sysdateToday);
        Collection<Form> unsyncedForms = appInfo.getDbHelper().getUnsyncedForms(null);
        Collection<Form> unclosedForms = appInfo.getDbHelper().getUnclosedForms();

        StringBuilder rSumText = new StringBuilder()
                .append("TODAY'S RECORDS SUMMARY\r\n")
                .append("=======================\r\n")
                .append("\r\n")
                .append("Total Forms Today" + "(").append(dtToday).append("): ").append(todaysForms.size()).append("\r\n");
        String TAG = "MainActivity";
        if (todaysForms.size() > 0) {
            String iStatus;
            rSumText.append("---------------------------------------------------------\r\n")
                    .append("[  Name  ][Ref. No][Form Status][Sync Status]\r\n")
                    .append("---------------------------------------------------------\r\n");

            for (Form form : todaysForms) {
                Log.d(TAG, "onCreate: '" + form.getIstatus() + "'");
                switch (form.getIstatus()) {
                    case "1":
                        iStatus = "Complete   ";
                        break;
                    case "2":
                        iStatus = "Incomplete ";
                        break;
                    case "3":
                        iStatus = "Refused    ";
                        break;
                    case "96":
                        iStatus = "Other    ";
                        break;
                    case "":
                        iStatus = "Open     ";
                        break;
                    default:
                        iStatus = "  -N/A-  " + form.getIstatus();
                }

                rSumText
                        /*.append((form.getMp101() + "          ").substring(0, 10))
                        .append((form.getMp102() + "      ").substring(0, 6))*/
                        .append("  \t\t")
                        .append(iStatus)
                        .append("\t\t\t\t")
                        .append(form.getSynced() == null ? "Not Synced" : "Synced    ")
                        .append("\r\n")
                        .append("---------------------------------------------------------\r\n");
            }
        }
        SharedPreferences syncPref = getSharedPreferences("src", Context.MODE_PRIVATE);
        rSumText.append("\r\nDEVICE INFORMATION\r\n")
                .append("  ========================================================\r\n")
                .append("\t|| Open Forms: \t\t\t\t\t\t").append(String.format("%02d", unclosedForms.size()))
                .append("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t||\r\n")
                .append("\t|| Unsynced Forms: \t\t\t\t").append(String.format("%02d", unsyncedForms.size()))
                .append("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t||\r\n")
                .append("\t|| Last Data Download: \t\t").append(syncPref.getString("LastDataDownload", "Never Downloaded   "))
                .append("\t\t\t\t\t\t||\r\n")
                .append("\t|| Last Data Upload: \t\t\t").append(syncPref.getString("LastDataUpload", "Never Uploaded     "))
                .append("\t\t\t\t\t\t||\r\n")
                .append("\t|| Last Photo Upload: \t\t").append(syncPref.getString("LastPhotoUpload", "Never Uploaded     "))
                .append("\t\t\t\t\t\t||\r\n")
                .append("\t========================================================\r\n");
        bi.recordSummary.setText(rSumText);

        Log.d(TAG, "onCreate: " + rSumText);
        if (MainApp.admin) {
            bi.databaseBtn.setVisibility(View.VISIBLE);
        } else {
            bi.databaseBtn.setVisibility(View.GONE);
        }

        // Auto download app
        sharedPrefDownload = getSharedPreferences("appDownload", MODE_PRIVATE);
        editorDownload = sharedPrefDownload.edit();
        versionApp = appInfo.getDbHelper().getVersionApp();
        if (versionApp.getVersioncode() != null) {

            preVer = appInfo.getVersionName() + "." + appInfo.getVersionCode();
            newVer = versionApp.getVersionname() + "." + versionApp.getVersioncode();

            if (appInfo.getVersionCode() < Integer.parseInt(versionApp.getVersioncode())) {
                bi.lblAppVersion.setVisibility(View.VISIBLE);

                String fileName = CreateTable.DATABASE_NAME.replace(".db", "-New-Apps");
                file = new File(Environment.getExternalStorageDirectory() + File.separator + fileName, versionApp.getPathname());

                if (file.exists()) {
                    bi.lblAppVersion.setText(new StringBuilder(getString(R.string.app_name) + " New Version ").append(newVer).append("  Downloaded"));
                    showDialog(newVer, preVer);
                } else {
                    NetworkInfo networkInfo = ((ConnectivityManager) Objects.requireNonNull(getSystemService(Context.CONNECTIVITY_SERVICE))).getActiveNetworkInfo();
                    if (networkInfo != null && networkInfo.isConnected()) {
                        bi.lblAppVersion.setText(new StringBuilder(getString(R.string.app_name) + " App New Version ").append(newVer).append("  Downloading.."));
                        downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
                        Uri uri = Uri.parse(MainApp._UPDATE_URL + versionApp.getPathname());
                        DownloadManager.Request request = new DownloadManager.Request(uri);
                        request.setDestinationInExternalPublicDir(fileName, versionApp.getPathname())
                                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                                .setTitle("Downloading " + getString(R.string.app_name) + " App new App ver." + newVer);
                        refID = downloadManager.enqueue(request);

                        editorDownload.putLong("refID", refID);
                        editorDownload.putBoolean("flag", false);
                        editorDownload.apply();

                    } else {
                        bi.lblAppVersion.setText(new StringBuilder(getString(R.string.app_name) + " App New Version ").append(newVer).append("  Available..\n(Can't download.. Internet connectivity issue!!)"));
                    }
                }

            } else {
                bi.lblAppVersion.setVisibility(View.GONE);
                bi.lblAppVersion.setText(null);
            }
        }
        registerReceiver(broadcastReceiver, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));

//        Testing visibility
        if (Integer.parseInt(appInfo.getVersionName().split("\\.")[0]) > 0) {
            bi.testing.setVisibility(View.GONE);
        } else {
            bi.testing.setVisibility(View.VISIBLE);
        }

        setUIContent();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = null;
        switch (item.getItemId()) {
            case R.id.onSync:
                intent = new Intent(MainActivity.this, SyncActivity.class);
                break;
         /*   case R.id.checkOpenForms:
                intent = new Intent(MainActivity.this, PendingFormsActivity.class);
                break;*/
            case R.id.formsReportDate:
                intent = new Intent(MainActivity.this, FormsReportDate.class);
                break;
        /*    case R.id.formsReportCluster:
                intent = new Intent(MainActivity.this, FormsReportCluster.class);
                break;*/
        }
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.item_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void callWarningActivity() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        if (exit) {
            finish(); // finish activity
            startActivity(new Intent(this, LoginActivity.class));
        } else {
            Toast.makeText(this, "Press Back again to Exit.",
                    Toast.LENGTH_SHORT).show();
            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                }
            }, 3 * 1000);

        }
    }

    private void showDialog(String newVer, String preVer) {
        AppUtilsKt.openWarningActivity(
                this,
                getString(R.string.app_name) + " APP is available!",
                getString(R.string.app_name) + " App Ver." + newVer + " is now available. Your are currently using older Ver." + preVer + ".\nInstall new version to use this app.",
                "Install",
                "Cancel"
        );
    }

    public void openSpecificActivity(View v) {
        Intent oF = null;
        switch (v.getId()) {
            case R.id.formA:
                oF = new Intent(this, SectionBActivity.class).putExtra(VILLAGES_DATA, village);
                break;
            case R.id.formC:
                oF = new Intent(this, SectionCActivity.class);
                break;
            case R.id.formD:
                oF = new Intent(this, SectionDActivity.class);
                break;
            case R.id.formE:
                oF = new Intent(this, SectionE01Activity.class);
                break;
            case R.id.formF:
                oF = new Intent(this, SectionF01Activity.class);
                break;
            case R.id.formG:
                oF = new Intent(this, SectionG01Activity.class);
                break;
            case R.id.formH:
                oF = new Intent(this, SectionH01Activity.class);
                break;
            case R.id.formI:
                oF = new Intent(this, SectionI01Activity.class);
                break;
            case R.id.formJ:
                oF = new Intent(this, SectionJ01Activity.class);
                break;
            case R.id.formK:
                oF = new Intent(this, SectionKActivity.class);
                break;
            case R.id.formL:
                oF = new Intent(this, SectionLActivity.class);
                break;
            case R.id.formN:
                oF = new Intent(this, SectionNActivity.class);
                break;
            case R.id.databaseBtn:
                oF = new Intent(this, AndroidDatabaseManager.class);
                break;
            case R.id.uploadData:
                if (!AndroidUtilityKt.isNetworkConnected(this)) {
                    Toast.makeText(this, "No network connection available!", Toast.LENGTH_SHORT).show();
                    return;
                }
                oF = new Intent(this, SyncActivity.class);
                break;
        }
        startActivity(oF);
    }

    public void toggleSummary(View view) {

        if (bi.recordSummary.getVisibility() == View.VISIBLE) {
            bi.recordSummary.setVisibility(View.GONE);
        } else {
            bi.recordSummary.setVisibility(View.VISIBLE);
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        gettingAreaData();
    }

    //Other Dependent Functions
    private void setUIContent() {
        bi.spArea.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                onSettingDropDownContent(false);
                if (i == 0) {
                    bi.spVillage.setEnabled(false);
                    bi.spVillage.setSelection(0);
                    return;
                }
                initializingVillageVariables();
                for (VillageContract item : areaList) {
                    if (item.getArea_code().equals(bi.spArea.getSelectedItem().toString())) {
                        villageName.add(item.getVillage_name());
                        villageMap.put(item.getVillage_name(), item);
                    }
                }
                bi.spVillage.setAdapter(new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_dropdown_item, villageName));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        bi.spVillage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) {
                    onSettingDropDownContent(false);
                    return;
                }
                village = villageMap.get(bi.spVillage.getSelectedItem().toString());
                onSettingDropDownContent(true);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void onSettingDropDownContent(boolean enable) {
        bi.formA.setEnabled(enable);
    }

    private void initializingAreaVariables() {
        areaName = new ArrayList<String>() {
            {
                add("....");
            }
        };
        areaList = new ArrayList<>();
    }

    private void initializingVillageVariables() {
        villageName = new ArrayList<String>() {
            {
                add("....");
            }
        };
        villageMap = new HashMap<>();
        bi.spVillage.setEnabled(true);
    }

    //Reactive Streams
    private Observable<List<VillageContract>> getAreas() {
        return Observable.create(emitter -> {
            emitter.onNext(appInfo.getDbHelper().getEnumBlock(MainApp.UC_ID));
            emitter.onComplete();
        });
    }

    //Getting data from db
    public void gettingAreaData() {
        initializingAreaVariables();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, areaName);
        bi.spArea.setAdapter(adapter);
        getAreas()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<VillageContract>>() {
                    Disposable disposable;

                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(List<VillageContract> vContract) {
                        for (VillageContract village : vContract) {
                            if (!areaName.contains(village.getArea_code()))
                                areaName.add(village.getArea_code());
                            areaList.add(village);
                        }
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                        disposable.dispose();
                    }
                });
    }
}
