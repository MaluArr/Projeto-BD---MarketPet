import React, { useRef, useEffect } from "react";
import { Chart } from "chart.js";
import {
    Chart as ChartJS,
    CategoryScale,
    LinearScale,
    BarElement,
    Title,
    Tooltip,
    Legend,
    PointElement,
    LineElement,
    ArcElement,
    RadialLinearScale,
} from "chart.js";


ChartJS.register(
    CategoryScale,
    LinearScale,
    BarElement,
    PointElement,
    LineElement,
    Title,
    Tooltip,
    Legend,
    ArcElement,
    RadialLinearScale
);


const GraficoQuantidadeVendasXAvaliacao = ({ dados }) => {
    const chartRef = useRef(null);
    const chartInstanceRef = useRef(null);


    useEffect(() => {
        const ctx = chartRef.current.getContext("2d");


        // Destroi o gráfico anterior, se já existir
        if (chartInstanceRef.current) {
            chartInstanceRef.current.destroy();
        }


        // Cria um novo gráfico com os dados corrigidos
        chartInstanceRef.current = new Chart(ctx, {
            type: "scatter",
            data: {
                datasets: dados.map((item, index) => ({
                    label: `Produto ${item.codigo_produto || `Item ${index + 1}`}`,
                    data: [{ x: item.quantidade_vendas, y: item.avaliacao_media }],
                    backgroundColor: `rgba(${(index * 40) % 255}, 99, 132, 0.6)`,
                })),
            },
            options: {
                responsive: true,
                maintainAspectRatio: false, // Permite ajustar ao container
                plugins: {
                    legend: { position: "top" },
                    tooltip: {
                        callbacks: {
                            label: (context) => {
                                const label = context.label || "Produto";
                                const xValue = context.raw.x || 0;
                                const yValue = context.raw.y || 0;
                                return `${label}: Quantidade ${xValue}, Avaliação ${yValue}`;
                            },
                        },
                    },
                },
                scales: {
                    x: {
                        title: { display: true, text: "Quantidade de Vendas" },
                    },
                    y: {
                        title: { display: true, text: "Avaliação Média" },
                        beginAtZero: true,
                    },
                },
            },
        });


        return () => {
            if (chartInstanceRef.current) {
                chartInstanceRef.current.destroy();
            }
        };
    }, [dados]);


    return (
        <div
            style={{
                width: "100%", // Ocupa toda a largura do container pai
                height: "600px", // Define altura adequada para o gráfico
                margin: "20px auto", // Centraliza e adiciona margens verticais
                padding: "20px",
                backgroundColor: "#fff",
                borderRadius: "10px",
                boxShadow: "0 4px 8px rgba(0, 0, 0, 0.1)",
            }}
        >
            <h2 style={{ textAlign: "center", marginBottom: "20px" }}>
                Quantidade de Vendas X Avaliação Média
            </h2>
            <canvas ref={chartRef}></canvas>
        </div>
    );
};


export default GraficoQuantidadeVendasXAvaliacao;

