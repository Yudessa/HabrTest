package com.example.HabrTests.pages;
//URL https://www.habr.com/

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.example.HabrTests.AllureLogger;
import io.qameta.allure.Step;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class MainPageHabr {
    private final AllureLogger LOG = new AllureLogger(LoggerFactory.getLogger(MainPageHabr.class));

    private final SelenideElement rulesPage = $x("//a[contains(., 'Устройство сайта')]");
    private final SelenideElement changeLog = $x("//a[@href='/ru/docs/changelog/']");
    private final SelenideElement settingsMenuButton = $x("//button[@data-test-id='user-menu-settings']");
    private final SelenideElement engLanguage = $x("//label[contains(@class,'tm-input-radio-labeled__label') and contains(.,'English')]");
    private final SelenideElement savePreferencesButton = $("button.tm-page-settings-form__submit");
    private final SelenideElement supportButton = $x("//a[@href='/ru/feedback/']");
    private final SelenideElement searchButton = $x("//a[@href='/ru/search/']");
    private final SelenideElement searchForm = $("form.tm-search");
    private final SelenideElement feedBackForm = $(".tm-feedback");
    private final SelenideElement writePublication = $x("//a[@href='/ru/sandbox/start/']");
    private final SelenideElement newPublicationButton = $x("(//a[contains(@class,'button') and contains(text(),'Написать публикацию')])[1]");
    private final SelenideElement burgerButton = $x("//button[contains(@class, 'tm-header__burger')]");
    private final SelenideElement burgerMenuContainer = $x("//div[contains(@class, 'navigation-wrapper')]");
    private final SelenideElement settingsForm = $("form.tm-page-settings-form");

    private final By settingsPopupBody = By.cssSelector(".tm-popup-base__body");
    private final By settingsForm = By.cssSelector(".tm-popup-base__body form.tm-page-settings-form");
    private final By settingsTitle = By.cssSelector(".tm-popup-base__body .tm-page-settings-form__title");
    private final By uiEnglishRadio = By.id("uiEnglish");


    public String getSavePreferencesText() {
        return savePreferencesButton.getText().trim();
    }

    public void waitSavePreferencesTextIs(String expectedText) {
        savePreferencesButton.shouldHave(Condition.text(expectedText));
    }

    @Step("Переход на страницу правил сайта")
    public void goToRulesPage() {
        rulesPage.click();
    }

    @Step("Нажатие кнопки «Поиск»")
    public void clickSearchButton() {
        searchButton.click();
    }

    @Step("Нажатие кнопки «Настройки» и ожидание открытия попапа")
    public void goToSettingsMenu() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.elementToBeClickable(settingsMenuButton));
        settingsMenuButton.click();
        settingsForm.shouldBe(visible);
    }

    @Step("Проверка кликабельности кнопки «Поиск»")
    public void checkSearchButtonClickable() {
        searchButton.shouldBe(visible)
                .shouldBe(Condition.enabled);
    }

    @Step("Нажатие радиобаттона English")
    public void clickEngLanguage() {
        engLanguage.click();
    }

    public boolean isChangelogLinkDisplayed() {
        return changeLog.is(visible);
    }

    public boolean isSearchFormDisplayed() {
        return searchForm.is(visible);
    }

    public boolean isFeedBackFormDisplayed() {
        return feedBackForm.is(visible);
    }

    public boolean isSettingMenuDisplayed() {
        return settingsMenuButton.isDisplayed();
    }

    @Step("Нажатие кнопки «Техническая поддержка»")
    public void clickSupportButton() {
        supportButton.click();
    }

    public boolean isSupportButtonDisplayed() {
        return supportButton.is(visible);
    }

    public boolean isWritePublicationButtonDisplayed() {
        return writePublication.is(visible);
    }

    public boolean isNewPublicationDisplayed() {
        return newPublicationButton.is(visible);
    }

    @Step("Клик по кнопке 'Написать публикацию'")
    public void clickWritePublicateButton() {
        writePublication.click();
    }

    @Step("Клик по бургер-меню")
    public void clickBurgerButton() {
        burgerButton.click();
    }

    public void isBurgerMenuVisible() {
        burgerMenuContainer.shouldBe(Condition.visible);
    }

    @Step("Проверка кликабельности бургер-кнопки")
    public void checkBurgerButtonClickable() {
        burgerButton.shouldBe(visible)
                .shouldBe(Condition.enabled);
    }
}
