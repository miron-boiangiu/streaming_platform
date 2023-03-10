package streamingplatform.database;

import lombok.Getter;

import java.util.ArrayList;

public class Database<T> {
    @Getter
    protected final ArrayList<T> entries = new ArrayList<T>();

    /**
     * Adds an entry to the database.
     * @param newEntry The entry to be added, which must be of
     *                 the Class the Database was initialized in.
     */
    public void addEntry(final T newEntry) {
        entries.add(newEntry);
    }
}
