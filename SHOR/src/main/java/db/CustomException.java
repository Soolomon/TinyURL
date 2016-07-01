package db;

import java.sql.SQLException;

public class CustomException extends RuntimeException {
    public CustomException()
    {
        super("Err SQL");
    }
}
