package com.MarketPet.MarketPet.Repository;

import com.MarketPet.MarketPet.Model.Atendimento;
import com.MarketPet.MarketPet.Model.Funcionario;
import com.MarketPet.MarketPet.Model.Usuario;
import com.MarketPet.MarketPet.Model.Chat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class AtendimentoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ChatRepository chatRepository;

    private RowMapper<Atendimento> atendimentoRowMapper = (rs, rowNum) -> {
        Atendimento atendimento = new Atendimento();
        atendimento.setIdAtendimento(rs.getInt("id_atendimento"));

        // Busca do Funcionário
        Long cpfFuncionario = rs.getLong("cpf_funcionario");
        Optional<Funcionario> funcionarioOpt = funcionarioRepository.findByCpf(cpfFuncionario);
        funcionarioOpt.ifPresent(atendimento::setFuncionario);

        // Busca do Usuário
        Long cpfUsuario = rs.getLong("cpf_usuario");
        Optional<Usuario> usuarioOpt = usuarioRepository.findByCpf(cpfUsuario);
        usuarioOpt.ifPresent(atendimento::setUsuario);

        // Busca do Chat
        Integer idChat = rs.getInt("id_chat");
        Optional<Chat> chatOpt = chatRepository.findById(idChat);
        chatOpt.ifPresent(atendimento::setChat);

        // Conversão de Date para LocalDate
        Date dataAtendimento = rs.getDate("data_atendimento");
        atendimento.setDataAtendimento(dataAtendimento != null ? dataAtendimento.toLocalDate() : null);

        atendimento.setCategoria(rs.getString("categoria"));

        return atendimento;
    };

    public List<Atendimento> findAll() {
        return jdbcTemplate.query("SELECT * FROM atendimento", atendimentoRowMapper);
    }

    public Optional<Atendimento> findById(Integer idAtendimento) {
        try {
            Atendimento atendimento = jdbcTemplate.queryForObject(
                    "SELECT * FROM atendimento WHERE id_atendimento = ?",
                    new Object[]{idAtendimento},
                    atendimentoRowMapper
            );
            return Optional.ofNullable(atendimento);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public Optional<Atendimento> findByChat(Integer idChat) {
        try {
            Atendimento atendimento = jdbcTemplate.queryForObject(
                    "SELECT * FROM atendimento WHERE id_chat = ?",
                    new Object[]{idChat},
                    atendimentoRowMapper
            );
            return Optional.ofNullable(atendimento);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public Atendimento save(Atendimento atendimento) {
        String sql = "INSERT INTO atendimento " +
                "(id_atendimento, cpf_funcionario, cpf_usuario, id_chat, data_atendimento, categoria) " +
                "VALUES (?, ?, ?, ?, ?, ?) " +
                "ON DUPLICATE KEY UPDATE " +
                "cpf_funcionario = ?, cpf_usuario = ?, id_chat = ?, data_atendimento = ?, categoria = ?";

        jdbcTemplate.update(sql,
                atendimento.getIdAtendimento(),
                atendimento.getFuncionario().getCpfFuncionario(),
                atendimento.getUsuario().getCpf(),
                atendimento.getChat().getIdChat(),
                Date.valueOf(atendimento.getDataAtendimento()),
                atendimento.getCategoria(),
                // Valores para UPDATE
                atendimento.getFuncionario().getCpfFuncionario(),
                atendimento.getUsuario().getCpf(),
                atendimento.getChat().getIdChat(),
                Date.valueOf(atendimento.getDataAtendimento()),
                atendimento.getCategoria()
        );

        return atendimento;
    }

    public void delete(Integer idAtendimento) {
        jdbcTemplate.update("DELETE FROM atendimento WHERE id_atendimento = ?", idAtendimento);
    }

    public List<Atendimento> findByFuncionario(Long cpfFuncionario) {
        return jdbcTemplate.query(
                "SELECT * FROM atendimento WHERE cpf_funcionario = ?",
                new Object[]{cpfFuncionario},
                atendimentoRowMapper
        );
    }

    public List<Atendimento> findByUsuario(Long cpfUsuario) {
        return jdbcTemplate.query(
                "SELECT * FROM atendimento WHERE cpf_usuario = ?",
                new Object[]{cpfUsuario},
                atendimentoRowMapper
        );
    }

    public List<Atendimento> findByCategoria(String categoria) {
        return jdbcTemplate.query(
                "SELECT * FROM atendimento WHERE categoria = ?",
                new Object[]{categoria},
                atendimentoRowMapper
        );
    }
}