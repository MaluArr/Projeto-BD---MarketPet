package com.MarketPet.MarketPet.Repository;

import com.MarketPet.MarketPet.Config.JDBC_Connection;
import com.MarketPet.MarketPet.Model.Chat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ChatRepository {

    private static final Logger logger = LoggerFactory.getLogger(ChatRepository.class);

    public List<Chat> findAll() {
        List<Chat> chats = new ArrayList<>();
        String sql = "SELECT * FROM chat_v_x_c";
        logger.info("Executando consulta para buscar todos os chats");

        try (Connection con = JDBC_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Chat chat = mapRowToChat(rs);
                chats.add(chat);
            }

        } catch (SQLException e) {
            logger.error("Erro ao buscar todos os chats", e);
        }

        return chats;
    }

    public Optional<Chat> findById(Integer idChat) {
        String sql = "SELECT * FROM chat_v_x_c WHERE id_chat = ?";
        logger.info("Executando consulta para buscar chat com ID: {}", idChat);

        try (Connection con = JDBC_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idChat);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Chat chat = mapRowToChat(rs);
                    return Optional.of(chat);
                }
            }

        } catch (SQLException e) {
            logger.error("Erro ao buscar chat com ID: {}", idChat, e);
        }

        return Optional.empty();
    }

    public Optional<Chat> findByCodigoChatvc(String codigoChatvc) {
        String sql = "SELECT * FROM chat_v_x_c WHERE codigo_chatvc = ?";
        logger.info("Executando consulta para buscar chat com código: {}", codigoChatvc);

        try (Connection con = JDBC_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, codigoChatvc);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Chat chat = mapRowToChat(rs);
                    return Optional.of(chat);
                }
            }

        } catch (SQLException e) {
            logger.error("Erro ao buscar chat com código: {}", codigoChatvc, e);
        }

        return Optional.empty();
    }

    public Chat save(Chat chat) {
        String sql = "INSERT INTO chat_v_x_c (id_chat, cpf_vendedor, cpf_comprador, codigo_chatvc, conteudo, data_criacao, ultima_atualizacao) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?) " +
                "ON DUPLICATE KEY UPDATE " +
                "cpf_vendedor = ?, cpf_comprador = ?, codigo_chatvc = ?, conteudo = ?, ultima_atualizacao = ?";
        logger.info("Salvando chat com ID: {}", chat.getIdChat());

        try (Connection con = JDBC_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, chat.getIdChat());
            ps.setLong(2, chat.getCpfVendedor());
            ps.setLong(3, chat.getCpfComprador());
            ps.setString(4, chat.getCodigoChatvc());
            ps.setString(5, chat.getConteudo());
            ps.setDate(6, Date.valueOf(chat.getDataCriacao()));
            ps.setDate(7, Date.valueOf(chat.getUltimaAtualizacao()));

            ps.setLong(8, chat.getCpfVendedor());
            ps.setLong(9, chat.getCpfComprador());
            ps.setString(10, chat.getCodigoChatvc());
            ps.setString(11, chat.getConteudo());
            ps.setDate(12, Date.valueOf(chat.getUltimaAtualizacao()));

            ps.executeUpdate();
            logger.info("Chat salvo com sucesso: {}", chat.getIdChat());

        } catch (SQLException e) {
            logger.error("Erro ao salvar chat com ID: {}", chat.getIdChat(), e);
        }

        return chat;
    }

    public void delete(Integer idChat) {
        String sql = "DELETE FROM chat_v_x_c WHERE id_chat = ?";
        logger.info("Deletando chat com ID: {}", idChat);

        try (Connection con = JDBC_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idChat);
            ps.executeUpdate();
            logger.info("Chat deletado com sucesso: {}", idChat);

        } catch (SQLException e) {
            logger.error("Erro ao deletar chat com ID: {}", idChat, e);
        }
    }

    public List<Chat> findByVendedor(Long cpfVendedor) {
        List<Chat> chats = new ArrayList<>();
        String sql = "SELECT * FROM chat_v_x_c WHERE cpf_vendedor = ?";
        logger.info("Executando consulta para buscar chats por vendedor com CPF: {}", cpfVendedor);

        try (Connection con = JDBC_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setLong(1, cpfVendedor);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Chat chat = mapRowToChat(rs);
                    chats.add(chat);
                }
            }

        } catch (SQLException e) {
            logger.error("Erro ao buscar chats por vendedor com CPF: {}", cpfVendedor, e);
        }

        return chats;
    }

    public List<Chat> findByComprador(Long cpfComprador) {
        List<Chat> chats = new ArrayList<>();
        String sql = "SELECT * FROM chat_v_x_c WHERE cpf_comprador = ?";
        logger.info("Executando consulta para buscar chats por comprador com CPF: {}", cpfComprador);

        try (Connection con = JDBC_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setLong(1, cpfComprador);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Chat chat = mapRowToChat(rs);
                    chats.add(chat);
                }
            }

        } catch (SQLException e) {
            logger.error("Erro ao buscar chats por comprador com CPF: {}", cpfComprador, e);
        }

        return chats;
    }

    private Chat mapRowToChat(ResultSet rs) throws SQLException {
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
    }
}