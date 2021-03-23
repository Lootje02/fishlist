package practicumopdracht.data;

import practicumopdracht.models.Fisherman;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.file.FileAlreadyExistsException;
import java.time.LocalDate;
import java.util.Scanner;

/**
 * This method is the DAO for saving fishermans to text file
 *
 * @author Lorenzo Bindemann
 */
public class TextFishermanDAO extends FishermanDAO {
    private static final String FILENAME = "src/practicumopdracht/textfiles/fisherman.txt";

    /**
     * save function to save a fisherman list to a text file
     *
     * @return
     */
    @Override
    public boolean save() {
        try {
            File file = new File(FILENAME);
            PrintWriter printWriter = new PrintWriter(file);

            // loop through objects to set for each item the field in the file
            for (Fisherman fisherman : objects) {
                printWriter.println(fisherman.getFirstname());
                printWriter.println(fisherman.getLastname());
                printWriter.println(fisherman.getDate_of_birth());
                printWriter.println(fisherman.getCity());
            }
            // close the printerWrite !Important
            printWriter.close();
        } catch(FileNotFoundException ex) {
            // file not found
            System.out.println("Bestand niet gevonden: " + ex);
            return false;
        } catch(Exception ex) {
            // other errors
            System.err.print(ex);
            return false;
        }
        return true;
    }

    /**
     * load function to load all the fishermans from the text file
     *
     * @return
     */
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
        } catch(FileNotFoundException ex) {
            // file not found
            System.out.println("Bestand niet gevonden: " + ex);
            return false;
        } catch(Exception ex) {
            // other errors
            System.err.print(ex);
            return false;
        }
        return true;
    }
}
