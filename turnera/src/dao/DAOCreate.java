package dao;

import entidades.*;
import java.sql.*;

public class DAOCreate{
    private static final String DB_JDBC_DRIVER = "org.h2.Driver";
    private static final String DB_URL = "jdbc:h2:C:/Users/bnbgi/OneDrive/Escritorio/server/test";
    private static final String DB_USER = "gio"; //rellenar
    private static final String DB_PASSWORD = "gio"; //rellenar

    private static void createMedico(){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement = connection.prepareStatement("CREATE TABLE Medico (dni INT PRIMARY KEY, nombre VARCHAR(50), apellido VARCHAR(50), legajo VARCHAR(50), honorarios INT)");
            int res = preparedStatement.executeUpdate();
            System.out.println("Se ha creado la tabla Medico " + res);
        }
        catch (ClassNotFoundException | SQLException e){
            System.out.println(e.getMessage());
        }
    }

    private static void createPaciente(){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement = connection.prepareStatement("CREATE TABLE Paciente (dni INT PRIMARY KEY, nombre VARCHAR(50), apellido VARCHAR(50), codObrasocial INT)");
            int res = preparedStatement.executeUpdate();
            System.out.println("Se ha creado la tabla Paciente " + res);
        }
        catch (ClassNotFoundException | SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public static void createTurno(){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement = connection.prepareStatement("CREATE TABLE Turno (dnimedico INT, dnipaciente INT, fecha DATE, hora TIME, PRIMARY KEY (dnimedico, dnipaciente), FOREIGN KEY (dnimedico) REFERENCES Medico(dni), FOREIGN KEY (dnipaciente) REFERENCES Paciente(dni));\n");
            int res = preparedStatement.executeUpdate();
            System.out.println("Se ha creado la tabla Turno " + res);
        }
        catch (ClassNotFoundException | SQLException e){
            System.out.println(e.getMessage());
        }
    }
    private static void createRelmedosoc(){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement = connection.prepareStatement("CREATE TABLE Relmedosoc (dnimedico INT, dnipaciente INT, PRIMARY KEY (dnimedico, dnipaciente), FOREIGN KEY (dnimedico) REFERENCES Medico(dni), FOREIGN KEY (dnipaciente) REFERENCES Paciente(dni));\n");
            int res = preparedStatement.executeUpdate();
            System.out.println("Se ha creado la tabla Relmedosoc " + res);
        }catch (ClassNotFoundException | SQLException e){
            System.out.println(e.getMessage());
        }
    }
    private static void createObraSocial(){
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

    public static void createTables(){
        createMedico();
        createPaciente();
        createTurno();
        createRelmedosoc();
        createObraSocial();
    }

    private static void dropMedico(){
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
    private static void dropPaciente(){
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
    private static void dropTurno(){
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
    private static void dropRelmedosoc(){
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
    private static void dropObraSocial(){
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
    public static void dropAllTables() {
        dropMedico();
        dropPaciente();
        dropTurno();
        dropRelmedosoc();
        dropObraSocial();
    }
}
