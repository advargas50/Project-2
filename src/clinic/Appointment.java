package clinic;

import util.Date;

/**
 * Class contains information about a patient's appointment, including provider, time, and date
 * @author Angel Vargas, Max Trofimchuk
 */
public class Appointment implements Comparable<Appointment> {
    protected Date date;
    protected Timeslot timeslot;
    protected Person patient;
    protected Person provider;

    /**
     * Parameterized Constructor for appointment class
     * @param date given date
     * @param timeslot given timeslot
     * @param patient given patient
     * @param provider given provider
     */
    public Appointment (Date date, Timeslot timeslot, Person patient, Person provider) {
        this.date = date;
        this.timeslot =timeslot;
        this.patient = patient;
        this.provider = provider;
    }

    /**
     * Get provider
     * @return returns provider object
     */
    public Person getProvider () {
        return provider;
    }

    /**
     * Get patient
     * @return returns profile object representing patient
     */
    public Person getPatient(){
        return patient;
    }

    /**
     * Get Date
     * @return returns date
     */
    public Date getDate(){
        return date;
    }

    /**
     * Get Timeslot
     * @return returns timeslot
     */
    public Timeslot getTimeslot(){
        return timeslot;
    }

    /**
     * Checks if two appointments have the same provider and are at the same date
     * @param appointment appointment object that is being compared
     * @return return true if appointments are at the same time with the same provider
     */
    public boolean sameTime(Appointment appointment) {
        if (appointment != null) {

            if (this.date.equals(appointment.date) && this.timeslot.equals(appointment.timeslot) && this.provider.equals(appointment.provider)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if two appointment objects are equal
     * @param obj object to be checked
     * @return returns true if equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {

        if (obj instanceof Appointment) {
            Appointment appointment = (Appointment) obj;

            if (this.patient!=null && this.date.equals(appointment.date) && this.timeslot.equals(appointment.timeslot) && this.patient.equals(appointment.patient)) {
                return true;
            }
        }
        return false;
    }

    /**
     * compares two appointment objects
     * @param appointment the object to be compared.
     * @return returns 1 if appointment is greater than other based on date/time/profile, -1 if less than, and 0 if equal
     */
    @Override
    public int compareTo(Appointment appointment) {

        if (this.date.compareTo(appointment.date) < 0) {
            return -1;
        }
        else if (this.date.compareTo(appointment.date) > 0) {
            return 1;
        }

        if (this.timeslot.compareTo(appointment.timeslot) < 0){
            return -1;
        }
        else if(this.timeslot.compareTo(appointment.timeslot) > 0){
            return 1;
        }

            if (this.patient.compareTo(appointment.getPatient()) < 0) {
                return -1;
            }
            else if (this.patient.compareTo(appointment.getPatient()) > 0) {
                return 1;
            }

        return 0;
    }

    /**
     * Turns appointment to a string
     * @return returns information as string
     */
    @Override
    public String toString() {
        return date.toString() + " " + timeslot.toString() + " " + patient.toString() + " " + provider.toString();
    }
}