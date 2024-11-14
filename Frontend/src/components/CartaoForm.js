import React, { useState, useEffect } from 'react';
import '../styles/Cartao.css';

const CartaoForm = ({ cartao, onSave, onCancel }) => {
    const [formData, setFormData] = useState({
        idCartao: '',
        numero: '',
        nomeTitular: '',
        validade: '',
        cvv: '',
        bandeira: ''
    });

    useEffect(() => {
        if (cartao) {
            setFormData({
                ...cartao,
                validade: cartao.validade ? cartao.validade.split('T')[0] : ''
            });
        }
    }, [cartao]);

    const handleChange = (e) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        onSave(formData);
    };

    return (
        <form onSubmit={handleSubmit} className="cartao-form">
            <h2>{formData.idCartao ? 'Editar Cartão' : 'Novo Cartão'}</h2>
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
            <button type="submit">{formData.idCartao ? 'Atualizar' : 'Criar'}</button>
            <button type="button" onClick={onCancel}>Cancelar</button>
        </form>
    );
};

export default CartaoForm;