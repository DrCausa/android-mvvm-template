package pe.drcausa.android_mvvm_template.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import pe.drcausa.android_mvvm_template.R;
import pe.drcausa.android_mvvm_template.data.model.Post;
import pe.drcausa.android_mvvm_template.data.model.User;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

    public interface OnPostActionListener {
        void onEdit(Post post);
        void onDelete(Post post);
    }

    private List<Post> posts = new ArrayList<>();
    private Map<Long, User> userMap;
    private final OnPostActionListener listener;

    public PostAdapter(OnPostActionListener listener, Map<Long, User> userMap) {
        this.listener = listener;
        this.userMap = userMap;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_post, parent, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        Post post = posts.get(position);
        User user = userMap.get(post.getUserId());

        holder.txtTitle.setText(post.getTitle());
        holder.txtContent.setText(post.getContent());
        holder.txtUser.setText(user != null ? user.getUsername() : "Unknown Author");
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
        notifyDataSetChanged();
    }

    public void setUserMap(Map<Long, User> userMap) {
        this.userMap = userMap;
        notifyDataSetChanged();
    }

    static class PostViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitle, txtContent, txtUser;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txtPostTitle);
            txtContent = itemView.findViewById(R.id.txtPostContent);
            txtUser = itemView.findViewById(R.id.txtPostUser);
        }
    }
}
