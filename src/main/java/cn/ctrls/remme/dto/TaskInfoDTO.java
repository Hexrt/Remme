package cn.ctrls.remme.dto;

public class TaskInfoDTO {
    private Integer id;
    private Integer type;
    private String description;
    private String memo;
    private Long gmtStartTime;
    private Long gmtEndTime;
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
        return gmtStartTime;
    }

    public void setGmtStartTime(Long gmtStartTime) {
        this.gmtStartTime = gmtStartTime;
    }

    public Long getGmtEndTime() {
        return gmtEndTime;
    }

    public void setGmtEndTime(Long gmtEndTime) {
        this.gmtEndTime = gmtEndTime;
    }
}
