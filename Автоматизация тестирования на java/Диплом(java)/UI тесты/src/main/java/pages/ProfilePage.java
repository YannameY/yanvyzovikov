package pages;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProfilePage {
    private WebDriver driver; // Поле для хранения экземпляра WebDriver
    private By logoButton1 = By.xpath(".//div/a[@href='/']");
    private By logoButton2 = By.xpath(".//div[@class='AppHeader_header__logo__2D0X2']"); // Локатор кнопки с логотипом на странице
    private By logoutButton = By.xpath(".//button[text()='Выход']");
    private By constructorButton = By.xpath(".//p[text()='Конструктор']");


    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Нажать кнопку 'Конструктор'")
    public void clickConstructorButton() {
        try {
            // 1. Ожидаем кликабельности элемента с обработкой перекрытия
            WebElement button = new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(driver -> {
                        WebElement elem = driver.findElement(constructorButton);
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
                        driver.findElement(constructorButton)
                );
            } catch (Exception ex) {
                throw new RuntimeException("Не удалось кликнуть на кнопку аккаунта после 5 секунд ожидания", e);
            }
        }

    }

    @Step("Нажать на логотип")
    public void clickLogoButton() {
        By[] possibleLocators = {
                logoButton1,
                logoButton2
        };

        WebElement logo = findFirstVisibleElement(possibleLocators, 3);

        new Actions(driver)
                .moveToElement(logo)
                .pause(300)
                .click()
                .perform();
    }

    private WebElement findFirstVisibleElement(By[] locators, int timeoutSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        for (By locator : locators) {
            try {
                return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            } catch (TimeoutException e) {
                continue;
            }
        }
        throw new NoSuchElementException("Не найдено ни одного видимого элемента по указанным локаторам");
    }

    @Step("Нажать кнопку 'Выход'")
    public void clickLogoutButton() {
        try {
            // 1. Ожидаем кликабельности элемента с обработкой перекрытия
            WebElement button = new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(driver -> {
                        WebElement elem = driver.findElement(logoutButton);
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
                        driver.findElement(logoutButton)
                );
            } catch (Exception ex) {
                throw new RuntimeException("Не удалось кликнуть на кнопку аккаунта после 5 секунд ожидания", e);
            }
        }

    }

    @Step("Получить текст кнопки 'Выход'")
    public String getLogoutButtonText(){
        return driver.findElement(logoutButton).getText();
    }
}



