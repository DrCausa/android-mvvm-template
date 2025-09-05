package pe.drcausa.android_mvvm_template.data.db.tables;

import android.provider.BaseColumns;

public class CommentTable implements BaseColumns {
    public static final String TABLE_NAME = "comment";
    public static final String COLUMN_COMMENT_ID = "comment_id";
    public static final String COLUMN_POST_ID = "post_id";
    public static final String COLUMN_USER_ID = "user_id";
    public static final String COLUMN_CONTENT = "content";
    public static final String COLUMN_CREATED_AT = "created_at";
    public static final String COLUMN_UPDATED_AT = "updated_at";

    public static final String[] ALL_COLUMNS = {
            _ID,
            COLUMN_COMMENT_ID,
            COLUMN_POST_ID,
            COLUMN_USER_ID,
            COLUMN_CONTENT,
            COLUMN_CREATED_AT,
            COLUMN_UPDATED_AT
    };
}
