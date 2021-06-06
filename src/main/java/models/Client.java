package models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@DynamoDBTable(tableName = "Client")
public class Client {

    private int id;
    private String name;
    private String surname;
    private String pesel;
    private String phoneNumer;

    @DynamoDBHashKey
    public int getId(){ return id; }

    public void setId(int id){ this.id = id; }

    @DynamoDBAttribute(attributeName="name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @DynamoDBAttribute(attributeName="surname")
    public void setSurname(String surname) {this.surname = surname;}

    public String getSurname() {return surname;}

    @DynamoDBAttribute(attributeName="pesel")
    public void setPesel(String pesel) {this.pesel = pesel;}

    public String getPesel() {return pesel;}

    @DynamoDBAttribute(attributeName="phoneNumer")
    public String getPhoneNumer() {
        return phoneNumer;
    }

    public void setPhoneNumer(String phoneNumer) {
        this.phoneNumer = phoneNumer;
    }

    @Override
    public String toString(){
        return "Id [" + id + "] Client: " + name + " " + surname + " Pesel: " + pesel + " Phone number: " + phoneNumer;
    }
}