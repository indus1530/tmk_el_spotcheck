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
import edu.aku.hassannaqvi.tmk_el_spotcheck.databinding.ActivitySectionMBinding;
import edu.aku.hassannaqvi.tmk_el_spotcheck.ui.other.EndingActivity;
import edu.aku.hassannaqvi.tmk_el_spotcheck.utils.AppUtilsKt;

import static edu.aku.hassannaqvi.tmk_el_spotcheck.core.MainApp.form;

public class SectionMActivity extends AppCompatActivity {

    ActivitySectionMBinding bi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_m);
        bi.setCallback(this);
        setupSkip();
    }

    private void setupSkip() {

        bi.elm01.setOnCheckedChangeListener((group, checkedId) -> {
            Clear.clearAllFields(bi.fldGrpCVelm02);
            Clear.clearAllFields(bi.fldGrpCVelm03);
            bi.fldGrpCVelm02.setVisibility(View.VISIBLE);
            bi.fldGrpCVelm03.setVisibility(View.VISIBLE);
            if (checkedId == bi.elm0103.getId()) {
                bi.fldGrpCVelm02.setVisibility(View.GONE);
                bi.fldGrpCVelm03.setVisibility(View.GONE);
            }
        });

    }


    public void BtnContinue() {
        if (!formValidation()) return;
        try {
            SaveDraft();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (UpdateDB()) {
            finish();
            startActivity(new Intent(this, EndingActivity.class).putExtra("complete", true));
        } else {
            Toast.makeText(this, "Sorry. You can't go further.\n Please contact IT Team (Failed to update DB)", Toast.LENGTH_SHORT).show();
        }
    }


    private boolean UpdateDB() {
        DatabaseHelper db = MainApp.appInfo.getDbHelper();
        int updcount = db.updatesFormColumn(FormsContract.FormsTable.COLUMN_SC, MainApp.form.getsC());
        return updcount == 1;
    }


    private void SaveDraft() throws JSONException {

        JSONObject json = new JSONObject();

        json.put("elm01", bi.elm0101.isChecked() ? "1"
                : bi.elm0102.isChecked() ? "2"
                : bi.elm0103.isChecked() ? "3"
                : "-1");

        json.put("elm02", bi.elm0201.isChecked() ? "1"
                : bi.elm0202.isChecked() ? "2"
                : "-1");

        json.put("elm03", bi.elm0301.isChecked() ? "1"
                : bi.elm0302.isChecked() ? "2"
                : "-1");

        json.put("elmm04", bi.elmm0401.isChecked() ? "1"
                : bi.elmm0402.isChecked() ? "2"
                : "-1");

        json.put("elmm05", bi.elmm0501.isChecked() ? "1"
                : bi.elmm0502.isChecked() ? "2"
                : "-1");

        json.put("elmm06", bi.elmm0601.isChecked() ? "1"
                : bi.elmm0602.isChecked() ? "2"
                : bi.elmm0603.isChecked() ? "3"
                : "-1");

        json.put("elmm07", bi.elmm0701.isChecked() ? "1"
                : bi.elmm0702.isChecked() ? "2"
                : "-1");

        json.put("elmm08", bi.elmm0801.isChecked() ? "1"
                : bi.elmm0802.isChecked() ? "2"
                : "-1");

        json.put("elmm09", bi.elmm0901.isChecked() ? "1"
                : bi.elmm0902.isChecked() ? "2"
                : "-1");

        json.put("elmm10", bi.elmm1001.isChecked() ? "1"
                : bi.elmm1002.isChecked() ? "2"
                : bi.elmm1003.isChecked() ? "3"
                : "-1");

        json.put("elmm11", bi.elmm1101.isChecked() ? "1"
                : bi.elmm1102.isChecked() ? "2"
                : "-1");

        json.put("elmm12", bi.elmm1201.isChecked() ? "1"
                : bi.elmm1202.isChecked() ? "2"
                : "-1");

        form.setsC(json.toString());


    }


    private boolean formValidation() {
        return Validator.emptyCheckingContainer(this, bi.GrpName);
    }


    public void BtnEnd() {
        AppUtilsKt.openEndActivity(this);
    }

}