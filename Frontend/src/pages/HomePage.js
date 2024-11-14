// src/components/HomePage.js
import React from 'react';
import { Link } from 'react-router-dom';
import '../styles/Home.css';

const HomePage = () => {
    return (
        <div className="home-container">
            <h1>Bem-vindo ao MarketPet</h1>
            <p className="slogan">Seu marketplace de produtos pet de segunda mão!</p>
            <h2>Escolha uma entidade para manipular:</h2>
            <div className="entity-links">
                <Link to="/usuarios" className="entity-button">Usuários</Link>
                <Link to="/cartoes" className="entity-button">Cartões</Link>
                <Link to="/enderecos" className="entity-button">Endereços</Link>
                <Link to="/vendedores" className="entity-button">Vendedores</Link>
                <Link to="/compradores" className="entity-button">Compradores</Link>
            </div>
        </div>
    );
};

export default HomePage;