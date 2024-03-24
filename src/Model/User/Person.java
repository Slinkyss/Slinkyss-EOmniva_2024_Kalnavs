package Model.User;

public class Person {

    private String name;
    private String surname;
    private String personCode;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name != null && name.matches("[A-ZĒŪĪĻĶĢŠĀČŅ]{1}[a-zēūīļķģšāžčņ]+")) {
            this.name = name;
        }
        else{
            this.name = "___Empty___";
        }
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        if(surname != null && surname.matches("[A-ZĒŪĪĻĶĢŠĀČŅ]{1}[a-zēūīļķģšāžčņ]+")) {
            this.surname = surname;
        }
        else{
            this.surname = "___Empty___";
        }
    }

    public void setPersonCode(String personCode) {
        if(personCode != null && personCode.matches("([0-9]+[-]+[0-9]+)") && personCode.length() == 12) {
            this.personCode = personCode;
        }
        else{
            this.personCode = "___Empty___";
        }
    }



    public Person(String name, String surname, String personCode){
        setName(name);
        setSurname(surname);
        setPersonCode(personCode);

    }

    public Person() {
        this.name = "empty";
        this.surname =  "empty";
    }

    public String toString(){

        return name + " " + surname + "id " + personCode;
    }

    public String getPersonCode() {
        return personCode;
    }


}
