package pe.drcausa.android_mvvm_template.data.db.schemas;

import pe.drcausa.android_mvvm_template.data.db.tables.PostStarTable;
import pe.drcausa.android_mvvm_template.data.db.tables.PostTable;
import pe.drcausa.android_mvvm_template.data.db.tables.UserTable;

public class PostStarSchema {
    public static final String SQL_CREATE = "CREATE TABLE " + PostStarTable.TABLE_NAME + " (" +
            PostStarTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            PostStarTable.COLUMN_USER_ID + " INTEGER NOT NULL, " +
            PostStarTable.COLUMN_POST_ID + " INTEGER NOT NULL, " +
            PostStarTable.COLUMN_CREATED_AT + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
            "FOREIGN KEY (" + PostStarTable.COLUMN_USER_ID + ") REFERENCES " +
            UserTable.TABLE_NAME + "(" + UserTable._ID + ") ON DELETE CASCADE, " +
            "FOREIGN KEY (" + PostStarTable.COLUMN_POST_ID + ") REFERENCES " +
            PostTable.TABLE_NAME + "(" + PostTable._ID + ") ON DELETE CASCADE" +
            ")";

    public static final String SQL_DROP = "DROP TABLE IF EXISTS " + PostStarTable.TABLE_NAME;
}
