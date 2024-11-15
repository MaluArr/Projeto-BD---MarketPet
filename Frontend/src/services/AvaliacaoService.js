import axios from 'axios';

const AvaliacaoService = {
    fetchAvaliacoes: async () => {
        try {
            const response = await axios.get('/api/avaliacoes');
            return response.data;
        } catch (error) {
            console.error('Erro ao buscar avaliações:', error);
            throw new Error('Erro ao buscar avaliações');
        }
    },

    getAvaliacaoById: async (id) => {
        try {
            const response = await axios.get(`/api/avaliacoes/${id}`);
            return response.data;
        } catch (error) {
            console.error('Erro ao buscar avaliação:', error);
            throw new Error('Erro ao buscar avaliação');
        }
    },

    createAvaliacao: async (avaliacaoData) => {
        try {
            const response = await axios.post('/api/avaliacoes', avaliacaoData);
            return response.data;
        } catch (error) {
            console.error('Erro ao criar avaliação:', error);
            throw new Error('Erro ao criar avaliação');
        }
    },

    updateAvaliacao: async (id, avaliacaoData) => {
        try {
            const response = await axios.put(`/api/avaliacoes/${id}`, avaliacaoData);
            return response.data;
        } catch (error) {
            console.error('Erro ao atualizar avaliação:', error);
            throw new Error('Erro ao atualizar avaliação');
        }
    },

    deleteAvaliacao: async (id) => {
        try {
            await axios.delete(`/api/avaliacoes/${id}`);
        } catch (error) {
            console.error('Erro ao deletar avaliação:', error);
            throw new Error('Erro ao deletar avaliação');
        }
    }
};

export default AvaliacaoService;