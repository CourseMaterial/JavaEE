package be.rubus.security.workshop.oauth2;

public final class Constants {

    // These values should be stored outside the application and read as part from the configuration!
    public static final String CLIENT_ID = "208845979122-dgn7s1umrv2ll15ilg7lvmt81e4651si.apps.googleusercontent.com";
    public static final String SECRET_ID = "UHErnUvaQEcKVYYqVBdjutrD";

    public static final String CALLBACK_SERVLET_PATH = "/oauth2callback";

    // Session keys
    public static final String USER_TOKEN = "user_OAuth2_token";
    public static final String ORIGINAL_URL = "orginalURL";
    public static final String CSRF_TOKEN = "csrf_token";


    private Constants() {
        throw new IllegalStateException("Unable to instantiate");
    }
}
