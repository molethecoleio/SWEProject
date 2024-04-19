package pizza.project.demo0;

public class Cards {
    private int cardNumbers;
    private int expMonth;
    private int expYear;
    private int CVV;
    private String name;

    Cards(int cardNumbers, int expMonth, int expYear, int CVV, String name){
        this.cardNumbers = cardNumbers;
        this.expMonth = expMonth;
        this.expYear = expYear;
        this.CVV = CVV;
        this.name = name;
    }


<<<<<<< Updated upstream
=======
    public String getFinalTitle(){
        String stringNum = String.valueOf(cardNumbers);
        return cardProvider + " " + stringNum.substring(0,3) + "*";
    }

    public String getCardProvider() {
        return cardProvider;
    }

    public void setCardProvider(String cardProvider) {
        this.cardProvider = cardProvider;
    }

    public int getOwnerUserId() {
        return ownerUserId;
    }

    public void setOwnerUserId(int ownerUserId) {
        this.ownerUserId = ownerUserId;
    }

    public String getCardNumbers() {
        return cardNumbers;
    }

    public void setCardNumbers(String cardNumbers) {
        this.cardNumbers = cardNumbers;
    }

    public int getExpMonth() {
        return expMonth;
    }

    public void setExpMonth(int expMonth) {
        this.expMonth = expMonth;
    }

    public int getExpYear() {
        return expYear;
    }

    public void setExpYear(int expYear) {
        this.expYear = expYear;
    }

    public int getCVV() {
        return CVV;
    }

    public void setCVV(int CVV) {
        this.CVV = CVV;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
>>>>>>> Stashed changes
}
