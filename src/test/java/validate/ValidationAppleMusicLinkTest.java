package validate;

import com.app.services.implementations.validators.LinkValidator;
import com.app.services.interfaces.validators.Validator;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class ValidationAppleMusicLinkTest {
    private final Validator validator = new LinkValidator();

    @Test
    public void isValidAppleMusicLink() {
        final String validSpotifyLink = "https://music.apple.com/ru/album/патрон/1560672735?i=1560672743";
        Assertions.assertTrue(validator.isValid(validSpotifyLink));
    }

    @Test
    public void isInvalidAppleMusicLink() {
        Assertions.assertFalse(validator.isValid("http://music.apple.com/ru/album/патрон/1560672735?i=1560672743") &&
                validator.isValid("https:music.apple.com/ru/album/патрон/1560672735?i=1560672743") &&
                validator.isValid("https://music.apple.com/ru//патрон/1560672735?i=1560672743") &&
                validator.isValid("https:/open.spotify.comtrack/3uCth4TyeQDnj3YbAVQB") &&
                validator.isValid("https://music.apple.com/ru/albm/патрон/1560672735?i=1560672743") &&
                validator.isValid("/привет это просто тест/"));
    }
}
