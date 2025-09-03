package pe.drcausa.android_mvvm_template.data.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import pe.drcausa.android_mvvm_template.data.db.DbHelper;
import pe.drcausa.android_mvvm_template.data.db.tables.UserTable;
import pe.drcausa.android_mvvm_template.data.model.User;
import pe.drcausa.android_mvvm_template.utils.PasswordUtils;

public class UserRepository {
    private final DbHelper dbHelper;
    private final Executor executor;

    public UserRepository(Context context) {
        this.dbHelper = new DbHelper(context);
        this.executor = Executors.newSingleThreadExecutor();
    }

    public LiveData<Long> insertUserAsync(User user) {
        MutableLiveData<Long> liveData = new MutableLiveData<>();
        executor.execute(() -> {
            long newId = insertUser(user);
            liveData.postValue(newId);
        });
        return liveData;
    }

    private long insertUser(User user) {
        try (SQLiteDatabase db = dbHelper.getWritableDatabase()) {
            ContentValues values = new ContentValues();

            values.put(UserTable.COLUMN_USERNAME, user.getUsername());
            values.put(UserTable.COLUMN_PASSWORD, PasswordUtils.hashPassword(user.getPassword()));
            values.put(UserTable.COLUMN_EMAIL, user.getEmail());
            values.put(UserTable.COLUMN_FIRST_NAME, user.getFirstName());
            values.put(UserTable.COLUMN_LAST_NAME, user.getLastName());

            return db.insert(UserTable.TABLE_NAME, null, values);
        }
    }

    public LiveData<List<User>> getAllUsersAsync() {
        MutableLiveData<List<User>> liveData = new MutableLiveData<>();
        executor.execute(() -> liveData.postValue(getAllUsersSync()));
        return liveData;
    }

    private List<User> getAllUsersSync() {
        List<User> users = new ArrayList<>();
        try (SQLiteDatabase db = dbHelper.getReadableDatabase();
             Cursor cursor = db.query(
                     UserTable.TABLE_NAME,
                     null,
                     null,
                     null,
                     null,
                     null,
                     null
             )) {
            if (cursor.moveToFirst()) {
                do {
                    users.add(cursorToUser(cursor));
                } while (cursor.moveToNext());
            }
        }
        return users;
    }

    public LiveData<User> getUserByIdAsync(int id) {
        MutableLiveData<User> liveData = new MutableLiveData<>();
        executor.execute(() -> liveData.postValue(getUserByIdSync(id)));
        return liveData;
    }

    private User getUserByIdSync(int id) {
        try (SQLiteDatabase db = dbHelper.getReadableDatabase();
             Cursor cursor = db.query(
                     UserTable.TABLE_NAME,
                     null,
                     UserTable._ID + "=?",
                     new String[]{ String.valueOf(id) },
                     null,
                     null,
                     null
             )) {
            if (cursor.moveToFirst()) {
                return cursorToUser(cursor);
            }
        }
        return null;
    }

    public LiveData<User> getUserByCredentialsAsync(String name, String plainPassword) {
        MutableLiveData<User> liveData = new MutableLiveData<>();
        executor.execute(() -> liveData.postValue(getUserByCredentialsSync(name, plainPassword)));
        return liveData;
    }

    private User getUserByCredentialsSync(String name, String plainPassword) {
        try (SQLiteDatabase db = dbHelper.getReadableDatabase();
             Cursor cursor = db.query(
                     UserTable.TABLE_NAME,
                     null,
                     UserTable.COLUMN_USERNAME + "=?",
                     new String[]{ name },
                     null,
                     null,
                     null
             )) {
            if (cursor.moveToFirst()) {
                String storedHash = cursor.getString(cursor.getColumnIndexOrThrow(UserTable.COLUMN_PASSWORD));
                if (PasswordUtils.verifyPassword(plainPassword, storedHash)) {
                    return cursorToUser(cursor);
                }
            }
        }
        return null;
    }

    public LiveData<Boolean> updateUserAsync(User user) {
        MutableLiveData<Boolean> liveData = new MutableLiveData<>();
        executor.execute(() -> liveData.postValue(updateUserSync(user)));
        return liveData;
    }

    private boolean updateUserSync(User user) {
        try (SQLiteDatabase db = dbHelper.getWritableDatabase()) {
            ContentValues values = new ContentValues();
            values.put(UserTable.COLUMN_USERNAME, user.getUsername());
            values.put(UserTable.COLUMN_PASSWORD, PasswordUtils.hashPassword(user.getPassword()));
            values.put(UserTable.COLUMN_EMAIL, user.getEmail());
            values.put(UserTable.COLUMN_FIRST_NAME, user.getFirstName());
            values.put(UserTable.COLUMN_LAST_NAME, user.getLastName());
            int rows = db.update(
                    UserTable.TABLE_NAME,
                    values,
                    UserTable._ID + "=?",
                    new String[]{ String.valueOf(user.getId()) }
            );
            return rows > 0;
        }
    }

    public LiveData<Boolean> deleteUserAsync(int id) {
        MutableLiveData<Boolean> liveData = new MutableLiveData<>();
        executor.execute(() -> liveData.postValue(deleteUserSync(id)));
        return liveData;
    }

    private boolean deleteUserSync(int id) {
        try (SQLiteDatabase db = dbHelper.getWritableDatabase()) {
            int rows = db.delete(
                    UserTable.TABLE_NAME,
                    UserTable._ID + "=?",
                    new String[]{String.valueOf(id)}
            );
            return rows > 0;
        }
    }

    private User cursorToUser(Cursor cursor) {
        return new User(
                cursor.getInt(cursor.getColumnIndexOrThrow(UserTable._ID)),
                cursor.getString(cursor.getColumnIndexOrThrow(UserTable.COLUMN_USERNAME)),
                cursor.getString(cursor.getColumnIndexOrThrow(UserTable.COLUMN_PASSWORD)),
                cursor.getString(cursor.getColumnIndexOrThrow(UserTable.COLUMN_EMAIL)),
                cursor.getString(cursor.getColumnIndexOrThrow(UserTable.COLUMN_FIRST_NAME)),
                cursor.getString(cursor.getColumnIndexOrThrow(UserTable.COLUMN_LAST_NAME))
        );
    }
}
