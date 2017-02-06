package components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import testapi.PageInteractionHelper;

public class DatePicker extends PageInteractionHelper {

    public String SELECTED_MONTH_YEAR = "";
    public String SELECTED_MONTH = "";
    public String SELECTED_YEAR = "";

    /**
     * To be used for pages with more than one date picker
     */

    public DatePicker(WebDriver driver) {
        super(driver);
    }


    public void getSelectedMonthAndYear(String dateType) {
        try {
            SELECTED_MONTH_YEAR = driver.findElement(By.xpath(String.format("//datepicker-directive[@name='%s']//thead//strong", dateType))).getText();
            SELECTED_YEAR = SELECTED_MONTH_YEAR.substring(SELECTED_MONTH_YEAR.length() - 4);
            SELECTED_MONTH = SELECTED_MONTH_YEAR.substring(0, SELECTED_MONTH_YEAR.length() - 5);
        } catch (Exception npe) {
            npe.printStackTrace();
        }
    }

    public void setDate(String day, String month, Integer year, String dateType) {
        getSelectedMonthAndYear(dateType);
        By DATE_PICKER_SELECT_DAY = By.xpath(String.format("//datepicker-directive[@name='%s']//button/span[text()='%s']", dateType, day));
        By PREV = By.xpath(String.format("//datepicker-directive[@name='%s']//i[@class='glyphicon glyphicon-chevron-left']", dateType));
        By NEXT = By.xpath(String.format("//datepicker-directive[@name='%s']//i[@class='glyphicon glyphicon-chevron-right']", dateType));

        try {
            while ((year == Integer.parseInt(SELECTED_YEAR)) && (month.equals(SELECTED_MONTH) == false)) {
                clickOnElement(PREV);
                getSelectedMonthAndYear(dateType);
            }

            while ((year < Integer.parseInt(SELECTED_YEAR)) && (month.equals(SELECTED_MONTH) == false)) {
                clickOnElement(PREV);
                getSelectedMonthAndYear(dateType);
            }

            while ((year >= Integer.parseInt(SELECTED_YEAR)) && (month.equals(SELECTED_MONTH) == false)) {
                clickOnElement(NEXT);
                getSelectedMonthAndYear(dateType);
            }

            clickOnElement(DATE_PICKER_SELECT_DAY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
