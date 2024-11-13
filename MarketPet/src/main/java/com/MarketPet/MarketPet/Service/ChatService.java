package com.MarketPet.MarketPet.Service;

import com.MarketPet.MarketPet.Model.Chat;
import com.MarketPet.MarketPet.Repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ChatService {

    @Autowired
    private ChatRepository chatRepository;

    public List<Chat> listarTodos() {
        return chatRepository.findAll();
    }

    public Optional<Chat> buscarPorId(Integer idChat) {
        return chatRepository.findById(idChat);
    }

    public Chat criarChat(Chat chat) {
        if (!chat.isCodigoChatvcValido()) {
            throw new RuntimeException("Código do chat inválido");
        }

        if (chat.getCodigoChatvc() == null) {
            chat.setCodigoChatvc(gerarCodigoUnico());
        }

        LocalDate dataAtual = LocalDate.now();
        chat.setDataCriacao(dataAtual);
        chat.setUltimaAtualizacao(dataAtual);

        return chatRepository.save(chat);
    }

    public Chat atualizarChat(Chat chat) {
        Chat chatExistente = chatRepository.findById(chat.getIdChat())
                .orElseThrow(() -> new RuntimeException("Chat não encontrado"));

        if (!chat.isCodigoChatvcValido()) {
            throw new RuntimeException("Código do chat inválido");
        }

        chatExistente.setConteudo(chat.getConteudo());
        chatExistente.setUltimaAtualizacao(LocalDate.now());

        return chatRepository.save(chatExistente);
    }

    public void deletarChat(Integer idChat) {
        chatRepository.findById(idChat)
                .orElseThrow(() -> new RuntimeException("Chat não encontrado"));

        chatRepository.delete(idChat);
    }

    public List<Chat> buscarPorVendedor(Long cpfVendedor) {
        return chatRepository.findByVendedor(cpfVendedor);
    }

    public List<Chat> buscarPorComprador(Long cpfComprador) {
        return chatRepository.findByComprador(cpfComprador);
    }

    public Optional<Chat> buscarPorCodigoChatvc(String codigoChatvc) {
        return chatRepository.findByCodigoChatvc(codigoChatvc);
    }

    private String gerarCodigoUnico() {
        return UUID.randomUUID().toString().substring(0, 20);
    }
}