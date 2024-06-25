import com.app.util.LinkValidator;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class ValidationLinkTest {
    @Test
    public void isInvalidLinkFirstPart() {
        Assertions.assertFalse(LinkValidator.isValidLink("htts://music.yandex.ru/album/4712278/track/37232253?utm_medium=copy_link") &&
                LinkValidator.isValidLink("//musicyandex.ru/album/4712278/track/37232253?utm_medium=copy_link") &&
                LinkValidator.isValidLink("/musicyandex.ru/album/4712278/track/37232253?utm_medium=copy_link") &&
                LinkValidator.isValidLink(" ") && LinkValidator.isValidLink(""));
    }

    @Test
    public void isInvalidLinkSecondPart() {
        Assertions.assertFalse(LinkValidator.isValidLink("https://musicyandex.ru/album/4712278/track/37232253?utm_medium=copy_link") &&
                LinkValidator.isValidLink("https://album/4712278/track/37232253?utm_medium=copy_link") &&
                LinkValidator.isValidLink("https:///album/4712278/track/37232253?utm_medium=copy_link") &&
                LinkValidator.isValidLink("https://musiс.yandex.ru/album/4712278/track/37232253?utm_medium=copy_link"));
    }

    @Test
    public void isInvalidLinkThirdPart() {
        Assertions.assertFalse(LinkValidator.isValidLink("https://music.yandex.ru//4712278/track/37232253?utm_medium=copy_link") &&
                LinkValidator.isValidLink("https://musicyandex.ru/4712278/track/37232253?utm_medium=copy_link") &&
                LinkValidator.isValidLink("https://musicyandex.ru/albu/4712278/track/37232253?utm_medium=copy_link"));
    }

    @Test
    public void isInvalidLinkFifthPart() {
        Assertions.assertFalse(LinkValidator.isValidLink("https://music.yandex.ru/album/4712278/trac/37232253?utm_medium=copy_link") &&
                LinkValidator.isValidLink("https://musicyandex.ru/album/4712278//37232253?utm_medium=copy_link") &&
                LinkValidator.isValidLink("https://musicyandex.ru/album/4712278/37232253?utm_medium=copy_link"));
    }

    @Test
    public void isValidLinkFirstPart() {
        Assertions.assertTrue(LinkValidator.isValidLink("https://music.yandex.ru/album/4712278/track/37232253?utm_medium=copy_link") &&
                LinkValidator.isValidLink("https://music.apple.com/us/album/патрон/1560672735?i=1560672743") &&
                LinkValidator.isValidLink("https://www.youtube.com/watch?v=dOaWwwBp-Ps"));
    }
}
