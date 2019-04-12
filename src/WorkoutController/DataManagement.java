package WorkoutController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DataManagement {

    public static final String WORKOUT_TABLE_NAME = "workout";
    public static final String WORKOUT_NAME = "name";
    public static final String WORKOUT_DATE = "data";

    public static final String ADD_WORKOUT_QUERY =
            "INSERT INTO " + WORKOUT_TABLE_NAME + " (ID, " + WORKOUT_NAME + " " + WORKOUT_DATE + ")"
             + " VALUES (";

    public void addWorkoutToDatabase()
    {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/test",
                            "postgres", "postgres");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "INSERT INTO workout (ID,NAME,DATA) "
                    + "VALUES (1, 'nogi', '2019-04-12');";
            stmt.executeUpdate(sql);


            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        System.out.println("Records created successfully");
    }
}
