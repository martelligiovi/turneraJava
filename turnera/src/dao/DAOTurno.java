package dao;

import entidades.Turno;

import java.sql.*;
import java.util.ArrayList;


public class DAOTurno implements DAO<Turno>{
    private String DB_JDBC_DRIVER="org.h2.Driver";
    private String DB_URL="jdbc:h2:C:/Users/bnbgi/OneDrive/Escritorio/server/test";
    private String DB_USER="gio";
    private String DB_PASSWORD="gio";
    @Override
    public void guardar(Turno elemento) throws DAOException {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        try {
            Class.forName(DB_JDBC_DRIVER);
            connection= DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            preparedStatement=connection.prepareStatement("INSERT into Turno Values(?,?,?,?)");
            preparedStatement.setInt(1,elemento.getLegajoMedico());
            preparedStatement.setInt(2, elemento.getDniPaciente());
            preparedStatement.setInt(3, elemento.getFecha());
            preparedStatement.setDouble(3, elemento.getCosto());


            int res=preparedStatement.executeUpdate();
            System.out.println("Se agregaron " + res);
        }
        catch (ClassNotFoundException | SQLException e)
        {
            throw  new DAOException(e.getMessage());
        }
    }

    @Override
    public void modificar(Turno elemento) throws DAOException {

        Connection connection=null;
        PreparedStatement preparedStatement=null;
        try {
            Class.forName(DB_JDBC_DRIVER);
            connection= DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            preparedStatement=connection.prepareStatement("UPDATE turno SET legajoMedico=?, dniPaciente=?, fecha=?,costo=? WHERE id=?");

            preparedStatement.setInt(1,elemento.getLegajoMedico());
            preparedStatement.setInt(2, elemento.getDniPaciente());
            preparedStatement.setInt(3, elemento.getFecha());
            preparedStatement.setDouble(3, elemento.getCosto());
            int res=preparedStatement.executeUpdate();
            System.out.println("Se modificaron " + res);
        }
        catch (ClassNotFoundException | SQLException e)
        {
            throw  new DAOException(e.getMessage());
        }


    }

    @Override
    public void eliminar(long id) throws DAOException {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        try {
            Class.forName(DB_JDBC_DRIVER);
            connection= DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            preparedStatement=connection.prepareStatement("DELETE FROM turno  WHERE id=?");

            preparedStatement.setLong(1,id);
            int res=preparedStatement.executeUpdate();
            System.out.println("Se elimino" + res);
        }
        catch (ClassNotFoundException | SQLException e)
        {
            throw  new DAOException(e.getMessage());
        }
    }

    @Override
    public Turno buscar(long id) throws DAOException {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        Turno turno=null;
        try {
            Class.forName(DB_JDBC_DRIVER);
            connection= DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            preparedStatement=connection.prepareStatement("SELECT * FROM turno  WHERE id=?");
            preparedStatement.setLong(1,id);
            ResultSet resultSet =preparedStatement.executeQuery();
            if (resultSet.next()) {
               // turno = new Turno(resultSet.getString("NOMBRE"));
            }
        }
        catch (ClassNotFoundException | SQLException e)
        {
            throw  new DAOException(e.getMessage());
        }
        return turno;
    }


    @Override
    public ArrayList buscarTodos() throws DAOException {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ArrayList<Turno> datos=new ArrayList<>();
        Turno turno=null;
        try {
            Class.forName(DB_JDBC_DRIVER);
            connection= DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            preparedStatement=connection.prepareStatement("SELECT * FROM turno");
            ResultSet resultSet =preparedStatement.executeQuery();
            while (resultSet.next()) {

                /*turno = new Turno(
                resultSet.getInt("LEGAJO"),
                resultSet.getInt("DNI")
                );
                datos.add(turno);*/
            }
        }
        catch (ClassNotFoundException | SQLException e)
        {
            throw  new DAOException(e.getMessage());
        }
        return datos;
    }
}