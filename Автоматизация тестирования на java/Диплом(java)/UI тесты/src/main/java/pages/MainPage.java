package pages;// pages.MainPage.java
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {
    private WebDriver driver;

    private By loginButton = By.xpath(".//button[text()='Войти в аккаунт']");
    private By profileButton = By.xpath(".//p[text()='Личный Кабинет']");
    private By bunButton = By.xpath(".//span[text()='Булки']");
    private By sauceButton = By.xpath(".//span[text()='Соусы']");
    private By fillingButton = By.xpath(".//span[text()='Начинки']");
    private By createOrderButton = By.xpath(".//button[text()='Оформить заказ']");
    private By menuTabLocator = By.xpath("//div[contains(@class,'tab_tab__1SPyG tab_tab_type_current__2BEPc')]");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Нажать кнопку 'Войти в аккаунт'")
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    @Step("Получить текст кнопки 'Оформить заказ'")
    public String getCreateOrderButtonText(){
        return driver.findElement(createOrderButton).getText();
    }

    @Step("Получить текст активного раздела меню")
    public String getMenuTabLocator() {
        // Ожидаем появления и видимости элемента в течение 10 секунд
        WebElement menuTab = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(menuTabLocator));
        return menuTab.getText();
    }

    @Step("Нажать кнопку 'Личный Кабинет'")
    public void clickAccountButton() {
        try {
            // 1. Ожидаем кликабельности элемента с обработкой перекрытия
            WebElement button = new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(driver -> {
                        WebElement elem = driver.findElement(profileButton);
                        if (elem.isDisplayed() && elem.isEnabled()) {
                            try {
                                // Пробуем обычный клик
                                elem.click();
                                return elem;
                            } catch (ElementClickInterceptedException e) {
                                // Если перекрыт - используем JS-клик с прокруткой
                                ((JavascriptExecutor) driver).executeScript(
                                        "arguments[0].scrollIntoView({block: 'center'}); arguments[0].click();",
                                        elem
                                );
                                return elem;
                            }
                        }
                        return null;
                    });

        } catch (TimeoutException e) {
            // 2. Делаем скриншот при ошибке
            TakesScreenshot ts = (TakesScreenshot) driver;
            byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
            // Здесь можно сохранить скриншот или прикрепить к отчёту

            // 3. Пробуем "аварийный" вариант клика
            try {
                ((JavascriptExecutor) driver).executeScript(
                        "arguments[0].scrollIntoView(true); arguments[0].click();",
                        driver.findElement(profileButton)
                );
            } catch (Exception ex) {
                throw new RuntimeException("Не удалось кликнуть на кнопку аккаунта после 5 секунд ожидания", e);
            }
        }
    }

    @Step("Перейти в раздел 'Булки'")
    public void clickBunButton() {
        driver.findElement(bunButton).click();
        // Ждем, пока кнопка "Булки" станет активной (видимой)
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(bunButton));
        // Проверяем, что кнопка активна
        if (!driver.findElement(bunButton).isDisplayed()) {
            throw new AssertionError("Раздел 'Булки' не стал активным после клика");
        }
    }

    @Step("Перейти в раздел 'Соусы'")
    public void clickSauceButton() {
        driver.findElement(sauceButton).click(); // Кликаем кнопку "Соусы"
        // Ожидаем, пока раздел "Соусы" станет активным
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(sauceButton));
        // Проверяем, что кнопка/раздел активен
        if (!driver.findElement(sauceButton).isDisplayed()) {
            throw new AssertionError("Раздел 'Соусы' не стал активным после клика");
        }
    }

    @Step("Перейти в раздел 'Начинки'")
    public void clickFillingButton() {
        driver.findElement(fillingButton).click();// Кликаем кнопку "Начинки"
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(fillingButton));
        WebElement activeFillingBtn = driver.findElement(fillingButton);
        if (!activeFillingBtn.isDisplayed()) {
            throw new AssertionError("Раздел 'Начинки' не отображается после клика");
        }
    }

}



