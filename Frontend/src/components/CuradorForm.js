import React, { useState, useEffect } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import CuradorService from '../services/CuradorService';
import '../styles/Curador.css';

const CuradorForm = () => {
    const [formData, setFormData] = useState({
        idCurador: '',
        cpfFuncionario: ''
    });
    const [error, setError] = useState('');
    const navigate = useNavigate();
    const { idCurador } = useParams();

    useEffect(() => {
        if (idCurador) {
            fetchCurador(idCurador);
        }
    }, [idCurador]);

    const fetchCurador = async (idCurador) => {
        try {
            const data = await CuradorService.getCuradorById(idCurador);
            setFormData(data);
        } catch (error) {
            setError('Falha ao carregar dados do curador');
        }
    };

    const handleChange = (e) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        setError('');
        try {
            if (idCurador) {
                await CuradorService.updateCurador(idCurador, formData);
            } else {
                await CuradorService.createCurador(formData);
            }
            navigate('/curadores');
        } catch (error) {
            setError('Falha ao salvar curador');
        }
    };

    return (
        <div className="entity-form">
            <h2>{idCurador ? 'Editar Curador' : 'Novo Curador'}</h2>
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
                <button type="submit">{idCurador ? 'Atualizar' : 'Criar'}</button>
                <button type="button" onClick={() => navigate('/curadores')}>Cancelar</button>
            </form>
        </div>
    );
};

export default CuradorForm;