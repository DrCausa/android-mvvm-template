package pe.drcausa.android_mvvm_template.data.db.seeders;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import pe.drcausa.android_mvvm_template.data.db.tables.UserTable;
import pe.drcausa.android_mvvm_template.utils.PasswordUtils;

public class UserSeeder {
    public static void seed(SQLiteDatabase db) {
        ContentValues values = new ContentValues();

        values.put(UserTable.COLUMN_USERNAME, "admin");
        values.put(UserTable.COLUMN_PASSWORD, PasswordUtils.hashPassword("admin"));
        values.put(UserTable.COLUMN_EMAIL, "admin@admin.com");
        values.put(UserTable.COLUMN_FIRST_NAME, "Admin");
        values.put(UserTable.COLUMN_LAST_NAME, "Admin");

        db.insert(UserTable.TABLE_NAME, null, values);
    }
}
