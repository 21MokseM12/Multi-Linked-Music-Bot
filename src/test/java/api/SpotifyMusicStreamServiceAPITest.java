package api;

import com.app.enums.StreamServiceType;
import com.app.exceptions.InvalidStreamServiceAPIException;
import com.app.exceptions.TrackNotFoundException;
import com.app.factories.StreamServiceAPIFactory;
import com.app.services.implementations.api.SpotifyMusicStreamServiceAPI;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class SpotifyMusicStreamServiceAPITest {
    @Test
    public void getTrackNameBySpotifyLink() throws InvalidStreamServiceAPIException, TrackNotFoundException {
        Assertions.assertEquals("Патрон", StreamServiceAPIFactory
                .get(StreamServiceType.SPOTIFY_MUSIC_LINK)
                .getTrackName("https://open.spotify.com/track/3uCth4TIWyeQDnj3YbAVQB"));
    }

    @Test
    public void getArtistNameBySpotifyLink() throws InvalidStreamServiceAPIException, TrackNotFoundException {
        Assertions.assertEquals("Miyagi & Andy Panda", StreamServiceAPIFactory
                .get(StreamServiceType.SPOTIFY_MUSIC_LINK)
                .getArtistName("https://open.spotify.com/track/3uCth4TIWyeQDnj3YbAVQB"));
    }

    @Test
    public void getTrackId() {
        SpotifyMusicStreamServiceAPI spotify = new SpotifyMusicStreamServiceAPI();
        Assertions.assertEquals("3uCth4TIWyeQDnj3YbAVQB", spotify.getTrackId("https://open.spotify.com/track/3uCth4TIWyeQDnj3YbAVQB?=gfkfkfkkfkf"));
    }
}
