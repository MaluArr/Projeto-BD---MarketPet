import React, { useState, useEffect } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import CarrinhoService from '../services/CarrinhoService';
import '../styles/Carrinho.css';

const CarrinhoForm = () => {
    const [formData, setFormData] = useState({
        idCarrinho: '',
        valorTotal: '',
        cpfComprador: ''
    });
    const [error, setError] = useState('');
    const navigate = useNavigate();
    const { idCarrinho } = useParams();

    useEffect(() => {
        if (idCarrinho) {
            fetchCarrinho(idCarrinho);
        }
    }, [idCarrinho]);

    const fetchCarrinho = async (idCarrinho) => {
        try {
            const data = await CarrinhoService.getCarrinhoById(idCarrinho);
            setFormData(data);
        } catch (error) {
            setError('Falha ao carregar dados do carrinho');
        }
    };

    const handleChange = (e) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        setError('');
        try {
            if (idCarrinho) {
                await CarrinhoService.updateCarrinho(idCarrinho, formData);
            } else {
                await CarrinhoService.createCarrinho(formData);
            }
            navigate('/carrinhos');
        } catch (error) {
            setError('Falha ao salvar carrinho');
        }
    };

    return (
        <div className="entity-form">
            <h2>{idCarrinho ? 'Editar Carrinho' : 'Novo Carrinho'}</h2>
            {error && <p className="error-message">{error}</p>}
            <form onSubmit={handleSubmit}>
                <input
                    type="number"
                    name="valorTotal"
                    value={formData.valorTotal}
                    onChange={handleChange}
                    placeholder="Valor Total"
                    step="0.01"
                    required
                />
                <input
                    type="text"
                    name="cpfComprador"
                    value={formData.cpfComprador}
                    onChange={handleChange}
                    placeholder="CPF do Comprador"
                    required
                />
                <button type="submit">{idCarrinho ? 'Atualizar' : 'Criar'}</button>
                <button type="button" onClick={() => navigate('/carrinhos')}>Cancelar</button>
            </form>
        </div>
    );
};

export default CarrinhoForm;