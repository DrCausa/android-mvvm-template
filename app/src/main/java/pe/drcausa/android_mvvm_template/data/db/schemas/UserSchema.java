package pe.drcausa.android_mvvm_template.data.db.schemas;

import pe.drcausa.android_mvvm_template.data.db.tables.UserTable;

public class UserSchema {
    public static final String SQL_CREATE = "CREATE TABLE " + UserTable.TABLE_NAME + "(" +
            UserTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            UserTable.COLUMN_USERNAME + " TEXT NOT NULL," +
            UserTable.COLUMN_PASSWORD + " TEXT NOT NULL," +
            UserTable.COLUMN_EMAIL + " TEXT NOT NULL," +
            UserTable.COLUMN_FIRST_NAME + " TEXT NOT NULL," +
            UserTable.COLUMN_LAST_NAME + " TEXT NOT NULL" +
            ")";

    public static final String SQL_DROP = "DROP TABLE IF EXISTS " + UserTable.TABLE_NAME;
}
