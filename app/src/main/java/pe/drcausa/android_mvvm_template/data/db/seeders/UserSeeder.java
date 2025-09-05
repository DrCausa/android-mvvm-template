package pe.drcausa.android_mvvm_template.data.db.seeders;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import java.util.UUID;

import pe.drcausa.android_mvvm_template.data.db.tables.UserTable;
import pe.drcausa.android_mvvm_template.utils.PasswordUtils;

public class UserSeeder {
    public static void seed(SQLiteDatabase db) {
        ContentValues values = new ContentValues();

        values.put(UserTable.COLUMN_USER_ID, UUID.randomUUID().toString());
        values.put(UserTable.COLUMN_USERNAME, "admin");
        values.put(UserTable.COLUMN_PASSWORD_HASH, PasswordUtils.hashPassword("admin"));
        values.put(UserTable.COLUMN_EMAIL, "admin@example.com");
        values.put(UserTable.COLUMN_FIRST_NAME, "John");
        values.put(UserTable.COLUMN_LAST_NAME, "Doe");
        values.put(UserTable.COLUMN_PROFILE_IMAGE_URL, "https://example.com/profile.jpg");

        db.insert(UserTable.TABLE_NAME, null, values);
    }
}
