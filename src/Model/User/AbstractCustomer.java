package Model.User;

import Model.Address;
import Model.City;
import Model.Parcel;

import java.util.ArrayList;

public abstract class AbstractCustomer{


    private long cID;

    private Address address;

    private String phoneNo;

    protected String customerCode;

    private long counter = 0;

    private ArrayList<Parcel> parcels = new ArrayList<>();

    public long getcID() {
        return cID;
    }

    public void setcID() {
        this.cID = counter;
        counter++;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        if(address != null){
            this.address = address;
        }else{
            this.address = new Address();
        }

    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        if(phoneNo != null && phoneNo.matches(("([0-9]+)")) && phoneNo.length() == 7){
            this.phoneNo = phoneNo;
        }else {
            this.phoneNo = "empty_number";
        }
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public abstract void setCustomerCode();

    public long getCounter() {
        return counter;
    }


    public ArrayList<Parcel> getParcels() {
        return parcels;
    }

    public void setParcels(ArrayList<Parcel> parcels) {

        this.parcels = parcels;
    }



    public AbstractCustomer(){

        setAddress(new Address(City.Liepaja,"Empty",0));
        setPhoneNo("222222222");
    }

    public AbstractCustomer(Address address, String phoneNo){
        setcID();
        setAddress(address);
        setPhoneNo(phoneNo);
    }

    @Override
    public String toString() {
        return "{" +
                "cID=" + cID +
                ", address=" + address +
                ", phoneNo='" + phoneNo + '\'' +
                 '\'' +
                ", parcels=" + parcels +
                '}';
    }
}
