package fiap.com.br.brinquedos.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fiap.com.br.brinquedos.model.Brinquedo;
import fiap.com.br.brinquedos.repository.BrinquedoRepository;

@RestController
@RequestMapping("/brinquedos")
public class BrinquedoController {

    @Autowired
    private BrinquedoRepository repository;

    // READ (Todos)
    @GetMapping
    public List<Brinquedo> listarTodos() {
        return repository.findAll();
    }

    // READ (Por ID)
    @GetMapping("/{id}")
    public ResponseEntity<Brinquedo> buscarPorId(@PathVariable Long id) {
        Optional<Brinquedo> brinquedo = repository.findById(id);
        return brinquedo.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // CREATE
    @PostMapping
    public ResponseEntity<Brinquedo> criar(@RequestBody Brinquedo brinquedo) {
        Brinquedo novoBrinquedo = repository.save(brinquedo);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoBrinquedo);
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Brinquedo> atualizar(@PathVariable Long id, @RequestBody Brinquedo brinquedoDetalhes) {
        Optional<Brinquedo> optionalBrinquedo = repository.findById(id);
        
        if (optionalBrinquedo.isPresent()) {
            Brinquedo brinquedo = optionalBrinquedo.get();
            brinquedo.setNome(brinquedoDetalhes.getNome());
            brinquedo.setTipo(brinquedoDetalhes.getTipo());
            brinquedo.setClassificacao(brinquedoDetalhes.getClassificacao());
            brinquedo.setTamanho(brinquedoDetalhes.getTamanho());
            brinquedo.setPreco(brinquedoDetalhes.getPreco());
            
            repository.save(brinquedo);
            return ResponseEntity.ok(brinquedo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}