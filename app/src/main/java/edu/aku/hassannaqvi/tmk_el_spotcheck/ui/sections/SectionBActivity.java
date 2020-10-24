package edu.aku.hassannaqvi.tmk_el_spotcheck.ui.sections;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.validatorcrawler.aliazaz.Clear;
import com.validatorcrawler.aliazaz.Validator;

import org.json.JSONException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import edu.aku.hassannaqvi.tmk_el_spotcheck.R;
import edu.aku.hassannaqvi.tmk_el_spotcheck.contracts.FormsContract;
import edu.aku.hassannaqvi.tmk_el_spotcheck.contracts.VillageContract;
import edu.aku.hassannaqvi.tmk_el_spotcheck.core.DatabaseHelper;
import edu.aku.hassannaqvi.tmk_el_spotcheck.core.MainApp;
import edu.aku.hassannaqvi.tmk_el_spotcheck.databinding.ActivitySectionBBinding;
import edu.aku.hassannaqvi.tmk_el_spotcheck.models.BLRandom;
import edu.aku.hassannaqvi.tmk_el_spotcheck.models.Form;
import edu.aku.hassannaqvi.tmk_el_spotcheck.utils.AppUtilsKt;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static edu.aku.hassannaqvi.tmk_el_spotcheck.CONSTANTS.VILLAGES_DATA;
import static edu.aku.hassannaqvi.tmk_el_spotcheck.core.MainApp.appInfo;
import static edu.aku.hassannaqvi.tmk_el_spotcheck.core.MainApp.form;


public class SectionBActivity extends AppCompatActivity {

    ActivitySectionBBinding bi;
    BLRandom bl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_b);
        bi.setCallback(this);
        setupSkip();
        VillageContract village = (VillageContract) getIntent().getSerializableExtra(VILLAGES_DATA);
        bi.elb1.setText(village.getCluster_code());
        bi.elb6.setText(getTalukaName(Integer.parseInt(MainApp.SELECTED_UC.getTaluka_code())));
        bi.elb7.setText(MainApp.SELECTED_UC.getUc_name());
        bi.elb8.setText(village.getVillage_name());
        bi.elb8a.setText(village.getVillage_code());
    }

    private String getTalukaName(int index) {
        switch (index) {
            case 1:
                return "Tando Mohammad Khan";
            case 2:
                return "Tando Ghulam Hyder";
            case 3:
                return "Bulri Shah Karim";
            default:
                return "Not Found";
        }
    }


    private void setupSkip() {

    }


    public void BtnContinue() {
        if (!formValidation()) return;
        try {
            SaveDraft();
            if (UpdateDB()) {
                finish();
                startActivity(new Intent(this, SectionCActivity.class));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private boolean UpdateDB() {
        DatabaseHelper db = MainApp.appInfo.getDbHelper();
        long updcount = db.addForm(form);
        form.set_ID(String.valueOf(updcount));
        if (updcount > 0) {
            form.set_UID(form.getDeviceID() + form.get_ID());
            db.updatesFormColumn(FormsContract.FormsTable.COLUMN_UID, form.get_UID());
            return true;
        } else {
            Toast.makeText(this, "Sorry. You can't go further.\n Please contact IT Team (Failed to update DB)", Toast.LENGTH_SHORT).show();
            return false;
        }
    }


    private void SaveDraft() throws JSONException {

        form = new Form();
        form.setSysdate(new SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.getDefault()).format(new Date().getTime()));
        form.setUsername(MainApp.userName);
        form.setDeviceID(MainApp.appInfo.getDeviceID());
        form.setDevicetagID(MainApp.appInfo.getTagName());
        form.setAppversion(MainApp.appInfo.getAppVersion());

        form.setElb1(bi.elb1.getText().toString());

        form.setElb2(bi.elb201.isChecked() ? "1"
                : bi.elb202.isChecked() ? "2"
                : bi.elb203.isChecked() ? "3"
                : "-1");

        form.setElb4(bi.elb4.getText().toString());

        form.setElb5(bi.elb5.getText().toString());

        form.setElb6(bi.elb6.getText().toString());

        form.setElb7(bi.elb7.getText().toString());

        form.setElb8(bi.elb8.getText().toString());

        form.setElb8a(bi.elb8a.getText().toString());

        form.setElb09(bi.elb0901.isChecked() ? "1"
                : bi.elb0902.isChecked() ? "2"
                : bi.elb0903.isChecked() ? "3"
                : "-1");

        form.setElb11(bi.elb11.getText().toString());

        form.setElb12(bi.elb12.getText().toString());

        MainApp.setGPS(this);
    }


    private boolean formValidation() {
        return Validator.emptyCheckingContainer(this, bi.GrpName);
    }


    public void BtnEnd() {
        AppUtilsKt.openEndActivity(this);
    }

    public void CheckHH(View v) {
        resetVariables(View.VISIBLE);
    }

    private void resetVariables(int visibility) {
        bi.fldGrpElb11.setVisibility(visibility);
        Clear.clearAllFields(bi.fldGrpElb11);
    }

    public void elb11OnTextChanged(CharSequence s, int start, int before, int count) {
        resetVariables(View.GONE);
    }


    //Reactive Streams
    private Observable<BLRandom> getBLRandom() {
        return Observable.create(emitter -> {
            emitter.onNext(appInfo.getDbHelper().getHHFromBLRandom(bi.elb8a.getText().toString(), bi.elb11.getText().toString()));
            emitter.onComplete();
        });
    }

    //Getting data from db
    public void gettingAreaData() {
        getBLRandom()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BLRandom>() {
                    Disposable disposable;

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(@NonNull BLRandom blRandom) {
                        bl = blRandom;
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        disposable.dispose();
                    }

                    @Override
                    public void onComplete() {
                        disposable.dispose();
                    }
                });
    }


}