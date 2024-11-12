package com.MarketPet.MarketPet.Controller;

import com.MarketPet.MarketPet.Model.Chat;
import com.MarketPet.MarketPet.Service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chats")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @GetMapping
    public List<Chat> listarTodos() {
        return chatService.listarTodos();
    }

    @GetMapping("/{idChat}")
    public ResponseEntity<Chat> buscarPorId(@PathVariable Integer idChat) {
        return chatService.buscarPorId(idChat)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Chat> criarChat(@RequestBody Chat chat) {
        Chat novoChat = chatService.criarChat(chat);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoChat);
    }

    @PutMapping("/{idChat}")
    public ResponseEntity<Chat> atualizarChat(
            @PathVariable Integer idChat,
            @RequestBody Chat chat) {
        chat.setIdChat(idChat);
        Chat chatAtualizado = chatService.atualizarChat(chat);
        return ResponseEntity.ok(chatAtualizado);
    }

    @DeleteMapping("/{idChat}")
    public ResponseEntity<Void> deletarChat(@PathVariable Integer idChat) {
        chatService.deletarChat(idChat);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/vendedor/{cpfVendedor}")
    public List<Chat> buscarPorVendedor(@PathVariable Long cpfVendedor) {
        return chatService.buscarPorVendedor(cpfVendedor);
    }

    @GetMapping("/comprador/{cpfComprador}")
    public List<Chat> buscarPorComprador(@PathVariable Long cpfComprador) {
        return chatService.buscarPorComprador(cpfComprador);
    }

    @GetMapping("/codigo/{codigoChatvc}")
    public ResponseEntity<Chat> buscarPorCodigoChatvc(@PathVariable String codigoChatvc) {
        return chatService.buscarPorCodigoChatvc(codigoChatvc)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}