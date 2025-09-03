package pe.drcausa.android_mvvm_template.data.db.tables;

import android.provider.BaseColumns;

public class UserTable implements BaseColumns {
    public static final String TABLE_NAME = "user";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_FIRST_NAME = "first_name";
    public static final String COLUMN_LAST_NAME = "last_name";

    public static final String[] ALL_COLUMNS = {
            _ID,
            COLUMN_USERNAME,
            COLUMN_PASSWORD,
            COLUMN_EMAIL,
            COLUMN_FIRST_NAME,
            COLUMN_LAST_NAME
    };
}
