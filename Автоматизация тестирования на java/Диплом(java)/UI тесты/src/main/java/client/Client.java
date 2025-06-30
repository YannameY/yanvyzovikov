package client;

import lombok.Data;

@Data
public class Client {

    private String email;
    private String password;
    private String name;


    public Client(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    // Пустой конструктор класса
    public Client() {
    }
}