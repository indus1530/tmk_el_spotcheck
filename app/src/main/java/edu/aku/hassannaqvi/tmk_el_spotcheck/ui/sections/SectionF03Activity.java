package edu.aku.hassannaqvi.tmk_el_spotcheck.ui.sections;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.validatorcrawler.aliazaz.Clear;
import com.validatorcrawler.aliazaz.Validator;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import edu.aku.hassannaqvi.tmk_el_spotcheck.CONSTANTS;
import edu.aku.hassannaqvi.tmk_el_spotcheck.R;
import edu.aku.hassannaqvi.tmk_el_spotcheck.contracts.DeathContract;
import edu.aku.hassannaqvi.tmk_el_spotcheck.core.DatabaseHelper;
import edu.aku.hassannaqvi.tmk_el_spotcheck.core.MainApp;
import edu.aku.hassannaqvi.tmk_el_spotcheck.databinding.ActivitySectionF03Binding;
import edu.aku.hassannaqvi.tmk_el_spotcheck.models.Death;
import edu.aku.hassannaqvi.tmk_el_spotcheck.utils.AppUtilsKt;

import static edu.aku.hassannaqvi.tmk_el_spotcheck.CONSTANTS.DEATH_COUNT;


public class SectionF03Activity extends AppCompatActivity {

    ActivitySectionF03Binding bi;
    int count, mCounter = 1;
    private Death death;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_f03);
        bi.setCallback(this);
        setupContent();
    }


    private void setupContent() {
        count = Integer.parseInt(getIntent().getStringExtra(DEATH_COUNT));
        setupNextButtonText();
        bi.raf7a.setText(String.valueOf(mCounter));
    }


    private boolean setupNextButtonText() {
        if (count > 1) {
            Clear.clearAllFields(bi.GrpName);
            bi.btnContinue.setText("Next Death");
            bi.raf7b.setFocusable(true);
            return false;
        } else if (count == 1) {
            bi.btnContinue.setText("Next Section");
            return false;
        }
        return true;
    }


    public void BtnContinue() {
        if (!formValidation()) return;
        try {
            SaveDraft();
            if (UpdateDB()) {
                if (setupNextButtonText()) {
                    finish();
                    startActivity(new Intent(this, SectionF04Activity.class));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private boolean UpdateDB() {
        DatabaseHelper db = MainApp.appInfo.getDbHelper();
        long updcount = db.addDeath(death);
        death.set_ID(String.valueOf(updcount));
        if (updcount > 0) {
            death.set_UID(death.getDeviceID() + death.get_ID());
            db.updatesDeathColumn(DeathContract.DeathTable.COLUMN_UID, death.get_UID(), death.get_ID());
            return true;
        } else {
            Toast.makeText(this, "Sorry. You can't go further.\n Please contact IT Team (Failed to update DB)", Toast.LENGTH_SHORT).show();
            return false;
        }
    }


    private void SaveDraft() throws JSONException {

        death = new Death();
        death.setSysdate(new SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.getDefault()).format(new Date().getTime()));
        death.setUUID(MainApp.form.get_UID());
        death.setUsername(MainApp.userName);
        death.setDeviceID(MainApp.appInfo.getDeviceID());
        death.setDevicetagID(MainApp.appInfo.getTagName());
        death.setAppversion(MainApp.appInfo.getAppVersion());
        death.setElb1(MainApp.form.getElb1());
        death.setElb11(MainApp.form.getElb11());
        death.setType(CONSTANTS.MOTHER_DEATH_TYPE);

        JSONObject json = new JSONObject();

        json.put("raf7a", bi.raf7a.getText().toString());
        json.put("raf7b", bi.raf7b.getText().toString());
        json.put("raf7cd", bi.raf7cd.getText().toString());
        json.put("raf7cm", bi.raf7cm.getText().toString());
        json.put("raf7cy", bi.raf7cy.getText().toString());

        json.put("raf7d", bi.raf7d01.isChecked() ? "1"
                : bi.raf7d02.isChecked() ? "2"
                : bi.raf7d03.isChecked() ? "3"
                : bi.raf7d04.isChecked() ? "4"
                : bi.raf7d05.isChecked() ? "5"
                : "-1");

        json.put("raf7e", bi.raf7e.getText().toString().trim().isEmpty() ? "-1" : bi.raf7e.getText().toString());

        json.put("raf7f", bi.raf7f01.isChecked() ? "1"
                : bi.raf7f02.isChecked() ? "2"
                : bi.raf7f03.isChecked() ? "3"
                : bi.raf7f04.isChecked() ? "4"
                : bi.raf7f05.isChecked() ? "5"
                : bi.raf7f06.isChecked() ? "6"
                : bi.raf7f96.isChecked() ? "96"
                : "-1");

        json.put("raf7f96x", bi.raf7f96x.getText().toString());

        death.setsB(json.toString());

        count--;
        mCounter++;

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