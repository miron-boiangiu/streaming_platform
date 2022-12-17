package streamingplatform.database;

import lombok.Getter;

import java.util.ArrayList;

public class Database<T> {
    @Getter
    private final ArrayList<T> entries = new ArrayList<T>();

    public void addEntry(final T newEntry) {
        entries.add(newEntry);
    }
}
