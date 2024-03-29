package webapp.main;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class MainDate {
    public static void main(String[] args) {

        long star = System.currentTimeMillis();
        Date date = new Date();
        System.out.println(date + "(use Date = new Date)");
        System.out.println(System.currentTimeMillis() - star);
        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(TimeZone.getTimeZone("America/Los_Angeles"));
        System.out.println(cal.getTime() + "(use Calendar = new Calendar)");

        LocalDate ld = LocalDate.now();
        LocalTime lt = LocalTime.now();
        LocalDateTime ldt = LocalDateTime.of(ld, lt);
        System.out.println(ldt);

        SimpleDateFormat sdf = new SimpleDateFormat("YY/MM/dd");
        System.out.println(sdf.format(date));

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("YY/MM/dd");
        System.out.println(dtf.format(ldt));



    }
}
