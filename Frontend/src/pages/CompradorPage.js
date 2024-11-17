import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import '../styles/Comprador.css';
import axios from 'axios';

const CompradorPage = () => {
    const [compradores, setCompradores] = useState([]); // Estado para armazenar os compradores
    const [error, setError] = useState(null); // Estado para erros

    const fetchCompradores = () => {
        axios.get('http://localhost:8080/api/compradores')
            .then(response => {
                // Ajusta os dados recebidos para o formato esperado
                const compradoresFormatados = response.data.map(comprador => ({
                    CPF: comprador.CPF || comprador.cpf,
                    idEndereco: comprador.idEndereco || comprador.id_endereco,
                    idCartao: comprador.idCartao || comprador.id_cartao,
                }));
                setCompradores(compradoresFormatados);
                setError(null);
            })
            .catch(err => {
                setError('Erro ao buscar compradores');
                console.error('Erro ao buscar compradores:', err);
            });
    };

    return (
        <div className="entity-page">
            <h1>Gerenciamento de Compradores</h1>
            <div className="action-buttons">
                <button onClick={fetchCompradores}>Listar Compradores</button>
                <Link to="/compradores/new" className="action-button">
                    Adicionar Novo Comprador
                </Link>
            </div>

            {error && <p className="error-message">{error}</p>}

            <div className="comprador-list">
                {compradores.length > 0 && (
                    <table className="comprador-table">
                        <thead>
                        <tr>
                            <th>CPF</th>
                            <th>ID do Endereço</th>
                            <th>ID do Cartão</th>
                        </tr>
                        </thead>
                        <tbody>
                        {compradores.map(comprador => (
                            <tr key={comprador.CPF}>
                                <td>{comprador.CPF}</td>
                                <td>{comprador.idEndereco}</td>
                                <td>{comprador.idCartao}</td>
                            </tr>
                        ))}
                        </tbody>
                    </table>
                )}
            </div>
        </div>
    );
};

export default CompradorPage;
