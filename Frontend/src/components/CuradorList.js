import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import CuradorService from '../services/CuradorService';
import '../styles/Curador.css';

const CuradorList = () => {
    const [curadores, setCuradores] = useState([]);
    const [error, setError] = useState('');

    useEffect(() => {
        fetchCuradores();
    }, []);

    const fetchCuradores = async () => {
        try {
            const data = await CuradorService.fetchCuradores();
            setCuradores(data);
        } catch (error) {
            setError('Falha ao carregar curadores');
        }
    };

    const handleDelete = async (idCurador) => {
        try {
            await CuradorService.deleteCurador(idCurador);
            setCuradores(curadores.filter(curador => curador.idCurador !== idCurador));
        } catch (error) {
            setError('Falha ao deletar curador');
        }
    };

    return (
        <div className="entity-list">
            <h1>Lista de Curadores</h1>
            {error && <p className="error-message">{error}</p>}
            <Link to="/curadores/new" className="add-button">Adicionar Novo Curador</Link>
            <table>
                <thead>
                <tr>
                    <th>ID</th>
                    <th>CPF do Funcionário</th>
                    <th>Ações</th>
                </tr>
                </thead>
                <tbody>
                {curadores.map(curador => (
                    <tr key={curador.idCurador}>
                        <td>{curador.idCurador}</td>
                        <td>{curador.cpfFuncionario}</td>
                        <td>
                            <Link to={`/curadores/${curador.idCurador}/edit`} className="edit-button">Editar</Link>
                            <button onClick={() => handleDelete(curador.idCurador)} className="delete-button">Excluir</button>
                        </td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
};

export default CuradorList;