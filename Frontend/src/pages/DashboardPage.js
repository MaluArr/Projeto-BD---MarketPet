import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom"; // Import para navegação
import GraficoVendasDiarias from "../components/Dashboard/GraficoVendasDiarias";
import GraficoTopProdutos from "../components/Dashboard/GraficoTopProdutos";
import GraficoTopVendedores from "../components/Dashboard/GraficoTopVendedores";
import GraficoDistribuicaoProdutos from "../components/Dashboard/GraficoDistribuicaoProduto";
import GraficoQuantidadeVendasXAvaliacao from "../components/Dashboard/GraficoQuantidadeVendasXAvaliacao";
import ResumoEstatisticas from "../components/Dashboard/ResumoEstatisticas";
import "../styles/Dashboard.css";
import { buscarDadosDashboard } from "../services/DashboardService";

const DashboardPagina = () => {
    const [estatisticas, setEstatisticas] = useState(null);
    const [carregando, setCarregando] = useState(true);


    useEffect(() => {
        const carregarDados = async () => {
            try {
                const dados = await buscarDadosDashboard();
                setEstatisticas(dados);
            } catch (error) {
                console.error("Erro ao carregar dados do dashboard:", error);
            } finally {
                setCarregando(false);
            }
        };


        carregarDados();
    }, []);


    if (carregando) {
        return (
            <div className="dashboard-container">
                <p className="carregando">Carregando...</p>
            </div>
        );
    }


    if (!estatisticas) {
        return (
            <div className="dashboard-container">
                <p className="carregando">Erro ao carregar os dados. Tente novamente.</p>
            </div>
        );
    }


    return (
        <div className="dashboard-container">
            <header className="dashboard-header">
                <h1>Dashboard de Vendas</h1>
                <Link to="/HOME" className="home-button">Ir para Página de Gerenciamento</Link> {/* Botão para Home */}
            </header>
            <ResumoEstatisticas resumo={estatisticas.resumo} />
            <div className="dashboard-conteudo">
                <GraficoVendasDiarias dados={estatisticas.vendasDiarias} />
                <GraficoTopProdutos dados={estatisticas.topProdutos} />
                <GraficoTopVendedores dados={estatisticas.topVendedores} />
                <GraficoDistribuicaoProdutos dados={estatisticas.distribuicaoProdutos} />
                <GraficoQuantidadeVendasXAvaliacao dados={estatisticas.vendasXAvaliacao} />
            </div>
        </div>
    );
};


export default DashboardPagina;
