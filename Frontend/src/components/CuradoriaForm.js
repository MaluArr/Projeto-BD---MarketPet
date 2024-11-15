import React, { useState, useEffect } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import CuradoriaService from '../services/CuradoriaService';
import '../styles/Curadoria.css';

const CuradoriaForm = () => {
    const [formData, setFormData] = useState({
        codigoCuradoria: '',
        descricao: '',
        resultadoCuradoria: '',
        idCurador: '',
        codigoProduto: ''
    });
    const [error, setError] = useState('');
    const navigate = useNavigate();
    const { codigoCuradoria } = useParams();

    useEffect(() => {
        if (codigoCuradoria) {
            fetchCuradoria(codigoCuradoria);
        }
    }, [codigoCuradoria]);

    const fetchCuradoria = async (codigoCuradoria) => {
        try {
            const data = await CuradoriaService.getCuradoriaByCodigo(codigoCuradoria);
            setFormData(data);
        } catch (error) {
            setError('Falha ao carregar dados da curadoria');
        }
    };

    const handleChange = (e) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        setError('');
        try {
            if (codigoCuradoria) {
                await CuradoriaService.updateCuradoria(codigoCuradoria, formData);
            } else {
                await CuradoriaService.createCuradoria(formData);
            }
            navigate('/curadorias');
        } catch (error) {
            setError('Falha ao salvar curadoria');
        }
    };

    return (
        <div className="entity-form">
            <h2>{codigoCuradoria ? 'Editar Curadoria' : 'Nova Curadoria'}</h2>
            {error && <p className="error-message">{error}</p>}
            <form onSubmit={handleSubmit}>
                <input
                    type="text"
                    name="descricao"
                    value={formData.descricao}
                    onChange={handleChange}
                    placeholder="Descrição"
                    required
                />
                <input
                    type="text"
                    name="resultadoCuradoria"
                    value={formData.resultadoCuradoria}
                    onChange={handleChange}
                    placeholder="Resultado da Curadoria"
                />
                <input
                    type="number"
                    name="idCurador"
                    value={formData.idCurador}
                    onChange={handleChange}
                    placeholder="ID do Curador"
                    required
                />
                <input
                    type="number"
                    name="codigoProduto"
                    value={formData.codigoProduto}
                    onChange={handleChange}
                    placeholder="Código do Produto"
                    required
                />
                <button type="submit">{codigoCuradoria ? 'Atualizar' : 'Criar'}</button>
                <button type="button" onClick={() => navigate('/curadorias')}>Cancelar</button>
            </form>
        </div>
    );
};

export default CuradoriaForm;