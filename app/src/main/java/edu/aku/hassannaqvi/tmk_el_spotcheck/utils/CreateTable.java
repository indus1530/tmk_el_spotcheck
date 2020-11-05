package edu.aku.hassannaqvi.tmk_el_spotcheck.utils;

import edu.aku.hassannaqvi.tmk_el_spotcheck.contracts.BLRandomContract.BLRandomTable;
import edu.aku.hassannaqvi.tmk_el_spotcheck.contracts.FormsContract.FormsTable;
import edu.aku.hassannaqvi.tmk_el_spotcheck.contracts.UCContract.UCTable;
import edu.aku.hassannaqvi.tmk_el_spotcheck.contracts.UsersContract.UsersTable;
import edu.aku.hassannaqvi.tmk_el_spotcheck.contracts.VersionAppContract.VersionAppTable;
import edu.aku.hassannaqvi.tmk_el_spotcheck.contracts.VillageContract.VillageTable;

public final class CreateTable {

    public static final String DATABASE_NAME = "TmkElSpotcheck.db";
    public static final String DB_NAME = "TmkElSpotcheck_copy.db";
    public static final String PROJECT_NAME = "uenTMKel2020/spotcheck";
    public static final int DATABASE_VERSION = 1;

    public static final String SQL_CREATE_FORMS = "CREATE TABLE "
            + FormsTable.TABLE_NAME + "("
            + FormsTable.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + FormsTable.COLUMN_PROJECT_NAME + " TEXT,"
            + FormsTable.COLUMN_DEVICEID + " TEXT,"
            + FormsTable.COLUMN_DEVICETAGID + " TEXT,"
            + FormsTable.COLUMN_SYSDATE + " TEXT,"
            + FormsTable.COLUMN_UID + " TEXT,"
            + FormsTable.COLUMN_USERNAME + " TEXT,"
            + FormsTable.COLUMN_ELB1 + " TEXT,"
            + FormsTable.COLUMN_ELB2 + " TEXT,"
            + FormsTable.COLUMN_ELB3 + " TEXT,"
            + FormsTable.COLUMN_ELB4 + " TEXT,"
            + FormsTable.COLUMN_ELB5 + " TEXT,"
            + FormsTable.COLUMN_ELB6 + " TEXT,"
            + FormsTable.COLUMN_ELB7 + " TEXT,"
            + FormsTable.COLUMN_ELB8 + " TEXT,"
            + FormsTable.COLUMN_ELB8a + " TEXT,"
            + FormsTable.COLUMN_ELB09 + " TEXT,"
            + FormsTable.COLUMN_ELB10 + " TEXT,"
            + FormsTable.COLUMN_ELB11 + " TEXT,"
            + FormsTable.COLUMN_ELB12 + " TEXT,"
            + FormsTable.COLUMN_SC + " TEXT,"
            + FormsTable.COLUMN_SD + " TEXT,"
            + FormsTable.COLUMN_SE + " TEXT,"
            + FormsTable.COLUMN_SF + " TEXT,"
            + FormsTable.COLUMN_SG + " TEXT,"
            + FormsTable.COLUMN_SH + " TEXT,"
            + FormsTable.COLUMN_SI + " TEXT,"
            + FormsTable.COLUMN_SJ + " TEXT,"
            + FormsTable.COLUMN_SK + " TEXT,"
            + FormsTable.COLUMN_SL + " TEXT,"
            + FormsTable.COLUMN_SN + " TEXT,"
            + FormsTable.COLUMN_GPSLAT + " TEXT,"
            + FormsTable.COLUMN_GPSLNG + " TEXT,"
            + FormsTable.COLUMN_GPSDATE + " TEXT,"
            + FormsTable.COLUMN_GPSACC + " TEXT,"
            + FormsTable.COLUMN_APPVERSION + " TEXT,"
            + FormsTable.COLUMN_ENDINGDATETIME + " TEXT,"
            + FormsTable.COLUMN_ISTATUS + " TEXT,"
            + FormsTable.COLUMN_ISTATUS96x + " TEXT,"
            + FormsTable.COLUMN_SYNCED + " TEXT,"
            + FormsTable.COLUMN_SYNCED_DATE + " TEXT"
            + " );";

    public static final String SQL_CREATE_USERS = "CREATE TABLE " + UsersTable.TABLE_NAME + "("
            + UsersTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + UsersTable.COLUMN_USERNAME + " TEXT,"
            + UsersTable.COLUMN_PASSWORD + " TEXT,"
            + UsersTable.COLUMN_FULL_NAME + " TEXT"
            + " );";


    public static final String SQL_CREATE_DISTRICTS = "CREATE TABLE " + UCTable.TABLE_NAME + "("
            + UCTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + UCTable.COLUMN_UC_CODE + " TEXT,"
            + UCTable.COLUMN_UC_NAME + " TEXT,"
            + UCTable.COLUMN_TALUKA_CODE + " TEXT );";


    public static final String SQL_CREATE_VILLAGE_TABLE = "CREATE TABLE " + VillageTable.TABLE_NAME + " (" +
            VillageTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            VillageTable.COLUMN_VILLAGE_CODE + " TEXT, " +
            VillageTable.COLUMN_VILLAGE_NAME + " TEXT, " +
            VillageTable.COLUMN_AREA_CODE + " TEXT, " +
            VillageTable.COLUMN_CLUSTER_CODE + " TEXT );";


    public static final String SQL_CREATE_VERSIONAPP = "CREATE TABLE " + VersionAppTable.TABLE_NAME + " (" +
            VersionAppTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            VersionAppTable.COLUMN_VERSION_CODE + " TEXT, " +
            VersionAppTable.COLUMN_VERSION_NAME + " TEXT, " +
            VersionAppTable.COLUMN_PATH_NAME + " TEXT " +
            ");";

    public static final String SQL_CREATE_BL_RANDOM = "CREATE TABLE " + BLRandomTable.TABLE_NAME + "("
            + BLRandomTable.COLUMN_ID + " TEXT,"
            + BLRandomTable.COLUMN_CLUSTER_CODE + " TEXT,"
            + BLRandomTable.COLUMN_VILLAGE_CODE + " TEXT,"
            + BLRandomTable.COLUMN_LUID + " TEXT,"
            + BLRandomTable.COLUMN_HH + " TEXT,"
            + BLRandomTable.COLUMN_STRUCTURE_NO + " TEXT,"
            + BLRandomTable.COLUMN_FAMILY_EXT_CODE + " TEXT,"
            + BLRandomTable.COLUMN_HH_HEAD + " TEXT,"
            + BLRandomTable.COLUMN_RANDDT + " TEXT,"
            + BLRandomTable.COLUMN_HH_SELECTED_UC + " TEXT,"
            + BLRandomTable.COLUMN_SYSDT + " TEXT,"
            + BLRandomTable.COLUMN_SNO_HH + " TEXT );";

}
