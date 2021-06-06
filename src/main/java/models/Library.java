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
@DynamoDBTable(tableName = "Library")
public class Library {

    private int id;
    private String address;
    private int capacity;

    @DynamoDBHashKey
    public int getId(){ return id; }

    public void setId(int id){ this.id = id; }

    @DynamoDBAttribute(attributeName="address")
    public void setAddress(String address) {this.address = address;}

    public String getAddress() {return address;}

    @DynamoDBAttribute(attributeName="capacity")
    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString(){
        return "Library ID: [" + id + "] Address: " + address + " Library capacity: " + capacity;
    }
}