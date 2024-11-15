import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import FavoritarProdutoService from '../services/FavoritarProdutoService';
import '../styles/FavoritarProduto.css';

const FavoritarProdutoList = () => {
    const [favoritarProdutos, setFavoritarProdutos] = useState([]);
    const [error, setError] = useState('');

    useEffect(() => {
        fetchFavoritarProdutos();
    }, []);

    const fetchFavoritarProdutos = async () => {
        try {
            const data = await FavoritarProdutoService.fetchFavoritarProdutos();
            setFavoritarProdutos(data);
        } catch (error) {
            setError('Falha ao carregar produtos favoritos');
        }
    };

    const handleDelete = async (idLista) => {
        try {
            await FavoritarProdutoService.deleteFavoritarProduto(idLista);
            setFavoritarProdutos(favoritarProdutos.filter(produto => produto.idLista !== idLista));
        } catch (error) {
            setError('Falha ao deletar produto favorito');
        }
    };

    return (
        <div className="entity-list">
            <h1>Lista de Produtos Favoritos</h1>
            {error && <p className="error-message">{error}</p>}
            <Link to="/favoritar-produtos/new" className="add-button">Adicionar Novo Produto Favorito</Link>
            <table>
                <thead>
                <tr>
                    <th>ID Lista</th>
                    <th>CPF Comprador</th>
                    <th>Código Produto</th>
                    <th>Nome Lista</th>
                    <th>Data Criação</th>
                    <th>Ações</th>
                </tr>
                </thead>
                <tbody>
                {favoritarProdutos.map(produto => (
                    <tr key={produto.idLista}>
                        <td>{produto.idLista}</td>
                        <td>{produto.cpfComprador}</td>
                        <td>{produto.codigoProduto}</td>
                        <td>{produto.nomeLista}</td>
                        <td>{produto.dataCriacao}</td>
                        <td>
                            <Link to={`/favoritar-produtos/${produto.idLista}/edit`} className="edit-button">Editar</Link>
                            <button onClick={() => handleDelete(produto.idLista)} className="delete-button">Excluir</button>
                        </td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
};

export default FavoritarProdutoList;