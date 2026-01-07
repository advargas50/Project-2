package clinic;

/**
 * Specialty enum class that contains information on each possible specialty
 * @author Angel Vargas, Max Trofimchuk
 */
public enum Specialty {
    FAMILY (250),
    PEDIATRICIAN (300),
    ALLERGIST (350);

    private final int charge;

    /**
     * Constructor defining enum parameter of charge
     * @param charge charge of specialty
     */
    Specialty (int charge) {
        this.charge = charge;
    }

    /**
     * retrieves a specialty's charge
     * @return returns charge
     */
    public int getCharge() {
        return charge;
    }

    /**
     * makes string containing specialty name
     * @return returns specialty as a string
     */
    @Override
    public String toString() {
        return String.format("%s", this.name());
    }
}
