package pizza.project.demo0;

/**
 * Represents an address associated with a user or entity.
 */
public class Addresses {

    private int ownerID;
    private int addressType;
    private String streetAddress;
    private String apartment;
    private String city;
    private String state;
    private int zip;
    private String phone;

    /**
     * Constructs an Addresses instance with specified details.
     *
     * @param ownerID the identifier of the owner of this address
     * @param addressType the type of address (e.g., 0 for home, 1 for work)
     * @param streetAddress the street address
     * @param apartment the apartment number or identifier
     * @param city the city of the address
     * @param state the state of the address
     * @param zip the postal code
     * @param phone the contact phone number
     */
    public Addresses(int ownerID, int addressType, String streetAddress, String apartment,
                     String city, String state, int zip, String phone) {
        this.ownerID = ownerID;
        this.addressType = addressType;
        setStreetAddress(streetAddress);
        setApartment(apartment);
        setCity(city);
        setState(state);
        setZip(zip);
        setPhone(phone);
    }

    // Getters and setters with validation examples for street address and zip code
    public int getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(int ownerID) {
        this.ownerID = ownerID;
    }

    public int getAddressType() {
        return addressType;
    }

    public void setAddressType(int addressType) {
        this.addressType = addressType;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setStreetAddress(String streetAddress) {
        if (streetAddress != null && !streetAddress.isEmpty()) {
            this.streetAddress = streetAddress;
        } else {
            throw new IllegalArgumentException("Invalid street address.");
        }
    }

    // Additional getters and setters with similar validation logic...

    @Override
    public String toString() {
        // Replace natural commas with full-width commas to avoid CSV conflicts
        return String.format("%d,%d,%s,%s,%s,%s,%d,%s",
                ownerID,
                addressType,
                streetAddress.replace(",", "，"),
                apartment.replace(",", "，"),
                city.replace(",", "，"),
                state.replace(",", "，"),
                zip,
                phone.replace(",", "，"));
    }

    public String getTitle() {
        return String.format("%s %s, %s, %s, %d",
                streetAddress, apartment, city, state, zip);
    }
}
