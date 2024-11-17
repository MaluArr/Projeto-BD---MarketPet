import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import '../styles/Vendedor.css';
import axios from 'axios';

const VendedorPage = () => {
    const [vendedores, setVendedores] = useState([]); // Estado para armazenar os vendedores
    const [error, setError] = useState(null); // Estado para erros

    const fetchVendedores = () => {
        axios.get('http://localhost:8080/api/vendedores')
            .then(response => {
                console.log('Dados recebidos do backend:', response.data);
                // Ajusta os dados recebidos para o formato esperado
                const vendedoresFormatados = response.data.map(vendedor => ({
                    CPF: vendedor.CPF || vendedor.cpf,
                    descricao: vendedor.descricao || 'Descrição não fornecida',
                    foto_perfil: vendedor.foto_perfil || vendedor.fotoPerfil || 'default.jpg',
                    total_vendas: parseFloat(vendedor.total_vendas || vendedor.totalVendas || 0),
                    avaliacao_media: parseFloat(vendedor.avaliacao_media || vendedor.avaliacaoMedia || 0),
                    data_inicio_vendas: vendedor.data_inicio_vendas || vendedor.dataInicioVendas || 'Data não disponível',
                }));
                setVendedores(vendedoresFormatados);
                setError(null);
            })
            .catch(err => {
                setError('Erro ao buscar vendedores');
                console.error('Erro ao buscar vendedores:', err);
            });
    };

    return (
        <div className="entity-page">
            <h1>Gerenciamento de Vendedores</h1>
            <div className="action-buttons">
                <button onClick={fetchVendedores}>Listar Vendedores</button>
                <Link to="/vendedores/new" className="action-button">
                    Adicionar Novo Vendedor
                </Link>
            </div>

            {error && <p className="error-message">{error}</p>}

            <div className="vendedor-list">
                {vendedores.length > 0 && (
                    <table className="vendedor-table">
                        <thead>
                        <tr>
                            <th>CPF</th>
                            <th>Descrição</th>
                            <th>Foto de Perfil</th>
                            <th>Total de Vendas</th>
                            <th>Avaliação Média</th>
                            <th>Data de Início</th>
                        </tr>
                        </thead>
                        <tbody>
                        {vendedores.map(vendedor => (
                            <tr key={vendedor.CPF}>
                                <td>{vendedor.CPF}</td>
                                <td>{vendedor.descricao}</td>
                                <td>
                                    <img
                                        src={vendedor.foto_perfil}
                                        alt="Foto de Perfil"
                                        className="vendedor-foto"
                                    />
                                </td>
                                <td>R$ {vendedor.total_vendas.toFixed(2)}</td>
                                <td>{vendedor.avaliacao_media.toFixed(1)}</td>
                                <td>{new Date(vendedor.data_inicio_vendas).toLocaleDateString()}</td>
                            </tr>
                        ))}
                        </tbody>
                    </table>
                )}
            </div>
        </div>
    );
};

export default VendedorPage;
