import React, { useState } from 'react';
import axios from 'axios';

const VendedorReports = () => {
    const [avaliacaoMinima, setAvaliacaoMinima] = useState('');
    const [vendasMinimas, setVendasMinimas] = useState('');
    const [resultados, setResultados] = useState([]);

    const handleAvaliacaoReport = async () => {
        const response = await axios.get(`/api/vendedores/avaliacao-superior/${avaliacaoMinima}`);
        setResultados(response.data);
    };

    const handleVendasReport = async () => {
        const response = await axios.get(`/api/vendedores/vendas-superior/${vendasMinimas}`);
        setResultados(response.data);
    };

    return (
        <div>
            <h2>Relatórios de Vendedores</h2>
            <div>
                <h3>Vendedores por Avaliação Mínima</h3>
                <input
                    type="number"
                    value={avaliacaoMinima}
                    onChange={(e) => setAvaliacaoMinima(e.target.value)}
                    placeholder="Avaliação mínima"
                />
                <button onClick={handleAvaliacaoReport}>Gerar Relatório</button>
            </div>
            <div>
                <h3>Vendedores por Total de Vendas Mínimo</h3>
                <input
                    type="number"
                    value={vendasMinimas}
                    onChange={(e) => setVendasMinimas(e.target.value)}
                    placeholder="Vendas mínimas"
                />
                <button onClick={handleVendasReport}>Gerar Relatório</button>
            </div>
            <div>
                <h3>Resultados</h3>
                <table>
                    <thead>
                    <tr>
                        <th>CPF</th>
                        <th>Descrição</th>
                        <th>Total Vendas</th>
                        <th>Avaliação Média</th>
                    </tr>
                    </thead>
                    <tbody>
                    {resultados.map((vendedor) => (
                        <tr key={vendedor.cpf}>
                            <td>{vendedor.cpf}</td>
                            <td>{vendedor.descricao}</td>
                            <td>{vendedor.totalVendas}</td>
                            <td>{vendedor.avaliacaoMedia}</td>
                        </tr>
                    ))}
                    </tbody>
                </table>
            </div>
        </div>
    );
};

export default VendedorReports;