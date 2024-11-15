import React, { useState, useEffect } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import AtendenteService from '../services/AtendenteService';
import '../styles/Atendente.css';

const AtendenteForm = () => {
    const [formData, setFormData] = useState({
        idAtendente: '',
        cpfFuncionario: ''
    });
    const [error, setError] = useState('');
    const navigate = useNavigate();
    const { idAtendente } = useParams();

    useEffect(() => {
        if (idAtendente) {
            fetchAtendente(idAtendente);
        }
    }, [idAtendente]);

    const fetchAtendente = async (idAtendente) => {
        try {
            const data = await AtendenteService.getAtendenteById(idAtendente);
            setFormData(data);
        } catch (error) {
            setError('Falha ao carregar dados do atendente');
        }
    };

    const handleChange = (e) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        setError('');
        try {
            if (idAtendente) {
                await AtendenteService.updateAtendente(idAtendente, formData);
            } else {
                await AtendenteService.createAtendente(formData);
            }
            navigate('/atendentes');
        } catch (error) {
            setError('Falha ao salvar atendente');
        }
    };

    return (
        <div className="entity-form">
            <h2>{idAtendente ? 'Editar Atendente' : 'Novo Atendente'}</h2>
            {error && <p className="error-message">{error}</p>}
            <form onSubmit={handleSubmit}>
                <input
                    type="text"
                    name="cpfFuncionario"
                    value={formData.cpfFuncionario}
                    onChange={handleChange}
                    placeholder="CPF do Funcionário"
                    required
                />
                <button type="submit">{idAtendente ? 'Atualizar' : 'Criar'}</button>
                <button type="button" onClick={() => navigate('/atendentes')}>Cancelar</button>
            </form>
        </div>
    );
};

export default AtendenteForm;