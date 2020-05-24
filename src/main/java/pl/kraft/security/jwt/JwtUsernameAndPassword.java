package pl.kraft.security.jwt;

public class JwtUsernameAndPassword {

    private String username;
    private String password;

    public JwtUsernameAndPassword() {
    }

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
