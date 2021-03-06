package cn.ctrls.remme.model;

public class UserMeta {
    private String avatarUrl;
    private String emailUrl;
    private Integer githubId;
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public Integer getGithubId() {
        return githubId;
    }

    public void setGithubId(Integer githubId) {
        this.githubId = githubId;
    }

    public String getEmailUrl() {
        return emailUrl;
    }
    public void setEmailUrl(String emailUrl) {
        this.emailUrl = emailUrl;
    }
}
