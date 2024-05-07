package CheckDataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class MySQLConnection {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/591houseprice";
        String username = "root";
        String password = "Binpro123";

        try {
            Connection connection = DriverManager.getConnection(url, username, password);

            // Using PreparedStatement to avoid SQL injection
            String sql = "INSERT INTO houseprice (Shape, Layout, Area, MainArea, Age, Floor, TopSchool, Parking, Balcony, LowStruct, DisTrict, Road, Money) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            // Set values for each parameter
            statement.setString(1, "電梯大樓");
            statement.setString(2, "3房2廳3衛");
            statement.setString(3, "權狀154.2坪");
            statement.setString(4, "主建82.46坪");
            statement.setString(5, "16年");
            statement.setString(6, "15F/24F");
            statement.setInt(7, 1);
            statement.setInt(8, 1);
            statement.setInt(9, 1);
            statement.setInt(10, 0);
            statement.setString(11, "苓雅區-");
            statement.setString(12, "四維三路");
            statement.setInt(13, 5380);

            // Execute the statement
            int rowsInserted = statement.executeUpdate();

            // Close the connection and statement
            statement.close();
            connection.close();
        } catch (Exception e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
    }
}
