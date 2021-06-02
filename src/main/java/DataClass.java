import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.math.BigInteger;
import java.util.HashMap;

public class DataClass {
    private final Long aLong;
    private final String firstname;
    private final String LastName;
    private final String address;
    private final String job;
    private final String phoneNumber;
    private final HashMap<String,AttributeValue> values;

    public DataClass(Long integer, String firstname, String lastName, String address, String job, String phoneNumber) {
        this.aLong = integer;
        this.firstname = firstname;
        LastName = lastName;
        this.address = address;
        this.job = job;
        this.phoneNumber = phoneNumber;
        values = toHashMap();

    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastName() {
        return LastName;
    }

    public String getAddress() {
        return address;
    }

    public String getJob() {
        return job;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Long getaLong() {
        return aLong;
    }

    public HashMap<String, AttributeValue> getValues() {
        return values;
    }

    private HashMap<String, AttributeValue> toHashMap(){
        HashMap<String, AttributeValue> map = new HashMap<>();
        map.put("Id", AttributeValue.builder().n(Long.toString(getaLong())).build());
        map.put("FirstName", AttributeValue.builder().s(getFirstname()).build());
        map.put("LastName", AttributeValue.builder().s(getLastName()).build());
        map.put("Address", AttributeValue.builder().s(getAddress()).build());
        map.put("Job", AttributeValue.builder().s(getJob()).build());
        map.put("PhoneNumber", AttributeValue.builder().s(getPhoneNumber()).build());
        return map;
    }
}
