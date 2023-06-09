package dao;

import entidades.*;
import java.sql.*;

public class DAOCreate{
    private static final String DB_JDBC_DRIVER = "org.h2.Driver";
    private static final String DB_URL = "jdbc:h2:./database/test";
    private static final String DB_USER = "gio"; //rellenar
    private static final String DB_PASSWORD = "gio"; //rellenar

    private static void createMedico(){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement = connection.prepareStatement("CREATE TABLE Medico (dni INT , nombre VARCHAR(50), apellido VARCHAR(50), legajo INT PRIMARY KEY)");
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
            preparedStatement = connection.prepareStatement("CREATE TABLE Turno (legajoMedico INT, dniPaciente INT, fecha VARCHAR(20),costo INT,PRIMARY KEY (legajoMedico, dniPaciente, fecha), FOREIGN KEY (legajoMedico) REFERENCES Medico(legajo), FOREIGN KEY (dniPaciente) REFERENCES Paciente(dni));\n");
            int res = preparedStatement.executeUpdate();
            System.out.println("Se ha creado la tabla Turno " + res);
        }
        catch (ClassNotFoundException | SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public static void createRelacionMedicoObrasocial(){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement = connection.prepareStatement("CREATE TABLE RelacionMedicoObrasocial (legajoMedico INT, codObrasocial INT, PRIMARY KEY (legajoMedico, codObrasocial), FOREIGN KEY (legajoMedico) REFERENCES Medico(legajo), FOREIGN KEY (codObrasocial) REFERENCES Obrasocial(cod));\n");
            int res = preparedStatement.executeUpdate();
            System.out.println("Se ha creado la tabla Relmedosoc " + res);
        }catch (ClassNotFoundException | SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public static void createObraSocial(){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement = connection.prepareStatement("CREATE TABLE ObraSocial (cod INT PRIMARY KEY)");
            int res = preparedStatement.executeUpdate();
            System.out.println("Se ha creado la tabla ObraSocial " + res);
        }catch (ClassNotFoundException | SQLException e){
            System.out.println(e.getMessage());
        }
    }
    //admin solo tiene usuario y contraseña, y los valores que van son admin y admin
    public static void crearAdmin(){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatement2 = null;
        try{
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement = connection.prepareStatement("CREATE TABLE Admin (usuario VARCHAR(50), contraseña VARCHAR(50))");
            preparedStatement2 = connection.prepareStatement("INSERT INTO Admin VALUES ('admin', 'admin')");
            int res = preparedStatement.executeUpdate();
            int res2 = preparedStatement2.executeUpdate();
            System.out.println("Se ha creado la tabla Admin " + res);
            System.out.println("Se ha insertado el usuario admin " + res2);
        }catch (ClassNotFoundException | SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public static void createTablas(){
        createMedico();
        createPaciente();
        createTurno();
        crearAdmin();
    }

    public static void dropMedico(){
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
    public static void dropPaciente(){
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
    public static void dropTurno(){
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
    public static void dropRelacionMedicoObrasocial(){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement = connection.prepareStatement("DROP TABLE RelacionMedicoObrasocial");
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
        dropTurno();
        dropMedico();
        dropRelacionMedicoObrasocial();
        dropPaciente();
        dropObraSocial();
    }
}
