package practicumopdracht.comparators;

import practicumopdracht.models.Fish;

import java.util.Comparator;

/**
 * This method <description of function>
 *
 * @author Lorenzo Bindemann
 */
public class LengthComparator implements Comparator<Fish> {
    private boolean highToLow;

    public LengthComparator(boolean highToLow) {
        this.highToLow = highToLow;
    }

    /**
     * function to sort the fishList based on length
     * @param o1
     * @param o2
     * @return
     */
    @Override
    public int compare(Fish o1, Fish o2) {
        return highToLow
                ? -Integer.compare(o1.getFishLengthInCm(), o2.getFishLengthInCm())
                : Integer.compare(o1.getFishLengthInCm(), o2.getFishLengthInCm());
    }
}
