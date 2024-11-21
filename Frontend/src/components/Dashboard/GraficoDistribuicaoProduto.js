import React, { useRef, useEffect } from "react";
import { Chart } from "chart.js";
import { Chart as ChartJS, CategoryScale, LinearScale, BarElement, Title, Tooltip, Legend, PointElement, LineElement, ArcElement, RadialLinearScale } from "chart.js";
ChartJS.register(CategoryScale, LinearScale, BarElement, PointElement, LineElement, Title, Tooltip, Legend, ArcElement, RadialLinearScale);


const GraficoDistribuicaoProdutos = ({ dados }) => {
    const chartRef = useRef(null);
    const chartInstanceRef = useRef(null);


    useEffect(() => {
        const ctx = chartRef.current.getContext("2d");


        if (chartInstanceRef.current) {
            chartInstanceRef.current.destroy();
        }


        // Função para gerar uma cor aleatória
        const gerarCoresUnicas = (quantidade) => {
            const cores = new Set();
            while (cores.size < quantidade) {
                const cor = `#${Math.floor(Math.random() * 16777215).toString(16)}`;
                cores.add(cor);
            }
            return Array.from(cores);
        };


        const colors = gerarCoresUnicas(dados.length);


        chartInstanceRef.current = new Chart(ctx, {
            type: "pie",
            data: {
                labels: dados.map((item) => `Produto ${item.codigo_produto}`),
                datasets: [
                    {
                        data: dados.map((item) => item.total_vendas),
                        backgroundColor: colors,
                        borderWidth: 1,
                    },
                ],
            },
            options: {
                responsive: true,
                plugins: {
                    legend: {
                        position: "bottom",
                        labels: {
                            boxWidth: 12,
                            padding: 10,
                        },
                    },
                    tooltip: {
                        callbacks: {
                            label: (context) => {
                                const label = context.label || "";
                                const value = context.raw || 0;
                                return `${label}: R$ ${value.toLocaleString("pt-BR", {
                                    minimumFractionDigits: 2,
                                })}`;
                            },
                        },
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
        <div style={{ marginTop: "20px", maxWidth: "600px", margin: "0 auto" }}>
            <h2 style={{ textAlign: "center", marginBottom: "20px" }}>
                Distribuição de Vendas por Produto
            </h2>
            <canvas ref={chartRef}></canvas>
        </div>
    );
};


export default GraficoDistribuicaoProdutos;


