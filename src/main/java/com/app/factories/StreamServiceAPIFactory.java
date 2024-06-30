package com.app.factories;

import com.app.enums.StreamServiceType;
import com.app.services.interfaces.api.MusicStreamServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;


@Component
public class StreamServiceAPIFactory {

    private final Map<StreamServiceType, MusicStreamServiceAPI> streamServiceMap = new HashMap<>();

    @Autowired
    public StreamServiceAPIFactory(List<MusicStreamServiceAPI> streamServices){
        for (MusicStreamServiceAPI streamServiceAPI : streamServices){
            streamServiceMap.put(streamServiceAPI.getServiceType(), streamServiceAPI);
        }
    }

    public MusicStreamServiceAPI getMusicService(StreamServiceType linkType) throws NoSuchElementException {
        Optional<MusicStreamServiceAPI> musicStreamService = Optional.of(streamServiceMap.get(linkType));
        return musicStreamService.orElseThrow();
    }
}
