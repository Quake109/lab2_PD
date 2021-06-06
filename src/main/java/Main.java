import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import models.Client;
import models.Library;
import models.Book;

public class Main {



    public static void save(DynamoDB dynamoDB, DynamoDBMapper mapper) {

        CreateTableRequest createTableRequest1 = mapper.generateCreateTableRequest(Book.class);
        createTableRequest1.setProvisionedThroughput(new ProvisionedThroughput(25L, 25L));
        dynamoDB.createTable(createTableRequest1);

        Book newBook1 = new Book().builder()
                .id(1)
                .title("Game of Thrones")
                .genre("fantasy")
                .rating(8)
                .build();

        mapper.save(newBook1);

        Book newBook2 = new Book().builder()
            .id(2)
            .title("House of cards")
            .genre("drama")
            .rating(9)
            .build();

        mapper.save(newBook2);

        Book newBook3 = new Book().builder()
            .id(3)
            .title("The Witcher: Season of Storms")
            .genre("fantasy")
            .rating(10)
            .build();

        mapper.save(newBook3);

        Book newBook4 = new Book().builder()
                .id(4)
                .title("The Witcher: Blood of Elves")
                .genre("fantasy")
                .rating(6)
                .build();

        mapper.save(newBook4);

        Book newBook5 = new Book().builder()
                .id(5)
                .title("The Witcher: Baptism of Fire")
                .genre("fantasy")
                .rating(5)
                .build();

        mapper.save(newBook5);


        CreateTableRequest createTableRequest2 = mapper.generateCreateTableRequest(Library.class);
        createTableRequest2.setProvisionedThroughput(new ProvisionedThroughput(25L, 25L));
        dynamoDB.createTable(createTableRequest2);

        Library newLibrary1 = new Library().builder()
                .id(1)
                .address("Swietokrzyska 5")
                .capacity(500)
                .build();

        mapper.save(newLibrary1);

        Library newLibrary2 = new Library().builder()
                .id(2)
                .address("Kielecka 25")
                .capacity(100)
                .build();

        mapper.save(newLibrary2);

        Library newLibrary3 = new Library().builder()
                .id(3)
                .address("Jana Pawla II")
                .capacity(50)
                .build();

        mapper.save(newLibrary3);

        CreateTableRequest createTableRequest3 = mapper.generateCreateTableRequest(Client.class);
        createTableRequest3.setProvisionedThroughput(new ProvisionedThroughput(25L, 25L));
        dynamoDB.createTable(createTableRequest3);

        Client newClient1 = new Client().builder()
                .id(1)
                .name("Krzysztof")
                .surname("Siczek")
                .pesel("85062948172")
                .phoneNumer("+48 666111222")
                .build();

        mapper.save(newClient1);

        Client newClient2 = new Client().builder()
                .id(2)
                .name("Przemyslaw")
                .surname("Pyk")
                .pesel("82090855445")
                .phoneNumer("+48 555444333")
                .build();

        Client newClient3 = new Client().builder()
                .id(3)
                .name("Jan")
                .surname("Kaczynski")
                .pesel("95072861235")
                .phoneNumer("+48 666666666")
                .build();

        mapper.save(newClient3);

}

    public static void update(DynamoDBMapper mapper){
        System.out.println("Update library capacity \n");
        System.out.println("Enter the unique id: \n");
        Scanner scan = new Scanner(System.in);
        int id = scan.nextInt();
        Library updatedLibrary = mapper.load(Library.class, id);

        System.out.println("Set library capatity: \n");
        Scanner scan2 = new Scanner(System.in);
        int newCapacity = scan2.nextInt();
        updatedLibrary.setCapacity(newCapacity);

        mapper.save(updatedLibrary);

    }

    public static void deleteAll(DynamoDB dynamoDB){
        dynamoDB.getTable("Book").delete();
        dynamoDB.getTable("Client").delete();
        dynamoDB.getTable("Library").delete();
        System.out.println("All tables have been removed.");
    }

    public static void download(DynamoDBMapper mapper){

        DynamoDBScanExpression getAllLists = new DynamoDBScanExpression();

        List<Book> books = mapper.scan(Book.class, getAllLists);
        books.forEach(System.out::println);
        System.out.println("");

        List<Client> clients = mapper.scan(Client.class, getAllLists);
        clients.forEach(System.out::println);
        System.out.println("");

        List<Library> libraries = mapper.scan(Library.class, getAllLists);
        libraries.forEach(System.out::println);

    }

    public static void downloadByRating(DynamoDBMapper mapper){
        System.out.println("Enter the minimal rating you wish to display: \n");
        Scanner scan = new Scanner(System.in);
        int rating = scan.nextInt();
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":value", new AttributeValue().withN(String.valueOf(rating)));
        DynamoDBScanExpression getAllBooksRating  = new DynamoDBScanExpression()
                .withFilterExpression("rating >= :value").withExpressionAttributeValues(eav);

        System.out.println("List of books:  \n");
        List<Book> booksByRating = mapper.scan(Book.class, getAllBooksRating);
        booksByRating.forEach(System.out::println);

    }

    public static void main(String[] args) {

        Connection con = new Connection();
        DynamoDB dynamoDB = con.dynamoDB;
        DynamoDBMapper mapper = con.mapper;

        Scanner scan = new Scanner(System.in);
        String option = "";
        while (!option.equals("0")) {
            System.out.println("Pick the option: ");
            System.out.println("1.Save \n2.Update \n3.Delete all \n4.Download \n5.Download books by rating \n0.Exit");
            option = scan.nextLine();
            switch (option) {
                case "0":
                    System.out.println("Exit");
                    break;
                case "1":
                    System.out.println("Save");
                    save(dynamoDB, mapper);
                    break;
                case "2":
                    System.out.println("Update");
                    update(mapper);
                    break;
                case "3":
                    System.out.println("Delete all");
                    deleteAll(dynamoDB);
                    break;
                case "4":
                    System.out.println("Download");
                    download(mapper);
                    break;
                case "5":
                    System.out.println("Download books by rating");
                    downloadByRating(mapper);
                    break;
            }
        }

    }


}
