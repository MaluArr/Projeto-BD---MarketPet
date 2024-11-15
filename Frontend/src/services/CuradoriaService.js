import axios from 'axios';

const CuradoriaService = {
    fetchCuradorias: async () => {
        try {
            const response = await axios.get('/api/curadorias');
            return response.data;
        } catch (error) {
            console.error('Erro ao buscar curadorias:', error);
            throw new Error('Erro ao buscar curadorias');
        }
    },

    getCuradoriaByCodigo: async (codigoCuradoria) => {
        try {
            const response = await axios.get(`/api/curadorias/${codigoCuradoria}`);
            return response.data;
        } catch (error) {
            console.error('Erro ao buscar curadoria:', error);
            throw new Error('Erro ao buscar curadoria');
        }
    },

    createCuradoria: async (curadoriaData) => {
        try {
            const response = await axios.post('/api/curadorias', curadoriaData);
            return response.data;
        } catch (error) {
            console.error('Erro ao criar curadoria:', error);
            throw new Error('Erro ao criar curadoria');
        }
    },

    updateCuradoria: async (codigoCuradoria, curadoriaData) => {
        try {
            const response = await axios.put(`/api/curadorias/${codigoCuradoria}`, curadoriaData);
            return response.data;
        } catch (error) {
            console.error('Erro ao atualizar curadoria:', error);
            throw new Error('Erro ao atualizar curadoria');
        }
    },

    deleteCuradoria: async (codigoCuradoria) => {
        try {
            await axios.delete(`/api/curadorias/${codigoCuradoria}`);
        } catch (error) {
            console.error('Erro ao deletar curadoria:', error);
            throw new Error('Erro ao deletar curadoria');
        }
    }
};

export default CuradoriaService;