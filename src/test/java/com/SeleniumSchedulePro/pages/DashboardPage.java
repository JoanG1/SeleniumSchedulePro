package com.SeleniumSchedulePro.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class DashboardPage {

    private WebDriver driver;

    // Paneles
    private By totalEmpleadosPanel = By.xpath("//div[contains(text(),'Total empleados')]/ancestor::div[contains(@class,'card')]");
    private By departamentosPanel = By.xpath("//div[contains(text(),'Departamentos')]/ancestor::div[contains(@class,'card')]");
    private By departamentoPrincipalPanel = By.xpath("//div[contains(text(),'Departamento principal')]/ancestor::div[contains(@class,'card')]");
    private By nuevosEmpleadosPanel = By.xpath("//div[contains(text(),'Nuevos empleados')]/ancestor::div[contains(@class,'card')]");

    // Navegación superior
    private By horariosLink = By.linkText("Horarios");
    private By reportesLink = By.linkText("Reportes");
    private By configuracionLink = By.linkText("Configuración");

    // 👇 Agrega esto dentro de DashboardPage.java

    private By searchInput = By.xpath("//input[@placeholder='Buscar empleados...']");
    private By tablaResultados = By.xpath("//table//tbody//tr"); // Asumiendo tabla con <tr> para empleados
    private By mensajeSinResultados = By.xpath("//*[contains(text(),'No hay resultados') or contains(text(),'sin resultados')]");

    private By botonGuardar = By.xpath("//button[contains(text(),'Guardar')]");
    private By botonConfirmarEliminar = By.xpath("//button[contains(text(),'Confirmar')]");

    public void buscarEmpleado(String nombre) {
        WebElement input = driver.findElement(searchInput);
        input.clear();
        input.sendKeys(nombre);
    }

    public boolean hayResultadosVisibles() {
        try {
            return driver.findElements(tablaResultados).size() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean sinResultadosVisibles() {
        return driver.findElements(mensajeSinResultados).size() > 0;
    }

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
    }

    // Métodos para paneles
    public void clickTotalEmpleadosPanel() {
        driver.findElement(totalEmpleadosPanel).click();
    }

    public void clickDepartamentosPanel() {
        driver.findElement(departamentosPanel).click();
    }

    public void clickDepartamentoPrincipalPanel() {
        driver.findElement(departamentoPrincipalPanel).click();
    }

    public void clickNuevosEmpleadosPanel() {
        driver.findElement(nuevosEmpleadosPanel).click();
    }

    public boolean isPanelClickable(By panelLocator) {
        WebElement panel = driver.findElement(panelLocator);
        return panel.isDisplayed() && panel.isEnabled();
    }

    public By getTotalEmpleadosPanelLocator() {
        return totalEmpleadosPanel;
    }

    public By getDepartamentosPanelLocator() {
        return departamentosPanel;
    }

    public By getDepartamentoPrincipalPanelLocator() {
        return departamentoPrincipalPanel;
    }

    public By getNuevosEmpleadosPanelLocator() {
        return nuevosEmpleadosPanel;
    }

    // Métodos de navegación
    public void clickHorarios() {
        driver.findElement(horariosLink).click();
    }

    public void clickReportes() {
        driver.findElement(reportesLink).click();
    }

    public void clickConfiguracion() {
        driver.findElement(configuracionLink).click();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }


}
