// src/pages/CartaoPage.js
import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import CartaoList from '../components/CartaoList';
import CartaoForm from '../components/CartaoForm';
import CartaoService from '../services/CartaoService';
import '../styles/Cartao.css';
import '../styles/Entity.css';

const CartaoPage = () => {
    const [cartoes, setCartoes] = useState([]);
    const [selectedCartao, setSelectedCartao] = useState(null);
    const [isFormVisible, setIsFormVisible] = useState(false);

    useEffect(() => {
        fetchCartoes();
    }, []);

    const fetchCartoes = () => {
        CartaoService.getCartoes()
            .then(response => {
                setCartoes(response.data);
            })
            .catch(error => {
                console.error('Erro ao buscar cartões:', error);
            });
    };

    const handleAddNew = () => {
        setSelectedCartao(null);
        setIsFormVisible(true);
    };

    const handleEdit = (cartao) => {
        setSelectedCartao(cartao);
        setIsFormVisible(true);
    };

    const handleDelete = (id) => {
        CartaoService.deleteCartao(id)
            .then(() => {
                fetchCartoes();
            })
            .catch(error => {
                console.error('Erro ao deletar cartão:', error);
            });
    };

    const handleSave = (cartao) => {
        const saveOperation = cartao.idCartao
            ? CartaoService.updateCartao(cartao.idCartao, cartao)
            : CartaoService.createCartao(cartao);

        saveOperation
            .then(() => {
                fetchCartoes();
                setIsFormVisible(false);
            })
            .catch(error => {
                console.error('Erro ao salvar cartão:', error);
            });
    };

    return (
        <div className="entity-page">
            <h1>Gerenciamento de Cartões</h1>
            <div className="action-buttons">
                <button onClick={handleAddNew} className="action-button">Adicionar Novo Cartão</button>
                <Link to="/cartoes/list" className="action-button">Listar Cartões</Link>
            </div>

            {isFormVisible && (
                <CartaoForm
                    cartao={selectedCartao}
                    onSave={handleSave}
                    onCancel={() => setIsFormVisible(false)}
                />
            )}

            <CartaoList
                cartoes={cartoes}
                onEdit={handleEdit}
                onDelete={handleDelete}
            />
        </div>
    );
};

export default CartaoPage;