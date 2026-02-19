package SistemadeAluguel.service;

import SistemadeAluguel.model.entity.Cliente;
import SistemadeAluguel.repository.ClienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Transactional
    public Cliente salvar(Cliente cliente) {

        if (cliente.getEmail() == null || cliente.getEmail().contains("@")) {
            throw new RuntimeException("O e=mail informado é inválido!");
        }

        if (clienteRepository.existsByCpf(cliente.getCpf())){
            throw new RuntimeException("Não foi possível concluir o cadastro. Verifique os dados ou entre em contato com o suporte.");
        }

        if (clienteRepository.existsByEmail(cliente.getEmail())) {
            throw new RuntimeException("Não foi possível concluir o cadastro. Verifique os dados ou entre em contato com o suporte.");
        }

        return clienteRepository.save(cliente);
    }

}
