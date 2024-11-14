import React, { useEffect, useState } from 'react';
import UsuarioService from '../services/UsuarioService';
import '../styles/Usuario.css';

const UsuarioDetails = ({ cpf }) => {
    const [usuario, setUsuario] = useState(null);
    const [error, setError] = useState(null);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        const fetchUsuario = async () => {
            try {
                setLoading(true);
                setError(null);
                const data = await UsuarioService.getUsuarioByCpf(cpf);
                setUsuario(data);
            } catch (error) {
                console.error('Erro ao buscar detalhes do usuário:', error);
                setError('Não foi possível carregar os detalhes do usuário. Por favor, tente novamente mais tarde.');
            } finally {
                setLoading(false);
            }
        };

        fetchUsuario();
    }, [cpf]);

    if (loading) return <div>Carregando...</div>;

    if (error) return <div className="error-message">{error}</div>;

    if (!usuario) return <div>Nenhum usuário encontrado com este CPF.</div>;

    return (
        <div className="usuario-details">
            <h2>Detalhes do Usuário</h2>
            <p><strong>Nome:</strong> {usuario.nomeReal}</p>
            <p><strong>Email:</strong> {usuario.email}</p>
            <p><strong>Telefone:</strong> {usuario.telefone1}</p>
        </div>
    );
};

export default UsuarioDetails;