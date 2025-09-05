package pe.drcausa.android_mvvm_template.data.db.seeders;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import java.util.UUID;

import pe.drcausa.android_mvvm_template.data.db.tables.PostTable;

public class PostSeeder {
    public static void seed(SQLiteDatabase db) {
        ContentValues values = new ContentValues();

        values.put(PostTable.COLUMN_POST_ID, UUID.randomUUID().toString());
        values.put(PostTable.COLUMN_USER_ID, 1);
        values.put(PostTable.COLUMN_CONTENT, "Lorem ipsum dolor sit amet consectetur adipisicing elit. Iste illo voluptatibus optio temporibus sapiente voluptate omnis architecto saepe ducimus laudantium velit nostrum accusamus soluta, doloremque dicta officiis harum ad molestiae?");

        db.insert(PostTable.TABLE_NAME, null, values);
    }
}
