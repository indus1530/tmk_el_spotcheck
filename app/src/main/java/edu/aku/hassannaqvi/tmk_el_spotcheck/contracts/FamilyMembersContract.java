package edu.aku.hassannaqvi.tmk_el_spotcheck.contracts;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;
import android.provider.BaseColumns;

import org.json.JSONException;
import org.json.JSONObject;

public class FamilyMembersContract implements Parcelable {
    public static final Creator<FamilyMembersContract> CREATOR = new Creator<FamilyMembersContract>() {
        @Override
        public FamilyMembersContract createFromParcel(Parcel in) {
            return new FamilyMembersContract(in);
        }

        @Override
        public FamilyMembersContract[] newArray(int size) {
            return new FamilyMembersContract[size];
        }
    };
    private String _id;
    private String uid;
    private String uuid;
    private String clusterno;
    private String hhno;
    private String serialno;
    private String name;
    private String relHH;
    private String age;
    private String mother_name;
    private String mother_serial;
    private String gender;
    private String marital;
    private String sD;
    private String kishSelected;
    private String luid;
    //Not required in db
    private int ageMonths;
    private String fName;
    private String available;
    private String relHHxx;

    public FamilyMembersContract() {
    }

    protected FamilyMembersContract(Parcel in) {
        _id = in.readString();
        uid = in.readString();
        uuid = in.readString();
        luid = in.readString();
        clusterno = in.readString();
        hhno = in.readString();
        serialno = in.readString();
        name = in.readString();
        relHH = in.readString();
        relHHxx = in.readString();
        age = in.readString();
        mother_name = in.readString();
        mother_serial = in.readString();
        gender = in.readString();
        marital = in.readString();
        sD = in.readString();
        kishSelected = in.readString();
        ageMonths = in.readInt();
        fName = in.readString();
        available = in.readString();
    }

    public FamilyMembersContract hydrate(Cursor cursor) {
        this._id = cursor.getString(cursor.getColumnIndex(MemberTable.COLUMN_ID));
        this.uid = cursor.getString(cursor.getColumnIndex(MemberTable.COLUMN_UID));
        this.uuid = cursor.getString(cursor.getColumnIndex(MemberTable.COLUMN_UUID));
        this.luid = cursor.getString(cursor.getColumnIndex(MemberTable.COLUMN_LUID));
        this.kishSelected = cursor.getString(cursor.getColumnIndex(MemberTable.COLUMN_KISH_SELECTED));
        this.clusterno = cursor.getString(cursor.getColumnIndex(MemberTable.COLUMN_CLUSTERNO));
        this.hhno = cursor.getString(cursor.getColumnIndex(MemberTable.COLUMN_HHNO));
        this.serialno = cursor.getString(cursor.getColumnIndex(MemberTable.COLUMN_SERIAL_NO));
        this.name = cursor.getString(cursor.getColumnIndex(MemberTable.COLUMN_NAME));
        this.relHH = cursor.getString(cursor.getColumnIndex(MemberTable.COLUMN_RELATION_HH));
        this.relHHxx = cursor.getString(cursor.getColumnIndex(MemberTable.COLUMN_RELATION_HHXX));
        this.age = cursor.getString(cursor.getColumnIndex(MemberTable.COLUMN_AGE));
        this.mother_name = cursor.getString(cursor.getColumnIndex(MemberTable.COLUMN_MOTHER_NAME));
        this.mother_serial = cursor.getString(cursor.getColumnIndex(MemberTable.COLUMN_MOTHER_SERIAL));
        this.gender = cursor.getString(cursor.getColumnIndex(MemberTable.COLUMN_GENDER));
        this.marital = cursor.getString(cursor.getColumnIndex(MemberTable.COLUMN_MARITAL));
        this.sD = cursor.getString(cursor.getColumnIndex(MemberTable.COLUMN_SD));

        return this;
    }

