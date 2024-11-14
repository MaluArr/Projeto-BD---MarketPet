import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useNavigate, useParams } from 'react-router-dom';
import '../styles/Vendedor.css';

const VendedorForm = () => {
    const [vendedor, setVendedor] = useState({
        cpf: '',
        descricao: '',
        fotoPerfil: '',
        totalVendas: '',
        avaliacaoMedia: '',
        dataInicioVendas: ''
    });
    const navigate = useNavigate();
    const { cpf } = useParams();

    useEffect(() => {
        if (cpf) {
            const fetchVendedor = async () => {
                const response = await axios.get(`/api/vendedores/${cpf}`);
                setVendedor(response.data);
            };
            fetchVendedor();
        }
    }, [cpf]);

    const handleChange = (e) => {
        setVendedor({ ...vendedor, [e.target.name]: e.target.value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        if (cpf) {
            await axios.put(`/api/vendedores/${cpf}`, vendedor);
        } else {
            await axios.post('/api/vendedores', vendedor);
        }
        navigate('/vendedores');
    };

    return (
        <form onSubmit={handleSubmit}>
            <h2>{cpf ? 'Editar' : 'Criar'} Vendedor</h2>
            <div>
                <label>CPF:</label>
                <input
                    type="text"
                    name="cpf"
                    value={vendedor.cpf}
                    onChange={handleChange}
                    required
                />
            </div>
            <div>
                <label>Descrição:</label>
                <input
                    type="text"
                    name="descricao"
                    value={vendedor.descricao}
                    onChange={handleChange}
                />
            </div>
            <div>
                <label>Foto de Perfil:</label>
                <input
                    type="text"
                    name="fotoPerfil"
                    value={vendedor.fotoPerfil}
                    onChange={handleChange}
                />
            </div>
            <div>
                <label>Total de Vendas:</label>
                <input
                    type="number"
                    name="totalVendas"
                    value={vendedor.totalVendas}
                    onChange={handleChange}
                    required
                />
            </div>
            <div>
                <label>Avaliação Média:</label>
                <input
                    type="number"
                    name="avaliacaoMedia"
                    value={vendedor.avaliacaoMedia}
                    onChange={handleChange}
                    required
                />
            </div>
            <div>
                <label>Data de Início das Vendas:</label>
                <input
                    type="date"
                    name="dataInicioVendas"
                    value={vendedor.dataInicioVendas}
                    onChange={handleChange}
                    required
                />
            </div>
            <button type="submit">Salvar</button>
        </form>
    );
};

export default VendedorForm;