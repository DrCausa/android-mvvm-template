package pe.drcausa.android_mvvm_template.data.db.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import pe.drcausa.android_mvvm_template.data.db.DbHelper;
import pe.drcausa.android_mvvm_template.data.db.tables.PermissionTable;
import pe.drcausa.android_mvvm_template.data.model.Permission;

public class PermissionDao {
    private final DbHelper dbHelper;

    public PermissionDao(Context context) {
        dbHelper = new DbHelper(context);
    }

    public long insert(Permission permission) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(PermissionTable.COLUMN_PERMISSION_ID, permission.getPermissionId());
        values.put(PermissionTable.COLUMN_NAME, permission.getName());
        values.put(PermissionTable.COLUMN_DESCRIPTION, permission.getDescription());

        return db.insert(PermissionTable.TABLE_NAME, null, values);
    }

    public Permission getById(long id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String selection = PermissionTable._ID + " = ?";
        String[] selectionArgs = {String.valueOf(id)};

        Cursor cursor = db.query(
                PermissionTable.TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if (cursor.moveToFirst()) {
            Permission permission = cursorToPermission(cursor);
            cursor.close();
            return permission;
        }
        return null;
    }

    public List<Permission> getAll() {
        List<Permission> permissions = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(
                PermissionTable.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                PermissionTable._ID + " DESC"
        );

        if (cursor.moveToFirst()) {
            do {
                permissions.add(cursorToPermission(cursor));
            } while (cursor.moveToNext());
            cursor.close();
        }
        return permissions;
    }

    public int update(Permission permission) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(PermissionTable.COLUMN_PERMISSION_ID, permission.getPermissionId());
        values.put(PermissionTable.COLUMN_NAME, permission.getName());
        values.put(PermissionTable.COLUMN_DESCRIPTION, permission.getDescription());

        return db.update(
                PermissionTable.TABLE_NAME,
                values,
                PermissionTable._ID + "=?",
                new String[]{String.valueOf(permission.getId())}
        );
    }

    public int delete(int id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        return db.delete(
                PermissionTable.TABLE_NAME,
                PermissionTable._ID + "=?",
                new String[]{String.valueOf(id)}
        );
    }

    private Permission cursorToPermission(Cursor cursor) {
        return new Permission(
                cursor.getInt(cursor.getColumnIndexOrThrow(PermissionTable._ID)),
                cursor.getString(cursor.getColumnIndexOrThrow(PermissionTable.COLUMN_PERMISSION_ID)),
                cursor.getString(cursor.getColumnIndexOrThrow(PermissionTable.COLUMN_NAME)),
                cursor.getString(cursor.getColumnIndexOrThrow(PermissionTable.COLUMN_DESCRIPTION))
        );
    }
}
