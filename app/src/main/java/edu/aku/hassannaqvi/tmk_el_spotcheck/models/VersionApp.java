package edu.aku.hassannaqvi.tmk_el_spotcheck.models;

import android.database.Cursor;

import org.json.JSONException;
import org.json.JSONObject;

import edu.aku.hassannaqvi.tmk_el_spotcheck.contracts.VersionAppContract.VersionAppTable;

public class VersionApp {

    private static final String TAG = VersionApp.class.getName();
    String versioncode;
    String versionname;
    String pathname;

    public VersionApp() {
        // Default Constructor
    }

    public VersionApp Sync(JSONObject jsonObject) throws JSONException {
        this.versioncode = jsonObject.getString(VersionAppTable.COLUMN_VERSION_CODE);
        this.pathname = jsonObject.getString(VersionAppTable.COLUMN_PATH_NAME);
        this.versionname = jsonObject.getString(VersionAppTable.COLUMN_VERSION_NAME);
        return this;
    }

    public VersionApp hydrate(Cursor cursor) {
        this.versioncode = cursor.getString(cursor.getColumnIndex(VersionAppTable.COLUMN_VERSION_CODE));
        this.pathname = cursor.getString(cursor.getColumnIndex(VersionAppTable.COLUMN_PATH_NAME));
        this.versionname = cursor.getString(cursor.getColumnIndex(VersionAppTable.COLUMN_VERSION_NAME));
        return this;
    }

    public String getVersioncode() {
        return versioncode;
    }

    public void setVersioncode(String versioncode) {
        this.versioncode = versioncode;
    }

    public String getPathname() {
        return pathname;
    }

    public void setPathname(String pathname) {
        this.pathname = pathname;
    }

    public String getVersionname() {
        return versionname;
    }

    public void setVersionname(String versionname) {
        this.versionname = versionname;
    }

}