import axios from 'axios';

const BASE_URL = '/api/enderecos';

const EnderecoService = {
    getEnderecos: () => axios.get(BASE_URL),
    getEnderecoById: (id) => axios.get(`${BASE_URL}/${id}`),
    createEndereco: (endereco) => axios.post(BASE_URL, endereco),
    updateEndereco: (id, endereco) => axios.put(`${BASE_URL}/${id}`, endereco),
    deleteEndereco: (id) => axios.delete(`${BASE_URL}/${id}`),
    getEnderecosByEstado: (estado) => axios.get(`${BASE_URL}/estado/${estado}`),
    getEnderecosByCidade: (cidade) => axios.get(`${BASE_URL}/cidade/${cidade}`),
    getEnderecosByCep: (cep) => axios.get(`${BASE_URL}/cep/${cep}`)
};

export default EnderecoService;