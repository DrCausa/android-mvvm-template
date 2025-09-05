package pe.drcausa.android_mvvm_template.data.db.tables;

import android.provider.BaseColumns;

public class PermissionTable implements BaseColumns {
    public static final String TABLE_NAME = "permission";
    public static final String COLUMN_PERMISSION_ID = "permission_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_CREATED_AT = "created_at";
    public static final String COLUMN_UPDATED_AT = "updated_at";

    public static final String[] ALL_COLUMNS = {
            _ID,
            COLUMN_PERMISSION_ID,
            COLUMN_NAME,
            COLUMN_DESCRIPTION,
            COLUMN_CREATED_AT,
            COLUMN_UPDATED_AT
    };
}
