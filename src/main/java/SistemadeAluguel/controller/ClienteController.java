package SistemadeAluguel.controller;

import SistemadeAluguel.model.entity.Cliente;
import SistemadeAluguel.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteRepository clienteRepository;

    @PostMapping
    public ResponseEntity<Cliente>  criar(@RequestBody Cliente cliente) {
        return ResponseEntity.status(201).body(clienteRepository.save(cliente));
    }
}
