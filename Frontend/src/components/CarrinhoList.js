import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import CarrinhoService from '../services/CarrinhoService';
import '../styles/Carrinho.css';

const CarrinhoList = () => {
    const [carrinhos, setCarrinhos] = useState([]);
    const [error, setError] = useState('');

    useEffect(() => {
        fetchCarrinhos();
    }, []);

    const fetchCarrinhos = async () => {
        try {
            const data = await CarrinhoService.fetchCarrinhos();
            setCarrinhos(data);
        } catch (error) {
            setError('Falha ao carregar carrinhos');
        }
    };

    const handleDelete = async (idCarrinho) => {
        try {
            await CarrinhoService.deleteCarrinho(idCarrinho);
            setCarrinhos(carrinhos.filter(carrinho => carrinho.idCarrinho !== idCarrinho));
        } catch (error) {
            setError('Falha ao deletar carrinho');
        }
    };

    return (
        <div className="entity-list">
            <h1>Lista de Carrinhos</h1>
            {error && <p className="error-message">{error}</p>}
            <Link to="/carrinhos/new" className="add-button">Adicionar Novo Carrinho</Link>
            <table>
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Valor Total</th>
                    <th>CPF Comprador</th>
                    <th>Ações</th>
                </tr>
                </thead>
                <tbody>
                {carrinhos.map(carrinho => (
                    <tr key={carrinho.idCarrinho}>
                        <td>{carrinho.idCarrinho}</td>
                        <td>{carrinho.valorTotal}</td>
                        <td>{carrinho.cpfComprador}</td>
                        <td>
                            <Link to={`/carrinhos/${carrinho.idCarrinho}/edit`} className="edit-button">Editar</Link>
                            <button onClick={() => handleDelete(carrinho.idCarrinho)} className="delete-button">Excluir</button>
                        </td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
};

export default CarrinhoList;