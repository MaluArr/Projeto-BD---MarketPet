// src/services/CompradorService.js
import axios from 'axios';

const CompradorService = {
    fetchCompradores: async () => {
        try {
            const response = await axios.get('/api/compradores');
            return response.data;
        } catch (error) {
            console.error('Erro ao buscar compradores:', error);
            throw new Error('Erro ao buscar compradores');
        }
    },

    getCompradorByCpf: async (cpf) => {
        try {
            const response = await axios.get(`/api/compradores/${cpf}`);
            return response.data;
        } catch (error) {
            console.error('Erro ao buscar comprador por CPF:', error);
            throw new Error('Erro ao buscar comprador por CPF');
        }
    },

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
    },

    deleteComprador: async (cpf) => {
        try {
            await axios.delete(`/api/compradores/${cpf}`);
        } catch (error) {
            console.error('Erro ao deletar comprador:', error);
            throw new Error('Erro ao deletar comprador');
        }
    }
};

export default CompradorService;