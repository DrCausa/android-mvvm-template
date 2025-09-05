package pe.drcausa.android_mvvm_template.data.db.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import pe.drcausa.android_mvvm_template.data.db.DbHelper;
import pe.drcausa.android_mvvm_template.data.db.tables.RoleTable;
import pe.drcausa.android_mvvm_template.data.model.Role;

public class RoleDao {
    private final DbHelper dbHelper;

    public RoleDao(Context context) {
        dbHelper = new DbHelper(context);
    }

    public long insert(@NotNull Role role) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(RoleTable.COLUMN_ROLE_ID, role.getRoleId());
        values.put(RoleTable.COLUMN_NAME, role.getName());
        values.put(RoleTable.COLUMN_DESCRIPTION, role.getDescription());
        values.put(RoleTable.COLUMN_IMAGE_URL, role.getImageUrl());

        return db.insert(RoleTable.TABLE_NAME, null, values);
    }

    public Role getById(long id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String selection = RoleTable._ID + " = ?";
        String[] selectionArgs = {String.valueOf(id)};

        Cursor cursor = db.query(
                RoleTable.TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if (cursor.moveToFirst()) {
            Role role = cursorToRole(cursor);
            cursor.close();
            return role;
        }
        return null;
    }

    public List<Role> getAll() {
        List<Role> roles = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(
                RoleTable.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                RoleTable._ID + " DESC"
        );

        if (cursor.moveToFirst()) {
            do {
                roles.add(cursorToRole(cursor));
            } while (cursor.moveToNext());
            cursor.close();
        }
        return roles;
    }

    public int update(Role role) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(RoleTable.COLUMN_NAME, role.getName());
        values.put(RoleTable.COLUMN_DESCRIPTION, role.getDescription());
        values.put(RoleTable.COLUMN_IMAGE_URL, role.getImageUrl());

        return db.update(
                RoleTable.TABLE_NAME,
                values,
                RoleTable._ID + "=?",
                new String[]{String.valueOf(role.getId())}
        );
    }

    public int delete(long id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        return db.delete(
                RoleTable.TABLE_NAME,
                RoleTable._ID + "=?",
                new String[]{String.valueOf(id)}
        );
    }

    private Role cursorToRole(Cursor cursor) {
        return new Role(
                cursor.getInt(cursor.getColumnIndexOrThrow(RoleTable._ID)),
                cursor.getString(cursor.getColumnIndexOrThrow(RoleTable.COLUMN_ROLE_ID)),
                cursor.getString(cursor.getColumnIndexOrThrow(RoleTable.COLUMN_NAME)),
                cursor.getString(cursor.getColumnIndexOrThrow(RoleTable.COLUMN_DESCRIPTION)),
                cursor.getString(cursor.getColumnIndexOrThrow(RoleTable.COLUMN_IMAGE_URL))
        );
    }
}
