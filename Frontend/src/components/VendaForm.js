import React, { useState, useEffect } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import VendaService from '../services/VendaService';
import '../styles/Venda.css';

const VendaForm = () => {
    const [formData, setFormData] = useState({
        cpfComprador: '',
        codigoProduto: '',
        dataVenda: ''
    });
    const [error, setError] = useState('');
    const navigate = useNavigate();
    const { idVenda } = useParams();

    useEffect(() => {
        if (idVenda) {
            fetchVenda(idVenda);
        }
    }, [idVenda]);

    const fetchVenda = async (id) => {
        try {
            const data = await VendaService.getVendaById(id);
            setFormData(data);
        } catch (error) {
            setError('Falha ao carregar dados da venda');
        }
    };

    const handleChange = (e) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        setError('');
        try {
            if (idVenda) {
                await VendaService.updateVenda(idVenda, formData);
            } else {
                await VendaService.createVenda(formData);
            }
            navigate('/vendas');
        } catch (error) {
            setError('Falha ao salvar venda');
        }
    };

    return (
        <div className="entity-form">
            <h2>{idVenda ? 'Editar Venda' : 'Nova Venda'}</h2>
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
                    type="date"
                    name="dataVenda"
                    value={formData.dataVenda}
                    onChange={handleChange}
                    required
                />
                <button type="submit">{idVenda ? 'Atualizar' : 'Criar'}</button>
                <button type="button" onClick={() => navigate('/vendas')}>Cancelar</button>
            </form>
        </div>
    );
};

export default VendaForm;