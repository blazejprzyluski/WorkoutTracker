package GUI;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class Wednesday  extends Day{
    public Wednesday()
    {
        setDate(LocalDate.now());
        if(this.getDate().getDayOfWeek()!= DayOfWeek.WEDNESDAY)
        {
            for(int j = 1; j <= 2;j++)
            {
                LocalDate date = this.getDate().plusDays(j);
                if(date.getDayOfWeek() == DayOfWeek.WEDNESDAY)
                {
                    this.setDate(date);
                    break;
                }
            }
            for(int i = 1; i <= 4; i++)
            {
                LocalDate date = this.getDate().minusDays(i);
                if(date.getDayOfWeek() == DayOfWeek.WEDNESDAY)
                {
                    this.setDate(date);
                    break;
                }
            }

        }
    }
}
