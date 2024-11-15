import React, { useState, useEffect } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import ProdutoService from '../services/ProdutoService';
import '../styles/Produto.css';

const ProdutoForm = () => {
    const [formData, setFormData] = useState({
        codigoProduto: '',
        descricao: '',
        categoria: '',
        preco: '',
        dimensoes: '',
        fotos: [],
        desconto: '',
        comissaoMkp: '',
        nota: ''
    });
    const [error, setError] = useState('');
    const navigate = useNavigate();
    const { codigoProduto } = useParams();

    useEffect(() => {
        if (codigoProduto) {
            fetchProduto(codigoProduto);
        }
    }, [codigoProduto]);

    const fetchProduto = async (codigoProduto) => {
        try {
            const data = await ProdutoService.getProdutoByCodigo(codigoProduto);
            setFormData(data);
        } catch (error) {
            setError('Falha ao carregar dados do produto');
        }
    };

    const handleChange = (e) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        setError('');
        try {
            if (codigoProduto) {
                await ProdutoService.updateProduto(codigoProduto, formData);
            } else {
                await ProdutoService.createProduto(formData);
            }
            navigate('/produtos');
        } catch (error) {
            setError('Falha ao salvar produto');
        }
    };

    return (
        <div className="entity-form">
            <h2>{codigoProduto ? 'Editar Produto' : 'Novo Produto'}</h2>
            {error && <p className="error-message">{error}</p>}
            <form onSubmit={handleSubmit}>
                <input
                    type="text"
                    name="descricao"
                    value={formData.descricao}
                    onChange={handleChange}
                    placeholder="Descrição"
                    required
                />
                <input
                    type="text"
                    name="categoria"
                    value={formData.categoria}
                    onChange={handleChange}
                    placeholder="Categoria"
                    required
                />
                <input
                    type="number"
                    name="preco"
                    value={formData.preco}
                    onChange={handleChange}
                    placeholder="Preço"
                    required
                    step="0.01"
                />
                <input
                    type="text"
                    name="dimensoes"
                    value={formData.dimensoes}
                    onChange={handleChange}
                    placeholder="Dimensões"
                />
                <input
                    type="number"
                    name="desconto"
                    value={formData.desconto}
                    onChange={handleChange}
                    placeholder="Desconto (%)"
                    step="0.01"
                />
                <input
                    type="number"
                    name="comissaoMkp"
                    value={formData.comissaoMkp}
                    onChange={handleChange}
                    placeholder="Comissão Marketplace"
                    step="0.01"
                />
                <input
                    type="number"
                    name="nota"
                    value={formData.nota}
                    onChange={handleChange}
                    placeholder="Nota"
                    step="0.1"
                    min="0"
                    max="5"
                />
                <button type="submit">{codigoProduto ? 'Atualizar' : 'Criar'}</button>
                <button type="button" onClick={() => navigate('/produtos')}>Cancelar</button>
            </form>
        </div>
    );
};

export default ProdutoForm;