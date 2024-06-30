package com.app.services.implementations.api;

import com.app.enums.StreamServiceType;
import com.app.exceptions.TrackNotFoundException;
import com.app.services.interfaces.api.MusicStreamServiceAPI;
import com.app.utils.PropertiesManager;
import org.apache.hc.core5.http.ParseException;
import org.springframework.stereotype.Service;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.ArtistSimplified;
import se.michaelthelin.spotify.model_objects.specification.Track;
import se.michaelthelin.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;

import java.io.IOException;

//https://open.spotify.com/track/3uCth4TIWyeQDnj3YbAVQB
@Service
public class SpotifyMusicStreamServiceAPI implements MusicStreamServiceAPI {
    private static final String CLIENT_ID = "spotify.client.id";
    private static final String CLIENT_SECRET = "spotify.client.secret";

    private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
            .setClientId(PropertiesManager.getPropertyByKey(CLIENT_ID))
            .setClientSecret(PropertiesManager.getPropertyByKey(CLIENT_SECRET))
            .build();
    private static final ClientCredentialsRequest clientCredentialsRequest = spotifyApi.clientCredentials()
            .build();

    @Override
    public String getLinkByFullTrackName(String trackName, String artistName) {
        return "";
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
            StringBuilder artistsName = new StringBuilder();
            ArtistSimplified[] artists = track.getArtists();

            if (artists.length == 0) throw new TrackNotFoundException();
            else {
                for (ArtistSimplified artist : artists) artistsName.append(artist.getName()).append(", ");
                return artistsName.delete(artistsName.length()-2, artistsName.length()).toString().trim();
            }
        } catch (IOException | SpotifyWebApiException | ParseException | NullPointerException | TrackNotFoundException e) {
            throw new TrackNotFoundException(e);
        }
    }

    @Override
    public StreamServiceType getServiceType(){
        return StreamServiceType.SPOTIFY_MUSIC_LINK;
    }

    private String getTrackId(String link) {
        return link.replace("//", "/")
                .replace('?', '/')
                .split("/")[3];
    }

}
