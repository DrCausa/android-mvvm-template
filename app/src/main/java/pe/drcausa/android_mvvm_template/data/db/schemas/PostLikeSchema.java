package pe.drcausa.android_mvvm_template.data.db.schemas;

import pe.drcausa.android_mvvm_template.data.db.tables.PostLikeTable;
import pe.drcausa.android_mvvm_template.data.db.tables.PostTable;
import pe.drcausa.android_mvvm_template.data.db.tables.UserTable;

public class PostLikeSchema {
    public static final String SQL_CREATE = "CREATE TABLE " + PostLikeTable.TABLE_NAME + " (" +
            PostLikeTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            PostLikeTable.COLUMN_USER_ID + " INTEGER NOT NULL, " +
            PostLikeTable.COLUMN_POST_ID + " INTEGER NOT NULL, " +
            PostLikeTable.COLUMN_CREATED_AT + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
            "FOREIGN KEY (" + PostLikeTable.COLUMN_USER_ID + ") REFERENCES " +
            UserTable.TABLE_NAME + "(" + UserTable._ID + ") ON DELETE CASCADE, " +
            "FOREIGN KEY (" + PostLikeTable.COLUMN_POST_ID + ") REFERENCES " +
            PostTable.TABLE_NAME + "(" + PostTable._ID + ") ON DELETE CASCADE" +
            ")";

    public static final String SQL_DROP = "DROP TABLE IF EXISTS " + PostLikeTable.TABLE_NAME;
}
