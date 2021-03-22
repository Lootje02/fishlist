package practicumopdracht.data;

import practicumopdracht.models.Fisherman;

import java.time.LocalDate;

/**
 * This method is a DAO for fake details for fisherman
 *
 * @author Lorenzo Bindemann
 */
public class FakeFishermanDAO extends FishermanDAO {
    /**
     * save function for fake fish save
     *
     * @return
     */
    @Override
    public boolean save() {
        Fisherman fisherman1 = new Fisherman("Lorenzo1", "Bindemann", LocalDate.now(), "Uithoorn");
        super.addOrUpdate(fisherman1);
        return true;
    }

    /**
     * function for load fake details
     *
     * @return
     */
    @Override
    public boolean load() {
        Fisherman fisherman1 = new Fisherman("Lorenzo", "Bindemann", LocalDate.now(), "Uithoorn");
        Fisherman fisherman2 = new Fisherman("Bart", "Bindemann", LocalDate.now(), "Uithoorn");
        Fisherman fisherman3 = new Fisherman("Joost", "Bindemann", LocalDate.now(), "Uithoorn");
        super.addOrUpdate(fisherman1);
        super.addOrUpdate(fisherman2);
        super.addOrUpdate(fisherman3);
        return true;
    }
}
