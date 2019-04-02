package GUI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Exercise {
    private String name;
    private String comment;
    private ObservableList<Series> series;

    public Exercise(String name)
    {
        this.name = name;
        this.comment = "stub";
        this.series = FXCollections.observableArrayList();
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public ObservableList<Series> getSeries() {
        return series;
    }

    public void addSeries(int kgs, int reps, int setNumber)
    {
        if(kgs != 0 && reps != 0 && kgs > 0 && reps > 0)
        {
            Series set = new Series(kgs,reps,setNumber);
            series.add(set);
        }

    }

    public void setSeries(ObservableList<Series> series) {
        this.series = series;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return this.name;
    }
}
