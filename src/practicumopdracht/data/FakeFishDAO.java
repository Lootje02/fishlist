package practicumopdracht.data;

import practicumopdracht.models.Fish;

import java.time.LocalDate;

/**
 * This method <description of function>
 *
 * @author Lorenzo Bindemann
 */
public class FakeFishDAO extends FishDAO{

    public boolean save() {
        return false;
    }


    public boolean load() {
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
