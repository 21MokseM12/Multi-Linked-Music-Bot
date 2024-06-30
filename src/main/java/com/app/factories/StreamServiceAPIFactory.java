package com.app.factories;

import com.app.enums.StreamServiceType;
import com.app.services.interfaces.api.StreamServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;


@Component
public class StreamServiceAPIFactory {

    private final Map<StreamServiceType, StreamServiceAPI> streamServiceMap = new HashMap<>();

    @Autowired
    public StreamServiceAPIFactory(List<StreamServiceAPI> streamServices){
        for (StreamServiceAPI streamServiceAPI : streamServices){
            streamServiceMap.put(streamServiceAPI.getServiceType(), streamServiceAPI);
        }
    }

    public StreamServiceAPI getMusicService(StreamServiceType linkType) throws NoSuchElementException {
        Optional<StreamServiceAPI> musicStreamService = Optional.of(streamServiceMap.get(linkType));
        return musicStreamService.orElseThrow();
    }
}
