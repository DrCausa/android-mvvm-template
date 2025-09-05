package pe.drcausa.android_mvvm_template.data.db.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import pe.drcausa.android_mvvm_template.data.db.DbHelper;
import pe.drcausa.android_mvvm_template.data.db.tables.PostDislikeTable;
import pe.drcausa.android_mvvm_template.data.model.PostDislike;

public class PostDislikeDao {
    private final DbHelper dbHelper;

    public PostDislikeDao(DbHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    public long insert(PostDislike postDislike) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(PostDislikeTable.COLUMN_POST_ID, postDislike.getPostId());
        values.put(PostDislikeTable.COLUMN_USER_ID, postDislike.getUserId());

        return db.insert(PostDislikeTable.TABLE_NAME, null, values);
    }

    public PostDislike getById(int id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String selection = PostDislikeTable._ID + " = ?";
        String[] selectionArgs = {String.valueOf(id)};

        Cursor cursor = db.query(
                PostDislikeTable.TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if (cursor.moveToFirst()) {
            PostDislike postDislike = cursorToPostDislike(cursor);
            cursor.close();
            return postDislike;
        }
        return null;
    }

    public List<PostDislike> getAll() {
        List<PostDislike> postDislikes = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(
                PostDislikeTable.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                PostDislikeTable._ID + " DESC"
        );

        if (cursor.moveToFirst()) {
            do {
                postDislikes.add(cursorToPostDislike(cursor));
            } while (cursor.moveToNext());
            cursor.close();
        }
        return postDislikes;
    }

    public List<PostDislike> getAllByPostId(int postId) {
        List<PostDislike> postDislikes = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String selection = PostDislikeTable.COLUMN_POST_ID + " = ?";
        String[] selectionArgs = {String.valueOf(postId)};

        Cursor cursor = db.query(
                PostDislikeTable.TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                PostDislikeTable._ID + " DESC"
        );

        if (cursor.moveToFirst()) {
            do {
                postDislikes.add(cursorToPostDislike(cursor));
            } while (cursor.moveToNext());
            cursor.close();
        }
        return postDislikes;
    }

    public List<PostDislike> getAllByUserId(long userId) {
        List<PostDislike> postDislikes = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String selection = PostDislikeTable.COLUMN_USER_ID + " = ?";
        String[] selectionArgs = {String.valueOf(userId)};

        Cursor cursor = db.query(
                PostDislikeTable.TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                PostDislikeTable._ID + " DESC"
        );

        if (cursor.moveToFirst()) {
            do {
                postDislikes.add(cursorToPostDislike(cursor));
            } while (cursor.moveToNext());
            cursor.close();
        }
        return postDislikes;
    }

    public int delete(int id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        return db.delete(
                PostDislikeTable.TABLE_NAME,
                PostDislikeTable._ID + "=?",
                new String[]{String.valueOf(id)}
        );
    }

    private PostDislike cursorToPostDislike(Cursor cursor) {
        return new PostDislike(
                cursor.getInt(cursor.getColumnIndexOrThrow(PostDislikeTable._ID)),
                cursor.getInt(cursor.getColumnIndexOrThrow(PostDislikeTable.COLUMN_POST_ID)),
                cursor.getLong(cursor.getColumnIndexOrThrow(PostDislikeTable.COLUMN_USER_ID))
        );
    }
}
