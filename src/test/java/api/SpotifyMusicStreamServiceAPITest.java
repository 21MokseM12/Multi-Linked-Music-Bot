package api;

import com.app.exceptions.StreamServiceAPINotFoundException;
import com.app.exceptions.TrackNotFoundException;
import com.app.services.implementations.api.SpotifyMusicStreamServiceAPI;
import com.app.services.interfaces.api.StreamServiceAPI;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class SpotifyMusicStreamServiceAPITest {

    private StreamServiceAPI spotify = new SpotifyMusicStreamServiceAPI();

    @Test
    public void getTrackNameBySpotifyLink() throws StreamServiceAPINotFoundException, TrackNotFoundException {
        Assertions.assertEquals("Патрон", spotify.getTrackName("https://open.spotify.com/track/3uCth4TIWyeQDnj3YbAVQB"));
    }

    @Test
    public void getArtistNameBySpotifyLink() throws StreamServiceAPINotFoundException, TrackNotFoundException {
        Assertions.assertEquals("Miyagi & Andy Panda", spotify.getArtistName("https://open.spotify.com/track/3uCth4TIWyeQDnj3YbAVQB"));
    }

    @Test
    public void getTrackId() {
        SpotifyMusicStreamServiceAPI spotify = new SpotifyMusicStreamServiceAPI();
        Assertions.assertEquals("3uCth4TIWyeQDnj3YbAVQB", spotify.getTrackId("https://open.spotify.com/track/3uCth4TIWyeQDnj3YbAVQB?=gfkfkfkkfkf"));
    }

    @Test
    public void getValidLink() throws TrackNotFoundException {
        SpotifyMusicStreamServiceAPI spotify = new SpotifyMusicStreamServiceAPI();
        String link = "https://open.spotify.com/track/3uCth4TIWyeQDnj3YbAVQB";
        Assertions.assertEquals(link, spotify.getLinkByFullTrackName(spotify.getTrackName(link), spotify.getArtistName(link)));
    }

    @Test
    public void getInvalidLink() throws TrackNotFoundException {
        SpotifyMusicStreamServiceAPI spotify = new SpotifyMusicStreamServiceAPI();
        String link = "https://open.spotify.com/track/0x60P5taxdI5pcGbqbap6S";
        Assertions.assertNotEquals("0x60P5taxdI5pcGbqbap6S", spotify.getLinkByFullTrackName(spotify.getTrackName(link), spotify.getArtistName(link)));
    }
}
