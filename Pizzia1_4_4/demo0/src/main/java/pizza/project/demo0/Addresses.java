package pizza.project.demo0;

public class Addresses {

    int ownerID;
    private int isBillingAddress;
    private String streetAddress;
    private String apartment;
    private String city;
    private String state;
    private int zip;
    private String phone;

    Addresses(int ownerID, int isBillingAddress, String streetAddress, String apartment, String city, String state, int zip, String phone){
        this.ownerID = ownerID;
        this.isBillingAddress = isBillingAddress;
        this.streetAddress = streetAddress;
        this.apartment = apartment;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.phone = phone;
    }

    public int isBillingAddress() {
        return isBillingAddress;
    }

    public void setBillingAddress(int billingAddress) {
        isBillingAddress = billingAddress;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
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

    @Override
    public String toString() {
        // Replace natural commas with full-width commas before writing to file
        return ownerID + "," +
                isBillingAddress + "," +
                streetAddress.replace(",", "，") + "," +
                apartment.replace(",", "，") + "," +
                city.replace(",", "，") + "," +
                state.replace(",", "，") + "," +
                zip + "," +
                phone.replace(",", "，");
    }
}
