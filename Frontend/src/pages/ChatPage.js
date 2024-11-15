// src/pages/ChatPage.js
import React from 'react';
import { Link } from 'react-router-dom';
import '../styles/Chat.css';

const ChatPage = () => {
    return (
        <div className="entity-page">
            <h1>Gerenciamento de Chats</h1>
            <div className="action-buttons">
                <Link to="/chats" className="action-button">
                    Listar Chats
                </Link>
                <Link to="/chats/new" className="action-button">
                    Adicionar Novo Chat
                </Link>
            </div>
        </div>
    );
};

export default ChatPage;