package Model;

public class Address {

    private City city;
    private String streetOrHouseTtile;

    private int houseNo;

    public City getCity() {
        return city;
    }

    public void setCity(City city) {

        if(city != null){
            this.city = city;
        } else {
            this.city = City.Liepaja;
        }

    }

    public String getStreetOrHouseTtile() {
        return streetOrHouseTtile;
    }

    public void setStreetOrHouseTtile(String streetOrHouseTtile) {
        if(streetOrHouseTtile != null && streetOrHouseTtile.matches("[A-ZĒŪĪĻĶĢŠĀČŅ]{1}[a-zēūīļķģšāžčņ]+")) {
            this.streetOrHouseTtile = streetOrHouseTtile;
        }
        else{
            this.streetOrHouseTtile = "___Empty___";
        }
    }

    public int getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(int houseNo) {
        if(houseNo > 0){
            this.houseNo = houseNo;
        }
    }

    public Address(){
        setCity(City.Liepaja);
        setHouseNo(0);
        setStreetOrHouseTtile("empty");
    }

    public Address(City city, String streetOrHouseTtile, int houseNo) {
        setCity(city);
        setHouseNo(houseNo);
        setStreetOrHouseTtile(streetOrHouseTtile);
    }

    @Override
    public String toString() {
        return "Address{" +
                "city=" + city +
                ", streetOrHouseTtile='" + streetOrHouseTtile + '\'' +
                ", houseNo=" + houseNo +
                '}';
    }
}
