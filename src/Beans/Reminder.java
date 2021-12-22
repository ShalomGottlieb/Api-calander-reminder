package Beans;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;

public class Reminder implements Comparable<Reminder> {
    private Calendar toRemind;
    private String memo;
    private boolean importent;
    private boolean poped;

    public Reminder(Calendar toRemind, String memo, boolean importent) {
        this.toRemind = toRemind;
        this.memo = memo;
        this.importent = importent;
        this.poped = false;
    }

    public Reminder() {
    }

    public Calendar getToRemind() {
        return toRemind;
    }

    public void setToRemind(Calendar toRemind) {
        this.toRemind = toRemind;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public boolean isImportent() {
        return importent;
    }

    public void setImportent(boolean importent) {
        this.importent = importent;
    }

    public boolean isPoped() {
        return poped;
    }

    public void setPoped(boolean poped) {
        this.poped = poped;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reminder reminder = (Reminder) o;
        return  /*Objects.equals(toRemind.get(Calendar.YEAR), reminder.toRemind.get(Calendar.YEAR)) &&
                Objects.equals(toRemind.get(Calendar.MONTH), reminder.toRemind.get(Calendar.MONTH)) &&
                Objects.equals(toRemind.get(Calendar.DAY_OF_MONTH), reminder.toRemind.get(Calendar.DAY_OF_MONTH)) &&
                Objects.equals(toRemind.get(Calendar.HOUR), reminder.toRemind.get(Calendar.HOUR)) &&
                Objects.equals(toRemind.get(Calendar.MINUTE), reminder.toRemind.get(Calendar.MINUTE)) &&*/
                Objects.equals(toRemind, reminder.toRemind) &&
                        Objects.equals(memo, reminder.memo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(toRemind, memo, importent, poped);
    }

    public static String beautyDate(Calendar calendar) {
        SimpleDateFormat formatCalendar = new SimpleDateFormat("dd-MM-yyyy HH:mm");

        return formatCalendar.format(calendar.getTime());
    }


    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("Reminder: " + "\n");
        sb.append("memo: " + memo + "\n");
        sb.append("to remind at: " + beautyDate(toRemind) + "\n");
        sb.append("is important: " + isImportent() + "\n");
        sb.append("is popped: " + isPoped() + "\n");

        return sb.toString();
    }


    @Override
    public int compareTo(Reminder reminder) {
        if (equals(reminder))
            return 0;
        return this.toRemind.compareTo(reminder.toRemind);
    }
}
