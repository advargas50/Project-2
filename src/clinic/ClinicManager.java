package clinic;

import util.Date;
import util.List;
import util.Sort;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Clinic Manager interprets the commands inputted in the command lines then executes the commands
 * @author Max Trofimchuk, Angel Vargas
 */
public class ClinicManager {
    private List<Technician> techList = new List();
    private List<Appointment> docAppList = new List();
    private List<Appointment> allAppList= new List();
    private List<Imaging> techAppList = new List();
    private List<Doctor> docList = new List();

    private static final int COMMAND_TOKENS = 7;
    private static final int CANCEL_TOKENS = 6;

    /**
     *This method takes in inputs from the command line and calls other functions to implement the commands
     */
    public void run() throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        try{
            createProviders();
        } catch(FileNotFoundException e){
            System.out.println("File not Found");
        }
        System.out.println("\nClinic Manager is running... \n");
        boolean notQ = true;
        while (notQ) {
            String commandLine = scanner.nextLine();
            String[] tokens = commandLine.split(",");
            String command = tokens[0].trim();
            switch (command) {
                case "":
                    break;
                case "D":
                    scheduleDoctorAppointment(tokens);
                    break;
                case "T":
                    scheduleTechnicianAppointment(tokens);
                    break;
                case "PP":
                    displayMessage(command);
                    Sort.sortByAppointment(allAppList);
                    Sort.sortByPatientProfile(allAppList);
                    printAppointmentList(allAppList);
                    break;
                case "Q":
                    notQ = false;
                    System.out.println("Scheduler Terminated");
                    scanner.close();
                    break;
                case "PA":
                    displayMessage(command);
                    Sort.sortByAppointment(allAppList);
                    printAppointmentList(allAppList);
                    break;
                case "PL":
                    displayMessage(command);
                    Sort.sortByAppointment(allAppList);
                    Sort.sortByLocation(allAppList);
                    printAppointmentList(allAppList);
                    break;
                case "C":
                    removeAppointment(tokens);
                    break;
                case "R":
                    rescheduleAppointment(tokens);
                    break;
                case "PS":
                    Sort.sortByPatientProfile(allAppList);
                    displayBillings();
                    break;
                case "PO":
                    displayMessage(command);
                    Sort.sortByAppointment(docAppList);
                    Sort.sortByLocation(docAppList);
                    printAppointmentList(docAppList);
                    break;
                case "PI":
                    displayMessage(command);
                    Sort.sortByAppointment(techAppList);
                    Sort.sortByLocation(techAppList);
                    printAppointmentList(techAppList);
                    break;
                case "PC":
                    providerBillings();
                    break;
                default:
                    System.out.println("Invalid Command!");
                    break;
            }
        }
    }


    /**
     * Iterates through list and prints each appointment if list not empty
     * @param list list object of appointments
     */
    private void printAppointmentList(List list) {
        if (!list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                System.out.println(list.get(i).toString());
            }
            System.out.println("** end of list **");
        }
        else{
            System.out.println("schedule calendar is empty");
        }
    }

    /**
     * Checks if date, dob, npi, and radiology room tokens are valid
     * @param command command taken from the command line
     * @param date appointment date to be checked
     * @param dob date of birth of patient to be checked
     * @param npi npi of doctor to be checked
     * @param rad radiology room value to be checked
     * @return returns false if any parameters are invalid and true otherwise
     */
    private boolean isValid(String command, Date date, Date dob, String npi, String rad){
        if (!date.isValid()) {
            System.out.print("\nAppointment date: " + date + " is not a valid calendar date.");
            return false;
        }
        if (date.isPast()) {
            System.out.print("\nAppointment date: " + date + " is today or a date before today.");
            return false;
        }
        if(!date.isWeekday()) {
            System.out.print("\nAppointment date: " + date + " is Saturday or Sunday.");
            return false;
        }
        if(!date.inSixMonth()){
            System.out.print("\nAppointment date: " + date + " is not within six months.");
            return false;
        }

        if (!dob.isValid()){
            System.out.print("\nPatient dob: " + dob + " is not a valid calendar date.");
            return false;
        }
        if (dob.isFuture()){
            System.out.print("\nPatient dob: " + dob + " is today or a date after today.");
            return false;
        }
        boolean npiCheck =  false;
        if(command.equals("D")){
            for (int i = 0; i < docList.size(); i++) {
                if (docList.get(i) instanceof Doctor){
                    Doctor doctor = (Doctor) docList.get(i);
                    if (doctor.getNpi().equals(npi)){
                        npiCheck = true;
                    }
                }
            }
            if(!npiCheck){
                System.out.print("\n" + npi + " - provider doesn't exist.");
                return false;
            }
            return true;
        }

        if (command.equals("T")){
            try {
                Radiology radiology = Radiology.valueOf(rad.toUpperCase());
            } catch (IllegalArgumentException e){
                System.out.println("\n" + rad + " - imaging service doesn't exist.");
                return false;
            }
        }
        return true;
    }

    /**
     * Chhecks if a given appointment conflicts with any existing appointment
     * @param app appointment object, either imaging or office
     * @return returns true if provided appointment conflicts with another exisiting appointment
     */
    private boolean sameTimeCheck(Appointment app) {
        if (app instanceof Imaging){
            for (int i = 0; i < techAppList.size(); i++) {
                Imaging imaging = (Imaging) techAppList.get(i);
                if (app.equals(imaging)) {
                    System.out.println("\n" + imaging.getPatient().toString() + " has an existing appointment at the same time slot");
                    return true;
                }
            }
        }
        else {
            for (int i = 0; i < docAppList.size(); i++) {
                Appointment appointment = (Appointment) docAppList.get(i);
                if (app.equals(appointment)) {
                    System.out.println("\n" + appointment.getPatient().toString() + " has an existing appointment at the same time slot");
                    return true;
                } else if (app.sameTime(appointment)) {
                    System.out.println("\n" + appointment.getProvider().toString() + " is unavailable at " + appointment.getTimeslot());
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Adds a doctor appointment to the system if all the parameters are valid
     * @param tokens commandline inputs
     */
    private void scheduleDoctorAppointment(String[] tokens) {
        if(!enoughDataTokens(tokens)) {
            return;
        }

        Date date = new Date(tokens[1]);

        if (!Character.isDigit(tokens[2].charAt(0))) {
            System.out.print("\n" + tokens[2] + " is not a valid time slot.");
            return;
        }

        int timeslotIndex = Integer.parseInt(tokens[2]);

        if (timeslotIndex < 1 || timeslotIndex > 12) {
            System.out.print("\n" + timeslotIndex + " is not a valid time slot.");
            return;
        }

        Timeslot slot=new Timeslot(timeslotIndex);
        Date dob = new Date(tokens[5]);
        Profile pro = new Profile(tokens[3], tokens[4], dob);
        Person per = new Person (pro);
        String npi=tokens[6];

        if(isValid(tokens[0], date, dob, npi, null)) {
            for (int i = 0; i < docList.size(); i++) {
                if (docList.get(i) instanceof Doctor) {
                    Doctor doc = (Doctor) docList.get(i);

                    if (doc.getNpi().equals(npi)) {
                        Appointment ap = new Appointment(date, slot, per, doc);
                        if (!sameTimeCheck(ap)) {
                            docAppList.add(ap);
                            allAppList.add(ap);
                            System.out.println(ap.toString() + " booked");
                        }
                        return;
                    }
                }
            }
        }
    }

    /**
     * Displays required messages for PP, PL, PA, PO, and PI commands
     * @param command first token from commandline
     */
    private void displayMessage (String command) {
        if (allAppList.isEmpty() || (command.equals("PI")&&techAppList.isEmpty()) || (command.equals("PO")&&docAppList.isEmpty())){
            return;
        }
        else{
            if (command.equals("PP")) {
                System.out.print("** List of appointments ordered by patient/date/time \n");
            }
            else if (command.equals("PL")) {
                System.out.print("** List of appointments ordered by county/date/time \n");
            }
            else if(command.equals("PA")) {
                System.out.print("** List of appointments ordered by date/time/provider \n");
            }
            else if (command.equals("PO")){
                System.out.print("** List of office appointments ordered by county/date/time \n");
            }
            else if (command.equals("PI")){
                System.out.print("** List of radiology appointments ordered by county/date/time \n");
            }
        }
    }

    /**
     * Checks if a commandline has enough tokens for schedule and reschedule
     * @param tokens commandline input
     * @return returns false if not enough tokens and true otherwise
     */
    private boolean enoughDataTokens (String[] tokens) {
        if(tokens.length < COMMAND_TOKENS) {
            System.out.println("\nMissing data tokens.");
            return false;
        }
        return true;
    }

    /**
     * Schedules technician appointment if all parameters are valid
     * @param tokens commandline input
     */
    private void scheduleTechnicianAppointment(String[] tokens){
        if(!enoughDataTokens(tokens)) {
            return;
        }

        Date date = new Date(tokens[1]);
        if (!Character.isDigit(tokens[2].charAt(0))) {
            System.out.print("\n" + tokens[2] + " is not a valid time slot.");
            return;
        }
        int timeslotIndex = Integer.parseInt(tokens[2]);

        if (timeslotIndex < 1 || timeslotIndex > 12) {
            System.out.print("\n" + timeslotIndex + " is not a valid time slot.");
            return;
        }
        Timeslot slot=new Timeslot(timeslotIndex);
        Date dob = new Date(tokens[5]);
        Profile pro = new Profile(tokens[3], tokens[4], dob);
        Person per = new Person (pro);
        String service = tokens[6];
        if(isValid(tokens[0], date, dob, null, service)) {
            Radiology rad = Radiology.valueOf(tokens[6].toUpperCase().trim());
            for (int i = 0; i < techList.size(); i++) {
                Technician techy = (Technician) techList.get(i);
                Imaging app = new Imaging(date, slot, per, techy, rad);
                if (!isTechnicianBusy(app)) {
                    if (!sameTimeCheck(app)) {
                        techAppList.add(app);
                        allAppList.add(app);
                        System.out.println(app.toString() + " booked");
                        return;
                    }
                    return;
                }
            }
            System.out.println("Cannot find an available technician at all locations for " + rad.name() + " at slot " + slot);
        }
    }

    /**
     * Orders the rotation list for technicians
     */
    private void createTechOrder() {
        int left = 0;
        int right = techList.size() - 1;

        while (left < right) {
            Technician temp = (Technician) techList.get(left);
            techList.set(left, techList.get(right));
            techList.set(right, temp);
            left++;
            right--;
        }
    }

    /**
     * Creates provider list from given providers in providers.txt file
     * @throws FileNotFoundException throws exception if file is not found
     */
    private void createProviders() throws FileNotFoundException {

        File file = new File("src/clinic/providers.txt");
        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {

            String proLine = scanner.nextLine();
            String[] tokens = proLine.split("  ");
            String type = tokens[0].trim();
            if (type.equals("D")) {
                createDoctor(tokens);
            }
            if (type.equals("T")) {
                createTechnician(tokens);
            }
        }
        List providersList= docList;
        createTechOrder();

        for (int i=0; i<techList.size(); i++){
            Technician techy = (Technician) techList.get(i);
            providersList.add(techy);
        }
        Sort.sortByProviderProfile(providersList);
        System.out.println("Providers loaded to the list.");
        for (int i = 0; i < providersList.size(); i++) {
            System.out.println(providersList.get(i).toString());
        }
        System.out.println();
        System.out.println("Rotation list for the technicians.");
        for (int i = 0; i < techList.size(); i++) {
            Technician techy = (Technician) techList.get(i);
            System.out.print(techy.getProfile().getFname() + " " + techy.getProfile().getLname() + " (" + techy.getLocation().getTown() + ") " + " -->  ");
        }
    }

    /**
     * Creates a doctor object and adds it to list of doctors
     * @param tokens commandline input
     * @return returns created doctor
     */
    private Doctor createDoctor(String[] tokens){
        Date dob= new Date (tokens[3]);
        Profile profile=new Profile(tokens[1], tokens[2], dob);

        Location location = Location.valueOf(tokens[4].trim().toUpperCase());

        Specialty specialty = Specialty.valueOf(tokens[5].trim().toUpperCase());
        String npi=tokens[6];
        Doctor doc= new Doctor (profile, location, specialty, npi);
        docList.add(doc);
        return doc;
    }

    /**
     * Creates a doctor object and adds it to list of doctors
     * @param tokens commandline input
     * @return returns created doctor
     */
    private Technician createTechnician(String[] tokens){

        Date dob= new Date (tokens[3]);
        Profile profile=new Profile(tokens[1], tokens[2], dob);

        Location location = Location.valueOf(tokens[4].trim().toUpperCase());
        int rate= Integer.parseInt(tokens[5]);
        Technician techy=new Technician(profile, location, rate);
        techList.add(techy);
        return techy;
    }

    /**
     * Checks if a technician is occupied at a certain date and timeslot
     * @param appointment imaging appointment which will be checked if its technician is busy
     * @return returns true if technician is occupied and false otherwise
     */
    private boolean isTechnicianBusy(Imaging appointment) {
        for (int i=0; i< techAppList.size();i++){
            Imaging techApp= (Imaging) techAppList.get(i);
            if (techApp!= null) {
                if (techApp.equals(appointment) || techApp.getProvider().equals(appointment.getProvider()) && techApp.getTimeslot().equals(appointment.getTimeslot())){
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Reschedules an existing appointment to another timeslot in the same day
     * @param tokens commandline input beginning with 'R'
     */
    private void rescheduleAppointment(String[] tokens) {
        if(!enoughDataTokens(tokens)){return;}
        if (!Character.isDigit(tokens[6].charAt(0))) {
            System.out.println(tokens[6] + " is not a valid time slot.");
            return;}
        int newTimeslotIndex = Integer.parseInt(tokens[6]);
        if (newTimeslotIndex < 1 || newTimeslotIndex > 12) {
            System.out.println(newTimeslotIndex + " is not a valid time slot.");
            return;}
        Timeslot newT = new Timeslot(newTimeslotIndex);
        Date dob = new Date(tokens[5]);
        Date date = new Date(tokens[1]);
        int timeslotIndex = Integer.parseInt(tokens[2]);
        Timeslot slot = new Timeslot(timeslotIndex);
        Profile profile = new Profile(tokens[3], tokens[4], dob);
        Person patient = new Person(profile);
        Appointment appointment = new Appointment(date, slot, patient, patient);
        Person provider=null;
        boolean contain = false;
            for (int i = 0; i < docAppList.size(); i++) {
                Appointment app = (Appointment) docAppList.get(i);
                if (app != null && app.equals(appointment)) {
                    Person temp = app.getProvider();
                    docAppList.remove(app);
                    allAppList.remove(app);
                    contain = true;
                    provider = temp;
                    break;
                }
            }
            if (!contain) {
                System.out.println(date + " " + slot + " " + patient + " does not exist.");
                return;}
            if (!sameTimeCheck(appointment)) {
                Appointment newAppointment = new Appointment(date, newT, patient, provider);
                System.out.println("Rescheduled to " + newAppointment);
                docAppList.add(newAppointment);
                allAppList.add(newAppointment);
            }
    }

    /**
     * Cancels an appointment if it exists
     * @param tokens commandline input beginning with 'C'
     */
    private void removeAppointment(String[] tokens) {
        if (tokens.length < CANCEL_TOKENS) {
            System.out.println("Missing data tokens");
            return;
        }

        Date dob = new Date(tokens[5]);
        Date date = new Date(tokens[1]);
        int timeslotIndex = Integer.parseInt(tokens[2]);
        Timeslot slot = new Timeslot(timeslotIndex);
        Profile profile = new Profile(tokens[3], tokens[4], dob);
        Person patient = new Person (profile);
        Appointment appointment = new Appointment (date, slot, patient, patient);

        boolean contain = false;
        for (int i = 0; i < allAppList.size(); i++) {
            Appointment app= (Appointment) allAppList.get(i);
            if (app != null) {
                if (app.equals(appointment)) {
                    docAppList.remove(app);
                    allAppList.remove(app);
                    if (tokens[0].equals("C")){
                        System.out.println(app + " has been canceled");
                    }
                    i--;
                    contain = true;
                }
            }
        }
        if (!contain) {
            System.out.println(date + " " + slot + " " + patient + " - appointment does not exist.");
        }
    }

    /**
     * Displays the amount each patient will be charged based on their appointments
     */
    private void displayBillings() {
        if (!allAppList.isEmpty()){
            List patients = allAppList.makePatients();
            List charges = new List();
            List profiles = new List();
            for (int i = 0; i < patients.size(); i++) {
                Patient patient = (Patient) patients.get(i);
                int charge = patient.charge();
                Profile profile = ((Patient) patients.get(i)).getProfile();
                profiles.add(profile);
                charges.add(charge);
            }
            List usedNames = new List();
            System.out.println("** Billing statement ordered by patient **");
            for (int i = 0; i < charges.size(); i++) {
                Profile pro= (Profile) profiles.get(i);
                int charge = (int) charges.get(i);
                boolean nameUsed = false;
                for (int k = 0; k < usedNames.size(); k++) {
                    Profile used = (Profile) usedNames.get(k);
                    if (pro != null && pro.equals(used)) {
                        nameUsed = true;
                        break;
                    }
                }

                if (!nameUsed) {
                    usedNames.add(pro);
                    int totalCharge = charge;
                    for (int j = i + 1; j < charges.size(); j++) {
                        Profile pro2= (Profile) profiles.get(j);
                        int charge2= (int) charges.get(j);
                        if (pro!=null){
                            if (pro.equals(pro2)) {
                                totalCharge += charge2;
                            }
                        }
                    }
                    if (pro != null) {
                        System.out.println(pro.toString() + ": " + totalCharge + " Dollars.");
                    }
                }
            }
            System.out.println("** end of list **");
        }
        else {
            System.out.println("schedule calendar is empty");
        }
    }

    /**
     * Displays provider credit amounts ordered by provider
     */
    private void providerBillings(){
        if (allAppList.isEmpty()){ System.out.println("Scheduler Calendar is empty"); return;}
        int totalCharge=0;
        boolean allZeroes=true;
        List charges= new List();
        List providersList= new List();
        for (int i=0; i<docList.size(); i++){
            if (docList.get(i) instanceof Doctor){
                providersList.add(docList.get(i));
            }
        }
        System.out.println(providersList.size());
        for (int i=0; i<techList.size(); i++){
            Technician techy = (Technician) techList.get(i);
            providersList.add(techy);
        }
        for (int i=0; i< providersList.size(); i++){
            for (int j=0; j< allAppList.size(); j++){
                Provider pro= (Provider) providersList.get(i);
                Appointment app= (Appointment) allAppList.get(j);
                Provider pro2= (Provider) app.getProvider();
                if (pro.equals(pro2)){
                    totalCharge+=pro2.rate();
                    charges.add(totalCharge);
                    allZeroes=false;
                }
            }
        }
        if (!allZeroes){
            System.out.println("**Credit Amount Ordered by Provider");
            for (int i=0; i<providersList.size();i++){
                Provider pro= (Provider) providersList.get(i);
                int charge= (int) charges.get(i);
                System.out.println ("("+(i+1)+")"+pro.toString()+"[credit amount $"+charge+"]");
            }
            System.out.println("** end of list **");
        }
    }
}