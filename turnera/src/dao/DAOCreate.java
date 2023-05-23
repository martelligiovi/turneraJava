package dao;

import entidades.*;
import java.sql.*;

public class DAOCreate{
    private static final String DB_JDBC_DRIVER = "org.h2.Driver";
    private static final String DB_URL = "jdbc:h2:~/base";
    private static final String DB_USER = "admin"; //rellenar
    private static final String DB_PASSWORD = "admin"; //rellenar

    private void createMedico(){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement = connection.prepareStatement("CREATE TABLE Medico (dni INT PRIMARY KEY, nombre VARCHAR(50), apellido VARCHAR(50), licencia VARCHAR(50), honorarios INT)");
            int res = preparedStatement.executeUpdate();
            System.out.println("Se ha creado la tabla Medico " + res);
        }
        catch (ClassNotFoundException | SQLException e){
            System.out.println(e.getMessage());
        }
    }

    private void createPaciente(){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement = connection.prepareStatement("CREATE TABLE Paciente (dni INT PRIMARY KEY, nombre VARCHAR(50), apellido VARCHAR(50), osocial INT)");
            int res = preparedStatement.executeUpdate();
            System.out.println("Se ha creado la tabla Paciente " + res);
        }
        catch (ClassNotFoundException | SQLException e){
            System.out.println(e.getMessage());
        }
    }

    private void createTurno(){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement = connection.prepareStatement("CREATE TABLE Turno (dnimedico INT PRIMARY KEY, dnipaciente INT PRIMARY KEY , fecha DATE, hora TIME, FOREIGN KEY (dnimedico), FOREIGN KEY (dnipaciente) REFERENCES Medico(dni) Paciente(dni))");
            int res = preparedStatement.executeUpdate();
            System.out.println("Se ha creado la tabla Turno " + res);
        }
        catch (ClassNotFoundException | SQLException e){
            System.out.println(e.getMessage());
        }
    }
    private void createRelmedosoc(){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement = connection.prepareStatement("CREATE TABLE Relmedosoc (dnimedico INT PRIMARY KEY, dnipaciente INT PRIMARY KEY , FOREIGN KEY (dnimedico), FOREIGN KEY (dnipaciente) REFERENCES Medico(dni) Paciente(dni))");
            int res = preparedStatement.executeUpdate();
            System.out.println("Se ha creado la tabla Relmedosoc " + res);
        }catch (ClassNotFoundException | SQLException e){
            System.out.println(e.getMessage());
        }
    }
    private void createObraSocial(){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement = connection.prepareStatement("CREATE TABLE ObraSocial (osocial INT PRIMARY KEY, nombre VARCHAR(50))");
            int res = preparedStatement.executeUpdate();
            System.out.println("Se ha creado la tabla ObraSocial " + res);
        }catch (ClassNotFoundException | SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void createTables(){
        createMedico();
        createPaciente();
        createTurno();
        createRelmedosoc();
        createObraSocial();
    }

    private void dropMedico(){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement = connection.prepareStatement("DROP TABLE Medico");
            int res = preparedStatement.executeUpdate();
            System.out.println("Se ha eliminado la tabla Medico " + res);
        }
        catch (ClassNotFoundException | SQLException e){
            System.out.println(e.getMessage());
        }
    }
    private void dropPaciente(){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement = connection.prepareStatement("DROP TABLE Paciente");
            int res = preparedStatement.executeUpdate();
            System.out.println("Se ha eliminado la tabla Paciente " + res);
        }
        catch (ClassNotFoundException | SQLException e){
            System.out.println(e.getMessage());
        }
    }
    private void dropTurno(){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement = connection.prepareStatement("DROP TABLE Turno");
            int res = preparedStatement.executeUpdate();
            System.out.println("Se ha eliminado la tabla Turno " + res);
        }
        catch (ClassNotFoundException | SQLException e){
            System.out.println(e.getMessage());
        }
    }
    private void dropRelmedosoc(){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement = connection.prepareStatement("DROP TABLE Relmedosoc");
            int res = preparedStatement.executeUpdate();
            System.out.println("Se ha eliminado la tabla Relmedosoc " + res);
        }
        catch (ClassNotFoundException | SQLException e){
            System.out.println(e.getMessage());
        }
    }
    private void dropObraSocial(){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement = connection.prepareStatement("DROP TABLE ObraSocial");
            int res = preparedStatement.executeUpdate();
            System.out.println("Se ha eliminado la tabla ObraSocial " + res);
        }
        catch (ClassNotFoundException | SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public void dropAllTables() {
        dropMedico();
        dropPaciente();
        dropTurno();
        dropRelmedosoc();
        dropObraSocial();
    }
}
