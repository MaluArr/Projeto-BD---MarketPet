package com.MarketPet.MarketPet.Repository;

import com.MarketPet.MarketPet.Config.JDBC_Connection;
import com.MarketPet.MarketPet.Model.Curadoria;
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
public class CuradoriaRepository {

    private static final Logger logger = LoggerFactory.getLogger(CuradoriaRepository.class);

    public List<Curadoria> findAll() {
        List<Curadoria> curadorias = new ArrayList<>();
        String sql = "SELECT * FROM curadoria";
        logger.info("Executando consulta para buscar todas as curadorias");

        try (Connection con = JDBC_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Curadoria curadoria = mapRowToCuradoria(rs);
                curadorias.add(curadoria);
            }

        } catch (SQLException e) {
            logger.error("Erro ao buscar todas as curadorias", e);
        }

        return curadorias;
    }

    public Optional<Curadoria> findByCodigo(Integer codigoCuradoria) {
        String sql = "SELECT * FROM curadoria WHERE codigo_curadoria = ?";
        logger.info("Executando consulta para buscar curadoria com código: {}", codigoCuradoria);

        try (Connection con = JDBC_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, codigoCuradoria);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Curadoria curadoria = mapRowToCuradoria(rs);
                    return Optional.of(curadoria);
                }
            }

        } catch (SQLException e) {
            logger.error("Erro ao buscar curadoria com código: {}", codigoCuradoria, e);
        }

        return Optional.empty();
    }

    public Curadoria save(Curadoria curadoria) {
        String sql = "INSERT INTO curadoria (codigo_curadoria, descricao, resultado_curadoria, id_curador, codigo_produto) " +
                "VALUES (?, ?, ?, ?, ?) " +
                "ON DUPLICATE KEY UPDATE " +
                "descricao = ?, resultado_curadoria = ?, id_curador = ?, codigo_produto = ?";
        logger.info("Salvando curadoria com código: {}", curadoria.getCodigoCuradoria());

        try (Connection con = JDBC_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, curadoria.getCodigoCuradoria());
            ps.setString(2, curadoria.getDescricao());
            ps.setString(3, curadoria.getResultadoCuradoria());
            ps.setInt(4, curadoria.getIdCurador());
            ps.setInt(5, curadoria.getCodigoProduto());

            ps.setString(6, curadoria.getDescricao());
            ps.setString(7, curadoria.getResultadoCuradoria());
            ps.setInt(8, curadoria.getIdCurador());
            ps.setInt(9, curadoria.getCodigoProduto());

            ps.executeUpdate();
            logger.info("Curadoria salva com sucesso: {}", curadoria.getCodigoCuradoria());

        } catch (SQLException e) {
            logger.error("Erro ao salvar curadoria com código: {}", curadoria.getCodigoCuradoria(), e);
        }

        return curadoria;
    }

    public void delete(Integer codigoCuradoria) {
        String sql = "DELETE FROM curadoria WHERE codigo_curadoria = ?";
        logger.info("Deletando curadoria com código: {}", codigoCuradoria);

        try (Connection con = JDBC_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, codigoCuradoria);
            ps.executeUpdate();
            logger.info("Curadoria deletada com sucesso: {}", codigoCuradoria);

        } catch (SQLException e) {
            logger.error("Erro ao deletar curadoria com código: {}", codigoCuradoria, e);
        }
    }

    public List<Curadoria> findByCurador(Integer idCurador) {
        List<Curadoria> curadorias = new ArrayList<>();
        String sql = "SELECT * FROM curadoria WHERE id_curador = ?";
        logger.info("Executando consulta para buscar curadorias por curador com ID: {}", idCurador);

        try (Connection con = JDBC_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idCurador);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Curadoria curadoria = mapRowToCuradoria(rs);
                    curadorias.add(curadoria);
                }
            }

        } catch (SQLException e) {
            logger.error("Erro ao buscar curadorias por curador com ID: {}", idCurador, e);
        }

        return curadorias;
    }

    public List<Curadoria> findByProduto(Integer codigoProduto) {
        List<Curadoria> curadorias = new ArrayList<>();
        String sql = "SELECT * FROM curadoria WHERE codigo_produto = ?";
        logger.info("Executando consulta para buscar curadorias por produto com código: {}", codigoProduto);

        try (Connection con = JDBC_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, codigoProduto);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Curadoria curadoria = mapRowToCuradoria(rs);
                    curadorias.add(curadoria);
                }
            }

        } catch (SQLException e) {
            logger.error("Erro ao buscar curadorias por produto com código: {}", codigoProduto, e);
        }

        return curadorias;
    }

    private Curadoria mapRowToCuradoria(ResultSet rs) throws SQLException {
        Curadoria curadoria = new Curadoria();
        curadoria.setCodigoCuradoria(rs.getInt("codigo_curadoria"));
        curadoria.setDescricao(rs.getString("descricao"));
        curadoria.setResultadoCuradoria(rs.getString("resultado_curadoria"));
        curadoria.setIdCurador(rs.getInt("id_curador"));
        curadoria.setCodigoProduto(rs.getInt("codigo_produto"));
        return curadoria;
    }
}