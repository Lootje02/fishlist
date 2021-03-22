package practicumopdracht.comparators;

import practicumopdracht.models.Fish;

import java.util.Comparator;

/**
 * This method is the comparator for the fish to sort on the length
 *
 * @author Lorenzo Bindemann
 */
public class LengthComparator implements Comparator<Fish> {
    private boolean highToLow;

    /**
     * default constructor
     *
     * @param highToLow
     */
    public LengthComparator(boolean highToLow) {
        this.highToLow = highToLow;
    }

    /**
     * function to sort the fishList based on length
     *
     * @param o1
     * @param o2
     * @return
     */
    @Override
    public int compare(Fish o1, Fish o2) {
        // sort on length from high to low or low to high depends on the boolean
        return highToLow
                ? -Integer.compare(o1.getFishLengthInCm(), o2.getFishLengthInCm())
                : Integer.compare(o1.getFishLengthInCm(), o2.getFishLengthInCm());
    }
}
