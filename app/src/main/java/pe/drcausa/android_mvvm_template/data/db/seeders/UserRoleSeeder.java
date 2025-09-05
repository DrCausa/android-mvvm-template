package pe.drcausa.android_mvvm_template.data.db.seeders;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import java.util.UUID;

import pe.drcausa.android_mvvm_template.data.db.tables.UserRoleTable;

public class UserRoleSeeder {
    public static void seed(SQLiteDatabase db) {
        ContentValues values = new ContentValues();

        values.put(UserRoleTable.COLUMN_USER_ID, 1);
        values.put(UserRoleTable.COLUMN_ROLE_ID, 1);

        db.insert(UserRoleTable.TABLE_NAME, null, values);
        values.clear();

        values.put(UserRoleTable.COLUMN_USER_ID, 1);
        values.put(UserRoleTable.COLUMN_ROLE_ID, 2);

        db.insert(UserRoleTable.TABLE_NAME, null, values);
    }
}
