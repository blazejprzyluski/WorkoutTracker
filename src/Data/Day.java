package Data;

import Data.workoutData.Workout;

import java.time.LocalDate;

public class Day {
    private LocalDate date;
    private boolean isWorkoutDay = false;
    private Workout workout;

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

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
