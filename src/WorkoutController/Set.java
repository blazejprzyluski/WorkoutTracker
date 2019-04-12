package WorkoutController;

public class Set {
    private int kgs;
    private int reps;

    public Set(int kgs, int reps) {
        this.kgs = kgs;
        this.reps = reps;
    }

    public int getKgs() {
        return kgs;
    }

    public int getReps() {
        return reps;
    }

    @Override
    public String toString()
    {
        return this.kgs + "X" + this.reps;
    }
}
