//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import Model.*;
import Model.User.CustomerAsCompany;
import Model.User.CustomerAsPerson;

public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.

        Driver driver = new Driver("Daniels","Kalnavs","261111-23237","AP123457",6);
        Parcel parcel = new Parcel(ParcelSize.XL,true,driver);
        CustomerAsCompany customerAsCompany = new CustomerAsCompany();
        Address address = new Address(City.Liepaja,"Maja",7);
        CustomerAsPerson customer = new CustomerAsPerson("Daniels", "perk","261111-23237",address,"24423747");


        System.out.println(parcel.toString());
        System.out.println(customerAsCompany.toString());
        System.out.println(customer.toString());

    }
}