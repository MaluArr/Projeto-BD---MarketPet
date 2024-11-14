import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useParams, useNavigate, Link } from 'react-router-dom';
import '../styles/Vendedor.css';

const VendedorDetails = () => {
    const [vendedor, setVendedor] = useState(null);
    const { cpf } = useParams();
    const navigate = useNavigate();

    useEffect(() => {
        const fetchVendedor = async () => {
            const response = await axios.get(`/api/vendedores/${cpf}`);
            setVendedor(response.data);
        };
        fetchVendedor();
    }, [cpf]);

    const handleDelete = async () => {
        if (window.confirm('Tem certeza que deseja deletar este vendedor?')) {
            await axios.delete(`/api/vendedores/${cpf}`);
            navigate('/vendedores');
        }
    };

    if (!vendedor) return <div>Carregando...</div>;

    return (
        <div>
            <h2>Detalhes do Vendedor</h2>
            <p><strong>CPF:</strong> {vendedor.cpf}</p>
            <p><strong>Descrição:</strong> {vendedor.descricao}</p>
            <p><strong>Foto de Perfil:</strong> {vendedor.fotoPerfil}</p>
            <p><strong>Total de Vendas:</strong> {vendedor.totalVendas}</p>
            <p><strong>Avaliação Média:</strong> {vendedor.avaliacaoMedia}</p>
            <p><strong>Data de Início das Vendas:</strong> {vendedor.dataInicioVendas}</p>
            <Link to={`/vendedores/${cpf}/edit`}>Editar</Link>
            <button onClick={handleDelete}>Deletar</button>
            <Link to="/vendedores">Voltar para a lista</Link>
        </div>
    );
};

export default VendedorDetails;