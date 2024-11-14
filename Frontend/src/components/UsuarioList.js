import React, { useState, useEffect } from 'react';
import UsuarioService from '../services/UsuarioService';
import { useNavigate, useParams } from 'react-router-dom';
import '../styles/Usuario.css';

const UsuarioForm = () => {
    const [formData, setFormData] = useState({
        cpf: '',
        nomeReal: '',
        email: '',
        telefone1: '',
        senha: '',
        nomeUsuario: '',
    });
    const [error, setError] = useState('');
    const navigate = useNavigate();
    const { cpf } = useParams();

    useEffect(() => {
        if (cpf) {
            UsuarioService.getUsuarioByCpf(cpf).then(data => setFormData(data));
        }
    }, [cpf]);

    const handleChange = (e) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        setError('');
        try {
            if (cpf) {
                await UsuarioService.updateUsuario(cpf, formData);
            } else {
                await UsuarioService.createUsuario(formData);
            }
            navigate('/usuarios');
        } catch (error) {
            setError('Ocorreu um erro ao salvar o usuário. Por favor, tente novamente.');
        }
    };

    return (
        <div className="form-container">
            <h2>{cpf ? 'Editar Usuário' : 'Novo Usuário'}</h2>
            {error && <div className="error-message">{error}</div>}
            <form onSubmit={handleSubmit}>
                <input type="text" name="cpf" value={formData.cpf} onChange={handleChange} placeholder="CPF" required />
                <input type="text" name="nomeReal" value={formData.nomeReal} onChange={handleChange} placeholder="Nome Real" required />
                <input type="email" name="email" value={formData.email} onChange={handleChange} placeholder="Email" required />
                <input type="tel" name="telefone1" value={formData.telefone1} onChange={handleChange} placeholder="Telefone" required />
                <input type="text" name="nomeUsuario" value={formData.nomeUsuario} onChange={handleChange} placeholder="Nome de Usuário" required />
                <input type="password" name="senha" value={formData.senha} onChange={handleChange} placeholder="Senha" required={!cpf} />
                <button type="submit">{cpf ? 'Atualizar' : 'Criar'}</button>
                <button type="button" onClick={() => navigate('/usuarios')}>Cancelar</button>
            </form>
        </div>
    );
};

export default UsuarioForm;