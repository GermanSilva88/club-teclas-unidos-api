package com.teclas;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class AltaSocioTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    void setUp() {
        System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--auto-open-devtools-for-tabs"); // Abrir consola DevTools al iniciar
        driver = new ChromeDriver(options);

        wait = new WebDriverWait(driver, Duration.ofSeconds(15)); // + tiempo por si tarda backend
        driver.manage().window().maximize();
    }

    @Test
    void registrarSocioExitosamente() throws InterruptedException {
        driver.get("http://localhost/TP-TDV-ClubTeclasUnidos/frontend/index.html");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("socioForm")));

        driver.findElement(By.id("dni")).sendKeys("33445566");
        driver.findElement(By.id("apellido")).sendKeys("Ramirez");
        driver.findElement(By.id("nombre")).sendKeys("Carla");
        driver.findElement(By.id("edad")).clear();
        driver.findElement(By.id("edad")).sendKeys("31");
        driver.findElement(By.id("fechaNacimiento")).sendKeys("22-04-1994");
        driver.findElement(By.id("direccion")).sendKeys("Calle Siempre Viva 123");
        driver.findElement(By.id("telefono")).sendKeys("123456789");

        driver.findElement(By.cssSelector("form button[type='submit']")).click();

        // Esperar a que el mensaje se vuelva visible y tenga texto
        WebElement mensaje = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("mensaje")));
        wait.until(d -> !mensaje.getText().trim().isEmpty());

        String texto = mensaje.getText().toLowerCase();

        // Quitar emojis y puntuación para comparar
        texto = texto.replaceAll("[^a-zA-Z0-9 ]", "");

        Assertions.assertFalse(texto.isEmpty(), "El mensaje está vacío");
        Assertions.assertTrue(texto.contains("socio registrado exitosamente"), "No se encontró el mensaje de éxito esperado.");

       // System.out.println("Test terminado, presioná CTRL+C o botón STOP para finalizar...");
        //Thread.sleep(Long.MAX_VALUE); // Pausa indefinida para que puedas inspeccionar la consola
    }

    @AfterEach
    void tearDown() {
        // Comento driver.quit() para que no cierre el navegador automáticamente

        if (driver != null) {
            driver.quit();
        }

    }
}
