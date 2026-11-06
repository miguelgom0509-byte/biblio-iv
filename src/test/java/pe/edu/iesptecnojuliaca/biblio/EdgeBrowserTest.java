package pe.edu.iesptecnojuliaca.biblio;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

import java.io.File;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class EdgeBrowserTest {

    @LocalServerPort
    int port;

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    void setUp() {
        // 1. Forzar a Selenium a usar tu msedgedriver local (Validando que exista)
        String rutaDriver = "E:/Selenium/Driver/edgedriver_win64/msedgedriver.exe";
        File driverFile = new File(rutaDriver);

        if (!driverFile.exists()) {
            throw new IllegalStateException("❌ NO SE ENCUENTRA EL DRIVER EN: " + rutaDriver);
        }
        System.setProperty("webdriver.edge.driver", rutaDriver);

        // 2. Configurar opciones de Edge
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--start-maximized");
        // options.addArguments("--headless=new");

        // 3. Inicializar el driver
        driver = new EdgeDriver(options);

        // 4. Configurar esperas
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @DisplayName("Probar la app BiblioTech usando Microsoft Edge")
    void testConEdge() {
        // Navegar a la página principal
        driver.get("http://localhost:" + port + "/");

        // 1. By.id: Encontrar el título
        WebElement titulo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("titulo")));
        assertEquals("BiblioTech | Sistema de Gestión", titulo.getText());
        System.out.println("✅ Título verificado con By.id");

        // 2. FIX: Usar By.id en lugar de By.tagName("p") para evitar ambigüedades
        WebElement subtitulo = driver.findElement(By.id("subtitulo"));
        assertEquals("Bienvenido al sistema", subtitulo.getText());
        System.out.println("✅ Subtítulo verificado con By.id");

        // 3. By.xpath: Encontrar el botón y hacer clic
        WebElement botonSaludo = driver.findElement(By.xpath("//button[text()='Saludar']"));
        assertTrue(botonSaludo.isDisplayed(), "El botón debe estar visible");
        botonSaludo.click();
        System.out.println("✅ Botón clickeado con By.xpath");

        // 4. FIX: Esperar a que el TEXTO cambie, no solo a que el elemento sea visible
        // Usamos textToBePresentInElementLocated para evitar la condición de carrera
        boolean textoCambio = wait.until(ExpectedConditions.textToBePresentInElementLocated(
                By.cssSelector("#saludo"), "Hola, mundo"));

        assertTrue(textoCambio, "El saludo no se actualizó a tiempo");

        WebElement textoSaludo = driver.findElement(By.cssSelector("#saludo"));
        assertEquals("Hola, mundo", textoSaludo.getText());
        System.out.println("✅ Saludo verificado con By.cssSelector");
    }
}