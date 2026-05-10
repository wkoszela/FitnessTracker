package pl.wsb.fitnesstracker;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class Lab03EntitiesTest {

    @Autowired
    private DataSource dataSource;

    @Test
    void shouldHaveEventTable() throws Exception {
        try (Connection conn = dataSource.getConnection()) {
            assertThat(tableExists(conn, "event")).isTrue();
        }
    }

    @Test
    void shouldHaveUserEventTable() throws Exception {
        try (Connection conn = dataSource.getConnection()) {
            assertThat(tableExists(conn, "user_event")).isTrue();
        }
    }

    @Test
    void shouldHaveWorkoutSessionTable() throws Exception {
        try (Connection conn = dataSource.getConnection()) {
            assertThat(tableExists(conn, "workout_session")).isTrue();
        }
    }

    private boolean tableExists(Connection conn, String expectedName) throws SQLException {
        DatabaseMetaData meta = conn.getMetaData();
        try (ResultSet rs = meta.getTables(conn.getCatalog(), null, "%", new String[]{"TABLE"})) {
            while (rs.next()) {
                String schema = rs.getString("TABLE_SCHEM");
                if (schema == null) {
                    continue;
                }
                if (!"PUBLIC".equalsIgnoreCase(schema)) {
                    continue;
                }
                String name = rs.getString("TABLE_NAME");
                if (expectedName.equalsIgnoreCase(name)) {
                    return true;
                }
            }
        }
        return false;
    }
}
