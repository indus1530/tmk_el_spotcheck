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
import edu.aku.hassannaqvi.tmk_el_spotcheck.databinding.ActivitySectionNBinding;
import edu.aku.hassannaqvi.tmk_el_spotcheck.ui.other.MainActivity;
import edu.aku.hassannaqvi.tmk_el_spotcheck.utils.AppUtilsKt;

public class SectionNActivity extends AppCompatActivity {

    ActivitySectionNBinding bi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_n);
        bi.setCallback(this);
        setupSkip();
    }

    private void setupSkip() {

        bi.can1.setOnCheckedChangeListener((group, id) -> {
            Clear.clearAllFields(bi.fldGrpCVcan2);
            Clear.clearAllFields(bi.fldGrpCVcan3);
            Clear.clearAllFields(bi.fldGrpCVcan4);
            Clear.clearAllFields(bi.fldGrpCVcan5);
            bi.fldGrpCVcan2.setVisibility(View.VISIBLE);
            bi.fldGrpCVcan3.setVisibility(View.VISIBLE);
            bi.fldGrpCVcan4.setVisibility(View.VISIBLE);
            bi.fldGrpCVcan5.setVisibility(View.VISIBLE);
            if (id == bi.can1.getId()) {
                bi.fldGrpCVcan2.setVisibility(View.GONE);
                bi.fldGrpCVcan3.setVisibility(View.GONE);
                bi.fldGrpCVcan4.setVisibility(View.GONE);
                bi.fldGrpCVcan5.setVisibility(View.GONE);
            }
        });


        bi.can4.setOnCheckedChangeListener((group, checkedId) -> {
            Clear.clearAllFields(bi.fldGrpCVcan5);
            bi.fldGrpCVcan5.setVisibility(View.VISIBLE);
            if (checkedId == bi.can402.getId()) {
                bi.fldGrpCVcan5.setVisibility(View.GONE);
            }
        });


        bi.can9.setOnCheckedChangeListener((group, checkedId) -> {
            Clear.clearAllFields(bi.llcan10t21);
            bi.llcan10t21.setVisibility(View.GONE);
            if (checkedId == bi.can901.getId()) {
                bi.llcan10t21.setVisibility(View.VISIBLE);
            }
        });


        bi.can12.setOnCheckedChangeListener((group, checkedId) -> {
            Clear.clearAllFields(bi.fldGrpCVcan13);
            bi.fldGrpCVcan13.setVisibility(View.VISIBLE);
            if (checkedId == bi.can1202.getId()) {
                bi.fldGrpCVcan13.setVisibility(View.GONE);
            }
        });


        bi.can16.setOnCheckedChangeListener((group, checkedId) -> {
            Clear.clearAllFields(bi.fldGrpCVcan17);
            bi.fldGrpCVcan17.setVisibility(View.VISIBLE);
            if (checkedId == bi.can1602.getId()) {
                bi.fldGrpCVcan17.setVisibility(View.GONE);
            }
        });


        bi.can20.setOnCheckedChangeListener((group, checkedId) -> {
            Clear.clearAllFields(bi.fldGrpCVcan21);
            bi.fldGrpCVcan21.setVisibility(View.VISIBLE);
            if (checkedId == bi.can2002.getId()) {
                bi.fldGrpCVcan21.setVisibility(View.GONE);
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
            startActivity(new Intent(this, MainActivity.class).putExtra("complete", true));
        } else {
            Toast.makeText(this, "Sorry. You can't go further.\n Please contact IT Team (Failed to update DB)", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean UpdateDB() {
        /*DatabaseHelper db = MainApp.appInfo.getDbHelper();
        long updcount = db.addForm(form);
        form.set_ID(String.valueOf(updcount));
        if (updcount > 0) {
            form.set_UID(form.getDeviceID() + form.get_ID());
            db.updatesFormColumn(FormsContract.FormsTable.COLUMN_UID, form.get_UID());
            return true;
        } else {
            Toast.makeText(this, "Sorry. You can't go further.\n Please contact IT Team (Failed to update DB)", Toast.LENGTH_SHORT).show();
            return false;
        }*/
        return true;
    }

    private void SaveDraft() throws JSONException {

        JSONObject json = new JSONObject();
        json.put("can1", bi.can101.isChecked() ? "1"
                : bi.can102.isChecked() ? "2"
                : "-1");

        json.put("can2", bi.can2.getText().toString());

        json.put("can3", bi.can3.getText().toString());

        json.put("can4", bi.can401.isChecked() ? "1"
                : bi.can402.isChecked() ? "2"
                : bi.can403.isChecked() ? "3"
                : "-1");

        json.put("can5", bi.can501.isChecked() ? "1"
                : bi.can502.isChecked() ? "98"
                : "-1");

        json.put("can501x", bi.can501x.getText().toString());
        json.put("can6", bi.can6.getText().toString());

        json.put("can7", bi.can701.isChecked() ? "1"
                : bi.can702.isChecked() ? "2"
                : bi.can703.isChecked() ? "3"
                : "-1");

        json.put("can8", bi.can801.isChecked() ? "1"
                : bi.can802.isChecked() ? "2"
                : bi.can803.isChecked() ? "3"
                : "-1");

        json.put("can9", bi.can901.isChecked() ? "1"
                : bi.can902.isChecked() ? "2"
                : bi.can903.isChecked() ? "3"
                : "-1");

        json.put("can10", bi.can10.getText().toString());

        json.put("can11", bi.can11.getText().toString());

        json.put("can12", bi.can1201.isChecked() ? "1"
                : bi.can1202.isChecked() ? "2"
                : "-1");

        json.put("can13", bi.can13.getText().toString());

        json.put("can14", bi.can14.getText().toString());

        json.put("can15", bi.can15.getText().toString());

        json.put("can16", bi.can1601.isChecked() ? "1"
                : bi.can1602.isChecked() ? "2"
                : "-1");

        json.put("can17", bi.can17.getText().toString());

        json.put("can18", bi.can18.getText().toString());

        json.put("can19", bi.can19.getText().toString());

        json.put("can20", bi.can2001.isChecked() ? "1"
                : bi.can2002.isChecked() ? "2"
                : "-1");

        json.put("can21", bi.can21.getText().toString());


    }

    private boolean formValidation() {
        return Validator.emptyCheckingContainer(this, bi.GrpName);
    }

    public void BtnEnd() {
        AppUtilsKt.openEndActivity(this);
    }

}