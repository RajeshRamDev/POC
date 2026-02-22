

package Pages;

import CommonBase.Utils;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.File;
import java.io.IOException;

public class BookingPage extends Utils {
    //LOCATORS//

    public By ClosePopup = By.xpath("//div[@class='pb-1 px-1 flex flex-middle nmx-1']");
    public By ClickBusTab = By.xpath("//p[text()='Buses']");

    public By FromCity = By.id("departure");
    public By FromInput = By.xpath("(//div[@class='py-3 px-6 c-pointer item-hover'])[1]");

    public By ToInput = By.id("arrival");
    public By ToInputClick = By.xpath("(//div[@class='py-3 px-6 c-pointer item-hover'])[1]");

    public By TravelDate = By.xpath("//div[@class='w-70p']");
    public By NextTravelDate = By.xpath("//button[contains(@class,'rdp-day_today')]/following::button[contains(@class,'rdp-day') and not(contains(@class,'rdp-day_disabled'))][1]");

    public By Search = By.xpath("//button[text()='Search Buses']");
    public By DepartureTime = By.xpath("(//div[@class='flex py-3'])[4]");
    public By SelectAc = By.xpath("//div[@data-testid='pill-wrapper']//p[normalize-space()='AC']");
    public By SleeperPill = By.xpath("//div[@data-testid='pill-wrapper']//p[normalize-space()='Sleeper']");

    //    Bus Selection
    public By SelectBus = By.xpath("//div[@id='0_0']//button[@class='sc-dcJsrY itZaiI']");

    public By lowerCanvas = By.xpath("//div[contains(@class,'lower')]//canvas");

    public By ContinueButton = By.xpath("//button[contains(text(),'Continue')]");

    public By pick = By.id("Pick-up point");
    public By inputPickup = By.xpath("(//div[contains(@class,'sc-aXZVg fc')])[1]");

    public By drop = By.id("Drop-off point");
    public By inputDrop = By.xpath("(//div[@class='sc-aXZVg jzmzlN py-3 px-4 c-pointer global-hover'])[1]");

    public By ContinuetoNext = By.xpath("//button[contains(@class,'sc-dcJsrY e')]");

    public By genderMale = By.xpath("(//div[@class='flex']//p[@class='sc-gEvEer caysYx flex flex-middle mr-1 c-pointer'])[1]");
    public By genderFemale = By.xpath("//div[contains(@class,'sc-aXZVg ga')]//div[contains(@class,'flex flex-c')]");
    public By Firstname = By.id("firstName_0");
    public By Lastname = By.id("lastNmae_0");
    public By Age = By.id("age_0");

    public By Mobile = By.id("mobile");
    public By Email = By.id("email");

    public By Review = By.xpath("//button[contains(@class,'sc-dcJsrY c')]");

    public By Payment = By.xpath("//button[@class='sc-dcJsrY czbNFO']");


    public By completePayment = By.xpath("//h1[@class='fs-7 fw-600 c-neutral-900']");

    //METHODS

    public void Popup() {
        wait.until(ExpectedConditions.elementToBeClickable(ClosePopup)).click();
    }

    public void BusTab() {
        wait.until(ExpectedConditions.elementToBeClickable(ClickBusTab)).click();
    }

    public void Fromcity(String city) {
        WebElement from = wait.until(ExpectedConditions.visibilityOfElementLocated(FromCity));
        from.clear();
        from.sendKeys(city);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        wait.until(ExpectedConditions.elementToBeClickable(FromInput)).click();
    }

    public void ToCity(String city) {
        WebElement to = wait.until(ExpectedConditions.visibilityOfElementLocated(ToInput));
        to.clear();
        to.sendKeys(city);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        wait.until(ExpectedConditions.elementToBeClickable(ToInputClick)).click();
    }

    public void SelectDate() {
        wait.until(ExpectedConditions.elementToBeClickable(TravelDate)).click();
        wait.until(ExpectedConditions.elementToBeClickable(NextTravelDate)).click();
    }

    public void SearchBus() {
        wait.until(ExpectedConditions.elementToBeClickable(Search)).click();
    }

