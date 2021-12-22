package Fasade;

import Beans.Reminder;
import Thread.ReminderScanner;

import java.util.*;

public class ReminderManager {
    private Set<Reminder> reminders;
    ReminderScanner reminderScanner;
    private Scanner scanner = new Scanner(System.in);

    public ReminderManager() {
        reminders = new TreeSet<>();
        reminderScanner = new ReminderScanner(reminders);
        new Thread(reminderScanner).start();
        reminderMenu();
    }

    private void reminderMenu() {
        int choice = 0;
        do {
            System.out.println("WELCOME TO REMINDER SYSTEM MENU");
            System.out.println("please enter your choice:");
            System.out.println("1 - create new reminder task");
            System.out.println("2 - view all reminders");
            System.out.println("3 - quit");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    addReminder();
                    break;

                case 2:
                    viewAllReminders();
                    break;

                case 3:
                    endProgram();
                    break;

                default:
                    System.out.println("Wrong choice");
            }
        } while (choice != 3);
    }

    private void addReminder() {
        System.out.println("Create a new reminder:");
        System.out.println("enter memo to remind:");
        scanner.nextLine();
        String memo = scanner.nextLine();
        System.out.println("enter date and time to remind:");
        Calendar toRemind = Calendar.getInstance();
        boolean correct = false;
        while (!correct) {
            System.out.println("enter year:");
            int year = scanner.nextInt();
            System.out.println("enter month:");
            int month = scanner.nextInt() - 1;
            System.out.println("enter a day of month:");
            int day = scanner.nextInt();
            System.out.println("enter hours (0-23):");
            int hour = scanner.nextInt();
            System.out.println("enter minutes (0-59):");
            int minutes = scanner.nextInt();
            toRemind.set(Calendar.DAY_OF_MONTH, day);
            toRemind.set(Calendar.MONTH, month);
            toRemind.set(Calendar.YEAR, year);
            toRemind.set(Calendar.HOUR, hour);
            toRemind.set(Calendar.MINUTE, minutes);
            toRemind.set(Calendar.SECOND, 0);             //in order to simplify equals method
            toRemind.set(Calendar.MILLISECOND, 0);         //in order to simplify equals method
            if (toRemind.after(Calendar.getInstance())) {
                correct = true;
            } else System.out.println("date enterd has passed. enter a correct date to remind:");
        }
        scanner.nextLine();
        System.out.println("is important ? (y/n)");
        char c = scanner.next().charAt(0);
        boolean importent = (c == 'Y' || c == 'y');

        Reminder reminder = new Reminder(toRemind, memo, importent);
        if (reminders != null) {
            for (Reminder item : reminders) {
                if (item.equals(reminder)) {
                    System.out.println("item already exists, going back to main menu");
                    return;
                }
            }
        }
        reminders.add(reminder);
        System.out.println("reminder successfully added, going back to main menu");

    }

    private void viewAllReminders() {
        //Collections.sort(reminders);
        reminders.forEach(System.out::println);
    }

    private void endProgram() {
        System.out.println("Good Bye !!!!");
        System.exit(100);

    }


}

