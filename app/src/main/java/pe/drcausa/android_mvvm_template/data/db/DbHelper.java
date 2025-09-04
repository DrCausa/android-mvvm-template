package pe.drcausa.android_mvvm_template.data.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import pe.drcausa.android_mvvm_template.data.db.schemas.PostSchema;
import pe.drcausa.android_mvvm_template.data.db.schemas.UserSchema;
import pe.drcausa.android_mvvm_template.utils.AppConstants;

public class DbHelper extends SQLiteOpenHelper {
    public DbHelper(Context context) {
        super(context, AppConstants.DATABASE_NAME, null, AppConstants.DATABASE_VERSION);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(UserSchema.SQL_CREATE);
        db.execSQL(PostSchema.SQL_CREATE);
        DbSeeder.seed(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(UserSchema.SQL_DROP);
        db.execSQL(PostSchema.SQL_DROP);
        onCreate(db);
    }
}
