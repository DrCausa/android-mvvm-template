package pe.drcausa.android_mvvm_template.data.model;

public class PostStar {
    private int id;
    private long userId;
    private long postId;

    public PostStar(int id, long userId, long postId) {
        this.id = id;
        this.userId = userId;
        this.postId = postId;
    }

    public PostStar(long userId, long postId) {
        this.userId = userId;
        this.postId = postId;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public long getUserId() { return userId; }
    public void setUserId(long userId) { this.userId = userId; }

    public long getPostId() { return postId; }
    public void setPostId(long postId) { this.postId = postId; }
}
