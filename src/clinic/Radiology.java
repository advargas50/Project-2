package clinic;

import java.util.Scanner;
import java.util.Calendar;

/**
 * Enum class that contains information about specific types of radiology appointments
 * @author Max Trofimchuk
 */
public enum  Radiology {
    XRAY("XRAY"),
    CATSCAN ("CATSCAN"),
    ULTRASOUND("ULTRASOUND");

    private final String type;

    /**
     * Radiology constructor defining enum parameter
     * @param type indicates type of room for imaging appointment
     */
    Radiology(String type) {
        this.type = type;
    }

    /**
     * Gets the enum type
     * @return returns type
     */
    public String getType() {
        return type;
    }

}