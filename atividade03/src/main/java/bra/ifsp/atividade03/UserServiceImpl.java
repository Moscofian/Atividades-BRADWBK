package bra.ifsp.atividade03;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private List<User> users = new ArrayList<>();

    @Override
    public void add(User newUser) {
        users.add(newUser);
    }

    @Override
    public User find(String login) {
        return users.stream()
                .filter(u -> u.getLogin().equals(login))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean remove(String login) {
        User user = find(login);
        if (user != null) {
            return users.remove(user);
        }
        return false;
    }
}