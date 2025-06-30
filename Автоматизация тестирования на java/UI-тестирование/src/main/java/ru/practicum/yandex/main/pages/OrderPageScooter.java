package ru.practicum.yandex.main.pages;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderPageScooter {

    private static WebDriver driver;

    public OrderPageScooter(WebDriver driver) {
        this.driver = driver;
    }

    private By ButtonCookie = By.xpath(".//button[text()='да все привыкли']");
    private By ButtonOrderOne = By.xpath("//button[@class='Button_Button__ra12g']");
    private By ButtonOrderTwo = By.className("Button_Middle__1CSJM");
    private By NameField = By.xpath(".//input[@placeholder='* Имя']");
    private By LastNameField = By.xpath(".//input[@placeholder='* Фамилия']");
    private By AddressField = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");

    private By clickMetroStation = By.className("select-search");
    private By FieldMetroStation = By.xpath(".//input[@placeholder='* Станция метро']");
    private By OptionMetroStation = By.xpath(".//div[text()='Пушкинская']");

    private By PhoneField = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    private By ButtonNext = By.xpath(".//button[text()='Далее']");


    private By BringScooter = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    private By RentalPeriod = By.className("Dropdown-arrow");
    private By deadlineButton = By.xpath(".//div[text()='двое суток']");
    private By colorSelectionBlack = By.xpath("//label[@for='black']");
    private By colorSelectionGrey = By.xpath("//label[@for='grey']");
    private By commentsToTheCourier = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    private By ButtonOrderRent = By.xpath("//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");
    private By ButtonYes = By.xpath(".//button[text()='Да']");


    public void clickButtonCookie() {
        driver.findElement(ButtonCookie).click();
    }

    public void clickButtonOrderOne() {
        driver.findElement(ButtonOrderOne).click();
    }

    public void clickButtonOrderTwo() {
        driver.findElement(ButtonOrderTwo).click();
    }

    public void SetNameField(String username) {
        driver.findElement(NameField).sendKeys(username);
    }

    public void SetLastNameField(String lastName) {
        driver.findElement(LastNameField).sendKeys(lastName);
    }

    public void SetAddressField(String address) {
        driver.findElement(AddressField).sendKeys(address);
    }

    public void clickMetroStation() {
        driver.findElement(clickMetroStation).click();
    }

    public void SetFieldMetroStation(String MetroStation) {
        driver.findElement(FieldMetroStation).sendKeys(MetroStation);
    }

    public void OptionMetroStation() {
        driver.findElement(OptionMetroStation).click();
    }

    public void SetPhoneField(String phoneNumber) {
        driver.findElement(PhoneField).sendKeys(phoneNumber);
    }

    public void clickButtonNext() {
        driver.findElement(ButtonNext).click();
    }

    public void setDeliveryDate(String deliveryDate) {
        driver.findElement(BringScooter).sendKeys(deliveryDate);
    }

    public void SetRentalPeriod() {
        driver.findElement(RentalPeriod).click();
    }

    public void SetDeadlineButton() {
        driver.findElement(deadlineButton).click();
    }

    public void SetColorSelectionBlack() {
        driver.findElement(colorSelectionBlack).click();
    }

    public void SetcolorSelectionGrey() {
        driver.findElement(colorSelectionGrey).click();
    }

    public void SetCommentsToTheCourier(String comments) {
        driver.findElement(commentsToTheCourier).sendKeys(comments);
    }

    public void ButtonOrderRent() {
        driver.findElement(ButtonOrderRent).click();
    }

    public void ButtonYes() {
        driver.findElement(ButtonYes).click();
    }


    public void orderUser(String username, String lastName, String address, String metroStation, String phoneNumber, String deliveryDate, String comments) {
        SetNameField(username);
        SetLastNameField(lastName);
        SetAddressField(address);
        clickMetroStation();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // 10 секунд
        wait.until(ExpectedConditions.visibilityOfElementLocated(FieldMetroStation));

        SetFieldMetroStation(metroStation);
        OptionMetroStation();
        SetPhoneField(phoneNumber);
        clickButtonNext();
        setDeliveryDate(deliveryDate);
        SetRentalPeriod();
        SetDeadlineButton();
        SetColorSelectionBlack();
        SetCommentsToTheCourier(comments);
        ButtonOrderRent();
        ButtonYes();
    }



    public void orderUserTwo(String username, String lastName, String address, String metroStation, String phoneNumber, String deliveryDate, String comments) {
        SetNameField(username);
        SetLastNameField(lastName);
        SetAddressField(address);
        clickMetroStation();

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(FieldMetroStation));

        SetFieldMetroStation(metroStation);
        OptionMetroStation();
        SetPhoneField(phoneNumber);
        clickButtonNext();
        setDeliveryDate(deliveryDate);
        SetRentalPeriod();
        SetDeadlineButton();
        SetcolorSelectionGrey();
        SetCommentsToTheCourier(comments);
        ButtonOrderRent();
        ButtonYes();
    }


    public void scrollToLandingButtonOrder() throws InterruptedException {
        WebElement element = driver.findElement(ButtonOrderTwo);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(ButtonOrderTwo));
        //Thread.sleep(2000);
    }

    private By orderDoneText = By.xpath(".//div[text()='Заказ оформлен']");


    public void assertOrderDoneTextVisible() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        //Ждем, пока элемент не станет видимым и получаем текст
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(orderDoneText));
            String actualText = driver.findElement(orderDoneText).getText();

            System.out.println("Текст попап окна: " + actualText);
            // Проверка на соответствие текста
             MatcherAssert.assertThat("Неверный ожидаемый текст", actualText, CoreMatchers.containsString("Заказ оформлен"));

        } catch (TimeoutException e) {
            System.err.println("Текст 'Заказ оформлен' не виден (время ожидания истекло)");
            throw e; // Проброс исключения
        }
    }
}



