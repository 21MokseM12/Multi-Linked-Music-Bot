package com.app.services;

import com.app.exceptions.TrackNotFoundException;
import com.app.factories.JDBCConnectionFactory;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

import javax.sound.midi.Track;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataBase {

    private Connection connection;

//    public DataBase() {
//        try {
//            connection = JDBCConnectionFactory.get();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    @PostConstruct
    private void initialization() {
        try {
            connection = JDBCConnectionFactory.get();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @PreDestroy
    private void destroy() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean containsTrack(String trackName, String artistName) throws TrackNotFoundException{
        final String containsTrackQuery = "SELECT COUNT(id) FROM tracks WHERE (track_name=? AND artist_name=?)";
        try {
            PreparedStatement statement = connection.prepareStatement(containsTrackQuery);
            statement.setString(1, trackName);
            statement.setString(2, artistName);
            ResultSet resultOfQuery = statement.executeQuery();
            int result = 0;
            if (resultOfQuery.next())
                result = resultOfQuery.getInt(1);
            return result != 0;
        } catch (SQLException e) {
            throw new TrackNotFoundException(e);
        }
    }

    public List<String> getLinks(String trackName, String artistName) {
        List<String> links = new ArrayList<>();
        final String getLinksQuery = "SELECT spotify_link FROM links WHERE track_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(getLinksQuery)) {
            statement.setLong(1, getTrackId(trackName, artistName));

            ResultSet result = statement.executeQuery();

            while (result.next())
                links.add(result.getString("spotify_link"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return links;
    }

    private long getTrackId(String trackName, String artistName) throws SQLException{
        final String getIdQuery = "SELECT id FROM tracks WHERE track_name = ? AND artist_name = ?";
        try (PreparedStatement statement = connection.prepareStatement(getIdQuery)) {
            statement.setString(1, trackName);
            statement.setString(2, artistName);

            ResultSet id = statement.executeQuery();
            if (id.next()) return id.getInt(1);
            else throw new SQLException();
        }
    }
}
