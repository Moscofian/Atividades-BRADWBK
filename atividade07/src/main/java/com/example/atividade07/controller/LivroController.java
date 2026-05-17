package com.example.atividade07.controller;

import com.example.atividade07.model.Livro;
import com.example.atividade07.repository.LivroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/livros")
@RequiredArgsConstructor
public class LivroController {

    private final LivroRepository livroRepository;

    @GetMapping
    public Iterable<Livro> listarTodos() {
        return livroRepository.findAll();
    }

    @PostMapping
    public Livro salvar(@RequestBody Livro livro) {
        return livroRepository.save(livro);
    }
}