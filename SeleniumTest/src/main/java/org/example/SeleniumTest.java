package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SeleniumTest {
    public static void main(String[] args) {
        // Set the WebDriver path
        System.setProperty("webdriver.chrome.driver", "C:\\Drivers\\chromedriver-win64\\chromedriver.exe"); // Cambia esta ruta según tu ubicación del WebDriver

        // Initialize the WebDriver (Chrome in this case)
        WebDriver driver = new ChromeDriver();

        try {
            // Step 1
            driver.get("https://www.qubika.com");

            // Maximize the browser window
            driver.manage().window().maximize();

            // Step 2: Validate URL and logo
            Assert.assertEquals(driver.getCurrentUrl(), "https://qubika.com/", "La URL no coincide.");
            WebElement logo = driver.findElement(By.cssSelector("body > div.overflower > header > div.content > a")); // Reemplaza con el ID correcto
            Assert.assertTrue(logo.isDisplayed(), "El logotipo no se muestra.");

            // Step 3: Click on 'Contact Us'
            WebElement contactButton = driver.findElement(By.cssSelector("body > div.overflower > header > div.content > nav > ul > li.menu-item.menu-item-contact-us.contact-us-main-menu-button.contact-us-modal-open > a")); // Reemplaza con el ID correcto
            contactButton.click();

            Thread.sleep(5000);

            // Step 4: Validate the form
            WebElement nameField = driver.findElement(By.id("label-f")); // Reemplaza con el ID correcto
            WebElement emailField = driver.findElement(By.id("label-e")); // Reemplaza con el ID correcto
            WebElement submitButton = driver.findElement(By.cssSelector("div.actions > input")); // Reemplaza con el ID correcto

            Assert.assertTrue(nameField.isDisplayed(), "El campo Nombre no se muestra.");
            Assert.assertTrue(emailField.isDisplayed(), "El campo Email no se muestra.");
            Assert.assertTrue(submitButton.isDisplayed(), "El botón 'Ponerse en contacto' no se muestra.");

            // Step 5 and 6: Validate errors when submitting an empty form
            submitButton.click();
            WebElement nameError = driver.findElement(By.cssSelector("div.hs_firstname.hs-firstname.hs-fieldtype-text.field.hs-form-field > ul > li > label")); // Reemplaza con el ID correcto
            WebElement emailError = driver.findElement(By.cssSelector("div.hs_email.hs-email.hs-fieldtype-text.field.hs-form-field > ul > li > label")); // Reemplaza con el ID correcto

            Assert.assertTrue(nameError.isDisplayed(), "El mensaje de error para Nombre no se muestra.");
            Assert.assertTrue(emailError.isDisplayed(), "El mensaje de error para Email no se muestra.");

            Thread.sleep(15000);

            // Step 7: Validate that only Name is in red
            String nameBorderColor = nameField.getCssValue("border-box");
            String emailBorderColor = emailField.getCssValue("border-box");
            Assert.assertEquals(nameBorderColor, "rgb(255, 0, 0)", "El campo Nombre no está en rojo.");
            Assert.assertNotEquals(emailBorderColor, "rgb(255, 0, 0)", "El campo Email no debe estar en rojo.");

            // Step 8 and 9: Complete Name and validate
            nameField.sendKeys("Nombre de la prueba");
            submitButton.click();

            // Wait 3 seconds to see the results
            Thread.sleep(15000);


        } catch (Exception e) {
            e.printStackTrace();
        } finally {


            // Close the browser
            driver.quit();
        }
    }
}
