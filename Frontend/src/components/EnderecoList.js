import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import EnderecoService from '../services/EnderecoService';
import '../styles/Endereco.css';

const EnderecoList = () => {
    const [enderecos, setEnderecos] = useState([]);
    const [error, setError] = useState('');

    useEffect(() => {
        fetchEnderecos();
    }, []);

    const fetchEnderecos = async () => {
        try {
            const response = await EnderecoService.getEnderecos();
            setEnderecos(response.data);
        } catch (error) {
            setError('Falha ao carregar endereços');
        }
    };

    const handleDelete = async (idEndereco) => {
        try {
            await EnderecoService.deleteEndereco(idEndereco);
            setEnderecos(enderecos.filter(endereco => endereco.idEndereco !== idEndereco));
        } catch (error) {
            setError('Falha ao deletar endereço');
        }
    };

    return (
        <div className="entity-list">
            <h1>Lista de Endereços</h1>
            {error && <p className="error-message">{error}</p>}
            <Link to="/enderecos/new" className="add-button">Adicionar Novo Endereço</Link>
            <table>
                <thead>
                <tr>
                    <th>CEP</th>
                    <th>Rua</th>
                    <th>Número</th>
                    <th>Cidade</th>
                    <th>Estado</th>
                    <th>Ações</th>
                </tr>
                </thead>
                <tbody>
                {enderecos.map(endereco => (
                    <tr key={endereco.idEndereco}>
                        <td>{endereco.cep}</td>
                        <td>{endereco.rua}</td>
                        <td>{endereco.numero}</td>
                        <td>{endereco.cidade}</td>
                        <td>{endereco.estado}</td>
                        <td>
                            <Link to={`/enderecos/${endereco.idEndereco}/edit`} className="edit-button">Editar</Link>
                            <button onClick={() => handleDelete(endereco.idEndereco)} className="delete-button">Excluir</button>
                        </td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
};

export default EnderecoList;