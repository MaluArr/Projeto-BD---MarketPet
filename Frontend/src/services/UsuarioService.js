import axios from 'axios';

const UsuarioService = {
    fetchUsuarios: async () => {
        try {
            const response = await axios.get('/api/usuarios');
            return response.data;
        } catch (error) {
            console.error('Erro ao buscar usuários:', error);
            throw new Error('Erro ao buscar usuários');
        }
    },

    getUsuarioByCpf: async (cpf) => {
        try {
            const response = await axios.get(`/api/usuarios/${cpf}`);
            return response.data;
        } catch (error) {
            console.error('Erro ao buscar usuário:', error);
            throw new Error('Erro ao buscar usuário');
        }
    },

    createUsuario: async (usuarioData) => {
        try {
            const response = await axios.post('/api/usuarios', usuarioData);
            return response.data;
        } catch (error) {
            console.error('Erro ao criar usuário:', error);
            throw new Error('Erro ao criar usuário');
        }
    },

    updateUsuario: async (cpf, usuarioData) => {
        try {
            const response = await axios.put(`/api/usuarios/${cpf}`, usuarioData);
            return response.data;
        } catch (error) {
            console.error('Erro ao atualizar usuário:', error);
            throw new Error('Erro ao atualizar usuário');
        }
    },

    deleteUsuario: async (cpf) => {
        try {
            await axios.delete(`/api/usuarios/${cpf}`);
        } catch (error) {
            console.error('Erro ao deletar usuário:', error);
            throw new Error('Erro ao deletar usuário');
        }
    }
};

export default UsuarioService;