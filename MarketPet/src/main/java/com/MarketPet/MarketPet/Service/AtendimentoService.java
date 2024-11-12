package com.MarketPet.MarketPet.Service;

import com.MarketPet.MarketPet.Model.Atendimento;
import com.MarketPet.MarketPet.Repository.AtendimentoRepository;
import com.MarketPet.MarketPet.Repository.FuncionarioRepository;
import com.MarketPet.MarketPet.Repository.UsuarioRepository;
import com.MarketPet.MarketPet.Repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AtendimentoService {

    @Autowired
    private AtendimentoRepository atendimentoRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ChatRepository chatRepository;

    public List<Atendimento> listarTodos() {
        return atendimentoRepository.findAll();
    }

    public Optional<Atendimento> buscarPorId(Integer idAtendimento) {
        return atendimentoRepository.findById(idAtendimento);
    }

    public Atendimento criarAtendimento(Atendimento atendimento) {
        // Validações
        if (!atendimento.isFuncionarioValido()) {
            throw new RuntimeException("Funcionário inválido");
        }

        if (!atendimento.isUsuarioValido()) {
            throw new RuntimeException("Usuário inválido");
        }

        if (!atendimento.isChatValido()) {
            throw new RuntimeException("Chat inválido");
        }

        if (!atendimento.isCategoriaValida()) {
            throw new RuntimeException("Categoria inválida");
        }

        // Define data de atendimento como hoje se não for especificada
        if (atendimento.getDataAtendimento() == null) {
            atendimento.setDataAtendimento(LocalDate.now());
        }

        // Verifica existência do funcionário
        funcionarioRepository.findByCpf(atendimento.getFuncionario().getCpfFuncionario())
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));

        // Verifica existência do usuário
        usuarioRepository.findByCpf(atendimento.getUsuario().getCpf())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        // Verifica existência do chat
        chatRepository.findById(atendimento.getChat().getIdChat())
                .orElseThrow(() -> new RuntimeException("Chat não encontrado"));

        // Verifica se o chat já está associado a outro atendimento
        atendimentoRepository.findByChat(atendimento.getChat().getIdChat())
                .ifPresent(a -> {
                    throw new RuntimeException("Chat já associado a outro atendimento");
                });

        return atendimentoRepository.save(atendimento);
    }

    public Atendimento atualizarAtendimento(Atendimento atendimento) {
        // Verifica se o atendimento existe
        atendimentoRepository.findById(atendimento.getIdAtendimento())
                .orElseThrow(() -> new RuntimeException("Atendimento não encontrado"));

        // Validações
        if (!atendimento.isFuncionarioValido()) {
            throw new RuntimeException("Funcionário inválido");
        }

        if (!atendimento.isUsuarioValido()) {
            throw new RuntimeException("Usuário inválido");
        }

        if (!atendimento.isChatValido()) {
            throw new RuntimeException("Chat inválido");
        }

        if (!atendimento.isCategoriaValida()) {
            throw new RuntimeException("Categoria inválida");
        }

        // Verifica existência do funcionário
        funcionarioRepository.findByCpf(atendimento.getFuncionario().getCpfFuncionario())
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));

        // Verifica existência do usuário
        usuarioRepository.findByCpf(atendimento.getUsuario().getCpf())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        // Verifica existência do chat
        chatRepository.findById(atendimento.getChat().getIdChat())
                .orElseThrow(() -> new RuntimeException("Chat não encontrado"));

        return atendimentoRepository.save(atendimento);
    }

    public void deletarAtendimento(Integer idAtendimento) {
        atendimentoRepository.findById(idAtendimento)
                .orElseThrow(() -> new RuntimeException("Atendimento não encontrado"));

        atendimentoRepository.delete(idAtendimento);
    }

    public List<Atendimento> buscarPorFuncionario(Long cpfFuncionario) {
        return atendimentoRepository.findByFuncionario(cpfFuncionario);
    }

    public List<Atendimento> buscarPorUsuario(Long cpfUsuario) {
        return atendimentoRepository.findByUsuario(cpfUsuario);
    }

    public List<Atendimento> buscarPorCategoria(String categoria) {
        return atendimentoRepository.findByCategoria(categoria);
    }

    public Optional<Atendimento> buscarPorChat(Integer idChat) {
        return atendimentoRepository.findByChat(idChat);
    }
}