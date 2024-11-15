import React, { useState, useEffect } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import ChatService from '../services/ChatService';
import '../styles/Chat.css';

const ChatForm = () => {
    const [formData, setFormData] = useState({
        idChat: '',
        cpfVendedor: '',
        cpfComprador: '',
        codigoChatvc: '',
        conteudo: '',
        dataCriacao: '',
        ultimaAtualizacao: ''
    });
    const [error, setError] = useState('');
    const navigate = useNavigate();
    const { idChat } = useParams();

    useEffect(() => {
        if (idChat) {
            fetchChat(idChat);
        }
    }, [idChat]);

    const fetchChat = async (idChat) => {
        try {
            const data = await ChatService.getChatById(idChat);
            setFormData(data);
        } catch (error) {
            setError('Falha ao carregar dados do chat');
        }
    };

    const handleChange = (e) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        setError('');
        try {
            if (idChat) {
                await ChatService.updateChat(idChat, formData);
            } else {
                await ChatService.createChat(formData);
            }
            navigate('/chats');
        } catch (error) {
            setError('Falha ao salvar chat');
        }
    };

    return (
        <div className="entity-form">
            <h2>{idChat ? 'Editar Chat' : 'Novo Chat'}</h2>
            {error && <p className="error-message">{error}</p>}
            <form onSubmit={handleSubmit}>
                <input
                    type="text"
                    name="cpfVendedor"
                    value={formData.cpfVendedor}
                    onChange={handleChange}
                    placeholder="CPF do Vendedor"
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
                <input
                    type="text"
                    name="codigoChatvc"
                    value={formData.codigoChatvc}
                    onChange={handleChange}
                    placeholder="Código do Chat"
                    required
                />
                <textarea
                    name="conteudo"
                    value={formData.conteudo}
                    onChange={handleChange}
                    placeholder="Conteúdo"
                    required
                />
                <button type="submit">{idChat ? 'Atualizar' : 'Criar'}</button>
                <button type="button" onClick={() => navigate('/chats')}>Cancelar</button>
            </form>
        </div>
    );
};

export default ChatForm;