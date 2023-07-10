package dao;

import java.sql.*;

public class DAOAdmin {
    public boolean login(String user, String password) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:C:/Users/bnbgi/OneDrive/Escritorio/server/test", "gio", "gio");
            preparedStatement = connection.prepareStatement("SELECT * FROM Admin WHERE username=? AND password=?");
            preparedStatement.setString(1, user);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            return resultSet.next(); // Devuelve true si hay al menos una fila en el resultado
        } catch (ClassNotFoundException | SQLException e) {
            throw new DAOException(e.getMessage());
        }
    }
}
