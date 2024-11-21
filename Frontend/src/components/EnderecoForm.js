import React, { useState, useEffect } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import axios from 'axios';
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
    const formatData = () => ({
        cep: formData.cep.replace(/[^0-9]/g, '').slice(0, 8), // Remove caracteres inválidos e limita a 8 dígitos
        rua: formData.rua.trim(), // Remove espaços em branco extras
        numero: formData.numero.trim(), // Mantém como string para seguir o padrão do banco
        bairro: formData.bairro.trim(),
        cidade: formData.cidade.trim(),
        estado: formData.estado.toUpperCase().trim().slice(0, 2), // Garante letras maiúsculas e limita a 2 caracteres
        complemento: formData.complemento.trim() || null // Envia `null` se estiver vazio
    });
    const [error, setError] = useState('');
    const navigate = useNavigate();
    const { idEndereco } = useParams(); // Pega o ID do endereço via URL

    useEffect(() => {
        if (idEndereco) {
            fetchEndereco(idEndereco); // Carrega os dados para edição
        }
    }, [idEndereco]);

    const fetchEndereco = async (id) => {
        try {
            const response = await axios.get(`http://localhost:8080/api/enderecos/${id}`);
            setFormData(response.data); // Preenche os dados no formulário
        } catch (error) {
            setError('Falha ao carregar dados do endereço');
        }
    };

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({ ...formData, [name]: value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        const formData= formatData();
        setError('');
        try {
            if (idEndereco) {
                await axios.put(`http://localhost:8080/api/enderecos/${idEndereco}`, formData);
            } else {
                await axios.post('http://localhost:8080/api/enderecos', formData);
            }
            navigate('/enderecos'); // Redireciona após salvar
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
