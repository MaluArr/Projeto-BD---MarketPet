package com.MarketPet.MarketPet.Repository;


import com.MarketPet.MarketPet.Config.JDBC_Connection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;


@Repository
public class DashboardRepository {


    private static final Logger logger = LoggerFactory.getLogger(DashboardRepository.class);


    public List<Map<String, Object>> getDailySales() {
        String sql = "SELECT data, total_vendas, quantidade_vendas FROM vendas_resumo ORDER BY data";
        return executeQuery(sql);
    }


    public List<Map<String, Object>> getTopProducts() {
        String sql = "SELECT codigo_produto, total_vendas, quantidade_vendas, avaliacao_media FROM produto_resumo ORDER BY total_vendas DESC LIMIT 10";
        return executeQuery(sql);
    }


    public List<Map<String, Object>> getTopSellers() {
        String sql = "SELECT cpf_vendedor, total_vendas, quantidade_vendas, avaliacao_media FROM vendedor_resumo ORDER BY total_vendas DESC LIMIT 10";
        return executeQuery(sql);
    }


    public List<Map<String, Object>> getProductDistribution() {
        String sql = "SELECT codigo_produto, total_vendas FROM produto_resumo";
        return executeQuery(sql);
    }


    public List<Map<String, Object>> getAverageRatingEvolution() {
        String sql = "SELECT codigo_produto AS identifier, avaliacao_media FROM produto_resumo UNION ALL SELECT cpf_vendedor AS identifier, avaliacao_media FROM vendedor_resumo";
        return executeQuery(sql);
    }


    public List<Map<String, Object>> getSellerPerformanceComparison() {
        String sql = "SELECT cpf_vendedor, total_vendas, quantidade_vendas, avaliacao_media FROM vendedor_resumo";
        return executeQuery(sql);
    }


    public List<Map<String, Object>> getSalesVsRatings() {
        String sql = "SELECT quantidade_vendas, avaliacao_media FROM produto_resumo UNION ALL SELECT quantidade_vendas, avaliacao_media FROM vendedor_resumo";
        return executeQuery(sql);
    }


    private List<Map<String, Object>> executeQuery(String sql) {
        List<Map<String, Object>> resultList = new ArrayList<>();
        try (Connection con = JDBC_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {


            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                int columnCount = rs.getMetaData().getColumnCount();
                for (int i = 1; i <= columnCount; i++) {
                    row.put(rs.getMetaData().getColumnName(i), rs.getObject(i));
                }
                resultList.add(row);
            }
        } catch (Exception e) {
            logger.error("Erro ao executar consulta SQL: " + sql, e);
        }
        return resultList;
    }
}

