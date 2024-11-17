import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import '../styles/Cartao.css';
import axios from 'axios';

const CartaoPage = () => {
    const [cartoes, setCartoes] = useState([]); // Estado para armazenar os cartões
    const [error, setError] = useState(null); // Estado para erros

    const fetchCartoes = () => {
        axios.get('http://localhost:8080/api/cartoes')
            .then(response => {
                // Ajusta os dados recebidos para o formato esperado
                const cartoesFormatados = response.data.map(cartao => ({
                    idCartao: cartao.idCartao || cartao.id_cartao,
                    numero: cartao.numero,
                    nomeTitular: cartao.nomeTitular || cartao.nome_titular,
                    validade: cartao.validade,
                    cvv: cartao.cvv,
                    bandeira: cartao.bandeira,
                }));
                setCartoes(cartoesFormatados);
                setError(null);
            })
            .catch(err => {
                setError('Erro ao buscar cartões');
                console.error('Erro ao buscar cartões:', err);
            });
    };

    return (
        <div className="entity-page">
            <h1>Gerenciamento de Cartões</h1>
            <div className="action-buttons">
                <button onClick={fetchCartoes}>Listar Cartões</button>
                <Link to="/cartoes/new" className="action-button">
                    Adicionar Novo Cartão
                </Link>
            </div>

            {error && <p className="error-message">{error}</p>}

            <div className="cartao-list">
                {cartoes.length > 0 && (
                    <table className="cartao-table">
                        <thead>
                        <tr>
                            <th>ID do Cartão</th>
                            <th>Número</th>
                            <th>Nome do Titular</th>
                            <th>Validade</th>
                            <th>CVV</th>
                            <th>Bandeira</th>
                        </tr>
                        </thead>
                        <tbody>
                        {cartoes.map(cartao => (
                            <tr key={cartao.idCartao}>
                                <td>{cartao.idCartao}</td>
                                <td>{cartao.numero}</td>
                                <td>{cartao.nomeTitular}</td>
                                <td>{new Date(cartao.validade).toLocaleDateString()}</td>
                                <td>{cartao.cvv}</td>
                                <td>{cartao.bandeira}</td>
                            </tr>
                        ))}
                        </tbody>
                    </table>
                )}
            </div>
        </div>
    );
};

export default CartaoPage;
