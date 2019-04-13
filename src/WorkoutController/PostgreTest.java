package WorkoutController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class PostgreTest {

    public static void main(String args[]) {

        DataManagement.getInstance().open();
//        try{
//            DataManagement.getInstance().addWorkoutToDatabase("chuj","2019-04-10");
//        }catch (SQLException e) {
//            e.printStackTrace();
//        }

        DataManagement.getInstance().close();
    }
    }

