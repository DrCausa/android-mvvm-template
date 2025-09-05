package pe.drcausa.android_mvvm_template.data.db.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import pe.drcausa.android_mvvm_template.data.db.DbHelper;
import pe.drcausa.android_mvvm_template.data.db.tables.PostStarTable;
import pe.drcausa.android_mvvm_template.data.model.PostStar;

public class PostStarDao {
    private final DbHelper dbHelper;

    public PostStarDao(DbHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    public long insert(PostStar postStar) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(PostStarTable.COLUMN_POST_ID, postStar.getPostId());
        values.put(PostStarTable.COLUMN_USER_ID, postStar.getUserId());

        return db.insert(PostStarTable.TABLE_NAME, null, values);
    }

    public PostStar getById(int id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String selection = PostStarTable._ID + " = ?";
        String[] selectionArgs = {String.valueOf(id)};

        Cursor cursor = db.query(
                PostStarTable.TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if (cursor.moveToFirst()) {
            PostStar postStar = cursorToPostStar(cursor);
            cursor.close();
            return postStar;
        }
        return null;
    }

    public List<PostStar> getAll() {
        List<PostStar> postStars = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(
                PostStarTable.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                PostStarTable._ID + " DESC"
        );

        if (cursor.moveToFirst()) {
            do {
                postStars.add(cursorToPostStar(cursor));
            } while (cursor.moveToNext());
            cursor.close();
        }
        return postStars;
    }

    public List<PostStar> getAllByPostId(int postId) {
        List<PostStar> postStars = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String selection = PostStarTable.COLUMN_POST_ID + " = ?";
        String[] selectionArgs = {String.valueOf(postId)};

        Cursor cursor = db.query(
                PostStarTable.TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                PostStarTable._ID + " DESC"
        );

        if (cursor.moveToFirst()) {
            do {
                postStars.add(cursorToPostStar(cursor));
            } while (cursor.moveToNext());
            cursor.close();
        }
        return postStars;
    }

    public List<PostStar> getAllByUserId(long userId) {
        List<PostStar> postStars = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String selection = PostStarTable.COLUMN_USER_ID + " = ?";
        String[] selectionArgs = {String.valueOf(userId)};

        Cursor cursor = db.query(
                PostStarTable.TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                PostStarTable._ID + " DESC"
        );

        if (cursor.moveToFirst()) {
            do {
                postStars.add(cursorToPostStar(cursor));
            } while (cursor.moveToNext());
            cursor.close();
        }
        return postStars;
    }

    public int delete(int id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        return db.delete(
                PostStarTable.TABLE_NAME,
                PostStarTable._ID + "=?",
                new String[]{String.valueOf(id)}
        );
    }

    private PostStar cursorToPostStar(Cursor cursor) {
        return new PostStar(
                cursor.getInt(cursor.getColumnIndexOrThrow(PostStarTable._ID)),
                cursor.getInt(cursor.getColumnIndexOrThrow(PostStarTable.COLUMN_POST_ID)),
                cursor.getLong(cursor.getColumnIndexOrThrow(PostStarTable.COLUMN_USER_ID))
        );
    }
}
