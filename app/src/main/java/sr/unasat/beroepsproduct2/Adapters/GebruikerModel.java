package sr.unasat.beroepsproduct2.Adapters;

public class GebruikerModel {

    int id;
    String username;
    String password;


    public  GebruikerModel(){

    }

    public GebruikerModel(int id, String username, String password) {
        this.username = username;
        this.password = password;
        this.id = id;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