    public void ApplyFilters() {
        wait.until(ExpectedConditions.elementToBeClickable(DepartureTime)).click();
        wait.until(ExpectedConditions.elementToBeClickable(SelectAc)).click();
        wait.until(ExpectedConditions.elementToBeClickable(SleeperPill)).click();
    }

//SELECT BUS
    public void SelectFirstBus() {
        wait.until(ExpectedConditions.elementToBeClickable(SelectBus)).click();
    }

   //SELECT SEAT



    public void selectLowestPriceSeatOnLowerDeck() {
        // 1. Wait for canvas and scroll it into view
        System.out.println("sucess 7");
        WebElement canvas = wait.until(ExpectedConditions.visibilityOfElementLocated(lowerCanvas));
        System.out.println("sucess 8");
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", canvas);
        System.out.println("sucess 9");
        int w = canvas.getSize().getWidth();
        int h = canvas.getSize().getHeight();

        // 2. Define Y-coordinates for typical seat rows (Top, Middle-ish, Bottom)
        // Most buses have seats at roughly 20%, 40%, 70%, and 85% of the canvas height
        double[] yPercents = {
                0.20,
                0.40,
                0.75,
                0.90

        };

        // 3. Define X-coordinates (columns) - usually 10-12 columns
//        int columns = 12;   //  4 rows × 12 columns = 48 tries
        int columns = 3;
        boolean seatClicked = false;
        for (double yPercent : yPercents) {
            int y = (int) (h * yPercent);
            for (int i = 1; i <= columns /3; i++) {
                int x = (w / columns) * i - (w / (columns * 2)); // Click center of the column
                clickCanvasCoordinate(canvas, x, y);
                // Brief pause for the 'Continue' button to react
                try {
                    Thread.sleep(400);
                } catch (InterruptedException ignored) {
                }
                if (isSeatSelected()) {
                    System.out.println("Lower deck seat selected at X: " + x + " Y: " + y);
                    seatClicked = true;
                    break;
                }
            }
            if (seatClicked) break;
        }
        System.out.println("check");
        try {
            Thread.sleep(4000);
        } catch (InterruptedException ignored) {
        }
    }


    //    Type 1
    private boolean isSeatSelected() {
        try {
            WebElement btn = driver.findElement(ContinueButton);
            return btn.isDisplayed() && btn.isEnabled();
        } catch (Exception e) {
            return false;

        }
    }


    private void clickCanvasCoordinate(WebElement canvas, int xOffset, int yOffset) {

        int centerXOffset = xOffset - (canvas.getSize().getWidth() / 2);
        int centerYOffset = yOffset - (canvas.getSize().getHeight() / 2);

        new org.openqa.selenium.interactions.Actions(driver).moveToElement(canvas, centerXOffset, centerYOffset).click().perform();
    }



    public void pickUpPoint(String up) throws InterruptedException {
        Thread.sleep(3000);
        WebElement pickup = wait.until(ExpectedConditions.visibilityOfElementLocated(pick));
        pickup.sendKeys(up);
        Thread.sleep(3000);
        wait.until(ExpectedConditions.elementToBeClickable(inputPickup)).click();
        Thread.sleep(3000);
    }

    public void dropOffPoint(String off) throws InterruptedException {
        Thread.sleep(3000);
        WebElement pickup = wait.until(ExpectedConditions.visibilityOfElementLocated(drop));
        pickup.sendKeys(off);
        Thread.sleep(3000);
        wait.until(ExpectedConditions.elementToBeClickable(inputDrop)).click();
        Thread.sleep(3000);
    }

    public void Continuetonext() throws InterruptedException {
        Thread.sleep(3000);
        wait.until(ExpectedConditions.elementToBeClickable(ContinuetoNext)).click();
        Thread.sleep(3000);
    }

      //TRAVELLER DETAILS

    public void GenderMale() {
        wait.until(ExpectedConditions.elementToBeClickable(genderMale)).click();
    }

    public void GenderFemale() {
        wait.until(ExpectedConditions.elementToBeClickable(genderFemale)).click();
    }

