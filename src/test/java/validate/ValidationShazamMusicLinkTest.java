package validate;

import com.app.services.implementations.validators.LinkValidator;
import com.app.services.interfaces.validators.Validator;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class ValidationShazamMusicLinkTest {
    private final Validator validator = new LinkValidator();

    @Test
    public void isValidShazamMusicLink() {
        final String validSpotifyLink = "https://www.shazam.com/ru-ru/song/1560672743/патрон";
        Assertions.assertTrue(validator.isValid(validSpotifyLink));
    }

    @Test
    public void isInvalidShazamMusicLink() {
        Assertions.assertFalse(validator.isValid("http://www.shazam.com/ru-ru/song/1560672743/патрон") &&
                validator.isValid("https:www.shazam.com/ru-ru/song/1560672743/патрон") &&
                validator.isValid("https://www.shazam.com/song/1560672743/патрон") &&
                validator.isValid("https://www.shm.com/ru-ru/song/1560672743/патрон") &&
                validator.isValid("https:open.spotify.comtrack/3uCth4TIWyeQDnj3YbAVQB") &&
                validator.isValid("/привет это просто тест/"));
    }
}
