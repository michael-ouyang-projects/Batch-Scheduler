package tw.ouyang.simplebatchplatform.model;

public class Batch {

    private String id;
    private String jarName;
    private String note;
    private Integer hour;
    private Integer minute;
    private String waitingBatchId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJarName() {
        return jarName;
    }

    public void setJarName(String jarName) {
        this.jarName = jarName;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getHour() {
        return hour;
    }

    public void setHour(Integer hour) {
        this.hour = hour;
    }

    public Integer getMinute() {
        return minute;
    }

    public void setMinute(Integer minute) {
        this.minute = minute;
    }

    public String getWaitingBatchId() {
        return waitingBatchId;
    }

    public void setWaitingBatchId(String waitingBatchId) {
        this.waitingBatchId = waitingBatchId;
    }

}
