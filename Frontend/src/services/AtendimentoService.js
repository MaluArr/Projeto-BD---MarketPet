import axios from 'axios';

const AtendimentoService = {
    fetchAtendimentos: async () => {
        try {
            const response = await axios.get('/api/atendimentos');
            return response.data;
        } catch (error) {
            console.error('Erro ao buscar atendimentos:', error);
            throw new Error('Erro ao buscar atendimentos');
        }
    },

    getAtendimentoById: async (idAtendimento) => {
        try {
            const response = await axios.get(`/api/atendimentos/${idAtendimento}`);
            return response.data;
        } catch (error) {
            console.error('Erro ao buscar atendimento:', error);
            throw new Error('Erro ao buscar atendimento');
        }
    },

    createAtendimento: async (atendimentoData) => {
        try {
            const response = await axios.post('/api/atendimentos', atendimentoData);
            return response.data;
        } catch (error) {
            console.error('Erro ao criar atendimento:', error);
            throw new Error('Erro ao criar atendimento');
        }
    },

    updateAtendimento: async (idAtendimento, atendimentoData) => {
        try {
            const response = await axios.put(`/api/atendimentos/${idAtendimento}`, atendimentoData);
            return response.data;
        } catch (error) {
            console.error('Erro ao atualizar atendimento:', error);
            throw new Error('Erro ao atualizar atendimento');
        }
    },

    deleteAtendimento: async (idAtendimento) => {
        try {
            await axios.delete(`/api/atendimentos/${idAtendimento}`);
        } catch (error) {
            console.error('Erro ao deletar atendimento:', error);
            throw new Error('Erro ao deletar atendimento');
        }
    }
};

export default AtendimentoService;