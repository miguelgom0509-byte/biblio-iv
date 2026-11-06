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
        // 1. Forzar a Selenium a usar tu msedgedriver local
        String rutaDriver = "E:/Selenium/Driver/edgedriver_win64/msedgedriver.exe";
        System.setProperty("webdriver.edge.driver", rutaDriver);

        // 2. Configurar opciones de Edge
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--start-maximized"); // Abrir maximizado
        // options.addArguments("--headless=new"); // Descomenta si quieres que corra sin ventana

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
        // Navegar a la página principal de Spring Boot
        driver.get("http://localhost:" + port + "/");

        // --- USO DE LOCALIZADORES By ---

        // 1. By.id: Encontrar el título por su ID
        WebElement titulo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("titulo")));
        assertEquals("BiblioTech | Sistema de Gestión", titulo.getText());
        System.out.println("✅ Título encontrado usando By.id");

        // 2. By.tagName: Encontrar el subtítulo
        WebElement subtitulo = driver.findElement(By.tagName("p"));
        System.out.println("✅ Subtítulo encontrado usando By.tagName: " + subtitulo.getText());

        // 3. By.xpath: Encontrar el botón por su texto exacto
        WebElement botonSaludo = driver.findElement(By.xpath("//button[text()='Saludar']"));
        assertTrue(botonSaludo.isDisplayed());

        // Hacer click en el botón
        botonSaludo.click();
        System.out.println("✅ Botón clickeado usando By.xpath");

        // 4. By.cssSelector: Verificar que el texto cambió
        WebElement textoSaludo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#saludo")));
        assertEquals("Hola, mundo", textoSaludo.getText());
        System.out.println("✅ Saludo verificado usando By.cssSelector");
    }
}
