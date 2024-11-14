import axios from 'axios';

const BASE_URL = '/api/cartoes';

const CartaoService = {
    getCartoes: () => axios.get(BASE_URL),
    getCartaoById: (id) => axios.get(`${BASE_URL}/${id}`),
    createCartao: (cartao) => axios.post(BASE_URL, cartao),
    updateCartao: (id, cartao) => axios.put(`${BASE_URL}/${id}`, cartao),
    deleteCartao: (id) => axios.delete(`${BASE_URL}/${id}`),
    getCartoesByBandeira: (bandeira) => axios.get(`${BASE_URL}/bandeira/${bandeira}`),
    getCartaoByNumero: (numero) => axios.get(`${BASE_URL}/numero/${numero}`)
};

export default CartaoService;