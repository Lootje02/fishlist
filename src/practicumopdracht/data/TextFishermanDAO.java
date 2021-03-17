package practicumopdracht.data;

import practicumopdracht.models.Fisherman;

import java.io.File;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Scanner;

/**
 * This method <description of function>
 *
 * @author Lorenzo Bindemann
 */
public class TextFishermanDAO extends FishermanDAO {
    private static final String FILENAME = "src/practicumopdracht/textfiles/fisherman.txt";

    @Override
    public boolean save() {
        try {
            File file = new File(FILENAME);
            PrintWriter printWriter = new PrintWriter(file);
//            // set the total objects in the file
//            printWriter.println(objects.size());

            // loop through objects to set for each item the field in the file
            for (Fisherman fisherman : objects) {
                printWriter.println(fisherman.getFirstname());
                printWriter.println(fisherman.getLastname());
                printWriter.println(fisherman.getDate_of_birth());
                printWriter.println(fisherman.getCity());
            }
            // close the printerWrite !Important
            printWriter.close();
        } catch (Exception ex) {
            System.out.println(ex);
            return false;
        }
        return true;
    }

    @Override
    public boolean load() {
        File file = new File(FILENAME);
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                // read the lines from the txt file
                String firstname = scanner.nextLine();
                String lastname = scanner.nextLine();
                LocalDate dateOfBirth = LocalDate.parse(scanner.nextLine());
                String city = scanner.nextLine();

                // create new fisherman object
                Fisherman fisherman = new Fisherman(
                        firstname,
                        lastname,
                        dateOfBirth,
                        city
                );
                // add the fisherman to the list
                addOrUpdate(fisherman);
            }
        } catch (Exception ex) {
            System.out.println(ex);
            return false;
        }
        return true;
    }
}
