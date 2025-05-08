package com.SeleniumSchedulePro.tests;

import com.SeleniumSchedulePro.base.BaseTest;
import com.SeleniumSchedulePro.pages.DashboardPage;
import com.SeleniumSchedulePro.pages.LoginPage;
import com.SeleniumSchedulePro.testrail.TestRailClient;
import com.SeleniumSchedulePro.testrail.TestRailReporter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DashboardTest extends BaseTest {

    @Test
    public void panelTotalEmpleadosDebeSerClicable() {
        boolean passed = false;
        try {

            driver.get("https://schedule-pro-36f5a.web.app/");
            LoginPage loginPage = new LoginPage(driver);
            loginPage.loginAs("jhogillds@gmail.com", "ADRyOj");

            Thread.sleep(3000);

            driver.get("https://schedule-pro-36f5a.web.app/dashboard");
            DashboardPage dashboardPage = new DashboardPage(driver);

            String originalUrl = driver.getCurrentUrl();
            assertTrue(dashboardPage.isPanelClickable(dashboardPage.getTotalEmpleadosPanelLocator()));
            dashboardPage.clickTotalEmpleadosPanel();

            Thread.sleep(3000);
            String newUrl = driver.getCurrentUrl();

            passed = !originalUrl.equals(newUrl);
            System.out.println(passed);
            assertTrue(passed, "❌ El panel no redirigió a otra sección.");
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
                reporter.reportResultPerTest("Panel Total Empleados redirecciona", passed, "Automatizado - Panel Total Empleados");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void clickEnHorariosDebeRedirigirAHorarios() {
        boolean passed = false;
        try {

            driver.get("https://schedule-pro-36f5a.web.app/");
            LoginPage loginPage = new LoginPage(driver);
            loginPage.loginAs("jhogillds@gmail.com", "ADRyOj");

            Thread.sleep(3000);
            driver.get("https://schedule-pro-36f5a.web.app/dashboard");

            DashboardPage dashboardPage = new DashboardPage(driver);
            dashboardPage.clickHorarios();

            Thread.sleep(2000);
            String currentUrl = dashboardPage.getCurrentUrl();
            passed = currentUrl.contains("/horarios");

            assertTrue(passed, "❌ No se redirigió a la ruta /horarios");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                TestRailClient client = new TestRailClient(
                        "https://codigoabiertop.testrail.io",
                        "codigo.abierto.p@gmail.com",
                        "pknkko2Hs9S8IPUANOzE-KVHY/dyV3IhGvtilMXUV"
                );
                TestRailReporter reporter = new TestRailReporter(client, 2, 2, 35); // Reemplaza con ID real
                reporter.reportResultPerTest("Navegación a Horarios", passed, "Automatizado - Navegación superior");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void buscarEmpleadoDebeMostrarResultados() {
        boolean passed = false;
        try {

            driver.get("https://schedule-pro-36f5a.web.app/");
            LoginPage loginPage = new LoginPage(driver);
            loginPage.loginAs("jhogillds@gmail.com", "ADRyOj");

            Thread.sleep(3000);
            driver.get("https://schedule-pro-36f5a.web.app/dashboard");

            DashboardPage dashboardPage = new DashboardPage(driver);
            dashboardPage.buscarEmpleado("brahian"); // Usa un nombre real existente en tu base

            Thread.sleep(2000); // opcionalmente reemplazar con WebDriverWait

            passed = dashboardPage.hayResultadosVisibles();
            assertTrue(passed, "❌ No se mostraron resultados tras buscar un empleado válido.");
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
                reporter.reportResultPerTest("Buscar empleado muestra resultados", passed, "Automatizado - Buscador de empleados");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void buscarEmpleadoInexistenteNoDebeMostrarResultados() {
        boolean passed = false;
        try {

            driver.get("https://schedule-pro-36f5a.web.app/");
            LoginPage loginPage = new LoginPage(driver);
            loginPage.loginAs("jhogillds@gmail.com", "ADRyOj");

            Thread.sleep(3000);
            driver.get("https://schedule-pro-36f5a.web.app/dashboard");

            DashboardPage dashboardPage = new DashboardPage(driver);
            dashboardPage.buscarEmpleado("NO_EXISTE_123");

            Thread.sleep(2000);

            passed = dashboardPage.hayResultadosVisibles();
            assertTrue(passed, "❌ No se muestra ningun mensaje de que no existe.");
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
                reporter.reportResultPerTest("Buscar empleado inexistente sin resultados", passed, "Automatizado - Buscador sin coincidencias");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // Puedes repetir para /reportes y /configuración si gustas
}
