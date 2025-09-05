package pe.drcausa.android_mvvm_template.data.db.schemas;

import pe.drcausa.android_mvvm_template.data.db.tables.PostDislikeTable;
import pe.drcausa.android_mvvm_template.data.db.tables.PostTable;
import pe.drcausa.android_mvvm_template.data.db.tables.UserTable;

public class PostDislikeSchema {
    public static final String SQL_CREATE = "CREATE TABLE " + PostDislikeTable.TABLE_NAME + " (" +
            PostDislikeTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            PostDislikeTable.COLUMN_USER_ID + " INTEGER NOT NULL, " +
            PostDislikeTable.COLUMN_POST_ID + " INTEGER NOT NULL, " +
            PostDislikeTable.COLUMN_CREATED_AT + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
            "FOREIGN KEY (" + PostDislikeTable.COLUMN_USER_ID + ") REFERENCES " +
            UserTable.TABLE_NAME + "(" + UserTable._ID + ") ON DELETE CASCADE, " +
            "FOREIGN KEY (" + PostDislikeTable.COLUMN_POST_ID + ") REFERENCES " +
            PostTable.TABLE_NAME + "(" + PostTable._ID + ") ON DELETE CASCADE" +
            ")";

    public static final String SQL_DROP = "DROP TABLE IF EXISTS " + PostDislikeTable.TABLE_NAME;
}