    public void FistName(String fname) throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(Firstname)).sendKeys(fname);
    }

    public void LastName(String lname) throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(Lastname)).sendKeys(lname);
    }

    public void AGE(String age) throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(Age)).sendKeys(age);
    }

    public void MobileNumber(String number) throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(Mobile)).sendKeys(number);
    }

    public void EmailAddress(String email) throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(Email)).sendKeys(email);
    }
   //REVIEW

    public void review() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(Review)).click();
        Thread.sleep(3000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'flex-column')]")));
        // Travels Name
        String travelsName = driver.findElement(By.xpath("(//div[contains(@class,'flex-column')]//p)[1]")).getText();
        // Bus Type
        String busType = driver.findElement(By.xpath("(//div[contains(@class,'flex-column')]//p)[2]")).getText();
        // Date
        String journeyDate = driver.findElement(By.xpath("(//p[contains(text(),',')])[2]")).getText();
        // From City
        String fromCity = driver.findElement(By.xpath("(//div[contains(@class,'sc-aXZVg bMkrZJ')]//p)[1]")).getText();
        // Departure Time
        String departureTime = driver.findElement(By.xpath("(//div[contains(@class,'sc-aXZVg bMkrZJ')]//h1)[1]")).getText();
        // Pickup Point
        String pickupPoint = driver.findElement(By.xpath("(//div[contains(@class,'sc-aXZVg bMkrZJ')]//p)[2]")).getText();
        // Duration
        String duration = driver.findElement(By.xpath("//div[@class='sc-aXZVg gyynpj sc-f6cad7e7-1 iEjIqg p-relative flex flex-middle ']")).getText();
        // Arrival Time
        String arrivalTime = driver.findElement(By.xpath("(//div[contains(@class,'sc-aXZVg bMkrZJ')]//h1)[2]")).getText();
        // To City
        String toCity = driver.findElement(By.xpath("(//div[contains(@class,'sc-aXZVg bMkrZJ')]//p)[3]")).getText();
        // Drop Point
        String dropPoint = driver.findElement(By.xpath("(//div[contains(@class,'sc-aXZVg bMkrZJ')]//p)[4]")).getText();
        String baseFare = driver.findElement(By.xpath("//*[contains(text(),'Base fare')]/following::p[1]")).getText();
        String tax = driver.findElement(By.xpath("//*[contains(text(),'Tax')]/following::p[1]")).getText();
        String convenienceFee = driver.findElement(By.xpath("//*[contains(text(),'Convenience fee')]/following::p[1]")).getText();
        String totalAmount = driver.findElement(By.xpath("(//h2[@class='sc-gEvEer dAbDZN'])[2]")).getText();
        // Wait for section
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Review traveller details')]")));
        // Seat Number
        String seatNumber = driver.findElement(By.xpath("//*[contains(text(),'Review traveller details')]/following::p[1]")).getText();
        // Traveller Info (Name, Age, Gender)
        String travellerInfo = driver.findElement(By.xpath("//p[@class='sc-gEvEer kdZUgb ml-1']")).getText();
        // Email
        String email = driver.findElement(By.xpath("//*[contains(text(),'Ticket details will be sent to')]/following::p[1]")).getText();
        // Mobile
        String mobile = driver.findElement(By.xpath("//*[contains(text(),'Ticket details will be sent to')]/following::p[2]")).getText();
        System.out.println("BOOKING DETAILS");
        System.out.println("Travels Name : " + travelsName);
        System.out.println("Bus Type     : " + busType);
        System.out.println("Date         : " + journeyDate);
        System.out.println("From         : " + fromCity + " - " + departureTime);
        System.out.println("Pickup       : " + pickupPoint);
        System.out.println("Duration     : " + duration);
        System.out.println("To           : " + toCity + " - " + arrivalTime);
        System.out.println("Drop Point   : " + dropPoint);
        System.out.println("PRICE STRUCTURE");
        System.out.println("Base Fare        : " + baseFare);
        System.out.println("Tax              : " + tax);
        System.out.println("Convenience Fee  : " + convenienceFee);
        System.out.println("Total Payable    : " + totalAmount);
        System.out.println("TRAVELLER DETAILS");
        System.out.println("Seat Number     : " + seatNumber);
        System.out.println("Traveller       : " + travellerInfo);
        System.out.println("Email           : " + email);
        System.out.println("Mobile          : " + mobile);
    }
    public void ProceedtoPayment () throws InterruptedException {
        Thread.sleep(3000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(Payment)).click();
    }
    public void ViewPayment () throws InterruptedException {
        Thread.sleep(3000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(completePayment));
        Thread.sleep(3000);
    }
    public void takeScreenshot()  {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        File target = new File("target/ScreenShot/ViewPayment.png");
        try {
            FileUtils.copyFile(source, target);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}