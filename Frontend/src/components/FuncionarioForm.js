import React, { useState, useEffect } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import FuncionarioService from '../services/FuncionarioService';
import '../styles/Funcionario.css';

const FuncionarioForm = () => {
    const [formData, setFormData] = useState({
        cpfFuncionario: '',
        nome: ''
    });
    const [error, setError] = useState('');
    const navigate = useNavigate();
    const { cpfFuncionario } = useParams();

    useEffect(() => {
        if (cpfFuncionario) {
            fetchFuncionario(cpfFuncionario);
        }
    }, [cpfFuncionario]);

    const fetchFuncionario = async (cpfFuncionario) => {
        try {
            const data = await FuncionarioService.getFuncionarioByCpf(cpfFuncionario);
            setFormData(data);
        } catch (error) {
            setError('Falha ao carregar dados do funcionário');
        }
    };

    const handleChange = (e) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        setError('');
        try {
            if (cpfFuncionario) {
                await FuncionarioService.updateFuncionario(cpfFuncionario, formData);
            } else {
                await FuncionarioService.createFuncionario(formData);
            }
            navigate('/funcionarios');
        } catch (error) {
            setError('Falha ao salvar funcionário');
        }
    };

    return (
        <div className="entity-form">
            <h2>{cpfFuncionario ? 'Editar Funcionário' : 'Novo Funcionário'}</h2>
            {error && <p className="error-message">{error}</p>}
            <form onSubmit={handleSubmit}>
                <input
                    type="text"
                    name="cpfFuncionario"
                    value={formData.cpfFuncionario}
                    onChange={handleChange}
                    placeholder="CPF do Funcionário"
                    required
                    readOnly={!!cpfFuncionario}
                />
                <input
                    type="text"
                    name="nome"
                    value={formData.nome}
                    onChange={handleChange}
                    placeholder="Nome do Funcionário"
                    required
                />
                <button type="submit">{cpfFuncionario ? 'Atualizar' : 'Criar'}</button>
                <button type="button" onClick={() => navigate('/funcionarios')}>Cancelar</button>
            </form>
        </div>
    );
};

export default FuncionarioForm;