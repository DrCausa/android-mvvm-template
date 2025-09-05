package pe.drcausa.android_mvvm_template.data.db.schemas;

import pe.drcausa.android_mvvm_template.data.db.tables.PostTable;
import pe.drcausa.android_mvvm_template.data.db.tables.UserTable;

public class PostSchema {
    public static final String SQL_CREATE = "CREATE TABLE " + PostTable.TABLE_NAME + " (" +
            PostTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            PostTable.COLUMN_POST_ID + " TEXT NOT NULL UNIQUE, " +
            PostTable.COLUMN_USER_ID + " INTEGER NOT NULL, " +
            PostTable.COLUMN_CONTENT + " TEXT NOT NULL, " +
            PostTable.COLUMN_CREATED_AT + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
            PostTable.COLUMN_UPDATED_AT + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
            "FOREIGN KEY (" + PostTable.COLUMN_USER_ID + ") REFERENCES " +
            UserTable.TABLE_NAME + "(" + UserTable._ID + ") ON DELETE CASCADE" +
            ")";

    public static final String SQL_DROP = "DROP TABLE IF EXISTS " + PostTable.TABLE_NAME;
}
