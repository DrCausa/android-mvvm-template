package pe.drcausa.android_mvvm_template.data.db.schemas;

import pe.drcausa.android_mvvm_template.data.db.tables.UserRoleTable;
import pe.drcausa.android_mvvm_template.data.db.tables.UserTable;
import pe.drcausa.android_mvvm_template.data.db.tables.RoleTable;

public class UserRoleSchema {
    public static final String SQL_CREATE = "CREATE TABLE " + UserRoleTable.TABLE_NAME + " (" +
            UserRoleTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            UserRoleTable.COLUMN_USER_ID + " INTEGER NOT NULL, " +
            UserRoleTable.COLUMN_ROLE_ID + " INTEGER NOT NULL, " +
            UserRoleTable.COLUMN_ASSIGNED_AT + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
            "FOREIGN KEY (" + UserRoleTable.COLUMN_USER_ID + ") REFERENCES " +
            UserTable.TABLE_NAME + "(" + UserTable._ID + ") ON DELETE CASCADE, " +
            "FOREIGN KEY (" + UserRoleTable.COLUMN_ROLE_ID + ") REFERENCES " +
            RoleTable.TABLE_NAME + "(" + RoleTable._ID + ") ON DELETE CASCADE" +
            ")";

    public static final String SQL_DROP = "DROP TABLE IF EXISTS " + UserRoleTable.TABLE_NAME;
}
