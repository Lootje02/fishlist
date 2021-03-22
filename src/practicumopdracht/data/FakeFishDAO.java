package practicumopdracht.data;

import practicumopdracht.models.Fish;

import java.time.LocalDate;

/**
 * This method is a DAO with fake details for fish
 *
 * @author Lorenzo Bindemann
 */
public class FakeFishDAO extends FishDAO{

    /**
     * save function for fake fish save
     * @return
     */
    public boolean save() {
        return false;
    }


    /**
     * function for load fake details
     *
     * @return
     */
    public boolean load() {
        // commented out because of new structure issues
//        Fish fish1 = new Fish(
//                "Karper",
//                1,
//                2,
//                2,
//                LocalDate.now(),
//                "Zoet water",
//                "Uithoorn",
//                "Mais",
//                true,
//                true,
//                "niks"
//        );
//        Fish fish2 = new Fish(
//                "Karper",
//                1,
//                2,
//                2,
//                LocalDate.now(),
//                "Zoet water",
//                "Uithoorn",
//                "Mais",
//                true,
//                true,
//                "niks"
//        );
//        super.addOrUpdate(fish1);
//        super.addOrUpdate(fish2);
        return true;
    }
}
