import React, { useState, useEffect } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import VendedorService from '../services/VendedorService';
import '../styles/Vendedor.css';

const VendedorForm = () => {
    const [formData, setFormData] = useState({
        cpf: '',
        descricao: '',
        fotoPerfil: '',
        totalVendas: '',
        avaliacaoMedia: '',
        dataInicioVendas: ''
    });
    const [error, setError] = useState('');
    const navigate = useNavigate();
    const { cpf } = useParams();

    useEffect(() => {
        if (cpf) {
            fetchVendedor(cpf);
        }
    }, [cpf]);

    const fetchVendedor = async (cpf) => {
        try {
            const data = await VendedorService.getVendedorByCpf(cpf);
            setFormData(data);
        } catch (error) {
            setError('Falha ao carregar dados do vendedor');
        }
    };

    const handleChange = (e) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        setError('');
        try {
            if (cpf) {
                await VendedorService.updateVendedor(cpf, formData);
            } else {
                await VendedorService.createVendedor(formData);
            }
            navigate('/vendedores');
        } catch (error) {
            setError('Falha ao salvar vendedor');
        }
    };

    return (
        <div className="entity-form">
            <h2>{cpf ? 'Editar Vendedor' : 'Novo Vendedor'}</h2>
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
                    name="descricao"
                    value={formData.descricao}
                    onChange={handleChange}
                    placeholder="Descrição"
                    required
                />
                <input
                    type="text"
                    name="fotoPerfil"
                    value={formData.fotoPerfil}
                    onChange={handleChange}
                    placeholder="URL da Foto de Perfil"
                />
                <input
                    type="number"
                    name="totalVendas"
                    value={formData.totalVendas}
                    onChange={handleChange}
                    placeholder="Total de Vendas"
                    required
                    step="0.01"
                />
                <input
                    type="number"
                    name="avaliacaoMedia"
                    value={formData.avaliacaoMedia}
                    onChange={handleChange}
                    placeholder="Avaliação Média"
                    required
                    step="0.1"
                    min="0"
                    max="5"
                />
                <input
                    type="date"
                    name="dataInicioVendas"
                    value={formData.dataInicioVendas}
                    onChange={handleChange}
                    required
                />
                <button type="submit">{cpf ? 'Atualizar' : 'Criar'}</button>
                <button type="button" onClick={() => navigate('/vendedores')}>Cancelar</button>
            </form>
        </div>
    );
};

export default VendedorForm;