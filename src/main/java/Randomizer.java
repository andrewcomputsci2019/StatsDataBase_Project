import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


/*
    private String firstname;
    private String LastName;
    private String id;
    private String address;
    private String job;
 */
public class Randomizer {
    private HashSet<String> FirstNames;
    private HashSet<String> Lastnames;
    private final HashSet<String> address = new HashSet<>(List.of("AshWood Drive","Ceder Place","Smith Parkway","NightHawk Lane"));
    private final HashSet<String> Jobs = new HashSet<>(List.of("Teacher","Doctor","Nurse","Cashier","Janitor","Bartender","Waiter","Labor","Programmer"));
    public Randomizer(){
        FirstNames = new HashSet<>();
        Lastnames= new HashSet<>();
        File lastNameFile = new File("Resource/LastNames.txt");
        File firstNameFile = new File("Resource/Names.txt");
        try(BufferedReader lastNameReader = new BufferedReader(new FileReader(lastNameFile)); BufferedReader firstnameReader = new BufferedReader(new FileReader(firstNameFile))) {
            String first;
            String last;
            while ((first=firstnameReader.readLine())!=null && (last=lastNameReader.readLine())!=null){
                FirstNames.add(first);
                Lastnames.add(last);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String genPhoneNumber(){
        Random random = new Random();
        return String.format("%s-%s-%s",iter(random.ints(3,1,9).boxed().collect(Collectors.toList())),
                        iter(random.ints(3,1,9).boxed().collect(Collectors.toList())),
                            iter(random.ints(4,1,9).boxed().collect(Collectors.toList())));
    }

    public String genAddress(){

        return iter(new Random().ints(5,1,9).boxed().collect(Collectors.toList()))+" "+ (address.toArray()[(int)(Math.random()*address.size())]);
    }

    public String genJob(){

        return Jobs.toArray()[(int)(Math.random()* Jobs.size())].toString();
    }

    public DataClass[] genUser(){
        List<Integer> range = IntStream.range(0,500).boxed().collect(Collectors.toList());
        String[] fnames = FirstNames.toArray(new String[0]);
        String[] lnames = Lastnames.toArray(new String[0]);
        Collections.shuffle(range);
        List<Integer> subRange= range.subList(0,200);
        DataClass[] users = new DataClass[200];
        for(int i=0; i<subRange.size(); i++){
            users[i] = new DataClass((long)i, fnames[subRange.get(i)],lnames[subRange.get(i)],genAddress(),genJob(),genPhoneNumber());
        }
        return users;
    }

    public static String iter(List<Integer> integers){
        StringBuilder temp= new StringBuilder();
        for (Integer intes: integers){
            temp.append(intes);
        }
        return temp.toString();
    }



}
