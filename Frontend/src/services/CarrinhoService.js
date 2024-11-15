import axios from 'axios';

const CarrinhoService = {
    fetchCarrinhos: async () => {
        try {
            const response = await axios.get('/api/carrinhos');
            return response.data;
        } catch (error) {
            console.error('Erro ao buscar carrinhos:', error);
            throw new Error('Erro ao buscar carrinhos');
        }
    },

    getCarrinhoById: async (idCarrinho) => {
        try {
            const response = await axios.get(`/api/carrinhos/${idCarrinho}`);
            return response.data;
        } catch (error) {
            console.error('Erro ao buscar carrinho:', error);
            throw new Error('Erro ao buscar carrinho');
        }
    },

    createCarrinho: async (carrinhoData) => {
        try {
            const response = await axios.post('/api/carrinhos', carrinhoData);
            return response.data;
        } catch (error) {
            console.error('Erro ao criar carrinho:', error);
            throw new Error('Erro ao criar carrinho');
        }
    },

    updateCarrinho: async (idCarrinho, carrinhoData) => {
        try {
            const response = await axios.put(`/api/carrinhos/${idCarrinho}`, carrinhoData);
            return response.data;
        } catch (error) {
            console.error('Erro ao atualizar carrinho:', error);
            throw new Error('Erro ao atualizar carrinho');
        }
    },

    deleteCarrinho: async (idCarrinho) => {
        try {
            await axios.delete(`/api/carrinhos/${idCarrinho}`);
        } catch (error) {
            console.error('Erro ao deletar carrinho:', error);
            throw new Error('Erro ao deletar carrinho');
        }
    }
};

export default CarrinhoService;