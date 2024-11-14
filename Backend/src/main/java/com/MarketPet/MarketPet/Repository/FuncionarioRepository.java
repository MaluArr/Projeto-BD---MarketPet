package com.MarketPet.MarketPet.Repository;

import com.MarketPet.MarketPet.Config.JDBC_Connection;
import com.MarketPet.MarketPet.Model.Funcionario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class FuncionarioRepository {

    private static final Logger logger = LoggerFactory.getLogger(FuncionarioRepository.class);

    public List<Funcionario> findAll() {
        List<Funcionario> funcionarios = new ArrayList<>();
        String sql = "SELECT * FROM funcionario_marketpet";
        logger.info("Executando consulta para buscar todos os funcionários");

        try (Connection con = JDBC_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Funcionario funcionario = mapRowToFuncionario(rs);
                funcionarios.add(funcionario);
            }

        } catch (SQLException e) {
            logger.error("Erro ao buscar todos os funcionários", e);
        }

        return funcionarios;
    }

    public Optional<Funcionario> findByCpf(Long cpfFuncionario) {
        String sql = "SELECT * FROM funcionario_marketpet WHERE cpf_funcionario = ?";
        logger.info("Executando consulta para buscar funcionário com CPF: {}", cpfFuncionario);

        try (Connection con = JDBC_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setLong(1, cpfFuncionario);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Funcionario funcionario = mapRowToFuncionario(rs);
                    return Optional.of(funcionario);
                }
            }

        } catch (SQLException e) {
            logger.error("Erro ao buscar funcionário com CPF: {}", cpfFuncionario, e);
        }

        return Optional.empty();
    }

    public Funcionario save(Funcionario funcionario) {
        String sql = "INSERT INTO funcionario_marketpet (cpf_funcionario, nome) " +
                "VALUES (?, ?) " +
                "ON DUPLICATE KEY UPDATE nome = ?";
        logger.info("Salvando funcionário com CPF: {}", funcionario.getCpfFuncionario());

        try (Connection con = JDBC_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setLong(1, funcionario.getCpfFuncionario());
            ps.setString(2, funcionario.getNome());
            ps.setString(3, funcionario.getNome());

            ps.executeUpdate();
            logger.info("Funcionário salvo com sucesso: {}", funcionario.getCpfFuncionario());

        } catch (SQLException e) {
            logger.error("Erro ao salvar funcionário com CPF: {}", funcionario.getCpfFuncionario(), e);
        }

        return funcionario;
    }

    public void delete(Long cpfFuncionario) {
        String sql = "DELETE FROM funcionario_marketpet WHERE cpf_funcionario = ?";
        logger.info("Deletando funcionário com CPF: {}", cpfFuncionario);

        try (Connection con = JDBC_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setLong(1, cpfFuncionario);
            ps.executeUpdate();
            logger.info("Funcionário deletado com sucesso: {}", cpfFuncionario);

        } catch (SQLException e) {
            logger.error("Erro ao deletar funcionário com CPF: {}", cpfFuncionario, e);
        }
    }

    public List<Funcionario> findByNomeContaining(String parteNome) {
        List<Funcionario> funcionarios = new ArrayList<>();
        String sql = "SELECT * FROM funcionario_marketpet WHERE nome LIKE ?";
        logger.info("Executando consulta para buscar funcionários por nome contendo: {}", parteNome);

        try (Connection con = JDBC_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, "%" + parteNome + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Funcionario funcionario = mapRowToFuncionario(rs);
                    funcionarios.add(funcionario);
                }
            }

        } catch (SQLException e) {
            logger.error("Erro ao buscar funcionários por nome contendo: {}", parteNome, e);
        }

        return funcionarios;
    }

    private Funcionario mapRowToFuncionario(ResultSet rs) throws SQLException {
        Funcionario funcionario = new Funcionario();
        funcionario.setCpfFuncionario(rs.getLong("cpf_funcionario"));
        funcionario.setNome(rs.getString("nome"));
        return funcionario;
    }
}