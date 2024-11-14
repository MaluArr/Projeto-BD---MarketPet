import axios from 'axios';

const CompradorService = {
    createComprador: async (compradorData) => {
        try {
            const response = await axios.post('/api/compradores', compradorData);
            return response.data;
        } catch (error) {
            console.error('Erro ao criar comprador:', error);
            throw new Error('Erro ao criar comprador');
        }
    },

    updateComprador: async (cpf, compradorData) => {
        try {
            const response = await axios.put(`/api/compradores/${cpf}`, compradorData);
            return response.data;
        } catch (error) {
            console.error('Erro ao atualizar comprador:', error);
            throw new Error('Erro ao atualizar comprador');
        }
    }
};

export default CompradorService;