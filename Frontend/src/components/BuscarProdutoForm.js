import React, { useState, useEffect } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import BuscarProdutoService from '../services/BuscarProdutoService';
import '../styles/BuscarProduto.css';

const BuscarProdutoForm = () => {
    const [formData, setFormData] = useState({
        idBusca: '',
        cpfComprador: '',
        codigoProduto: '',
        preco: '',
        regiao: '',
        categoria: '',
        cor: '',
        tamanho: '',
        avaliacao: '',
        descricaoBusca: ''
    });
    const [error, setError] = useState('');
    const navigate = useNavigate();
    const { idBusca } = useParams();

    useEffect(() => {
        if (idBusca) {
            fetchBuscarProduto(idBusca);
        }
    }, [idBusca]);

    const fetchBuscarProduto = async (idBusca) => {
        try {
            const data = await BuscarProdutoService.getBuscarProdutoById(idBusca);
            setFormData(data);
        } catch (error) {
            setError('Falha ao carregar dados da busca de produto');
        }
    };

    const handleChange = (e) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        setError('');
        try {
            if (idBusca) {
                await BuscarProdutoService.updateBuscarProduto(idBusca, formData);
            } else {
                await BuscarProdutoService.createBuscarProduto(formData);
            }
            navigate('/buscar-produto');
        } catch (error) {
            setError('Falha ao salvar busca de produto');
        }
    };

    return (
        <div className="entity-form">
            <h2>{idBusca ? 'Editar Busca de Produto' : 'Nova Busca de Produto'}</h2>
            {error && <p className="error-message">{error}</p>}
            <form onSubmit={handleSubmit}>
                <input
                    type="text"
                    name="cpfComprador"
                    value={formData.cpfComprador}
                    onChange={handleChange}
                    placeholder="CPF do Comprador"
                    required
                />
                <input
                    type="text"
                    name="codigoProduto"
                    value={formData.codigoProduto}
                    onChange={handleChange}
                    placeholder="Código do Produto"
                    required
                />
                <input
                    type="number"
                    name="preco"
                    value={formData.preco}
                    onChange={handleChange}
                    placeholder="Preço"
                    step="0.01"
                />
                <input
                    type="text"
                    name="regiao"
                    value={formData.regiao}
                    onChange={handleChange}
                    placeholder="Região"
                />
                <input
                    type="text"
                    name="categoria"
                    value={formData.categoria}
                    onChange={handleChange}
                    placeholder="Categoria"
                />
                <input
                    type="text"
                    name="cor"
                    value={formData.cor}
                    onChange={handleChange}
                    placeholder="Cor"
                />
                <input
                    type="text"
                    name="tamanho"
                    value={formData.tamanho}
                    onChange={handleChange}
                    placeholder="Tamanho"
                />
                <input
                    type="number"
                    name="avaliacao"
                    value={formData.avaliacao}
                    onChange={handleChange}
                    placeholder="Avaliação"
                    step="0.1"
                />
                <textarea
                    name="descricaoBusca"
                    value={formData.descricaoBusca}
                    onChange={handleChange}
                    placeholder="Descrição da Busca"
                />
                <button type="submit">{idBusca ? 'Atualizar' : 'Criar'}</button>
                <button type="button" onClick={() => navigate('/buscar-produto')}>Cancelar</button>
            </form>
        </div>
    );
};

export default BuscarProdutoForm;