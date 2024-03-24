package Model.User;

import Model.Address;

public class CustomerAsCompany extends AbstractCustomer{

    public String title;

    public String companyRegNo;


    @Override
    public void setCustomerCode() {

        this.customerCode = String.valueOf(super.getcID()) + "_" + getTitle() + "_" + getCompanyRegNo();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if(title != null && title.matches("[A-ZĒŪĪĻĶĢŠĀČŅ]{1}[a-zēūīļķģšāžčņ]+")) {
            this.title = title;
        }
        else{
            this.title = "___Empty___";
        }
    }

    public String getCompanyRegNo() {
        return companyRegNo;
    }

    public void setCompanyRegNo(String companyRegNo) {
        if(companyRegNo != null ) {
            this.companyRegNo = companyRegNo;
        }
        else{
            this.companyRegNo = "___Empty___";
        }
    }

    public CustomerAsCompany(){
        super();
        setTitle("Macdonald");
        setCompanyRegNo("ASA12345");
        setCustomerCode();
    }

    public CustomerAsCompany(Address address, String phone, String title, String companyRegNo){
        super(address,phone);
        setTitle(title);
        setCompanyRegNo(companyRegNo);
        setCustomerCode();
    }

    @Override
    public String toString() {
        return "CustomerAsCompany {" + super.toString() +
                "title='" + title + '\'' +
                ", companyRegNo='" + companyRegNo + '\'' +
                ", customerCode='" + customerCode + '\'' +
                '}';
    }
}

