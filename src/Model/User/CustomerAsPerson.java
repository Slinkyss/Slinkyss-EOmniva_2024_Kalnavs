package Model.User;

import Model.Address;

public class CustomerAsPerson extends AbstractCustomerAsPerson{

    @Override
    public void setCustomerCode() {

        this.customerCode = String.valueOf(super.getcID()) + "_" + "person" + "_" + person.getPersonCode();

    }

    public CustomerAsPerson(){
        super();
        setCustomerCode();
    }

    public CustomerAsPerson(String name, String surname, String personCode, Address address, String phone){
        super(name,surname,personCode,address,phone);
        setCustomerCode();
    }

    @Override
    public String toString() {
        return "CustomerAsPerson{" +
                "person=" + super.toString() + customerCode + '}';
    }
}
