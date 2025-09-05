package pe.drcausa.android_mvvm_template.data.db.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import pe.drcausa.android_mvvm_template.data.db.DbHelper;
import pe.drcausa.android_mvvm_template.data.db.tables.RolePermissionTable;
import pe.drcausa.android_mvvm_template.data.model.RolePermission;

public class RolePermissionDao {
    private final DbHelper dbHelper;

    public RolePermissionDao(Context context) {
        dbHelper = new DbHelper(context);
    }

    public long insert(RolePermission rolePermission) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(RolePermissionTable.COLUMN_ROLE_ID, rolePermission.getRoleId());
        values.put(RolePermissionTable.COLUMN_PERMISSION_ID, rolePermission.getPermissionId());

        return db.insert(RolePermissionTable.TABLE_NAME, null, values);
    }

    public RolePermission getById(int id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String selection = RolePermissionTable._ID + "=?";
        String[] selectionArgs = {String.valueOf(id)};

        Cursor cursor = db.query(
                RolePermissionTable.TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if (cursor.moveToFirst()) {
            RolePermission rolePermission = cursorToRolePermission(cursor);
            cursor.close();
            return rolePermission;
        }
        return null;
    }

    public List<RolePermission> getAll() {
        List<RolePermission> rolePermissions = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(
                RolePermissionTable.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                RolePermissionTable._ID + " DESC"
        );

        if (cursor.moveToFirst()) {
            do {
                rolePermissions.add(cursorToRolePermission(cursor));
            } while (cursor.moveToNext());
            cursor.close();
        }
        return rolePermissions;
    }

    public List<RolePermission> getAllByRoleId(long roleId) {
        List<RolePermission> rolePermissions = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String selection = RolePermissionTable.COLUMN_ROLE_ID + "=?";
        String[] selectionArgs = {String.valueOf(roleId)};

        Cursor cursor = db.query(
                RolePermissionTable.TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                RolePermissionTable._ID + " DESC"
        );

        if (cursor.moveToFirst()) {
            do {
                rolePermissions.add(cursorToRolePermission(cursor));
            } while (cursor.moveToNext());
            cursor.close();
        }
        return rolePermissions;
    }

    public List<RolePermission> getAllByPermissionId(long permissionId) {
        List<RolePermission> rolePermissions = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String selection = RolePermissionTable.COLUMN_PERMISSION_ID + "=?";
        String[] selectionArgs = {String.valueOf(permissionId)};

        Cursor cursor = db.query(
                RolePermissionTable.TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                RolePermissionTable._ID + " DESC"
        );

        if (cursor.moveToFirst()) {
            do {
                rolePermissions.add(cursorToRolePermission(cursor));
            } while (cursor.moveToNext());
            cursor.close();
        }
        return rolePermissions;
    }

    public int update(RolePermission rolePermission) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(RolePermissionTable.COLUMN_ROLE_ID, rolePermission.getRoleId());
        values.put(RolePermissionTable.COLUMN_PERMISSION_ID, rolePermission.getPermissionId());

        return db.update(
                RolePermissionTable.TABLE_NAME,
                values,
                RolePermissionTable._ID + "=?",
                new String[]{String.valueOf(rolePermission.getId())}
        );
    }

    public int delete(int id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        return db.delete(
                RolePermissionTable.TABLE_NAME,
                RolePermissionTable._ID + "=?",
                new String[]{String.valueOf(id)}
        );
    }

    private RolePermission cursorToRolePermission(Cursor cursor) {
        return new RolePermission(
                cursor.getInt(cursor.getColumnIndexOrThrow(RolePermissionTable._ID)),
                cursor.getLong(cursor.getColumnIndexOrThrow(RolePermissionTable.COLUMN_ROLE_ID)),
                cursor.getLong(cursor.getColumnIndexOrThrow(RolePermissionTable.COLUMN_PERMISSION_ID))
        );
    }
}
