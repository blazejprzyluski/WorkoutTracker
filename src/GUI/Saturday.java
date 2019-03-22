package GUI;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class Saturday extends Day{
    public Saturday()
    {
        setDate(LocalDate.now());
        if(this.getDate().getDayOfWeek()!= DayOfWeek.SATURDAY)
        {
            for(int j = 1; j <= 5;j++)
            {
                LocalDate date = this.getDate().plusDays(j);
                if(date.getDayOfWeek() == DayOfWeek.SATURDAY)
                {
                    this.setDate(date);
                    break;
                }
            }
            for(int i = 1; i <= 1; i++)
            {
                LocalDate date = this.getDate().minusDays(i);
                if(date.getDayOfWeek() == DayOfWeek.SATURDAY)
                {
                    this.setDate(date);
                    break;
                }
            }

        }
    }
}