    public JSONObject toJSONObject() throws JSONException {

        JSONObject json = new JSONObject();
        json.put(MemberTable.COLUMN_ID, this._id == null ? JSONObject.NULL : this._id);
        json.put(MemberTable.COLUMN_UID, this.uid == null ? JSONObject.NULL : this.uid);
        json.put(MemberTable.COLUMN_UUID, this.uuid == null ? JSONObject.NULL : this.uuid);
        json.put(MemberTable.COLUMN_LUID, this.luid == null ? JSONObject.NULL : this.luid);
        json.put(MemberTable.COLUMN_KISH_SELECTED, this.kishSelected == null ? JSONObject.NULL : this.kishSelected);
        json.put(MemberTable.COLUMN_CLUSTERNO, this.clusterno == null ? JSONObject.NULL : this.clusterno);
        json.put(MemberTable.COLUMN_HHNO, this.hhno == null ? JSONObject.NULL : this.hhno);
        json.put(MemberTable.COLUMN_SERIAL_NO, this.serialno == null ? JSONObject.NULL : this.serialno);
        json.put(MemberTable.COLUMN_NAME, this.name == null ? JSONObject.NULL : this.name);
        json.put(MemberTable.COLUMN_RELATION_HH, this.relHH == null ? JSONObject.NULL : this.relHH);
        json.put(MemberTable.COLUMN_RELATION_HHXX, this.relHHxx == null ? JSONObject.NULL : this.relHHxx);
        json.put(MemberTable.COLUMN_AGE, this.age == null ? JSONObject.NULL : this.age);
        json.put(MemberTable.COLUMN_MOTHER_NAME, this.mother_name == null ? JSONObject.NULL : this.mother_name);
        json.put(MemberTable.COLUMN_MOTHER_SERIAL, this.mother_serial == null ? JSONObject.NULL : this.mother_serial);
        json.put(MemberTable.COLUMN_GENDER, this.gender == null ? JSONObject.NULL : this.gender);
        json.put(MemberTable.COLUMN_MARITAL, this.marital == null ? JSONObject.NULL : this.marital);

        if (!this.sD.equals("")) {
            json.put(MemberTable.COLUMN_SD, new JSONObject(this.sD));
        }

        return json;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getKishSelected() {
        return kishSelected;
    }

    public void setKishSelected(String kishSelected) {
        this.kishSelected = kishSelected;
    }

    public String getClusterno() {
        return clusterno;
    }

    public void setClusterno(String clusterno) {
        this.clusterno = clusterno;
    }

    public String getHhno() {
        return hhno;
    }

    public void setHhno(String hhno) {
        this.hhno = hhno;
    }

    public String getSerialno() {
        return serialno;
    }

    public void setSerialno(String serialno) {
        this.serialno = serialno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRelHH() {
        return relHH;
    }

    public void setRelHH(String relHH) {
        this.relHH = relHH;
    }

    public String getRelHHxx() {
        return relHHxx;
    }

    public void setRelHHxx(String relHHxx) {
        this.relHHxx = relHHxx;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getMother_name() {
        return mother_name;
    }

    public void setMother_name(String mother_name) {
        this.mother_name = mother_name;
    }

    public String getMother_serial() {
        return mother_serial;
    }

    public void setMother_serial(String mother_serial) {
        this.mother_serial = mother_serial;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMarital() {
        return marital;
    }

    public void setMarital(String marital) {
        this.marital = marital;
    }

    public String getsD() {
        return sD;
    }

    public void setsD(String sD) {
        this.sD = sD;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public String getLuid() {
        return luid;
    }

    public void setLuid(String luid) {
        this.luid = luid;
    }

    public int getAgeMonths() {
        return ageMonths;
    }

    public void setAgeMonths(int ageMonths) {
        this.ageMonths = ageMonths;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(_id);
        parcel.writeString(uid);
        parcel.writeString(uuid);
        parcel.writeString(luid);
        parcel.writeString(clusterno);
        parcel.writeString(hhno);
        parcel.writeString(serialno);
        parcel.writeString(name);
        parcel.writeString(relHH);
        parcel.writeString(relHHxx);
        parcel.writeString(age);
        parcel.writeString(mother_name);
        parcel.writeString(mother_serial);
        parcel.writeString(gender);
        parcel.writeString(marital);
        parcel.writeString(sD);
        parcel.writeString(kishSelected);
        parcel.writeInt(ageMonths);
        parcel.writeString(fName);
        parcel.writeString(available);
    }

    public static abstract class MemberTable implements BaseColumns {

        public static final String TABLE_NAME = "familyMembers";
        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_UID = "_uid";
        public static final String COLUMN_UUID = "_uuid";
        public static final String COLUMN_LUID = "_luid";
        public static final String COLUMN_AGE = "age";
        public static final String COLUMN_CLUSTERNO = "elb1";
        public static final String COLUMN_HHNO = "elb11";
        public static final String COLUMN_RELATION_HH = "relHH";
        public static final String COLUMN_RELATION_HHXX = "relHHxx";
        public static final String COLUMN_KISH_SELECTED = "kishSelected";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_SERIAL_NO = "serial_no";
        public static final String COLUMN_MOTHER_NAME = "mother_name";
        public static final String COLUMN_MOTHER_SERIAL = "mother_serial";
        public static final String COLUMN_GENDER = "gender";
        public static final String COLUMN_MARITAL = "marital";
        public static final String COLUMN_SD = "sD";
        public static final String COLUMN_SYNCED = "synced";
        public static final String COLUMN_SYNCED_DATE = "synced_date";
    }
}
