package pe.drcausa.android_mvvm_template.data.model;

public class Post {
    private int id;
    private String title;
    private String content;
    private long userId;

    public Post(int id, String title, String content, long userId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.userId = userId;
    }

    public Post(String title, String content, long userId) {
        this.title = title;
        this.content = content;
        this.userId = userId;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public long getUserId() { return userId; }
    public void setUserId(long userId) { this.userId = userId; }
}
