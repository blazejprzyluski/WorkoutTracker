package GUI;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class Thursday extends Day {
    public Thursday()
    {
        setDate(LocalDate.now());
        if(this.getDate().getDayOfWeek()!= DayOfWeek.THURSDAY)
        {
            for(int j = 1; j <= 3;j++)
            {
                LocalDate date = this.getDate().plusDays(j);
                if(date.getDayOfWeek() == DayOfWeek.THURSDAY)
                {
                    this.setDate(date);
                    break;
                }
            }
            for(int i = 1; i <= 3; i++)
            {
                LocalDate date = this.getDate().minusDays(i);
                if(date.getDayOfWeek() == DayOfWeek.THURSDAY)
                {
                    this.setDate(date);
                    break;
                }
            }

        }
    }
}
