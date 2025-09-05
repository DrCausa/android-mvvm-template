package pe.drcausa.android_mvvm_template.data.db.tables;

import android.provider.BaseColumns;

public class RoleTable implements BaseColumns {
    public static final String TABLE_NAME = "app_role";
    public static final String COLUMN_ROLE_ID = "role_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_IMAGE_URL = "image_url";
    public static final String COLUMN_CREATED_AT = "created_at";
    public static final String COLUMN_UPDATED_AT = "updated_at";

    public static final String[] ALL_COLUMNS = {
            _ID,
            COLUMN_ROLE_ID,
            COLUMN_NAME,
            COLUMN_DESCRIPTION,
            COLUMN_IMAGE_URL,
            COLUMN_CREATED_AT,
            COLUMN_UPDATED_AT
    };
}
