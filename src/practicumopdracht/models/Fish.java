package practicumopdracht.models;

import java.time.LocalDate;

/**
 * This method is a fish and describes everything about the catch and the fish
 *
 * @author Lorenzo Bindemann
 */
public class Fish extends FishList{
    private String fishSpecies;
    private int fishLengthInCm;
    private double weightInKg;
    private LocalDate caughtOn;
    private String location;
    private String bait;
    private boolean prefeed;
    private boolean gotOnTheSide;
}
