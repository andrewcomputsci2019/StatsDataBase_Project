import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        Randomizer randomizer = new Randomizer();
        DataClass[] randomData = randomizer.genUser();
        long[] postgresPutTime = new long[200];
        long[] dynamoDBPutTime = new long[200];
        long[] postgresGetTime = new long[200];
        long[] dynamoDBGetTime = new long[200];
        DataBaseStuff dataBaseStuff;
        try {
             dataBaseStuff = new DataBaseStuff();
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(12);
            return;
        }
        for(int i =0; i< randomData.length; i++){
            long origin = System.nanoTime();
            dataBaseStuff.postgresPut(randomData[i]);
            postgresPutTime[i] = System.nanoTime()-origin;
            origin = System.nanoTime();
            dataBaseStuff.dynamoDBPut(randomData[i].getValues());
            dynamoDBPutTime[i] = System.nanoTime()-origin;
        }
        for (long i =0; i<200; i++){
            long origin = System.nanoTime();
            dataBaseStuff.postgreGET(i);
            postgresGetTime[(int) i] = System.nanoTime()-origin;
            origin = System.nanoTime();
            dataBaseStuff.dynamoDbGet(i);
            dynamoDBGetTime[(int)i] = System.nanoTime()-origin;
        }
        CsvFileWriter fileWriter = new CsvFileWriter();
        fileWriter.CsvWrite(postgresPutTime,dynamoDBPutTime,postgresGetTime,dynamoDBGetTime);
    }
}
