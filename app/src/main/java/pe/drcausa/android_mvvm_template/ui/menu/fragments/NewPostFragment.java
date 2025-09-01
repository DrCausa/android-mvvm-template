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
        super(R.layout.fragment_new_post);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MaterialButton btnReturn = view.findViewById(R.id.btnReturn);
        MaterialButton btnPost = view.findViewById(R.id.btnPost);

        btnReturn.setOnClickListener(v -> handleReturn());
        btnPost.setOnClickListener(v -> handlePost());
    }

    private void switchFragment(Fragment fragment) {
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }

    private void handleReturn() { switchFragment(new HomeFragment()); }

    private void handlePost() {
        Toast.makeText(requireContext(), "Post", Toast.LENGTH_SHORT).show();
    }
}
