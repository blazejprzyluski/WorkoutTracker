package Data.workoutData;

import java.util.ArrayList;
import java.util.List;

public class Exercise {
    private String name;
    private String comment;
    private List<Series> series;

    public Exercise(String name)
    {
        this.name = "";
        this.comment = "";
        this.series = new ArrayList<>();
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<Series> getSeries() {
        return series;
    }

    public void addSeries(int kgs, int reps)
    {
        if(kgs != 0 && reps != 0 && kgs > 0 && reps > 0)
        {
            Series series = new Series(kgs,reps);
        }

    }

    public void setSeries(List<Series> series) {
        this.series = series;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
