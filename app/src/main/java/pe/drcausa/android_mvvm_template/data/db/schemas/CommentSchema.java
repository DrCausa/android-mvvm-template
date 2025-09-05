package pe.drcausa.android_mvvm_template.data.db.schemas;

import pe.drcausa.android_mvvm_template.data.db.tables.CommentTable;
import pe.drcausa.android_mvvm_template.data.db.tables.PostTable;
import pe.drcausa.android_mvvm_template.data.db.tables.UserTable;

public class CommentSchema {
    public static final String SQL_CREATE = "CREATE TABLE " + CommentTable.TABLE_NAME + " (" +
            CommentTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            CommentTable.COLUMN_COMMENT_ID + " TEXT NOT NULL UNIQUE, " +
            CommentTable.COLUMN_POST_ID + " INTEGER NOT NULL, " +
            CommentTable.COLUMN_USER_ID + " INTEGER NOT NULL, " +
            CommentTable.COLUMN_CONTENT + " TEXT NOT NULL, " +
            CommentTable.COLUMN_CREATED_AT + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
            CommentTable.COLUMN_UPDATED_AT + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
            "FOREIGN KEY (" + CommentTable.COLUMN_POST_ID + ") REFERENCES " +
            PostTable.TABLE_NAME + "(" + PostTable._ID + ") ON DELETE CASCADE, " +
            "FOREIGN KEY (" + CommentTable.COLUMN_USER_ID + ") REFERENCES " +
            UserTable.TABLE_NAME + "(" + UserTable._ID + ") ON DELETE CASCADE" +
            ")";

    public static final String SQL_DROP = "DROP TABLE IF EXISTS " + CommentTable.TABLE_NAME;
}
