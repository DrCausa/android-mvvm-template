package pe.drcausa.android_mvvm_template.ui.menu.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;

import pe.drcausa.android_mvvm_template.R;
import pe.drcausa.android_mvvm_template.ui.menu.MenuActivity;

public class NewPostFragment extends Fragment {

    private final MenuActivity parentActivity;

    public NewPostFragment(MenuActivity parentActivity) {
        super(R.layout.fragment_new_post);
        this.parentActivity = parentActivity;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MaterialButton btnReturn = view.findViewById(R.id.btnReturn);
        MaterialButton btnCreatePost = view.findViewById(R.id.btnCreatePost);

        btnReturn.setOnClickListener(v -> handleBtnReturn());
        btnCreatePost.setOnClickListener(v -> handleBtnCreatePost());
    }

    private void handleBtnReturn() { parentActivity.switchFragment(new HomeFragment(parentActivity)); }

    private void handleBtnCreatePost() {
        Toast.makeText(requireContext(), "Create Post", Toast.LENGTH_SHORT).show();
    }
}
