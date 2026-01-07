package util;

import clinic.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class ListTest {

    @Test
    public void add() {
        List addList= new List();
        Date dob= new Date("12/31/2005");
        Date providerDob= new Date ("10/10/2001");
        Timeslot slot= new Timeslot(2);
        Date appDate= new Date("12/20/2024");
        Profile patientProfile = new Profile ("John", "Cena", dob);
        Person patient= new Person (patientProfile);
        Profile providerpro= new Profile ("George", "WashingBeard", providerDob);
        Person provider= new Person (providerpro);
        Appointment appointment= new Appointment(appDate, slot, patient, provider);
        addList.add(appointment);


        assertTrue("The array should contain appoimntment", addList.contains(appointment));
    }

    @Test
    public void remove() {
        List removeList= new List();
        Date dob= new Date("12/31/2005");
        Date providerDob= new Date ("10/10/2001");
        Timeslot slot= new Timeslot(2);
        Date appDate= new Date("12/20/2024");
        Profile patientProfile = new Profile ("John", "Cena", dob);
        Person patient= new Person (patientProfile);
        Profile providerpro= new Profile ("George", "WashingBeard", providerDob);
        Person provider= new Person (providerpro);
        Appointment appointment= new Appointment(appDate, slot, patient, provider);
        removeList.add(appointment);
        removeList.remove(appointment);
        assertFalse("The appointment should not be in the list because it was removed", removeList.contains(appointment) );

    }
}