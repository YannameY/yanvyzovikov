package ru.practicum.yandex.main.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LandingPageScooter {

    private static WebDriver driver;
    private By scrollToLandingScooter = By.id("accordion__heading-0");
    public static final String BASE_URL = "https://qa-scooter.praktikum-services.ru/";

    public LandingPageScooter(WebDriver driver) {
        this.driver = driver;
    }

    private By ButtonCookie = By.xpath(".//button[text()='да все привыкли']");


    public void clickButtonCookie() {
        driver.findElement(ButtonCookie).click();
    }

    public void scrollToLanding() {
        WebElement element = driver.findElement(scrollToLandingScooter);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }


    static String closedDropdownLocatorFormat = "accordion__heading-%d";
    public static String textInputLocatorFormat = "accordion__panel-%d";

    // Метод для клика по закрытому дропдауну с использованием форматирования локатора
    public static void clickClosedDropdownByIndex(int index) {
        driver.findElement(By.id(String.format(closedDropdownLocatorFormat, index))).click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id(String.format(LandingPageScooter.textInputLocatorFormat, index)))
                );
    }

    // Метод для получения текста из текстового поля с использованием форматирования локатора
    public static String getTextFromTextInputByIndex(int index) {
        return driver.findElement(By.id(String.format(textInputLocatorFormat, index))).getText();
    }
}
