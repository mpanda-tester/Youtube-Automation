package demo;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;

import demo.utils.ExcelDataProvider;
// import io.github.bonigarcia.wdm.WebDriverManager;
import demo.wrappers.Wrappers;

public class TestCases extends ExcelDataProvider{ // Lets us read the data
        ChromeDriver driver;

        /*
         * TODO: Write your tests here with testng @Test annotation.
         * Follow `testCase01` `testCase02`... format or what is provided in
         * instructions
         */

        /*
         * Do not change the provided methods unless necessary, they will help in
         * automation and assessment
         */
        @BeforeTest
        public void startBrowser() {
                System.setProperty("java.util.logging.config.file", "logging.properties");

                // NOT NEEDED FOR SELENIUM MANAGER
                // WebDriverManager.chromedriver().timeout(30).setup();

                ChromeOptions options = new ChromeOptions();
                LoggingPreferences logs = new LoggingPreferences();

                logs.enable(LogType.BROWSER, Level.ALL);
                logs.enable(LogType.DRIVER, Level.ALL);
                options.setCapability("goog:loggingPrefs", logs);
                options.addArguments("--remote-allow-origins=*");

                System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "build/chromedriver.log");

                driver = new ChromeDriver(options);

                driver.manage().window().maximize();
                
        }

        @Test
        public void testCase01(){
                System.out.println("Start of testCase01");
                try {
                //Navigated to youtube and validate the Url
                driver.get("https://www.youtube.com/");
                Thread.sleep(5000);
                String url = driver.getCurrentUrl();
                Assert.assertTrue(url.contains("youtube"));
                //Click on about link
                WebElement aboutBtn=driver.findElement(By.xpath("//a[contains(text(),'About')]"));
                Wrappers.clickOnElement(aboutBtn, driver);

                WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));
                wait.until(ExpectedConditions.urlContains("about"));

              //Print the text present in about section
                WebElement text1= driver.findElement(By.xpath("//h1[@class='lb-font-display-1 lb-font-weight-700 lb-font-color-text-primary lb-font--no-crop']"));
                WebElement text3=driver.findElement(By.xpath("(//p[contains(@class,'lb-font-display-3 lb-font-color-text-primary')])[2]"));
               WebElement text2=driver.findElement(By.xpath("(//p[contains(@class,'lb-font-display-3 lb-font-color-text-primary')])[1]"));
               String text2_new= text2.getText();
               String text_new=text1.getText();
               String text3_new=text3.getText();
        //Print the entire message about youtube
               if(text_new.contains("About")){
                System.out.println(text2_new + "," +text3_new);
               }
                        
                } catch (Exception e) {
                        e.printStackTrace();
                }
                System.out.println("End of testCase01");

        }
       @Test
        public void testCase02(){
                System.out.println("Start of testCase02");
                try {
                       //Navigate to youtube
                       driver.get("https://www.youtube.com/");
                       Thread.sleep(5000);
                       //Thread.sleep(6000);

                        //Click on films tab

                        //Create awrapper method for film,music and news tab
                        WebElement filmsBtn=driver.findElement(By.xpath("//yt-formatted-string[contains(text(),'Movies')]"));
                        Wrappers.clickOnElement(filmsBtn, driver);

                        Thread.sleep(5000);

                         
                        ////span[@id='title' and contains(text(),'Top selling')]/../../../../../../following-sibling::div//button[@aria-label='Next']
                        //Write xpath as per top selling header
                        WebElement moveBtn=driver.findElement(By.xpath("//span[@id='title' and contains(text(),'Top selling')]/../../../../../../following-sibling::div//button[@aria-label='Next']"));
                        while (moveBtn.isDisplayed()) {
                                Wrappers.clickOnElement(moveBtn, driver);
                        }

                        Thread.sleep(5000);
                        WebElement gener = driver.findElement(By.xpath("(//div[contains(@class,'badge  badge-style-type-simple')]/p)[15]"));
                        String generText=gener.getText();
                        Thread.sleep(5000);
                        WebElement movitType = driver.findElement(By.xpath("(//span[@class='grid-movie-renderer-metadata style-scope ytd-grid-movie-renderer'])[15]"));
                        String moviTypeText=movitType.getText();

                        System.out.println(moviTypeText);
                        System.out.println(generText);
                        SoftAssert softAssert = new SoftAssert();

                       softAssert.assertTrue(generText.contains("A"));
                       softAssert.assertTrue(moviTypeText.contains("Animation")|| moviTypeText.contains("Comedy "));

                      softAssert.assertAll();
                } catch (Exception e) {
                        // TODO: handle exception
                        e.printStackTrace();
                }
                System.out.println("End of testCase02");
        }
        @Test
        public void testCase03(){
                System.out.println("Start of testCase03");
                try {
                        //Navigate to youtube
                       driver.get("https://www.youtube.com/");
                       Thread.sleep(5000);

                       //Click on music tab
                       WebElement musicBtn=driver.findElement(By.xpath("//yt-formatted-string[text()='Music']"));
                       Wrappers.clickOnElement(musicBtn, driver);

                       Thread.sleep(5000);
                        //Create a xpath as per india biggest it
                       Wrappers.scrollToRight(driver, "Biggest Hits");
                
                   WebElement trackbtn= driver.findElement(By.xpath("(//p[@id='video-count-text'])[11]"));
                   String trackText=trackbtn.getText().replaceAll("[^0-9]", "").trim();
                   int trackCount=Integer.parseInt(trackText);
                   WebElement title=driver.findElement(By.xpath("(//h3[@class='style-scope ytd-compact-station-renderer'])[11]"));
                   String titleText=title.getText();
                   System.out.println("Total count : "+trackCount);
                   System.out.println("Title : "+titleText);
                   SoftAssert softAssert=new SoftAssert();
                   softAssert.assertTrue(trackCount <= 50, "Playlist " + titleText + " has more than 50 tracks.");
                   softAssert.assertAll();
                        
                } catch (Exception e) {
                        // TODO: handle exception
                        e.printStackTrace();
                }

                System.out.println("End of testCase03");
        }

        @Test
        public void testCase04(){
                System.out.println("Start of testCase04");
                try {
                         //Navigate to youtube
                       driver.get("https://www.youtube.com/");
                       Thread.sleep(5000);

                       //Click on news tab
                       WebElement newsBtn=driver.findElement(By.xpath("//yt-formatted-string[text()='News']"));
                       Wrappers.clickOnElement(newsBtn, driver);
                       //Print the title,body,like count
               List<WebElement> newsList=driver.findElements(By.xpath("//ytd-post-renderer[@class='style-scope ytd-rich-item-renderer' ]"));
               int totalVoteCount =0;
               for(int i=0;i<3;i++){
                WebElement title=newsList.get(i).findElement(By.xpath("./div/div/div/a[@id='author-text']/span"));
                String titleText=title.getText();
                System.out.println(titleText);

                WebElement body=newsList.get(i).findElement(By.xpath(".//div[@id='body']/div/yt-formatted-string"));
                String bodyText=body.getText();
                System.out.println(bodyText);

                WebElement likeText=newsList.get(i).findElement(By.xpath(".//div[@id='toolbar']//span[@id='vote-count-middle']"));
               String liikeTexts=likeText.getText().trim();

               int count = liikeTexts.isEmpty()? 0 : Integer.parseInt(liikeTexts);
               totalVoteCount+=count;
               System.out.println(totalVoteCount);
               
        }
                

                } catch (Exception e) {
                        // TODO: handle exception
                        e.printStackTrace();
                }


                System.out.println("End of testCase04");
        }

        @Test
        public void testCase05(){
                System.out.println("Start of testCase05");
                  try {
                          //Navigate to youtube
                       driver.get("https://www.youtube.com/");
                       Thread.sleep(5000);

                       WebElement searchBox = driver.findElement(By.xpath("//input[@placeholder='Search']"));
                       Wrappers.enterText(searchBox, "Movies");

                       WebElement clickBtn=driver.findElement(By.xpath("//button[@id='search-icon-legacy']"));
                       Wrappers.clickOnElement(clickBtn, driver);

                       WebElement searchBox1 = driver.findElement(By.xpath("//input[@placeholder='Search']"));
                       Wrappers.enterText(searchBox1, "Music");

                       Wrappers.clickOnElement(clickBtn, driver);

                       WebElement searchBox2 = driver.findElement(By.xpath("//input[@placeholder='Search']"));
                       Wrappers.enterText(searchBox2, "Games");
                       Wrappers.clickOnElement(clickBtn, driver);

        //                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        //     wait.until(ExpectedConditions.urlContains(""));
                     

                  } catch (Exception e) {
                        // TODO: handle exception
                        e.printStackTrace();
                  }
                System.out.println("End of testCase05");
        }

       @AfterTest
        public void endTest() {
                driver.close();
                driver.quit();

        }
public class ExcelDataProvider {

    @DataProvider(name = "videoSearchData")
    public Object[][] getData() throws IOException {
        String excelFilePath = "src/test/resources/data.xlsx";
        FileInputStream fileInputStream = new FileInputStream(excelFilePath);
        Workbook workbook = WorkbookFactory.create(fileInputStream);
        Sheet sheet = workbook.getSheetAt(0);

        List<Object[]> data = new ArrayList<>();
        Iterator<Row> rowIterator = sheet.rowIterator();
        rowIterator.next(); // Skip header row
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            data.add(new Object[]{row.getCell(1).getStringCellValue()});
        }

        fileInputStream.close();
        return data.toArray(new Object[0][]);
    }
}
}