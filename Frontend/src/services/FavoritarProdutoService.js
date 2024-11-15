import axios from 'axios';

const FavoritarProdutoService = {
    fetchFavoritarProdutos: async () => {
        try {
            const response = await axios.get('/api/favoritar-produtos');
            return response.data;
        } catch (error) {
            console.error('Erro ao buscar produtos favoritos:', error);
            throw new Error('Erro ao buscar produtos favoritos');
        }
    },

    getFavoritarProdutoById: async (idLista) => {
        try {
            const response = await axios.get(`/api/favoritar-produtos/${idLista}`);
            return response.data;
        } catch (error) {
            console.error('Erro ao buscar produto favorito:', error);
            throw new Error('Erro ao buscar produto favorito');
        }
    },

    createFavoritarProduto: async (favoritarProdutoData) => {
        try {
            const response = await axios.post('/api/favoritar-produtos', favoritarProdutoData);
            return response.data;
        } catch (error) {
            console.error('Erro ao criar produto favorito:', error);
            throw new Error('Erro ao criar produto favorito');
        }
    },

    updateFavoritarProduto: async (idLista, favoritarProdutoData) => {
        try {
            const response = await axios.put(`/api/favoritar-produtos/${idLista}`, favoritarProdutoData);
            return response.data;
        } catch (error) {
            console.error('Erro ao atualizar produto favorito:', error);
            throw new Error('Erro ao atualizar produto favorito');
        }
    },

    deleteFavoritarProduto: async (idLista) => {
        try {
            await axios.delete(`/api/favoritar-produtos/${idLista}`);
        } catch (error) {
            console.error('Erro ao deletar produto favorito:', error);
            throw new Error('Erro ao deletar produto favorito');
        }
    }
};

export default FavoritarProdutoService;