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
import pe.drcausa.android_mvvm_template.data.model.User;
import pe.drcausa.android_mvvm_template.viewmodel.PostViewModel;
import pe.drcausa.android_mvvm_template.viewmodel.UserViewModel;

public class EditPostFragment extends Fragment {
    private MaterialButton btnReturn, btnSavePost;
    private TextInputEditText edtPostNewContent;
    private PostViewModel postViewModel;
    private UserViewModel userViewModel;

    private User loggedUser;
    private Post selectedPost;

    public EditPostFragment() {
        super(R.layout.fragment_menu_edit_post);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnReturn = view.findViewById(R.id.btnReturn);
        btnSavePost = view.findViewById(R.id.btnSavePost);
        edtPostNewContent = view.findViewById(R.id.edtPostNewContent);

        postViewModel = new ViewModelProvider(requireActivity()).get(PostViewModel.class);
        userViewModel = new ViewModelProvider(requireActivity()).get(UserViewModel.class);

        postViewModel.getSelectedPost().observe(getViewLifecycleOwner(), post -> {
            if (post != null) {
                selectedPost = post;
                edtPostNewContent.setText(post.getContent());
            }
        });

        userViewModel.getCurrentUser().observe(getViewLifecycleOwner(), user -> loggedUser = user);

        btnReturn.setOnClickListener(v -> handleBtnReturn());
        btnSavePost.setOnClickListener(v -> handleBtnSavePost());
    }

    private void handleBtnReturn() { requireActivity().getOnBackPressedDispatcher().onBackPressed(); }

    private void handleBtnSavePost() {
        if (selectedPost == null) {
            Toast.makeText(requireContext(), "No post selected", Toast.LENGTH_SHORT).show();
            return;
        }

        String newContent = edtPostNewContent.getText() != null ? edtPostNewContent.getText().toString().trim() : "";

        if (newContent.isEmpty()) {
            Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        if (loggedUser == null) {
            Toast.makeText(requireContext(), "User not logged in", Toast.LENGTH_SHORT).show();
            return;
        }

        selectedPost.setContent(newContent);

        postViewModel.updatePost(selectedPost).observe(getViewLifecycleOwner(), success -> {
            if (Boolean.TRUE.equals(success)) {
                Toast.makeText(requireContext(), "Post updated", Toast.LENGTH_SHORT).show();
                requireActivity().getOnBackPressedDispatcher().onBackPressed();
            } else {
                Toast.makeText(requireContext(), "Error updating post", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
