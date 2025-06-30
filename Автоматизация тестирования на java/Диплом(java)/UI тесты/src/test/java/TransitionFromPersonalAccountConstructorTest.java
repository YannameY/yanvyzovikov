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
import pages.LoginPage;
import pages.MainPage;
import pages.ProfilePage;

import java.util.concurrent.TimeUnit;

public class TransitionFromPersonalAccountConstructorTest extends BrowserFactory {
    private Client client;
    private String accessToken;

    @Before
    public void setUp() {
        driver = BrowserFactory.createDriver(BrowserFactory.choice);
        driver.get(ClientSteps.baseURL);
        RestAssured.baseURI = ClientSteps.baseURL;
        client = ClientFaker.getRandomClient();
        accessToken = ClientSteps.createNewClient(client).then().extract().path("accessToken");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор по клику на «Конструктор»")
    @Description("Тест проверяет переход из личного кабинета в конструктор бургеров "
            + "через кнопку 'Конструктор'")
    public void profileToConstructorFromButtonTest() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        ProfilePage profilePage = new ProfilePage(driver);
        mainPage.clickAccountButton();
        loginPage.setClientLoginData(client.getEmail(), client.getPassword());
        loginPage.clickLoginButton();
        mainPage.clickAccountButton();
        profilePage.clickConstructorButton();
        String text = mainPage.getCreateOrderButtonText();
        Assert.assertEquals("Оформить заказ", text);
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор по клику на логотип Stellar Burgers")
    @Description("Тест проверяет переход из личного кабинета в конструктор бургеров "
            + "через клик по логотипу Stellar Burgers")
    public void profileToConstructorFromLogoTest() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        ProfilePage profilePage = new ProfilePage(driver);
        mainPage.clickAccountButton();
        loginPage.setClientLoginData(client.getEmail(), client.getPassword());
        loginPage.clickLoginButton();
        mainPage.clickAccountButton();
        profilePage.clickLogoButton();
        String text = mainPage.getCreateOrderButtonText();
        Assert.assertEquals("Оформить заказ", text);
    }

    @After
    public void tearDown() {
        driver.quit();
        if (accessToken != null) {
            ClientSteps.deleteClient(accessToken);
        }
    }
}