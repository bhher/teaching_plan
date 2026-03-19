package kculture;

public class MemberDTO {
    private int id;
    private String email;
    private String password;
    private String name;
    private String nationality;
    private String language;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getNationality() { return nationality; }
    public void setNationality(String nationality) { this.nationality = nationality; }
    public String getLanguage() { return language; }
    public void setLanguage(String language) { this.language = language; }
}
