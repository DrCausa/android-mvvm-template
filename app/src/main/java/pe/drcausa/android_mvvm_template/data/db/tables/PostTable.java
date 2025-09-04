package pe.drcausa.android_mvvm_template.data.db.tables;

import android.provider.BaseColumns;

public class PostTable implements BaseColumns {
    public static final String TABLE_NAME = "post";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_CONTENT = "content";
    public static final String COLUMN_USER_ID = "user_id";

    public static final String[] ALL_COLUMNS = {
            _ID,
            COLUMN_TITLE,
            COLUMN_CONTENT,
            COLUMN_USER_ID
    };
}
