import React, { useState, useEffect } from 'react';
import UsuarioService from '../services/UsuarioService';
import '../styles/Usuario.css';

const UsuarioForm = ({ usuario, onSave }) => {
    const [formData, setFormData] = useState({
        cpf: '',
        nomeReal: '',
        email: '',
        telefone1: '',
        senha: '',
        nomeUsuario: '',
    });
    const [error, setError] = useState('');

    useEffect(() => {
        if (usuario) {
            setFormData({
                ...usuario,
                senha: '' // Por segurança, não preenchemos a senha ao editar
            });
        }
    }, [usuario]);

    const handleChange = (e) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        setError('');
        try {
            if (formData.cpf) {
                await UsuarioService.updateUsuario(formData.cpf, formData);
            } else {
                await UsuarioService.createUsuario(formData);
            }
            onSave();
            resetForm();
        } catch (error) {
            console.error('Erro ao salvar usuário:', error);
            setError('Ocorreu um erro ao salvar o usuário. Por favor, tente novamente.');
        }
    };

    const resetForm = () => {
        setFormData({
            cpf: '',
            nomeReal: '',
            email: '',
            telefone1: '',
            senha: '',
            nomeUsuario: '',
        });
    };

    return (
        <form onSubmit={handleSubmit} className="usuario-form">
            <h2>{formData.cpf ? 'Editar Usuário' : 'Novo Usuário'}</h2>
            {error && <div className="error-message">{error}</div>}
            <input
                type="text"
                name="cpf"
                value={formData.cpf}
                onChange={handleChange}
                placeholder="CPF"
                required
            />
            <input
                type="text"
                name="nomeReal"
                value={formData.nomeReal}
                onChange={handleChange}
                placeholder="Nome Real"
                required
            />
            <input
                type="email"
                name="email"
                value={formData.email}
                onChange={handleChange}
                placeholder="Email"
                required
            />
            <input
                type="tel"
                name="telefone1"
                value={formData.telefone1}
                onChange={handleChange}
                placeholder="Telefone"
                required
            />
            <input
                type="text"
                name="nomeUsuario"
                value={formData.nomeUsuario}
                onChange={handleChange}
                placeholder="Nome de Usuário"
                required
            />
            <input
                type="password"
                name="senha"
                value={formData.senha}
                onChange={handleChange}
                placeholder={formData.cpf ? "Nova Senha (deixe em branco para não alterar)" : "Senha"}
                required={!formData.cpf}
            />
            <button type="submit">{formData.cpf ? 'Atualizar' : 'Criar'}</button>
            <button type="button" onClick={resetForm}>Cancelar</button>
        </form>
    );
};

export default UsuarioForm;