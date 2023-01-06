package streamingplatform.database;

import lombok.Getter;
import streamingplatform.StreamingPlatform;
import streamingplatform.movie.Movie;
import streamingplatform.user.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public final class MovieDatabase extends Database<Movie> {
    @Getter
    private final HashMap<String, HashSet<User>> userSubscriptions = new HashMap<>();

    @Override
    public void addEntry(final Movie newEntry) {
        for (Movie movie: entries) {
            if (movie.getName().equals(newEntry.getName())) {
                StreamingPlatform.getINSTANCE().addErrorOutputNode();
                return;
            }
        }

        super.addEntry(newEntry);
        ArrayList<User> notifiedUsers = new ArrayList<>();

        for (String genre: newEntry.getGenres()) {
            if (!userSubscriptions.containsKey(genre)) {
                continue;
            }
            for (User subscribedUser: userSubscriptions.get(genre)) {
                if (!notifiedUsers.contains(subscribedUser)) {
                    notifiedUsers.add(subscribedUser);
                    subscribedUser.updateAboutNewMovie(newEntry);
                }
            }
        }

    }

    /**
     * Removes a movie from the database and informs all owners of that movie of its removal.
     * @param removedEntryName The name of the movie to be removed.
     */
    public void removeEntry(final String removedEntryName) {
        Movie movieToRemove = null;
        for (Movie entry: entries) {
            if (entry.getName().equals(removedEntryName)) {
                movieToRemove = entry;
                break;
            }
        }

        if (movieToRemove == null) {
            StreamingPlatform.getINSTANCE().addErrorOutputNode();
            return;
        }

        for (User user: movieToRemove.getMovieOwners()) {
            user.updateAboutDeletedMovie(movieToRemove);
        }

        entries.remove(movieToRemove);
    }

    /**
     * Subscribes a user to a genre, so they get notified when a
     * movie of that genre is added to the database.
     * @param user The user to subscribe to the genre
     * @param genre The genre to be subscribed to
     * @return
     */
    public boolean addSubscriber(final User user, final String genre) {
        if (!userSubscriptions.containsKey(genre)) {
            userSubscriptions.put(genre, new HashSet<>());
        }
        return userSubscriptions.get(genre).add(user);
    }
}
