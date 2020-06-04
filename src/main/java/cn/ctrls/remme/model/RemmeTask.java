package cn.ctrls.remme.model;

public class RemmeTask {
    private Integer id;
    private Integer type;
    private String description;
    private String memo;
    private Long gmtStart;
    private Long gmtEnd;
    private Integer owner;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Long getGmtStartTime() {
        return gmtStart;
    }

    public void setGmtStartTime(Long gmtStartTime) {
        this.gmtStart = gmtStartTime;
    }

    public Long getGmtEndTime() {
        return gmtEnd;
    }

    public void setGmtEndTime(Long gmtEndTime) {
        this.gmtEnd = gmtEndTime;
    }

    public Integer getOwner() {
        return owner;
    }

    public void setOwner(Integer owner) {
        this.owner = owner;
    }
}
