import axios from 'axios';

const BuscarProdutoService = {
    fetchBuscarProdutos: async () => {
        try {
            const response = await axios.get('/api/buscar-produto');
            return response.data;
        } catch (error) {
            console.error('Erro ao buscar buscas de produto:', error);
            throw new Error('Erro ao buscar buscas de produto');
        }
    },

    getBuscarProdutoById: async (idBusca) => {
        try {
            const response = await axios.get(`/api/buscar-produto/${idBusca}`);
            return response.data;
        } catch (error) {
            console.error('Erro ao buscar busca de produto:', error);
            throw new Error('Erro ao buscar busca de produto');
        }
    },

    createBuscarProduto: async (buscarProdutoData) => {
        try {
            const response = await axios.post('/api/buscar-produto', buscarProdutoData);
            return response.data;
        } catch (error) {
            console.error('Erro ao criar busca de produto:', error);
            throw new Error('Erro ao criar busca de produto');
        }
    },

    updateBuscarProduto: async (idBusca, buscarProdutoData) => {
        try {
            const response = await axios.put(`/api/buscar-produto/${idBusca}`, buscarProdutoData);
            return response.data;
        } catch (error) {
            console.error('Erro ao atualizar busca de produto:', error);
            throw new Error('Erro ao atualizar busca de produto');
        }
    },

    deleteBuscarProduto: async (idBusca) => {
        try {
            await axios.delete(`/api/buscar-produto/${idBusca}`);
        } catch (error) {
            console.error('Erro ao deletar busca de produto:', error);
            throw new Error('Erro ao deletar busca de produto');
        }
    }
};

export default BuscarProdutoService;