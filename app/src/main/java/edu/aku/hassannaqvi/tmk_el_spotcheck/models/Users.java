package edu.aku.hassannaqvi.tmk_el_spotcheck.models;

import android.database.Cursor;

import org.json.JSONException;
import org.json.JSONObject;

import edu.aku.hassannaqvi.tmk_el_spotcheck.contracts.UsersContract.UsersTable;

/**
 * Created by hassan.naqvi on 11/30/2016.
 */

public class Users {

    private static final String TAG = "Users_CONTRACT";
    Long id;
    String username;
    String password;
    String full_name;
//    String REGION_DSS;

    public Users() {
        // Default Constructor
    }

    public Users(String username, String password) {
        this.password = password;
        this.username = username;
    }

    public Long getUserID() {
        return this.id;
    }

    public void setId(int id) {
        this.id = Long.valueOf(id);
    }

    public String getUserName() {
        return this.username;
    }

    public void setUserName(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

/*    public String getREGION_DSS() {
        return REGION_DSS;
    }

    public void setREGION_DSS(String REGION_DSS) {
        this.REGION_DSS = REGION_DSS;
    }*/

    public Users Sync(JSONObject jsonObject) throws JSONException {
        this.username = jsonObject.getString(UsersTable.COLUMN_USERNAME);
        this.password = jsonObject.getString(UsersTable.COLUMN_PASSWORD);
        this.full_name = jsonObject.getString(UsersTable.COLUMN_FULL_NAME);
//        this.REGION_DSS = jsonObject.getString(singleUser.REGION_DSS);
        return this;

    }

    public Users Hydrate(Cursor cursor) {
        this.username = cursor.getString(cursor.getColumnIndex(UsersTable.COLUMN_USERNAME));
        this.full_name = cursor.getString(cursor.getColumnIndex(UsersTable.COLUMN_FULL_NAME));
        return this;

    }


    public JSONObject toJSONObject() throws JSONException {

        JSONObject json = new JSONObject();
        json.put(UsersTable._ID, this.id == null ? JSONObject.NULL : this.id);
        json.put(UsersTable.COLUMN_USERNAME, this.username == null ? JSONObject.NULL : this.username);
        json.put(UsersTable.COLUMN_PASSWORD, this.password == null ? JSONObject.NULL : this.password);
        json.put(UsersTable.COLUMN_FULL_NAME, this.full_name == null ? JSONObject.NULL : this.full_name);
//        json.put(singleUser.REGION_DSS, this.REGION_DSS == null ? JSONObject.NULL : this.REGION_DSS);
        return json;
    }

}