package pe.drcausa.android_mvvm_template.data.db.schemas;

import pe.drcausa.android_mvvm_template.data.db.tables.PermissionTable;

public class PermissionSchema {
    public static final String SQL_CREATE = "CREATE TABLE " + PermissionTable.TABLE_NAME + " (" +
            PermissionTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            PermissionTable.COLUMN_PERMISSION_ID + " TEXT NOT NULL UNIQUE, " +
            PermissionTable.COLUMN_NAME + " TEXT NOT NULL, " +
            PermissionTable.COLUMN_DESCRIPTION + " TEXT, " +
            PermissionTable.COLUMN_CREATED_AT + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
            PermissionTable.COLUMN_UPDATED_AT + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
            ")";

    public static final String SQL_DROP = "DROP TABLE IF EXISTS " + PermissionTable.TABLE_NAME;
}
