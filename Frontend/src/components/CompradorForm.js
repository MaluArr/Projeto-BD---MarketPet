import React, { useState, useEffect } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import CompradorService from '../services/CompradorService';
import '../styles/Comprador.css';

const CompradorForm = () => {
    const [formData, setFormData] = useState({
        cpf: '',
        idEndereco: '',
        idCartao: ''
    });
    const [error, setError] = useState('');
    const navigate = useNavigate();
    const { cpf } = useParams();

    useEffect(() => {
        if (cpf) {
            fetchComprador(cpf);
        }
    }, [cpf]);

    const fetchComprador = async (cpf) => {
        try {
            const data = await CompradorService.getCompradorByCpf(cpf);
            setFormData(data);
        } catch (error) {
            setError('Falha ao carregar dados do comprador');
        }
    };

    const handleChange = (e) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        setError('');
        try {
            if (cpf) {
                await CompradorService.updateComprador(cpf, formData);
            } else {
                await CompradorService.createComprador(formData);
            }
            navigate('/compradores');
        } catch (error) {
            setError('Falha ao salvar comprador');
        }
    };

    return (
        <div className="entity-form">
            <h2>{cpf ? 'Editar Comprador' : 'Novo Comprador'}</h2>
            {error && <p className="error-message">{error}</p>}
            <form onSubmit={handleSubmit}>
                <input
                    type="text"
                    name="cpf"
                    value={formData.cpf}
                    onChange={handleChange}
                    placeholder="CPF"
                    required
                    readOnly={!!cpf}
                />
                <input
                    type="number"
                    name="idEndereco"
                    value={formData.idEndereco}
                    onChange={handleChange}
                    placeholder="ID do Endereço"
                    required
                />
                <input
                    type="number"
                    name="idCartao"
                    value={formData.idCartao}
                    onChange={handleChange}
                    placeholder="ID do Cartão"
                    required
                />
                <button type="submit">{cpf ? 'Atualizar' : 'Criar'}</button>
                <button type="button" onClick={() => navigate('/compradores')}>Cancelar</button>
            </form>
        </div>
    );
};

export default CompradorForm;