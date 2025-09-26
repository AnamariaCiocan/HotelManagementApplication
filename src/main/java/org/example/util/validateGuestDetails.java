package org.example.util;

public class validateGuestDetails {

    public Boolean isValid(String name, String email, String phone){
        if(name.matches("[a-zA-Z]+") && checkEmail(email) && checkPhone(phone)){
            return true;

        }
        return false;
    }

    public Boolean checkEmail(String email){
        if(email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")){
            return true;
        }
        return false;
    }
    public Boolean checkPhone(String phone){
        if(phone.matches("^[0-9]{10}$")){
            return true;
        }
        return false;
    }
}
