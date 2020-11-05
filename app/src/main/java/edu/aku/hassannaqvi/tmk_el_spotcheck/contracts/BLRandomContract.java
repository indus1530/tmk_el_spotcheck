package edu.aku.hassannaqvi.tmk_el_spotcheck.contracts;


import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

public class BLRandomContract {

    public static String CONTENT_AUTHORITY = "edu.aku.hassannaqvi.uen_tmk_el";

    public static abstract class BLRandomTable implements BaseColumns {

        public static final String TABLE_NAME = "bl_randomised";
        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_LUID = "uid";
        public static final String COLUMN_SYSDT = "sysdate";
        public static final String COLUMN_SNO_HH = "srno";
        public static final String COLUMN_HH_SELECTED_UC = "uc_code";
        public static final String COLUMN_CLUSTER_CODE = "hh02";
        public static final String COLUMN_STRUCTURE_NO = "hh03";
        public static final String COLUMN_FAMILY_EXT_CODE = "hh07";
        public static final String COLUMN_HH_HEAD = "hh08";
        public static final String COLUMN_RANDDT = "randDT";
        public static final String COLUMN_VILLAGE_CODE = "village_code";
        public static final String COLUMN_VILLAGE_NAME = "village_name";
        public static final String COLUMN_HH = "hh";
        public static String PATH = "bl_randomised";
        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH;
        public static Uri CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY)
                .buildUpon().appendPath(PATH).build();
        public static String SERVER_URI = "bl_random.php";

        public static String getMovieKeyFromUri(Uri uri) {
            return uri.getPathSegments().get(1);
        }

        public static Uri buildUriWithId(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }

}