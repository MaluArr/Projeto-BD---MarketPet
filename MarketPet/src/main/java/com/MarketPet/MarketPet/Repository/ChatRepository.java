package com.MarketPet.MarketPet.Repository;

import com.MarketPet.MarketPet.Model.Chat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class ChatRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<Chat> chatRowMapper = (rs, rowNum) -> {
        Chat chat = new Chat();
        chat.setIdChat(rs.getInt("id_chat"));
        chat.setCpfVendedor(rs.getLong("cpf_vendedor"));
        chat.setCpfComprador(rs.getLong("cpf_comprador"));
        chat.setCodigoChatvc(rs.getString("codigo_chatvc"));
        chat.setConteudo(rs.getString("conteudo"));
        Date dataCriacao = rs.getDate("data_criacao");
        chat.setDataCriacao(dataCriacao != null ? dataCriacao.toLocalDate() : null);
        Date ultimaAtualizacao = rs.getDate("ultima_atualizacao");
        chat.setUltimaAtualizacao(ultimaAtualizacao != null ? ultimaAtualizacao.toLocalDate() : null);
        return chat;
    };

    public List<Chat> findAll() {
        return jdbcTemplate.query("SELECT * FROM chat_v_x_c", chatRowMapper);
    }

    public Optional<Chat> findById(Integer idChat) {
        try {
            Chat chat = jdbcTemplate.queryForObject(
                    "SELECT * FROM chat_v_x_c WHERE id_chat = ?",
                    new Object[]{idChat},
                    chatRowMapper
            );
            return Optional.ofNullable(chat);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public Optional<Chat> findByCodigoChatvc(String codigoChatvc) {
        try {
            Chat chat = jdbcTemplate.queryForObject(
                    "SELECT * FROM chat_v_x_c WHERE codigo_chatvc = ?",
                    new Object[]{codigoChatvc},
                    chatRowMapper
            );
            return Optional.ofNullable(chat);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public Chat save(Chat chat) {
        String sql = "INSERT INTO chat_v_x_c (id_chat, cpf_vendedor, cpf_comprador, codigo_chatvc, conteudo, data_criacao, ultima_atualizacao) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?) " +
                "ON DUPLICATE KEY UPDATE " +
                "cpf_vendedor = ?, cpf_comprador = ?, codigo_chatvc = ?, conteudo = ?, ultima_atualizacao = ?";

        jdbcTemplate.update(sql,
                chat.getIdChat(), chat.getCpfVendedor(), chat.getCpfComprador(),
                chat.getCodigoChatvc(), chat.getConteudo(), Date.valueOf(chat.getDataCriacao()),
                Date.valueOf(chat.getUltimaAtualizacao()), chat.getCpfVendedor(),
                chat.getCpfComprador(), chat.getCodigoChatvc(), chat.getConteudo(),
                Date.valueOf(chat.getUltimaAtualizacao())
        );

        return chat;
    }

    public void delete(Integer idChat) {
        jdbcTemplate.update("DELETE FROM chat_v_x_c WHERE id_chat = ?", idChat);
    }

    public List<Chat> findByVendedor(Long cpfVendedor) {
        return jdbcTemplate.query(
                "SELECT * FROM chat_v_x_c WHERE cpf_vendedor = ?",
                new Object[]{cpfVendedor},
                chatRowMapper
        );
    }

    public List<Chat> findByComprador(Long cpfComprador) {
        return jdbcTemplate.query(
                "SELECT * FROM chat_v_x_c WHERE cpf_comprador = ?",
                new Object[]{cpfComprador},
                chatRowMapper
        );
    }
}