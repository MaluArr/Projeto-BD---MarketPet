import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import CuradoriaService from '../services/CuradoriaService';
import '../styles/Curadoria.css';

const CuradoriaList = () => {
    const [curadorias, setCuradorias] = useState([]);
    const [error, setError] = useState('');

    useEffect(() => {
        fetchCuradorias();
    }, []);

    const fetchCuradorias = async () => {
        try {
            const data = await CuradoriaService.fetchCuradorias();
            setCuradorias(data);
        } catch (error) {
            setError('Falha ao carregar curadorias');
        }
    };

    const handleDelete = async (codigoCuradoria) => {
        try {
            await CuradoriaService.deleteCuradoria(codigoCuradoria);
            setCuradorias(curadorias.filter(curadoria => curadoria.codigoCuradoria !== codigoCuradoria));
        } catch (error) {
            setError('Falha ao deletar curadoria');
        }
    };

    return (
        <div className="entity-list">
            <h1>Lista de Curadorias</h1>
            {error && <p className="error-message">{error}</p>}
            <Link to="/curadorias/new" className="add-button">Adicionar Nova Curadoria</Link>
            <table>
                <thead>
                <tr>
                    <th>Código</th>
                    <th>Descrição</th>
                    <th>Resultado</th>
                    <th>ID Curador</th>
                    <th>Código Produto</th>
                    <th>Ações</th>
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
                        <td>
                            <Link to={`/curadorias/${curadoria.codigoCuradoria}/edit`} className="edit-button">Editar</Link>
                            <button onClick={() => handleDelete(curadoria.codigoCuradoria)} className="delete-button">Excluir</button>
                        </td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
};

export default CuradoriaList;