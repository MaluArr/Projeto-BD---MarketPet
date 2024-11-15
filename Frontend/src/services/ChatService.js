import axios from 'axios';

const ChatService = {
    fetchChats: async () => {
        try {
            const response = await axios.get('/api/chats');
            return response.data;
        } catch (error) {
            console.error('Erro ao buscar chats:', error);
            throw new Error('Erro ao buscar chats');
        }
    },

    getChatById: async (idChat) => {
        try {
            const response = await axios.get(`/api/chats/${idChat}`);
            return response.data;
        } catch (error) {
            console.error('Erro ao buscar chat:', error);
            throw new Error('Erro ao buscar chat');
        }
    },

    createChat: async (chatData) => {
        try {
            const response = await axios.post('/api/chats', chatData);
            return response.data;
        } catch (error) {
            console.error('Erro ao criar chat:', error);
            throw new Error('Erro ao criar chat');
        }
    },

    updateChat: async (idChat, chatData) => {
        try {
            const response = await axios.put(`/api/chats/${idChat}`, chatData);
            return response.data;
        } catch (error) {
            console.error('Erro ao atualizar chat:', error);
            throw new Error('Erro ao atualizar chat');
        }
    },

    deleteChat: async (idChat) => {
        try {
            await axios.delete(`/api/chats/${idChat}`);
        } catch (error) {
            console.error('Erro ao deletar chat:', error);
            throw new Error('Erro ao deletar chat');
        }
    }
};

export default ChatService;