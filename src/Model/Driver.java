package Model;

import Model.User.Person;

public class Driver extends Person {

    private double dID;
    private String licenseNo;

    private float ExperienceInYears;

    private static long counter = 0;


    public double getdID() {
        return dID;
    }

    public void setdID() {
        this.dID = counter;
        counter++;
    }

    public String getLicenseNo() {
        return licenseNo;
    }

    public void setLicenseNo(String licenseNo) {
        if(licenseNo != null && licenseNo.matches("(^[A-Z]{2}\\d+)") && licenseNo.length() == 8) {
            this.licenseNo = licenseNo;
        }
        else{
            this.licenseNo = "___Empty___";
        }
    }

    public float getExperienceInYears() {
        return ExperienceInYears;
    }

    public void setExperienceInYears(float experienceInYears) {
        if(experienceInYears > 0 || experienceInYears < 70) {
            this.ExperienceInYears = experienceInYears;
        }else{
            this.ExperienceInYears = 1;
        }
    }

    public Driver(){
        super();
    }

    public Driver(String name, String surname, String personCode,String licenseNo,float experienceInYears) {
        super(name, surname,personCode);
        setdID();
        setLicenseNo(licenseNo);
        setExperienceInYears(experienceInYears);
    }

    @Override
    public String toString() {
        return "Driver{"  + super.toString() +
                "dID=" + dID +
                ", licenseNo='" + licenseNo + '\'' +
                ", ExperienceInYears=" + ExperienceInYears +
                '}';
    }
}
