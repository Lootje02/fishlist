package practicumopdracht.comparators;

import practicumopdracht.models.Fish;

import java.util.Comparator;
import java.util.Locale;

/**
 * This method is the fish sorting on length
 *
 * @author Lorenzo Bindemann
 */
public class SpeciesComparator implements Comparator<Fish> {
    private boolean sortZA;

    /**
     * default constructor
     * @param sortZA
     */
    public SpeciesComparator(boolean sortZA) {
        this.sortZA = sortZA;
    }


    /**
     * function to compare the fish species
     * @param o1
     * @param o2
     * @return
     */
    @Override
    public int compare(Fish o1, Fish o2) {
        // get int of result
        // using toLowercase because highercase has a priority above lowercase
        int compareFishSpecies = o1.getFishSpecies().toLowerCase().compareTo(
                o2.getFishSpecies().toLowerCase()
        );
        final int MIN_RESULT = 0;
        if (compareFishSpecies == MIN_RESULT) {
            return o1.getBait().toLowerCase().compareTo(o2.getFishSpecies().toLowerCase());
        }
        if (sortZA) {
            return -compareFishSpecies;
        }
        return compareFishSpecies;
    }
}
