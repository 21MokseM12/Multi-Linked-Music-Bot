package database;

import com.app.exceptions.TrackNotFoundException;
import com.app.services.DataBase;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.sql.SQLException;
import java.util.List;

public class DataBaseTest {

    private final DataBase db = new DataBase();

    @Test
    public void validGetTrackId() throws SQLException {
        Assertions.assertEquals(1, db.getTrackId("?", "?"));
    }

    @Test
    public void invalidGetTrackId() throws SQLException {
        Assertions.assertNotEquals(2, db.getTrackId("?", "?"));
    }

    @Test
    public void validGetLinks() {
        Assertions.assertEquals(List.of("?"), db.getLinks("?", "?"));
    }

    @Test
    public void invalidGetLinks() {
        Assertions.assertNotEquals(List.of(), db.getLinks("?", "?"));
    }

    @Test
    public void validContainsTrack() throws TrackNotFoundException {
        Assertions.assertTrue(db.containsTrack("?", "?"));
    }

    @Test
    public void invalidContainsTrack() throws TrackNotFoundException {
        Assertions.assertFalse(db.containsTrack("привет", "это тест"));
    }
}
