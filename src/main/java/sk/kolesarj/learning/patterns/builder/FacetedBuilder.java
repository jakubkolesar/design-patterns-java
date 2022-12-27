package sk.kolesarj.learning.patterns.builder;

class PersonF{
    /*
    * Objective is building different aspects with different builders*/
    // address
    public String streetAddress,postcode,city;
    // employment
    public String companyName, position;
    public int annualIncome;

    @Override
    public String toString() {
        return "Person{" +
                "streetAddress='" + streetAddress + '\'' +
                ", postcode='" + postcode + '\'' +
                ", city='" + city + '\'' +
                ", companyName='" + companyName + '\'' +
                ", position='" + position + '\'' +
                ", annualIncome=" + annualIncome +
                '}';
    }
}

// builder facade
class PersonBuilderF {
    protected PersonF person = new PersonF();
    public PersonF build(){
        return person;
    }
    public PersonAddressBuilder lives(){
        return new PersonAddressBuilder(this.person);
    }

    public PersonJobBuilder works(){
        return new PersonJobBuilder(this.person);
    }
}

class PersonAddressBuilder extends PersonBuilderF {
    public PersonAddressBuilder(PersonF person) {
        this.person = person;
    }

    public PersonAddressBuilder at(String streetAddress){
        this.person.streetAddress = streetAddress;
        return this;
    }
    public PersonAddressBuilder withPostcode(String postcode){
        this.person.postcode = postcode;
        return this;
    }
    public PersonAddressBuilder withCity(String city){
        this.person.city = city;
        return this;
    }
}

class PersonJobBuilder extends PersonBuilderF {
    public PersonJobBuilder(PersonF person){
        this.person = person;
    }

    public PersonJobBuilder at(String companyName){
        this.person.companyName = companyName;
        return this;
    }
    public PersonJobBuilder withPosition(String position){
        this.person.position = position;
        return this;
    }
    public PersonJobBuilder withIncome(int annualIncome){
        this.person.annualIncome = annualIncome;
        return this;
    }
}

public class FacetedBuilder {
    public static void main(String[] args) {
        PersonBuilderF pb = new PersonBuilderF();
        PersonF person = pb
                .lives()
                    .at("Slovensko")
                    .withCity("Presov")
                    .withPostcode("080 05")
                .works()
                    .at("QORPO")
                    .withPosition("developer")
                    .withIncome(120123)
                .build();
        System.out.println(person);
    }
}
