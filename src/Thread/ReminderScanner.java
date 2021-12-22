package Thread;

import Beans.Reminder;

import java.util.Calendar;
import java.util.List;
import java.util.Set;

public class ReminderScanner implements Runnable {

    private Set<Reminder> reminders;

    public ReminderScanner(Set<Reminder> reminders) {
        this.reminders = reminders;
    }

    @Override
    public void run() {
        while (true) {
            for (Reminder item : reminders) {
                if (!item.isPoped()) {
                    if (item.getToRemind().equals(Calendar.getInstance()) ||
                            item.getToRemind().before(Calendar.getInstance())) {
                        System.out.println(item);
                        item.setPoped(true);
                        if (item.isImportent()) {
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        Thread.sleep(60 * 1000);
                                        System.out.println("IMPORTANT REMINDER SECOND POPPING");
                                        System.out.println(item);
                                        Thread.sleep(60 * 1000);
                                        System.out.println("IMPORTANT REMINDER THIRD AND LAST POPPING");
                                        System.out.println(item);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }).start();
                        }
                    }
                }
            }

            try {
                Thread.sleep(60 * 1000);
            } catch (InterruptedException err) {
                System.out.println("Repair scanner has been stopped");
            }
        }
    }
}
