package pe.drcausa.android_mvvm_template.data.db.tables;

import android.provider.BaseColumns;

public class UserRoleTable implements BaseColumns {
    public static final String TABLE_NAME = "user_role";
    public static final String COLUMN_USER_ID = "user_id";
    public static final String COLUMN_ROLE_ID = "role_id";
    public static final String COLUMN_ASSIGNED_AT = "assigned_at";

    public static final String[] ALL_COLUMNS = {
            _ID,
            COLUMN_USER_ID,
            COLUMN_ROLE_ID,
            COLUMN_ASSIGNED_AT
    };
}
