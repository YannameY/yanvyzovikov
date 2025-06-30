package client;

import lombok.Data;

@Data // Аннотация Lombok для автоматической генерации геттеров, сеттеров и т.д.
public class ClientLogin {

    private String email;
    private String password;

    // Конструктор класса client.ClientLogin для инициализации email и пароля
    public ClientLogin(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // Приватный конструктор без параметров
    private ClientLogin() {
    }
}