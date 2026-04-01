package bra.ifsp.atividade04;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/postagens")
public class PostagemController {

    private static List<Postagem> bancoDadosFake = new ArrayList<>();
    private static Long proximoId = 1L;

    @GetMapping
    public List<Postagem> listar() {
        return bancoDadosFake;
    }

    @PostMapping
    public ResponseEntity<Postagem> criar(@RequestBody Postagem postagem) {
        postagem.setId(proximoId++);
        bancoDadosFake.add(postagem);
        return new ResponseEntity<>(postagem, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Postagem> buscar(@PathVariable Long id) {
        Optional<Postagem> post = bancoDadosFake.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();

        return post.map(ResponseEntity::ok)
                   .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        boolean removido = bancoDadosFake.removeIf(p -> p.getId().equals(id));
        return removido ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}