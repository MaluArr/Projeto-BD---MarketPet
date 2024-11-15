import axios from 'axios';

const VendaService = {
    fetchVendas: async () => {
        try {
            const response = await axios.get('/api/vendas');
            return response.data;
        } catch (error) {
            console.error('Erro ao buscar vendas:', error);
            throw new Error('Erro ao buscar vendas');
        }
    },

    getVendaById: async (id) => {
        try {
            const response = await axios.get(`/api/vendas/${id}`);
            return response.data;
        } catch (error) {
            console.error('Erro ao buscar venda:', error);
            throw new Error('Erro ao buscar venda');
        }
    },

    createVenda: async (vendaData) => {
        try {
            const response = await axios.post('/api/vendas', vendaData);
            return response.data;
        } catch (error) {
            console.error('Erro ao criar venda:', error);
            throw new Error('Erro ao criar venda');
        }
    },

    updateVenda: async (id, vendaData) => {
        try {
            const response = await axios.put(`/api/vendas/${id}`, vendaData);
            return response.data;
        } catch (error) {
            console.error('Erro ao atualizar venda:', error);
            throw new Error('Erro ao atualizar venda');
        }
    },

    deleteVenda: async (id) => {
        try {
            await axios.delete(`/api/vendas/${id}`);
        } catch (error) {
            console.error('Erro ao deletar venda:', error);
            throw new Error('Erro ao deletar venda');
        }
    }
};

export default VendaService;