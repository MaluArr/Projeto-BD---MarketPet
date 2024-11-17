import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import '../styles/Produto.css';
import axios from 'axios';

const ProdutoPage = () => {
    const [produtos, setProdutos] = useState([]); // Estado para armazenar os produtos
    const [error, setError] = useState(null); // Estado para erros

    const fetchProdutos = () => {
        axios.get('http://localhost:8080/api/produtos')
            .then(response => {
                // Ajusta os dados recebidos para o formato esperado
                const produtosFormatados = response.data.map(produto => ({
                    codigo_produto: produto.codigo_produto,
                    descricao: produto.descricao,
                    categoria: produto.categoria,
                    preco: parseFloat(produto.preco || 0),
                    dimensoes: produto.dimensoes || 'Dimensões não fornecidas',
                    foto1: produto.foto1,
                    foto2: produto.foto2,
                    foto3: produto.foto3,
                    foto4: produto.foto4,
                    foto5: produto.foto5,
                    foto6: produto.foto6,
                    desconto: parseFloat(produto.desconto || 0),
                    comissao_mkp: parseFloat(produto.comissao_mkp || 0),
                    nota: parseFloat(produto.nota || 0),
                }));
                setProdutos(produtosFormatados);
                setError(null);
            })
            .catch(err => {
                setError('Erro ao buscar produtos');
                console.error('Erro ao buscar produtos:', err);
            });
    };

    return (
        <div className="entity-page">
            <h1>Gerenciamento de Produtos</h1>
            <div className="action-buttons">
                <button onClick={fetchProdutos}>Listar Produtos</button>
                <Link to="/produtos/new" className="action-button">
                    Adicionar Novo Produto
                </Link>
            </div>

            {error && <p className="error-message">{error}</p>}

            <div className="produto-list">
                {produtos.length > 0 && (
                    <table className="produto-table">
                        <thead>
                        <tr>
                            <th>Código</th>
                            <th>Descrição</th>
                            <th>Categoria</th>
                            <th>Preço</th>
                            <th>Dimensões</th>
                            <th>Fotos</th>
                            <th>Desconto</th>
                            <th>Comissão</th>
                            <th>Nota</th>
                        </tr>
                        </thead>
                        <tbody>
                        {produtos.map(produto => (
                            <tr key={produto.codigo_produto}>
                                <td>{produto.codigo_produto}</td>
                                <td>{produto.descricao}</td>
                                <td>{produto.categoria}</td>
                                <td>R$ {produto.preco.toFixed(2)}</td>
                                <td>{produto.dimensoes}</td>
                                <td>
                                    <div className="produto-fotos">
                                        {[produto.foto1, produto.foto2, produto.foto3, produto.foto4, produto.foto5, produto.foto6]
                                            .filter(foto => foto) // Exibe apenas fotos válidas
                                            .map((foto, index) => (
                                                <img
                                                    key={index}
                                                    src={foto}
                                                    alt={`Foto ${index + 1}`}
                                                    className="produto-foto"
                                                />
                                            ))}
                                    </div>
                                </td>
                                <td>{produto.desconto.toFixed(2)}%</td>
                                <td>{produto.comissao_mkp.toFixed(2)}%</td>
                                <td>{produto.nota.toFixed(1)}</td>
                            </tr>
                        ))}
                        </tbody>
                    </table>
                )}
            </div>
        </div>
    );
};

export default ProdutoPage;
