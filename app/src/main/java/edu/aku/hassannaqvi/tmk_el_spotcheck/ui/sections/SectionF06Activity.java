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
import edu.aku.hassannaqvi.tmk_el_spotcheck.databinding.ActivitySectionF06Binding;
import edu.aku.hassannaqvi.tmk_el_spotcheck.ui.list_activity.FamilyMembersListActivity;
import edu.aku.hassannaqvi.tmk_el_spotcheck.utils.AppUtilsKt;
import edu.aku.hassannaqvi.tmk_el_spotcheck.utils.JSONUtils;

public class SectionF06Activity extends AppCompatActivity {

    ActivitySectionF06Binding bi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_f06);
        bi.setCallback(this);
        setupSkip();
    }


    private void setupSkip() {
        bi.cmf10.setOnCheckedChangeListener((group, id) -> Clear.clearAllFields(bi.llcmf11t12));
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
            boolean routeFlag = FamilyMembersListActivity.mainVModel.getChildLstU5().getValue() != null && FamilyMembersListActivity.mainVModel.getChildLstU5().getValue().size() > 0;
            startActivity(new Intent(this, routeFlag ? SectionG01Activity.class : SectionKActivity.class));
        } else {
            Toast.makeText(this, "Sorry. You can't go further.\n Please contact IT Team (Failed to update DB)", Toast.LENGTH_SHORT).show();
        }
    }


    private boolean UpdateDB() {
        DatabaseHelper db = MainApp.appInfo.getDbHelper();
        int updcount = db.updatesFormColumn(FormsContract.FormsTable.COLUMN_SF, MainApp.form.getsF());
        return updcount == 1;
    }


    private void SaveDraft() throws JSONException {

        JSONObject json = new JSONObject();

        json.put("cmf10", bi.cmf1001.isChecked() ? "1"
                : bi.cmf1002.isChecked() ? "2"
                : bi.cmf1003.isChecked() ? "66"
                : bi.cmf1098.isChecked() ? "98"
                : "-1");

        json.put("cmf11", bi.cmf1101.isChecked() ? "1"
                : bi.cmf1102.isChecked() ? "2"
                : bi.cmf1103.isChecked() ? "3"
                : bi.cmf1104.isChecked() ? "4"
                : bi.cmf1196.isChecked() ? "96"
                : "-1");
        json.put("cmf1196x", bi.cmf1196x.getText().toString());

        json.put("cmf1201", bi.cmf1201.isChecked() ? "1" : "-1");
        json.put("cmf1202", bi.cmf1202.isChecked() ? "2" : "-1");
        json.put("cmf1203", bi.cmf1203.isChecked() ? "3" : "-1");
        json.put("cmf1204", bi.cmf1204.isChecked() ? "4" : "-1");
        json.put("cmf1205", bi.cmf1205.isChecked() ? "5" : "-1");
        json.put("cmf1206", bi.cmf1206.isChecked() ? "6" : "-1");

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