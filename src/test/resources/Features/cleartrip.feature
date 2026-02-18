Feature: Cleartrip Bus Booking Flow


  Scenario Outline: Launch website and search buses
    Given User opens Cleartrip website
    When User closes the popup
    And User clicks on Buses tab
    And User enters source city as "<FromCity>"
    And User enters destination city as "<ToCity>"
    And User selects next available travel date
    And User clicks on Search button
    And User selects Applyfilter
    And user selects Bus
    And User selects seat from lower deck
    And User enters pickup location "<PickUp>"
    And User enters drop off location "<DropOff>"
    And User clicks on Continue To Next Page
    And User Select Gender Male
#    And User Select Gender Female
    And User Enter First Name "<firstname>"
    And User Enter Last Name "<lastname>"
    And User Enter Age "<agedetails>"
    And User Enter Mobile No "<PhoneNumber>"
    And User Enter Email "<EM>"
    And User Click Review
    And User Click Continue to Payment
    Then User Pay To Complete Your Booking

    Examples:
      | FromCity | ToCity    |  PickUp    | DropOff           | firstname | lastname | agedetails | PhoneNumber | EM                    |
      | Chennai  | Bangalore | Velachery | Attibele TollGate | Rajesh   | R   | 30       | 7812882803  | rajeshramdeveee56@gmail.com   |