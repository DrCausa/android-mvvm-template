package pe.drcausa.android_mvvm_template.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import pe.drcausa.android_mvvm_template.data.model.Post;
import pe.drcausa.android_mvvm_template.data.repository.PostRepository;
import pe.drcausa.android_mvvm_template.data.repository.UserRepository;

public class PostViewModel extends AndroidViewModel {
    private final PostRepository postRepository;

    private final MutableLiveData<String> errorMessage = new MutableLiveData<>();
    private final MutableLiveData<Post> selectedPost = new MutableLiveData<>();

    private LiveData<List<Post>> allPosts;

    public PostViewModel(@NonNull Application application) {
        super(application);
        postRepository = new PostRepository(application);
        allPosts = postRepository.getAllPostsAsync();
    }

    public void insertPost(Post post) {
        postRepository.insertPostAsync(post).observeForever(id -> {
            if (id != null && id > 0) {
                post.setId(Math.toIntExact(id));
                selectedPost.postValue(post);
            } else {
                errorMessage.postValue("Error inserting post");
            }
        });
    }

    public LiveData<List<Post>> getAllPosts() {
        return allPosts;
    }

    public void getPostById(int id) {
        postRepository.getPostByIdAsync(id).observeForever(post -> {
            if (post != null) {
                selectedPost.postValue(post);
            } else {
                errorMessage.postValue("Post not found");
            }
        });
    }

    public LiveData<List<Post>> getPostsByUserId(long userId) {
        return postRepository.getAllPostsByUserIdAsync(userId);
    }

    public void updatePost(Post post) {
        postRepository.updatePostAsync(post).observeForever(success -> {
            if (Boolean.TRUE.equals(success)) {
                selectedPost.postValue(post);
            } else {
                errorMessage.postValue("Error updating post");
            }
        });
    }

    public void deletePost(int postId) {
        postRepository.deletePostAsync(postId).observeForever(success -> {
            if (Boolean.TRUE.equals(success)) {
                selectedPost.postValue(null);
            } else {
                errorMessage.postValue("Error deleting post");
            }
        });
    }

    public LiveData<Post> getSelectedPost() {
        return selectedPost;
    }

    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }
}
