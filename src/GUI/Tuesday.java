package GUI;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class Tuesday extends Day {
    public Tuesday()
    {
        setDate(LocalDate.now());
        if(this.getDate().getDayOfWeek()!= DayOfWeek.TUESDAY)
        {
            if(LocalDate.now().plusDays(1).getDayOfWeek() == DayOfWeek.TUESDAY)
            {
                setDate(LocalDate.now().plusDays(1));
            }else{
                for(int i = 1; i <= 5; i++)
                {
                    LocalDate date = this.getDate().minusDays(i);
                    if(date.getDayOfWeek() == DayOfWeek.TUESDAY)
                    {
                        this.setDate(date);
                        break;
                    }
                }
            }

        }
    }

}
