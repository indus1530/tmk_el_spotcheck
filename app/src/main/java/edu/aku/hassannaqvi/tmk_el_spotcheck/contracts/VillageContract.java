package edu.aku.hassannaqvi.tmk_el_spotcheck.contracts;


import android.database.Cursor;
import android.provider.BaseColumns;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class VillageContract implements Serializable {

    private static final String TAG = "Village_CONTRACT";
    String village_code;
    String village_name;
    String area_code;
    String cluster_code;

    public VillageContract() {
        // Default Constructor
    }

    public VillageContract Sync(JSONObject jsonObject) throws JSONException {
        this.village_code = jsonObject.getString(VillageTable.COLUMN_VILLAGE_CODE);
        this.village_name = jsonObject.getString(VillageTable.COLUMN_VILLAGE_NAME);
        this.area_code = jsonObject.getString(VillageTable.COLUMN_AREA_CODE);
        this.cluster_code = jsonObject.getString(VillageTable.COLUMN_CLUSTER_CODE);
        return this;
    }

    public VillageContract HydrateEnum(Cursor cursor) {
        this.village_code = cursor.getString(cursor.getColumnIndex(VillageTable.COLUMN_VILLAGE_CODE));
        this.village_name = cursor.getString(cursor.getColumnIndex(VillageTable.COLUMN_VILLAGE_NAME));
        this.area_code = cursor.getString(cursor.getColumnIndex(VillageTable.COLUMN_AREA_CODE));
        this.cluster_code = cursor.getString(cursor.getColumnIndex(VillageTable.COLUMN_CLUSTER_CODE));
        return this;
    }

    public String getVillage_code() {
        return village_code;
    }

    public void setVillage_code(String village_code) {
        this.village_code = village_code;
    }

    public String getVillage_name() {
        return village_name;
    }

    public void setVillage_name(String village_name) {
        this.village_name = village_name;
    }

    public String getArea_code() {
        return area_code;
    }

    public void setArea_code(String area_code) {
        this.area_code = area_code;
    }

    public String getCluster_code() {
        return cluster_code;
    }

    public void setCluster_code(String cluster_code) {
        this.cluster_code = cluster_code;
    }

    public JSONObject toJSONObject() throws JSONException {

        JSONObject json = new JSONObject();
        json.put(VillageTable.COLUMN_VILLAGE_CODE, this.village_code == null ? JSONObject.NULL : this.village_code);
        json.put(VillageTable.COLUMN_VILLAGE_NAME, this.village_name == null ? JSONObject.NULL : this.village_name);
        json.put(VillageTable.COLUMN_AREA_CODE, this.area_code == null ? JSONObject.NULL : this.area_code);
        json.put(VillageTable.COLUMN_CLUSTER_CODE, this.cluster_code == null ? JSONObject.NULL : this.cluster_code);
        return json;
    }


    public static abstract class VillageTable implements BaseColumns {

        public static final String TABLE_NAME = "villages_el";
        public static final String COLUMN_VILLAGE_CODE = "village_code";
        public static final String COLUMN_VILLAGE_NAME = "village_name";
        public static final String COLUMN_AREA_CODE = "area_code";
        public static final String COLUMN_CLUSTER_CODE = "cluster_code";
    }
}