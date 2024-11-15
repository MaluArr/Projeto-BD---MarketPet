import React, { useState, useEffect } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import EnderecoService from '../services/EnderecoService';
import '../styles/Endereco.css';

const EnderecoForm = () => {
    const [formData, setFormData] = useState({
        cep: '',
        rua: '',
        numero: '',
        bairro: '',
        cidade: '',
        estado: '',
        complemento: ''
    });
    const [error, setError] = useState('');
    const navigate = useNavigate();
    const { idEndereco } = useParams();

    useEffect(() => {
        if (idEndereco) {
            fetchEndereco(idEndereco);
        }
    }, [idEndereco]);

    const fetchEndereco = async (id) => {
        try {
            const response = await EnderecoService.getEnderecoById(id);
            setFormData(response.data);
        } catch (error) {
            setError('Falha ao carregar dados do endereço');
        }
    };

    const handleChange = (e) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        setError('');
        try {
            if (idEndereco) {
                await EnderecoService.updateEndereco(idEndereco, formData);
            } else {
                await EnderecoService.createEndereco(formData);
            }
            navigate('/enderecos');
        } catch (error) {
            setError('Falha ao salvar endereço');
        }
    };

    return (
        <div className="entity-form">
            <h2>{idEndereco ? 'Editar Endereço' : 'Novo Endereço'}</h2>
            {error && <p className="error-message">{error}</p>}
            <form onSubmit={handleSubmit}>
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
                <button type="submit">{idEndereco ? 'Atualizar' : 'Criar'}</button>
                <button type="button" onClick={() => navigate('/enderecos')}>Cancelar</button>
            </form>
        </div>
    );
};

export default EnderecoForm;