import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import '../styles/Chat.css';
import axios from 'axios';

const ChatPage = () => {
    const [chats, setChats] = useState([]); // Estado para armazenar os chats
    const [error, setError] = useState(null); // Estado para erros

    const fetchChats = () => {
        axios.get('http://localhost:8080/api/chats')
            .then(response => {
                // Ajusta os dados recebidos para o formato esperado
                const chatsFormatados = response.data.map(chat => ({
                    idChat: chat.idChat || chat.id_chat,
                    cpfVendedor: chat.cpfVendedor || chat.cpf_vendedor,
                    cpfComprador: chat.cpfComprador || chat.cpf_comprador,
                    codigoChatVC: chat.codigoChatVC || chat.codigo_chatvc,
                    conteudo: chat.conteudo,
                    dataCriacao: chat.dataCriacao || chat.data_criacao,
                    ultimaAtualizacao: chat.ultimaAtualizacao || chat.ultima_atualizacao,
                }));
                setChats(chatsFormatados);
                setError(null);
            })
            .catch(err => {
                setError('Erro ao buscar chats');
                console.error('Erro ao buscar chats:', err);
            });
    };

    return (
        <div className="entity-page">
            <h1>Gerenciamento de Chats</h1>
            <div className="action-buttons">
                <button onClick={fetchChats}>Listar Chats</button>
                <Link to="/chats/new" className="action-button">
                    Adicionar Novo Chat
                </Link>
            </div>

            {error && <p className="error-message">{error}</p>}

            <div className="chat-list">
                {chats.length > 0 && (
                    <table className="chat-table">
                        <thead>
                        <tr>
                            <th>ID do Chat</th>
                            <th>CPF do Vendedor</th>
                            <th>CPF do Comprador</th>
                            <th>Código do Chat VC</th>
                            <th>Conteúdo</th>
                            <th>Data de Criação</th>
                            <th>Última Atualização</th>
                        </tr>
                        </thead>
                        <tbody>
                        {chats.map(chat => (
                            <tr key={chat.idChat}>
                                <td>{chat.idChat}</td>
                                <td>{chat.cpfVendedor}</td>
                                <td>{chat.cpfComprador}</td>
                                <td>{chat.codigoChatVC}</td>
                                <td>{chat.conteudo}</td>
                                <td>
                                    {chat.dataCriacao
                                        ? new Date(chat.dataCriacao).toLocaleDateString()
                                        : "N/A"}
                                </td>
                                <td>
                                    {chat.ultimaAtualizacao
                                        ? new Date(chat.ultimaAtualizacao).toLocaleDateString()
                                        : "N/A"}
                                </td>
                            </tr>
                        ))}
                        </tbody>
                    </table>
                )}
            </div>
        </div>
    );
};

export default ChatPage;
