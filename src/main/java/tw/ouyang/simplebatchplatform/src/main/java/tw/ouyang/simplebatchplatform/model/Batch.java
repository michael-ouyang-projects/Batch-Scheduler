package tw.ouyang.simplebatchplatform.model;

public class Batch {

    private String jarName;
    private String note;
    private Integer hour;
    private Integer minute;
    private String waitingJar;

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

    public String getWaitingJar() {
        return waitingJar;
    }

    public void setWaitingJar(String waitingJar) {
        this.waitingJar = waitingJar;
    }

}
