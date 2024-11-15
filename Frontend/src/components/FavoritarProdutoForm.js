import React, { useState, useEffect } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import FavoritarProdutoService from '../services/FavoritarProdutoService';
import '../styles/FavoritarProduto.css';

const FavoritarProdutoForm = () => {
    const [formData, setFormData] = useState({
        idLista: '',
        cpfComprador: '',
        codigoProduto: '',
        nomeLista: '',
        dataCriacao: ''
    });
    const [error, setError] = useState('');
    const navigate = useNavigate();
    const { idLista } = useParams();

    useEffect(() => {
        if (idLista) {
            fetchFavoritarProduto(idLista);
        }
    }, [idLista]);

    const fetchFavoritarProduto = async (idLista) => {
        try {
            const data = await FavoritarProdutoService.getFavoritarProdutoById(idLista);
            setFormData(data);
        } catch (error) {
            setError('Falha ao carregar dados do produto favorito');
        }
    };

    const handleChange = (e) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        setError('');
        try {
            if (idLista) {
                await FavoritarProdutoService.updateFavoritarProduto(idLista, formData);
            } else {
                await FavoritarProdutoService.createFavoritarProduto(formData);
            }
            navigate('/favoritar-produtos');
        } catch (error) {
            setError('Falha ao salvar produto favorito');
        }
    };

    return (
        <div className="entity-form">
            <h2>{idLista ? 'Editar Produto Favorito' : 'Novo Produto Favorito'}</h2>
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
                    type="text"
                    name="nomeLista"
                    value={formData.nomeLista}
                    onChange={handleChange}
                    placeholder="Nome da Lista"
                    required
                />
                <input
                    type="date"
                    name="dataCriacao"
                    value={formData.dataCriacao}
                    onChange={handleChange}
                    required
                />
                <button type="submit">{idLista ? 'Atualizar' : 'Criar'}</button>
                <button type="button" onClick={() => navigate('/favoritar-produtos')}>Cancelar</button>
            </form>
        </div>
    );
};

export default FavoritarProdutoForm;