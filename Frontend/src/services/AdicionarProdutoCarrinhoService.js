import axios from 'axios';

const AdicionarProdutoCarrinhoService = {
    fetchAdicionarProdutoCarrinho: async () => {
        try {
            const response = await axios.get('/api/adicionar-produto-carrinho');
            return response.data;
        } catch (error) {
            console.error('Erro ao buscar produtos no carrinho:', error);
            throw new Error('Erro ao buscar produtos no carrinho');
        }
    },

    getAdicionarProdutoCarrinhoById: async (id) => {
        try {
            const response = await axios.get(`/api/adicionar-produto-carrinho/${id}`);
            return response.data;
        } catch (error) {
            console.error('Erro ao buscar produto no carrinho:', error);
            throw new Error('Erro ao buscar produto no carrinho');
        }
    },

    createAdicionarProdutoCarrinho: async (produtoCarrinhoData) => {
        try {
            const response = await axios.post('/api/adicionar-produto-carrinho', produtoCarrinhoData);
            return response.data;
        } catch (error) {
            console.error('Erro ao adicionar produto ao carrinho:', error);
            throw new Error('Erro ao adicionar produto ao carrinho');
        }
    },

    updateAdicionarProdutoCarrinho: async (id, produtoCarrinhoData) => {
        try {
            const response = await axios.put(`/api/adicionar-produto-carrinho/${id}`, produtoCarrinhoData);
            return response.data;
        } catch (error) {
            console.error('Erro ao atualizar produto no carrinho:', error);
            throw new Error('Erro ao atualizar produto no carrinho');
        }
    },

    deleteAdicionarProdutoCarrinho: async (id) => {
        try {
            await axios.delete(`/api/adicionar-produto-carrinho/${id}`);
        } catch (error) {
            console.error('Erro ao deletar produto do carrinho:', error);
            throw new Error('Erro ao deletar produto do carrinho');
        }
    }
};

export default AdicionarProdutoCarrinhoService;