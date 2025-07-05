package com.teclas;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.*;
import com.github.javafaker.Faker;

import java.time.Duration;

public class AltaSocioTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    void setUp() {
        System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        //options.addArguments("--auto-open-devtools-for-tabs"); // Abrir consola DevTools al iniciar
        driver = new ChromeDriver(options);

        wait = new WebDriverWait(driver, Duration.ofSeconds(15)); // + tiempo por si tarda backend
        driver.manage().window().maximize();
    }

    @Test
    void registrarSocioExitosamente() throws InterruptedException {
        driver.get("http://localhost/TP-TDV-ClubTeclasUnidos/frontend/index.html");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("socioForm")));

        Faker faker = new Faker();

        String dni = String.valueOf(30000000 + faker.number().numberBetween(0, 999999));
        String apellido = faker.name().lastName();
        String nombre = faker.name().firstName();
        String direccion = faker.address().streetAddress();
        String telefono = faker.phoneNumber().cellPhone();

        driver.findElement(By.id("dni")).sendKeys(dni);
        driver.findElement(By.id("apellido")).sendKeys(apellido);
        driver.findElement(By.id("nombre")).sendKeys(nombre);
        driver.findElement(By.id("edad")).clear();
        driver.findElement(By.id("edad")).sendKeys(String.valueOf(faker.number().numberBetween(18, 65)));
        driver.findElement(By.id("fechaNacimiento")).sendKeys("01-01-1990"); // Se podria automatizar
        driver.findElement(By.id("direccion")).sendKeys(direccion);
        driver.findElement(By.id("telefono")).sendKeys(telefono);

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

    /*@AfterEach
    void tearDown() {
        // Comento driver.quit() para que no cierre el navegador automáticamente

        if (driver != null) {
            driver.quit();
        }

    }*/
}
