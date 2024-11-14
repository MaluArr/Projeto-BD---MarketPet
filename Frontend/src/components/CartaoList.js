import React from 'react';
import '../styles/Cartao.css';

const CartaoList = ({ cartoes, onEdit, onDelete }) => {
    return (
        <div className="cartao-list">
            <h2>Lista de Cartões</h2>
            <table>
                <thead>
                <tr>
                    <th>Número</th>
                    <th>Nome do Titular</th>
                    <th>Validade</th>
                    <th>Bandeira</th>
                    <th>Ações</th>
                </tr>
                </thead>
                <tbody>
                {cartoes.map(cartao => (
                    <tr key={cartao.idCartao}>
                        <td>{cartao.numero.replace(/\d{12}/, '************')}</td>
                        <td>{cartao.nomeTitular}</td>
                        <td>{new Date(cartao.validade).toLocaleDateString()}</td>
                        <td>{cartao.bandeira}</td>
                        <td>
                            <button onClick={() => onEdit(cartao)}>Editar</button>
                            <button onClick={() => onDelete(cartao.idCartao)}>Excluir</button>
                        </td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
};

export default CartaoList;