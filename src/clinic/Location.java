package clinic;

/**
 * Enum class that contains information on six relevant towns
 * @author Angel Vargas, Max Trofimchuk
 */
public enum Location {
    BRIDGEWATER ("Somerset","08807"),
    CLARK ("Union", "07066"),
    PRINCETON ("Mercer", "08540"),
    PISCATAWAY ("Middlesex", "08854"),
    MORRISTOWN ("Morris", "07960"),
    EDISON ("Middlesex", "08817");

    private final String county;
    private final String zip;

    /**
     * Location Constructor defining enum parameters
     * @param county county of town
     * @param zip zip code of town
     */
    Location (String county, String zip) {
        this.county = county;
        this.zip = zip;
    }

    /**
     * Get County
     * @return returns county of town
     */
    public String getCounty() {
        return county;
    }

    /**
     * Returns the name of the town
     * @return town name
     */
    public String getTown() {
        return this.name();
    }

    /**
     * turns the location object into a string
     * @return the location object string
     */
    @Override
    public String toString() {
        return String.format("%s, %s %s", this.name(), county, zip);
    }

}