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

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainPageHabr {
    private final AllureLogger LOG = new AllureLogger(LoggerFactory.getLogger(MainPageHabr.class));
    private WebDriver driver;
    private final WebDriverWait wait;

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

    private final By settingsPopupBody = By.cssSelector(".tm-popup-base__body");
    private final By settingsForm = By.cssSelector(".tm-popup-base__body form.tm-page-settings-form");
    private final By settingsTitle = By.cssSelector(".tm-popup-base__body .tm-page-settings-form__title");
    private final By uiEnglishRadio = By.id("uiEnglish");


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
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.elementToBeClickable(settingsMenuButton));
        settingsMenuButton.click();

        if (driver.findElements(By.cssSelector(".tm-popup-base__body")).isEmpty()) {
            settingsMenuButton.click();
        }

        // ждём попап
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".tm-popup-base__body")
        ));
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
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
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

    public void assertSettingsMenuOpened() {

        // 1) Попап должен стать видимым
        wait.until(ExpectedConditions.visibilityOfElementLocated(settingsPopupBody));
        wait.until(ExpectedConditions.visibilityOfElementLocated(settingsForm));

        // 2) Заголовок должен быть "Настройки страницы"
        WebElement titleEl = wait.until(ExpectedConditions.visibilityOfElementLocated(settingsTitle));
        String title = titleEl.getText().trim();

        assertTrue(
                title.equals("Настройки страницы"),
                "Открылось не то окно. Ожидали заголовок 'Настройки страницы', получили: '" + title + "'"
        );

        // 3) Дополнительно: дождаться, что языковые радиокнопки уже в DOM
        wait.until(ExpectedConditions.presenceOfElementLocated(uiEnglishRadio));
    }
}
