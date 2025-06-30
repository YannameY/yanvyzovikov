package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage { // Объявляем класс RegisterPage

    private WebDriver driver;
    private By nameField = By.xpath(".//label[text()='Имя']/../input");
    private By emailField = By.xpath(".//label[text()='Email']/../input");
    private By passwordField = By.xpath(".//label[text()='Пароль']/../input");
    private By registerButton = By.xpath("//button[contains(text(),'Зарегистрироваться')]");
    private By loginButton = By.xpath(".//a[text()='Войти']");
    private By passwordError = By.xpath(".//p[text()='Некорректный пароль']");


    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Ввести имя пользователя: '{name}'")
    public void setNameField(String name) {
        driver.findElement(nameField).sendKeys(name);
    }

    @Step("Ввести email: '{email}'")
    public void setEmailField(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    @Step("Ввести пароль")
    public void setPasswordField(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    @Step("Нажать кнопку 'Зарегистрироваться'")
    public void clickRegisterButton() {
        driver.findElement(registerButton).click();
    }

    @Step("Нажать кнопку 'Войти'")
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    @Step("Заполнить форму регистрации: имя '{name}', email '{email}', пароль")
    public void setClientInfo(String name, String email, String password) {
        setNameField(name);
        setEmailField(email);
        setPasswordField(password);
    }

    @Step("Получить текст ошибки валидации пароля")
    public String getPasswordErrorText() {
        return driver.findElement(passwordError).getText();
    }
}