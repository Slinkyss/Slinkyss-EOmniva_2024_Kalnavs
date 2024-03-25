package Service;

import Model.*;
import Model.User.AbstractCustomer;
import Model.User.CustomerAsCompany;
import Model.User.CustomerAsPerson;
import org.w3c.dom.ls.LSOutput;

import java.lang.reflect.Array;
import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class MainServcie {


    public static ArrayList<Driver> driverList = new ArrayList<>();

    public static ArrayList<AbstractCustomer> customersList = new ArrayList<>();

    public static void main(String[] args) throws Exception {

        Driver d1 = new Driver("Danis", "Kalnins", "25501-22212", "AD123456", 6);
        Driver d2 = new Driver("Danis", "Kalnunss", "25501-23212", "AD223456", 2);
        driverList.add(d1);
        driverList.add(d2);

        Address address = new Address(City.Liepaja, "Maja", 1);
        Address address2 = new Address(City.Riga, "Maxima", 2);
        Address address3 = new Address(City.Riga, "Top", 3);
        CustomerAsPerson p1 = new CustomerAsPerson("Danis", "Susura", "20000-10000", address, "24432321");
        CustomerAsPerson p2 = new CustomerAsPerson("Daigs", "Ssura", "20022-10001", address, "22232321");
        CustomerAsPerson p3 = new CustomerAsPerson("Daina", "Kaja", "20022-22222", address, "20000001");
        CustomerAsCompany c1 = new CustomerAsCompany(address2, "24432321", "Maxima", "40003520643");
        CustomerAsCompany c2 = new CustomerAsCompany(address3, "22443211", "Top", "40003520641");

        customersList.addAll(Arrays.asList(p1, p2, p3, c1, c2));

        try {
            createDriver("Juris", "Kalnsss", "22501-23002", "AD223456", 3);
            System.out.println("Old list");
            for (Driver tempDr : driverList) {
                System.out.println(tempDr);
            }
            System.out.println("-------------------------------");
            System.out.println("Found by person code");
            System.out.println(retrieveDriverByPersonCode("22501-23002"));
            System.out.println("-------------------------------");
            System.out.println("Updated license and experience");
            updateDriverYearsOfExpierience("22501-23002", 5);
            updateLicenseByPersonCode("22501-23002", "AC123456");
            System.out.println("-------------------------------");
            for (Driver tempDr : driverList) {
                System.out.println(tempDr);
            }
            System.out.println("-------------------------------");
            System.out.println("Deleted user Juris");
            deleteDriverByPersonCode("22501-23002");
            for (Driver tempDr : driverList) {
                System.out.println(tempDr);
            }
            System.out.println("-------------------------------");


        } catch (Exception e) {
            System.out.println("Kaut kas nav ar driver");
        }

        try {
            System.out.println("Retrieve all customers as person");
            for (CustomerAsPerson tempDr : retrieveAllCustomersAsPerson()) {
                System.out.println(tempDr);
            }
            System.out.println("-------------------------------");
            System.out.println("Retrieve all customers as Company");
            for (CustomerAsCompany tempComp : retrieveAllCustomersAsCompany()) {
                System.out.println(tempComp);
            }
            System.out.println("-------------------------------");
            System.out.println("Create new Customer as person");
            CreateNewCustomerAsPerson("Zebra", "Maja", "11111-22222", address, "00000000");
            for (CustomerAsPerson tempDr : retrieveAllCustomersAsPerson()) {
                System.out.println(tempDr);
            }
            System.out.println("-------------------------------");
            System.out.println("Create new Customer as a company");
            CreateNewCustomerAsCompany(address3, "12345678", "Topp", "40003520642");
            for (CustomerAsCompany tempComp : retrieveAllCustomersAsCompany()) {
                System.out.println(tempComp);
            }
            System.out.println("-------------------------------");
            System.out.println("Create Parcel for customer");
            createNewParcelForCustomer(ParcelSize.M, true, d1, "0_person_20000-10000");
            System.out.println(p1);
            System.out.println("-------------------------------");
            System.out.println("Create Parcel for Company");
            createNewParcelForCustomer(ParcelSize.XL, false, d1, "4_Top_40003520641");
            System.out.println(c2);
            System.out.println("-------------------------------");

        } catch (Exception e) {
            System.out.println("Kaut kas nav ar customer");
        }

        try {
            System.out.println("Find all parcels for person");
            System.out.println(retrieveAllParcelByCustomerCode("0_person_20000-10000"));
            System.out.println("-------------------------------");
            System.out.println("Find all parcels for Company");
            System.out.println(retrieveAllParcelByCustomerCode("4_Top_40003520641"));
            System.out.println("-------------------------------");
            System.out.println("Find all parcels for driver");
            for (Parcel temp : retrieveAllParcelsByDriverPersonCode("25501-22212")) {
                System.out.println(temp);
            }
            System.out.println("-------------------------------");
            System.out.println("Find all parcels by City");
            for (Parcel temp : retrieveAllParcelsByCity(City.Liepaja)) {
                System.out.println(temp);
            }
            System.out.println("-------------------------------");
            System.out.println("Find all parcels by size");
            for (Parcel temp : retrieveAllParcelBySize(ParcelSize.M)) {
                System.out.println(temp);
            }
            System.out.println("-------------------------------");
            System.out.println("Calculate customers parcel price");
            System.out.println(calculatePriceOfAllCustomerParcelsByCustomerCode(c2.getCustomerCode()));
            System.out.println("Price with added new parcel");
            createNewParcelForCustomer(ParcelSize.M,true,d1, c2.getCustomerCode());
            for (Parcel temp : retrieveAllParcelByCustomerCode(c2.getCustomerCode())) {
                System.out.println(temp);
            }
            System.out.println(calculatePriceOfAllCustomerParcelsByCustomerCode(c2.getCustomerCode()));
            System.out.println("-------------------------------");
            System.out.println("Printed out parcel stats");
            System.out.println(Arrays.toString(retrieveStatisticsOfCustomerParcelSize(c2.getCustomerCode())));
            System.out.println("-------------------------------");
            sortDriversByExperience();
            for (Driver tempDr : driverList) {
                System.out.println(tempDr);
            }




        } catch (Exception e) {
            System.out.printf("Kaut kas nav ar e funkciju");
        }

    }


    // Driver crud

    // create

    public static void createDriver(String name, String surname, String personCode, String licenseNo, float experienceInYears) throws Exception {
        if (name == null || surname == null || personCode == null || licenseNo == null || experienceInYears < 0) {
            throw new Exception("Problems with input arguments");
        }

        for (Driver tempDr : driverList) {
            if (tempDr.getName().equals(name) || tempDr.getPersonCode().equals(personCode)) {
                throw new Exception(name + " " + surname + "already exists");
            }
        }
        Driver driver = new Driver(name, surname, personCode, licenseNo, experienceInYears);
        driverList.add(driver);
    }

    // retrieve driver by person code

    public static Driver retrieveDriverByPersonCode(String personCode) throws Exception {
        if (personCode == null) {
            throw new Exception("Input is empty");
        }

        for (Driver tempDr : driverList) {
            if (tempDr.getPersonCode().equals(personCode)) {
                return tempDr;
            }
        }
        throw new Exception("Driver does not exist");
    }

    //  update driver licence by personcode;

    public static void updateLicenseByPersonCode(String personCode, String licenseNo) throws Exception {
        if (personCode == null || licenseNo == null) {
            throw new Exception("Input is empty");
        }
        for (Driver tempDr : driverList) {
            if (tempDr.getPersonCode().equals(personCode)) {
                if (!tempDr.getLicenseNo().equals(licenseNo)) {
                    tempDr.setLicenseNo(licenseNo);
                    return;
                }
            }
        }
        throw new Exception("Driver does not exist");
    }

    // update driver years

    public static void updateDriverYearsOfExpierience(String personCode, float newExpierience) throws Exception {
        if (personCode == null || newExpierience < 0) {
            throw new Exception("Input is empty or wrong");
        }

        for (Driver tempDr : driverList) {
            if (tempDr.getPersonCode().equals(personCode)) {
                if (tempDr.getExperienceInYears() != (newExpierience)) {
                    tempDr.setExperienceInYears(newExpierience);
                    return;
                }
            }
        }
        throw new Exception("Driver does not exist");
    }

    public static void deleteDriverByPersonCode(String personCode) throws Exception {
        if (personCode == null) {
            throw new Exception("Input is empty");
        }
        for (Driver tempDr : driverList) {
            if (tempDr.getPersonCode().equals(personCode)) {
                driverList.remove(tempDr);
                return;
            }
        }
        throw new Exception("Driver does not exist");
    }

    // Customer functions
    public static ArrayList<CustomerAsPerson> retrieveAllCustomersAsPerson() throws Exception {
        ArrayList<CustomerAsPerson> customerPersonList = new ArrayList<>();
        for (AbstractCustomer tempPer : customersList) {
            if (tempPer instanceof CustomerAsPerson) {
                customerPersonList.add((CustomerAsPerson) tempPer);
            }

        }
        return customerPersonList;

    }

    public static ArrayList<CustomerAsCompany> retrieveAllCustomersAsCompany() throws Exception {
        ArrayList<CustomerAsCompany> customerAsCompany = new ArrayList<>();
        for (AbstractCustomer tempComp : customersList) {
            if (tempComp instanceof CustomerAsCompany) {
                customerAsCompany.add((CustomerAsCompany) tempComp);
            }
        }
        return customerAsCompany;
    }


    public static void CreateNewCustomerAsPerson(String name, String surname, String personCode, Address address, String phone) throws Exception {

        if (name == null || surname == null || personCode == null) {
            throw new Exception("Problems with input arguments");
        }
        CustomerAsPerson temp = new CustomerAsPerson(name, surname, personCode, address, phone);


        for (AbstractCustomer tempCus : customersList) {
            if (tempCus.getCustomerCode().equals(temp.getCustomerCode())) {
                throw new Exception("Customer already exists");
            }
        }

        customersList.add(temp);

    }

    public static void CreateNewCustomerAsCompany(Address address, String phone, String title, String companyRegNo) throws Exception {
        if (phone == null || title == null || companyRegNo == null) {
            throw new Exception("Problems with input arguments");
        }
        CustomerAsCompany temp = new CustomerAsCompany(address, phone, title, companyRegNo);

        for (AbstractCustomer tempCus : customersList) {
            if (tempCus.getCustomerCode().equals(temp.getCustomerCode())) {
                throw new Exception("Company already exists");
            }
        }

        customersList.add(temp);

    }

    public static void createNewParcelForCustomer(ParcelSize size, boolean isFragile, Driver driver, String customerCode) throws Exception {
        if (size == null || customerCode == null) {
            throw new Exception("Problems with input arguments");
        }

        Parcel parcel = new Parcel(size, isFragile, driver);

        for (AbstractCustomer customer : customersList) {
            if (customer.getCustomerCode().equals(customerCode)) {

                customer.addNewParcel(new Parcel(size,isFragile,driver));
                return;
                }

            }
        }


    public static ArrayList<Parcel> retrieveAllParcelByCustomerCode(String customerCode) throws Exception {
        if (customerCode == null) {
            throw new Exception("Problems with input arguments");
        }
        ArrayList<Parcel> parcels = new ArrayList<>();

        for (AbstractCustomer customer : customersList) {
            if (customer.getCustomerCode().equals(customerCode)) {
                parcels.addAll(customer.getParcels());
            }


        }

        if (parcels.isEmpty()) {
            throw new Exception("Customer has not ordered any parcels");
        }
        return parcels;
    }

    public static ArrayList<Parcel> retrieveAllParcelsByDriverPersonCode(String personCode) throws Exception {
        if (personCode == null) {
            throw new Exception("Problems with input arguments");
        }

        ArrayList<Parcel> parcels = new ArrayList<>();
        ArrayList<Parcel> DriverParcels = new ArrayList<>();

        for (AbstractCustomer customer : customersList) {
            parcels.addAll(customer.getParcels());

        }

        for (Parcel temp : parcels) {
            if (temp.getDriver().getPersonCode().equals(personCode)) {
                DriverParcels.add(temp);
            }
        }

        if (DriverParcels.isEmpty()) {
            throw new Exception("Driver has no parcels to deliver");
        }

        return DriverParcels;
    }

    public static ArrayList<Parcel> retrieveAllParcelsByCity(City city)throws Exception{
        if (city == null) {
            throw new Exception("Problems with input arguments");
        }

        ArrayList<Parcel> parcels = new ArrayList<>();


        for (AbstractCustomer customer : customersList) {
            if(customer.getAddress().getCity().equals(city)){
                parcels.addAll(customer.getParcels());
            }

        }
        if (parcels.isEmpty()) {
            throw new Exception("Driver has no parcels to deliver");
        }
        return parcels;
    }

    public static ArrayList<Parcel> retrieveAllParcelBySize(ParcelSize parcelSize) throws Exception{
        if(parcelSize == null){
            throw new Exception("Problems with input arguments");
        }
        ArrayList<Parcel> parcels = new ArrayList<>();
        ArrayList<Parcel> SizeParcels = new ArrayList<>();

        for (AbstractCustomer customer : customersList) {
            parcels.addAll(customer.getParcels());

        }

        for(Parcel parcel : parcels){
            if(parcel.getSize().equals(parcelSize)){
                SizeParcels.add(parcel);
            }
        }

        if (parcels.isEmpty()) {
            throw new Exception("Driver has no parcels to deliver");
        }
        return SizeParcels;
    }

    public static float calculatePriceOfAllCustomerParcelsByCustomerCode(String customerCode)throws Exception{
        if(customerCode == null){
            throw new Exception("Problems with input arguments");
        }

        float result = 0;
        ArrayList<Parcel> customerParcels = new ArrayList<>();

        for (AbstractCustomer customer : customersList) {
            if(customer.getCustomerCode().equals(customerCode)){
                customerParcels.addAll(customer.getParcels());
            }
        }


        for(Parcel parcel : customerParcels){

            result += parcel.getPrice();
        }
        if (customerParcels.isEmpty()) {
            throw new Exception("Driver has no parcels to deliver");
        }



        return result;


    }

    public static int[] retrieveStatisticsOfCustomerParcelSize(String customerCode)throws Exception{
        if(customerCode == null){
            throw new Exception("Problems with input arguments");
        }
        ArrayList<Parcel> customerParcels = new ArrayList<>();
        int[] parcels = new int[5];

        for (AbstractCustomer customer : customersList) {
            if(customer.getCustomerCode().equals(customerCode)){
                customerParcels.addAll(customer.getParcels());
            }
        }

        for(Parcel parcel :customerParcels){
            switch (parcel.getSize()){
                case X:
                    parcels[0]++;
                    break;
                case S:
                    parcels[1]++;
                    break;
                case M:
                    parcels[2]++;
                    break;
                case L:
                    parcels[3]++;
                    break;
                case XL:
                    parcels[4]++;
                    break;
            }
        }

        return parcels;

    }

    public static void sortDriversByExperience() throws Exception{
        if(driverList.isEmpty()){
            throw new Exception("Driver list is empty");
        }

        for(int i = 0; i < driverList.size(); i++){
            for(int j = 0; j < driverList.size(); j++){
                if(driverList.get(i).getExperienceInYears() < driverList.get(j).getExperienceInYears()){
                    Driver tempDriver = driverList.get(i);
                    driverList.set(i,driverList.get(j));
                    driverList.set(j,tempDriver);
                }
            }
        }

    }



  
}
