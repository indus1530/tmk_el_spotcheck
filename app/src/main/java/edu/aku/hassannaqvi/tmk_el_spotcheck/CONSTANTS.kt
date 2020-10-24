package edu.aku.hassannaqvi.tmk_el_spotcheck

class CONSTANTS {
    companion object {
        //For Login
        const val MY_PERMISSIONS_REQUEST_READ_CONTACTS = 0
        const val MY_PERMISSIONS_REQUEST_READ_PHONE_STATE = 2
        const val TWO_MINUTES = 1000 * 60 * 2
        const val MINIMUM_DISTANCE_CHANGE_FOR_UPDATES: Long = 1 // in Meters
        const val MINIMUM_TIME_BETWEEN_UPDATES: Long = 1000 // in Milliseconds


        const val MEMBER_ITEM = 101
        const val SERIAL_EXTRA = "key"
        const val MINYEAR = 1940
        const val MAXYEAR = 2020

        //For J Section
        const val MINYEAR_IM = 2015
        const val SEC_J_FLAG = "jFlag"

        //For E4 Section
        const val MORTALITY_INFO = 2017
        const val MWRA_INFO = "mwra"
        const val SYNC_LOGIN = "sync_login"

        //For CH Section
        const val IM02FLAG = "im02_flag"
        const val IM01CARDSEEN = "im01_card_seen"
        const val ADD_IMMUNIZATION = "add_immunization"

        //Login Result Code
        const val LOGIN_RESULT_CODE = 10101
        const val LOGIN_SPLASH_FLAG = "splash_flag"

        //SubInfo
        const val SUB_INFO_END_FLAG = "sub_info_end_flag"
        const val FSTATUS_END_FLAG = "fstatus_end_flag"

        //Child Activity
        const val CHILD_ENDING_AGE_ISSUE = "childAgeIssue"
        const val CHILD_NO_ANSWER = "childNoAns"
        const val CHILD_SERIAL = "serial_extra"

        //MP & MF variables
        const val FORM_MP = "MP"
        const val FORM_MF = "MF"

        //Main
        const val VILLAGES_DATA = "villages_data"

        //MDeath
        const val DEATH_COUNT = "death_count"

        //CDeath
        const val C_DEATH_COUNT = "c_death_count"

        //TYPE_COUNTERS
        const val MWRA_TYPE = "M101"
        const val CHILD_TYPE = "C101"
        const val MOTHER_DEATH_TYPE = "M102"
        const val CHILD_DEATH_TYPE = "C102"

    }
}