package com.MarketPet.MarketPet.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig implements WebMvcConfigurer {

    /**
     * Configura o DataSource para a conexão com o banco de dados MySQL
     */
    @Bean
    public DataSource dataSource() {
        // Cria uma nova fonte de dados para conexão com o banco
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        // Configura o driver JDBC do MySQL
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");

        // URL de conexão com o banco de dados
        dataSource.setUrl("jdbc:mysql://localhost:3306/MarketPet");

        // Credenciais de acesso ao banco de dados
        dataSource.setUsername("root");
        dataSource.setPassword("1234");

        return dataSource;
    }

    /**
     * Cria um template JDBC para execução de operações no banco de dados
     */
    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        // Cria e retorna um JdbcTemplate usando a fonte de dados fornecida
        return new JdbcTemplate(dataSource);
    }

    /**
     * Configura o Cross-Origin Resource Sharing (CORS) para permitir requisições de diferentes origens
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // Aplica a configuração para todos os endpoints
                .allowedOrigins("http://localhost:3000")  // URL do frontend React
                .allowedMethods(  // Métodos HTTP permitidos
                        "GET",    // Buscar recursos
                        "POST",   // Criar novos recursos
                        "PUT",    // Atualizar recursos existentes
                        "DELETE", // Excluir recursos
                        "OPTIONS" // Preflight requests
                )
                .allowedHeaders("*")  // Permite todos os cabeçalhos
                .allowCredentials(true);  // Permite envio de credenciais
    }
}