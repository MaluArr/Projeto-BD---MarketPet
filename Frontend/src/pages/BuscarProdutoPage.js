import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import '../styles/BuscarProduto.css';
import axios from 'axios';

const BuscarProdutoPage = () => {
    const [buscas, setBuscas] = useState([]); // Estado para armazenar as buscas de produto
    const [error, setError] = useState(null); // Estado para erros

    const fetchBuscas = () => {
        axios.get('http://localhost:8080/api/buscas-produto')
            .then(response => {
                // Ajusta os dados recebidos para o formato esperado
                const buscasFormatadas = response.data.map(busca => ({
                    idBusca: busca.idBusca || busca.id_busca,
                    cpfComprador: busca.cpfComprador || busca.cpf_comprador,
                    codigoProduto: busca.codigoProduto || busca.codigo_produto,
                    preco: busca.preco,
                    regiao: busca.regiao,
                    categoria: busca.categoria,
                    cor: busca.cor,
                    tamanho: busca.tamanho,
                    avaliacao: busca.avaliacao,
                    descricaoBusca: busca.descricaoBusca || busca.descricao_busca,
                }));
                setBuscas(buscasFormatadas);
                setError(null);
            })
            .catch(err => {
                setError('Erro ao buscar produtos');
                console.error('Erro ao buscar produtos:', err);
            });
    };

    return (
        <div className="entity-page">
            <h1>Gerenciamento de Buscas de Produto</h1>
            <div className="action-buttons">
                <button onClick={fetchBuscas}>Listar Buscas de Produto</button>
                <Link to="/buscar-produto/new" className="action-button">
                    Adicionar Nova Busca de Produto
                </Link>
            </div>

            {error && <p className="error-message">{error}</p>}

            <div className="buscar-produto-list">
                {buscas.length > 0 && (
                    <table className="buscar-produto-table">
                        <thead>
                        <tr>
                            <th>ID da Busca</th>
                            <th>CPF do Comprador</th>
                            <th>Código do Produto</th>
                            <th>Preço</th>
                            <th>Região</th>
                            <th>Categoria</th>
                            <th>Cor</th>
                            <th>Tamanho</th>
                            <th>Avaliação</th>
                            <th>Descrição da Busca</th>
                        </tr>
                        </thead>
                        <tbody>
                        {buscas.map(busca => (
                            <tr key={busca.idBusca}>
                                <td>{busca.idBusca}</td>
                                <td>{busca.cpfComprador}</td>
                                <td>{busca.codigoProduto}</td>
                                <td>R$ {busca.preco.toFixed(2)}</td>
                                <td>{busca.regiao}</td>
                                <td>{busca.categoria}</td>
                                <td>{busca.cor}</td>
                                <td>{busca.tamanho}</td>
                                <td>{busca.avaliacao}</td>
                                <td>{busca.descricaoBusca}</td>
                            </tr>
                        ))}
                        </tbody>
                    </table>
                )}
            </div>
        </div>
    );
};

export default BuscarProdutoPage;
