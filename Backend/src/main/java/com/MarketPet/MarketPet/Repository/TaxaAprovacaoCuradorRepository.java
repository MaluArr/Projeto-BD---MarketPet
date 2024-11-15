package com.MarketPet.MarketPet.Repository;

import com.MarketPet.MarketPet.Config.JDBC_Connection;
import org.springframework.stereotype.Repository;
import com.MarketPet.MarketPet.Exception.RelatorioException;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Repository
public class TaxaAprovacaoCuradorRepository {

    // Método para obter Taxa de aprovação de produtos por curador:
    public List<EstatisticaCurador> obterTaxaAprovacaoCuradores() {
        List<EstatisticaCurador> estatisticas = new ArrayList<>();
        String sql = "{CALL sp_taxa_aprovacao_curadores()}";

        try (Connection conn = JDBC_Connection.getConnection();
             CallableStatement cstmt = conn.prepareCall(sql);
             ResultSet rs = cstmt.executeQuery()) {

            while (rs.next()) {
                estatisticas.add(new EstatisticaCurador(
                        rs.getInt("id_curador"),
                        rs.getString("nome_curador"),
                        rs.getInt("total_produtos"),
                        rs.getInt("produtos_aprovados"),
                        rs.getBigDecimal("taxa_aprovacao")
                ));
            }
        } catch (SQLException e) {
            String errorMessage = "Erro ao obter taxa de aprovação dos curadores: " + e.getMessage();
            e.printStackTrace();
            throw new RelatorioException(errorMessage, e);
        }

        return estatisticas;
    }

    // Classe interna para representar as estatísticas de um curador
    public static class EstatisticaCurador {
        private int idCurador;
        private String nomeCurador;
        private int totalProdutos;
        private int produtosAprovados;
        private BigDecimal taxaAprovacao;

        public EstatisticaCurador(int idCurador, String nomeCurador, int totalProdutos,
                                  int produtosAprovados, BigDecimal taxaAprovacao) {
            this.idCurador = idCurador;
            this.nomeCurador = nomeCurador;
            this.totalProdutos = totalProdutos;
            this.produtosAprovados = produtosAprovados;
            this.taxaAprovacao = taxaAprovacao;
        }

        // Getters
        public int getIdCurador() { return idCurador; }
        public String getNomeCurador() { return nomeCurador; }
        public int getTotalProdutos() { return totalProdutos; }
        public int getProdutosAprovados() { return produtosAprovados; }
        public BigDecimal getTaxaAprovacao() { return taxaAprovacao; }
    }

}