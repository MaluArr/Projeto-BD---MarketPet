package com.MarketPet.MarketPet.Repository;

import com.MarketPet.MarketPet.Config.JDBC_Connection;
import com.MarketPet.MarketPet.Model.Carrinho;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class CarrinhoRepository {

    private static final Logger logger = LoggerFactory.getLogger(CarrinhoRepository.class);

    public List<Carrinho> findAll() {
        List<Carrinho> carrinhos = new ArrayList<>();
        String sql = "SELECT * FROM carrinho_de_compras";
        logger.info("Executando consulta para buscar todos os carrinhos");

        try (Connection con = JDBC_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Carrinho carrinho = mapRowToCarrinho(rs);
                carrinhos.add(carrinho);
            }

        } catch (SQLException e) {
            logger.error("Erro ao buscar todos os carrinhos", e);
        }

        return carrinhos;
    }

    public Optional<Carrinho> findById(Integer idCarrinho) {
        String sql = "SELECT * FROM carrinho_de_compras WHERE id_carrinho = ?";
        logger.info("Executando consulta para buscar carrinho com ID: {}", idCarrinho);

        try (Connection con = JDBC_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idCarrinho);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Carrinho carrinho = mapRowToCarrinho(rs);
                    return Optional.of(carrinho);
                }
            }

        } catch (SQLException e) {
            logger.error("Erro ao buscar carrinho com ID: {}", idCarrinho, e);
        }

        return Optional.empty();
    }

    public Carrinho save(Carrinho carrinho) {
        String sql = "INSERT INTO carrinho_de_compras (id_carrinho, valor_total, cpf_comprador) " +
                "VALUES (?, ?, ?) " +
                "ON DUPLICATE KEY UPDATE " +
                "valor_total = ?, cpf_comprador = ?";
        logger.info("Salvando carrinho com ID: {}", carrinho.getIdCarrinho());

        try (Connection con = JDBC_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, carrinho.getIdCarrinho());
            ps.setBigDecimal(2, carrinho.getValorTotal());
            ps.setLong(3, carrinho.getCpfComprador());
            ps.setBigDecimal(4, carrinho.getValorTotal());
            ps.setLong(5, carrinho.getCpfComprador());

            ps.executeUpdate();
            logger.info("Carrinho salvo com sucesso: {}", carrinho.getIdCarrinho());

        } catch (SQLException e) {
            logger.error("Erro ao salvar carrinho com ID: {}", carrinho.getIdCarrinho(), e);
        }

        return carrinho;
    }

    public void deleteById(Integer idCarrinho) {
        String sql = "DELETE FROM carrinho_de_compras WHERE id_carrinho = ?";
        logger.info("Deletando carrinho com ID: {}", idCarrinho);

        try (Connection con = JDBC_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idCarrinho);
            ps.executeUpdate();
            logger.info("Carrinho deletado com sucesso: {}", idCarrinho);

        } catch (SQLException e) {
            logger.error("Erro ao deletar carrinho com ID: {}", idCarrinho, e);
        }
    }

    public List<Map<String, Object>> getCarrinhosPorComprador() {
        List<Map<String, Object>> resultados = new ArrayList<>();
        String sql = "SELECT u.nome_real, COUNT(*) as total_carrinhos, SUM(c.valor_total) as valor_total " +
                "FROM carrinho_de_compras c " +
                "JOIN usuario u ON c.cpf_comprador = u.CPF " +
                "GROUP BY u.CPF, u.nome_real";
        logger.info("Executando consulta para buscar carrinhos por comprador");

        try (Connection con = JDBC_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                resultados.add(Map.of(
                        "nome_real", rs.getString("nome_real"),
                        "total_carrinhos", rs.getInt("total_carrinhos"),
                        "valor_total", rs.getBigDecimal("valor_total")
                ));
            }

        } catch (SQLException e) {
            logger.error("Erro ao buscar carrinhos por comprador", e);
        }

        return resultados;
    }

    public List<Map<String, Object>> getCarrinhosPorValor() {
        List<Map<String, Object>> resultados = new ArrayList<>();
        String sql = "SELECT CASE " +
                "    WHEN valor_total < 100 THEN 'Até R$100' " +
                "    WHEN valor_total >= 100 AND valor_total < 500 THEN 'R$100 a R$500' " +
                "    ELSE 'Acima de R$500' " +
                "END AS faixa_valor, " +
                "COUNT(*) as total_carrinhos " +
                "FROM carrinho_de_compras " +
                "GROUP BY faixa_valor";
        logger.info("Executando consulta para buscar carrinhos por faixa de valor");

        try (Connection con = JDBC_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                resultados.add(Map.of(
                        "faixa_valor", rs.getString("faixa_valor"),
                        "total_carrinhos", rs.getInt("total_carrinhos")
                ));
            }

        } catch (SQLException e) {
            logger.error("Erro ao buscar carrinhos por faixa de valor", e);
        }

        return resultados;
    }

    private Carrinho mapRowToCarrinho(ResultSet rs) throws SQLException {
        Carrinho carrinho = new Carrinho();
        carrinho.setIdCarrinho(rs.getInt("id_carrinho"));
        carrinho.setValorTotal(rs.getBigDecimal("valor_total"));
        carrinho.setCpfComprador(rs.getLong("cpf_comprador"));
        return carrinho;
    }
}