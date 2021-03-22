package practicumopdracht.data;

import java.util.List;

/**
 * Interface for all the DAO's
 *
 * @param <T>
 */
public interface DAO<T> {
    List<T> getAll();

    /**
     * implements function to get by id
     *
     * @param id
     * @return
     */
    T getById(int id);

    /**
     * function to add item to list or update existing item
     *
     * @param object
     */
    void addOrUpdate(T object);

    /**
     * remove item from list
     *
     * @param object
     */
    void remove(T object);

    /**
     * implements method for saving
     *
     * @return
     */
    boolean save();

    /**
     * implements method for laoding
     *
     * @return
     */
    boolean load();
}
