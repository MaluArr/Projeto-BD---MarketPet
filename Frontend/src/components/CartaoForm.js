import React, { useState, useEffect } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import CartaoService from '../services/CartaoService';
import '../styles/Cartao.css';

const CartaoForm = () => {
    const [formData, setFormData] = useState({
        idCartao: '',
        numero: '',
        nomeTitular: '',
        validade: '',
        cvv: '',
        bandeira: ''
    });
    const [error, setError] = useState('');
    const navigate = useNavigate();
    const { idCartao } = useParams();

    useEffect(() => {
        if (idCartao) {
            fetchCartao(idCartao);
        }
    }, [idCartao]);

    const fetchCartao = async (id) => {
        try {
            const response = await CartaoService.getCartaoById(id);
            const cartao = response.data;
            setFormData({
                ...cartao,
                validade: cartao.validade.split('T')[0] // Formatando a data
            });
        } catch (error) {
            setError('Falha ao carregar dados do cartão');
        }
    };

    const handleChange = (e) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        setError('');
        try {
            if (idCartao) {
                await CartaoService.updateCartao(idCartao, formData);
            } else {
                await CartaoService.createCartao(formData);
            }
            navigate('/cartoes');
        } catch (error) {
            setError('Falha ao salvar cartão');
        }
    };

    return (
        <div className="entity-form">
            <h2>{idCartao ? 'Editar Cartão' : 'Novo Cartão'}</h2>
            {error && <p className="error-message">{error}</p>}
            <form onSubmit={handleSubmit}>
                <input
                    type="text"
                    name="numero"
                    value={formData.numero}
                    onChange={handleChange}
                    placeholder="Número do Cartão"
                    required
                />
                <input
                    type="text"
                    name="nomeTitular"
                    value={formData.nomeTitular}
                    onChange={handleChange}
                    placeholder="Nome do Titular"
                    required
                />
                <input
                    type="date"
                    name="validade"
                    value={formData.validade}
                    onChange={handleChange}
                    required
                />
                <input
                    type="text"
                    name="cvv"
                    value={formData.cvv}
                    onChange={handleChange}
                    placeholder="CVV"
                    required
                />
                <input
                    type="text"
                    name="bandeira"
                    value={formData.bandeira}
                    onChange={handleChange}
                    placeholder="Bandeira"
                    required
                />
                <button type="submit">{idCartao ? 'Atualizar' : 'Criar'}</button>
                <button type="button" onClick={() => navigate('/cartoes')}>Cancelar</button>
            </form>
        </div>
    );
};

export default CartaoForm;