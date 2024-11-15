import axios from 'axios';

const AtendenteService = {
    fetchAtendentes: async () => {
        try {
            const response = await axios.get('/api/atendentes');
            return response.data;
        } catch (error) {
            console.error('Erro ao buscar atendentes:', error);
            throw new Error('Erro ao buscar atendentes');
        }
    },

    getAtendenteById: async (idAtendente) => {
        try {
            const response = await axios.get(`/api/atendentes/${idAtendente}`);
            return response.data;
        } catch (error) {
            console.error('Erro ao buscar atendente:', error);
            throw new Error('Erro ao buscar atendente');
        }
    },

    createAtendente: async (atendenteData) => {
        try {
            const response = await axios.post('/api/atendentes', atendenteData);
            return response.data;
        } catch (error) {
            console.error('Erro ao criar atendente:', error);
            throw new Error('Erro ao criar atendente');
        }
    },

    updateAtendente: async (idAtendente, atendenteData) => {
        try {
            const response = await axios.put(`/api/atendentes/${idAtendente}`, atendenteData);
            return response.data;
        } catch (error) {
            console.error('Erro ao atualizar atendente:', error);
            throw new Error('Erro ao atualizar atendente');
        }
    },

    deleteAtendente: async (idAtendente) => {
        try {
            await axios.delete(`/api/atendentes/${idAtendente}`);
        } catch (error) {
            console.error('Erro ao deletar atendente:', error);
            throw new Error('Erro ao deletar atendente');
        }
    }
};

export default AtendenteService;