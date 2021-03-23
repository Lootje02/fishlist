package practicumopdracht.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * This method creates a fisherman and on a fisherman you can add fishes
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

    /**
     * getter function for the firstname
     * @return
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * getter for the lastname
     * @return
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * getter for the date of birth
     * @return
     */
    public LocalDate getDate_of_birth() {
        return date_of_birth;
    }

    /**
     * getter for the city
     * @return
     */
    public String getCity() {
        return city;
    }

    /**
     * setter function for firstname
     * @param firstname
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * setter function for lastname
     * @param lastname
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * setter function for date of birth
     * @param date_of_birth
     */
    public void setDate_of_birth(LocalDate date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    /**
     * setter function for city
     * @param city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @Override function for the toString of the fisherman
     * @return
     */
    @Override
    public String toString() {
        StringBuilder text = new StringBuilder();
        text.append(firstname + " " +lastname);
        text.append(" - " + date_of_birth.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        text.append(" - " + city);
        return text.toString();
    }
}
