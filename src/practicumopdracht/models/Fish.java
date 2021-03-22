package practicumopdracht.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * This method is a fish and describes everything about the catch and the fish
 *
 * @author Lorenzo Bindemann
 */
public class Fish implements Serializable {
    private transient Fisherman hoortBij;
    private String fishSpecies;
    private int fishLengthInCm;
    private double weightInKg;
    private LocalDate caughtOn;
    private String waterType;
    private String location;
    private String bait;
    private boolean prefeed;
    private boolean gotOnTheSide;
    private String remark;

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
        Fisherman hoortBij,
        int fishLengthInCm,
        double weightInKg,
        LocalDate caughtOn,
        String waterType,
        String location,
        String bait,
        boolean prefeed,
        boolean gotOnTheSide,
        String remark
    ) {
        this.fishSpecies = fishSpecies;
        this.hoortBij = hoortBij;
        this.fishLengthInCm = fishLengthInCm;
        this.weightInKg = weightInKg;
        this.caughtOn = caughtOn;
        this.location = location;
        this.waterType = waterType;
        this.bait = bait;
        this.prefeed = prefeed;
        this.gotOnTheSide = gotOnTheSide;
        this.remark = remark;
    }

    /**
     * getter for the fish species
     * @return
     */
    public String getFishSpecies() {
        return fishSpecies;
    }

    /**
     * setter for the hoortBij
     * @param hoortBij
     */
    public void setHoortBij(Fisherman hoortBij) {
        this.hoortBij = hoortBij;
    }

    /**
     * getter for the fish length
     * @return
     */
    public int getFishLengthInCm() {
        return fishLengthInCm;
    }

    /**
     * getter for the fish weight
     * @return
     */
    public double getWeightInKg() {
        return weightInKg;
    }

    /**
     * getter for the caught on date
     * @return
     */
    public LocalDate getCaughtOn() {
        return caughtOn;
    }

    /**
     * getter the waterType
     * @return
     */
    public String getWaterType() {
        return waterType;
    }

    /**
     * getter for the location
     * @return
     */
    public String getLocation() {
        return location;
    }

    /**
     * getter for the bait
     * @return
     */
    public String getBait() {
        return bait;
    }

    /**
     * getter for the prefeed boolean
     * @return
     */
    public boolean isPrefeed() {
        return prefeed;
    }

    /**
     * getter for the gotOnSide boolean
     * @return
     */
    public boolean isGotOnTheSide() {
        return gotOnTheSide;
    }

    /**
     * getter for the remark
     * @return
     */
    public String getRemark() {
        return remark;
    }

    /**
     * getter for the hoortBij
     * @return
     */
    public Fisherman getHoortBij() {
        return hoortBij;
    }

    /**
     * @Override function for the fish toString
     * @return
     */
    @Override
    public String toString() {
        StringBuilder text = new StringBuilder();
        final String YES = "ja";
        final String NO = "nee";
        text.append(fishSpecies);
        text.append("\n - vislengte in cm: " + fishLengthInCm);
        text.append("\n - gewicht in kg: " + weightInKg);
        text.append("\n - gevangen op: " + caughtOn.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        text.append("\n - watertype: " + waterType);
        text.append("\n - aas: " + bait);
        text.append("\n - voorgevoerd: " + (prefeed ? YES : NO));
        text.append("\n - op de kant gekregen: " + (gotOnTheSide ? YES : NO));
        text.append("\n - opmerking: " + remark );
        return text.toString();
    }
}
