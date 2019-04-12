package WorkoutController;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Exercise {
    private String name;
    private ObservableList<Set> reps;
    private String set1;
    private String set2;
    private String set3;

    public Exercise(String name)
    {
        this.name = name;
        this.set1 = "";
        this.set2 = "";
        this.set3 = "";
    }

    public void addReps(ObservableList<Set> e)
    {
        this.reps = e;
        if (e.get(0) != null && e.get(1) != null && e.get(2) !=null)
        {
            this.set1 = e.get(0).toString();
            this.set2 = e.get(1).toString();
            this.set3 = e.get(2).toString();
        }
        else if (e.get(0) != null && e.get(1) != null)
        {
            this.set1 = e.get(0).toString();
            this.set2 = e.get(1).toString();
        }
        else if (e.get(0) != null)
        {
            this.set1 = e.get(0).toString();
        }

    }
    @Override
    public String toString()
    {
        return  this.reps + "";
    }

    public String getSet1() {
        return set1;
    }

    public String getSet2() {
        return set2;
    }

    public String getSet3() {
        return set3;
    }

    public String getName()
    {
        return this.name;
    }
}
