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

public class LogoutTest extends BrowserFactory {
    private String accessToken;
    private Client client;


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
    @DisplayName("Выход из аккаунта в личном кабинете")
    @Description("Тест проверяет функционал выхода из аккаунта через личный кабинет")
    public void logoutFromProfilePageTest() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        ProfilePage profilePage = new ProfilePage(driver);
        mainPage.clickAccountButton();
        loginPage.setClientLoginData(client.getEmail(), client.getPassword());
        loginPage.clickLoginButton();
        mainPage.clickAccountButton();
        profilePage.clickLogoutButton();
        String text = loginPage.getEnterLabelText();
        Assert.assertEquals("Вход", text);
    }

    @After
    public void tearDown() {
        // Закрываем драйвер
        driver.quit();
        // Если токен доступа был получен, удаляем пользователя
        if (accessToken != null) {
            ClientSteps.deleteClient(accessToken);
        }
    }
}