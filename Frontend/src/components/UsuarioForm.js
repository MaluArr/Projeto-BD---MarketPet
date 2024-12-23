import React, { useState, useEffect } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import UsuarioService from '../services/UsuarioService';
import '../styles/Usuario.css';

const UsuarioForm = () => {
    const [formData, setFormData] = useState({
        cpf: '',
        nomeReal: '',
        email: '',
        telefone1: '',
        telefone2: '',
        senha: '',
        nomeUsuario: '',
    });
    const [error, setError] = useState('');
    const navigate = useNavigate();
    const { cpf } = useParams();

    useEffect(() => {
        if (cpf) {
            fetchUsuario(cpf);
        }
    }, [cpf]);

    const fetchUsuario = async (cpf) => {
        try {
            const data = await UsuarioService.getUsuarioByCpf(cpf);
            setFormData(data);
        } catch (error) {
            console.log(error)
            setError('Falha ao carregar dados do usuário');
        }
    };

    const handleChange = (e) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        setError('');
        const preparedData = {
            ...formData,
            cpf: parseInt(formData.cpf, 10) || '',           // Convertendo para número ou mantendo como string vazia
            telefone1: parseInt(formData.telefone1, 10) || '', // Convertendo para número ou mantendo como string vazia
            telefone2: parseInt(formData.telefone2, 10) || '', // Convertendo para número ou mantendo como string vazia
            nomeReal: formData.nomeReal.trim(),              // Garantindo que seja uma string sem espaços desnecessários
            email: formData.email.trim(),                    // Garantindo que seja uma string sem espaços desnecessários
            senha: formData.senha.trim(),                    // Garantindo que seja uma string sem espaços desnecessários
            nomeUsuario: formData.nomeUsuario.trim()         // Garantindo que seja uma string sem espaços desnecessários
        };
        console.log(preparedData)

        try {
            if (cpf) {
                await UsuarioService.updateUsuario(cpf, preparedData);
            } else {
                await UsuarioService.createUsuario(preparedData);
            }
            navigate('/usuarios');
        } catch (error) {
            setError('Falha ao salvar usuário');
        }
    };

    return (
        <div className="entity-form">
            <h2>{cpf ? 'Editar Usuário' : 'Novo Usuário'}</h2>
            {error && <p className="error-message">{error}</p>}
            <form onSubmit={handleSubmit}>
                <input
                    type="text"
                    name="cpf"
                    value={formData.cpf}
                    onChange={handleChange}
                    placeholder="CPF"
                    required
                    readOnly={!!cpf}
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
                    type="tel"
                    name="telefone2"
                    value={formData.telefone2}
                    onChange={handleChange}
                    placeholder="Telefone2"
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
                    placeholder={cpf ? "Nova Senha (deixe em branco para não alterar)" : "Senha"}
                    required={!cpf}
                />
                <button type="submit">{cpf ? 'Atualizar' : 'Criar'}</button>
                <button type="button" onClick={() => navigate('/usuarios')}>Cancelar</button>
            </form>
        </div>
    );
};

export default UsuarioForm;