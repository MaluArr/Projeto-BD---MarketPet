// src/components/VendedorList.js
import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';
import '../styles/Vendedor.css';

const VendedorList = () => {
    const [vendedores, setVendedores] = useState([]);

    useEffect(() => {
        const fetchVendedores = async () => {
            try {
                const response = await axios.get('/api/vendedores');
                setVendedores(response.data);
            } catch (error) {
                console.error('Erro ao buscar vendedores:', error);
            }
        };
        fetchVendedores();
    }, []);

    return (
        <div className="vendedor-list">
            <h2>Lista de Vendedores</h2>
            <Link to="/vendedores/new" className="button">Novo Vendedor</Link>
            <table>
                <thead>
                <tr>
                    <th>CPF</th>
                    <th>Descrição</th>
                    <th>Total Vendas</th>
                    <th>Avaliação Média</th>
                    <th>Ações</th>
                </tr>
                </thead>
                <tbody>
                {vendedores.map((vendedor) => (
                    <tr key={vendedor.cpf}>
                        <td data-label="CPF">{vendedor.cpf}</td>
                        <td data-label="Descrição">{vendedor.descricao}</td>
                        <td data-label="Total Vendas">{vendedor.totalVendas}</td>
                        <td data-label="Avaliação Média">{vendedor.avaliacaoMedia}</td>
                        <td data-label="Ações">
                            <Link to={`/vendedores/${vendedor.cpf}`}>Detalhes</Link>
                            <Link to={`/vendedores/${vendedor.cpf}/edit`}>Editar</Link>
                        </td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
};

export default VendedorList;