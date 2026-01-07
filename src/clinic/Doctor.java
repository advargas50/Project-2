package clinic;

import java.util.Scanner;
import java.util.Calendar;

/**
 * A subclass of provider. It handles doctors which provide regular office appointments
 * @author Maxim Trofimchuk, Angel Vargas
 */
public class Doctor extends Provider {
    protected Specialty specialty;
    protected String npi;

    /**
     * The parameterized constructor of the doctor class
     * @param profile the profile of the doctor including the first name, last name, and date of birth
     * @param location location of the provider
     * @param specialty doctor's specialty
     * @param npi the unique identifying string for each doctor
     */
    public Doctor(Profile profile, Location location, Specialty specialty, String npi) {
        super(profile, location);
        this.specialty=specialty;
        this.npi=npi;
    }

    /**
     * retrieves the unique npi of the doctor
     * @return the npi string
     */
    public String getNpi(){
        return npi;
    }

    /**
     * get the specialty of the doctor
     * @return
     */
    public Specialty getSpecialty() {
        return specialty;
    }

    /**
     * gets the rate, which is the charge based on the doctor's specialty
     * @return the rate
     */
    public int rate() {
        return specialty.getCharge();
    }

    /**
     * Turns the doctor object into a string
     * @return the doctor string
     */
    @Override
    public String toString() {
        return String.format("[%s, %s][%s, #%s]", profile.toString(), location, this.specialty.name(), npi);
    }
}