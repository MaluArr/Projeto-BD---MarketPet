import React, { useState } from 'react';
import '../styles/Curadoria.css';
import axios from 'axios';

const CuradoriaPage = () => {
    const [curadorias, setCuradorias] = useState([]); // Estado para armazenar as curadorias
    const [error, setError] = useState(null); // Estado para erros

    const fetchCuradorias = () => {
        axios.get('http://localhost:8080/api/curadorias')
            .then(response => {
                setCuradorias(response.data); // Atualiza o estado com as curadorias
                setError(null); // Limpa qualquer erro anterior
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
                <button onClick={() => window.location.href = '/curadorias/list'} className="action-button">
                    Gerenciamento Curadoria
                </button>
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
