package salihkorkmaz.proje_d3.user.dto;


import salihkorkmaz.proje_d3.user.User;

public class UserDTO {


    long id;


    String username;


    String email;

    String image;

    String fullName;

    public UserDTO(User user) {
        setId(user.getId());
        setUsername(user.getUsername());
        setEmail(user.getEmail());
        setImage(user.getImage());
    }




    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
