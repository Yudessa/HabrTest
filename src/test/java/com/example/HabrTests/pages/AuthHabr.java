package com.example.HabrTests.pages;
//URL https://www.habr.com/

import com.example.HabrTests.AllureLogger;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class AuthHabr {
    private final AllureLogger LOG = new AllureLogger(LoggerFactory.getLogger(AuthHabr.class));
    private WebDriver driver;

    @FindBy(css = "input[type='email']")
    private WebElement emailInput;

    @FindBy(css = "input[type='password']")
    private WebElement passwordInput;

    @FindBy(xpath = "//a[contains(@class, 'tm-header-user-menu__login')]")
    private WebElement enterButton;

    @FindBy(xpath = "//button[contains(@class,'button button_wide button_primary')]")
    private WebElement nextRecoveryPasswordButton;

    @FindBy(xpath = "//button[contains(@class,'socials-buttons__button_github')]")
    private WebElement enterWithGitHubButton;

    @FindBy(xpath = "//button[contains(@class,'button button_wide button_primary')]")
    private WebElement loginButton;

    @FindBy(xpath = "//a[@href='/ru/docs/changelog/']")
    private WebElement changelogLink;

    @FindBy(xpath = "//a[contains(., 'Забыли пароль')]")
    private WebElement remindPassword;

    @FindBy(xpath = "//div[normalize-space()='Восстановление пароля']")
    private WebElement recoveryForm;

    @FindBy(css = "input[type='email']:invalid")
    private WebElement invalidEmailInput;

    public AuthHabr(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step("Нажатие кнопки «забыли пароль»")
    public void clickRemindPasswordButton() {
        remindPassword.click();
    }

    @Step("Нажатие кнопки «Войти» на главной странице")
    public void clickLoginButton() {
        loginButton.click();
    }

    @Step("Проверка отображения формы восстановления пароля")
    public boolean isRecoveryFormDisplayed() {
        return recoveryForm.isDisplayed();
    }

    public boolean isEnterButtonVisible() {
        return enterButton.isDisplayed();
    }

    @Step("Проверка кликабельности кнопки «Войти»")
    public void checkEnterButtonClickable() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(enterButton));
    }

    public boolean isRemindPasswordClickable() {
        return remindPassword.isEnabled();
    }

    @Step("Нажатие кнопки «Войти»")
    public void clickEnter() {
        LOG.infoWithScreenshot("Нажатие кнопки Войти");
        enterButton.click();
    }

    @Step("Проверка отображения кнопки 'Войти через GitHub'")
    public boolean isEnterWithGitHubButton() {
        return enterWithGitHubButton.isDisplayed();
    }

    @Step("Проверить, что форма не отправляется без заполнения email")
    public boolean isEmailFieldInvalid() {
        return invalidEmailInput.isDisplayed();
    }

    @Step("Ввести email: {email}")
    public void enterEmail(String email) {
        emailInput.clear();
        emailInput.sendKeys(email);
    }

    @Step("Ввести пароль")
    public void enterPassword(String password) {
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }

    @Step("Ввести логин и пароль")
    public void enterCredentials(String email, String password) {
        enterEmail(email);
        enterPassword(password);
    }

    public boolean isOnLoginPage() {
        return driver.getCurrentUrl().contains("/ident/");
    }

    @Step("Отправка формы восстановления пароля на странице восстановления пароля")
    public void clickNextRecoveryPasswordButton() {
        nextRecoveryPasswordButton.click();
    }
}
