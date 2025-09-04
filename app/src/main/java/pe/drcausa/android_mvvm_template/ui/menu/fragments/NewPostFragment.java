package pe.drcausa.android_mvvm_template.ui.menu.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;

import pe.drcausa.android_mvvm_template.R;

public class NewPostFragment extends Fragment {

    public NewPostFragment() {
        super(R.layout.fragment_menu_new_post);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MaterialButton btnReturn = view.findViewById(R.id.btnReturn);
        MaterialButton btnCreatePost = view.findViewById(R.id.btnCreatePost);

        btnReturn.setOnClickListener(v -> handleBtnReturn());
        btnCreatePost.setOnClickListener(v -> handleBtnCreatePost());
    }

    private void handleBtnReturn() { requireActivity().getOnBackPressedDispatcher().onBackPressed(); }

    private void handleBtnCreatePost() {
        Toast.makeText(requireContext(), "Create Post", Toast.LENGTH_SHORT).show();
    }
}
