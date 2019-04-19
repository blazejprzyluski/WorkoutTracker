package WorkoutController;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.security.PrivilegedAction;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DataManagement {

    public static final String CREATE_DATABASE = "CREATE DATABASE WORKOUT_TRACKER";
    public static final String CONNECTION_STRING  = "jdbc:postgresql://localhost:5432/";
    public static final String CONNECTION_STRING_TO_DATABASE  = "jdbc:postgresql://localhost:5432/workout_tracker";

    public static final String WORKOUT_TABLE_NAME = "workout";
    public static final String WORKOUT_NAME = "name";
    public static final String WORKOUT_DATE = "date";
    public static final String WORKOUT_COMMENT = "comment";
    public static final String EXERCISE_TABLE_NAME = "exercise";
    public static final String EXERCISE_NAME = "name";
    public static final String EXERCISE_WORKOUT_ID = "workout_id";
    public static final String SET_TABLE_NAME = "set";
    public static final String SET_KGS = "kgs";
    public static final String SET_REPS = "reps";
    public static final String SET_EXERCISE_ID = "exercise_id";

    public static final String USERNAME = "postgres";
    public static final String PASSWORD = "postgres";
    public static final String CREATE_WORKOUT_TABLE = "CREATE TABLE IF NOT EXISTS WORKOUT(ID BIGSERIAL PRIMARY KEY NOT NULL,NAME VARCHAR(20) NOT NULL, DATE VARCHAR(20) NOT NULL, COMMENT VARCHAR(500))";
    public static final String CREATE_EXERCISE_TABLE = "CREATE TABLE IF NOT EXISTS EXERCISE(ID BIGSERIAL PRIMARY KEY NOT NULL, NAME VARCHAR(50) NOT NULL, WORKOUT_ID INT NOT NULL)";
    public static final String CREATE_SET_TABLE = "CREATE TABLE IF NOT EXISTS SET(ID BIGSERIAL PRIMARY KEY NOT NULL, KGS INT NOT NULL, REPS INT NOT NULL,EXERCISE_ID INT NOT NULL)";
    public static final String CHECK_IF_DB_EXISTS = "SELECT datname FROM pg_catalog.pg_database WHERE lower(datname) = lower('workout_tracker')";
    public static final String CHECK_IF_TABLE_EXISTS = "SELECT EXISTS(SELECT 1 FROM information_schema.tables WHERE table_schema = 'public' AND table_name = ";




    public static final String GET_WORKOUT_ID_QUERY = "SELECT id FROM WORKOUT WHERE " + WORKOUT_NAME + " = ? AND " + WORKOUT_DATE + " = ?";
    public static final String GET_EXERCISE_ID = "SELECT id FROM " + EXERCISE_TABLE_NAME + " WHERE " + EXERCISE_NAME + " = ?" + " AND " + EXERCISE_WORKOUT_ID + " = ?";

    public static final String ADD_WORKOUT_QUERY =
            "INSERT INTO " + WORKOUT_TABLE_NAME + " (" + WORKOUT_NAME + "," + WORKOUT_DATE + ")"
             + " VALUES (?,?)";
    public static final String ADD_EXERCISE_QUERY = "INSERT INTO " + EXERCISE_TABLE_NAME + " (" + EXERCISE_NAME + "," + EXERCISE_WORKOUT_ID + ")" + " VALUES (?,?)";
    public static final String ADD_SET_QUERY = "INSERT INTO " + SET_TABLE_NAME + " (" + SET_KGS + "," + SET_REPS + "," + SET_EXERCISE_ID + ") " + "VALUES (?,?,?)";
    public static final String ADD_COMMENT_QUERY = "UPDATE " + WORKOUT_TABLE_NAME + " SET " + WORKOUT_COMMENT + " = ? WHERE id = ?";
    public static final String UPDATE_WORKOUT = "UPDATE " + WORKOUT_TABLE_NAME + " SET " + WORKOUT_NAME + " = ? " + "," + WORKOUT_DATE + " = ? WHERE id = ?";
    public static final String DELETE_WORKOUT = "DELETE FROM " + WORKOUT_TABLE_NAME + " WHERE id = ?";
    public static final String DELETE_EXERCISE = "DELETE FROM " + EXERCISE_TABLE_NAME + " WHERE " + EXERCISE_WORKOUT_ID + " = ?";
    public static final String DELETE_SET = "DELETE FROM " + SET_TABLE_NAME + " WHERE " + SET_EXERCISE_ID + " = ?";
    public static final String GET_WORKOUT_NAMES = "SELECT " +WORKOUT_NAME +" FROM " + WORKOUT_TABLE_NAME;
    public static final String GET_WORKOUT_DATES = "SELECT " +WORKOUT_DATE +" FROM " + WORKOUT_TABLE_NAME;
    public static final String GET_WORKOUT_COMMENT = "SELECT " + WORKOUT_COMMENT + " FROM " + WORKOUT_TABLE_NAME;
    public static final String GET_EXERCISE_NAMES = "SELECT " + EXERCISE_NAME + " FROM " + EXERCISE_TABLE_NAME + " WHERE " + EXERCISE_WORKOUT_ID + " = ?";
    public static final String GET_SET_KGS = "SELECT " + SET_KGS + " FROM " + SET_TABLE_NAME + " WHERE " + SET_EXERCISE_ID + " = ?";
    public static final String GET_SET_REPS = "SELECT " + SET_REPS + " FROM " + SET_TABLE_NAME + " WHERE " + SET_EXERCISE_ID + " = ?";
    public static final String UPDATE_EXERCISE = "UPDATE " + EXERCISE_TABLE_NAME + " SET " + EXERCISE_NAME + " = ? WHERE id = ?";
    public static final String UPDATE_SET = "UPDATE " + SET_TABLE_NAME + " SET " + SET_KGS  + " = ? , " + SET_REPS  + " = ? WHERE id = ?";
    public static final String GET_SET_ID = "SELECT id FROM " + SET_TABLE_NAME + " WHERE " + SET_KGS + " = ? AND " + SET_REPS + " = ? AND " + SET_EXERCISE_ID + " = ?";



    private Connection conn;

    private PreparedStatement insertNewWorkout = null;
    private PreparedStatement getWorkoutID = null;
    private PreparedStatement insertNewExercise = null;
    private PreparedStatement insertNewSet = null;
    private PreparedStatement getExerciseID = null;
    private PreparedStatement updateComment = null;
    private PreparedStatement updateWorkout = null;
    private PreparedStatement deleteWorkout = null;
    private PreparedStatement deleteExercise = null;
    private PreparedStatement deleteSet = null;
    private PreparedStatement getExerciseNames = null;
    private PreparedStatement getSetKgs = null;
    private PreparedStatement getSetReps = null;
    private PreparedStatement updateExercise = null;
    private PreparedStatement updateSet = null;
    private PreparedStatement getSetID = null;


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

            insertNewWorkout =conn.prepareStatement(ADD_WORKOUT_QUERY);
            getWorkoutID = conn.prepareStatement(GET_WORKOUT_ID_QUERY);
            insertNewExercise = conn.prepareStatement(ADD_EXERCISE_QUERY);
            insertNewSet = conn.prepareStatement(ADD_SET_QUERY);
            getExerciseID = conn.prepareStatement(GET_EXERCISE_ID);
            updateComment = conn.prepareStatement(ADD_COMMENT_QUERY);
            updateWorkout = conn.prepareStatement(UPDATE_WORKOUT);
            deleteWorkout = conn.prepareStatement(DELETE_WORKOUT);
            deleteExercise = conn.prepareStatement(DELETE_EXERCISE);
            deleteSet = conn.prepareStatement(DELETE_SET);
            getExerciseNames = conn.prepareStatement(GET_EXERCISE_NAMES);
            getSetKgs = conn.prepareStatement(GET_SET_KGS);
            getSetReps = conn.prepareStatement(GET_SET_REPS);
            updateExercise= conn.prepareStatement(UPDATE_EXERCISE);
            updateSet = conn.prepareStatement(UPDATE_SET);
            getSetID = conn.prepareStatement(GET_SET_ID);

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

            if(getWorkoutID != null)
            {
                getWorkoutID.close();
            }
            if(insertNewExercise != null)
            {
                insertNewExercise.close();
            }
            if(insertNewSet != null)
            {
                insertNewSet.close();
            }
            if(getExerciseID != null)
            {
                getExerciseID.close();
            }
            if(updateComment != null)
            {
                updateComment.close();
            }
            if(updateWorkout != null)
            {
                updateWorkout.close();
            }
            if(deleteWorkout != null)
            {
                deleteWorkout.close();
            }
            if(deleteExercise != null)
            {
                deleteExercise.close();
            }
            if(deleteSet != null)
            {
                deleteSet.close();
            }
            if(getExerciseNames != null)
            {
                getExerciseNames.close();
            }
            if(getSetReps != null)
            {
                getSetReps.close();
            }
            if(getSetKgs != null)
            {
                getSetKgs.close();
            }

            if(updateSet != null)
            {
                updateSet.close();
            }

            if(updateExercise != null)
            {
                updateExercise.close();
            }

            if(getSetID != null)
            {
                getSetID.close();
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
        insertNewWorkout.executeUpdate();
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


    public int getWorkoutId(String name, String date)
    {
        try{
            int help;
            getWorkoutID.setString(1,name);
            getWorkoutID.setString(2,date);
            ResultSet resultSet = getWorkoutID.executeQuery();

            while(resultSet.next())
            {
                return resultSet.getInt(1);
            }
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }

        return 0;
    }

    public void createExercise(String name, int workoutID)
    {
        try{
            insertNewExercise.setString(1,name);
            insertNewExercise.setInt(2,workoutID);
            insertNewExercise.executeUpdate();
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void createSet(int kgs, int reps, int exercise_id)
    {
        try
        {
            insertNewSet.setInt(1,kgs);
            insertNewSet.setInt(2,reps);
            insertNewSet.setInt(3,exercise_id);
            insertNewSet.executeUpdate();
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public int getExerciseID(String name, int workout_id)
    {
        try{
            getExerciseID.setString(1,name);
            getExerciseID.setInt(2,workout_id);
            ResultSet r = getExerciseID.executeQuery();
            if(r.next())
            {
                return r.getInt(1);
            }
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    public void addComment(String comment, int workout_id)
    {
        try{
            updateComment.setString(1,comment);
            updateComment.setInt(2,workout_id);
            updateComment.executeUpdate();
        }catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void updateWorkout(String name, String date, int workout_id)
    {
        try{
            updateWorkout.setString(1,name);
            updateWorkout.setString(2,date);
            updateWorkout.setInt(3,workout_id);
            updateWorkout.executeUpdate();
        }catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void deleteWorkout(int id)
    {
        try
        {
            deleteWorkout.setInt(1,id);
            deleteWorkout.executeUpdate();
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void deleteExercise(int workout_id)
    {
        try
        {
            deleteExercise.setInt(1,workout_id);
            deleteExercise.executeUpdate();
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void deleteSet(int exercise_id)
    {
        try
        {
            deleteSet.setInt(1,exercise_id);
            deleteSet.executeUpdate();
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }

    private List<String> helperGetMethod(List a,String sql)
    {
        try(Statement s  =conn.createStatement())
        {
            ResultSet res = s.executeQuery(sql);
            while(res.next())
            {
                a.add(res.getString(1));
            }
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return a;
    }

    public List<String> getWorkoutDates()
    {
        List<String> dates = new ArrayList<>();

        return helperGetMethod(dates,GET_WORKOUT_DATES);
    }

    public List<String> getWorkoutNames()
    {
        List<String> names = new ArrayList<>();

        return helperGetMethod(names,GET_WORKOUT_NAMES);
    }

    public List<String> getWorkoutComments()
    {
        List<String> comments = new ArrayList<>();

        return helperGetMethod(comments,GET_WORKOUT_COMMENT);
    }

    public List<String> getExerciseNames(int workout_id)
    {
        List<String> names = new ArrayList<>();
        try
        {
            getExerciseNames.setInt(1,workout_id);
            ResultSet res = getExerciseNames.executeQuery();

            while(res.next())
            {
                names.add(res.getString(1));
            }
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }

        return names;
    }

    private List<Integer> setHelper(PreparedStatement ps, int index)
    {
        List<Integer> a = new ArrayList<>();
        try
        {
            ps.setInt(1,index);
            ResultSet res = ps.executeQuery();

            while(res.next())
            {
                a.add(res.getInt(1));
            }
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return a;
    }

    public List<Integer> getSetKgs(int exercise_id)
    {
        return setHelper(getSetKgs,exercise_id);
    }

    public List<Integer> getSetReps(int exercise_id)
    {
        return setHelper(getSetReps,exercise_id);
    }

    public ObservableList<Workout> createWorkouts()
    {
        ObservableList<Workout> lista = FXCollections.observableArrayList();
        List<String> workoutNames = getWorkoutNames();
        List<String> workoutDates = getWorkoutDates();
        List<String> workoutComments = getWorkoutComments();

        for(int i  = 0; i < workoutNames.size();i++)
        {
            String name = workoutNames.get(i);
            LocalDate date = LocalDate.parse(workoutDates.get(i));
            int workout_id = getWorkoutId(name,date.toString());
            String comment = "";
            if(workoutComments.get(i) != null)
            {
               comment = workoutComments.get(i);
            }
            Workout w = new Workout(name,date);
            w.setComment(comment);
            w.setExercises(createExercises(workout_id));
            lista.add(w);
        }
        return lista;
    }

    private ObservableList<Exercise> createExercises(int workout_id)
    {
        ObservableList<Exercise> lista = FXCollections.observableArrayList();
        List<String> names = getExerciseNames(workout_id);
        for(int i = 0; i < names.size();i++)
        {
            String name = names.get(i);
            int id = getExerciseID(name,workout_id);
            ObservableList<Set> sets = createSets(id);
            Exercise e = new Exercise(name);
            e.addReps(sets);
            lista.add(e);
        }

        return lista;
    }

    private ObservableList<Set> createSets(int exercise_id)
    {
        ObservableList<Set> lista = FXCollections.observableArrayList();
        List<Integer> kgs = getSetKgs(exercise_id);
        List<Integer> reps = getSetReps(exercise_id);

        for(int i = 0; i < kgs.size();i++)
        {
            Set s = new Set(kgs.get(i),reps.get(i));
            lista.add(s);
        }
        return lista;
    }

    public void updateExercise(String name, int exercise_id)
    {
        try{
            updateExercise.setString(1,name);
            updateExercise.setInt(2,exercise_id);
            updateExercise.executeUpdate();
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }



    public int getSetID(int kgs, int reps, int exercise_id)
    {
        int id = 0;
        try{
            getSetID.setInt(1,kgs);
            getSetID.setInt(2,reps);
            getSetID.setInt(3,exercise_id);

            ResultSet set = getSetID.executeQuery();

            if(set.next())
            {
                id = set.getInt(1);
            }
            return id;

        }
        catch (SQLException e)
        {
            e.getMessage();
        }

        return id;
    }

    public void updateSet(int kgs, int reps, int id)
    {
        try{
            updateSet.setInt(1,kgs);
            updateSet.setInt(2,reps);
            updateSet.setInt(3,id);
            updateSet.executeUpdate();
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }

}
