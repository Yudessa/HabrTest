package com.example.HabrTests.tests;

import com.example.HabrTests.pages.AuthHabr;
import com.example.HabrTests.pages.BaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class AuthTest extends BaseTest {
    private AuthHabr authHabr;

    @BeforeEach
    @Override
    public void setUp() {
        super.setUp();
        getDriver().get("https://www.habr.com/");
        authHabr = new AuthHabr(getDriver());
    }

    @Test
    @DisplayName("Проверка отображения кнопки «Войти»")
    public void visibleEnterButton() {
        assertTrue(
                authHabr.isEnterButtonVisible(),
                "Кнопка «Войти» не отображается"
        );
    }

    @Test
    @DisplayName("Проверка кликабельности кнопки «Войти»")
    public void enterButtonShouldBeClickable() {
        authHabr.checkEnterButtonClickable();
    }

    @Test
    @DisplayName("Переход на форму авторизации по клику «Войти»")
    public void openLoginForm() {
        authHabr.clickEnter();
        assertTrue(
                authHabr.isRemindPasswordClickable(),
                "Переход на форму авторизации не произошел"
        );
    }

    @Test
    @DisplayName("Кликабельность подсказки «Забыли пароль»")
    public void clickableRemindPasswordButton() {
        authHabr.clickEnter();
        assertTrue(
                authHabr.isRemindPasswordClickable(),
                "Кнопка «Забыли пароль» некликабельна"
        );
    }

    @Test
    @DisplayName("Переход на форму восстановления пароля")
    public void goRecoveryPassword() {
        authHabr.clickEnter();
        authHabr.clickRemindPasswordButton();
        assertTrue(
                authHabr.isRecoveryFormDisplayed(),
                "Переход на форму восстановления пароля не произошел"
        );
    }

    @Test
    @DisplayName("Наличие кнопки «Войти с помощью GitHub»")
    public void checkGitSocialButton() {
        authHabr.clickEnter();
        assertTrue(
                authHabr.isEnterWithGitHubButton(),
                "Кнопка «Войти с помощью GitHub» не отображается"
        );
    }

    @Test
    @DisplayName("Вход без заполения логина и пароля")
    public void loginWithoutData() {
        authHabr.clickEnter();
        authHabr.clickLoginButton();
        assertTrue(
                authHabr.isEmailFieldInvalid(),
                "Валидация для пустого email не сработала"
        );
    }

    @Test
    @DisplayName("Нельзя войти с невалидными логином и паролем")
    public void loginWithInvalidCredentials() {
        authHabr.clickEnter();

        authHabr.enterCredentials(
                "test@test.ru",
                "wrongPassword"
        );

        authHabr.clickLoginButton();

        assertTrue(
                authHabr.isOnLoginPage(),
                "Произошёл переход со страницы авторизации"
        );
    }

    @Test
    @DisplayName("Отправка пустой формы восстановления пароля")
    public void sendEmptyRecoveryPassword() {
        authHabr.clickEnter();
        authHabr.clickRemindPasswordButton();
        authHabr.clickNextRecoveryPasswordButton();
        assertTrue(
                authHabr.isEmailFieldInvalid(),
                "Валидация для пустого email не сработала"
        );
    }
}

