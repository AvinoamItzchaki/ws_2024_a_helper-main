public class Client {
    private int lineNumber;
    private String id;
    private String firstName;
    private String lastName;
    private int yearBorn;
    private String gender;
    private String Hometown;
    private int purchaseAmount;

    //   [19999, 27866548, Nicolas, Ratke, 1992, Female, Renaldoside, 838]


    public Client(int lineNumber, String id, String firstName, String lastName, int yearBorn, String gender, String hometown, int purchaseAmount) {
        this.lineNumber = lineNumber;
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.yearBorn = yearBorn;
        this.gender = gender;
        Hometown = hometown;
        this.purchaseAmount = purchaseAmount;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getYearBorn() {
        return yearBorn;
    }

    public void setYearBorn(int yearBorn) {
        this.yearBorn = yearBorn;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHometown() {
        return Hometown;
    }

    public void setHometown(String hometown) {
        Hometown = hometown;
    }

    public int getPurchaseAmount() {
        return purchaseAmount;
    }

    public void setPurchaseAmount(int purchaseAmount) {
        this.purchaseAmount = purchaseAmount;
    }

    @Override
    public String toString() {
        return "Client{" +
                "lineNumber=" + lineNumber +
                ", id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", yearBorn=" + yearBorn +
                ", gender='" + gender + '\'' +
                ", Hometown='" + Hometown + '\'' +
                ", purchaseAmount=" + purchaseAmount +
                '}';
    }
}
