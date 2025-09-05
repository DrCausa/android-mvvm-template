package pe.drcausa.android_mvvm_template.data.db.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import pe.drcausa.android_mvvm_template.data.db.DbHelper;
import pe.drcausa.android_mvvm_template.data.db.tables.CommentTable;
import pe.drcausa.android_mvvm_template.data.model.Comment;

public class CommentDao {
    private final DbHelper dbHelper;

    public CommentDao(Context context) {
        dbHelper = new DbHelper(context);
    }

    public long insert(Comment comment) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(CommentTable.COLUMN_COMMENT_ID, comment.getCommentId());
        values.put(CommentTable.COLUMN_POST_ID, comment.getPostId());
        values.put(CommentTable.COLUMN_USER_ID, comment.getUserId());
        values.put(CommentTable.COLUMN_CONTENT, comment.getContent());

        return db.insert(CommentTable.TABLE_NAME, null, values);
    }

    public Comment getById(int id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String selection = CommentTable._ID + " = ?";
        String[] selectionArgs = {String.valueOf(id)};

        Cursor cursor = db.query(
                CommentTable.TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if (cursor.moveToFirst()) {
            Comment comment = cursorToComment(cursor);
            cursor.close();
            return comment;
        }
        return null;
    }

    public List<Comment> getAll() {
        List<Comment> comments = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(
                CommentTable.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                CommentTable._ID + " DESC"
        );

        if (cursor.moveToFirst()) {
            do {
                comments.add(cursorToComment(cursor));
            } while (cursor.moveToNext());
            cursor.close();
        }
        return comments;
    }

    public List<Comment> getAllByPostId(int postId) {
        List<Comment> comments = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String selection = CommentTable.COLUMN_POST_ID + " = ?";
        String[] selectionArgs = {String.valueOf(postId)};

        Cursor cursor = db.query(
                CommentTable.TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                CommentTable._ID + " DESC"
        );

        if (cursor.moveToFirst()) {
            do {
                comments.add(cursorToComment(cursor));
            } while (cursor.moveToNext());
            cursor.close();
        }
        return comments;
    }

    public List<Comment> getAllByUserId(long userId) {
        List<Comment> comments = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String selection = CommentTable.COLUMN_USER_ID + " = ?";
        String[] selectionArgs = {String.valueOf(userId)};

        Cursor cursor = db.query(
                CommentTable.TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                CommentTable._ID + " DESC"
        );

        if (cursor.moveToFirst()) {
            do {
                comments.add(cursorToComment(cursor));
            } while (cursor.moveToNext());
            cursor.close();
        }
        return comments;
    }

    public int update(Comment comment) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(CommentTable.COLUMN_COMMENT_ID, comment.getCommentId());
        values.put(CommentTable.COLUMN_POST_ID, comment.getPostId());
        values.put(CommentTable.COLUMN_USER_ID, comment.getUserId());
        values.put(CommentTable.COLUMN_CONTENT, comment.getContent());

        return db.update(
                CommentTable.TABLE_NAME,
                values,
                CommentTable._ID + "=?",
                new String[]{String.valueOf(comment.getId())}
        );
    }

    public int delete(int id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        return db.delete(
                CommentTable.TABLE_NAME,
                CommentTable._ID + "=?",
                new String[]{String.valueOf(id)}
        );
    }

    private Comment cursorToComment(Cursor cursor) {
        return new Comment(
                cursor.getInt(cursor.getColumnIndexOrThrow(CommentTable._ID)),
                cursor.getString(cursor.getColumnIndexOrThrow(CommentTable.COLUMN_COMMENT_ID)),
                cursor.getInt(cursor.getColumnIndexOrThrow(CommentTable.COLUMN_POST_ID)),
                cursor.getLong(cursor.getColumnIndexOrThrow(CommentTable.COLUMN_USER_ID)),
                cursor.getString(cursor.getColumnIndexOrThrow(CommentTable.COLUMN_CONTENT)),
                cursor.getString(cursor.getColumnIndexOrThrow(CommentTable.COLUMN_CREATED_AT)),
                cursor.getString(cursor.getColumnIndexOrThrow(CommentTable.COLUMN_UPDATED_AT))
        );
    }
}
