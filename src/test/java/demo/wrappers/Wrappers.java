package demo.wrappers;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;

public class Wrappers {
   public static boolean enterText(WebElement element,String text){
    try{
        element.clear();
        Thread.sleep(3000);
        element.sendKeys(text);
        return true;

    }catch(Exception e){
        e.printStackTrace();
        return false;
    }

   }

   public static boolean clickOnElement(WebElement element,WebDriver driver){
    try {
        JavascriptExecutor js=(JavascriptExecutor)driver;
        js.executeScript("arguments[0].scrollIntoView();",element);
        element.click();
        Thread.sleep(3000);
        return true;
        
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
   }


   public static void scrollToRight( WebDriver driver,String sectionName) {
    try {
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement sectionElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@id='title' and contains(text(),'" + sectionName + "')]")));
 
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", sectionElement);

        WebElement nextButtonElement = wait
                .until(ExpectedConditions
                        .visibilityOfElementLocated(By.xpath("//span[@id='title' and contains(text(),'"
                                + sectionName
                                + "')]//ancestor::div[@id='dismissible']//child::button[@aria-label='Next']")));

        while (nextButtonElement.isDisplayed()) {
            nextButtonElement.click();
            //System.out.println("Next click is Done");
        }
    } catch (Exception e) {
        System.out.println(e.getMessage());
    }
}



}
