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
    private int id;
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

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public LocalDate getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(LocalDate date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
