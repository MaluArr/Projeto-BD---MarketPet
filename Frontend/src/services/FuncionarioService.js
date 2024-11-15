import axios from 'axios';

const FuncionarioService = {
    fetchFuncionarios: async () => {
        try {
            const response = await axios.get('/api/funcionarios');
            return response.data;
        } catch (error) {
            console.error('Erro ao buscar funcionários:', error);
            throw new Error('Erro ao buscar funcionários');
        }
    },

    getFuncionarioByCpf: async (cpfFuncionario) => {
        try {
            const response = await axios.get(`/api/funcionarios/${cpfFuncionario}`);
            return response.data;
        } catch (error) {
            console.error('Erro ao buscar funcionário:', error);
            throw new Error('Erro ao buscar funcionário');
        }
    },

    createFuncionario: async (funcionarioData) => {
        try {
            const response = await axios.post('/api/funcionarios', funcionarioData);
            return response.data;
        } catch (error) {
            console.error('Erro ao criar funcionário:', error);
            throw new Error('Erro ao criar funcionário');
        }
    },

    updateFuncionario: async (cpfFuncionario, funcionarioData) => {
        try {
            const response = await axios.put(`/api/funcionarios/${cpfFuncionario}`, funcionarioData);
            return response.data;
        } catch (error) {
            console.error('Erro ao atualizar funcionário:', error);
            throw new Error('Erro ao atualizar funcionário');
        }
    },

    deleteFuncionario: async (cpfFuncionario) => {
        try {
            await axios.delete(`/api/funcionarios/${cpfFuncionario}`);
        } catch (error) {
            console.error('Erro ao deletar funcionário:', error);
            throw new Error('Erro ao deletar funcionário');
        }
    }
};

export default FuncionarioService;