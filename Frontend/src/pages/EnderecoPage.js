import React, { useState, useEffect } from 'react';
import EnderecoList from '../components/EnderecoList';
import EnderecoForm from '../components/EnderecoForm';
import EnderecoService from '../services/EnderecoService';
import '../styles/Endereco.css';

const EnderecoPage = () => {
    const [enderecos, setEnderecos] = useState([]);
    const [selectedEndereco, setSelectedEndereco] = useState(null);
    const [isFormVisible, setIsFormVisible] = useState(false);

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

    const handleAddNew = () => {
        setSelectedEndereco(null);
        setIsFormVisible(true);
    };

    const handleEdit = (endereco) => {
        setSelectedEndereco(endereco);
        setIsFormVisible(true);
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

    const handleSave = (endereco) => {
        if (endereco.idEndereco) {
            EnderecoService.updateEndereco(endereco.idEndereco, endereco)
                .then(() => {
                    fetchEnderecos();
                    setIsFormVisible(false);
                })
                .catch(error => {
                    console.error('Erro ao atualizar endereço:', error);
                });
        } else {
            EnderecoService.createEndereco(endereco)
                .then(() => {
                    fetchEnderecos();
                    setIsFormVisible(false);
                })
                .catch(error => {
                    console.error('Erro ao criar endereço:', error);
                });
        }
    };

    return (
        <div className="endereco-page">
            <h1>Gerenciamento de Endereços</h1>
            <button onClick={handleAddNew} className="add-button">Adicionar Novo Endereço</button>

            {isFormVisible && (
                <EnderecoForm
                    endereco={selectedEndereco}
                    onSave={handleSave}
                    onCancel={() => setIsFormVisible(false)}
                />
            )}

            <EnderecoList
                enderecos={enderecos}
                onEdit={handleEdit}
                onDelete={handleDelete}
            />
        </div>
    );
};

export default EnderecoPage;