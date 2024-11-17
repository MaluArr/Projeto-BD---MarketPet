import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import '../styles/Curador.css';
import axios from 'axios';

const CuradorPage = () => {
    const [curadores, setCuradores] = useState([]); // Estado para armazenar os curadores
    const [error, setError] = useState(null); // Estado para erros

    const fetchCuradores = () => {
        axios.get('http://localhost:8080/api/curadores')
            .then(response => {
                // Ajusta os dados recebidos para o formato esperado
                const curadoresFormatados = response.data.map(curador => ({
                    idCurador: curador.idCurador || curador.id_curador,
                    cpfFuncionario: curador.cpfFuncionario || curador.cpf_funcionario,
                }));
                setCuradores(curadoresFormatados);
                setError(null);
            })
            .catch(err => {
                setError('Erro ao buscar curadores');
                console.error('Erro ao buscar curadores:', err);
            });
    };

    return (
        <div className="entity-page">
            <h1>Gerenciamento de Curadores</h1>
            <div className="action-buttons">
                <button onClick={fetchCuradores}>Listar Curadores</button>
                <Link to="/curadores/new" className="action-button">
                    Adicionar Novo Curador
                </Link>
            </div>

            {error && <p className="error-message">{error}</p>}

            <div className="curador-list">
                {curadores.length > 0 && (
                    <table className="curador-table">
                        <thead>
                        <tr>
                            <th>ID do Curador</th>
                            <th>CPF do Funcionário</th>
                        </tr>
                        </thead>
                        <tbody>
                        {curadores.map(curador => (
                            <tr key={curador.idCurador}>
                                <td>{curador.idCurador}</td>
                                <td>{curador.cpfFuncionario}</td>
                            </tr>
                        ))}
                        </tbody>
                    </table>
                )}
            </div>
        </div>
    );
};

export default CuradorPage;
