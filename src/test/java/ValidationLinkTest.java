import com.app.services.implementations.validators.LinkValidator;
import com.app.services.interfaces.validators.Validator;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class ValidationLinkTest {
    private Validator validator = new LinkValidator();

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
