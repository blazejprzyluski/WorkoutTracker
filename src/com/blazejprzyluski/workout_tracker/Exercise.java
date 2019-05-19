package com.blazejprzyluski.workout_tracker;

import javafx.collections.ObservableList;

import java.util.Objects;

public class Exercise{
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
        if(e.size() > 2)
        {
            if (e.get(0) != null && e.get(1) != null && e.get(2) !=null)
            {
                this.set1 = e.get(0).toString();
                this.set2 = e.get(1).toString();
                this.set3 = e.get(2).toString();
            }else if (e.get(0) != null && e.get(1) != null)
            {
                this.set1 = e.get(0).toString();
                this.set2 = e.get(1).toString();
            }
            else if (e.get(0) != null)
            {
                this.set1 = e.get(0).toString();
            }
        }
        else if(e.size() > 1)
        {
            if (e.get(0) != null && e.get(1) != null)
            {
                this.set1 = e.get(0).toString();
                this.set2 = e.get(1).toString();
            }
        }
        else if(e.size() > 0)
        {
            if (e.get(0) != null)
            {
                this.set1 = e.get(0).toString();
            }
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

    public ObservableList<Set> getReps() {
        return reps;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Exercise exercise = (Exercise) o;
        return name.equals(exercise.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public void setName(String name)
    {
        this.name = name;
    }
}
