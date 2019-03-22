package GUI;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class Sunday extends Day{
    public Sunday()
    {
        setDate(LocalDate.now());
        if(this.getDate().getDayOfWeek()!= DayOfWeek.SUNDAY)
        {
            for(int j = 1; j <= 6;j++)
            {
                LocalDate date = this.getDate().plusDays(j);
                if(date.getDayOfWeek() == DayOfWeek.SUNDAY)
                {
                    this.setDate(date);
                    break;
                }
            }

        }
    }
}
