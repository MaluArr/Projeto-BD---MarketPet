import React from "react";
import { Bar } from "react-chartjs-2";


import { Chart as ChartJS, CategoryScale, LinearScale, BarElement, Title, Tooltip, Legend, PointElement, LineElement, ArcElement, RadialLinearScale} from "chart.js";
ChartJS.register(CategoryScale, LinearScale, BarElement, PointElement, LineElement, Title, Tooltip, Legend, ArcElement, RadialLinearScale);




const GraficoTopProdutos = ({ dados }) => {
    const chartData = {
        labels: dados.map((item) => `Produto ${item.codigo_produto}`),
        datasets: [
            {
                label: "Total de Vendas (R$)",
                data: dados.map((item) => item.total_vendas),
                backgroundColor: "rgba(54, 162, 235, 0.6)",
            },
        ],
    };


    const options = {
        responsive: true,
        plugins: {
            legend: { position: "top" },
        },
        scales: {
            x: { title: { display: true, text: "Produtos" } },
            y: { title: { display: true, text: "Total de Vendas (R$)" }, beginAtZero: true },
        },
    };


    return (
        <div className="grafico-container">
            <h2>Top Produtos</h2>
            <Bar data={chartData} options={options} />
        </div>
    );
};


export default GraficoTopProdutos;


