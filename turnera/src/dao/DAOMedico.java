package dao;
import entidades.Medico;import java.sql.*;
import java.util.ArrayList;


    public class DAOMedico implements DAO<Medico>{
        private String DB_JDBC_DRIVER="org.h2.Driver";
        private String DB_URL="jdbc:h2~/base";
        private String DB_USER="sa";
        private String DB_PASSWORD="";
        @Override
        public void guardar(Medico elemento) throws DAOException {
            Connection connection=null;
            PreparedStatement preparedStatement=null;
            try {
                Class.forName(DB_JDBC_DRIVER);
                connection= DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
                preparedStatement=connection.prepareStatement("INSERT into Medico Values(?,?,?,?,?)");
                preparedStatement.setLong(1,elemento.getLegajo());
                preparedStatement.setString(2, elemento.getNombre());
                preparedStatement.setString(3, elemento.getNombre());
                preparedStatement.setString(4, elemento.getApellido());
                preparedStatement.setInt(5, elemento.getDni());

                int res=preparedStatement.executeUpdate();
                System.out.println("Se agregaron " + res);
            }
            catch (ClassNotFoundException | SQLException e)
            {
                throw  new DAOException(e.getMessage());
            }
        }

        @Override
        public void modificar(Medico elemento) throws DAOException {

            Connection connection=null;
            PreparedStatement preparedStatement=null;
            try {
                Class.forName(DB_JDBC_DRIVER);
                connection= DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
                preparedStatement=connection.prepareStatement("UPDATE medico SET nombre=?, domicilio=?, telefono=? WHERE id=?");

                preparedStatement.setLong(1,elemento.getLegajo());
                preparedStatement.setString(2, elemento.getNombre());
                preparedStatement.setString(3, elemento.getNombre());
                preparedStatement.setString(4, elemento.getApellido());
                preparedStatement.setInt(5, elemento.getDni());
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
                preparedStatement=connection.prepareStatement("DELETE FROM medico  WHERE id=?");

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
        public Medico buscar(long id) throws DAOException {
            Connection connection=null;
            PreparedStatement preparedStatement=null;
            Medico medico=null;
            try {
                Class.forName(DB_JDBC_DRIVER);
                connection= DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
                preparedStatement=connection.prepareStatement("SELECT * FROM medico  WHERE id=?");
                preparedStatement.setLong(1,id);
                ResultSet resultSet =preparedStatement.executeQuery();
                if (resultSet.next()) {
                    medico = new Medico(
                            resultSet.getString("NOMBRE"),
                            resultSet.getString("APELLIDO"),
                            resultSet.getInt("LEGAJO"),
                            resultSet.getInt("DNI")
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
            ArrayList<Medico> datos=new ArrayList<>();
            Medico medico=null;
            try {
                Class.forName(DB_JDBC_DRIVER);
                connection= DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
                preparedStatement=connection.prepareStatement("SELECT * FROM medico");
                ResultSet resultSet =preparedStatement.executeQuery();
                while (resultSet.next()) {

                    medico = new Medico(
                    resultSet.getString("NOMBRE"),
                    resultSet.getString("APELLIDO"),
                    resultSet.getInt("LEGAJO"),
                    resultSet.getInt("DNI")
                    );
                    datos.add(medico);
                }
            }
            catch (ClassNotFoundException | SQLException e)
            {
                throw  new DAOException(e.getMessage());
            }
            return datos;
        }
    }