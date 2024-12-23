package in.gov.cgg.scheduler.paymentFiles;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class DatabaseHelper {
    @Autowired
    private DataSource dataSource;

    public DatabaseHelper(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Fetches a string value from the database using the provided query and parameters.
     * @param query SQL query to execute
     * @param params parameters for the query
     * @return the string result or null if not found
     */
    public String getStringfromQuery(String query, Object[] params) {
        String result = null;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            // Set parameters dynamically
            setParameters(stmt, params);

            // Execute the query
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    result = rs.getString(1); // Assuming the first column is the string we want
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // You can handle this differently based on your app needs
        }

        return result;
    }

    /**
     * Checks if a row exists based on the provided query and parameters.
     * @param query SQL query to execute
     * @param params parameters for the query
     * @return true if the row exists, false otherwise
     */
    public boolean isExist(String query, Object[] params) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            // Set parameters dynamically
            setParameters(stmt, params);

            // Execute the query and check if the result set is not empty
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exception appropriately
            return false;
        }
    }

     /**
     * Executes the provided query and returns a list of results as maps.
     * Each map represents a row with column names as keys and the column values as values.
     * @param query SQL query to execute
     * @param params parameters for the query
     * @return List of maps representing the result set
     * @throws SQLException if there's an issue executing the query
     */
    public List<Map<String, Object>> executeQuery(String query, Object[] params) throws SQLException {
        List<Map<String, Object>> results = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            // Set parameters dynamically
            setParameters(stmt, params);

            // Execute the query and get the ResultSet
            try (ResultSet rs = stmt.executeQuery()) {
                ResultSetMetaData metaData = rs.getMetaData();
                int columnCount = metaData.getColumnCount();

                // Process the result set
                while (rs.next()) {
                    Map<String, Object> row = new HashMap<>();
                    for (int i = 1; i <= columnCount; i++) {
                        String columnName = metaData.getColumnLabel(i);
                        Object columnValue = rs.getObject(i);
                        row.put(columnName, columnValue);
                    }
                    results.add(row);
                }
            }
        }

        return results;
    }

    // New executeUpdate method for INSERT/UPDATE/DELETE queries
    public int executeUpdate(String query, Object[] params) {
        int rowsAffected = 0;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            setParameters(stmt, params);

            // Execute the update query (INSERT, UPDATE, DELETE)
            rowsAffected = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rowsAffected;
    }
    /**
     * Helper method to set parameters for PreparedStatement.
     * @param stmt PreparedStatement to set the parameters on
     * @param params parameters to set
     * @throws SQLException if there's an issue setting parameters
     */
    private void setParameters(PreparedStatement stmt, Object[] params) throws SQLException {
        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i + 1, params[i]);
            }
        }
    }


    public int executeBatch(List<String> sqls) {
        int result = 0;
        if (sqls == null || sqls.isEmpty()) {
            throw new IllegalArgumentException("executeBatch(): NULL or empty SQLs");
        }
        Connection con = null;
        Statement st = null;

        try {
            // Get the connection from DriverManagerDataSource
            con = dataSource.getConnection();

            // Create a statement
            st = con.createStatement();

            // Add each SQL query to the batch
            for (String sql : sqls) {
                st.addBatch(sql);
            }

            // Execute the batch
            int[] count = st.executeBatch();

            // If the batch was executed successfully, set the result
            if (count != null) {
                result = 1; // Success
            }
        } catch (SQLException e) {
            // Handle exceptions (e.g., logging, rethrowing)
            e.printStackTrace();
            result = 0; // Failure
        } finally {
            // Clean up resources
            closeResources(st, con);
        }

        return result;
    }

    private void closeResources(Statement st, Connection con) {
        try {
            if (st != null) {
                st.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
