package dao;


public class DAORelacionMedicoObraSocial /*implements DAO<ObraSocial>*/{
    /*private String DB_JDBC_DRIVER="org.h2.Driver";
    private String DB_URL="jdbc:h2:C:/Users/bnbgi/OneDrive/Escritorio/server/test";
    private String DB_USER="gio";
    private String DB_PASSWORD="gio";
    @Override
    public void guarder(ObraSocial elemento) throws DAOException {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        try {
            Class.forName(DB_JDBC_DRIVER);
            connection= DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            preparedStatement=connection.prepareStatement("INSERT into ObraSocial Values(?,?,?,?,?)");
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
    public void modificar(ObraSocial elemento) throws DAOException {

        Connection connection=null;
        PreparedStatement preparedStatement=null;
        try {
            Class.forName(DB_JDBC_DRIVER);
            connection= DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            preparedStatement=connection.prepareStatement("UPDATE obraSocial SET nombre=?, domicilio=?, telefono=? WHERE id=?");

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
            preparedStatement=connection.prepareStatement("DELETE FROM obraSocial  WHERE id=?");

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
        ObraSocial obraSocial=null;
        try {
            Class.forName(DB_JDBC_DRIVER);
            connection= DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            preparedStatement=connection.prepareStatement("SELECT * FROM obraSocial  WHERE id=?");
            preparedStatement.setLong(1,id);
            ResultSet resultSet =preparedStatement.executeQuery();
            if (resultSet.next()) {
                obraSocial = new ObraSocial(
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
        return obraSocial;
    }


    @Override
    public ArrayList buscarTodos() throws DAOException {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ArrayList<ObraSocial> datos=new ArrayList<>();
        ObraSocial obraSocial=null;
        try {
            Class.forName(DB_JDBC_DRIVER);
            connection= DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            preparedStatement=connection.prepareStatement("SELECT * FROM obraSocial");
            ResultSet resultSet =preparedStatement.executeQuery();
            while (resultSet.next()) {

                obraSocial = new ObraSocial(
                resultSet.getString("NOMBRE"),
                resultSet.getString("APELLIDO"),
                resultSet.getInt("LEGAJO"),
                resultSet.getInt("DNI")
                );
                datos.add(obraSocial);
            }
        }
        catch (ClassNotFoundException | SQLException e)
        {
            throw  new DAOException(e.getMessage());
        }
        return datos;
    }*/
}