package com.example.HabrTests.tests;

import com.example.HabrTests.pages.BaseTest;
import com.example.HabrTests.pages.MainPageHabr;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainPageTest extends BaseTest {
    private MainPageHabr mainPageHabr;

    @BeforeEach
    @Override
    public void setUp() {
        super.setUp();
        getDriver().get("https://habr.com/ru/feed/");
        mainPageHabr = new MainPageHabr(getDriver());
    }

    @Test
    @DisplayName("Переход на страницу с правилами сайта")
    public void goRulesPage() {
        mainPageHabr.goToRulesPage();

        assertTrue(
                mainPageHabr.isChangelogLinkDisplayed(),
                "Changelog не найден"
        );
    }

    @Test
    @DisplayName("Видимость кнопки «Настройки»")
    public void settingsMenuVisible() {
        mainPageHabr.isSettingMenuDisplayed();
        assertTrue(
                mainPageHabr.isSettingMenuDisplayed(), "Кнопка «Настройки» не отображается"
        );
    }

    @Test
    @DisplayName("Проверка отображения кнопки «Техподдержка»")
    public void visibleSupportButton() {
        assertTrue(
                mainPageHabr.isSupportButtonDisplayed(),
                "Кнопка «Техническая поддержка» не отображается"
        );
    }

    @Test
    @DisplayName("Переход на форму обращения в ТП")
    public void goFeedBackForm() {
        mainPageHabr.clickSupportButton();
        assertTrue(
                mainPageHabr.isFeedBackFormDisplayed(), "Переход на форму обращения в техподдержку не произошел"
        );
    }

    @Test
    @DisplayName("Проверка кликабельности кнопки «Поиск»")
    public void beClickableSearchButton() {
        mainPageHabr.checkSearchButtonClickable();
    }

    @Test
    @DisplayName("Нажатие на кнопку «Поиск»")
    public void clickSearchButton() {
        mainPageHabr.clickSearchButton();
        assertTrue(
                mainPageHabr.isSearchFormDisplayed(), "Строка поиска не отображается"
        );
    }

    @Test
    @DisplayName("Проверка отображения кнопки «Написать публикацию»")
    public void visibleWritePublication() {
        assertTrue(
                mainPageHabr.isWritePublicationButtonDisplayed(),
                "Кнопка «Написать публикацию» не отображается"
        );
    }

    @Test
    @DisplayName("Переход на страницу «Как стать автором»")
    public void goPageStartAuthor() {
        mainPageHabr.clickWritePublicateButton();
        assertTrue(
                mainPageHabr.isNewPublicationDisplayed(),
                "Переход на страницу «Как стать автором» не произошел"
        );
    }

    @Test
    @DisplayName("Смена языка интерфейса")
    public void changeLanguage() {
        mainPageHabr.goToSettingsMenu();
        mainPageHabr.assertSettingsMenuOpened();
        mainPageHabr.clickEngLanguage();
        mainPageHabr.waitSavePreferencesTextIs("Save preferences");
        assertTrue(
                mainPageHabr.getSavePreferencesText().equals("Save preferences"),
                "Текст кнопки сохранения не переключился на английский"
        );
    }

    @Test
    @DisplayName("Кликабельность бургер-кнопки")
    public void burgerButtonIsClickable() {
        mainPageHabr.checkBurgerButtonClickable();
    }

    @Test
    @DisplayName("Открытие бургер-меню")
    public void burgerMenuOpens() throws InterruptedException {

        mainPageHabr.clickBurgerButton();

        Thread.sleep(2000);

        assertTrue(mainPageHabr.isBurgerMenuVisible());
    }
}

