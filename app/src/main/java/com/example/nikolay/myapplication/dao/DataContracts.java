package com.example.nikolay.myapplication.dao;

import android.provider.BaseColumns;

public class DataContracts {

    private DataContracts() {

    }

    static abstract class AudioEntry implements BaseColumns {
        public static final String TABLE_NAME = "AUDIOS";

        public static final String COLUMN_TITLE = "TITLE";
        public static final String COLUMN_DURATION = "DURATION";
    }

    static abstract class AuthorEntry implements BaseColumns {
        public static final String TABLE_NAME = "AUTHORS";

        public static final String COLUMN_FIRST_NAME = "FIRST_NAME";
        public static final String COLUMN_LAST_NAME = "LAST_NAME";
        public static final String COLUMN_DATE_OF_BIRTH = "DATE_OF_BIRTH";
    }

    static abstract class AudioAuthorLink implements BaseColumns {
        public static final String TABLE_NAME = "AUDIOS_AUTHORS";

        public static final String COLUMN_AUDIO_ID = "AUDIO_ID";
        public static final String COLUMN_AUTHOR_ID = "AUTHOR_ID";
    }
}
