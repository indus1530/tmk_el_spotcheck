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
import org.json.JSONObject;

import edu.aku.hassannaqvi.tmk_el_spotcheck.R;
import edu.aku.hassannaqvi.tmk_el_spotcheck.contracts.FormsContract;
import edu.aku.hassannaqvi.tmk_el_spotcheck.core.DatabaseHelper;
import edu.aku.hassannaqvi.tmk_el_spotcheck.core.MainApp;
import edu.aku.hassannaqvi.tmk_el_spotcheck.databinding.ActivitySectionLBinding;
import edu.aku.hassannaqvi.tmk_el_spotcheck.ui.other.EndingActivity;
import edu.aku.hassannaqvi.tmk_el_spotcheck.utils.AppUtilsKt;

public class SectionLActivity extends AppCompatActivity {

    ActivitySectionLBinding bi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_l);
        bi.setCallback(this);
        setupSkip();

    }


    private void setupSkip() {

        bi.hwl1.setOnCheckedChangeListener((radioGroup, i) -> Clear.clearAllFields(bi.llhwl2t4));


        bi.hwl3.setOnCheckedChangeListener(((radioGroup, i) -> {

            Clear.clearAllFields(bi.fldGrpCVhwl4);
            Clear.clearAllFields(bi.fldGrpCVhwl5);
            Clear.clearAllFields(bi.fldGrpCVhwl6);
            Clear.clearAllFields(bi.fldGrpCVhwl7);
            bi.fldGrpCVhwl4.setVisibility(View.GONE);
            bi.fldGrpCVhwl5.setVisibility(View.GONE);
            bi.fldGrpCVhwl6.setVisibility(View.GONE);
            bi.fldGrpCVhwl7.setVisibility(View.GONE);

            if (i == bi.hwl301.getId()) {
                bi.fldGrpCVhwl4.setVisibility(View.VISIBLE);
            } else {
                bi.fldGrpCVhwl5.setVisibility(View.VISIBLE);
                bi.fldGrpCVhwl6.setVisibility(View.VISIBLE);
                bi.fldGrpCVhwl7.setVisibility(View.VISIBLE);
            }
        }));


        bi.hwl5.setOnCheckedChangeListener(((radioGroup, i) -> {
            Clear.clearAllFields(bi.fldGrpCVhwl6);
            Clear.clearAllFields(bi.fldGrpCVhwl7);
            bi.fldGrpCVhwl6.setVisibility(View.VISIBLE);
            bi.fldGrpCVhwl7.setVisibility(View.VISIBLE);
            if (i == bi.hwl502.getId()) {
                bi.fldGrpCVhwl6.setVisibility(View.GONE);
                bi.fldGrpCVhwl7.setVisibility(View.GONE);
            }
        }));


        bi.hwl6.setOnCheckedChangeListener(((radioGroup, i) -> {
            Clear.clearAllFields(bi.fldGrpCVhwl7);
            bi.fldGrpCVhwl7.setVisibility(View.VISIBLE);
            if (i == bi.hwl602.getId()) {
                bi.fldGrpCVhwl7.setVisibility(View.GONE);
            }
        }));


        bi.hwl809.setOnCheckedChangeListener((compoundButton, b) -> {
            Clear.clearAllFields(bi.hwl8check, !b);
        });


        bi.hwl1210.setOnCheckedChangeListener((compoundButton, b) -> {
            Clear.clearAllFields(bi.hwl12check, !b);
        });

    }


    public void BtnContinue() {
        if (!formValidation()) return;
        try {
            SaveDraft();
            if (UpdateDB()) {
                finish();
                startActivity(new Intent(this, EndingActivity.class).putExtra("complete", true));
            } else {
                Toast.makeText(this, "Sorry. You can't go further.\n Please contact IT Team (Failed to update DB)", Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private boolean UpdateDB() {
        DatabaseHelper db = MainApp.appInfo.getDbHelper();
        int updcount = db.updatesFormColumn(FormsContract.FormsTable.COLUMN_SL, MainApp.form.getsL());
        return updcount == 1;
    }

    private void SaveDraft() throws JSONException {

        JSONObject json = new JSONObject();

        json.put("hwl1", bi.hwl101.isChecked() ? "1"
                : bi.hwl102.isChecked() ? "2"
                : bi.hwl103.isChecked() ? "3"
                : bi.hwl196.isChecked() ? "96"
                : "-1");

        json.put("hwl196x", bi.hwl196x.getText().toString());
        json.put("hwl2", bi.hwl201.isChecked() ? "1"
                : bi.hwl202.isChecked() ? "2"
                : "-1");

        json.put("hwl3", bi.hwl301.isChecked() ? "1"
                : bi.hwl302.isChecked() ? "2"
                : bi.hwl303.isChecked() ? "3"
                : "-1");

        json.put("hwl401", bi.hwl401.isChecked() ? "1" : "-1");

        json.put("hwl402", bi.hwl402.isChecked() ? "2" : "-1");

        json.put("hwl403", bi.hwl403.isChecked() ? "3" : "-1");

        json.put("hwl404", bi.hwl404.isChecked() ? "4" : "-1");

        json.put("hwl5", bi.hwl501.isChecked() ? "1"
                : bi.hwl502.isChecked() ? "2"
                : "-1");

        json.put("hwl6", bi.hwl601.isChecked() ? "1"
                : bi.hwl602.isChecked() ? "2"
                : "-1");

        json.put("hwl701", bi.hwl701.isChecked() ? "1" : "-1");

        json.put("hwl702", bi.hwl702.isChecked() ? "2" : "-1");

        json.put("hwl703", bi.hwl703.isChecked() ? "3" : "-1");

        json.put("hwl704", bi.hwl704.isChecked() ? "4" : "-1");

//        json.put("hwl8a",bi.hwl8a.isChecked() ? "" :"-1");

        json.put("hwl801", bi.hwl801.isChecked() ? "1" : "-1");

        json.put("hwl802", bi.hwl802.isChecked() ? "2" : "-1");

        json.put("hwl803", bi.hwl803.isChecked() ? "3" : "-1");

        json.put("hwl804", bi.hwl804.isChecked() ? "4" : "-1");

        json.put("hwl805", bi.hwl805.isChecked() ? "5" : "-1");

        json.put("hwl806", bi.hwl806.isChecked() ? "6" : "-1");

        json.put("hwl807", bi.hwl807.isChecked() ? "7" : "-1");

        json.put("hwl808", bi.hwl808.isChecked() ? "8" : "-1");

        json.put("hwl809", bi.hwl809.isChecked() ? "9" : "-1");

        json.put("hwl9", bi.hwl901.isChecked() ? "1"
                : bi.hwl902.isChecked() ? "2"
                : bi.hwl903.isChecked() ? "3"
                : bi.hwl904.isChecked() ? "4"
                : bi.hwl996.isChecked() ? "96"
                : "-1");

        json.put("hwl996x", bi.hwl996x.getText().toString());
        json.put("hwl10", bi.hwl1001.isChecked() ? "1"
                : bi.hwl1002.isChecked() ? "2"
                : bi.hwl1003.isChecked() ? "3"
                : bi.hwl1004.isChecked() ? "4"
                : bi.hwl1096.isChecked() ? "96"
                : "-1");

        json.put("hwl1096x", bi.hwl1096x.getText().toString());
        json.put("hwl11", bi.hwl1101.isChecked() ? "1"
                : bi.hwl1102.isChecked() ? "2"
                : bi.hwl1103.isChecked() ? "3"
                : bi.hwl1196.isChecked() ? "96"
                : "-1");

        json.put("hwl1196x", bi.hwl1196x.getText().toString());
        json.put("hwl11a", bi.hwl11a.getText().toString());

//        json.put("hwl12title",bi.hwl12title.isChecked() ? "" :"-1");

        json.put("hwl1201", bi.hwl1201.isChecked() ? "1" : "-1");

        json.put("hwl1202", bi.hwl1202.isChecked() ? "2" : "-1");

        json.put("hwl1203", bi.hwl1203.isChecked() ? "3" : "-1");

        json.put("hwl1204", bi.hwl1204.isChecked() ? "4" : "-1");

        json.put("hwl1205", bi.hwl1205.isChecked() ? "5" : "-1");

        json.put("hwl1206", bi.hwl1206.isChecked() ? "6" : "-1");

        json.put("hwl1207", bi.hwl1207.isChecked() ? "7" : "-1");

        json.put("hwl1208", bi.hwl1208.isChecked() ? "8" : "-1");

        json.put("hwl1209", bi.hwl1209.isChecked() ? "9" : "-1");

        json.put("hwl1210", bi.hwl1210.isChecked() ? "10" : "-1");

        json.put("hwl13", bi.hwl1301.isChecked() ? "1"
                : bi.hwl1302.isChecked() ? "2"
                : bi.hwl1303.isChecked() ? "3"
                : bi.hwl1304.isChecked() ? "4"
                : bi.hwl1396.isChecked() ? "96"
                : "-1");

        json.put("hwl1396x", bi.hwl1396x.getText().toString());
        json.put("hwl14", bi.hwl1401.isChecked() ? "1"
                : bi.hwl1402.isChecked() ? "2"
                : bi.hwl1403.isChecked() ? "3"
                : bi.hwl1404.isChecked() ? "4"
                : bi.hwl1496.isChecked() ? "96"
                : "-1");

        json.put("hwl1496x", bi.hwl1496x.getText().toString());
        json.put("hwl15", bi.hwl1501.isChecked() ? "1"
                : bi.hwl1502.isChecked() ? "2"
                : bi.hwl1503.isChecked() ? "3"
                : bi.hwl1596.isChecked() ? "96"
                : "-1");

        json.put("hwl1596x", bi.hwl1596x.getText().toString());

        MainApp.form.setsL(json.toString());

    }

    private boolean formValidation() {
        return Validator.emptyCheckingContainer(this, bi.GrpName);
    }

    public void BtnEnd() {
        AppUtilsKt.openEndActivity(this);
    }


    @Override
    public void onBackPressed() {
        Toast.makeText(this, "You Can't go back", Toast.LENGTH_LONG).show();
    }
}