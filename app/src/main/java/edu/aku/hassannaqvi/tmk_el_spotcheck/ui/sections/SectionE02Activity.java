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
import edu.aku.hassannaqvi.tmk_el_spotcheck.databinding.ActivitySectionE02Binding;
import edu.aku.hassannaqvi.tmk_el_spotcheck.ui.list_activity.FamilyMembersListActivity;
import edu.aku.hassannaqvi.tmk_el_spotcheck.utils.AppUtilsKt;
import edu.aku.hassannaqvi.tmk_el_spotcheck.utils.JSONUtils;

public class SectionE02Activity extends AppCompatActivity {

    ActivitySectionE02Binding bi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_e02);
        bi.setCallback(this);

        setupSkip();
    }

    private void setupSkip() {
        bi.ele11.setOnCheckedChangeListener((radioGroup, i) -> Clear.clearAllFields(bi.ele12cv));
        bi.ele13.setOnCheckedChangeListener((radioGroup, i) -> Clear.clearAllFields(bi.ele14cv));

        bi.ele1905.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                Clear.clearAllFields(bi.ele19check, false);
                Clear.clearAllFields(bi.fldGrpCVele20);
                bi.fldGrpCVele20.setVisibility(View.GONE);

            } else {
                Clear.clearAllFields(bi.ele19check, true);
                bi.fldGrpCVele20.setVisibility(View.VISIBLE);
            }
        });

    }


    public void BtnContinue() {
        if (!formValidation()) return;
        try {
            SaveDraft();
            if (UpdateDB()) {
                finish();
                boolean routeFlag = FamilyMembersListActivity.mainVModel.getMwraLst().getValue() != null && FamilyMembersListActivity.mainVModel.getMwraLst().getValue().size() > 0;
                startActivity(new Intent(this, routeFlag ? SectionF01Activity.class : SectionF02Activity.class));
            } else {
                Toast.makeText(this, "Sorry. You can't go further.\n Please contact IT Team (Failed to update DB)", Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private boolean UpdateDB() {
        DatabaseHelper db = MainApp.appInfo.getDbHelper();
        int updcount = db.updatesFormColumn(FormsContract.FormsTable.COLUMN_SE, MainApp.form.getsE());
        return updcount == 1;
    }


    private void SaveDraft() throws JSONException {

        JSONObject json = new JSONObject();

        json.put("ele11", bi.ele1101.isChecked() ? "1"
                : bi.ele1102.isChecked() ? "2"
                : bi.ele1103.isChecked() ? "98"
                : "-1");

        json.put("ele12", bi.ele1201.isChecked() ? "1"
                : bi.ele1202.isChecked() ? "2"
                : bi.ele1203.isChecked() ? "3"
                : "-1");
        json.put("ele1201x", bi.ele1201x.getText().toString().trim().isEmpty() ? "-1" : bi.ele1201x.getText().toString());
        json.put("ele1202x", bi.ele1202x.getText().toString().trim().isEmpty() ? "-1" : bi.ele1202x.getText().toString());

        json.put("ele13", bi.ele1301.isChecked() ? "1"
                : bi.ele1302.isChecked() ? "2"
                : "-1");

        json.put("ele1401", bi.ele1401.getText().toString().trim().isEmpty() ? "-1" : bi.ele1401.getText().toString());
        json.put("ele1402", bi.ele1402.getText().toString().trim().isEmpty() ? "-1" : bi.ele1402.getText().toString());
        json.put("ele1403", bi.ele1403.getText().toString().trim().isEmpty() ? "-1" : bi.ele1403.getText().toString());
        json.put("ele1404", bi.ele1404.getText().toString().trim().isEmpty() ? "-1" : bi.ele1404.getText().toString());
        json.put("ele1405", bi.ele1405.getText().toString().trim().isEmpty() ? "-1" : bi.ele1405.getText().toString());
        json.put("ele1406", bi.ele1406.getText().toString().trim().isEmpty() ? "-1" : bi.ele1406.getText().toString());

        json.put("ele15", bi.ele1501.isChecked() ? "1"
                : bi.ele1502.isChecked() ? "2"
                : bi.ele15098.isChecked() ? "98"
                : "-1");

        json.put("ele16", bi.ele1601.isChecked() ? "1"
                : bi.ele1602.isChecked() ? "2"
                : bi.ele1603.isChecked() ? "3"
                : bi.ele1604.isChecked() ? "4"
                : bi.ele1605.isChecked() ? "5"
                : bi.ele1606.isChecked() ? "6"
                : bi.ele1607.isChecked() ? "7"
                : bi.ele1608.isChecked() ? "8"
                : bi.ele1609.isChecked() ? "9"
                : bi.ele1610.isChecked() ? "10"
                : bi.ele1611.isChecked() ? "11"
                : bi.ele1696.isChecked() ? "96"
                : "-1");
        json.put("ele1696x", bi.ele1696x.getText().toString().trim().isEmpty() ? "-1" : bi.ele1696x.getText().toString());

        json.put("ele17", bi.ele1701.isChecked() ? "1"
                : bi.ele1702.isChecked() ? "2"
                : bi.ele1798.isChecked() ? "98"
                : "-1");
        json.put("ele1701x", bi.ele1701x.getText().toString().trim().isEmpty() ? "-1" : bi.ele1701x.getText().toString());

        json.put("ele18", bi.ele1801.isChecked() ? "1"
                : bi.ele1802.isChecked() ? "2"
                : bi.ele1803.isChecked() ? "3"
                : bi.ele1804.isChecked() ? "4"
                : bi.ele1896.isChecked() ? "96"
                : "-1");
        json.put("ele1896x", bi.ele1896x.getText().toString().trim().isEmpty() ? "-1" : bi.ele1896x.getText().toString());

        json.put("ele1901", bi.ele1901.isChecked() ? "1" : "-1");
        json.put("ele1902", bi.ele1902.isChecked() ? "2" : "-1");
        json.put("ele1903", bi.ele1903.isChecked() ? "3" : "-1");
        json.put("ele1904", bi.ele1904.isChecked() ? "4" : "-1");
        json.put("ele1905", bi.ele1905.isChecked() ? "5" : "-1");
        json.put("ele1996", bi.ele1996.isChecked() ? "96" : "-1");
        json.put("ele1996x", bi.ele1996x.getText().toString().trim().isEmpty() ? "-1" : bi.ele1996x.getText().toString());

        json.put("ele2001", bi.ele2001.isChecked() ? "1" : "-1");
        json.put("ele2002", bi.ele2002.isChecked() ? "2" : "-1");
        json.put("ele2003", bi.ele2003.isChecked() ? "3" : "-1");
        json.put("ele2096", bi.ele2096.isChecked() ? "96" : "-1");
        json.put("ele2096x", bi.ele2096x.getText().toString().trim().isEmpty() ? "-1" : bi.ele2096x.getText().toString());

        try {
            JSONObject json_merge = JSONUtils.mergeJSONObjects(new JSONObject(MainApp.form.getsE()), json);
            MainApp.form.setsE(String.valueOf(json_merge));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private boolean formValidation() {

        if (!bi.ele1401.getText().toString().trim().isEmpty()
                && !bi.ele1402.getText().toString().trim().isEmpty()
                && !bi.ele1403.getText().toString().trim().isEmpty()
                && !bi.ele1404.getText().toString().trim().isEmpty()
                && !bi.ele1405.getText().toString().trim().isEmpty()
                && !bi.ele1406.getText().toString().trim().isEmpty()) {

            if ((Integer.parseInt(bi.ele1401.getText().toString())
                    + Integer.parseInt(bi.ele1402.getText().toString())
                    + Integer.parseInt(bi.ele1403.getText().toString())
                    + Integer.parseInt(bi.ele1404.getText().toString())
                    + Integer.parseInt(bi.ele1405.getText().toString())
                    + Integer.parseInt(bi.ele1406.getText().toString())) == 0) {
                Toast.makeText(this, "Question ELE14 \nAll options Can't be ZERO!", Toast.LENGTH_SHORT).show();
                return false;
            }
        }

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