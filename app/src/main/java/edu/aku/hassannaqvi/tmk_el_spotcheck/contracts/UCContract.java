package edu.aku.hassannaqvi.tmk_el_spotcheck.contracts;


import android.database.Cursor;
import android.provider.BaseColumns;

import org.json.JSONException;
import org.json.JSONObject;

public class UCContract {

    private static final String TAG = "UC_CONTRACT";
    String uc_code;
    String uc_name;
    String taluka_code;

    public UCContract() {
        // Default Constructor
    }

    public UCContract Sync(JSONObject jsonObject) throws JSONException {
        this.uc_code = jsonObject.getString(UCTable.COLUMN_UC_CODE);
        this.uc_name = jsonObject.getString(UCTable.COLUMN_UC_NAME);
        this.taluka_code = jsonObject.getString(UCTable.COLUMN_TALUKA_CODE);
        return this;
    }

    public UCContract Hydrate(Cursor cursor) {
        this.uc_code = cursor.getString(cursor.getColumnIndex(UCTable.COLUMN_UC_CODE));
        this.uc_name = cursor.getString(cursor.getColumnIndex(UCTable.COLUMN_UC_NAME));
        this.taluka_code = cursor.getString(cursor.getColumnIndex(UCTable.COLUMN_TALUKA_CODE));
        return this;
    }

    public String getUc_code() {
        return uc_code;
    }

    public void setUc_code(String uc_code) {
        this.uc_code = uc_code;
    }

    public String getUc_name() {
        return uc_name;
    }

    public void setUc_name(String uc_name) {
        this.uc_name = uc_name;
    }

    public String getTaluka_code() {
        return taluka_code;
    }

    public void setTaluka_code(String taluka_code) {
        this.taluka_code = taluka_code;
    }

    public JSONObject toJSONObject() throws JSONException {

        JSONObject json = new JSONObject();
        json.put(UCTable.COLUMN_UC_CODE, this.uc_code == null ? JSONObject.NULL : this.uc_code);
        json.put(UCTable.COLUMN_UC_NAME, this.uc_name == null ? JSONObject.NULL : this.uc_name);
        json.put(UCTable.COLUMN_TALUKA_CODE, this.taluka_code == null ? JSONObject.NULL : this.taluka_code);
        return json;
    }


    public static abstract class UCTable implements BaseColumns {

        public static final String TABLE_NAME = "ucs";
        public static final String COLUMN_UC_CODE = "uc_code";
        public static final String COLUMN_UC_NAME = "uc_name";
        public static final String COLUMN_TALUKA_CODE = "taluka_code";
    }
}