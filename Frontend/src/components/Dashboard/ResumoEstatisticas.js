import React from "react";


import { Chart as ChartJS, CategoryScale, LinearScale, BarElement, Title, Tooltip, Legend, PointElement, LineElement, ArcElement, RadialLinearScale } from "chart.js";
ChartJS.register(CategoryScale, LinearScale, BarElement, PointElement, LineElement, Title, Tooltip, Legend, ArcElement, RadialLinearScale);




const ResumoEstatisticas = ({ resumo }) => {
    if (!resumo) {
        return (
            <div className="resumo-estatisticas">
                <p>Carregando dados...</p>
            </div>
        );
    }


    return (
        <div className="resumo-estatisticas">
            <div className="cartao-resumo">
                <h3>Total de Vendas</h3>
                <p>R$ {resumo.totalVendas || "Não disponível"}</p>
            </div>
            <div className="cartao-resumo">
                <h3>Quantidade de Vendas</h3>
                <p>{resumo.quantidadeVendas || "Não disponível"}</p>
            </div>
            <div className="cartao-resumo">
                <h3>Avaliação Média</h3>
                <p>{resumo.avaliacaoMedia || "Não disponível"}</p>
            </div>
        </div>
    );
};


export default ResumoEstatisticas;
