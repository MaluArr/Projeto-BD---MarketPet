// src/services/VendedorService.js
import axios from 'axios';

const API_URL = '/api/vendedores';

class VendedorService {
    // Buscar todos os vendedores
    static async getAllVendedores() {
        try {
            const response = await axios.get(API_URL);
            return response.data;
        } catch (error) {
            console.error('Erro ao buscar vendedores:', error);
            throw error;
        }
    }

    // Buscar um vendedor específico por CPF
    static async getVendedorByCpf(cpf) {
        try {
            const response = await axios.get(`${API_URL}/${cpf}`);
            return response.data;
        } catch (error) {
            console.error(`Erro ao buscar vendedor com CPF ${cpf}:`, error);
            throw error;
        }
    }

    // Criar um novo vendedor
    static async createVendedor(vendedorData) {
        try {
            const response = await axios.post(API_URL, vendedorData);
            return response.data;
        } catch (error) {
            console.error('Erro ao criar vendedor:', error);
            throw error;
        }
    }

    // Atualizar um vendedor existente
    static async updateVendedor(cpf, vendedorData) {
        try {
            const response = await axios.put(`${API_URL}/${cpf}`, vendedorData);
            return response.data;
        } catch (error) {
            console.error(`Erro ao atualizar vendedor com CPF ${cpf}:`, error);
            throw error;
        }
    }

    // Excluir um vendedor
    static async deleteVendedor(cpf) {
        try {
            await axios.delete(`${API_URL}/${cpf}`);
        } catch (error) {
            console.error(`Erro ao excluir vendedor com CPF ${cpf}:`, error);
            throw error;
        }
    }

    // Buscar vendedores por avaliação superior
    static async getVendedoresByAvaliacaoSuperior(nota) {
        try {
            const response = await axios.get(`${API_URL}/avaliacao-superior/${nota}`);
            return response.data;
        } catch (error) {
            console.error('Erro ao buscar vendedores por avaliação superior:', error);
            throw error;
        }
    }

    // Buscar vendedores por total de vendas superior
    static async getVendedoresByTotalVendasSuperior(valor) {
        try {
            const response = await axios.get(`${API_URL}/vendas-superior/${valor}`);
            return response.data;
        } catch (error) {
            console.error('Erro ao buscar vendedores por total de vendas superior:', error);
            throw error;
        }
    }
}

export default VendedorService;