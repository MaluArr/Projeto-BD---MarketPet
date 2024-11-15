import React, { useState, useEffect } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import AvaliacaoService from '../services/AvaliacaoService';
import '../styles/Avaliacao.css';

const AvaliacaoForm = () => {
    const [formData, setFormData] = useState({
        cpfComprador: '',
        codigoProduto: '',
        idVenda: '',
        nota: ''
    });
    const [error, setError] = useState('');
    const navigate = useNavigate();
    const { idAvaliacao } = useParams();

    useEffect(() => {
        if (idAvaliacao) {
            fetchAvaliacao(idAvaliacao);
        }
    }, [idAvaliacao]);

    const fetchAvaliacao = async (id) => {
        try {
            const data = await AvaliacaoService.getAvaliacaoById(id);
            setFormData(data);
        } catch (error) {
            setError('Falha ao carregar dados da avaliação');
        }
    };

    const handleChange = (e) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        setError('');
        try {
            if (idAvaliacao) {
                await AvaliacaoService.updateAvaliacao(idAvaliacao, formData);
            } else {
                await AvaliacaoService.createAvaliacao(formData);
            }
            navigate('/avaliacoes');
        } catch (error) {
            setError('Falha ao salvar avaliação');
        }
    };

    return (
        <div className="entity-form">
            <h2>{idAvaliacao ? 'Editar Avaliação' : 'Nova Avaliação'}</h2>
            {error && <p className="error-message">{error}</p>}
            <form onSubmit={handleSubmit}>
                <input
                    type="text"
                    name="cpfComprador"
                    value={formData.cpfComprador}
                    onChange={handleChange}
                    placeholder="CPF do Comprador"
                    required
                />
                <input
                    type="text"
                    name="codigoProduto"
                    value={formData.codigoProduto}
                    onChange={handleChange}
                    placeholder="Código do Produto"
                    required
                />
                <input
                    type="text"
                    name="idVenda"
                    value={formData.idVenda}
                    onChange={handleChange}
                    placeholder="ID da Venda"
                    required
                />
                <input
                    type="number"
                    name="nota"
                    value={formData.nota}
                    onChange={handleChange}
                    placeholder="Nota"
                    step="0.1"
                    min="0"
                    max="5"
                    required
                />
                <button type="submit">{idAvaliacao ? 'Atualizar' : 'Criar'}</button>
                <button type="button" onClick={() => navigate('/avaliacoes')}>Cancelar</button>
            </form>
        </div>
    );
};

export default AvaliacaoForm;