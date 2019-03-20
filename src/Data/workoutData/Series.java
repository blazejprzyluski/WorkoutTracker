package Data.workoutData;

public class Series {
    private int kgs;
    private int reps;

    public Series(int kgs, int reps) {
        this.kgs = kgs;
        this.reps = reps;
    }

    public void setKgs(int kgs) {
        this.kgs = kgs;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }
}
