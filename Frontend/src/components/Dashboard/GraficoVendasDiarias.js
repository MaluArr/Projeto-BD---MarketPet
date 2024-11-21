import React from "react";
import { Line } from "react-chartjs-2";


import { Chart as ChartJS, CategoryScale, LinearScale, BarElement, Title, Tooltip, Legend, PointElement, LineElement, ArcElement, RadialLinearScale } from "chart.js";
ChartJS.register(CategoryScale, LinearScale, BarElement, PointElement, LineElement, Title, Tooltip, Legend, ArcElement, RadialLinearScale);




const GraficoVendasDiarias = ({ dados }) => {
    const chartData = {
        labels: dados.map((item) => item.data),
        datasets: [
            {
                label: "Total de Vendas (R$)",
                data: dados.map((item) => item.total_vendas),
                borderColor: "rgba(75, 192, 192, 1)",
                backgroundColor: "rgba(75, 192, 192, 0.2)",
                fill: true,
                tension: 0.3,
            },
        ],
    };


    const options = {
        responsive: true,
        plugins: {
            legend: {
                position: "top",
            },
        },
        scales: {
            x: {
                title: {
                    display: true,
                    text: "Data",
                },
            },
            y: {
                title: {
                    display: true,
                    text: "Total de Vendas (R$)",
                },
                beginAtZero: true,
            },
        },
    };


    return (
        <div className="grafico-container">
            <h2>Vendas Diárias</h2>
            <Line data={chartData} options={options} />
        </div>
    );
};


export default GraficoVendasDiarias;
