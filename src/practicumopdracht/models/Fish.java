package practicumopdracht.models;

import java.time.LocalDate;

/**
 * This method is a fish and describes everything about the catch and the fish
 *
 * @author Lorenzo Bindemann
 */
public class Fish{
    private String fishSpecies;
    private int fishLengthInCm;
    private double weightInKg;
    private LocalDate caughtOn;
    private String location;
    private String bait;
    private boolean prefeed;
    private boolean gotOnTheSide;

    /**
     * constructor
     * @param fishSpecies
     * @param fishLengthInCm
     * @param weightInKg
     * @param caughtOn
     * @param location
     * @param bait
     * @param prefeed
     * @param gotOnTheSide
     */
    public Fish(
        String fishSpecies,
        int fishLengthInCm,
        double weightInKg,
        LocalDate caughtOn,
        String location,
        String bait,
        boolean prefeed,
        boolean gotOnTheSide
    ) {
        this.fishSpecies = fishSpecies;
        this.fishLengthInCm = fishLengthInCm;
        this.weightInKg = weightInKg;
        this.caughtOn = caughtOn;
        this.location = location;
        this.bait = bait;
        this.prefeed = prefeed;
        this.gotOnTheSide = gotOnTheSide;
    }
}
