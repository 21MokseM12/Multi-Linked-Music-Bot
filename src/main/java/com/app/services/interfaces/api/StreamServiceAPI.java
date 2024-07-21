package com.app.services.interfaces.api;

import com.app.enums.StreamServiceType;
import com.app.exceptions.TrackNotFoundException;

public interface StreamServiceAPI {
    String getTrackName(String link) throws TrackNotFoundException;
    String getArtistName(String link) throws TrackNotFoundException;
    StreamServiceType getServiceType();
    String getLinkByFullTrackName(String trackName, String artistName) throws TrackNotFoundException;
}
