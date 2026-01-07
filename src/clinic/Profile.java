package clinic;

import util.Date;

/**
 * class that describes the name and date of birth of a person
 * @author Angel Vargas, Max Trofimchuk
 */
public class Profile implements Comparable<Profile> {
    protected String fname;
    protected String lname;
    protected Date dob;

    /**
     * Profile constructor that initializes created profile with given parameters
     * @param fname name of person
     * @param lname last name of person
     * @param dob date of birth of person
     */
    public Profile(String fname, String lname, Date dob) {
        this.fname = fname;
        this.lname = lname;
        this.dob = dob;
    }

    /**
     * retrieves profile first name
     * @return returns String first name
     */
    public String getFname() {
        return fname;
    }

    /**
     * retrieves last name
     * @return returns String last name
     */
    public String getLname() {
        return lname;
    }

    /**
     * retrieves date of birth
     * @return returns date of birth date object
     */
    public Date getDob() {
        return dob;
    }

    /**
     * turns profile into comprehensive string
     * @return returns profile in string formation
     */
    @Override
    public String toString() {
        String bday = dob.toString();

        return fname + " " + lname + " " + bday;
    }

    /**
     * checks if two profile objects are equal
     * @param obj profile object to be checked
     * @return returns true if equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Profile) {
            Profile profile = (Profile) obj;

            String lName = this.lname.toLowerCase();
            String plName = profile.lname.toLowerCase();

            String fName = this.fname.toLowerCase();
            String pfName = profile.fname.toLowerCase();

            return fName.equals(pfName) && lName.equals(plName) && this.dob.equals(profile.dob);
        }
        return false;
    }

    /**
     * compares two profile objects
     * @param profile the object to be compared.
     * @return returns 1 if profile is greater than other based on last name/first name/dob, -1 if less than, and 0 if equal
     */
    @Override
    public int compareTo(Profile profile) {
        String lName = this.lname.toLowerCase();
        String plName = profile.lname.toLowerCase();

        String fName = this.fname.toLowerCase();
        String pfName = profile.fname.toLowerCase();

        if (lName.compareTo(plName) > 0) {
            return 1;
        }
        if (lName.compareTo(plName) < 0) {
            return -1;
        }
        if (lName.compareTo(plName) == 0) {
            if (fName.compareTo(pfName) > 0) {
                return 1;
            }
            if (fName.compareTo(pfName) < 0) {
                return -1;
            }
            if (fName.compareTo(pfName) == 0) {
                if (this.dob.compareTo(profile.dob) > 0) {
                    return 1;
                }
                if (this.dob.compareTo(profile.dob) < 0) {
                    return -1;
                }
                return 0;
            }
        }
        return 0;
    }
}