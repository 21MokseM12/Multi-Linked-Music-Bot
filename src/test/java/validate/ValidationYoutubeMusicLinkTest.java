package validate;

import com.app.services.implementations.validators.LinkValidator;
import com.app.services.interfaces.validators.Validator;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class ValidationYoutubeMusicLinkTest {
    private final Validator validator = new LinkValidator();

    @Test
    public void isValidYouTubeMusicLink() {
        final String validSpotifyLink = "https://music.youtube.com/playlist?list=OLAK5uy_k_3fod9H3jP2IfhHPeqpmLbqPj5FvTiYQ";
        Assertions.assertTrue(validator.isValid(validSpotifyLink));
    }

    @Test
    public void isInvalidYouTubeMusicLink() {
        Assertions.assertFalse(validator.isValid("https:music.youtube.com/playlist?list=OLAK5uy_k_3fod9H3jP2IfhHPeqpmLbqPj5FvTiYQ") &&
                validator.isValid("https://music.youtube/playlist?list=OLAK5uy_k_3fod9H3jP2IfhHPeqpmLbqPj5FvTiYQ") &&
                validator.isValid("http://music.youtube.com/playlist?list=OLAK5uy_k_3fod9H3jP2IfhHPeqpmLbqPj5FvTiYQ") &&
                validator.isValid("https://playlist?list=OLAK5uy_k_3fod9H3jP2IfhHPeqpmLbqPj5FvTiYQ") &&
                validator.isValid("https:open.spotify.comtrack/3uCth4TIWyeQDnj3YbAVQB") &&
                validator.isValid("/привет это просто тест/"));
    }
}
