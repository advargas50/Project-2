package clinic;

/**
 * Visit class represents a node in a linked list of a patient's appointments
 * @author Angel Vargas, Max Trofimchuk
 */
public class Visit {
    private Appointment appointment;
    private Visit next;

    /**
     * Visit constructor that initializes a node with a reference to an appointment and a pointer to null
     * @param appointment the appointment that this node contains
     */
    public Visit(Appointment appointment) {
        this.appointment = appointment;
        this.next = null;
    }

    /**
     * retrieves the next Visit
     * @return returns next
     */
    public Visit getNext() {
        return next;
    }

    /**
     * Sets what the next visit in the list is
     * @param next represents the next visit in the list
     */
    public void setNext(Visit next) {
        this.next = next;
    }

    /**
     * retrieves the appointment represented in this node
     * @return returns appointment object
     */
    public Appointment getAppointment() {
        return appointment;
    }
}