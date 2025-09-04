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
        allPosts = postRepository.getAllPosts();
    }

    public LiveData<List<Post>> getAllPosts() {
        return allPosts;
    }

    public LiveData<Long> insertPost(Post post) {
        return postRepository.insertPostAsync(post);
    }

    public LiveData<Post> getPostById(int id) {
        return postRepository.getPostByIdAsync(id);
    }

    public LiveData<List<Post>> getPostsByUserId(long userId) {
        return postRepository.getAllPostsByUserIdAsync(userId);
    }

    public LiveData<Boolean> updatePost(Post post) {
        return postRepository.updatePostAsync(post);
    }

    public LiveData<Boolean> deletePost(int postId) {
        return postRepository.deletePostAsync(postId);
    }

    public void setSelectedPost(Post post) {
        selectedPost.setValue(post);
    }

    public LiveData<Post> getSelectedPost() {
        return selectedPost;
    }

    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }
}
