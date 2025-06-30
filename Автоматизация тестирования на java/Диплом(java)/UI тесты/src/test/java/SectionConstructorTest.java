import client.Client;
import client.ClientFaker;
import client.ClientSteps;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pages.MainPage;

import java.util.concurrent.TimeUnit;

public class SectionConstructorTest extends BrowserFactory {
    private Client client;
    private String accessToken;

    @Before
    public void setUp() {
        driver = BrowserFactory.createDriver(BrowserFactory.choice);
        driver.get(ClientSteps.baseURL);
        RestAssured.baseURI = ClientSteps.baseURL;
        client = ClientFaker.getRandomClient();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    @DisplayName("Переход к разделу Булки на главной странице")
    @Description("Тест проверяет переход в раздел 'Булки' через конструктор бургеров")
    public void bunSectionTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickSauceButton();
        mainPage.clickBunButton();
        String text = mainPage.getMenuTabLocator();
        Assert.assertEquals("Булки", text);
    }

    @Test
    @DisplayName("Переход к разделу Соусы на главной странице")
    @Description("Тест проверяет переход в раздел 'Соусы' через конструктор бургеров")
    public void sauceSectionTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickSauceButton();
        String text = mainPage.getMenuTabLocator();
        Assert.assertEquals("Соусы", text);
    }

    @Test
    @DisplayName("Переход к разделу Начинки на главной странице")
    @Description("Тест проверяет переход в раздел 'Начинки' через конструктор бургеров")
    public void fillingSectionTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickFillingButton();
        String text = mainPage.getMenuTabLocator();
        Assert.assertEquals("Начинки", text);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}