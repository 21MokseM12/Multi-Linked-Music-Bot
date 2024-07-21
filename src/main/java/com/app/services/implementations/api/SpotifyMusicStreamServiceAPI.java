package com.app.services.implementations.api;

import com.app.enums.StreamServiceType;
import com.app.exceptions.TrackNotFoundException;
import com.app.services.interfaces.api.StreamServiceAPI;
import com.app.utils.PropertiesManager;
import org.apache.hc.core5.http.ParseException;
import org.springframework.stereotype.Service;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.ArtistSimplified;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.model_objects.specification.Track;
import se.michaelthelin.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;

import java.io.IOException;

//https://open.spotify.com/track/3uCth4TIWyeQDnj3YbAVQB
@Service
public class SpotifyMusicStreamServiceAPI implements StreamServiceAPI {
    private static final String CLIENT_ID = "spotify.client.id";
    private static final String CLIENT_SECRET = "spotify.client.secret";

    private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
            .setClientId(PropertiesManager.getPropertyByKey(CLIENT_ID))
            .setClientSecret(PropertiesManager.getPropertyByKey(CLIENT_SECRET))
            .build();
    private static final ClientCredentialsRequest clientCredentialsRequest = spotifyApi.clientCredentials()
            .build();

    @Override
    public String getLinkByFullTrackName(String trackName, String artistName) throws TrackNotFoundException{
        try {
            String accessToken = clientCredentialsRequest.execute().getAccessToken();
            spotifyApi.setAccessToken(accessToken);
            Paging<Track> tracks = spotifyApi.searchTracks(trackName).build().execute();

            for (Track track : tracks.getItems())
                if (buildFullArtistName(track.getArtists()).equals(artistName)) return buildSpotifyLink(track.getId());

            throw new TrackNotFoundException();
        } catch (SpotifyWebApiException | IOException | ParseException e) {
            throw new TrackNotFoundException(e);
        }
    }

    @Override
    public String getTrackName(String link) throws TrackNotFoundException{
        try {
            String accessToken = clientCredentialsRequest.execute().getAccessToken();
            spotifyApi.setAccessToken(accessToken);
            Track track = spotifyApi
                    .getTrack(getTrackId(link))
                    .build()
                    .execute();
            String trackName = track.getName();

            if (trackName.isEmpty()) throw new TrackNotFoundException();
            else return trackName;
        } catch (IOException | SpotifyWebApiException | ParseException | NullPointerException | TrackNotFoundException e) {
            throw new TrackNotFoundException(e);
        }
    }

    @Override
    public String getArtistName(String link) throws TrackNotFoundException {
        try {
            String accessToken = clientCredentialsRequest.execute().getAccessToken();
            spotifyApi.setAccessToken(accessToken);
            Track track = spotifyApi
                    .getTrack(getTrackId(link))
                    .build()
                    .execute();
            ArtistSimplified[] artists = track.getArtists();

            if (artists.length == 0) throw new TrackNotFoundException();
            else return buildFullArtistName(artists);

        } catch (IOException | SpotifyWebApiException | ParseException | NullPointerException | TrackNotFoundException e) {
            throw new TrackNotFoundException(e);
        }
    }

    @Override
    public StreamServiceType getServiceType(){
        return StreamServiceType.SPOTIFY_MUSIC_LINK;
    }

    public String getTrackId(String link) {
        return link.replace("//", "/")
                .replace('?', '/')
                .split("/")[3];
    }

    private String buildFullArtistName(ArtistSimplified[] artists) {
        StringBuilder res = new StringBuilder();
        for (ArtistSimplified artist : artists) res.append(artist.getName()).append(", ");
        return res.delete(res.length()-2, res.length()).toString().trim();
    }

    private String buildSpotifyLink(String id) {
        return "https://open.spotify.com/track/".concat(id);
    }
}
