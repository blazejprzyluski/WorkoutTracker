package WorkoutController;

import java.sql.*;

public class DataManagement {

    public static final String CREATE_DATABASE = "CREATE DATABASE WORKOUT_TRACKER";
    public static final String CONNECTION_STRING  = "jdbc:postgresql://localhost:5432/";
    public static final String CONNECTION_STRING_TO_DATABASE  = "jdbc:postgresql://localhost:5432/workout_tracker";

    public static final String USERNAME = "postgres";
    public static final String PASSWORD = "postgres";
    public static final String CREATE_WORKOUT_TABLE = "CREATE TABLE IF NOT EXISTS WORKOUT(ID BIGSERIAL PRIMARY KEY NOT NULL,NAME VARCHAR(20) NOT NULL, DATE VARCHAR(20) NOT NULL)";
    public static final String CREATE_EXERCISE_TABLE = "CREATE TABLE IF NOT EXISTS EXERCISE(ID BIGSERIAL PRIMARY KEY NOT NULL, NAME VARCHAR(50) NOT NULL, WORKOUT_ID INT NOT NULL)";
    public static final String CREATE_SET_TABLE = "CREATE TABLE IF NOT EXISTS SET(ID BIGSERIAL PRIMARY KEY NOT NULL, KGS INT NOT NULL, REPS INT NOT NULL,EXERCISE_ID INT NOT NULL)";
    public static final String CHECK_IF_DB_EXISTS = "SELECT datname FROM pg_catalog.pg_database WHERE lower(datname) = lower('workout_tracker')";
    public static final String CHECK_IF_TABLE_EXISTS = "SELECT EXISTS(SELECT 1 FROM information_schema.tables WHERE table_schema = 'public' AND table_name = ";

    public static final String WORKOUT_TABLE_NAME = "workout";
    public static final String WORKOUT_NAME = "name";
    public static final String WORKOUT_DATE = "date";

    public static final String ADD_WORKOUT_QUERY =
            "INSERT INTO " + WORKOUT_TABLE_NAME + " (" + WORKOUT_NAME + "," + WORKOUT_DATE + ")"
             + " VALUES (?,?)";


    private Connection conn;

    private PreparedStatement insertNewWorkout = null;

    ///making a singleton
    private static DataManagement instance = new DataManagement();

    private DataManagement(){}

    public static DataManagement getInstance(){return instance;}

    public boolean open()
    {
        try{
            conn = DriverManager.getConnection(CONNECTION_STRING,USERNAME,PASSWORD);
            createDatabase();
            conn = DriverManager.getConnection(CONNECTION_STRING_TO_DATABASE,USERNAME,PASSWORD);
            createTables();
            insertNewWorkout = conn.prepareStatement(ADD_WORKOUT_QUERY);
            return true;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    public void close()
    {
        try{
            if(insertNewWorkout != null)
            {
                insertNewWorkout.close();
            }

            if(conn != null)
            {
                conn.close();
            }
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    private void createDatabase()
    {
        if(!checkIfDBExists())
        {
            try(Statement statement = conn.createStatement())
            {
                statement.executeUpdate(CREATE_DATABASE);
            }catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }

    private void createTables()
    {
        try(Statement statement = conn.createStatement()) {
            statement.executeUpdate(CREATE_WORKOUT_TABLE);
            statement.executeUpdate(CREATE_EXERCISE_TABLE);
            statement.executeUpdate(CREATE_SET_TABLE);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void addWorkoutToDatabase(String name, String date) throws SQLException
    {
        insertNewWorkout.setString(1,name);
        insertNewWorkout.setString(2,date);
        insertNewWorkout.executeQuery();
    }

    private boolean checkIfDBExists()
    {
        try {
            Statement statement = conn.createStatement();
            ResultSet r = statement.executeQuery(CHECK_IF_DB_EXISTS);
            return r.next();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    private boolean checkIfTableExists()
    {
        boolean checker1 = true;
        boolean checker2 = true;
        boolean checker3 = true;
        boolean sum = true;
        try{
            Statement s = conn.createStatement();
            ResultSet r = s.executeQuery(CHECK_IF_TABLE_EXISTS + "'workout')");
            r.next();
            checker1 = r.getString(1).equals("t");
             r = s.executeQuery(CHECK_IF_TABLE_EXISTS + "'exercise')");
            r.next();
            checker2 = r.getString(1).equals("t");
            r = s.executeQuery(CHECK_IF_TABLE_EXISTS + "'set')");
            r.next();
            checker3 = r.getString(1).equals("t");
            if(checker1 == false || checker2 == false || checker3 == false)
            {
                sum = false;
            }
            return sum;

        }catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }


}
