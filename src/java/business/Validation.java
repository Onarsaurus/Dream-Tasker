/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author turtl
 */
public class Validation {

    public static String isEmailValid(String email) {
        String errorMessage = "";

        if (email.isEmpty()) {
            errorMessage += "Please enter an email. ";
        } else if (email.length() < 5 || !email.contains("@") || !email.substring(email.indexOf("@")).contains(".")) {
            errorMessage += "Please make sure this is a valid email address. ";
        }

        return errorMessage;
    }

    public static String isUsernameValid(String username) {
        String errorMessage = "";

        if (username.isEmpty()) {
            errorMessage += "Please enter a username. ";
        } else if (username.length() < 2) {
            errorMessage += "Please give a username greater than 2 characters. ";
        } else if (username.length() > 35) {
            errorMessage += "Please give a username less than 35 characters. ";
        }

        return errorMessage;
    }

    public static String isPasswordValid(String password) {
        String errorMessage = "";

        if (password.isEmpty()) {
            errorMessage += "Please enter a password. ";
        } else if (password.length() < 10) {
            errorMessage += "Please enter a password that is at least 10 characters. ";
        } else {
            Pattern p = Pattern.compile("\\p{Lower}");
            Matcher m = p.matcher(password);
            boolean valid = m.find();
            if (!valid) {
                errorMessage += "Please make sure there's at least one lowercase character in your password. ";
            }

            p = Pattern.compile("\\p{Upper}");
            m = p.matcher(password);
            valid = m.find();
            if (!valid) {
                errorMessage += "Please make sure there's at least one uppercase character in your password. ";
            }

            p = Pattern.compile("\\p{Digit}");
            m = p.matcher(password);
            valid = m.find();
            if (!valid) {
                errorMessage += "Please make sure there's at least one number in your password. ";
            }

            p = Pattern.compile("\\p{Punct}");
            m = p.matcher(password);
            valid = m.find();
            if (!valid) {
                errorMessage += "Please make sure there's at least one special character in your password. ";
            }
        }

        return errorMessage;
    }

    public static String isBirthdateValid(LocalDate birthdate) {
        String errorMessage = "";

        if (birthdate.isAfter(LocalDate.now().minusYears(18))) {
            errorMessage += "You must be at least 18 to use this website. ";
        }

        return errorMessage;
    }

}
