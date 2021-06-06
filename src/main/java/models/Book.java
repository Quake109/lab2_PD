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
@DynamoDBTable(tableName = "Book")
public class Book{

    private int id;
    private String title;
    private String genre;
    private int rating;

    @DynamoDBHashKey
    public int getId(){ return id; }

    public void setId(int id){ this.id = id; }

    @DynamoDBAttribute(attributeName="title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @DynamoDBAttribute(attributeName="genre")
    public void setGenre(String genre) {this.genre = genre;}

    public String getGenre() {return genre;}

    @DynamoDBAttribute(attributeName="rating")
    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString(){
        return "[Id " + id + "] Book: " + title + " Autor: " + genre + " Book rating: " + rating;
    }

}