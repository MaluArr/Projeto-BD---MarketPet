import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import VendedorService from '../services/VendedorService';
import '../styles/Vendedor.css';

const VendedorList = () => {
    const [vendedores, setVendedores] = useState([]);
    const [error, setError] = useState('');

    useEffect(() => {
        fetchVendedores();
    }, []);

    const fetchVendedores = async () => {
        try {
            const data = await VendedorService.fetchVendedores();
            setVendedores(data);
        } catch (error) {
            setError('Falha ao carregar vendedores');
        }
    };

    const handleDelete = async (cpf) => {
        try {
            await VendedorService.deleteVendedor(cpf);
            setVendedores(vendedores.filter(vendedor => vendedor.cpf !== cpf));
        } catch (error) {
            setError('Falha ao deletar vendedor');
        }
    };

    return (
        <div className="entity-list">
            <h1>Lista de Vendedores</h1>
            {error && <p className="error-message">{error}</p>}
            <Link to="/vendedores/new" className="add-button">Adicionar Novo Vendedor</Link>
            <table>
                <thead>
                <tr>
                    <th>CPF</th>
                    <th>Descrição</th>
                    <th>Total de Vendas</th>
                    <th>Avaliação Média</th>
                    <th>Data de Início</th>
                    <th>Ações</th>
                </tr>
                </thead>
                <tbody>
                {vendedores.map(vendedor => (
                    <tr key={vendedor.cpf}>
                        <td>{vendedor.cpf}</td>
                        <td>{vendedor.descricao}</td>
                        <td>{vendedor.totalVendas}</td>
                        <td>{vendedor.avaliacaoMedia}</td>
                        <td>{new Date(vendedor.dataInicioVendas).toLocaleDateString()}</td>
                        <td>
                            <Link to={`/vendedores/${vendedor.cpf}/edit`} className="edit-button">Editar</Link>
                            <button onClick={() => handleDelete(vendedor.cpf)} className="delete-button">Excluir</button>
                        </td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
};

export default VendedorList;