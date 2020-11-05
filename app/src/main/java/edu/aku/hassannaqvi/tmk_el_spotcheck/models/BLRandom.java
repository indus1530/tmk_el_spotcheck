package edu.aku.hassannaqvi.tmk_el_spotcheck.models;


import android.database.Cursor;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Locale;

import edu.aku.hassannaqvi.tmk_el_spotcheck.contracts.BLRandomContract.BLRandomTable;

public class BLRandom {

    private static final String TAG = "BLRandom_CONTRACT";

    private String _ID;
    private String LUID;
    private String ebCode; // hh05
    private String pCode; // hh02
    private String structure;  // Structure
    private String extension; // Extension
    private String hh;
    private String hhhead;
    private String sysDT;
    private String randDT;
    private String selUC;
    private String sno;
    private String villageName;

    //Not saving in DB
    private String rndType;
    private String assignHH;

    public BLRandom() {
    }

    public String getEbcode() {
        return ebCode;
    }

    public void setEbcode(String ebcode) {
        this.ebCode = ebcode;
    }

    public String getVillageName() {
        return villageName;
    }

    public void setVillageName(String villageName) {
        this.villageName = villageName;
    }

    public BLRandom Sync(JSONObject jsonObject) throws JSONException {
        this._ID = jsonObject.getString(BLRandomTable.COLUMN_ID);
        this.LUID = jsonObject.getString(BLRandomTable.COLUMN_LUID);
        this.pCode = jsonObject.getString(BLRandomTable.COLUMN_CLUSTER_CODE);
        this.ebCode = jsonObject.getString(BLRandomTable.COLUMN_VILLAGE_CODE);
        this.structure = jsonObject.getString(BLRandomTable.COLUMN_STRUCTURE_NO);
        this.structure = String.format(Locale.ENGLISH, "%04d", Integer.valueOf(this.structure));
        this.extension = jsonObject.getString(BLRandomTable.COLUMN_FAMILY_EXT_CODE);
        this.extension = String.format(Locale.ENGLISH, "%03d", Integer.valueOf(this.extension));
        this.villageName = jsonObject.getString(BLRandomTable.COLUMN_VILLAGE_NAME);
        this.hh = structure + "-" + extension;
        this.sysDT = jsonObject.getString(BLRandomTable.COLUMN_SYSDT);
        this.hhhead = jsonObject.getString(BLRandomTable.COLUMN_HH_HEAD);
        this.randDT = jsonObject.getString(BLRandomTable.COLUMN_RANDDT);
        this.selUC = jsonObject.getString(BLRandomTable.COLUMN_HH_SELECTED_UC);
        this.sno = jsonObject.getString(BLRandomTable.COLUMN_SNO_HH);
        return this;
    }

    public BLRandom hydrate(Cursor cursor) {
        this._ID = cursor.getString(cursor.getColumnIndex(BLRandomTable.COLUMN_ID));
        this.LUID = cursor.getString(cursor.getColumnIndex(BLRandomTable.COLUMN_LUID));
        this.pCode = cursor.getString(cursor.getColumnIndex(BLRandomTable.COLUMN_CLUSTER_CODE));
        this.ebCode = cursor.getString(cursor.getColumnIndex(BLRandomTable.COLUMN_VILLAGE_CODE));
        this.structure = cursor.getString(cursor.getColumnIndex(BLRandomTable.COLUMN_STRUCTURE_NO));
        this.extension = cursor.getString(cursor.getColumnIndex(BLRandomTable.COLUMN_FAMILY_EXT_CODE));
        this.hh = cursor.getString(cursor.getColumnIndex(BLRandomTable.COLUMN_HH));
        this.sysDT = cursor.getString(cursor.getColumnIndex(BLRandomTable.COLUMN_SYSDT));
        this.hhhead = cursor.getString(cursor.getColumnIndex(BLRandomTable.COLUMN_HH_HEAD));
        this.randDT = cursor.getString(cursor.getColumnIndex(BLRandomTable.COLUMN_RANDDT));
        this.selUC = cursor.getString(cursor.getColumnIndex(BLRandomTable.COLUMN_HH_SELECTED_UC));
        this.sno = cursor.getString(cursor.getColumnIndex(BLRandomTable.COLUMN_SNO_HH));
        return this;
    }

    public String get_ID() {
        return _ID;
    }

    public void set_ID(String _ID) {
        this._ID = _ID;
    }

    public String getLUID() {
        return LUID;
    }

    public void setLUID(String LUID) {
        this.LUID = LUID;
    }

    public String getpCode() {
        return pCode;
    }

    public void setpCode(String pCode) {
        this.pCode = pCode;
    }

    public String getStructure() {
        return structure;
    }

    public void setStructure(String structure) {
        this.structure = structure;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getHh() {
        return hh;
    }

    public void setHh(String hh) {
        this.hh = hh;
    }

    public String getSysDT() {
        return sysDT;
    }

    public void setSysDT(String sysDT) {
        this.sysDT = sysDT;
    }

    public String getHhhead() {
        return hhhead;
    }

    public void setHhhead(String hhhead) {
        this.hhhead = hhhead;
    }

    public String getRandDT() {
        return randDT;
    }

    public void setRandDT(String randDT) {
        this.randDT = randDT;
    }

    public String getSelUC() {
        return selUC;
    }

    public void setSelUC(String selUC) {
        this.selUC = selUC;
    }

    public String getAssignHH() {
        return assignHH;
    }

    public void setAssignHH(String assignHH) {
        this.assignHH = assignHH;
    }

    public String getRndType() {
        return rndType;
    }

    public void setRndType(String rndType) {
        this.rndType = rndType;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

}