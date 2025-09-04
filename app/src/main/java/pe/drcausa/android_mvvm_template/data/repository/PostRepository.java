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
    private final MutableLiveData<List<Post>> allPosts;

    public PostRepository(Context context) {
        this.postDao = new PostDao(context);
        this.executor = Executors.newSingleThreadExecutor();
        this.allPosts = new MutableLiveData<>();
        loadAllPosts();
    }

    private void loadAllPosts() {
        executor.execute(() -> {
            List<Post> posts = postDao.getAll();
            allPosts.postValue(posts);
        });
    }

    public LiveData<List<Post>> getAllPosts() {
        return allPosts;
    }

    public LiveData<Long> insertPostAsync(Post post) {
        MutableLiveData<Long> result = new MutableLiveData<>();
        executor.execute(() -> {
            long id = postDao.insert(post);
            result.postValue(id);
            loadAllPosts();
        });
        return result;
    }

    public LiveData<Post> getPostByIdAsync(int id) {
        MutableLiveData<Post> result = new MutableLiveData<>();
        executor.execute(() -> result.postValue(postDao.getById(id)));
        return result;
    }

    public LiveData<List<Post>> getAllPostsByUserIdAsync(long userId) {
        MutableLiveData<List<Post>> result = new MutableLiveData<>();
        executor.execute(() -> result.postValue(postDao.getAllByUserId(userId)));
        return result;
    }

    public LiveData<Boolean> updatePostAsync(Post post) {
        MutableLiveData<Boolean> result = new MutableLiveData<>();
        executor.execute(() -> {
            boolean success = postDao.update(post) > 0;
            result.postValue(success);
            if (success) { loadAllPosts(); }
        });
        return result;
    }

    public LiveData<Boolean> deletePostAsync(int postId) {
        MutableLiveData<Boolean> result = new MutableLiveData<>();
        executor.execute(() -> {
            boolean success = postDao.delete(postId) > 0;
            result.postValue(success);
            if (success) { loadAllPosts(); }
        });
        return result;
    }
}
