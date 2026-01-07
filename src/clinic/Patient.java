package clinic;

/**
 * Subclass of Person that contains a linked list of a patient's visits
 * @author Angel Vargas, Max Trofimchuk
 */
public class Patient extends Person {
    private Visit visits;

    /**
     * Parameterized Constructor that initializes a Patient object with a profile and visit node
     * @param profile Profile object that represents patient
     * @param visits Visit object that represents initial node in Visit linked list
     */
    public Patient (Profile profile, Visit visits) {
        super(profile);
        this.visits = visits;
    }

    /**
     * Traverses visit linked list and accumulates the charge for each appointment in patient's previous appointments
     * @return returns the total charge that a patient owes
     */
    public int charge() {
        int totalCharge = 0;

        Visit currentVisit = visits;

        while (currentVisit != null) {
            Provider provider= (Provider) currentVisit.getAppointment().getProvider();
            if (provider instanceof Doctor doc){
                totalCharge += doc.rate();
            }
            else if (provider instanceof Technician techy){
                totalCharge += techy.rate();
            }

            currentVisit = currentVisit.getNext();
        }

        return totalCharge;
    }

    /**
     * checks if patient objects are equal
     * @param obj patient object to be checked
     * @return returns true if equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Patient) {
            Patient patient = (Patient) obj;
            return profile.equals(patient.profile);
        }
        return false;
    }

    /**
     * Adds a new visit node to the linked list
     * @param appointment given appointment object that will be used to create visit node
     */
    public void addVisit(Appointment appointment) {
        Visit newVisit = new Visit(appointment);

        if (visits == null) {
            visits = newVisit;
        }
        else {
            Visit current = visits;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(newVisit);
        }
    }

    /**
     * creates string containing information summarizing patient
     * @return returns string containing information
     */
    @Override
    public String toString() {
        return profile.toString();
    }

    /**
     * Gets Profile object
     * @return returns Patient object's profile
     */
    public Profile getProfile() {
        return profile;
    }

    /**
     * Gets visit node
     * @return visit object
     */
    public Visit getVisits() {
        return visits;
    }
}