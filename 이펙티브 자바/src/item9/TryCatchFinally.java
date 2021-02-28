package item9;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TryCatchFinally
{
    private static final String DB_URL      = "";
    private static final String DB_USER     = "";
    private static final String DB_PASSWORD = "";
    
    private static final String SQL = "";
    
    public void add(String string)
    {
        Connection        connection        = null;
        PreparedStatement preparedStatement = null;
        
        try {
            connection        = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.executeUpdate( );
        }
        catch (SQLException exception) {
            exception.printStackTrace( );
        }
        finally {
            try {
                preparedStatement.close( );
            }
            catch (SQLException e) {
                e.printStackTrace( );
            }
            try {
                connection.close( );
            }
            catch (SQLException e) {
                e.printStackTrace( );
            }
        }
    }
}
