package com.SeleniumSchedulePro.tests;

import com.SeleniumSchedulePro.base.BaseTest;
import com.SeleniumSchedulePro.pages.LoginPage;
import com.SeleniumSchedulePro.testrail.TestRailClient;
import com.SeleniumSchedulePro.testrail.TestRailReporter;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class LoginTest extends BaseTest {

    @Test
    public void loginConCredencialesValidasDebeRedirigir() {
        boolean passed = false;
        try {
            driver.get("https://schedule-pro-36f5a.web.app/");
            LoginPage loginPage = new LoginPage(driver);
            loginPage.loginAs("jhogillds@gmail.com", "ADRyOj");

            Thread.sleep(3000);
            passed = driver.getCurrentUrl().contains("dashboard");
            assertTrue(passed);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                TestRailClient client = new TestRailClient(
                        "https://codigoabiertop.testrail.io",
                        "codigo.abierto.p@gmail.com",
                        "pknkko2Hs9S8IPUANOzE-KVHY/dyV3IhGvtilMXUV"
                );
                TestRailReporter reporter = new TestRailReporter(client, 2, 2, 35);
                reporter.reportResultPerTest("Run automático: login válido", passed, "Login válido - Automatizado");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void loginConCredencialesInvalidasNoDebeRedirigir() {
        boolean passed = false;
        try {
            driver.get("https://schedule-pro-36f5a.web.app/");
            LoginPage loginPage = new LoginPage(driver);
            loginPage.loginAs("usuario_invalido@gmail.com", "claveIncorrecta");

            Thread.sleep(3000);
            passed = !driver.getCurrentUrl().contains("dashboard");
            assertTrue(passed);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                TestRailClient client = new TestRailClient(
                        "https://codigoabiertop.testrail.io",
                        "codigo.abierto.p@gmail.com",
                        "pknkko2Hs9S8IPUANOzE-KVHY/dyV3IhGvtilMXUV"
                );
                TestRailReporter reporter = new TestRailReporter(client, 2, 2, 35);
                reporter.reportResultPerTest("Run automático: login inválido", passed, "Login inválido - Automatizado");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void loginConEmailVacioDebePermanecerEnLogin() {
        boolean passed = false;
        try {
            driver.get("https://schedule-pro-36f5a.web.app/");
            LoginPage loginPage = new LoginPage(driver);
            loginPage.loginAs("", "claveValida123");

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            boolean loginStillVisible = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Iniciar sesión')]"))
            ).isDisplayed();

            passed = !driver.getCurrentUrl().contains("dashboard") && loginStillVisible;
            assertTrue(passed);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                TestRailClient client = new TestRailClient(
                        "https://codigoabiertop.testrail.io",
                        "codigo.abierto.p@gmail.com",
                        "pknkko2Hs9S8IPUANOzE-KVHY/dyV3IhGvtilMXUV"
                );
                TestRailReporter reporter = new TestRailReporter(client, 2, 2, 35);
                reporter.reportResultPerTest("Run automático: email vacío", passed, "Email vacío - Automatizado");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void loginConPasswordVaciaDebePermanecerEnLogin() {
        boolean passed = false;
        try {
            driver.get("https://schedule-pro-36f5a.web.app/");
            LoginPage loginPage = new LoginPage(driver);
            loginPage.loginAs("usuario_valido@gmail.com", "");

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            boolean loginStillVisible = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Iniciar sesión')]"))
            ).isDisplayed();

            passed = !driver.getCurrentUrl().contains("dashboard") && loginStillVisible;
            assertTrue(passed);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                TestRailClient client = new TestRailClient(
                        "https://codigoabiertop.testrail.io",
                        "codigo.abierto.p@gmail.com",
                        "pknkko2Hs9S8IPUANOzE-KVHY/dyV3IhGvtilMXUV"
                );
                TestRailReporter reporter = new TestRailReporter(client, 2, 2, 35);
                reporter.reportResultPerTest("Run automático: password vacío", passed, "Password vacío - Automatizado");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void enlaceContactaAdministradorDebeLlevarAlgunaParte() {
        boolean passed = false;
        try {
            driver.get("https://schedule-pro-36f5a.web.app/");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

            WebElement link = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("a[href^='mailto:']")));

            String href = link.getAttribute("href");
            passed = link.isDisplayed() && href != null && href.startsWith("mailto:");
            assertTrue(passed, "❌ El enlace no lleva a ningún lado válido (mailto: esperado).");

        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(passed);
        } finally {
            try {
                TestRailClient client = new TestRailClient(
                        "https://codigoabiertop.testrail.io",
                        "codigo.abierto.p@gmail.com",
                        "pknkko2Hs9S8IPUANOzE-KVHY/dyV3IhGvtilMXUV"
                );
                TestRailReporter reporter = new TestRailReporter(client, 2, 2, 35);
                reporter.reportResultPerTest("Run automático: enlace administrador", passed, "Enlace administrador - Automatizado");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
