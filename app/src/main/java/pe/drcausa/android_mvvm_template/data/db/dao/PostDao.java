package pe.drcausa.android_mvvm_template.data.db.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import pe.drcausa.android_mvvm_template.data.db.DbHelper;
import pe.drcausa.android_mvvm_template.data.db.tables.PostTable;
import pe.drcausa.android_mvvm_template.data.model.Post;

public class PostDao {
    private final DbHelper dbHelper;

    public PostDao(Context context) {
        dbHelper = new DbHelper(context);
    }

    public long insert(@NotNull Post post) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(PostTable.COLUMN_POST_ID, post.getPostId());
        values.put(PostTable.COLUMN_USER_ID, post.getUserId());
        values.put(PostTable.COLUMN_CONTENT, post.getContent());

        return db.insert(PostTable.TABLE_NAME, null, values);
    }

    public Post getById(int id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String selection = PostTable._ID + " = ?";
        String[] selectionArgs = {String.valueOf(id)};

        Cursor cursor = db.query(
                PostTable.TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if (cursor.moveToFirst()) {
            Post post = cursorToPost(cursor);
            cursor.close();
            return post;
        }
        return null;
    }

    public List<Post> getAll() {
        List<Post> posts = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(
                PostTable.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                PostTable._ID + " DESC"
        );

        if (cursor.moveToFirst()) {
            do {
                posts.add(cursorToPost(cursor));
            } while (cursor.moveToNext());
            cursor.close();
        }
        return posts;
    }

    public List<Post> getAllByUserId(long userId) {
        List<Post> posts = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String selection = PostTable.COLUMN_USER_ID + " = ?";
        String[] selectionArgs = {String.valueOf(userId)};

        Cursor cursor = db.query(
                PostTable.TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                PostTable._ID + " DESC"
        );

        if (cursor.moveToFirst()) {
            do {
                posts.add(cursorToPost(cursor));
            } while (cursor.moveToNext());
            cursor.close();
        }
        return posts;
    }

    public int update(Post post) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(PostTable.COLUMN_POST_ID, post.getPostId());
        values.put(PostTable.COLUMN_USER_ID, post.getUserId());
        values.put(PostTable.COLUMN_CONTENT, post.getContent());

        return db.update(
                PostTable.TABLE_NAME,
                values,
                PostTable._ID + "=?",
                new String[]{String.valueOf(post.getId())}
        );
    }

    public int delete(int id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        return db.delete(
                PostTable.TABLE_NAME,
                PostTable._ID + "=?",
                new String[]{String.valueOf(id)}
        );
    }

    private Post cursorToPost(Cursor cursor) {
        return new Post(
                cursor.getInt(cursor.getColumnIndexOrThrow(PostTable._ID)),
                cursor.getString(cursor.getColumnIndexOrThrow(PostTable.COLUMN_POST_ID)),
                cursor.getLong(cursor.getColumnIndexOrThrow(PostTable.COLUMN_USER_ID)),
                cursor.getString(cursor.getColumnIndexOrThrow(PostTable.COLUMN_CONTENT)),
                cursor.getString(cursor.getColumnIndexOrThrow(PostTable.COLUMN_CREATED_AT)),
                cursor.getString(cursor.getColumnIndexOrThrow(PostTable.COLUMN_UPDATED_AT))
        );
    }
}
