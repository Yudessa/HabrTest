package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainPageTest {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        FirefoxOptions options = new FirefoxOptions();

        driver = new FirefoxDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://www.habr.com/");
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void changeLogTest() {
        WebElement webLink = driver.findElement(By.xpath("//*[contains(text(), 'Устройство сайта')]"));
        webLink.click();

        assertTrue(driver.findElement(By.xpath("//*[contains(text(), 'Changelog')]")).isDisplayed(), "Changelog не найден");
    }
}

