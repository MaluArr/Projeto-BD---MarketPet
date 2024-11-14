// src/components/EntityPage.js
import React from 'react';
import { Link } from 'react-router-dom';
import '../styles/Entity.css';

const EntityPage = ({ title, links }) => {
    return (
        <div className="entity-page">
            <h1>Gerenciamento de {title}</h1>
            <div className="action-buttons">
                {links.map((link, index) => (
                    <Link key={index} to={link.path} className="action-button">
                        {link.label}
                    </Link>
                ))}
            </div>
        </div>
    );
};

export default EntityPage;