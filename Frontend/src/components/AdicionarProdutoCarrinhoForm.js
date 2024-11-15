import React, { useState, useEffect } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import AdicionarProdutoCarrinhoService from '../services/AdicionarProdutoCarrinhoService';
import '../styles/AdicionarProdutoCarrinho.css';

const AdicionarProdutoCarrinhoForm = () => {
    const [formData, setFormData] = useState({
        comprador: { cpf: '' },
        produto: { codigoProduto: '' },
        carrinho: { idCarrinho: '' },
        quantidade: ''
    });
    const [error, setError] = useState('');
    const navigate = useNavigate();
    const { id } = useParams();

    useEffect(() => {
        if (id) {
            fetchAdicionarProdutoCarrinho(id);
        }
    }, [id]);

    const fetchAdicionarProdutoCarrinho = async (id) => {
        try {
            const data = await AdicionarProdutoCarrinhoService.getAdicionarProdutoCarrinhoById(id);
            setFormData(data);
        } catch (error) {
            setError('Falha ao carregar dados do produto no carrinho');
        }
    };

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData(prevState => ({
            ...prevState,
            [name]: name === 'comprador' || name === 'produto' || name === 'carrinho'
                ? { ...prevState[name], [e.target.getAttribute('data-field')]: value }
                : value
        }));
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        setError('');
        try {
            if (id) {
                await AdicionarProdutoCarrinhoService.updateAdicionarProdutoCarrinho(id, formData);
            } else {
                await AdicionarProdutoCarrinhoService.createAdicionarProdutoCarrinho(formData);
            }
            navigate('/adicionar-produto-carrinho');
        } catch (error) {
            setError('Falha ao salvar produto no carrinho');
        }
    };

    return (
        <div className="entity-form">
            <h2>{id ? 'Editar Produto no Carrinho' : 'Adicionar Produto ao Carrinho'}</h2>
            {error && <p className="error-message">{error}</p>}
            <form onSubmit={handleSubmit}>
                <input
                    type="text"
                    name="comprador"
                    data-field="cpf"
                    value={formData.comprador.cpf}
                    onChange={handleChange}
                    placeholder="CPF do Comprador"
                    required
                />
                <input
                    type="text"
                    name="produto"
                    data-field="codigoProduto"
                    value={formData.produto.codigoProduto}
                    onChange={handleChange}
                    placeholder="Código do Produto"
                    required
                />
                <input
                    type="text"
                    name="carrinho"
                    data-field="idCarrinho"
                    value={formData.carrinho.idCarrinho}
                    onChange={handleChange}
                    placeholder="ID do Carrinho"
                    required
                />
                <input
                    type="number"
                    name="quantidade"
                    value={formData.quantidade}
                    onChange={handleChange}
                    placeholder="Quantidade"
                    required
                />
                <button type="submit">{id ? 'Atualizar' : 'Adicionar'}</button>
                <button type="button" onClick={() => navigate('/adicionar-produto-carrinho')}>Cancelar</button>
            </form>
        </div>
    );
};

export default AdicionarProdutoCarrinhoForm;