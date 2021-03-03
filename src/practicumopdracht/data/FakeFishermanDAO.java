package practicumopdracht.data;

import practicumopdracht.models.Fisherman;

import java.time.LocalDate;

/**
 * This method <description of function>
 *
 * @author Lorenzo Bindemann
 */
public class FakeFishermanDAO extends FishermanDAO{
    @Override
    public boolean save() {
        Fisherman fisherman1 = new Fisherman("Lorenzo1", "Bindemann", LocalDate.now(), "Uithoorn");
        super.addOrUpdate(fisherman1);
        return true;
    }

    @Override
    public boolean load() {
        Fisherman fisherman1 = new Fisherman("Lorenzo", "Bindemann", LocalDate.now(), "Uithoorn");
        Fisherman fisherman2 = new Fisherman("Bart", "Bindemann", LocalDate.now(), "Uithoorn");
        Fisherman fisherman3 = new Fisherman("Joost", "Bindemann", LocalDate.now(), "Uithoorn");
        super.addOrUpdate(fisherman1);
        super.addOrUpdate(fisherman2);
        super.addOrUpdate(fisherman3);
        fisherman1.setId(0);
        fisherman2.setId(1);
        fisherman3.setId(2);
        return true;
    }
}
