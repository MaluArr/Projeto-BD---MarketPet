import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import '../styles/Curadoria.css';
import axios from 'axios';

const CuradoriaPage = () => {
    const [curadorias, setCuradorias] = useState([]); // Estado para armazenar as curadorias
    const [error, setError] = useState(null); // Estado para erros

    const fetchCuradorias = () => {
        axios.get('http://localhost:8080/api/curadorias')
            .then(response => {
                // Ajusta os dados recebidos para o formato esperado
                const curadoriasFormatadas = response.data.map(curadoria => ({
                    codigoCuradoria: curadoria.codigoCuradoria || curadoria.codigo_curadoria,
                    descricao: curadoria.descricao,
                    resultadoCuradoria: curadoria.resultadoCuradoria || curadoria.resultado_curadoria,
                    idCurador: curadoria.idCurador || curadoria.id_curador,
                    codigoProduto: curadoria.codigoProduto || curadoria.codigo_produto,
                }));
                setCuradorias(curadoriasFormatadas);
                setError(null);
            })
            .catch(err => {
                setError('Erro ao buscar curadorias');
                console.error('Erro ao buscar curadorias:', err);
            });
    };

    return (
        <div className="entity-page">
            <h1>Gerenciamento de Curadorias</h1>
            <div className="action-buttons">
                <button onClick={fetchCuradorias}>Listar Curadorias</button>
                <Link to="/curadorias/new" className="action-button">
                    Adicionar Nova Curadoria
                </Link>
            </div>

            {error && <p className="error-message">{error}</p>}

            <div className="curadoria-list">
                {curadorias.length > 0 && (
                    <table className="curadoria-table">
                        <thead>
                        <tr>
                            <th>Código da Curadoria</th>
                            <th>Descrição</th>
                            <th>Resultado</th>
                            <th>ID do Curador</th>
                            <th>Código do Produto</th>
                        </tr>
                        </thead>
                        <tbody>
                        {curadorias.map(curadoria => (
                            <tr key={curadoria.codigoCuradoria}>
                                <td>{curadoria.codigoCuradoria}</td>
                                <td>{curadoria.descricao}</td>
                                <td>{curadoria.resultadoCuradoria}</td>
                                <td>{curadoria.idCurador}</td>
                                <td>{curadoria.codigoProduto}</td>
                            </tr>
                        ))}
                        </tbody>
                    </table>
                )}
            </div>
        </div>
    );
};

export default CuradoriaPage;

