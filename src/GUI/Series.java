package GUI;

public class Series {
    private int kgs;
    private int reps;
    public int setNumber;



    public Series(int kgs, int reps, int setNumber) {
        this.kgs = kgs;
        this.reps = reps;
        this.setNumber = setNumber;
    }

    public void setKgs(int kgs) {
        this.kgs = kgs;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public int getSetNumber() {
        return setNumber;
    }

    public void setSetNumber(int setNumber) {
        this.setNumber = setNumber;
    }

    public int getKgs() {
        return kgs;
    }

    public int getReps() {
        return reps;
    }
}