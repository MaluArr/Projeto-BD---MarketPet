// src/pages/HomePage.js
import React from 'react';
import { Link } from 'react-router-dom';
import '../styles/Home.css';

const HomePage = () => {
    return (
        <div className="home-container">
            <h1>Bem-vindo ao Gerenciador do Banco de Dados MarketPet</h1>
            <p className="slogan"> Gerenciando o marketplace de produtos pet de segunda mão!</p>
            <h2>Escolha uma entidade para gerenciar:</h2>
            <div className="entity-links">
                <Link to="/usuarios" className="entity-button">Usuários</Link>
                <Link to="/cartoes" className="entity-button">Cartões</Link>
                <Link to="/enderecos" className="entity-button">Endereços</Link>
                <Link to="/vendedores" className="entity-button">Vendedores</Link>
                <Link to="/compradores" className="entity-button">Compradores</Link>
                <Link to="/chats" className="entity-button">Chats</Link>
                <Link to="/produtos" className="entity-button">Produtos</Link>
                <Link to="/funcionarios" className="entity-button">Funcionários</Link>
                <Link to="/curadores" className="entity-button">Curadores</Link>
                <Link to="/curadorias" className="entity-button">Curadorias</Link>
                <Link to="/atendentes" className="entity-button">Atendentes</Link>
                <Link to="/atendimentos" className="entity-button">Atendimentos</Link>
                <Link to="/buscar-produto" className="entity-button">Buscar Produto</Link>
                <Link to="/favoritar-produtos" className="entity-button">Produtos Favoritos</Link>
                <Link to="/carrinhos" className="entity-button">Carrinhos</Link>
                <Link to="/adicionar-produto-carrinho" className="entity-button">Adicionar Produto ao Carrinho</Link>
                <Link to="/vendas" className="entity-button">Vendas</Link>
                <Link to="/avaliacoes" className="entity-button">Avaliações</Link>
            </div>
        </div>
    );
};

export default HomePage;