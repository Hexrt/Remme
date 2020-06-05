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

    public Long getGmtStart() {
        return gmtStart;
    }

    public void setGmtStart(Long gmtStart) {
        this.gmtStart = gmtStart;
    }

    public Long getGmtEnd() {
        return gmtEnd;
    }

    public void setGmtEnd(Long gmtEnd) {
        this.gmtEnd = gmtEnd;
    }

    public Integer getOwner() {
        return owner;
    }

    public void setOwner(Integer owner) {
        this.owner = owner;
    }
}
