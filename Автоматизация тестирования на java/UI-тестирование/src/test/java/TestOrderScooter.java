import org.openqa.selenium.firefox.FirefoxDriver;
import ru.practicum.yandex.main.pages.OrderPageScooter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static ru.practicum.yandex.main.pages.LandingPageScooter.BASE_URL;


@RunWith(Parameterized.class)
public class TestOrderScooter {

    private final String username;
    private final String lastName;
    private final String address;
    private final String phoneNumber;
    private final String metroStation;
    private final String deliveryDate;
    private final String comment;

    public TestOrderScooter(String username, String lastName, String address, String phoneNumber, String metroStation, String deliveryDate, String comment) {
        this.username = username;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.metroStation = metroStation;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] getOrderData() {
        return new Object[][]{
                {"Андрей", "Понпон", "Большой Патриарший пер., 7", "87771232233", "Пушкинская", "06.04.2025", "как можно быстрее"},
                {"Ксения", "Понпон", "майями", "79993331122", "Пушкинская", "10.04.2025", "мне надо срочно!"},
                {"Варвара", "Китаева", "Санкт-Петербург", "79991236621", "Пушкинская", "31.12.2024", "уже не надо"},
        };
    }

    private static WebDriver driver;

    @Before
    public void setup() {
        driver = new ChromeDriver();
        //driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get(BASE_URL);
        OrderPageScooter orderPageScooter = new OrderPageScooter(driver);
        orderPageScooter.clickButtonCookie();



    }

    @Test
    public void testOrderButtonOne() {
        OrderPageScooter orderPageScooter = new OrderPageScooter(driver);
        orderPageScooter.clickButtonOrderOne();
        orderPageScooter.orderUser(username, lastName, address, metroStation, phoneNumber, deliveryDate, comment);
        orderPageScooter.assertOrderDoneTextVisible();

    }

    @Test
    public void testOrderButtonTwo() throws InterruptedException {
        OrderPageScooter orderPageScooter = new OrderPageScooter(driver);
        orderPageScooter.scrollToLandingButtonOrder();
        orderPageScooter.clickButtonOrderTwo();
        orderPageScooter.orderUserTwo(username, lastName, address, metroStation, phoneNumber, deliveryDate, comment);
        orderPageScooter.assertOrderDoneTextVisible();

    }



    @After
    public void teardown() {
        // Закрыть браузер
        driver.quit();
    }

}
