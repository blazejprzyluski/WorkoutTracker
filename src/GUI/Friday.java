package GUI;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class Friday extends Day{
    public Friday()
    {
        setDate(LocalDate.now());
        if(this.getDate().getDayOfWeek()!= DayOfWeek.FRIDAY)
        {
            for(int j = 1; j <= 4;j++)
            {
                LocalDate date = this.getDate().plusDays(j);
                if(date.getDayOfWeek() == DayOfWeek.FRIDAY)
                {
                    this.setDate(date);
                    break;
                }
            }
            for(int i = 1; i <= 2; i++)
            {
                LocalDate date = this.getDate().minusDays(i);
                if(date.getDayOfWeek() == DayOfWeek.FRIDAY)
                {
                    this.setDate(date);
                    break;
                }
            }

        }
    }
}
