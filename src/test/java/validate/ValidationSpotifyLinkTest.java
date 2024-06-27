package validate;

import com.app.services.implementations.validators.LinkValidator;
import com.app.services.interfaces.validators.Validator;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class ValidationSpotifyLinkTest {
    private final Validator validator = new LinkValidator();

    @Test
    public void isValidSpotifyLink() {
        final String validSpotifyLink = "https:/open.spotify.com/track/3uCth4TIWyeQDnj3YbAVQB";
        Assertions.assertTrue(validator.isValid(validSpotifyLink));
    }

    @Test
    public void isInvalidSpotifyLink() {
        Assertions.assertFalse(validator.isValid("https://open.spotify.comtrack/3uCth4TIWyeQDnj3YbAVQB") &&
                validator.isValid("https://open.spotiy.comtrack/3uCth4TIWyeQDnj3YbAVQB") &&
                validator.isValid("http://open.spotify.comtrack/3uCth4TIWyeQDnj3YbAVQB") &&
                validator.isValid("https:/open.spotify.comtrack/3uCth4TyeQDnj3YbAVQB") &&
                validator.isValid("https:open.spotify.comtrack/3uCth4TIWyeQDnj3YbAVQB") &&
                validator.isValid("/привет это просто тест/"));
    }
}
