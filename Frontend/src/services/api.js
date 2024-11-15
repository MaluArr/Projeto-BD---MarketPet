// src/services/api.js

const API_URL = process.env.REACT_APP_API_URL;

export const fetchUsuarios = async () => {
    try {
        const response = await fetch(`${API_URL}/usuarios`);
        if (!response.ok) {
            throw new Error('Erro ao buscar usuários');
        }
        return await response.json();
    } catch (error) {
        console.error('Erro:', error);
        throw error;
    }
};