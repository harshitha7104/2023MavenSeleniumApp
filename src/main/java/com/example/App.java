package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class App 
{
    public static void main( String[] args )
    {
        // Setup options to run in the background
        ChromeOptions options = new ChromeOptions();
        
        // This is the magic line that fixes the Jenkins crash
        options.addArguments("--headless=new"); 
        
        // These are essential for running on Linux VMs
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        WebDriver driver = new ChromeDriver(options);
        
        try {
            driver.get("https://www.saucedemo.com/");
            driver.findElement(By.id("user-name")).sendKeys("standard_user");
            driver.findElement(By.id("password")).sendKeys("secret_sauce");
            driver.findElement(By.id("login-button")).click();
            
            System.out.println("Login executed successfully in Headless Mode!");
        } finally {
            // This cleans up the background processes
            driver.quit();
        }
    }
}
