import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;

public class CsvFileWriter {

    /**
     * Constructor for the CSV file writer
     */
    private final String header;
    private final static String path = "results";
    private static String Filename = "results.csv";
    public CsvFileWriter(){
      header = "postgreSQL Put,dynamoDB Put,postgreSQL Get,dynamoDB Get";
    }
    public CsvFileWriter(String header){
        this.header = header;
    }

    /**
     *
     * @param postPut writes the postgreSQL time for the put operation for each line
     * @param dynamoPut writes the DynamoDB time for the put operation into the
     * @param postGet writes the postgreSQL time for getting data out of the database
     * @param dynamoGet writes the
     */
    public void CsvWrite(long[] postPut,long[] dynamoPut, long[] postGet, long[] dynamoGet){
        new File(path).mkdir();
        if (new File(path+"/"+Filename).exists()) {
            Filename = Filename.substring(0, Filename.length() - 4) + LocalDateTime.now() + ".csv";
        }
        try {
            new File(path+"/"+Filename).createNewFile();
        }catch (IOException e){
            e.printStackTrace();
        }
        if ((postPut.length==dynamoPut.length)&&(postPut.length==postGet.length)&&(postPut.length==dynamoGet.length)){
            try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path+"/"+Filename))){
                bufferedWriter.write(header);
                bufferedWriter.newLine();
                for(int i=0; i<postGet.length; i++){
                    bufferedWriter.write(String.format("%d,%d,%d,%d",postPut[i],dynamoPut[i],postGet[i],dynamoGet[i]));
                    bufferedWriter.newLine();
                }
            }catch (IOException e){
                System.err.println(e.getMessage());
                System.exit(40); // code to be used if an error is encountered
            }
        }else{
                int[] maxs = {postPut.length, dynamoPut.length, postGet.length, dynamoGet.length};
                int max = Arrays.stream(maxs).max().getAsInt();
                try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(Filename))) {
                    bufferedWriter.write(header);
                    bufferedWriter.newLine();
                    for(int i=0; i<max; i++){
                        if (i< postPut.length){
                            bufferedWriter.write(String.format("%d,",postPut[i]));
                        }else{
                            bufferedWriter.write(",");
                        }
                        if (i< dynamoPut.length){
                            bufferedWriter.write(String.format("%d,",dynamoPut[i]));
                        }else{
                            bufferedWriter.write(",");
                        }
                        if (i< postGet.length){
                            bufferedWriter.write(String.format("%d,",postGet[i]));
                        }else{
                            bufferedWriter.write(",");
                        }
                        if (i< dynamoGet.length){
                            bufferedWriter.write(String.format("%d,",dynamoGet[i]));
                        }else{
                            bufferedWriter.write(",");
                        }
                        bufferedWriter.newLine();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
                    }
    }

}
