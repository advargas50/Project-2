package clinic;

import util.Date;

/**
 * The imaging class is a subclass of appointment. It adds a radiology object to appointments and is used by technicians
 * @author Maxim Trofimchuk, Angel Vargas
 */
public class Imaging extends Appointment {
    private Radiology room;

    /**
     * The parameterized constructor for the imaging class
     * @param date the date of the appointment
     * @param timeslot the timeslot of the appointment
     * @param patient the patient attending the appointment
     * @param provider the provider providing this appointment
     * @param room the radiology room of the appointment
     */
    public Imaging(Date date, Timeslot timeslot, Person patient, Person provider, Radiology room) {
        super(date, timeslot, patient, provider);
        this.room=room;
    }

    /**
     * Retrieves the radiology room object of the appointment
     * @return returns radiology room
     */
    public Radiology getRoom() {
        return room;
    }

    /**
     * checks to see if 2 imaging appointments are equal or not
     * @param appointment the imaging appointment being compared
     * @return true if equal, false otherwise
     */
    public boolean equals(Imaging appointment){
        if (this.provider!=null && this.date.equals(appointment.date) && this.timeslot.equals(appointment.timeslot) && this.provider.equals(appointment.provider) && this.room.equals(appointment.getRoom())) {
            return true;
        }
        return false;
    }

    /**
     * turns the imaging object into a string
     * @return the imaging object string
     */
    @Override
    public String toString(){
        int rate=0;
        if (provider instanceof Technician){
            Technician techy=(Technician) provider;
            rate= techy.rate();
        }
        return date.toString() + " " + timeslot.toString() + " " + patient.toString() + " " + provider.toString() + "[" + room + "]";
    }

}