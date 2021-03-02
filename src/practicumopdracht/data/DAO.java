package practicumopdracht.data;

import java.util.List;

public interface DAO<T> {
    List<T> getAll();

    T getById(int id);

    void addOrUpdate(T object);

    void remove(T object);

    boolean save();

    boolean load();
}
