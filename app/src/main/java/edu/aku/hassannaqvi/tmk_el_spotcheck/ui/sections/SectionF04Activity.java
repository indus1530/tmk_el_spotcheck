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
import edu.aku.hassannaqvi.tmk_el_spotcheck.databinding.ActivitySectionF04Binding;
import edu.aku.hassannaqvi.tmk_el_spotcheck.utils.AppUtilsKt;
import edu.aku.hassannaqvi.tmk_el_spotcheck.utils.JSONUtils;

import static edu.aku.hassannaqvi.tmk_el_spotcheck.CONSTANTS.C_DEATH_COUNT;

public class SectionF04Activity extends AppCompatActivity {

    ActivitySectionF04Binding bi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_f04);
        bi.setCallback(this);
        setupSkip();
    }


    private void setupSkip() {
        bi.cmf802.setOnCheckedChangeListener((group, id) -> Clear.clearAllFields(bi.fldGrpCVcmf9));
    }


    public void BtnContinue() {
        if (!formValidation()) return;
        try {
            SaveDraft();
            if (UpdateDB()) {
                finish();
                startActivity(new Intent(this, bi.cmf801.isChecked() ? SectionF05Activity.class : SectionF06Activity.class).putExtra(C_DEATH_COUNT, bi.cmf9.getText().toString()));
            } else {
                Toast.makeText(this, "Sorry. You can't go further.\n Please contact IT Team (Failed to update DB)", Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private boolean UpdateDB() {

        DatabaseHelper db = MainApp.appInfo.getDbHelper();
        int updcount = db.updatesFormColumn(FormsContract.FormsTable.COLUMN_SF, MainApp.form.getsF());
        return updcount == 1;
    }


    private void SaveDraft() throws JSONException {

        JSONObject json = new JSONObject();

        json.put("cmf8", bi.cmf801.isChecked() ? "1"
                : bi.cmf802.isChecked() ? "2"
                : "-1");

        json.put("cmf9", bi.cmf9.getText().toString());

        try {
            JSONObject json_merge = JSONUtils.mergeJSONObjects(new JSONObject(MainApp.form.getsF()), json);
            MainApp.form.setsF(String.valueOf(json_merge));
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    private boolean formValidation() {
        return Validator.emptyCheckingContainer(this, bi.GrpName);
    }


    public void BtnEnd() {
        AppUtilsKt.openEndActivity(this);
    }


    public void showTooltipView(View view) {
        AppUtilsKt.showTooltip(this, view);
    }


    @Override
    public void onBackPressed() {
        Toast.makeText(this, "You Can't go back", Toast.LENGTH_LONG).show();
    }
}