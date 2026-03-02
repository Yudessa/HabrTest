package com.example.HabrTests.pages;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.example.HabrTests.AllureLogger;
import io.qameta.allure.Attachment;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.OutputType;
import org.slf4j.LoggerFactory;

public class BaseTest {

    protected final AllureLogger LOG;

    @RegisterExtension
    AfterTestExecutionCallback screenshotOnFail = new AfterTestExecutionCallback() {
        @Override
        public void afterTestExecution(ExtensionContext context) {
            if (context.getExecutionException().isPresent()) {
                attachScreenshot();
            }
        }
    };

    public BaseTest() {
        LOG = new AllureLogger(LoggerFactory.getLogger(this.getClass()));
    }

    @BeforeEach
    public void setUp() {
        Configuration.browser = "chrome";
        Configuration.timeout = 10000;
        Configuration.pageLoadStrategy = "eager";
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 5000;
        SelenideLogger.addListener("AllureSelenide",
                new AllureSelenide()
                        .screenshots(true)
                        .savePageSource(true)
        );
    }

    @Attachment(value = "Screenshot on failure", type = "image/png")
    public byte[] attachScreenshot() {
        return Selenide.screenshot(OutputType.BYTES);
    }

    @AfterEach
    public void tearDown() {
        Selenide.closeWebDriver();
    }
}