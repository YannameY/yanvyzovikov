import org.junit.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.practicum.yandex.main.pages.LandingPageScooter;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;
import static ru.practicum.yandex.main.pages.LandingPageScooter.BASE_URL;

@RunWith(Parameterized.class)
public class TestTextChecking {

    private static WebDriver driver;
    private final String expected;
    private final int index;

    public TestTextChecking(String expected, int index) {
        this.index = index;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Object[][] getListData() {
        return new Object[][]{
                {"Сутки — 400 рублей. Оплата курьеру — наличными или картой.", 0},
                {"Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.", 1},
                {"Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.", 2},
                {"Только начиная с завтрашнего дня. Но скоро станем расторопнее.", 3},
                {"Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.", 4},
                {"Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.", 5},
                {"Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.", 6},
                {"Да, обязательно. Всем самокатов! И Москве, и Московской области.", 7}
        };
    }

    @Before
    public void setup() {
        driver = new ChromeDriver();
        //driver = new FirefoxDriver();
        driver.manage().window().maximize();
        LandingPageScooter landingPageScooter = new LandingPageScooter(driver);
        driver.get(BASE_URL);
        landingPageScooter.clickButtonCookie();
        landingPageScooter.scrollToLanding();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

    }

    @Test
    public void testAccordionDropdown() {
        LandingPageScooter.clickClosedDropdownByIndex(index);
        String actualText = LandingPageScooter.getTextFromTextInputByIndex(index);
        assertEquals("Неверный ожидаемый текст", expected, actualText);
    }

    @After
    public void teardown() {
        // Закрыть браузер
        driver.quit();
    }
}
