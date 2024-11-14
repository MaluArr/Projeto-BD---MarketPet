import React, { useState, useEffect } from 'react';
import EnderecoService from '../services/EnderecoService';
import '../styles/Endereco.css';
import EnderecoForm from '../components/EnderecoForm';

const EnderecoList = () => {
    const [enderecos, setEnderecos] = useState([]);
    const [selectedEndereco, setSelectedEndereco] = useState(null);

    useEffect(() => {
        fetchEnderecos();
    }, []);

    const fetchEnderecos = () => {
        EnderecoService.getEnderecos()
            .then(response => {
                setEnderecos(response.data);
            })
            .catch(error => {
                console.error('Erro ao buscar endereços:', error);
            });
    };

    const handleDelete = (id) => {
        EnderecoService.deleteEndereco(id)
            .then(() => {
                fetchEnderecos();
            })
            .catch(error => {
                console.error('Erro ao deletar endereço:', error);
            });
    };

    const handleEdit = (endereco) => {
        setSelectedEndereco(endereco);
    };

    return (
        <div className="endereco-list">
            <h2>Lista de Endereços</h2>
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
                            <button onClick={() => handleEdit(endereco)}>Editar</button>
                            <button onClick={() => handleDelete(endereco.idEndereco)}>Excluir</button>
                        </td>
                    </tr>
                ))}
                </tbody>
            </table>
            {selectedEndereco && <EnderecoForm endereco={selectedEndereco} onSave={fetchEnderecos} />}
        </div>
    );
};

export default EnderecoList;