package cn.ctrls.remme.model;

public class UserState {
    private Integer doneCount;
    private Integer taskCount;
    private Integer doingCount;
    private Integer finishCount;
    private Integer cancelCount;
    private Integer delayCount;
    private Integer readyCount;



    public Integer getDoneCount() {
        return doneCount;
    }

    public void setDoneCount(Integer doneCount) {
        this.doneCount = doneCount;
    }

    public Integer getTaskCount() {
        return taskCount;
    }

    public void setTaskCount(Integer taskCount) {
        this.taskCount = taskCount;
    }

    public Integer getFinishCount() {
        return finishCount;
    }

    public void setFinishCount(Integer finishCount) {
        this.finishCount = finishCount;
    }

    public Integer getCancelCount() {
        return cancelCount;
    }

    public void setCancelCount(Integer cancelCount) {
        this.cancelCount = cancelCount;
    }

    public Integer getDelayCount() {
        return delayCount;
    }

    public void setDelayCount(Integer delayCount) {
        this.delayCount = delayCount;
    }

    public Integer getDoingCount() {
        return doingCount;
    }

    public void setDoingCount(Integer doingCount) {
        this.doingCount = doingCount;
    }

    public Integer getReadyCount() {
        return readyCount;
    }

    public void setReadyCount(Integer readyCount) {
        this.readyCount = readyCount;
    }
}
