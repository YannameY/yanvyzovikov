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
import pages.ForgotPasswordPage;
import pages.LoginPage;
import pages.MainPage;
import pages.RegisterPage;

import java.util.concurrent.TimeUnit;

public class LoginTest extends BrowserFactory{
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
    @DisplayName("Вход по кнопке «Войти в аккаунт» на главной")
    @Description("Тест проверяет авторизацию пользователя через кнопку 'Войти в аккаунт' на главной странице. "
            + "Ожидаемый результат: после успешной авторизации отображается кнопка 'Оформить заказ'")
    public void loginFromMainPageTest() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        mainPage.clickLoginButton();
        loginPage.setClientLoginData(client.getEmail(), client.getPassword());
        loginPage.clickLoginButton();
        String text = mainPage.getCreateOrderButtonText();
        Assert.assertEquals("Оформить заказ", text);
    }


    @Test
    @DisplayName("Вход через кнопку «Личный кабинет»")
    @Description("Тест проверяет авторизацию пользователя через кнопку 'Личный кабинет' в хедере. "
            + "Ожидаемый результат: после успешной авторизации отображается кнопка 'Оформить заказ'")
    public void loginFromProfilePageTest() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        mainPage.clickAccountButton();
        loginPage.setClientLoginData(client.getEmail(), client.getPassword());
        loginPage.clickLoginButton();
        String text = mainPage.getCreateOrderButtonText();
        Assert.assertEquals("Оформить заказ", text);
    }


    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    @Description("Тест проверяет авторизацию пользователя через ссылку 'Войти' на странице регистрации")
    public void loginFromRegisterPageTest() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        RegisterPage registerPage = new RegisterPage(driver);
        mainPage.clickAccountButton();
        loginPage.clickRegisterButton();
        registerPage.clickLoginButton();
        loginPage.setClientLoginData(client.getEmail(), client.getPassword());
        loginPage.clickLoginButton();
        String text = mainPage.getCreateOrderButtonText();
        Assert.assertEquals("Оформить заказ", text);
    }


    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    @Description("Тест проверяет авторизацию пользователя через ссылку 'Войти' на странице восстановления пароля.")
    public void loginFromForgotPasswordPageTest() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);
        mainPage.clickAccountButton();
        loginPage.clickForgotPasswordButton();
        forgotPasswordPage.clickLoginButton();
        loginPage.setClientLoginData(client.getEmail(), client.getPassword());
        loginPage.clickLoginButton();
        String text = mainPage.getCreateOrderButtonText();
        Assert.assertEquals("Оформить заказ", text);
    }

    @After
    public void tearDown(){
        // Закрываем драйвер
        driver.quit();
        // Если токен доступа был получен, удаляем пользователя
        if (accessToken != null) {
            ClientSteps.deleteClient(accessToken);
        }
    }
}