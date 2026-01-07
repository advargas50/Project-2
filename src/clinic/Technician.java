package clinic;

import java.util.Scanner;
import java.util.Calendar;

/**
 * This is a subclass of provider. It handles technicians which provide imaging appointments
 * @author Maxim Trofimchuk, Angel Vargas
 */
public class Technician extends Provider {
    protected int ratePerVisit;

    /**
     * Constructor for Technician
     * @param profile the technician's profile, including first and last name and date of birth
     * @param location the location of the provider
     * @param ratePerVisit the cost of a visit to this technician
     */
    public Technician(Profile profile, Location location, int ratePerVisit) {
        super(profile, location);
        this.ratePerVisit=ratePerVisit;
    }

    /**
     * Gets the rate of the technician
     * @return the rate
     */
    public int rate(){
        return ratePerVisit;
    }

    /**
     * Turns Technician into a String for printing
     */
    @Override
    public String toString() {
        return String.format("[%s, %s][rate: $%s]", profile.toString(), location, ratePerVisit);
    }
}