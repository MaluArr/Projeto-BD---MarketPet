import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import VendaService from '../services/VendaService';
import '../styles/Venda.css';

const VendaList = () => {
    const [vendas, setVendas] = useState([]);
    const [error, setError] = useState('');

    useEffect(() => {
        fetchVendas();
    }, []);

    const fetchVendas = async () => {
        try {
            const data = await VendaService.fetchVendas();
            setVendas(data);
        } catch (error) {
            setError('Falha ao carregar vendas');
        }
    };

    const handleDelete = async (idVenda) => {
        try {
            await VendaService.deleteVenda(idVenda);
            setVendas(vendas.filter(venda => venda.idVenda !== idVenda));
        } catch (error) {
            setError('Falha ao deletar venda');
        }
    };

    return (
        <div className="entity-list">
            <h1>Lista de Vendas</h1>
            {error && <p className="error-message">{error}</p>}
            <Link to="/vendas/new" className="add-button">Adicionar Nova Venda</Link>
            <table>
                <thead>
                <tr>
                    <th>ID</th>
                    <th>CPF Comprador</th>
                    <th>Código Produto</th>
                    <th>Data Venda</th>
                    <th>Ações</th>
                </tr>
                </thead>
                <tbody>
                {vendas.map(venda => (
                    <tr key={venda.idVenda}>
                        <td>{venda.idVenda}</td>
                        <td>{venda.cpfComprador}</td>
                        <td>{venda.codigoProduto}</td>
                        <td>{venda.dataVenda}</td>
                        <td>
                            <Link to={`/vendas/${venda.idVenda}/edit`} className="edit-button">Editar</Link>
                            <button onClick={() => handleDelete(venda.idVenda)} className="delete-button">Excluir</button>
                        </td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
};

export default VendaList;