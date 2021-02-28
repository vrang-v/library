package item9;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TryWithResources
{
    private static final String DB_URL      = "";
    private static final String DB_USER     = "";
    private static final String DB_PASSWORD = "";
    
    private static final String SQL = "";
    
    public void add(String string)
    {
        try (
                var connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                var preparedStatement = connection.prepareStatement(SQL)
        ) {
            preparedStatement.executeUpdate( );
        }
        catch (SQLException exception) {
            exception.printStackTrace( );
        }
    }
    
    @SneakyThrows
    public void java9( )
    {
        var bufferedReader1 = new BufferedReader(new FileReader("C:\\number.txt"));
        var bufferedReader2 = new BufferedReader(new FileReader("C:\\abc.txt"));
        try (bufferedReader1; bufferedReader2) {
        
        }
        catch (IOException e) {
            e.printStackTrace( );
        }
    }
}
