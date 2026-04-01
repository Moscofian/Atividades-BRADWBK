package bra.ifsp.atividade03;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> endPoint3(@RequestBody User novoObjeto) {
        userService.add(novoObjeto);
        return ResponseEntity.created(URI.create("user/" + novoObjeto.getLogin())).body(novoObjeto);
    }

    @GetMapping("/{login}")
    public ResponseEntity<User> endPoint2(@PathVariable("login") String login) {
        User user = userService.find(login);
        if (user != null) {
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{login}")
    public ResponseEntity<String> endPoint5(@PathVariable("login") String login) {
        boolean removido = userService.remove(login);
        if (removido) {
            return ResponseEntity.ok("Remoção de informação com LOGIN " + login + " realizada");
        } else {
            return ResponseEntity.status(404).body("Objeto para login " + login + " não encontrado");
        }
    }
}