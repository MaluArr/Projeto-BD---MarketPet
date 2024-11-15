import axios from 'axios';

const VendedorService = {
    fetchVendedores: async () => {
        try {
            const response = await axios.get('/api/vendedores');
            return response.data;
        } catch (error) {
            console.error('Erro ao buscar vendedores:', error);
            throw new Error('Erro ao buscar vendedores');
        }
    },

    getVendedorByCpf: async (cpf) => {
        try {
            const response = await axios.get(`/api/vendedores/${cpf}`);
            return response.data;
        } catch (error) {
            console.error('Erro ao buscar vendedor:', error);
            throw new Error('Erro ao buscar vendedor');
        }
    },

    createVendedor: async (vendedorData) => {
        try {
            const response = await axios.post('/api/vendedores', vendedorData);
            return response.data;
        } catch (error) {
            console.error('Erro ao criar vendedor:', error);
            throw new Error('Erro ao criar vendedor');
        }
    },

    updateVendedor: async (cpf, vendedorData) => {
        try {
            const response = await axios.put(`/api/vendedores/${cpf}`, vendedorData);
            return response.data;
        } catch (error) {
            console.error('Erro ao atualizar vendedor:', error);
            throw new Error('Erro ao atualizar vendedor');
        }
    },

    deleteVendedor: async (cpf) => {
        try {
            await axios.delete(`/api/vendedores/${cpf}`);
        } catch (error) {
            console.error('Erro ao deletar vendedor:', error);
            throw new Error('Erro ao deletar vendedor');
        }
    }
};

export default VendedorService;