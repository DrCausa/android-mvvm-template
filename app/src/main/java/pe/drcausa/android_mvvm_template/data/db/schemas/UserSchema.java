package pe.drcausa.android_mvvm_template.data.db.schemas;

import pe.drcausa.android_mvvm_template.data.db.tables.UserTable;

public class UserSchema {
    public static final String SQL_CREATE = "CREATE TABLE " + UserTable.TABLE_NAME + " (" +
            UserTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            UserTable.COLUMN_USER_ID + " TEXT NOT NULL UNIQUE, " +
            UserTable.COLUMN_USERNAME + " TEXT NOT NULL UNIQUE, " +
            UserTable.COLUMN_PASSWORD_HASH + " TEXT NOT NULL, " +
            UserTable.COLUMN_EMAIL + " TEXT NOT NULL UNIQUE, " +
            UserTable.COLUMN_FIRST_NAME + " TEXT, " +
            UserTable.COLUMN_LAST_NAME + " TEXT, " +
            UserTable.COLUMN_PROFILE_IMAGE_URL + " TEXT, " +
            UserTable.COLUMN_CREATED_AT + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
            UserTable.COLUMN_UPDATED_AT + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
            ")";

    public static final String SQL_DROP = "DROP TABLE IF EXISTS " + UserTable.TABLE_NAME;
}
