package util;

import clinic.Appointment;
import clinic.Person;
import clinic.Provider;

/**
 * A class containing static methods that sorts the list in a variety of ways
 * @author Maxim Trofimchuk, Angel Vargas
 */
public class Sort {
    /**
     * Sorts the list of appointments based on their location
     * @param list the list meant to be sorted
     */
    public static void sortByLocation(List list){
        for (int i = 0; i < list.size(); i++) {
            for (int j = i+1; j < list.size(); j++) {
                Appointment app = (Appointment) list.get(i);
                Appointment app2= (Appointment) list.get(j);
                if (app != null) {
                    Provider provider = (Provider) app.getProvider();
                    Provider provider2= (Provider) app2.getProvider();
                    String l1 = provider.getLocation().getCounty();
                    String l2 = provider2.getLocation().getCounty();
                    if (l1.compareTo(l2) > 0) {
                        list.set(i, app2);
                        list.set (j, app);
                    }
                }
            }

        }
    }

    /**
     * Sorts the list of appointments based on the patient's profile
     * @param list the list meant to be sorted
     */
    public static void sortByPatientProfile(List list){
        for (int i=0; i< list.size(); i++){
            for (int j=i+1; j<list.size(); j++){
                Appointment app= (Appointment) list.get(i);
                Person person = app.getPatient();
                Appointment app2= (Appointment) list.get(j);
                Person person2=app2.getPatient();
                if (person.compareTo(person2)>0){
                    list.set(i, app2);
                    list.set (j, app);
                }
            }
        }
    }

    /**
     * Sorts the list of providers based on their profile
     * @param list the list meant to be sorted
     */
    public static void sortByProviderProfile(List list){
        for (int i=0; i< list.size(); i++){
            for (int j=i+1; j<list.size(); j++){
                Person person = (Person) list.get(i);
                Person person2=(Person) list.get(j);
                if (person.compareTo(person2)>0){
                    list.set(i, person2);
                    list.set (j, person);
                }
            }
        }
    }

    /**
     * Sorts the list of appointments based on appointment time and date
     * @param list the list meant to be sorted
     */
    public static void sortByAppointment(List list){
        for (int i=0; i< list.size(); i++){
            for (int j=i+1; j<list.size(); j++){
                Appointment app = (Appointment) list.get(i);
                Appointment app2=(Appointment) list.get(j);
                if (app.compareTo(app2)>0){
                    list.set(i, app2);
                    list.set (j, app);
                }
            }
        }
    }
}