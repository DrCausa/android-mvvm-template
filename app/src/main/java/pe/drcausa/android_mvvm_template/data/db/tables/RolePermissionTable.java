package pe.drcausa.android_mvvm_template.data.db.tables;

import android.provider.BaseColumns;

public class RolePermissionTable implements BaseColumns {
    public static final String TABLE_NAME = "role_permission";
    public static final String COLUMN_ROLE_ID = "role_id";
    public static final String COLUMN_PERMISSION_ID = "permission_id";
    public static final String COLUMN_ASSIGNED_AT = "assigned_at";

    public static final String[] ALL_COLUMNS = {
            _ID,
            COLUMN_ROLE_ID,
            COLUMN_PERMISSION_ID,
            COLUMN_ASSIGNED_AT
    };
}
