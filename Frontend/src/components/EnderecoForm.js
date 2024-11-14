import React, { useState, useEffect } from 'react';
import EnderecoService from '../services/EnderecoService';
import '../styles/Endereco.css';

const EnderecoForm = ({ endereco, onSave }) => {
    const [formData, setFormData] = useState({
        idEndereco: '',
        cep: '',
        rua: '',
        numero: '',
        bairro: '',
        cidade: '',
        estado: '',
        complemento: ''
    });

    useEffect(() => {
        if (endereco) {
            setFormData(endereco);
        }
    }, [endereco]);

    const handleChange = (e) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        if (formData.idEndereco) {
            EnderecoService.updateEndereco(formData.idEndereco, formData)
                .then(() => {
                    onSave();
                    resetForm();
                })
                .catch(error => {
                    console.error('Erro ao atualizar endereço:', error);
                });
        } else {
            EnderecoService.createEndereco(formData)
                .then(() => {
                    onSave();
                    resetForm();
                })
                .catch(error => {
                    console.error('Erro ao criar endereço:', error);
                });
        }
    };

    const resetForm = () => {
        setFormData({
            idEndereco: '',
            cep: '',
            rua: '',
            numero: '',
            bairro: '',
            cidade: '',
            estado: '',
            complemento: ''
        });
    };

    return (
        <form onSubmit={handleSubmit} className="endereco-form">
            <h2>{formData.idEndereco ? 'Editar Endereço' : 'Novo Endereço'}</h2>
            <input
                type="text"
                name="cep"
                value={formData.cep}
                onChange={handleChange}
                placeholder="CEP"
                required
            />
            <input
                type="text"
                name="rua"
                value={formData.rua}
                onChange={handleChange}
                placeholder="Rua"
                required
            />
            <input
                type="text"
                name="numero"
                value={formData.numero}
                onChange={handleChange}
                placeholder="Número"
                required
            />
            <input
                type="text"
                name="bairro"
                value={formData.bairro}
                onChange={handleChange}
                placeholder="Bairro"
                required
            />
            <input
                type="text"
                name="cidade"
                value={formData.cidade}
                onChange={handleChange}
                placeholder="Cidade"
                required
            />
            <input
                type="text"
                name="estado"
                value={formData.estado}
                onChange={handleChange}
                placeholder="Estado"
                required
            />
            <input
                type="text"
                name="complemento"
                value={formData.complemento}
                onChange={handleChange}
                placeholder="Complemento"
            />
            <button type="submit">{formData.idEndereco ? 'Atualizar' : 'Criar'}</button>
            <button type="button" onClick={resetForm}>Cancelar</button>
        </form>
    );
};

export default EnderecoForm;