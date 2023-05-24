package dao;


import entidades.ObraSocial;

import java.sql.*;
import java.util.ArrayList;


public class DAOObraSocial implements DAO<ObraSocial>{
    private String DB_JDBC_DRIVER="org.h2.Driver";
    private String DB_URL="jdbc:h2:C:/Users/bnbgi/OneDrive/Escritorio/server/test";
    private String DB_USER="gio";
    private String DB_PASSWORD="gio";
    @Override
    public void guardar(ObraSocial elemento) throws DAOException {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        try {
            Class.forName(DB_JDBC_DRIVER);
            connection= DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            preparedStatement=connection.prepareStatement("INSERT into Obrasocial Values(?,?)");
            preparedStatement.setLong(1,elemento.getCodigo());
            preparedStatement.setString(2, elemento.getNombre());

            int res=preparedStatement.executeUpdate();
            System.out.println("Se agregaron " + res);
        }
        catch (ClassNotFoundException | SQLException e)
        {
            throw  new DAOException(e.getMessage());
        }
    }

    @Override
    public void modificar(ObraSocial elemento) throws DAOException {

        Connection connection=null;
        PreparedStatement preparedStatement=null;
        try {
            Class.forName(DB_JDBC_DRIVER);
            connection= DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            preparedStatement=connection.prepareStatement("UPDATE Obrasocial SET nombre=? WHERE cod=?");
            preparedStatement.setString(2, elemento.getNombre());
            preparedStatement.setLong(1,elemento.getCod());
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
            preparedStatement=connection.prepareStatement("DELETE FROM Obrasocial  WHERE cod=?");

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
    public ObraSocial buscar(long id) throws DAOException {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ObraSocial medico=null;
        try {
            Class.forName(DB_JDBC_DRIVER);
            connection= DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            preparedStatement=connection.prepareStatement("SELECT * FROM Obrasocial  WHERE cod=?");
            preparedStatement.setLong(1,id);
            ResultSet resultSet =preparedStatement.executeQuery();
            if (resultSet.next()) {
                ObraSocial = new ObraSocial(
                        resultSet.getString("NOMBRE"),
                        resultSet.getInt("COD")
                );
            }
        }
        catch (ClassNotFoundException | SQLException e)
        {
            throw  new DAOException(e.getMessage());
        }
        return medico;
    }


    @Override
    public ArrayList buscarTodos() throws DAOException {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ArrayList<ObraSocial> datos=new ArrayList<>();
        ObraSocial obrasocial=null;
        try {
            Class.forName(DB_JDBC_DRIVER);
            connection= DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            preparedStatement=connection.prepareStatement("SELECT * FROM medico");
            ResultSet resultSet =preparedStatement.executeQuery();
            while (resultSet.next()) {

                obrasocial = new ObraSocial(
                        resultSet.getString("NOMBRE"),
                        resultSet.getInt("COD")
                );

                datos.add(obrasocial);
            }
        }
        catch (ClassNotFoundException | SQLException e)
        {
            throw  new DAOException(e.getMessage());
        }
        return datos;
    }
}