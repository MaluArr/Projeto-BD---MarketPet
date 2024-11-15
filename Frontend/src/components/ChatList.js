import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import ChatService from '../services/ChatService';
import '../styles/Chat.css';

const ChatList = () => {
    const [chats, setChats] = useState([]);
    const [error, setError] = useState('');

    useEffect(() => {
        fetchChats();
    }, []);

    const fetchChats = async () => {
        try {
            const data = await ChatService.fetchChats();
            setChats(data);
        } catch (error) {
            setError('Falha ao carregar chats');
        }
    };

    const handleDelete = async (idChat) => {
        try {
            await ChatService.deleteChat(idChat);
            setChats(chats.filter(chat => chat.idChat !== idChat));
        } catch (error) {
            setError('Falha ao deletar chat');
        }
    };

    return (
        <div className="entity-list">
            <h1>Lista de Chats</h1>
            {error && <p className="error-message">{error}</p>}
            <Link to="/chats/new" className="add-button">Adicionar Novo Chat</Link>
            <table>
                <thead>
                <tr>
                    <th>ID</th>
                    <th>CPF Vendedor</th>
                    <th>CPF Comprador</th>
                    <th>Código Chat</th>
                    <th>Data Criação</th>
                    <th>Ações</th>
                </tr>
                </thead>
                <tbody>
                {chats.map(chat => (
                    <tr key={chat.idChat}>
                        <td>{chat.idChat}</td>
                        <td>{chat.cpfVendedor}</td>
                        <td>{chat.cpfComprador}</td>
                        <td>{chat.codigoChatvc}</td>
                        <td>{new Date(chat.dataCriacao).toLocaleDateString()}</td>
                        <td>
                            <Link to={`/chats/${chat.idChat}/edit`} className="edit-button">Editar</Link>
                            <button onClick={() => handleDelete(chat.idChat)} className="delete-button">Excluir</button>
                        </td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
};

export default ChatList;