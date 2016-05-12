package com.example.nikolay.myapplication.dao;

import android.provider.BaseColumns;

class DataContracts {

    private DataContracts() {

    }

    static abstract class AudioEntry implements BaseColumns {
        static final String TABLE_NAME = "AUDIOS";

        static final String COLUMN_TITLE = "TITLE";
        static final String COLUMN_DURATION = "DURATION";
    }

    static abstract class AuthorEntry implements BaseColumns {
        static final String TABLE_NAME = "AUTHORS";

        static final String COLUMN_FIRST_NAME = "FIRST_NAME";
        static final String COLUMN_LAST_NAME = "LAST_NAME";
        static final String COLUMN_DATE_OF_BIRTH = "DATE_OF_BIRTH";
    }

    static abstract class AudioAuthorLink implements BaseColumns {
        static final String TABLE_NAME = "AUDIOS_AUTHORS";

        static final String COLUMN_AUDIO_ID = "AUDIO_ID";
        static final String COLUMN_AUTHOR_ID = "AUTHOR_ID";
    }
}
