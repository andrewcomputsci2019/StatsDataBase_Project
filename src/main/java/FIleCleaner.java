import java.io.*;

public class FIleCleaner {


    public static void main(String[] args) throws IOException {
        File file =new File("Resource/LastNames.txt");
        StringBuilder names = new StringBuilder();
        try(BufferedReader reader = new BufferedReader(new FileReader(file))){
            String lines;
            while ((lines= reader.readLine())!=null){
                lines = lines.replaceAll(",","").trim();
                names.append(lines).append("\n");
            }
        }
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))){
            String[] array = names.toString().split("\n");
            for (String st: array){
                bufferedWriter.write(st+"\n");
            }
        }
    }
}
