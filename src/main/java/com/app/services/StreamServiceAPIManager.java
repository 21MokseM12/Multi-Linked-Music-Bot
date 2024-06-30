package com.app.services;

import com.app.services.interfaces.api.StreamServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class StreamServiceAPIManager {
    @Autowired
    private List<StreamServiceAPI> streamServiceAPIList;

    public List<String> getLinks(String trackName, String artistName) {
        List<String> links = new ArrayList<>();
        for (StreamServiceAPI streamService : streamServiceAPIList)
            links.add(streamService.getLinkByFullTrackName(trackName, artistName));
        return links;
    }
}
