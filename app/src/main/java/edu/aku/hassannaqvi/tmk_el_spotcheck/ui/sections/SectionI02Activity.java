package edu.aku.hassannaqvi.tmk_el_spotcheck.ui.sections;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingUtil;

import com.validatorcrawler.aliazaz.Clear;
import com.validatorcrawler.aliazaz.Validator;

import org.json.JSONException;
import org.json.JSONObject;

import edu.aku.hassannaqvi.tmk_el_spotcheck.R;
import edu.aku.hassannaqvi.tmk_el_spotcheck.contracts.Mwra_ChildrenContract;
import edu.aku.hassannaqvi.tmk_el_spotcheck.core.DatabaseHelper;
import edu.aku.hassannaqvi.tmk_el_spotcheck.core.MainApp;
import edu.aku.hassannaqvi.tmk_el_spotcheck.databinding.ActivitySectionI02Binding;
import edu.aku.hassannaqvi.tmk_el_spotcheck.models.MWRA_CHILD;
import edu.aku.hassannaqvi.tmk_el_spotcheck.utils.AppUtilsKt;

import static edu.aku.hassannaqvi.tmk_el_spotcheck.CONSTANTS.ADD_IMMUNIZATION;
import static edu.aku.hassannaqvi.tmk_el_spotcheck.ui.list_activity.FamilyMembersListActivity.mainVModel;
import static edu.aku.hassannaqvi.tmk_el_spotcheck.ui.sections.SectionH01Activity.childListU2;

public class SectionI02Activity extends AppCompatActivity {

