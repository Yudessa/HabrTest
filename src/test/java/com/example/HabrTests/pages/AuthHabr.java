package com.example.HabrTests.pages;
//URL https://www.habr.com/

import com.example.HabrTests.AllureLogger;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.LoggerFactory;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selectors.byXpath;

public class AuthHabr {
    private final AllureLogger LOG = new AllureLogger(LoggerFactory.getLogger(AuthHabr.class));
    private WebDriver driver;

    private final By emailInputBy = byCssSelector("input[type='email']");
    private final By passwordInputBy = byCssSelector("input[type='password']");
    private final By enterButtonBy = byXpath("//a[contains(@class, 'tm-header-user-menu__login')]");
    // развести кнопки private final By nextRecoveryPasswordButtonBy = byXpath("//button[contains(@class,'button button_wide button_primary')]");
    private final By enterWithGitHubButtonBy = byXpath("//button[contains(@class,'socials-buttons__button_github')]");
    // private final By loginButtonBy = byXpath("//button[contains(@class,'button button_wide button_primary')]");
    private final By changelogLinkBy = byXpath("//a[@href='/ru/docs/changelog/']");
    private final By remindPasswordBy = byXpath("//a[contains(., 'Забыли пароль')]");
    private final By recoveryFormBy = byXpath("//div[normalize-space()='Восстановление пароля']");
    private final By invalidEmailInputBy = byCssSelector("input[type='email']:invalid");

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
