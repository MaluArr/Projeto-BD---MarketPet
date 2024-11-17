import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import '../styles/Carrinho.css';
import axios from 'axios';

const CarrinhoPage = () => {
    const [carrinhos, setCarrinhos] = useState([]); // Estado para armazenar os carrinhos
    const [error, setError] = useState(null); // Estado para erros

    const fetchCarrinhos = () => {
        axios.get('http://localhost:8080/api/carrinhos')
            .then(response => {
                // Ajusta os dados recebidos para o formato esperado
                const carrinhosFormatados = response.data.map(carrinho => ({
                    idCarrinho: carrinho.idCarrinho || carrinho.id_carrinho,
                    valorTotal: carrinho.valorTotal || carrinho.valor_total,
                    cpfComprador: carrinho.cpfComprador || carrinho.cpf_comprador,
                }));
                setCarrinhos(carrinhosFormatados);
                setError(null);
            })
            .catch(err => {
                setError('Erro ao buscar carrinhos');
                console.error('Erro ao buscar carrinhos:', err);
            });
    };

    return (
        <div className="entity-page">
            <h1>Gerenciamento de Carrinhos</h1>
            <div className="action-buttons">
                <button onClick={fetchCarrinhos}>Listar Carrinhos</button>
                <Link to="/carrinhos/new" className="action-button">
                    Adicionar Novo Carrinho
                </Link>
            </div>

            {error && <p className="error-message">{error}</p>}

            <div className="carrinho-list">
                {carrinhos.length > 0 && (
                    <table className="carrinho-table">
                        <thead>
                        <tr>
                            <th>ID do Carrinho</th>
                            <th>Valor Total</th>
                            <th>CPF do Comprador</th>
                        </tr>
                        </thead>
                        <tbody>
                        {carrinhos.map(carrinho => (
                            <tr key={carrinho.idCarrinho}>
                                <td>{carrinho.idCarrinho}</td>
                                <td>R$ {carrinho.valorTotal.toFixed(2)}</td>
                                <td>{carrinho.cpfComprador}</td>
                            </tr>
                        ))}
                        </tbody>
                    </table>
                )}
            </div>
        </div>
    );
};

export default CarrinhoPage;
