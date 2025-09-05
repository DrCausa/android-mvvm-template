package pe.drcausa.android_mvvm_template.data.db.schemas;

import pe.drcausa.android_mvvm_template.data.db.tables.RolePermissionTable;
import pe.drcausa.android_mvvm_template.data.db.tables.RoleTable;
import pe.drcausa.android_mvvm_template.data.db.tables.PermissionTable;

public class RolePermissionSchema {
    public static final String SQL_CREATE = "CREATE TABLE " + RolePermissionTable.TABLE_NAME + " (" +
            RolePermissionTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            RolePermissionTable.COLUMN_ROLE_ID + " INTEGER NOT NULL, " +
            RolePermissionTable.COLUMN_PERMISSION_ID + " INTEGER NOT NULL, " +
            RolePermissionTable.COLUMN_ASSIGNED_AT + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
            "FOREIGN KEY (" + RolePermissionTable.COLUMN_ROLE_ID + ") REFERENCES " +
            RoleTable.TABLE_NAME + "(" + RoleTable._ID + ") ON DELETE CASCADE, " +
            "FOREIGN KEY (" + RolePermissionTable.COLUMN_PERMISSION_ID + ") REFERENCES " +
            PermissionTable.TABLE_NAME + "(" + PermissionTable._ID + ") ON DELETE CASCADE" +
            ")";

    public static final String SQL_DROP = "DROP TABLE IF EXISTS " + RolePermissionTable.TABLE_NAME;
}
