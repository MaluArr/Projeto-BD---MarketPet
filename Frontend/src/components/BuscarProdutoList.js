import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import BuscarProdutoService from '../services/BuscarProdutoService';
import '../styles/BuscarProduto.css';

const BuscarProdutoList = () => {
    const [buscasProduto, setBuscasProduto] = useState([]);
    const [error, setError] = useState('');

    useEffect(() => {
        fetchBuscasProduto();
    }, []);

    const fetchBuscasProduto = async () => {
        try {
            const data = await BuscarProdutoService.fetchBuscarProdutos();
            setBuscasProduto(data);
        } catch (error) {
            setError('Falha ao carregar buscas de produto');
        }
    };

    const handleDelete = async (idBusca) => {
        try {
            await BuscarProdutoService.deleteBuscarProduto(idBusca);
            setBuscasProduto(buscasProduto.filter(busca => busca.idBusca !== idBusca));
        } catch (error) {
            setError('Falha ao deletar busca de produto');
        }
    };

    return (
        <div className="entity-list">
            <h1>Lista de Buscas de Produto</h1>
            {error && <p className="error-message">{error}</p>}
            <Link to="/buscar-produto/new" className="add-button">Adicionar Nova Busca de Produto</Link>
            <table>
                {/* Cabeçalho e corpo da tabela (mesmos de antes) */}
            </table>
        </div>
    );
};

export default BuscarProdutoList;