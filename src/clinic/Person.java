package clinic;

import java.util.Scanner;
import java.util.Calendar;

/**
 * Class that represents a person. Superclass for patient and provider
 * @author Max Trofimchuk, Angel Vargas
 */
public class Person implements Comparable<Person> {
    protected Profile profile;

    /**
     * Person constructor that initializes object with given profile object
     * @param profile profile object containing a person's basic information
     */
    public Person (Profile profile){
        this.profile=profile;
    }

    /**
     * Gets Person's profile
     * @return returns profile
     */
    public Profile getProfile() {
        return profile;
    }

    /**
     * turns person information into comprehensive string
     * @return returns string of person information
     */
    @Override
    public String toString() {
        return profile.toString();
    }

    /**
     * compares two person objects
     * @param person the object to be compared.
     * @return returns 1 if person is greater than other based on profile, -1 if less than, and 0 if equal
     */
    @Override
    public int compareTo(Person person) {
        if (this.getProfile().compareTo(person.getProfile()) > 0) {
            return 1;
        }
        if (this.getProfile().compareTo(person.getProfile()) < 0) {
            return -1;
        }
        return 0;
    }

    /**
     * checks if two person objects are equal
     * @param obj object to be checked
     * @return returns true if equal, false otherwise
     */
    @Override
    public boolean equals(Object obj){
        if (obj instanceof Person) {
            Person person = (Person) obj;
            if (this.profile.equals(person.profile)){
                return true;
            }
        }
        return false;
    }


}