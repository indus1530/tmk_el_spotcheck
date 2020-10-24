package edu.aku.hassannaqvi.tmk_el_spotcheck.ui.sections;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import edu.aku.hassannaqvi.tmk_el_spotcheck.CONSTANTS;
import edu.aku.hassannaqvi.tmk_el_spotcheck.R;
import edu.aku.hassannaqvi.tmk_el_spotcheck.contracts.FamilyMembersContract;
import edu.aku.hassannaqvi.tmk_el_spotcheck.contracts.Mwra_ChildrenContract;
import edu.aku.hassannaqvi.tmk_el_spotcheck.core.DatabaseHelper;
import edu.aku.hassannaqvi.tmk_el_spotcheck.core.MainApp;
import edu.aku.hassannaqvi.tmk_el_spotcheck.databinding.ActivitySectionI01Binding;
import edu.aku.hassannaqvi.tmk_el_spotcheck.models.MWRA_CHILD;
import edu.aku.hassannaqvi.tmk_el_spotcheck.utils.AppUtilsKt;

import static edu.aku.hassannaqvi.tmk_el_spotcheck.CONSTANTS.ADD_IMMUNIZATION;
import static edu.aku.hassannaqvi.tmk_el_spotcheck.ui.list_activity.FamilyMembersListActivity.mainVModel;
import static edu.aku.hassannaqvi.tmk_el_spotcheck.ui.sections.SectionH01Activity.childListU2;

public class SectionI01Activity extends AppCompatActivity {

