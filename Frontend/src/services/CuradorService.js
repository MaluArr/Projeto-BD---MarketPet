import axios from 'axios';

const CuradorService = {
    fetchCuradores: async () => {
        try {
            const response = await axios.get('/api/curadores');
            return response.data;
        } catch (error) {
            console.error('Erro ao buscar curadores:', error);
            throw new Error('Erro ao buscar curadores');
        }
    },

    getCuradorById: async (idCurador) => {
        try {
            const response = await axios.get(`/api/curadores/${idCurador}`);
            return response.data;
        } catch (error) {
            console.error('Erro ao buscar curador:', error);
            throw new Error('Erro ao buscar curador');
        }
    },

    createCurador: async (curadorData) => {
        try {
            const response = await axios.post('/api/curadores', curadorData);
            return response.data;
        } catch (error) {
            console.error('Erro ao criar curador:', error);
            throw new Error('Erro ao criar curador');
        }
    },

    updateCurador: async (idCurador, curadorData) => {
        try {
            const response = await axios.put(`/api/curadores/${idCurador}`, curadorData);
            return response.data;
        } catch (error) {
            console.error('Erro ao atualizar curador:', error);
            throw new Error('Erro ao atualizar curador');
        }
    },

    deleteCurador: async (idCurador) => {
        try {
            await axios.delete(`/api/curadores/${idCurador}`);
        } catch (error) {
            console.error('Erro ao deletar curador:', error);
            throw new Error('Erro ao deletar curador');
        }
    }
};

export default CuradorService;