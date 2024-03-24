package Service;

import Model.Driver;
import Model.User.AbstractCustomer;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainServcie {


    public static ArrayList<Driver> driverList = new ArrayList<>();

    public static ArrayList<AbstractCustomer> customersList = new ArrayList<>();
    public static void Main(String[] args) {



    }


    // Driver crud

    // create

    public static void createDriver(String name, String surname, String personCode,String licenseNo,float experienceInYears)throws Exception{
        if (name == null || surname == null || personCode == null || licenseNo == null || experienceInYears < 0) {
            throw new Exception("Problems with input arguments");
        }

        for(Driver tempDr : driverList){
            if(tempDr.getName().equals(name) || tempDr.getPersonCode().equals(personCode)){
                throw new Exception(name + " " + surname + "already exists");
            }
        }
        Driver driver = new Driver(name,surname,personCode,licenseNo,experienceInYears);
        driverList.add(driver);
    }

    // retrieve driver by person code

    public static Driver retrieveDriverByPersonCode(String personCode)throws Exception{
        if(personCode == null) {
            throw new Exception("Input is empty");
        }

        for (Driver tempDr : driverList){
            if(tempDr.getPersonCode().equals(personCode)){
                return tempDr;
            }
        }
        throw new Exception("Driver does not exist");
    }

    //  update driver licence by personcode;

    public static void updateLicenseByPersonCode(String personCode, String licenseNo)throws Exception{
        if(personCode == null || licenseNo == null) {
            throw new Exception("Input is empty");
        }
        for (Driver tempDr : driverList){
            if(tempDr.getPersonCode().equals(personCode)){
                if(!tempDr.getLicenseNo().equals(licenseNo)) {
                    tempDr.setLicenseNo(licenseNo);
                    return;
                }
            }
        }
        throw new Exception("Driver does not exist");
    }

    // update driver years

    public static void updateDriverYearsOfExpierience(String personCode,float newExpierience)throws Exception{
        if(personCode == null || newExpierience < 0) {
            throw new Exception("Input is empty or wrong");
        }

        for (Driver tempDr : driverList){
            if(tempDr.getPersonCode().equals(personCode)){
                if(tempDr.getExperienceInYears() != (newExpierience)) {
                    tempDr.setExperienceInYears(newExpierience);
                    return;
                }
            }
        }
        throw new Exception("Driver does not exist");
    }

    public static void deleteDriverByPersonCode(String personCode)throws Exception{
        if(personCode == null) {
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



}
