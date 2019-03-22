package GUI;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class Monday extends Day {
    public Monday()
    {
        setDate(LocalDate.now());
        if(this.getDate().getDayOfWeek()!= DayOfWeek.MONDAY)
        {
            while(this.getDate().getDayOfWeek() != DayOfWeek.MONDAY)
            {
                for(int i = 1; i <= 6; i++)
                {
                    LocalDate date = this.getDate().minusDays(i);
                    if(date.getDayOfWeek() == DayOfWeek.MONDAY)
                    {
                        this.setDate(date);
                        break;
                    }
                }
            }
        }
    }

    @Override
    public String toString() {
        if(this.getWorkout() ==  null && !this.isWorkoutDay())
        {
            return "Normal day";
        }
        else
        {
            return this.getWorkout().getName();
        }
    }

}
