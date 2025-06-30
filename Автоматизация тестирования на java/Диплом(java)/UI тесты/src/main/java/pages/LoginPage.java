package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage { // Объявляем класс pages.LoginPage

    private WebDriver driver;
    private By emailField = By.xpath(".//label[text()='Email']/../input");
    private By passwordField = By.xpath(".//label[text()='Пароль']/../input");
    private By loginButton = By.xpath(".//button[text()='Войти']");
    private By registerButton = By.xpath(".//a[(@class='Auth_link__1fOlj' and text()='Зарегистрироваться')]");
    private By forgotPasswordButton = By.xpath(".//a[text()='Восстановить пароль']");
    private By enterLabelText = By.xpath("//h2[text()='Вход']");


    public LoginPage(WebDriver driver) {
        this.driver = driver; // Устанавливаем переданный WebDriver
    }

    @Step("Ввод email в поле ввода")
    public void setEmailField(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    @Step("Ввести пароль в поле ввода")
    public void setPasswordField(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    @Step("Нажать кнопку 'Войти'")
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    @Step("Нажать кнопку 'Зарегистрироваться'")
    public void clickRegisterButton() {
        WebElement element = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(registerButton));
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", element);
    }

    @Step("Нажать кнопку 'Восстановить пароль'")
    public void clickForgotPasswordButton() {
        WebElement element = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(forgotPasswordButton));
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", element);
    }

    @Step("Получить текст заголовка 'Вход'")
    public String getEnterLabelText() {
        // Ожидаем появления элемента и его видимости в течение 10 секунд
        WebElement element = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(enterLabelText));
        return element.getText();
    }

    @Step("Заполнить форму входа: email '{email}', пароль")
    public void setClientLoginData(String email, String password) {
        setEmailField(email);
        setPasswordField(password);
    }
}