    ActivitySectionI02Binding bi;
    MWRA_CHILD mwraChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_i02);
        bi.setCallback(this);

        setupSkip();

        mwraChild = (MWRA_CHILD) getIntent().getSerializableExtra(ADD_IMMUNIZATION);
    }

    private void setupSkip() {

        /*bi.imi1.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == bi.imi101.getId()) {
                Clear.clearAllFields(bi.fldGrpCVimi2);
            }
        });*/

        /*bi.chg6.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == bi.chg601.getId()) {
                Clear.clearAllFields(bi.fldGrpCVchg7);
            }
        });*/

        radioGroupImp(bi.imi4k, bi.imi4k01, bi.fldGrpCVimi4ksrc, bi.fldGrpCVimi4kplc);
        radioGroupImp(bi.imi4l, bi.imi4l01, bi.fldGrpCVimi4lsrc, bi.fldGrpCVimi4lplc);
        radioGroupImp(bi.imi4m, bi.imi4m01, bi.fldGrpCVimi4msrc, bi.fldGrpCVimi4mplc);
        radioGroupImp(bi.imi4n, bi.imi4n01, bi.fldGrpCVimi4nsrc, bi.fldGrpCVimi4nplc);
        radioGroupImp(bi.imi4o, bi.imi4o01, bi.fldGrpCVimi4osrc, bi.fldGrpCVimi4oplc);

    }


    public void radioGroupImp(RadioGroup rg, RadioButton rb, CardView cv1, CardView cv2) {

        rg.setOnCheckedChangeListener((group, checkedId) -> {
            Clear.clearAllFields(cv1);
            Clear.clearAllFields(cv2);
            if (checkedId == rb.getId()) {
                cv1.setVisibility(View.VISIBLE);
                cv2.setVisibility(View.VISIBLE);
            } else {
                cv1.setVisibility(View.GONE);
                cv2.setVisibility(View.GONE);
            }
        });

    }


    public void BtnContinue() {
        if (!formValidation()) return;
        try {
            SaveDraft();
            if (UpdateDB()) {
                finish();
                startActivity(new Intent(this, childListU2.getFirst().size() == 0 ? mainVModel.getAllUnder2().size() > 0 ? SectionJ01Activity.class : SectionKActivity.class : SectionI01Activity.class));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private boolean UpdateDB() {
        DatabaseHelper db = MainApp.appInfo.getDbHelper();
        int updcount = db.updatesMWRAColumn(Mwra_ChildrenContract.MWRAChildTable.COLUMN_SC, mwraChild.getsC(), mwraChild.get_ID());
        return updcount == 1;
    }


    private void SaveDraft() throws JSONException {

        JSONObject json = new JSONObject();

        json.put("imi4k", bi.imi4k01.isChecked() ? "1"
                : bi.imi4k02.isChecked() ? "2"
                : "-1");

        json.put("imi4ksrc", bi.imi4ksrc01.isChecked() ? "1"
                : bi.imi4ksrc02.isChecked() ? "2"
                : "-1");

        json.put("imi4kplc", bi.imi4kplc01.isChecked() ? "1"
                : bi.imi4kplc02.isChecked() ? "2"
                : bi.imi4kplc03.isChecked() ? "3"
                : "-1");

        json.put("imi4l", bi.imi4l01.isChecked() ? "1"
                : bi.imi4l02.isChecked() ? "2"
                : "-1");

        json.put("imi4lsrc", bi.imi4lsrc01.isChecked() ? "1"
                : bi.imi4lsrc02.isChecked() ? "2"
                : "-1");

        json.put("imi4lplc", bi.imi4lplc01.isChecked() ? "1"
                : bi.imi4lplc02.isChecked() ? "2"
                : bi.imi4lplc03.isChecked() ? "3"
                : "-1");

        json.put("imi4m", bi.imi4m01.isChecked() ? "1"
                : bi.imi4m02.isChecked() ? "2"
                : "-1");

        json.put("imi4msrc", bi.imi4msrc01.isChecked() ? "1"
                : bi.imi4msrc02.isChecked() ? "2"
                : "-1");

        json.put("imi4mplc", bi.imi4mplc01.isChecked() ? "1"
                : bi.imi4mplc02.isChecked() ? "2"
                : bi.imi4mplc03.isChecked() ? "3"
                : "-1");

        json.put("imi4n", bi.imi4n01.isChecked() ? "1"
                : bi.imi4n02.isChecked() ? "2"
                : "-1");

        json.put("imi4nsrc", bi.imi4nsrc01.isChecked() ? "1"
                : bi.imi4nsrc02.isChecked() ? "2"
                : "-1");

        json.put("imi4nplc", bi.imi4nplc01.isChecked() ? "1"
                : bi.imi4nplc02.isChecked() ? "2"
                : bi.imi4nplc03.isChecked() ? "3"
                : "-1");

        json.put("imi4o", bi.imi4o01.isChecked() ? "1"
                : bi.imi4o02.isChecked() ? "2"
                : "-1");

        json.put("imi4osrc", bi.imi4osrc01.isChecked() ? "1"
                : bi.imi4osrc02.isChecked() ? "2"
                : "-1");

        json.put("imi4oplc", bi.imi4oplc01.isChecked() ? "1"
                : bi.imi4oplc02.isChecked() ? "2"
                : bi.imi4oplc03.isChecked() ? "3"
                : "-1");

        json.put("imi4o1", bi.imi4o101.isChecked() ? "1"
                : bi.imi4o102.isChecked() ? "2"
                : "-1");

        json.put("imi4o1src", bi.imi4o1src01.isChecked() ? "1"
                : bi.imi4o1src02.isChecked() ? "2"
                : "-1");

        json.put("imi4o1plc", bi.imi4o1plc01.isChecked() ? "1"
                : bi.imi4o1plc02.isChecked() ? "2"
                : bi.imi4o1plc03.isChecked() ? "3"
                : "-1");

        json.put("imi4p", bi.imi4p01.isChecked() ? "1"
                : bi.imi4p02.isChecked() ? "2"
                : "-1");

        json.put("imi4psrc", bi.imi4psrc01.isChecked() ? "1"
                : bi.imi4psrc02.isChecked() ? "2"
                : "-1");

        json.put("imi4pplc", bi.imi4pplc01.isChecked() ? "1"
                : bi.imi4pplc02.isChecked() ? "2"
                : bi.imi4pplc03.isChecked() ? "3"
                : "-1");

        mwraChild.setsC(json.toString());

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