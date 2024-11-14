// src/components/CompradorList.js
import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import CompradorService from '../services/CompradorService';
import '../styles/Comprador.css';

const CompradorList = () => {
    const [compradores, setCompradores] = useState([]);
    const [error, setError] = useState('');

    useEffect(() => {
        fetchCompradores();
    }, []);

    const fetchCompradores = async () => {
        try {
            const data = await CompradorService.fetchCompradores();
            setCompradores(data);
        } catch (error) {
            setError('Falha ao carregar compradores');
        }
    };

    const handleDelete = async (cpf) => {
        try {
            await CompradorService.deleteComprador(cpf);
            setCompradores(compradores.filter(comprador => comprador.cpf !== cpf));
        } catch (error) {
            setError('Falha ao deletar comprador');
        }
    };

    return (
        <div className="entity-list">
            <h1>Lista de Compradores</h1>
            {error && <p className="error-message">{error}</p>}
            <Link to="/compradores/new" className="add-button">Adicionar Novo Comprador</Link>
            <table>
                <thead>
                <tr>
                    <th>CPF</th>
                    <th>ID Endereço</th>
                    <th>ID Cartão</th>
                    <th>Ações</th>
                </tr>
                </thead>
                <tbody>
                {compradores.map(comprador => (
                    <tr key={comprador.cpf}>
                        <td>{comprador.cpf}</td>
                        <td>{comprador.idEndereco}</td>
                        <td>{comprador.idCartao}</td>
                        <td>
                            <Link to={`/compradores/${comprador.cpf}/edit`} className="edit-button">Editar</Link>
                            <button onClick={() => handleDelete(comprador.cpf)} className="delete-button">Excluir</button>
                        </td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
};

export default CompradorList;