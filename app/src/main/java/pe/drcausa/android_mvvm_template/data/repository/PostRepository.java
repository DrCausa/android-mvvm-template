package pe.drcausa.android_mvvm_template.data.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import pe.drcausa.android_mvvm_template.data.db.dao.PostDao;
import pe.drcausa.android_mvvm_template.data.model.Post;

public class PostRepository {
    private final PostDao postDao;
    private final Executor executor;

    public PostRepository(Context context) {
        this.postDao = new PostDao(context);
        this.executor = Executors.newSingleThreadExecutor();
    }

    public LiveData<Long> insertPostAsync(Post post) {
        MutableLiveData<Long> result = new MutableLiveData<>();
        executor.execute(() -> result.postValue(postDao.insert(post)));
        return result;
    }

    public LiveData<Post> getPostByIdAsync(int id) {
        MutableLiveData<Post> result = new MutableLiveData<>();
        executor.execute(() -> result.postValue(postDao.getById(id)));
        return result;
    }

    public LiveData<List<Post>> getAllPostsAsync() {
        MutableLiveData<List<Post>> result = new MutableLiveData<>();
        executor.execute(() -> result.postValue(postDao.getAll()));
        return result;
    }

    public LiveData<List<Post>> getAllPostsByUserIdAsync(long userId) {
        MutableLiveData<List<Post>> result = new MutableLiveData<>();
        executor.execute(() -> result.postValue(postDao.getAllByUserId(userId)));
        return result;
    }

    public LiveData<Boolean> updatePostAsync(Post post) {
        MutableLiveData<Boolean> result = new MutableLiveData<>();
        executor.execute(() -> result.postValue(postDao.update(post) > 0));
        return result;
    }

    public LiveData<Boolean> deletePostAsync(int postId) {
        MutableLiveData<Boolean> result = new MutableLiveData<>();
        executor.execute(() -> result.postValue(postDao.delete(postId) > 0));
        return result;
    }
}
