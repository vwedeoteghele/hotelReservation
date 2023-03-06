package model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Customer {

    String firstName;
    String lastName;
    String email;
    public Customer(String firstName, String lastName, String email) {
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        if(!matcher.matches()) {
         throw new IllegalArgumentException("This email is not correct");
        }
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;

    }

    public String getEmail() {
        return email;
    }

    public String toString() {
        return "firstName: " + firstName + " lastname: " + lastName + " email: " + email;
    }

}
