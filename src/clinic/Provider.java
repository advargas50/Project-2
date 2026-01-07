package clinic;

import java.util.Scanner;
import java.util.Calendar;

/**
 * A sublass of person and super class of doctor and technician which handles the providers of an appointment
 * This class implements an abstract method which its subclasses must also implement
 * @author maxim Trofimchuk, Angel Vargas
 */
public abstract class Provider extends Person{
    protected Location location;

    /**
     * parameterized constructor of provider
     * @param profile the profile of the provider including first name, last name, and date of birth
     * @param location the location of the provider
     */
    public Provider(Profile profile, Location location){
        super(profile);
        this.location=location;
    }

    /**
     * Retrieves the location of the provider
     * @return the location of the provider
     */
    public Location getLocation(){
        return location;
    }

    /**
     * The abstract class that is meant to return the rate of the provider
     * @return the rate
     */
    public abstract int rate();

    /**
     * Turns the provider object into a string
     * @return the provider string
     */
    @Override
    public String toString() {
        return String.format("[%s, %s]", profile.toString(), location);
    }
}