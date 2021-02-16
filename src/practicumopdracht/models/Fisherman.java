package practicumopdracht.models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * This method creates the list of all the fishes
 *
 * @author Lorenzo Bindemann
 */
public class Fisherman {
    private String firstname;
    private String lastname;
    private LocalDate date_of_birth;
    private String city;

    /**
     * constructor for fisherman
     * @param firstname
     * @param lastname
     * @param date_of_birth
     * @param city
     */
    public Fisherman(String firstname, String lastname, LocalDate date_of_birth, String city) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.date_of_birth = date_of_birth;
        this.city = city;
    }

    @Override
    public String toString() {
        StringBuilder text = new StringBuilder();
        text.append(firstname + " " +lastname);
        text.append(" - " + date_of_birth.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        text.append(" - " + city);
        return text.toString();
    }
}
