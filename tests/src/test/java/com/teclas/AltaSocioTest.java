package com.teclas;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

import java.nio.file.Paths;
import java.time.Duration;

public class AltaSocioTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    void setUp() {
        System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
        driver = new ChromeDriver();

        // Espera explícita (hasta 10 segundos para encontrar elementos)
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.manage().window().maximize();
    }

    @Test
    void registrarSocioExitosamente() {
        // Cargar el archivo HTML local
        String filePath = Paths.get("frontend/index.html").toAbsolutePath().toUri().toString();
        driver.get(filePath);

        // Esperar que el formulario esté cargado antes de continuar
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("socioForm")));

        // Completar el formulario
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dni"))).sendKeys("33445566");
        driver.findElement(By.id("apellido")).sendKeys("Ramirez");
        driver.findElement(By.id("nombre")).sendKeys("Carla");
        driver.findElement(By.id("edad")).sendKeys("30");
        driver.findElement(By.id("fechaNacimiento")).sendKeys("1994-04-22");
        driver.findElement(By.id("direccion")).sendKeys("Calle Siempre Viva 123");
        driver.findElement(By.id("telefono")).sendKeys("123456789");

        // Enviar el formulario
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[type='submit']"))).click();

        // Verificar el mensaje de éxito
        WebElement mensaje = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("mensaje")));
        Assertions.assertTrue(mensaje.getText().toLowerCase().contains("socio registrado"),
                "No se encontró el mensaje de éxito esperado.");
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
