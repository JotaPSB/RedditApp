package cat.itb.redditapp.model;

public class Comment {
    private String picture;
    private String user;
    private int votes;

    public Comment(String picture, String user, int votes) {
        this.picture = picture;
        this.user = user;
        this.votes = votes;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }
}
