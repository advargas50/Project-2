package clinic;

import org.junit.Test;
import util.Date;

import static org.junit.Assert.*;

public class ProfileTest {

    @Test
    public void compareTo1() {
        Date dob = new Date ("2/29/2004");
        Date dob2 = new Date ("1/29/2004");

        Profile pro1 = new Profile ("John", "Smith", dob);
        Profile pro2 = new Profile("John", "Jones", dob);

        assertEquals("Profile 1 is less than profile 2", 1, pro1.compareTo(pro2));

        Profile pro3 = new Profile ("John", "Jones", dob);
        Profile pro4 = new Profile ("John", "Jones", dob2);

        assertEquals("Profile 3 is less than profile 4", 1, pro3.compareTo(pro4));

        Date dob5 = new Date("2/27/1965");
        Date dobE = new Date("2/27/1965");
        Profile profile5 = new Profile("Jimmy", "Proton", dob5);
        Profile profileE = new Profile("Jimmy", "Neutron", dobE);
        assertEquals("Profile 1 is less than profile 2", 1, profile5.compareTo(profileE));

    }
    @Test
    public void compareToNegative1(){
        Date dob6 = new Date("1/1/2000");
        Date dobF = new Date("12/31/1999");
        Profile profile6 = new Profile("Squidward", "Tentacles", dob6);
        Profile profileF = new Profile("Squidward", "Tentacles", dobF);
        assertEquals("Profile 1 is less than profile 2", -1, profileF.compareTo(profile6));

        Date dob4 = new Date("12/31/1999");
        Date dobD = new Date("12/31/1999");
        Profile profile4 = new Profile("Zach", "Jones", dob4);
        Profile profileD = new Profile("Abe", "Jones", dobD);
        assertEquals("Profile 1 is less than profile 2", -1, profileD.compareTo(profile4));

        Date dob5 = new Date("2/27/1965");
        Date dobE = new Date("2/27/1965");
        Profile profile5 = new Profile("Jimmy", "Proton", dob5);
        Profile profileE = new Profile("Jimmy", "Neutron", dobE);
        assertEquals("Profile 1 is less than profile 2", -1, profileE.compareTo(profile5));

    }

    @Test
    public void compareToEquals(){
        Date dob7 = new Date("4/4/2004");
        Date dobG = new Date("4/4/2004");
        Profile profile7 = new Profile("Angel", "Vargas", dob7);
        Profile profileG = new Profile("Angel", "Vargas", dobG);
        assertEquals("Profile 1 is less than profile 2", 0, profile7.compareTo(profileG));
    }
}