    ActivitySectionI01Binding bi;
    MWRA_CHILD mwraChild;
    int position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_i01);
        bi.setCallback(this);

        setupSkip();
        setupContent();
    }

    private void setupContent() {
        List<String> children = new ArrayList<String>() {
            {
                add("....");
            }
        };
        for (FamilyMembersContract item : childListU2.getSecond())
            children.add(item.getName());
        bi.imi1a.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, children));
        bi.imi1a.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) return;
                position = i - 1;
                bi.i1b.setText(String.valueOf(childListU2.getFirst().get(i - 1)));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        bi.btnContinue.setText(childListU2.getFirst().size() > 1 ? "Next Child" : "Next Section");

    }

    private void setupSkip() {

        bi.imi1.setOnCheckedChangeListener((group, checkedId) -> {
            Clear.clearAllFields(bi.fldGrpCVimi2);
            Clear.clearAllFields(bi.llimi3);
        });

        /*bi.chg6.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == bi.chg601.getId()) {
                Clear.clearAllFields(bi.fldGrpCVchg7);
            }
        });*/

        radioGroupImp(bi.imi4a, bi.imi4a01, bi.fldGrpCVimi4asrc, bi.fldGrpCVimi4aplc);
        radioGroupImp(bi.imi4b, bi.imi4b01, bi.fldGrpCVimi4bsrc, bi.fldGrpCVimi4bplc);
        radioGroupImp(bi.imi4c, bi.imi4c01, bi.fldGrpCVimi4csrc, bi.fldGrpCVimi4cplc);
        radioGroupImp(bi.imi4d, bi.imi4d01, bi.fldGrpCVimi4dsrc, bi.fldGrpCVimi4dplc);
        radioGroupImp(bi.imi4e, bi.imi4e01, bi.fldGrpCVimi4esrc, bi.fldGrpCVimi4eplc);
        radioGroupImp(bi.imi4f, bi.imi4f01, bi.fldGrpCVimi4fsrc, bi.fldGrpCVimi4fplc);
        radioGroupImp(bi.imi4g, bi.imi4g01, bi.fldGrpCVimi4gsrc, bi.fldGrpCVimi4gplc);
        radioGroupImp(bi.imi4h, bi.imi4h01, bi.fldGrpCVimi4hsrc, bi.fldGrpCVimi4hplc);
        radioGroupImp(bi.imi4i, bi.imi4i01, bi.fldGrpCVimi4isrc, bi.fldGrpCVimi4iplc);
        radioGroupImp(bi.imi4j, bi.imi4j01, bi.fldGrpCVimi4jsrc, bi.fldGrpCVimi4jplc);


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
                startActivity(new Intent(this, bi.imi101.isChecked() ? SectionI02Activity.class : childListU2.getFirst().size() == 0 ? mainVModel.getAllUnder2().size() > 0 ? SectionJ01Activity.class : SectionKActivity.class : SectionI01Activity.class).putExtra(ADD_IMMUNIZATION, mwraChild));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private boolean UpdateDB() {
        DatabaseHelper db = MainApp.appInfo.getDbHelper();
        long updcount = db.addMWRACHILD(mwraChild);
        mwraChild.set_ID(String.valueOf(updcount));
        if (updcount > 0) {
            mwraChild.set_UID(mwraChild.getDeviceID() + mwraChild.get_ID());
            db.updatesMWRAColumn(Mwra_ChildrenContract.MWRAChildTable.COLUMN_UID, mwraChild.get_UID(), mwraChild.get_ID());
            return true;
        } else {
            Toast.makeText(this, "Sorry. You can't go further.\n Please contact IT Team (Failed to update DB)", Toast.LENGTH_SHORT).show();
            return false;
        }
    }


    private void SaveDraft() throws JSONException {

        mwraChild = new MWRA_CHILD();
        mwraChild.setSysdate(new SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.getDefault()).format(new Date().getTime()));
        mwraChild.setUsername(MainApp.userName);
        mwraChild.setDeviceID(MainApp.appInfo.getDeviceID());
        mwraChild.setDevicetagID(MainApp.appInfo.getTagName());
        mwraChild.setAppversion(MainApp.appInfo.getAppVersion());
        mwraChild.setUUID(MainApp.form.get_UID());
        mwraChild.setElb1(MainApp.form.getElb1());
        mwraChild.setElb11(MainApp.form.getElb11());
        mwraChild.setFmuid(childListU2.getSecond().get(position).getUid());
        mwraChild.setType(CONSTANTS.CHILD_TYPE);

        JSONObject json = new JSONObject();

        json.put("mserial", childListU2.getSecond().get(position).getMother_serial());
        json.put("mname", childListU2.getSecond().get(position).getMother_name());
        json.put("imi1a", bi.imi1a.getSelectedItem().toString());
        json.put("i1b", bi.i1b.getText().toString().trim().isEmpty() ? "-1" : bi.i1b.getText().toString());

        json.put("imi1", bi.imi101.isChecked() ? "1"
                : bi.imi102.isChecked() ? "2"
                : bi.imi198.isChecked() ? "98"
                : "-1");

        json.put("imi201", bi.imi201.isChecked() ? "1" : "-1");
        json.put("imi202", bi.imi202.isChecked() ? "2" : "-1");
        json.put("imi203", bi.imi203.isChecked() ? "3" : "-1");
        json.put("imi204", bi.imi204.isChecked() ? "4" : "-1");
        json.put("imi205", bi.imi205.isChecked() ? "5" : "-1");
        json.put("imi296", bi.imi296.isChecked() ? "96" : "-1");
        json.put("imi296x", bi.imi296x.getText().toString());

        json.put("imi3", bi.imi301.isChecked() ? "1"
                : bi.imi302.isChecked() ? "2"
                : bi.imi303.isChecked() ? "3"
                : bi.imi304.isChecked() ? "4"
                : "-1");

        json.put("imi4a", bi.imi4a01.isChecked() ? "1"
                : bi.imi4a02.isChecked() ? "2"
                : "-1");

        json.put("imi4asrc", bi.imi4asrc01.isChecked() ? "1"
                : bi.imi4asrc02.isChecked() ? "2"
                : "-1");

        json.put("imi4aplc", bi.imi4aplc01.isChecked() ? "1"
                : bi.imi4aplc02.isChecked() ? "2"
                : bi.imi4aplc03.isChecked() ? "3"
                : "-1");

        json.put("imi4b", bi.imi4b01.isChecked() ? "1"
                : bi.imi4b02.isChecked() ? "2"
                : "-1");

        json.put("imi4bsrc", bi.imi4bsrc01.isChecked() ? "1"
                : bi.imi4bsrc02.isChecked() ? "2"
                : "-1");

        json.put("imi4bplc", bi.imi4bplc01.isChecked() ? "1"
                : bi.imi4bplc02.isChecked() ? "2"
                : bi.imi4bplc03.isChecked() ? "3"
                : "-1");

        json.put("imi4c", bi.imi4c01.isChecked() ? "1"
                : bi.imi4c02.isChecked() ? "2"
                : "-1");

        json.put("imi4csrc", bi.imi4csrc01.isChecked() ? "1"
                : bi.imi4csrc02.isChecked() ? "2"
                : "-1");

        json.put("imi4cplc", bi.imi4cplc01.isChecked() ? "1"
                : bi.imi4cplc02.isChecked() ? "2"
                : bi.imi4cplc03.isChecked() ? "3"
                : "-1");

        json.put("imi4d", bi.imi4d01.isChecked() ? "1"
                : bi.imi4d02.isChecked() ? "2"
                : "-1");

        json.put("imi4dsrc", bi.imi4dsrc01.isChecked() ? "1"
                : bi.imi4dsrc02.isChecked() ? "2"
                : "-1");

        json.put("imi4dplc", bi.imi4dplc01.isChecked() ? "1"
                : bi.imi4dplc02.isChecked() ? "2"
                : bi.imi4dplc03.isChecked() ? "3"
                : "-1");

        json.put("imi4e", bi.imi4e01.isChecked() ? "1"
                : bi.imi4e02.isChecked() ? "2"
                : "-1");

        json.put("imi4esrc", bi.imi4esrc01.isChecked() ? "1"
                : bi.imi4esrc02.isChecked() ? "2"
                : "-1");

        json.put("imi4eplc", bi.imi4eplc01.isChecked() ? "1"
                : bi.imi4eplc02.isChecked() ? "2"
                : bi.imi4eplc03.isChecked() ? "3"
                : "-1");

        json.put("imi4f", bi.imi4f01.isChecked() ? "1"
                : bi.imi4f02.isChecked() ? "2"
                : "-1");

        json.put("imi4fsrc", bi.imi4fsrc01.isChecked() ? "1"
                : bi.imi4fsrc02.isChecked() ? "2"
                : "-1");

        json.put("imi4fplc", bi.imi4fplc01.isChecked() ? "1"
                : bi.imi4fplc02.isChecked() ? "2"
                : bi.imi4fplc03.isChecked() ? "3"
                : "-1");

        json.put("imi4g", bi.imi4g01.isChecked() ? "1"
                : bi.imi4g02.isChecked() ? "2"
                : "-1");

        json.put("imi4gsrc", bi.imi4gsrc01.isChecked() ? "1"
                : bi.imi4gsrc02.isChecked() ? "2"
                : "-1");

        json.put("imi4gplc", bi.imi4gplc01.isChecked() ? "1"
                : bi.imi4gplc02.isChecked() ? "2"
                : bi.imi4gplc03.isChecked() ? "3"
                : "-1");

        json.put("imi4h", bi.imi4h01.isChecked() ? "1"
                : bi.imi4h02.isChecked() ? "2"
                : "-1");

        json.put("imi4hsrc", bi.imi4hsrc01.isChecked() ? "1"
                : bi.imi4hsrc02.isChecked() ? "2"
                : "-1");

        json.put("imi4hplc", bi.imi4hplc01.isChecked() ? "1"
                : bi.imi4hplc02.isChecked() ? "2"
                : bi.imi4hplc03.isChecked() ? "3"
                : "-1");

        json.put("imi4i", bi.imi4i01.isChecked() ? "1"
                : bi.imi4i02.isChecked() ? "2"
                : "-1");

        json.put("imi4isrc", bi.imi4isrc01.isChecked() ? "1"
                : bi.imi4isrc02.isChecked() ? "2"
                : "-1");

        json.put("imi4iplc", bi.imi4iplc01.isChecked() ? "1"
                : bi.imi4iplc02.isChecked() ? "2"
                : bi.imi4iplc03.isChecked() ? "3"
                : "-1");

        json.put("imi4j", bi.imi4j01.isChecked() ? "1"
                : bi.imi4j02.isChecked() ? "2"
                : "-1");

        json.put("imi4jsrc", bi.imi4jsrc01.isChecked() ? "1"
                : bi.imi4jsrc02.isChecked() ? "2"
                : "-1");

        json.put("imi4jplc", bi.imi4jplc01.isChecked() ? "1"
                : bi.imi4jplc02.isChecked() ? "2"
                : bi.imi4jplc03.isChecked() ? "3"
                : "-1");

        mwraChild.setsB(json.toString());

        childListU2.getFirst().remove(position);
        childListU2.getSecond().remove(position);
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