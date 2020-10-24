package edu.aku.hassannaqvi.tmk_el_spotcheck.ui.other

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.validatorcrawler.aliazaz.Validator
import edu.aku.hassannaqvi.tmk_el_spotcheck.CONSTANTS.Companion.FSTATUS_END_FLAG
import edu.aku.hassannaqvi.tmk_el_spotcheck.R
import edu.aku.hassannaqvi.tmk_el_spotcheck.core.MainApp.appInfo
import edu.aku.hassannaqvi.tmk_el_spotcheck.core.MainApp.form
import edu.aku.hassannaqvi.tmk_el_spotcheck.databinding.ActivityEndingBinding
import java.text.SimpleDateFormat
import java.util.*

class EndingActivity : AppCompatActivity() {
    lateinit var bi: ActivityEndingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bi = DataBindingUtil.setContentView(this, R.layout.activity_ending)
        bi.callback = this

        val check = intent.getBooleanExtra("complete", false)
        if (check) {
            bi.istatusa.isEnabled = true
            bi.istatusb.isEnabled = false
            bi.istatusc.isEnabled = false
            bi.istatusd.isEnabled = false
            bi.istatuse.isEnabled = false
            bi.istatusf.isEnabled = false
            bi.istatusg.isEnabled = false
            bi.istatush.isEnabled = false
            bi.istatus96.isEnabled = false
        } else {
            val bool = intent.getIntExtra(FSTATUS_END_FLAG, 0)
            bi.istatusa.isEnabled = false
            bi.istatusb.isEnabled = true
            bi.istatusc.isEnabled = true
            bi.istatusd.isEnabled = true
            bi.istatuse.isEnabled = true
            bi.istatusf.isEnabled = true
            bi.istatusg.isEnabled = true
            bi.istatush.isEnabled = true
            bi.istatus96.isEnabled = true
        }
    }

    fun BtnEnd() {
        if (!formValidation()) return
        saveDraft()
        if (updateDB()) {
            finish()
            startActivity(Intent(this, MainActivity::class.java))
        } else {
            Toast.makeText(this, "Error in updating db!!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun saveDraft() {
        val statusValue = if (bi.istatusa.isChecked) "1"
        else if (bi.istatusb.isChecked) "2"
        else if (bi.istatusc.isChecked) "3"
        else if (bi.istatusd.isChecked) "4"
        else if (bi.istatuse.isChecked) "5"
        else if (bi.istatusf.isChecked) "6"
        else if (bi.istatusg.isChecked) "7"
        else if (bi.istatush.isChecked) "8"
        else if (bi.istatus96.isChecked) "96"
        else "-1"
        form.istatus = statusValue
        form.istatus96x = if (bi.istatus96x.text.toString().trim().isEmpty()) "-1" else bi.istatus96x.text.toString()
        form.endingdatetime = SimpleDateFormat("dd-MM-yyyy HH:mm").format(Date().time)

    }


    private fun updateDB(): Boolean {
        val db = appInfo.dbHelper
        var updcount = db.updateEnding()
        return if (updcount == 1) {
            true
        } else {
            Toast.makeText(this, "Sorry. You can't go further.\n Please contact IT Team (Failed to update DB)", Toast.LENGTH_SHORT).show()
            false
        }
    }

    private fun formValidation(): Boolean {
        return Validator.emptyCheckingContainer(this, bi.fldGrpEnd)
    }

    override fun onBackPressed() {
        Toast.makeText(applicationContext, "You Can't go back", Toast.LENGTH_LONG).show()
    }
}