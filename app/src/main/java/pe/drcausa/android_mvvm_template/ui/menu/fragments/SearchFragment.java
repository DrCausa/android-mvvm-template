package pe.drcausa.android_mvvm_template.ui.menu.fragments;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import pe.drcausa.android_mvvm_template.R;
import pe.drcausa.android_mvvm_template.data.model.Post;
import pe.drcausa.android_mvvm_template.data.model.User;
import pe.drcausa.android_mvvm_template.ui.adapters.PostAdapter;
import pe.drcausa.android_mvvm_template.utils.ActivityUtils;
import pe.drcausa.android_mvvm_template.viewmodel.PostViewModel;
import pe.drcausa.android_mvvm_template.viewmodel.UserViewModel;

public class SearchFragment extends Fragment {

    private MaterialButton btnReturn;
    private TextInputEditText edtSearch;
    private RecyclerView rvResults;

    private PostAdapter postAdapter;
    private PostViewModel postViewModel;
    private UserViewModel userViewModel;
    private Map<Long, User> userMap = new HashMap<>();
    private List<Post> allPosts = new ArrayList<>();

    public SearchFragment() {
        super(R.layout.fragment_menu_search);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnReturn = view.findViewById(R.id.btnReturn);
        edtSearch = view.findViewById(R.id.edtSearch);
        rvResults = view.findViewById(R.id.rvResults);

        rvResults.setLayoutManager(new LinearLayoutManager(requireContext()));

        postAdapter = new PostAdapter(new PostAdapter.OnPostActionListener() {
            @Override
            public void onEdit(Post post) {
                postViewModel.setSelectedPost(post);
                ActivityUtils.switchFragment(
                        (AppCompatActivity) requireActivity(),
                        new EditPostFragment()
                );
            }

            @Override
            public void onDelete(Post post) {
                postViewModel.deletePost(post.getId()).observe(getViewLifecycleOwner(), success -> {
                    if (Boolean.TRUE.equals(success)) {
                        Toast.makeText(requireContext(), "Post deleted", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(requireContext(), "Error deleting post", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }, userMap);

        rvResults.setAdapter(postAdapter);

        postViewModel = new ViewModelProvider(requireActivity()).get(PostViewModel.class);
        userViewModel = new ViewModelProvider(requireActivity()).get(UserViewModel.class);

        userViewModel.getAllUsers().observe(getViewLifecycleOwner(), users -> {
            if (users != null) {
                userMap.clear();
                for (User user : users) {
                    userMap.put((long) user.getId(), user);
                }
                postAdapter.setUserMap(userMap);
            }
        });

        postViewModel.getAllPosts().observe(getViewLifecycleOwner(), posts -> {
            if (posts != null) {
                allPosts.clear();
                allPosts.addAll(posts);
                postAdapter.setPosts(allPosts);
            }
        });

        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                searchPosts(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) { }
        });

        btnReturn.setOnClickListener(v -> handleBtnReturn());
    }

    private void handleBtnReturn() { requireActivity().getOnBackPressedDispatcher().onBackPressed(); }

    private void searchPosts(String query) {
        if (query.isEmpty()) {
            postAdapter.setPosts(allPosts);
            return;
        }

        List<Post> filtered = new ArrayList<>();
        for (Post post : allPosts) {
            User user = userMap.get(post.getUserId());

            boolean matchesUser = user != null && user.getUsername()
                    .toLowerCase(Locale.ROOT)
                    .contains(query.toLowerCase(Locale.ROOT));

            boolean matchesContent = post.getContent()
                    .toLowerCase(Locale.ROOT)
                    .contains(query.toLowerCase(Locale.ROOT));

            if (matchesUser || matchesContent) {
                filtered.add(post);
            }
        }
        postAdapter.setPosts(filtered);
    }
}
