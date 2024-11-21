import axios from 'axios';

const ProdutoService = {
    fetchProdutos: async () => {
        try {
            const response = await axios.get('http://localhost:8080/api/produtos');
            return response.data;
        } catch (error) {
            console.error('Erro ao buscar produtos:', error);
            throw new Error('Erro ao buscar produtos');
        }
    },

    getProdutoByCodigo: async (codigoProduto) => {
        try {
            const response = await axios.get(`http://localhost:8080/api/produtos/${codigoProduto}`);
            return response.data;
        } catch (error) {
            console.error('Erro ao buscar produto:', error);
            throw new Error('Erro ao buscar produto');
        }
    },

    createProduto: async (produtoData) => {
        try {
            const response = await axios.post('http://localhost:8080/api/produtos', produtoData);
            return response.data;
        } catch (error) {
            console.error('Erro ao criar produto:', error);
            throw new Error('Erro ao criar produto');
        }
    },

    updateProduto: async (codigoProduto, produtoData) => {
        try {
            const response = await axios.put(`http://localhost:8080/api/produtos/${codigoProduto}`, produtoData);
            return response.data;
        } catch (error) {
            console.error('Erro ao atualizar produto:', error);
            throw new Error('Erro ao atualizar produto');
        }
    },

    deleteProduto: async (codigoProduto) => {
        try {
            await axios.delete(`http://localhost:8080/api/produtos/${codigoProduto}`);
        } catch (error) {
            console.error('Erro ao deletar produto:', error);
            throw new Error('Erro ao deletar produto');
        }
    }
};

export default ProdutoService;
