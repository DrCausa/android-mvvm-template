package pe.drcausa.android_mvvm_template.data.db.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import pe.drcausa.android_mvvm_template.data.db.DbHelper;
import pe.drcausa.android_mvvm_template.data.db.tables.PostLikeTable;
import pe.drcausa.android_mvvm_template.data.model.PostLike;

public class PostLikeDao {
    private final DbHelper dbHelper;

    public PostLikeDao(Context context) {
        dbHelper = new DbHelper(context);
    }

    public long insert(PostLike postLike) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(PostLikeTable.COLUMN_POST_ID, postLike.getPostId());
        values.put(PostLikeTable.COLUMN_USER_ID, postLike.getUserId());

        return db.insert(PostLikeTable.TABLE_NAME, null, values);
    }

    public PostLike getById(int id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String selection = PostLikeTable._ID + " = ?";
        String[] selectionArgs = {String.valueOf(id)};

        Cursor cursor = db.query(
                PostLikeTable.TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if (cursor.moveToFirst()) {
            PostLike postLike = cursorToPostLike(cursor);
            cursor.close();
            return postLike;
        }
        return null;
    }

    public List<PostLike> getAll() {
        List<PostLike> postLikes = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(
                PostLikeTable.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                PostLikeTable._ID + " DESC"
        );

        if (cursor.moveToFirst()) {
            do {
                postLikes.add(cursorToPostLike(cursor));
            } while (cursor.moveToNext());
            cursor.close();
        }
        return postLikes;
    }

    public List<PostLike> getAllByPostId(int postId) {
        List<PostLike> postLikes = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String selection = PostLikeTable.COLUMN_POST_ID + " = ?";
        String[] selectionArgs = {String.valueOf(postId)};

        Cursor cursor = db.query(
                PostLikeTable.TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                PostLikeTable._ID + " DESC"
        );

        if (cursor.moveToFirst()) {
            do {
                postLikes.add(cursorToPostLike(cursor));
            } while (cursor.moveToNext());
            cursor.close();
        }
        return postLikes;
    }

    public List<PostLike> getAllByUserId(long userId) {
        List<PostLike> postLikes = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String selection = PostLikeTable.COLUMN_USER_ID + " = ?";
        String[] selectionArgs = {String.valueOf(userId)};

        Cursor cursor = db.query(
                PostLikeTable.TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                PostLikeTable._ID + " DESC"
        );

        if (cursor.moveToFirst()) {
            do {
                postLikes.add(cursorToPostLike(cursor));
            } while (cursor.moveToNext());
            cursor.close();
        }
        return postLikes;
    }

    public int delete(int id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        return db.delete(
                PostLikeTable.TABLE_NAME,
                PostLikeTable._ID + "=?",
                new String[]{String.valueOf(id)}
        );
    }

    private PostLike cursorToPostLike(Cursor cursor) {
        return new PostLike(
                cursor.getInt(cursor.getColumnIndexOrThrow(PostLikeTable._ID)),
                cursor.getInt(cursor.getColumnIndexOrThrow(PostLikeTable.COLUMN_POST_ID)),
                cursor.getLong(cursor.getColumnIndexOrThrow(PostLikeTable.COLUMN_USER_ID))
        );
    }
}
