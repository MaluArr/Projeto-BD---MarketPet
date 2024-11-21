import React from "react";
import { Bar } from "react-chartjs-2";


import { Chart as ChartJS, CategoryScale, LinearScale, BarElement, Title, Tooltip, Legend, PointElement, LineElement, ArcElement, RadialLinearScale } from "chart.js";
ChartJS.register(CategoryScale, LinearScale, BarElement, PointElement, LineElement, Title, Tooltip, Legend, ArcElement, RadialLinearScale);




const GraficoTopVendedores = ({ dados }) => {
    const chartData = {
        labels: dados.map((item) => `Vendedor ${item.cpf_vendedor}`),
        datasets: [
            {
                label: "Total de Vendas (R$)",
                data: dados.map((item) => item.total_vendas),
                backgroundColor: "rgba(75, 192, 192, 0.6)",
            },
        ],
    };


    const options = {
        responsive: true,
        plugins: {
            legend: { position: "top" },
        },
        scales: {
            x: { title: { display: true, text: "Vendedores" } },
            y: { title: { display: true, text: "Total de Vendas (R$)" }, beginAtZero: true },
        },
    };


    return (
        <div className="grafico-container">
            <h2>Top Vendedores</h2>
            <Bar data={chartData} options={options} />
        </div>
    );
};


export default GraficoTopVendedores;
