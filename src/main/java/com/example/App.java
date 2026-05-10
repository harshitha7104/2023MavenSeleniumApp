package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class App {
    public static void main(String[] args) {
        ChromeOptions options = new ChromeOptions();
        
        // CORE FLAGS FOR JENKINS/LINUX
        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox"); // Fixes Root user issues
        options.addArguments("--disable-dev-shm-usage"); // Fixes memory issues in VMs
        
        // EXTRA STABILITY FLAGS
        options.addArguments("--disable-gpu"); // Fixes VirtualBox/Hardware acceleration crashes
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--user-data-dir=/tmp/chrome-user-data"); // Prevents profile lock errors

        WebDriver driver = new ChromeDriver(options);

        try {
            System.out.println("Browser started successfully!");
            driver.get("https://www.saucedemo.com/");
            
            driver.findElement(By.id("user-name")).sendKeys("standard_user");
            driver.findElement(By.id("password")).sendKeys("secret_sauce");
            driver.findElement(By.id("login-button")).click();
            
            System.out.println("Current URL: " + driver.getCurrentUrl());
            System.out.println("Build Success - Final Verification complete.");
        } catch (Exception e) {
            System.out.println("Error during execution: " + e.getMessage());
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
