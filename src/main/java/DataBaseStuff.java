import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClientBuilder;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;
import software.amazon.awssdk.services.dynamodb.model.GetItemRequest;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class DataBaseStuff {

    private final static String dynamoDbTable = "dataTable";
    private final Connection connection;
    private final DynamoDbClient client = DynamoDbClient.create();

    public DataBaseStuff() throws SQLException {

        connection = DriverManager.getConnection("jdbc:postgresql://database-1.czsv2dgubntu.us-west-2.rds.amazonaws.com:5432/datastore","postgres","damNEIDS541o");

    }


    /**
     * Table Will be named People
     * id BIGINT primary key
     * firstName varchar
     * lastName varchar
     * address varchar
     * job varchar
     * phoneNumber varchar
     */

    public void postgresPut(DataClass dataClass){
            String SQLStatement = String.format("INSERT INTO datatable VALUES (%d,'%s','%s','%s','%s','%s')",dataClass.getaLong(),dataClass.getFirstname(),dataClass.getLastName(),dataClass.getAddress(),dataClass.getJob(),dataClass.getPhoneNumber());
            try{
                Statement statement = connection.createStatement();
                statement.executeUpdate(SQLStatement);
            }catch (Exception e){
                System.err.println(e.getMessage());
            }

    }


    public void postgreGET(long id){
        String SQLStatement = String.format("SELECT * FROM datatable WHERE id=%d",id); // this can be read as SELECT all FROM table WHERE id=number
        try {
            Statement statement = connection.createStatement();
            statement.executeQuery(SQLStatement);
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }

    }

    public void dynamoDBPut(HashMap<String, AttributeValue> dataClass){

        PutItemRequest request = PutItemRequest.builder().tableName(dynamoDbTable).item(dataClass).conditionExpression("attribute_not_exists(Id)").build();
        try{
            client.putItem(request);
        }catch (DynamoDbException e){
            System.err.println(e.getMessage());
        }
    }

    public void dynamoDbGet(long id){
        GetItemRequest request = GetItemRequest.builder().tableName(dynamoDbTable).key(new HashMap<>(Map.of("Id",AttributeValue.builder().n(Long.toString(id)).build()))).build();
        try{
            client.getItem(request);
        }catch (DynamoDbException e){
            System.err.println(e.getMessage());
        }

    }



}
