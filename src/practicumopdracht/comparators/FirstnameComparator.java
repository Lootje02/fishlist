package practicumopdracht.comparators;

import practicumopdracht.models.Fisherman;

import java.util.Comparator;

/**
 * This method <description of function>
 *
 * @author Lorenzo Bindemann
 */
public class FirstnameComparator implements Comparator<Fisherman> {
    private boolean sortZA;

    public FirstnameComparator(Boolean sortZA) {
        this.sortZA = sortZA;
    }

    /**
     * function to sort the masterList
     * @param o1
     * @param o2
     * @return
     */
    @Override
    public int compare(Fisherman o1, Fisherman o2) {
        // get int of result
        // using toLowercase because highercase has a priority above lowercase
        int compareFirstname = o1.getFirstname().toLowerCase().compareTo(
                o2.getFirstname().toLowerCase()
        );
        final int MIN_RESULT = 0;
        if (compareFirstname == MIN_RESULT) {
            return o1.getLastname().toLowerCase().compareTo(o2.getLastname().toLowerCase());
        }
        if (sortZA) {
           return -compareFirstname;
        }
        return compareFirstname;
    }

}
