package edu.aku.hassannaqvi.tmk_el_spotcheck.models;

import android.database.Cursor;

import androidx.lifecycle.LiveData;

import com.google.gson.GsonBuilder;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import edu.aku.hassannaqvi.tmk_el_spotcheck.contracts.DeathContract.DeathTable;

public class Death extends LiveData<Death> {

    private String _ID = "";
    private String _UID = "";
    private String UUID = "";
    private String elb1 = "";
    private String elb11 = "";
    private String username = "";
    private String sysdate = "";
    private String type = "";
    private String sC = "-2";
    private String sB = "-2";
    private String endingdatetime = "";
    private String deviceID = "";
    private String devicetagID = "";
    private String synced = "";
    private String synced_date = "";
    private String appversion = "";

    public Death() {
    }


    public String get_ID() {
        return _ID;
    }

    public void set_ID(String _ID) {
        this._ID = _ID;
    }

    public String get_UID() {
        return _UID;
    }


    public void set_UID(String _UID) {
        this._UID = _UID;
    }


    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }


    public String getElb1() {
        return elb1;
    }

    public void setElb1(String elb1) {
        this.elb1 = elb1;
    }


    public String getElb11() {
        return elb11;
    }

    public void setElb11(String elb11) {
        this.elb11 = elb11;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getSysdate() {
        return sysdate;
    }

    public void setSysdate(String sysdate) {
        this.sysdate = sysdate;
    }


    public String getsC() {
        return sC;
    }

    public void setsC(String sC) {
        this.sC = sC;
    }


    public String getsB() {
        return sB;
    }

    public void setsB(String sB) {
        this.sB = sB;
    }


    public String getEndingdatetime() {
        return endingdatetime;
    }

    public void setEndingdatetime(String endingdatetime) {
        this.endingdatetime = endingdatetime;
    }


    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }


    public String getDevicetagID() {
        return devicetagID;
    }

    public void setDevicetagID(String devicetagID) {
        this.devicetagID = devicetagID;
    }


    public String getSynced() {
        return synced;
    }

    public void setSynced(String synced) {
        this.synced = synced;
    }


    public String getSynced_date() {
        return synced_date;
    }

    public void setSynced_date(String synced_date) {
        this.synced_date = synced_date;
    }


    public String getAppversion() {
        return appversion;
    }

    public void setAppversion(String appversion) {
        this.appversion = appversion;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public Death Sync(JSONObject jsonObject) throws JSONException {
        this._ID = jsonObject.getString(DeathTable.COLUMN_ID);
        this._UID = jsonObject.getString(DeathTable.COLUMN_UID);
        this.UUID = jsonObject.getString(DeathTable.COLUMN_UUID);
        this.elb1 = jsonObject.getString(DeathTable.COLUMN_ELB1);
        this.elb11 = jsonObject.getString(DeathTable.COLUMN_ELB11);
        this.username = jsonObject.getString(DeathTable.COLUMN_USERNAME);
        this.sysdate = jsonObject.getString(DeathTable.COLUMN_SYSDATE);
        this.type = jsonObject.getString(DeathTable.COLUMN_TYPE);
        this.sC = jsonObject.getString(DeathTable.COLUMN_SC);
        this.sB = jsonObject.getString(DeathTable.COLUMN_SB);
        this.deviceID = jsonObject.getString(DeathTable.COLUMN_DEVICEID);
        this.devicetagID = jsonObject.getString(DeathTable.COLUMN_DEVICETAGID);
        this.synced = jsonObject.getString(DeathTable.COLUMN_SYNCED);
        this.synced_date = jsonObject.getString(DeathTable.COLUMN_SYNCED_DATE);
        this.appversion = jsonObject.getString(DeathTable.COLUMN_APPVERSION);
        return this;
    }


    public Death Hydrate(Cursor cursor) {
        this._ID = cursor.getString(cursor.getColumnIndex(DeathTable.COLUMN_ID));
        this._UID = cursor.getString(cursor.getColumnIndex(DeathTable.COLUMN_UID));
        this.UUID = cursor.getString(cursor.getColumnIndex(DeathTable.COLUMN_UUID));
        this.elb1 = cursor.getString(cursor.getColumnIndex(DeathTable.COLUMN_ELB1));
        this.elb11 = cursor.getString(cursor.getColumnIndex(DeathTable.COLUMN_ELB11));
        this.username = cursor.getString(cursor.getColumnIndex(DeathTable.COLUMN_USERNAME));
        this.sysdate = cursor.getString(cursor.getColumnIndex(DeathTable.COLUMN_SYSDATE));
        this.deviceID = cursor.getString(cursor.getColumnIndex(DeathTable.COLUMN_DEVICEID));
        this.devicetagID = cursor.getString(cursor.getColumnIndex(DeathTable.COLUMN_DEVICETAGID));
        this.appversion = cursor.getString(cursor.getColumnIndex(DeathTable.COLUMN_APPVERSION));
        this.type = cursor.getString(cursor.getColumnIndex(DeathTable.COLUMN_TYPE));
        this.sC = cursor.getString(cursor.getColumnIndex(DeathTable.COLUMN_SC));
        this.sB = cursor.getString(cursor.getColumnIndex(DeathTable.COLUMN_SB));

        return this;
    }


    //TODO: Try this instead of toJSONObject
    @NotNull
    @Override
    public String toString() {
        return new GsonBuilder().create().toJson(this, Death.class);
    }


    public JSONObject toJSONObject() {

        JSONObject json = new JSONObject();

        try {
            json.put(DeathTable.COLUMN_ID, this._ID == null ? JSONObject.NULL : this._ID);
            json.put(DeathTable.COLUMN_UID, this._UID == null ? JSONObject.NULL : this._UID);
            json.put(DeathTable.COLUMN_UUID, this.UUID == null ? JSONObject.NULL : this.UUID);
            json.put(DeathTable.COLUMN_ELB1, this.elb1 == null ? JSONObject.NULL : this.elb1);
            json.put(DeathTable.COLUMN_ELB11, this.elb11 == null ? JSONObject.NULL : this.elb11);
            json.put(DeathTable.COLUMN_USERNAME, this.username == null ? JSONObject.NULL : this.username);
            json.put(DeathTable.COLUMN_SYSDATE, this.sysdate == null ? JSONObject.NULL : this.sysdate);
            json.put(DeathTable.COLUMN_TYPE, this.type == null ? JSONObject.NULL : this.type);

            if (this.sC != null && !this.sC.equals("")) {
                json.put(DeathTable.COLUMN_SC, new JSONObject(this.sC));
            }
            if (this.sB != null && !this.sB.equals("")) {
                json.put(DeathTable.COLUMN_SB, new JSONObject(this.sB));
            }

            json.put(DeathTable.COLUMN_DEVICEID, this.deviceID == null ? JSONObject.NULL : this.deviceID);
            json.put(DeathTable.COLUMN_DEVICETAGID, this.devicetagID == null ? JSONObject.NULL : this.devicetagID);
            json.put(DeathTable.COLUMN_APPVERSION, this.appversion == null ? JSONObject.NULL : this.appversion);

            return json;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }
}
