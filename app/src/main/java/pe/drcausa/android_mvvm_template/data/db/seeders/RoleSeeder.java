package pe.drcausa.android_mvvm_template.data.db.seeders;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import java.util.UUID;

import pe.drcausa.android_mvvm_template.data.db.tables.RoleTable;

public class RoleSeeder {
    public static void seed(SQLiteDatabase db) {
        ContentValues values = new ContentValues();

        values.put(RoleTable.COLUMN_ROLE_ID, UUID.randomUUID().toString());
        values.put(RoleTable.COLUMN_NAME, "admin");
        values.put(RoleTable.COLUMN_DESCRIPTION, "Administrator role");
        values.put(RoleTable.COLUMN_IMAGE_URL, "https://example.com/admin.png");

        db.insert(RoleTable.TABLE_NAME, null, values);
        values.clear();

        values.put(RoleTable.COLUMN_ROLE_ID, UUID.randomUUID().toString());
        values.put(RoleTable.COLUMN_NAME, "user");
        values.put(RoleTable.COLUMN_DESCRIPTION, "User role");
        values.put(RoleTable.COLUMN_IMAGE_URL, "https://example.com/user.png");

        db.insert(RoleTable.TABLE_NAME, null, values);
        values.clear();

        values.put(RoleTable.COLUMN_ROLE_ID, UUID.randomUUID().toString());
        values.put(RoleTable.COLUMN_NAME, "guest");
        values.put(RoleTable.COLUMN_DESCRIPTION, "Guest role");
        values.put(RoleTable.COLUMN_IMAGE_URL, "https://example.com/guest.png");

        db.insert(RoleTable.TABLE_NAME, null, values);
    }
}
