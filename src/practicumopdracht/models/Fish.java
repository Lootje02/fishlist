package practicumopdracht.models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * This method is a fish and describes everything about the catch and the fish
 *
 * @author Lorenzo Bindemann
 */
public class Fish{
    private Fisherman hoortBij;
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

    public String getFishSpecies() {
        return fishSpecies;
    }

    public void setHoortBij(Fisherman hoortBij) {
        this.hoortBij = hoortBij;
    }

    public void setFishSpecies(String fishSpecies) {
        this.fishSpecies = fishSpecies;
    }

    public int getFishLengthInCm() {
        return fishLengthInCm;
    }

    public void setFishLengthInCm(int fishLengthInCm) {
        this.fishLengthInCm = fishLengthInCm;
    }

    public double getWeightInKg() {
        return weightInKg;
    }

    public void setWeightInKg(double weightInKg) {
        this.weightInKg = weightInKg;
    }

    public LocalDate getCaughtOn() {
        return caughtOn;
    }

    public void setCaughtOn(LocalDate caughtOn) {
        this.caughtOn = caughtOn;
    }

    public String getWaterType() {
        return waterType;
    }

    public void setWaterType(String waterType) {
        this.waterType = waterType;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBait() {
        return bait;
    }

    public void setBait(String bait) {
        this.bait = bait;
    }

    public boolean isPrefeed() {
        return prefeed;
    }

    public void setPrefeed(boolean prefeed) {
        this.prefeed = prefeed;
    }

    public boolean isGotOnTheSide() {
        return gotOnTheSide;
    }

    public void setGotOnTheSide(boolean gotOnTheSide) {
        this.gotOnTheSide = gotOnTheSide;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Fisherman getHoortBij() {
        return hoortBij;
    }

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
