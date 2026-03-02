package com.example.HabrTests.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.example.HabrTests.AllureLogger;
import io.qameta.allure.Step;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.WebDriverRunner.url;


public class AuthHabr {

    private final AllureLogger LOG =
            new AllureLogger(LoggerFactory.getLogger(AuthHabr.class));

    private final SelenideElement emailInput = $("input[type='email']");
    private final SelenideElement passwordInput = $("input[type='password']");
    private final SelenideElement enterButton =
            $x("//a[contains(@class, 'tm-header-user-menu__login')]");
    private final SelenideElement enterWithGitHubButton =
            $x("//button[contains(@class,'socials-buttons__button_github')]");
    private final SelenideElement remindPassword =
            $x("//a[contains(., 'Забыли пароль')]");
    private final SelenideElement recoveryForm =
            $x("//div[normalize-space()='Восстановление пароля']");
    private final SelenideElement invalidEmailInput =
            $("input[type='email']:invalid");
    private final SelenideElement loginButton =
            $x("//button[contains(@class,'button button_wide button_primary')]");
    private final SelenideElement nextRecoveryPasswordButton =
            $x("//button[contains(@class,'button button_wide button_primary')]");

    @Step("Нажатие кнопки «Забыли пароль»")
    public void clickRemindPasswordButton() {
        remindPassword.click();
    }

    @Step("Нажатие кнопки «Войти» на главной странице")
    public void clickLoginButton() {
        loginButton.click();
    }

    @Step("Проверка отображения формы восстановления пароля")
    public boolean isRecoveryFormDisplayed() {
        return recoveryForm.is(Condition.visible);
    }

    @Step("Проверка отображения кнопки «Войти»")
    public boolean isEnterButtonVisible() {
        return enterButton.is(Condition.visible);
    }

    @Step("Проверка кликабельности кнопки «Войти»")
    public void checkEnterButtonClickable() {
        enterButton.shouldBe(Condition.visible)
                .shouldBe(Condition.enabled);
    }

    public boolean isRemindPasswordClickable() {
        return remindPassword.is(Condition.visible)
                && remindPassword.is(Condition.enabled);
    }

    @Step("Нажатие кнопки «Войти»")
    public void clickEnter() {
        enterButton.click();
    }

    @Step("Проверка отображения кнопки 'Войти через GitHub'")
    public boolean isEnterWithGitHubButton() {
        return enterWithGitHubButton.is(Condition.visible);
    }

    @Step("Проверить, что форма не отправляется без заполнения email")
    public boolean isEmailFieldInvalid() {
        return invalidEmailInput.is(Condition.exist);
    }

    @Step("Ввести email: {email}")
    public void enterEmail(String email) {
        emailInput.setValue(email);
    }

    @Step("Ввести пароль")
    public void enterPassword(String password) {
        passwordInput.setValue(password);
    }

    @Step("Ввести логин и пароль")
    public void enterCredentials(String email, String password) {
        enterEmail(email);
        enterPassword(password);
    }

    public boolean isOnLoginPage() {
        return url().contains("/ident/");
    }

    @Step("Отправка формы восстановления пароля")
    public void clickNextRecoveryPasswordButton() {
        nextRecoveryPasswordButton.click();
    }
}