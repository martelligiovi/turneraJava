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
        System.out.println("legajo");
        System.out.println(elemento.getLegajoMedico());
        try {
            Class.forName(DB_JDBC_DRIVER);
            connection= DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            preparedStatement=connection.prepareStatement("INSERT into Turno Values(?,?,?,?)");
            preparedStatement.setInt(1,elemento.getLegajoMedico());
            preparedStatement.setInt(2, elemento.getDniPaciente());
            preparedStatement.setString(3, elemento.getFecha());
            preparedStatement.setDouble(4, elemento.getCosto());
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
            preparedStatement.setString(3, elemento.getFecha());
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

                turno = new Turno();
                turno.setLegajoMedico(resultSet.getInt("legajoMedico"));
                turno.setDniPaciente(resultSet.getInt("dniPaciente"));
                turno.setFecha(resultSet.getString("fecha"));
                datos.add(turno);
            }
        }
        catch (ClassNotFoundException | SQLException e)
        {
            throw  new DAOException(e.getMessage());
        }
        return datos;
    }

    public ArrayList<String> buscarHorariosPorMedico(String fecha, int legajoMedico) throws DAOException {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ArrayList<String> datos=new ArrayList<>();
        String turno=null;
        try {
            Class.forName(DB_JDBC_DRIVER);
            connection= DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            preparedStatement=connection.prepareStatement("SELECT * FROM turno WHERE SUBSTRING(fecha, 1, 10) = ? AND legajoMedico = ?");
            preparedStatement.setString(1, fecha);
            preparedStatement.setInt(2, legajoMedico);
            ResultSet resultSet =preparedStatement.executeQuery();
            while (resultSet.next()) {
                turno = resultSet.getString("fecha");
                datos.add(turno);
            }
        }
        catch (ClassNotFoundException | SQLException e)
        {
            throw  new DAOException(e.getMessage());
        }
        return datos;
    }
    public ArrayList<String> buscarHorariosPorPaciente(String fecha, int dni) throws DAOException {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ArrayList<String> datos=new ArrayList<>();
        String turno=null;
        try {
            Class.forName(DB_JDBC_DRIVER);
            connection= DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            preparedStatement=connection.prepareStatement("SELECT * FROM turno WHERE SUBSTRING(fecha, 1, 10) = ? AND dnipaciente = ?");
            preparedStatement.setString(1, fecha);
            preparedStatement.setInt(2, dni);
            ResultSet resultSet =preparedStatement.executeQuery();
            while (resultSet.next()) {
                turno = resultSet.getString("fecha");
                datos.add(turno);
            }
        }
        catch (ClassNotFoundException | SQLException e)
        {
            throw  new DAOException(e.getMessage());
        }
        return datos;
    }
    public ArrayList<Turno> buscarTurnosMedico(String fecha, int legajoMedico) throws DAOException {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ArrayList<Turno> datos=new ArrayList<>();
        Turno turno=new Turno();
        try {
            Class.forName(DB_JDBC_DRIVER);
            connection= DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            preparedStatement=connection.prepareStatement("SELECT * FROM turno WHERE SUBSTRING(fecha, 1, 10) = ? AND legajoMedico = ? ORDER BY fecha DESC;");
            preparedStatement.setString(1, fecha);
            preparedStatement.setInt(2, legajoMedico);
            ResultSet resultSet =preparedStatement.executeQuery();
            while (resultSet.next()) {

                turno = new Turno();
                turno.setLegajoMedico(resultSet.getInt("legajoMedico"));
                turno.setDniPaciente(resultSet.getInt("dniPaciente"));
                turno.setFecha(resultSet.getString("fecha"));
                turno.setCosto(resultSet.getDouble("costo"));
                datos.add(turno);
            }
        }
        catch (ClassNotFoundException | SQLException e)
        {
            throw  new DAOException(e.getMessage());
        }
        return datos;
    }

    public ArrayList<Turno>buscarCobros(String fecha1, String fecha2, int legajo) throws DAOException {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        Turno turno;
        ArrayList<Turno> datos=new ArrayList<>();

        try {
            Class.forName(DB_JDBC_DRIVER);
            connection= DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            preparedStatement=connection.prepareStatement("SELECT * FROM turno WHERE fecha BETWEEN CONCAT(?, ' 00:00') AND CONCAT(?, ' 23:59') AND legajoMedico = ? ORDER BY fecha DESC;");
            preparedStatement.setString(1, fecha1);
            preparedStatement.setString(2, fecha2);
            preparedStatement.setInt(3, legajo);
            ResultSet resultSet =preparedStatement.executeQuery();
            while (resultSet.next()) {
                turno=new Turno();
                turno.setCosto(resultSet.getDouble("costo"));
                turno.setLegajoMedico(resultSet.getInt("legajoMedico"));
                turno.setDniPaciente(resultSet.getInt("dniPaciente"));
                turno.setFecha(resultSet.getString("fecha"));
                datos.add(turno);
            }
        }
        catch (ClassNotFoundException | SQLException e)
        {
            throw  new DAOException(e.getMessage());
        }
        return datos;
    }
    public ArrayList<Turno> buscarTurnosPaciente(int dni) throws DAOException {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ArrayList<Turno> datos=new ArrayList<>();
        Turno turno=new Turno();
        try {
            Class.forName(DB_JDBC_DRIVER);
            connection= DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            preparedStatement=connection.prepareStatement("SELECT * FROM turno WHERE DniPaciente = ? ORDER BY fecha DESC;");
            preparedStatement.setInt(1, dni);
            ResultSet resultSet =preparedStatement.executeQuery();
            while (resultSet.next()) {
                turno = new Turno();
                turno.setLegajoMedico(resultSet.getInt("legajoMedico"));
                turno.setFecha(resultSet.getString("fecha"));
                turno.setCosto(resultSet.getDouble("costo"));
                datos.add(turno);
            }
        }
        catch (ClassNotFoundException | SQLException e)
        {
            throw  new DAOException(e.getMessage());
        }
        return datos;
    }

}