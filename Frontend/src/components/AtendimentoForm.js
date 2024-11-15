import React, { useState, useEffect } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import AtendimentoService from '../services/AtendimentoService';
import '../styles/Atendimento.css';

const AtendimentoForm = () => {
    const [formData, setFormData] = useState({
        idAtendimento: '',
        cpfFuncionario: '',
        cpfUsuario: '',
        idChat: '',
        dataAtendimento: '',
        categoria: ''
    });
    const [error, setError] = useState('');
    const navigate = useNavigate();
    const { idAtendimento } = useParams();

    useEffect(() => {
        if (idAtendimento) {
            fetchAtendimento(idAtendimento);
        }
    }, [idAtendimento]);

    const fetchAtendimento = async (idAtendimento) => {
        try {
            const data = await AtendimentoService.getAtendimentoById(idAtendimento);
            setFormData(data);
        } catch (error) {
            setError('Falha ao carregar dados do atendimento');
        }
    };

    const handleChange = (e) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        setError('');
        try {
            if (idAtendimento) {
                await AtendimentoService.updateAtendimento(idAtendimento, formData);
            } else {
                await AtendimentoService.createAtendimento(formData);
            }
            navigate('/atendimentos');
        } catch (error) {
            setError('Falha ao salvar atendimento');
        }
    };

    return (
        <div className="entity-form">
            <h2>{idAtendimento ? 'Editar Atendimento' : 'Novo Atendimento'}</h2>
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
                <input
                    type="text"
                    name="cpfUsuario"
                    value={formData.cpfUsuario}
                    onChange={handleChange}
                    placeholder="CPF do Usuário"
                    required
                />
                <input
                    type="number"
                    name="idChat"
                    value={formData.idChat}
                    onChange={handleChange}
                    placeholder="ID do Chat"
                    required
                />
                <input
                    type="date"
                    name="dataAtendimento"
                    value={formData.dataAtendimento}
                    onChange={handleChange}
                    required
                />
                <input
                    type="text"
                    name="categoria"
                    value={formData.categoria}
                    onChange={handleChange}
                    placeholder="Categoria"
                    required
                />
                <button type="submit">{idAtendimento ? 'Atualizar' : 'Criar'}</button>
                <button type="button" onClick={() => navigate('/atendimentos')}>Cancelar</button>
            </form>
        </div>
    );
};

export default AtendimentoForm;