package validate;

import com.app.services.implementations.validators.LinkValidator;
import com.app.services.interfaces.validators.Validator;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class ValidationYandexMusicLinkTest {
    private final Validator validator = new LinkValidator();

    @Test
    public void isValidYandexMusicSLink() {
        final String validSpotifyLink = "https://music.yandex.ru/album/4712278/track/37232256";
        Assertions.assertTrue(validator.isValid(validSpotifyLink));
    }

    @Test
    public void isInvalidYandexMusicLink() {
        Assertions.assertFalse(validator.isValid("https:music.yandex.ru/album/4712278/track/37232256") &&
                validator.isValid("https://music.yandex.ru/lbum/4712278/track/37232256") &&
                validator.isValid("htt://music.yandex.ru/album/4712278/track/37232256") &&
                validator.isValid("https:/open.spotify.comtrack/3uCth4TyeQDnj3YbAVQB") &&
                validator.isValid("https://music.yandex.ru/album/4712278//37232256") &&
                validator.isValid("/привет это просто тест/"));
    }
}
