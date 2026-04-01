package bra.ifsp.atividade03;

public interface UserService {
    void add(User newUser);
    User find(String login);
    boolean remove(String login);
}