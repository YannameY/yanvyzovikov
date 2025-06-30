import client.Client;
import client.ClientFaker;
import client.ClientLogin;
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
import pages.RegisterPage;


public class RegistrationTest extends BrowserFactory {
    private Client client;
    private RegisterPage registerPage;
    private String accessToken;

    @Before
    public void setUp() {
        driver = BrowserFactory.createDriver(BrowserFactory.choice);
        driver.get(ClientSteps.baseURL);
        driver.manage().window().maximize();
    }

    @Test
    @DisplayName("Регистрация пользователя")
    @Description("Тест проверяет успешную регистрацию нового пользователя")
    public void registerNewClientTest() {
        // Генерируем случайного пользователя
        client = ClientFaker.getRandomClient();
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        RegisterPage registerPage = new RegisterPage(driver);
        mainPage.clickLoginButton();
        loginPage.clickRegisterButton();
        registerPage.setClientInfo(client.getName(), client.getEmail(), client.getPassword());
        registerPage.clickRegisterButton();
        String text = loginPage.getEnterLabelText();
        Assert.assertEquals("Вход", text);
        ClientLogin login = new ClientLogin(client.getEmail(), client.getPassword());
        RestAssured.baseURI = ClientSteps.baseURL;
        accessToken = ClientSteps.loginClient(login).then().extract().path("accessToken");
    }

    @Test
    @DisplayName("Регистрация пользователя с некорректным паролем")
    @Description("Тест проверяет обработку ошибки при регистрации с некорректным паролем")
    public void registerNewClientWithWrongPasswordTest() {
        // Генерируем случайного пользователя с некорректным паролем
        client = ClientFaker.getRandomClientWithWrongPassword();
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        RegisterPage registerPage = new RegisterPage(driver);
        mainPage.clickLoginButton();
        loginPage.clickRegisterButton();
        registerPage.setClientInfo(client.getName(), client.getEmail(), client.getPassword());
        registerPage.clickRegisterButton();
        String text = registerPage.getPasswordErrorText();
        Assert.assertEquals("Некорректный пароль", text);
    }

    @After
    public void tearDown() {
        driver.quit();
        // Если токен доступа был получен, пытаемся удалить пользователя
        if (accessToken != null) {
            try {
                ClientSteps.deleteClient(accessToken);
            } catch (Exception e) {
                // Если возникла ошибка при удалении пользователя, выводим сообщение об ошибке
                System.out.println("Ошибка при удалении пользователя: " + e.getMessage());
            }
        }
    }
}