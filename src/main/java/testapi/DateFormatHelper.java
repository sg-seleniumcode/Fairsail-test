package testapi;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.openqa.selenium.WebDriver;

import java.util.Locale;

public class DateFormatHelper extends PageInteractionHelper {
    public DateFormatHelper(WebDriver driver) {
        super(driver);
    }

    public String getDay(String inputDate) {
        DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy")
                .withLocale(Locale.UK);

        LocalDate date = formatter.parseLocalDate(inputDate);

        return String.valueOf(date.getDayOfMonth());
    }

    public String getMonth(String inputDate) {
        DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy")
                .withLocale(Locale.UK);

        LocalDate date = formatter.parseLocalDate(inputDate);

        return date.monthOfYear().getAsText();
    }

    public Integer getYear(String inputDate) {
        DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy")
                .withLocale(Locale.UK);

        LocalDate date = formatter.parseLocalDate(inputDate);

        return date.getYear();
    }

}
