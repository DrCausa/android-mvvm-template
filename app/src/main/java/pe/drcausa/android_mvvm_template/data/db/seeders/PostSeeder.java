package pe.drcausa.android_mvvm_template.data.db.seeders;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import pe.drcausa.android_mvvm_template.data.db.tables.PostTable;

public class PostSeeder {
    public static void seed(SQLiteDatabase db) {
        ContentValues values = new ContentValues();

        values.put(PostTable.COLUMN_TITLE, "Hello World!");
        values.put(PostTable.COLUMN_CONTENT, "This is a sample post.");
        values.put(PostTable.COLUMN_USER_ID, 1);

        db.insert(PostTable.TABLE_NAME, null, values);
    }
}
