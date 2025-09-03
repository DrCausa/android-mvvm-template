package pe.drcausa.android_mvvm_template.data.db;

import android.database.sqlite.SQLiteDatabase;

import pe.drcausa.android_mvvm_template.data.db.seeders.UserSeeder;

public class DbSeeder {
    public static void seed(SQLiteDatabase db) {
        UserSeeder.seed(db);
    }
}
