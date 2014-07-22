package com.joshrincon.web.formbean;

import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by on 7/21/2014.
 */
public class UserBean {

    private String username;
    private String password;
    private String password2;
    private String email;
    private String birthday;

    private static final String unReg="^[a-z0-9_-]{3,15}$";
    private static final String passReg="^\\d{6,20}$";
    private static final String emailReg="^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final String dateReg="^\\d{4}(\\-|\\/|\\.)\\d{1,2}\\1\\d{1,2}$";

    private Map<String,String> errors=new HashMap();

    public String getUsername() {
        return username;
    }
    public Map getErrors() {
        return errors;
    }
    public void setErrors(Map errors) {
        this.errors = errors;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword2() {
        return password2;
    }
    public void setPassword2(String password2) {
        this.password2 = password2;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }


    public String getBirthday() {
        return birthday;
    }
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public boolean validate(){
        boolean isOK=true;

        if(username==null || username.trim().equals("")){
            isOK=false;
            errors.put("username", "Please enter a username！");
        }else if(!username.matches(unReg)){
            isOK=false;
            errors.put("username", "unRed Error");
        }

        if(password==null || password.trim().equals("")){
            isOK=false;
            errors.put("password", "No password1.");
        }else if(!password.matches(passReg)){
            isOK=false;
            errors.put("password", "passReg error");
        }

        if(password==null || password.equals("")){
            isOK=false;
            errors.put("password", "No password2");
        }else if(!password.matches(passReg)){
            isOK=false;
            errors.put("password", "passReg error2");
        }

        if(password2==null || password2.equals("")){
            isOK=false;
            errors.put("password2", "password not equals");
        }else if(!password.equals(password2)){
            isOK=false;
            errors.put("password2", "password2 error");
        }

        if(email==null || email.equals("")){
            isOK=false;
            errors.put("email", "email empty or spaces error");
        }else if(!email.matches(emailReg)){
            isOK=false;
            errors.put("email", "emailReg1 error");
        }

        if(birthday!=null && !birthday.trim().equals("")){
            try{
                DateLocaleConverter dlc=new DateLocaleConverter();
                dlc.convert(birthday,"yyyy-MM-dd");
            }catch(Exception e){
                isOK=false;
                errors.put("birthday", "birthday error");
            }
        }

        return isOK;

    }

}
