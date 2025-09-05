package pe.drcausa.android_mvvm_template.data.db.schemas;

import pe.drcausa.android_mvvm_template.data.db.tables.RoleTable;

public class RoleSchema {
    public static final String SQL_CREATE = "CREATE TABLE " + RoleTable.TABLE_NAME + " (" +
            RoleTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            RoleTable.COLUMN_ROLE_ID + " TEXT NOT NULL UNIQUE, " +
            RoleTable.COLUMN_NAME + " TEXT NOT NULL, " +
            RoleTable.COLUMN_DESCRIPTION + " TEXT, " +
            RoleTable.COLUMN_IMAGE_URL + " TEXT, " +
            RoleTable.COLUMN_CREATED_AT + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
            RoleTable.COLUMN_UPDATED_AT + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
            ")";

    public static final String SQL_DROP = "DROP TABLE IF EXISTS " + RoleTable.TABLE_NAME;
}
