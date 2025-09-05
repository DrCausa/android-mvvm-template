package pe.drcausa.android_mvvm_template.data.db.tables;

import android.provider.BaseColumns;

public class UserTable implements BaseColumns {
    public static final String TABLE_NAME = "app_user";
    public static final String COLUMN_USER_ID = "user_id";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSWORD_HASH = "password_hash";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_FIRST_NAME = "first_name";
    public static final String COLUMN_LAST_NAME = "last_name";
    public static final String COLUMN_PROFILE_IMAGE_URL = "profile_image_url";
    public static final String COLUMN_CREATED_AT = "created_at";
    public static final String COLUMN_UPDATED_AT = "updated_at";

    public static final String[] ALL_COLUMNS = {
            _ID,
            COLUMN_USER_ID,
            COLUMN_USERNAME,
            COLUMN_PASSWORD_HASH,
            COLUMN_EMAIL,
            COLUMN_FIRST_NAME,
            COLUMN_LAST_NAME,
            COLUMN_PROFILE_IMAGE_URL,
            COLUMN_CREATED_AT,
            COLUMN_UPDATED_AT
    };
}
