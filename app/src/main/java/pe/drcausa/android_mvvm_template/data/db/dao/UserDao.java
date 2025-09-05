package pe.drcausa.android_mvvm_template.data.db.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import pe.drcausa.android_mvvm_template.data.db.DbHelper;
import pe.drcausa.android_mvvm_template.data.db.tables.UserTable;
import pe.drcausa.android_mvvm_template.data.model.User;

public class UserDao {
    private final DbHelper dbHelper;

    public UserDao(Context context) {
        dbHelper = new DbHelper(context);
    }

    public long insert(@NonNull User user) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(UserTable.COLUMN_USER_ID, user.getUserId());
        values.put(UserTable.COLUMN_USERNAME, user.getUsername());
        values.put(UserTable.COLUMN_PASSWORD_HASH, user.getPasswordHash());
        values.put(UserTable.COLUMN_EMAIL, user.getEmail());
        values.put(UserTable.COLUMN_FIRST_NAME, user.getFirstName());
        values.put(UserTable.COLUMN_LAST_NAME, user.getLastName());
        values.put(UserTable.COLUMN_PROFILE_IMAGE_URL, user.getProfileImageUrl());

        return db.insert(UserTable.TABLE_NAME, null, values);
    }

    public User getById(int id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String selection = UserTable._ID + "=?";
        String[] selectionArgs = {String.valueOf(id)};

        Cursor cursor = db.query(
                UserTable.TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if (cursor.moveToFirst()) {
            User user = cursorToUser(cursor);
            cursor.close();
            return user;
        }
        return null;
    }

    public User getByUsername(String username) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String selection = UserTable.COLUMN_USERNAME + "=?";
        String[] selectionArgs = {username};

        Cursor cursor = db.query(
                UserTable.TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if (cursor.moveToFirst()) {
            User user = cursorToUser(cursor);
            cursor.close();
            return user;
        }
        return null;
    }

    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(
                UserTable.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                UserTable._ID + " DESC"
        );

        if (cursor.moveToFirst()) {
            do {
                users.add(cursorToUser(cursor));
            } while (cursor.moveToNext());
            cursor.close();
        }
        return users;
    }

    public int update(User user) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(UserTable.COLUMN_USERNAME, user.getUsername());
        values.put(UserTable.COLUMN_PASSWORD_HASH, user.getPasswordHash());
        values.put(UserTable.COLUMN_EMAIL, user.getEmail());
        values.put(UserTable.COLUMN_FIRST_NAME, user.getFirstName());
        values.put(UserTable.COLUMN_LAST_NAME, user.getLastName());
        values.put(UserTable.COLUMN_PROFILE_IMAGE_URL, user.getProfileImageUrl());

        return db.update(
                UserTable.TABLE_NAME,
                values,
                UserTable._ID + "=?",
                new String[]{String.valueOf(user.getId())}
        );
    }

    public int delete(int id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        return db.delete(
                UserTable.TABLE_NAME,
                UserTable._ID + "=?",
                new String[]{String.valueOf(id)}
        );
    }

    public User cursorToUser(Cursor cursor) {
        return new User(
                cursor.getInt(cursor.getColumnIndexOrThrow(UserTable._ID)),
                cursor.getString(cursor.getColumnIndexOrThrow(UserTable.COLUMN_USER_ID)),
                cursor.getString(cursor.getColumnIndexOrThrow(UserTable.COLUMN_USERNAME)),
                cursor.getString(cursor.getColumnIndexOrThrow(UserTable.COLUMN_PASSWORD_HASH)),
                cursor.getString(cursor.getColumnIndexOrThrow(UserTable.COLUMN_EMAIL)),
                cursor.getString(cursor.getColumnIndexOrThrow(UserTable.COLUMN_FIRST_NAME)),
                cursor.getString(cursor.getColumnIndexOrThrow(UserTable.COLUMN_LAST_NAME)),
                cursor.getString(cursor.getColumnIndexOrThrow(UserTable.COLUMN_PROFILE_IMAGE_URL))
        );
    }
}
