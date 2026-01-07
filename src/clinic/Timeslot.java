package clinic;

import java.util.Scanner;
import java.util.Calendar;

/**
 * The timeslot class handles the time of the appointment, which is entered as an integer
 * @author Maxim Trofimchuk, Angel Vargas
 */
public class Timeslot implements Comparable<Timeslot> {
    private int hour;
    private int minute;
    private static final int NOON = 12;

    /**
     * The constructor for timeslot. It takes a number from 1 to 12 the makes the corresponding hour and minute integer
     * @param slotNumber, the identifying number for the specific time slot
     */
    public Timeslot(int slotNumber) {
        if (slotNumber < 1 || slotNumber > 12) {
            throw new IllegalArgumentException("Slot number must be between 1 and 12");
        }
        switch (slotNumber) {
            case 1:
                hour = 9;
                minute = 0;
                break;
            case 2:
                hour = 9;
                minute = 30;
                break;
            case 3:
                hour = 10;
                minute = 0;
                break;
            case 4:
                hour = 10;
                minute = 30;
                break;
            case 5:
                hour = 11;
                minute = 0;
                break;
            case 6:
                hour = 11;
                minute = 30;
                break;
            case 7:
                hour = 14;
                minute = 0;
                break;
            case 8:
                hour = 14;
                minute = 30;
                break;
            case 9:
                hour = 15;
                minute = 0;
                break;
            case 10:
                hour = 15;
                minute = 30;
                break;
            case 11:
                hour = 16;
                minute = 0;
                break;
            case 12:
                hour = 16;
                minute = 30;
                break;
        }
    }

    /**
     * returns the hour of the timeslot
     * @return the hour
     */
    public int getHour() {
        return hour;
    }

    /**
     * turns the timeslot into a string and also checks if the time is AM or PM
     * @return the timeslot string
     */
    @Override
    public String toString() {
        if (hour > NOON) {
            return String.format("%02d:%02d", hour % 12, minute) + " PM";
        }
        return String.format("%02d:%02d", hour, minute) + " AM";
    }

    /**
     * checks to see if two time slots are equal
     * @param obj the timeslot to be compared
     * @return true if equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Timeslot) {
            Timeslot timeslot = (Timeslot) obj;

            if (this.hour == timeslot.hour && this.minute == timeslot.minute) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks to see if a timeslot is earlier than another
     * @param timeslot the object to be compared.
     * @return -1 if the time is earlier, 0 if equal, 1 otherwise
     */
    @Override
    public int compareTo(Timeslot timeslot) {
        if (this.hour < timeslot.hour) {
            return -1;
        }
        else if (this.hour > timeslot.hour) {
            return 1;
        }

        if(this.minute < timeslot.minute) {
            return -1;
        }
        else if (this.minute > timeslot.minute) {
            return 1;
        }

        return 0;
    }
}