package com.blazejprzyluski.workout_tracker;

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
        if(this.kgs > 0 && this.reps > 0)
        {
            return this.kgs + "kg X " + this.reps;
        }
        else {
            return "";
        }
    }

    @Override
    public boolean equals(Object o)
    {
        if(this == o){return true;}
        if(o == null || this.getClass() != o.getClass()){return false;}
        Set s = (Set) o;
        return (this.kgs == ((Set) o).getKgs() && this.reps == ((Set) o).getReps());
    }
}
