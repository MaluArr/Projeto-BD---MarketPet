import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import CartaoService from '../services/CartaoService';
import '../styles/Cartao.css';

const CartaoList = () => {
    const [cartoes, setCartoes] = useState([]);
    const [error, setError] = useState('');

    useEffect(() => {
        fetchCartoes();
    }, []);

    const fetchCartoes = async () => {
        try {
            const response = await CartaoService.getCartoes();
            setCartoes(response.data);
        } catch (error) {
            setError('Falha ao carregar cartões');
        }
    };

    const handleDelete = async (idCartao) => {
        try {
            await CartaoService.deleteCartao(idCartao);
            setCartoes(cartoes.filter(cartao => cartao.idCartao !== idCartao));
        } catch (error) {
            setError('Falha ao deletar cartão');
        }
    };

    return (
        <div className="entity-list">
            <h1>Lista de Cartões</h1>
            {error && <p className="error-message">{error}</p>}
            <Link to="/cartoes/new" className="add-button">Adicionar Novo Cartão</Link>
            <table>
                <thead>
                <tr>
                    <th>Número</th>
                    <th>Nome do Titular</th>
                    <th>Validade</th>
                    <th>Bandeira</th>
                    <th>Ações</th>
                </tr>
                </thead>
                <tbody>
                {cartoes.map(cartao => (
                    <tr key={cartao.idCartao}>
                        <td>{cartao.numero.replace(/\d{12}/, '************')}</td>
                        <td>{cartao.nomeTitular}</td>
                        <td>{new Date(cartao.validade).toLocaleDateString()}</td>
                        <td>{cartao.bandeira}</td>
                        <td>
                            <Link to={`/cartoes/${cartao.idCartao}/edit`} className="edit-button">Editar</Link>
                            <button onClick={() => handleDelete(cartao.idCartao)} className="delete-button">Excluir</button>
                        </td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
};

export default CartaoList;