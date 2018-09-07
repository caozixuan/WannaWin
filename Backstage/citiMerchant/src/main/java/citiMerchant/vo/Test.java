package citiMerchant.vo;

import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;

public class Test {
    private String username;
    private String password;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date birthday;

    public String getUsername() {
        return username;
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
}
