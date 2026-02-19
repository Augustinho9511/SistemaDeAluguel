package SistemadeAluguel.service;

import SistemadeAluguel.model.dto.AluguelRequestDTO;
import SistemadeAluguel.model.dto.AluguelResponseDTO;
import SistemadeAluguel.model.entity.Aluguel;
import SistemadeAluguel.model.entity.Cliente;
import SistemadeAluguel.model.entity.Equipamento;
import SistemadeAluguel.model.enums.StatusAluguel;
import SistemadeAluguel.repository.AluguelRepository;
import SistemadeAluguel.repository.ClienteRepository;
import SistemadeAluguel.repository.EquipamentoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


@Service
public class AluguelService {

    private final AluguelRepository aluguelRepository;
    private final EquipamentoRepository equipamentoRepository;
    private final ClienteRepository clienteRepository;

    public AluguelService(AluguelRepository aluguelRepository, EquipamentoRepository equipamentoRepository, ClienteRepository clienteRepository) {
        this.aluguelRepository = aluguelRepository;
        this.equipamentoRepository = equipamentoRepository;
        this.clienteRepository = clienteRepository;
    }

    public AluguelResponseDTO alugar(AluguelRequestDTO request) {

        var cliente = clienteRepository.findById(request.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        var equipamento = equipamentoRepository.findById(request.getEquipamentoId())
                .orElseThrow(() -> new RuntimeException("Equipamento não encontrado"));

        boolean temAtraso = aluguelRepository.existsByClienteClienteIdAndStatusAndDataPrevistaBefore(
                cliente.getClienteId(),
                StatusAluguel.ABERTO,
                LocalDate.now()
        );

        if (temAtraso) {
            throw new RuntimeException("Locação negada: O cliente possui equipamentos com devolução atrasada.");
        }

        if (!equipamento.isDisponivel()) {
            throw new RuntimeException("Equipamento já está alugado por outra pessoa!");
        }

        equipamento.setDisponivel(false);
        equipamentoRepository.save(equipamento);

        LocalDate dataPrevista = LocalDate.now().plusDays(request.getQuantidadeDias());
        BigDecimal valorTotal = equipamento.getValorDiaria().multiply(BigDecimal.valueOf(request.getQuantidadeDias()));

        Aluguel novoAluguel = new Aluguel();
        novoAluguel.setCliente(cliente);
        novoAluguel.setEquipamento(equipamento);
        novoAluguel.setDataInicio(LocalDate.now());
        novoAluguel.setDataPrevista(dataPrevista);
        novoAluguel.setValorTotal(valorTotal);
        novoAluguel.setStatus(StatusAluguel.ABERTO);

        aluguelRepository.save(novoAluguel);

        AluguelResponseDTO response = new AluguelResponseDTO();
        response.setId(novoAluguel.getId());
        response.setNomeCliente(cliente.getNome());
        response.setNomeEquipamento(equipamento.getNome());
        response.setDatapPrevista(novoAluguel.getDataPrevista());
        response.setValorTotal(novoAluguel.getValorTotal());
        response.setStatus(novoAluguel.getStatus().toString());

        return response;
    }

    public String devolver(Long aluguelId) {
        Aluguel aluguel = aluguelRepository.findById(aluguelId)
                .orElseThrow(() -> new RuntimeException("Aluguel não encontrado"));

        if (aluguel.getStatus() == StatusAluguel.FINALIZADO) {
            throw new RuntimeException("Este aluguel já foi finalizado anteriormente!");
        }

        Equipamento equipamento = aluguel.getEquipamento();
        LocalDate hoje = LocalDate.now();
        BigDecimal valorTotal = aluguel.getValorTotal();

        if (hoje.isAfter(aluguel.getDataPrevista())) {
            long diasAtraso = java.time.temporal.ChronoUnit.DAYS.between(aluguel.getDataPrevista(), hoje);
            BigDecimal multa = equipamento.getValorDiaria().multiply(new BigDecimal(diasAtraso)).multiply(new BigDecimal("0.20"));
            valorTotal = valorTotal.add(multa);
        }

        aluguel.setValorTotal(valorTotal);
        aluguel.setStatus(StatusAluguel.FINALIZADO);
        aluguel.setDataDevolucaoReal(hoje);

        aluguelRepository.save(aluguel);

        equipamento.setDisponivel(true);
        equipamentoRepository.save(equipamento);

        return "Equipamento devolvido com sucesso! valor total a pagar: R$" + valorTotal;
    }

    public BigDecimal obterRendimentoGeral(){
        System.out.println("Buscando faturamento...");
        BigDecimal total = aluguelRepository.calcularFaturamentoTotal();
        System.out.println("Total encontrado:" + total);
        return total != null ? total : BigDecimal.ZERO;
    }

    public List<Aluguel> listAtrasados() {
        return aluguelRepository.buscarAlugueisAtrasados(LocalDate.now());
    }

}