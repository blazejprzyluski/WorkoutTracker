package GUI;

import GUI.Data.workoutData.Workout;

import java.time.LocalDate;

public class Day {
    private LocalDate date;
    private boolean isWorkoutDay = false;
    private Workout workout;
    private String workoutData = "xD";

    public void addWorkout(Workout workout) {
        if(workout != null)
        {
            this.workout = workout;
        }

        this.isWorkoutDay = true;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date)
    {
        this.date=date;
    }

    public boolean isWorkoutDay() {
        return isWorkoutDay;
    }

    public void setWorkoutDay(boolean workoutDay) {
        isWorkoutDay = workoutDay;
    }

    public Workout getWorkout() {
        return workout;
    }

    public void setWorkout(Workout workout) {
        this.workout = workout;
    }

    public String getWorkoutData() {
        return workoutData;
    }

    public void setWorkoutData(String workoutData) {
        this.workoutData = workoutData;
    }
}
