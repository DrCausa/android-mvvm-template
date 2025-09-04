package pe.drcausa.android_mvvm_template.ui.menu.fragments;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;

import pe.drcausa.android_mvvm_template.R;
import pe.drcausa.android_mvvm_template.utils.ActivityUtils;

public class HomeFragment extends Fragment {

    private MaterialButton btnPrefs;
    private RecyclerView recyclerPosts;

    public HomeFragment() {
        super(R.layout.fragment_menu_home);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnPrefs = view.findViewById(R.id.btnPrefs);
        recyclerPosts = view.findViewById(R.id.recyclerPosts);

        btnPrefs.setOnClickListener(v -> handleBtnPrefs());
    }

    private void handleBtnPrefs() {
        ActivityUtils.switchFragment(
                (AppCompatActivity) requireActivity(),
                new PrefsFragment()
        );
    }
}
