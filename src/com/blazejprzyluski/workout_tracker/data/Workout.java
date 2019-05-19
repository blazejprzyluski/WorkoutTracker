package com.blazejprzyluski.workout_tracker.data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;

public class Workout {
    private String name;
    private LocalDate date;
    private ObservableList<Exercise> exercises;
    private String comment;

    public Workout()
    {
        this.name = "";
        this.date = null;
        this.exercises = FXCollections.observableArrayList();
        this.comment = "";
    }

    public Workout(String name, LocalDate date)
    {
        this.name  = name;
        this.date = date;
        this.exercises = FXCollections.observableArrayList();
        this.comment = "";
    }

    public void setComment(String comment)
    {
        this.comment = comment;
    }

    public String nameDate()
    {
        return this.name + " " +  this.date.toString();
    }

    public void setExercises(ObservableList<Exercise> e)
    {
        this.exercises = e;
    }

    public ObservableList<Exercise> getExercises()
    {
        return this.exercises;
    }

    @Override
    public String toString()
    {
        return this.name + "  " + this.date.toString() + "\t" + this.comment;
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String s)
    {
        this.name = s;
    }

    public LocalDate getDate()
    {
        return this.date;
    }

    public void setDate(LocalDate date)
    {
        this.date = date;
    }

    public String getComment()
    {
        return this.comment;
    }
}
