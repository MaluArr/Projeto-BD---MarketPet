import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import '../styles/Venda.css';
import axios from 'axios';

const VendaPage = () => {
    const [vendas, setVendas] = useState([]); // Estado para armazenar as vendas
    const [error, setError] = useState(null); // Estado para erros

    const fetchVendas = () => {
        axios.get('http://localhost:8080/api/vendas')
            .then(response => {
                // Ajusta os dados recebidos para o formato esperado
                const vendasFormatadas = response.data.map(venda => ({
                    idVenda: venda.idVenda || venda.id_venda,
                    cpfComprador: venda.cpfComprador || venda.cpf_comprador,
                    codigoProduto: venda.codigoProduto || venda.codigo_produto,
                    dataVenda: venda.dataVenda || venda.data_venda,
                }));
                setVendas(vendasFormatadas);
                setError(null);
            })
            .catch(err => {
                setError('Erro ao buscar vendas');
                console.error('Erro ao buscar vendas:', err);
            });
    };

    return (
        <div className="entity-page">
            <h1>Gerenciamento de Vendas</h1>
            <div className="action-buttons">
                <button onClick={fetchVendas}>Listar Vendas</button>
                <Link to="/vendas/new" className="action-button">
                    Adicionar Nova Venda
                </Link>
            </div>

            {error && <p className="error-message">{error}</p>}

            <div className="vendas-list">
                {vendas.length > 0 && (
                    <table className="vendas-table">
                        <thead>
                        <tr>
                            <th>ID da Venda</th>
                            <th>CPF do Comprador</th>
                            <th>Código do Produto</th>
                            <th>Data da Venda</th>
                        </tr>
                        </thead>
                        <tbody>
                        {vendas.map(venda => (
                            <tr key={venda.idVenda}>
                                <td>{venda.idVenda}</td>
                                <td>{venda.cpfComprador}</td>
                                <td>{venda.codigoProduto}</td>
                                <td>
                                    {venda.dataVenda
                                        ? new Date(venda.dataVenda).toLocaleDateString()
                                        : "N/A"}
                                </td>
                            </tr>
                        ))}
                        </tbody>
                    </table>
                )}
            </div>
        </div>
    );
};

export default VendaPage;
