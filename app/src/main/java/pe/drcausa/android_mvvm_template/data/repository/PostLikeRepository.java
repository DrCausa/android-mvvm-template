package pe.drcausa.android_mvvm_template.data.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import pe.drcausa.android_mvvm_template.data.db.dao.PostLikeDao;
import pe.drcausa.android_mvvm_template.data.model.PostLike;

public class PostLikeRepository {
    private final PostLikeDao postLikeDao;
    private final Executor executor;
    private final MutableLiveData<List<PostLike>> allPostLikes;

    public PostLikeRepository(Context context) {
        this.postLikeDao = new PostLikeDao(context);
        this.executor = Executors.newSingleThreadExecutor();
        this.allPostLikes = new MutableLiveData<>();
        loadAllPostLikes();
    }

    private void loadAllPostLikes() {
        executor.execute(() -> {
            List<PostLike> postLikes = postLikeDao.getAll();
            allPostLikes.postValue(postLikes);
        });
    }

    public LiveData<List<PostLike>> getAllPostLikes() {
        return allPostLikes;
    }

    public LiveData<Long> insertPostLikeAsync(PostLike postLike) {
        MutableLiveData<Long> result = new MutableLiveData<>();
        executor.execute(() -> {
            long id = postLikeDao.insert(postLike);
            result.postValue(id);
            loadAllPostLikes();
        });
        return result;
    }

    public LiveData<PostLike> getPostLikeByIdAsync(int id) {
        MutableLiveData<PostLike> result = new MutableLiveData<>();
        executor.execute(() -> result.postValue(postLikeDao.getById(id)));
        return result;
    }

    public LiveData<List<PostLike>> getAllPostLikesByPostIdAsync(int postId) {
        MutableLiveData<List<PostLike>> result = new MutableLiveData<>();
        executor.execute(() -> result.postValue(postLikeDao.getAllByPostId(postId)));
        return result;
    }

    public LiveData<List<PostLike>> getAllPostLikesByUserIdAsync(long userId) {
        MutableLiveData<List<PostLike>> result = new MutableLiveData<>();
        executor.execute(() -> result.postValue(postLikeDao.getAllByUserId(userId)));
        return result;
    }

    public LiveData<Boolean> deletePostLikeAsync(int postLikeId) {
        MutableLiveData<Boolean> result = new MutableLiveData<>();
        executor.execute(() -> {
            boolean success = postLikeDao.delete(postLikeId) > 0;
            result.postValue(success);
            if (success) { loadAllPostLikes(); }
        });
        return result;
    }
}
