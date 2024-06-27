package validate;

import com.app.services.implementations.validators.LinkValidator;
import com.app.services.interfaces.validators.Validator;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class ValidationSoundCloudMusicLinkTest {
    private final Validator validator = new LinkValidator();

    @Test
    public void isValidSoundCloudMusicMusicLink() {
        final String validSpotifyLink = "https://soundcloud.com/miyagi_black/minor";
        Assertions.assertTrue(validator.isValid(validSpotifyLink));
    }

    @Test
    public void isInvalidSoundCloudMusicLink() {
        Assertions.assertFalse(validator.isValid("http://soundcloud.com/miyagi_black/minor") &&
                validator.isValid("https:soundcloud.com/miyagi_black/minor") &&
                validator.isValid("https://soundcloud.c/miyagi_black/minor") &&
                validator.isValid("https://miyagi_black/minor") &&
                validator.isValid("https:open.spotify.comtrack/3uCth4TIWyeQDnj3YbAVQB") &&
                validator.isValid("/привет это просто тест/"));
    }
}
