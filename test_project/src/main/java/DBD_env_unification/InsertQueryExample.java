package DBD_env_unification;

import java.sql.*;

public class InsertQueryExample {
    public static void main(String[] argv) {
        /* Retrieve DB authentication information */
        DatabaseAuthInformation db_info = new DatabaseAuthInformation();
        db_info.parse_auth_info("auth/mysql.auth");
        /* Prepare the URL for DB connection */
        //String db_connection_url = String.format("jdbc:mysql://%s:%s/%s", db_info.getHost(),
        //        db_info.getPort(),
        //        db_info.getDatabase_name());
        String db_connection_url = String.format("jdbc:mysql://localhost:3306/dbdesign_lecture_db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");

        /* Prepare the query statement */
        String query_string = "insert into TEST_TB (id, value) VALUES (?, ?)";

        /* DB insertion process */
        try (Connection db_connection = DriverManager.getConnection(db_connection_url,
                db_info.getUsername(),
                db_info.getPassword());
             PreparedStatement db_statement = db_connection.prepareStatement(query_string)){
            int result;

            // 1st Record
            db_statement.setInt(1,12345);
            db_statement.setFloat(2,93.7f);
            result = db_statement.executeUpdate();
            System.out.println("Updated query: " + result);

            // 2nd Record
            db_statement.setInt(1,16872);
            db_statement.setFloat(2,88.4f);
            result = db_statement.executeUpdate();
            System.out.println("Updated query: " + result);

            // 3rd Record
            db_statement.setInt(1,78916);
            db_statement.setFloat(2,99.2f);
            result = db_statement.executeUpdate();
            System.out.println("Updated query: " + result);

            // 4th Record
            db_statement.setInt(1,81647);
            db_statement.setFloat(2,81.9f);
            result = db_statement.executeUpdate();
            System.out.println("Updated query: " + result);

            // 5th Record
            db_statement.setInt(1,48463);
            db_statement.setFloat(2,68.2f);
            result = db_statement.executeUpdate();
            System.out.println("Updated query: " + result);

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}