package pe.drcausa.android_mvvm_template.data.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import pe.drcausa.android_mvvm_template.data.db.dao.CommentDao;
import pe.drcausa.android_mvvm_template.data.model.Comment;

public class CommentRepository {
    private final CommentDao commentDao;
    private final Executor executor;
    private final MutableLiveData<List<Comment>> allComments;

    public CommentRepository(Context context) {
        this.commentDao = new CommentDao(context);
        this.executor = Executors.newSingleThreadExecutor();
        this.allComments = new MutableLiveData<>();
        loadAllComments();
    }

    private void loadAllComments() {
        executor.execute(() -> {
            List<Comment> comments = commentDao.getAll();
            allComments.postValue(comments);
        });
    }

    public LiveData<List<Comment>> getAllComments() {
        return allComments;
    }

    public LiveData<Long> insertCommentAsync(Comment comment) {
        MutableLiveData<Long> result = new MutableLiveData<>();
        executor.execute(() -> {
            long id = commentDao.insert(comment);
            result.postValue(id);
            loadAllComments();
        });
        return result;
    }

    public LiveData<Comment> getCommentByIdAsync(int id) {
        MutableLiveData<Comment> result = new MutableLiveData<>();
        executor.execute(() -> result.postValue(commentDao.getById(id)));
        return result;
    }

    public LiveData<List<Comment>> getAllCommentsByPostIdAsync(int postId) {
        MutableLiveData<List<Comment>> result = new MutableLiveData<>();
        executor.execute(() -> result.postValue(commentDao.getAllByPostId(postId)));
        return result;
    }

    public LiveData<List<Comment>> getAllCommentsByUserIdAsync(long userId) {
        MutableLiveData<List<Comment>> result = new MutableLiveData<>();
        executor.execute(() -> result.postValue(commentDao.getAllByUserId(userId)));
        return result;
    }

    public LiveData<Boolean> updateCommentAsync(Comment comment) {
        MutableLiveData<Boolean> result = new MutableLiveData<>();
        executor.execute(() -> {
            boolean success = commentDao.update(comment) > 0;
            result.postValue(success);
            if (success) { loadAllComments(); }
        });
        return result;
    }

    public LiveData<Boolean> deleteCommentAsync(int commentId) {
        MutableLiveData<Boolean> result = new MutableLiveData<>();
        executor.execute(() -> {
            boolean success = commentDao.delete(commentId) > 0;
            result.postValue(success);
            if (success) { loadAllComments(); }
        });
        return result;
    }
}
