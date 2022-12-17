package streamingplatform;

public final class StreamingPlatformConstants {
    // User attributes
    public static final String PREMIUM_USER_ATTRIBUTE = "premium";
    public static final String STANDARD_USER_ATTRIBUTE = "standard";

    // Actions
    public static final String BUY_PREMIUM_ACTION = "buy premium account";
    public static final String BUY_TOKENS_ACTION = "buy tokens";
    public static final String REGISTER_ACTION = "register";
    public static final String SEARCH_ACTION = "search";
    public static final String FILTER_ACTION = "filter";
    public static final String LIKE_ACTION = "like";
    public static final String RATE_ACTION = "rate";
    public static final String PURCHASE_ACTION = "purchase";
    public static final String WATCH_ACTION = "watch";
    public static final String LOGIN_ACTION = "login";
    public static final String CHANGE_PAGE_ACTION_TYPE = "change page";
    public static final String ON_PAGE_ACTION_TYPE = "on page";

    // Pages
    public static final String SEE_DETAILS_PAGE = "see details";
    public static final String MOVIES_PAGE = "movies";
    public static final String LOGOUT_PAGE = "logout";
    public static final String UNAUTHENTICATED_HOMEPAGE_PAGE = "unauthhome";
    public static final String LOGIN_PAGE = "login";
    public static final String AUTHENTICATED_HOMEPAGE_PAGE = "authhome";
    public static final String UPGRADES_PAGE = "upgrades";
    public static final String REGISTER_PAGE = "register";

    // Shop
    public static final Integer INITIAL_FREE_MOVIES_COUNT = 15;
    public static final Integer PREMIUM_ACCOUNT_PRICE = 10;
    public static final Integer PURCHASE_MOVIE_PRICE = 2;
    public static final Integer PURCHASE_MOVIE_FREE_TOKEN_PRICE = 1;

    // Filter options
    public static final String INCREASING_FILTER_OPTION = "increasing";
    public static final String DECREASING_FILTER_OPTION = "decreasing";

    // Rate options
    public static final Integer MAXIMUM_RATING_VALUE = 5;
    public static final Integer MINIMUM_RATING_VALUE = 0;

    // Output
    // Errors output
    public static final String CURRENT_USER_PROPERTY_NAME = "currentUser";
    public static final String ERROR_PROPERTY_NAME = "error";
    public static final String ERROR_PROPERTY_VALUE = "Error";
    // Movies output
    public static final String CURRENT_MOVIES_LIST_PROPERTY_NAME = "currentMoviesList";
    public static final String MOVIE_NAME_PROPERTY_NAME = "name";
    public static final String YEAR_PROPERTY_NAME = "year";
    public static final String DURATION_PROPERTY_NAME = "duration";
    public static final String GENRES_PROPERTY_NAME = "genres";
    public static final String ACTORS_PROPERTY_NAME = "actors";
    public static final String BANNED_COUNTRIES_PROPERTY_NAME = "countriesBanned";
    public static final String NUMBER_OF_LIKES_PROPERTY_NAME = "numLikes";
    public static final String MOVIE_RATING_PROPERTY_NAME = "rating";
    public static final String NUMBER_OF_RATINGS_PROPERTY_NAME = "numRatings";

    // Users output
    public static final String CREDENTIALS_PROPERTY_NAME = "credentials";
    public static final String USERNAME_PROPERTY_NAME = "name";
    public static final String PASSWORD_PROPERTY_NAME = "password";
    public static final String ACCOUNT_TYPE_PROPERTY_NAME = "accountType";
    public static final String COUNTRY_PROPERTY_NAME = "country";
    public static final String BALANCE_PROPERTY_NAME = "balance";
    public static final String TOKENS_COUNT_PROPERTY_NAME = "tokensCount";
    public static final String FREE_PREMIUM_MOVIES_COUNT_PROPERTY_NAME = "numFreePremiumMovies";
    public static final String PURCHASED_MOVIES_PROPERTY_NAME = "purchasedMovies";
    public static final String WATCHED_MOVIES_PROPERTY_NAME = "watchedMovies";
    public static final String LIKED_MOVIES_PROPERTY_NAME = "likedMovies";
    public static final String RATED_MOVIES_PROPERTY_NAME = "ratedMovies";

    private StreamingPlatformConstants() {

    }
}
