package util;

import java.util.Calendar;

/**
 * Class holds a day, month, and year to represent a date
 * @author Angel Vargas, Max Trofimchuk
 */
public class Date implements Comparable<Date> {
    private int year;
    private int month;
    private int day;

    public static final int QUADRENNIAL = 4;
    public static final int CENTENNIAL = 100;
    public static final int QUATERCENTENNIAL = 400;
    public static final int JANUARY = 1, JULY = 7,
            FEBRUARY = 2, AUGUST = 8,
            MARCH = 3, SEPTEMBER = 9,
            APRIL = 4, OCTOBER = 10,
            MAY = 5, NOVEMBER = 11,
            JUNE = 6, DECEMBER = 12;
    public static final int MAXTHIRTY = 30, MAXLEAP = 29,
            MAXNORMAL = 31, MAXFEB = 28;


    /**
     * Constructor that takes date string in the form of M/D/Y and creates date object
     * @param date string in the form of M/D/Y
     */
    public Date(String date) {
        String[] dates = date.split("/");

        this.year = Integer.parseInt(dates[2]);
        this.month = Integer.parseInt(dates[0]);
        this.day = Integer.parseInt((dates[1]));
    }

    /**
     * Default Constructor initializing date to today
     */
    private Date() {
        Calendar calendar = Calendar.getInstance();

        this.year = calendar.get(Calendar.YEAR);
        this.month = calendar.get(Calendar.MONTH) + 1;
        this.day = calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * Copy Constructor that makes a Date with same values as given Date
     * @param date Date object that will have values copied
     */
    public Date(Date date) {
        this.year = date.year;
        this.month = date.month;
        this.day = date.day;
    }

    /**
     * compares two date objects
     * @param date the object to be compared.
     * @return returns 1 if date is greater than other based on day/time, -1 if less than, and 0 if equal
     */
    @Override
    public int compareTo(Date date) {
        Calendar date1 = Calendar.getInstance();
        Calendar date2 = Calendar.getInstance();

        date1.set(this.year, this.month, this.day);
        date2.set(date.year, date.month, date.day);

        if (this.day == date.day && this.month == date.month && this.year == date.year) {
            return 0;
        }

        int compare = date1.compareTo(date2);

        if (compare < 0) {
            return -1;
        }
        if (compare > 0) {
            return 1;
        }
        return 0;
    }

    /**
     * Checks if a date is before today or today
     * @return returns true if a date is today or before today
     */
    public boolean isPast () {
        Calendar date = Calendar.getInstance();
        Calendar date2 = Calendar.getInstance();
        Date date3 = new Date();
        date2.set(this.year, this.month - 1, this.day);

        if (date2.before(date) || this.equals(date3)) {
            return true;
        }
        return false;
    }

    /**
     * Checks if the Date object has a valid date
     * @return returns true if the date has a valid month, day, and year, and false otherwise
     */
    public boolean isValid() {
        if (month > 12 || month < 1 || day < 1) {
            return false;
        }

        boolean isLeap = (year % QUADRENNIAL == 0 && year % CENTENNIAL != 0) || (year % QUADRENNIAL == 0 && year % CENTENNIAL == 0 && year % QUATERCENTENNIAL == 0);

        if (month == FEBRUARY) {
            if (isLeap) {
                if (day > MAXLEAP) {
                    return false;
                }
            }
            else {
                if (day > MAXFEB) {
                    return false;
                }
            }
        }
        else if (month == SEPTEMBER || month == APRIL || month == JUNE || month == NOVEMBER) {
            if (day > MAXTHIRTY) {
                return false;
            }
        }
        else if (day > MAXNORMAL) {
            return false;
        }

        return true;
    }

    /**
     * Checks if date is in the future
     * @return returns true if a date is after today
     */
    public boolean isFuture() {
        Calendar date = Calendar.getInstance();
        Calendar date2 = Calendar.getInstance();
        Date date3 = new Date();
        date2.set(this.year, this.month - 1, this.day);

        if (date2.after(date) || this.equals(date3)) {
            return true;
        }
        return false;
    }

    /**
     * makes a string containing date information
     * @return returns a date in string form
     */
    @Override
    public String toString() {
        return month + "/" + day + "/" + year;
    }

    /**
     * Checks if the date of this Date object is within six months of today
     * @return returns true if date of Date object is within six months of today, and false otherwise
     */
    public boolean inSixMonth() {
        Calendar date = Calendar.getInstance();
        Calendar sixMonths = Calendar.getInstance();
        Calendar date2 = Calendar.getInstance();

        date2.set(this.year, this.month - 1, this.day);

        sixMonths.add(Calendar.MONTH, 6);

        if (date2.after(date) && date2.before(sixMonths)) {
            return true;
        }
        return false;
    }

    /**
     * Checks if date of Date object is a weekday
     * @return returns true if date of Date object falls on a weekday, and false if on a weekend
     */
    public boolean isWeekday() {
        Calendar date = Calendar.getInstance();

        date.set(this.year, (this.month - 1), this.day);

        if (date.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && date.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
            return true;
        }

        return false;
    }

    /**
     * checks if two dates are equal
     * @param obj object to be checked
     * @return returns true if equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Date) {
            Date date = (Date) obj;

            return this.day == date.day && this.month == date.month && this.year == date.year;
        }
        return false;
    }

}