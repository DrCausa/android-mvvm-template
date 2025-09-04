package pe.drcausa.android_mvvm_template.ui.menu.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import pe.drcausa.android_mvvm_template.R;
import pe.drcausa.android_mvvm_template.data.model.Post;
import pe.drcausa.android_mvvm_template.utils.SessionManager;
import pe.drcausa.android_mvvm_template.viewmodel.PostViewModel;
import pe.drcausa.android_mvvm_template.viewmodel.UserViewModel;

public class NewPostFragment extends Fragment {

    private MaterialButton btnReturn, btnCreatePost;
    private TextInputEditText edtPostTitle, edtPostContent;
    private PostViewModel postViewModel;
    private UserViewModel userViewModel;

    public NewPostFragment() {
        super(R.layout.fragment_menu_new_post);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnReturn = view.findViewById(R.id.btnReturn);
        btnCreatePost = view.findViewById(R.id.btnCreatePost);
        edtPostTitle = view.findViewById(R.id.edtPostTitle);
        edtPostContent = view.findViewById(R.id.edtPostContent);

        postViewModel = new ViewModelProvider(this).get(PostViewModel.class);
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        btnReturn.setOnClickListener(v -> handleBtnReturn());
        btnCreatePost.setOnClickListener(v -> handleBtnCreatePost());
    }

    private void handleBtnReturn() { requireActivity().getOnBackPressedDispatcher().onBackPressed(); }

    private void handleBtnCreatePost() {
        String title = edtPostTitle.getText().toString();
        String content = edtPostContent.getText().toString();

        if (title.isEmpty() || content.isEmpty()) {
            Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        if (userViewModel.getCurrentUser().getValue() == null) {
            Toast.makeText(requireContext(), "User not logged in", Toast.LENGTH_SHORT).show();
            return;
        }

        long userId = userViewModel.getCurrentUser().getValue().getId();
        Post post = new Post(title, content, userId);

        postViewModel.insertPost(post).observe(getViewLifecycleOwner(), success -> {
            if (success) {
                Toast.makeText(requireContext(), "Post created!", Toast.LENGTH_SHORT).show();
                edtPostTitle.setText("");
                edtPostContent.setText("");
                requireActivity().getOnBackPressedDispatcher().onBackPressed();
            } else {
                Toast.makeText(requireContext(), "Error creating post", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
