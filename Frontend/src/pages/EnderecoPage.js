import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import '../styles/Endereco.css';
import axios from 'axios';

const EnderecoPage = () => {
    const [enderecos, setEnderecos] = useState([]); // Estado para armazenar os endereços
    const [error, setError] = useState(null); // Estado para erros

    const fetchEnderecos = () => {
        axios.get('http://localhost:8080/api/enderecos')
            .then(response => {
                // Ajusta os dados recebidos para o formato esperado
                const enderecosFormatados = response.data.map(endereco => ({
                    idEndereco: endereco.idEndereco || endereco.id_endereco,
                    cep: endereco.cep,
                    rua: endereco.rua,
                    numero: endereco.numero,
                    bairro: endereco.bairro,
                    cidade: endereco.cidade,
                    estado: endereco.estado,
                    complemento: endereco.complemento,
                }));
                setEnderecos(enderecosFormatados);
                setError(null);
            })
            .catch(err => {
                setError('Erro ao buscar endereços');
                console.error('Erro ao buscar endereços:', err);
            });
    };

    return (
        <div className="entity-page">
            <h1>Gerenciamento de Endereços</h1>
            <div className="action-buttons">
                <button onClick={fetchEnderecos}>Listar Endereços</button>
                <Link to="/enderecos/new" className="action-button">
                    Adicionar Novo Endereço
                </Link>
            </div>

            {error && <p className="error-message">{error}</p>}

            <div className="endereco-list">
                {enderecos.length > 0 && (
                    <table className="endereco-table">
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>CEP</th>
                            <th>Rua</th>
                            <th>Número</th>
                            <th>Bairro</th>
                            <th>Cidade</th>
                            <th>Estado</th>
                            <th>Complemento</th>
                        </tr>
                        </thead>
                        <tbody>
                        {enderecos.map(endereco => (
                            <tr key={endereco.idEndereco}>
                                <td>{endereco.idEndereco}</td>
                                <td>{endereco.cep}</td>
                                <td>{endereco.rua}</td>
                                <td>{endereco.numero}</td>
                                <td>{endereco.bairro}</td>
                                <td>{endereco.cidade}</td>
                                <td>{endereco.estado}</td>
                                <td>{endereco.complemento || "N/A"}</td>
                            </tr>
                        ))}
                        </tbody>
                    </table>
                )}
            </div>
        </div>
    );
};

export default EnderecoPage;
