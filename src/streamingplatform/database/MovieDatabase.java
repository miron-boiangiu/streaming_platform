package streamingplatform.database;

import lombok.Getter;
import streamingplatform.movie.Movie;
import streamingplatform.user.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class MovieDatabase extends Database<Movie>{
    @Getter
    private final HashMap<String, HashSet<User>> userSubscriptions = new HashMap<>();

    @Override
    public void addEntry(Movie newEntry) {
        super.addEntry(newEntry);
        ArrayList<User> notifiedUsers = new ArrayList<>();

        for(String genre: newEntry.getGenres()){
            if(!userSubscriptions.containsKey(genre)){
                continue;
            }
            for(User subscribedUser: userSubscriptions.get(genre)){
                if(!notifiedUsers.contains(subscribedUser)){
                    notifiedUsers.add(subscribedUser);
                    subscribedUser.updateAboutNewMovie(newEntry);
                }
            }
        }

    }

    public boolean addSubscriber(final User user, final String genre) {
        if (!userSubscriptions.containsKey(genre)) {
            userSubscriptions.put(genre, new HashSet<>());
        }
        return userSubscriptions.get(genre).add(user);
    }
}
