package pe.drcausa.android_mvvm_template.data.db.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import pe.drcausa.android_mvvm_template.data.db.DbHelper;
import pe.drcausa.android_mvvm_template.data.db.tables.UserRoleTable;
import pe.drcausa.android_mvvm_template.data.model.UserRole;

public class UserRoleDao {
    private final DbHelper dbHelper;

    public UserRoleDao(Context context) {
        dbHelper = new DbHelper(context);
    }

    public long insert(UserRole userRole) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(UserRoleTable.COLUMN_USER_ID, userRole.getUserId());
        values.put(UserRoleTable.COLUMN_ROLE_ID, userRole.getRoleId());

        return db.insert(UserRoleTable.TABLE_NAME, null, values);
    }

    public UserRole getById(int id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String selection = UserRoleTable._ID + "=?";
        String[] selectionArgs = {String.valueOf(id)};

        Cursor cursor = db.query(
                UserRoleTable.TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if (cursor.moveToFirst()) {
            UserRole userRole = cursorToUserRole(cursor);
            cursor.close();
            return userRole;
        }
        return null;
    }

    public List<UserRole> getAll() {
        List<UserRole> userRoles = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(
                UserRoleTable.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                UserRoleTable._ID + " DESC"
        );

        if (cursor.moveToFirst()) {
            do {
                userRoles.add(cursorToUserRole(cursor));
            } while (cursor.moveToNext());
            cursor.close();
        }
        return userRoles;
    }

    public int update(UserRole userRole) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(UserRoleTable.COLUMN_USER_ID, userRole.getUserId());
        values.put(UserRoleTable.COLUMN_ROLE_ID, userRole.getRoleId());

        return db.update(
                UserRoleTable.TABLE_NAME,
                values,
                UserRoleTable._ID + "=?",
                new String[]{String.valueOf(userRole.getId())}
        );
    }

    public int delete(int id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        return db.delete(
                UserRoleTable.TABLE_NAME,
                UserRoleTable._ID + "=?",
                new String[]{String.valueOf(id)}
        );
    }

    private UserRole cursorToUserRole(Cursor cursor) {
        return new UserRole(
                cursor.getInt(cursor.getColumnIndexOrThrow(UserRoleTable._ID)),
                cursor.getInt(cursor.getColumnIndexOrThrow(UserRoleTable.COLUMN_USER_ID)),
                cursor.getInt(cursor.getColumnIndexOrThrow(UserRoleTable.COLUMN_ROLE_ID))
        );
    }
}
