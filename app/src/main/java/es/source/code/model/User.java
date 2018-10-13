package es.source.code.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/10/2.
 */

public class User implements Serializable{
    private String userName,password;
    private boolean oldUser;

    public boolean isOldUser() {
        return oldUser;
    }

    public void setOldUser(boolean oldUser) {
        this.oldUser = oldUser;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
