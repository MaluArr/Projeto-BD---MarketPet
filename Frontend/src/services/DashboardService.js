import axios from "axios";


const API_URL = "http://localhost:8080/api/dashboard";


export const buscarDadosDashboard = async () => {
    try {
        const [
            vendasDiarias,
            topProdutos,
            topVendedores,
            distribuicaoProdutos,
            evolucaoAvaliacoes,
            desempenhoVendedores,
            vendasXAvaliacao,
        ] = await Promise.all([
            axios.get(`${API_URL}/daily-sales`),
            axios.get(`${API_URL}/top-products`),
            axios.get(`${API_URL}/top-sellers`),
            axios.get(`${API_URL}/product-distribution`),
            axios.get(`${API_URL}/average-rating-evolution`),
            axios.get(`${API_URL}/seller-performance-comparison`),
            axios.get(`${API_URL}/sales-vs-ratings`),
        ]);


        return {
            vendasDiarias: vendasDiarias.data,
            topProdutos: topProdutos.data,
            topVendedores: topVendedores.data,
            distribuicaoProdutos: distribuicaoProdutos.data,
            evolucaoAvaliacoes: evolucaoAvaliacoes.data,
            desempenhoVendedores: desempenhoVendedores.data,
            vendasXAvaliacao: vendasXAvaliacao.data,
        };
    } catch (error) {
        console.error("Erro ao buscar dados do dashboard:", error);
        throw error;
    }
};
