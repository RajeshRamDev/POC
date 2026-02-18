package Steps;

import CommonBase.Utils;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import Pages.BookingPage;
import reader.ConfigReader;



public class BookingSteps extends Utils {
    BookingPage booking = new BookingPage();

    @Given("User opens Cleartrip website")
    public void user_opens_cleartrip_website() {
        launchUrl(ConfigReader.getProp("url"));
    }

    @When("User closes the popup")
    public void user_closes_the_popup() {
        booking.Popup();
    }

    @When("User clicks on Buses tab")
    public void user_clicks_on_buses_tab() {
        booking.BusTab();
    }

    @When("User enters source city as {string}")
    public void user_enters_source_city_as(String from) throws InterruptedException {
        booking.Fromcity(from);
    }

    @When("User enters destination city as {string}")
    public void user_enters_destination_city_as(String to) throws InterruptedException {
        booking.ToCity(to);
    }

    @When("User selects next available travel date")
    public void user_selects_next_available_travel_date() throws InterruptedException {
        booking.SelectDate();
    }

    @When("User clicks on Search button")
    public void user_clicks_on_search_button() throws InterruptedException {
        booking.SearchBus();
    }

    @When("User selects Applyfilter")
    public void user_selects_applyfilter() throws InterruptedException {
        booking.ApplyFilters();
    }

    @When("user selects Bus")
    public void user_selects_bus() throws InterruptedException {

        booking.SelectFirstBus();
    }

    @When("User selects seat from lower deck")
    public void user_selects_seat_from_lower_deck() throws InterruptedException {
        booking.selectLowestPriceSeatOnLowerDeck();

    }

    @When("User enters pickup location {string}")
    public void user_enters_pickup_location(String pickupLocation) throws InterruptedException {
        booking.pickUpPoint(pickupLocation);
    }

    @When("User enters drop off location {string}")
    public void user_enters_drop_off_location(String dropOffLocation) throws InterruptedException {
        booking.dropOffPoint(dropOffLocation);
    }

    @When("User clicks on Continue To Next Page")
    public void user_clicks_on_continue_to_next_page() throws InterruptedException {
        booking.Continuetonext();
    }
    @When("User Select Gender Male")
    public void user_select_gender_male() {
        booking.GenderMale();
    }
    @When("User Select Gender Female")
    public void user_select_gender_female() {
        booking.GenderFemale();
    }
    @When("User Enter First Name {string}")
    public void user_enter_first_name(String FIRSTNAME) throws InterruptedException {
        booking.FistName(FIRSTNAME);
    }
    @When("User Enter Last Name {string}")
    public void user_enter_last_name(String LASTNAME) throws InterruptedException {
        booking.LastName(LASTNAME);
    }
    @When("User Enter Age {string}")
    public void user_enter_age(String AgeDetails) throws InterruptedException {
        booking.AGE(AgeDetails);
    }
    @When("User Enter Mobile No {string}")
    public void user_enter_mobile_no(String MobileNo) throws InterruptedException {
        booking.MobileNumber(MobileNo);
    }
    @When("User Enter Email {string}")
    public void user_enter_email(String EMAIL) throws InterruptedException {
        booking.EmailAddress(EMAIL);
    }
    @When("User Click Review")
    public void user_click_review() throws InterruptedException {
        booking.review();
    }
    @When("User Click Continue to Payment")
    public void user_click_continue_to_payment() throws InterruptedException {
        booking.ProceedtoPayment();
    }
    @Then("User Pay To Complete Your Booking")
    public void user_pay_to_complete_your_booking() throws InterruptedException {
        booking.ViewPayment();
        booking.takeScreenshot();
    }
}