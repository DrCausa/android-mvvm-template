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

import java.util.UUID;

import pe.drcausa.android_mvvm_template.R;
import pe.drcausa.android_mvvm_template.data.model.Post;
import pe.drcausa.android_mvvm_template.data.model.User;
import pe.drcausa.android_mvvm_template.utils.SessionManager;
import pe.drcausa.android_mvvm_template.viewmodel.PostViewModel;
import pe.drcausa.android_mvvm_template.viewmodel.UserViewModel;

public class NewPostFragment extends Fragment {

    private MaterialButton btnReturn, btnCreatePost;
    private TextInputEditText edtPostContent;
    private PostViewModel postViewModel;
    private UserViewModel userViewModel;

    private User loggedUser;

    public NewPostFragment() {
        super(R.layout.fragment_menu_new_post);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnReturn = view.findViewById(R.id.btnReturn);
        btnCreatePost = view.findViewById(R.id.btnCreatePost);
        edtPostContent = view.findViewById(R.id.edtPostContent);

        postViewModel = new ViewModelProvider(requireActivity()).get(PostViewModel.class);
        userViewModel = new ViewModelProvider(requireActivity()).get(UserViewModel.class);

        userViewModel.getCurrentUser().observe(getViewLifecycleOwner(), user -> loggedUser = user);

        btnReturn.setOnClickListener(v -> handleBtnReturn());
        btnCreatePost.setOnClickListener(v -> handleBtnCreatePost());
    }

    private void handleBtnReturn() { requireActivity().getOnBackPressedDispatcher().onBackPressed(); }

    private void handleBtnCreatePost() {
        String content = edtPostContent.getText() != null ? edtPostContent.getText().toString().trim() : "";

        if (content.isEmpty()) {
            Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        if (loggedUser == null) {
            Toast.makeText(requireContext(), "User not logged in", Toast.LENGTH_SHORT).show();
            return;
        }

        Post post = new Post(
                UUID.randomUUID().toString(),
                loggedUser.getId(),
                content
        );

        postViewModel.insertPost(post).observe(getViewLifecycleOwner(), id -> {
            if (id != null && id > 0) {
                Toast.makeText(requireContext(), "Post created!", Toast.LENGTH_SHORT).show();
                edtPostContent.setText("");
                requireActivity().getOnBackPressedDispatcher().onBackPressed();
            } else {
                Toast.makeText(requireContext(), "Error creating post", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
