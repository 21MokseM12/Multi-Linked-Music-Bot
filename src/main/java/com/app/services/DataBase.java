package com.app.services;

import com.app.exceptions.TrackNotFoundException;
import com.app.factories.JDBCConnectionFactory;
import com.app.utils.PropertiesManager;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class DataBase {

    private Connection connection;

    private List<String> columnNames;

    private String allColumnNames;

    private static final String COLUMNS_NAMES_KEY = "db.column.names";

//    public DataBase() {
//        columnNames = new ArrayList<>();
//        columnNames.addAll(Arrays.asList(PropertiesManager.getPropertyByKey(COLUMNS_NAMES_KEY).split(";")));
//
//        try {
//            connection = JDBCConnectionFactory.get();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    @PostConstruct
    private void initialization() {
        allColumnNames = PropertiesManager.getPropertyByKey(COLUMNS_NAMES_KEY);

        columnNames = new ArrayList<>();
        columnNames.addAll(Arrays.asList(allColumnNames.split(" ,")));

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

        final String getLinksQuery = "SELECT ? FROM links WHERE track_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(getLinksQuery)) {
            statement.setString(1, allColumnNames);
            statement.setLong(2, getTrackId(trackName, artistName));

            ResultSet result = statement.executeQuery();

            while (result.next())
                for (String columnName : columnNames)
                    links.add(result.getString(columnName));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return links;
    }

    public long getTrackId(String trackName, String artistName) throws SQLException{
        final String getIdQuery = "SELECT id FROM tracks WHERE track_name = ? AND artist_name = ?";
        try (PreparedStatement statement = connection.prepareStatement(getIdQuery)) {
            statement.setString(1, trackName);
            statement.setString(2, artistName);

            ResultSet id = statement.executeQuery();
            if (id.next()) return id.getInt(1);
            else throw new SQLException();
        }
    }

    public void saveTrackNameAndLinks(long id, String trackName, String artistName, List<String> links) throws SQLException {
        final String saveName = "INSERT INTO tracks VALUES (id = ?, track_name = ?, artist_name = ?)";
        final String saveLinks = """
                            INSERT INTO links
                            VALUES (
                                track_id = ?,
                                spotify_link = ?)""";

        connection.setAutoCommit(false);

        try (PreparedStatement saveNameState = connection.prepareStatement(saveName);
        PreparedStatement saveLinksState = connection.prepareStatement(saveLinks)) {
            saveNameState.setLong(1, id);
            saveNameState.setString(2, trackName);
            saveNameState.setString(3, artistName);

            saveLinksState.setLong(1, id);
            for (int i = 0; i < links.size(); i++)
                saveLinksState.setString(i+2, links.get(i));

            saveNameState.executeUpdate();
            saveLinksState.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
        } finally {
            connection.setAutoCommit(true);
        }
    }
}
