package com.example.HabrTests.pages;
//URL https://www.habr.com/

import com.example.HabrTests.AllureLogger;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;

public class MainPageHabr {
    private final AllureLogger LOG = new AllureLogger(LoggerFactory.getLogger(MainPageHabr.class));
    private WebDriver driver;

    @FindBy(xpath = "//a[contains(., 'Устройство сайта')]")
    private WebElement rulesPage;

    @FindBy(xpath = "//a[@href='/ru/docs/changelog/']")
    private WebElement changeLog;

    @FindBy(xpath = "//button[@data-test-id='user-menu-settings']")
    private WebElement settingsMenuButton;

    @FindBy(xpath = "//label[contains(@class,'tm-input-radio-labeled__label') and contains(.,'English')]")
    private WebElement engLanguage;

    @FindBy(css = "button.tm-page-settings-form__submit")
    private WebElement savePreferencesButton;

    @FindBy(xpath = "//a[@href='/ru/feedback/']")
    private WebElement supportButton;

    @FindBy(xpath = "//a[@href='/ru/search/']")
    private WebElement searchButton;

    @FindBy(css = "form.tm-search")
    private WebElement searchForm;

    @FindBy(css = ".tm-feedback")
    private WebElement feedBackForm;

    @FindBy(xpath = "//a[@href='/ru/sandbox/start/']")
    private WebElement writePublication;

    @FindBy(xpath = "(//a[contains(@class,'button') and contains(text(),'Написать публикацию')])[1]")
    private WebElement newPublicationButton;

    @FindBy(xpath = "//button[contains(@class, 'tm-header__burger')]")
    private WebElement burgerButton;

    @FindBy(xpath = "//div[contains(@class, 'navigation-wrapper')]")
    private WebElement burgerMenuContainer;

    public String getSavePreferencesText() {
        return savePreferencesButton.getText().trim();
    }

    public void waitSavePreferencesTextIs(String expectedText) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.textToBePresentInElement(savePreferencesButton, expectedText));
    }

    @Step("Переход на страницу правил сайта")
    public void goToRulesPage() {
        rulesPage.click();
    }

    @Step("Нажатие кнопки «Поиск»")
    public void clickSearchButton() {
        LOG.infoWithScreenshot("Нажатие кнопки «Поиск»");
        searchButton.click();
    }

    @Step("Нажатие кнопки «Настройки» и ожидание открытия попапа")
    public void goToSettingsMenu() {
        settingsMenuButton.click();

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("uiEnglish")));
    }

    @Step("Проверка кликабельности кнопки «Поиск»")
    public void checkSearchButtonClickable() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(searchButton));
    }

    @Step("Нажатие радиобаттона English")
    public void clickEngLanguage() {
        engLanguage.click();
    }

    public boolean isChangelogLinkDisplayed() {
        return changeLog.isDisplayed();
    }

    public boolean isSearchFormDisplayed() {
        return searchForm.isDisplayed();
    }

    public boolean isFeedBackFormDisplayed() {
        return feedBackForm.isDisplayed();
    }

    public MainPageHabr(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isSettingMenuDisplayed() {
        return settingsMenuButton.isDisplayed();
    }

    @Step("Нажатие кнопки «Техническая поддержка»")
    public void clickSupportButton() {
        supportButton.click();
    }

    public boolean isSupportButtonDisplayed() {
        return supportButton.isDisplayed();
    }

    public boolean isWritePublicationButtonDisplayed() {
        return writePublication.isDisplayed();
    }

    public boolean isNewPublicationDisplayed() {
        return newPublicationButton.isDisplayed();
    }

    @Step("Клик по кнопке 'Написать публикацию'")
    public void clickWritePublicateButton() {
        writePublication.click();
    }

    @Step("Клик по бургер-меню")
    public void clickBurgerButton() {
        burgerButton.click();
    }

    public boolean isBurgerMenuVisible() {
        return burgerMenuContainer.isDisplayed();
    }

    @Step("Проверка кликабельности бургер-кнопки")
    public void checkBurgerButtonClickable() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(burgerButton));
    }
}
