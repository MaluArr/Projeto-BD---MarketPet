// src/App.js
import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import HomePage from './pages/HomePage';
import UsuarioPage from './pages/UsuarioPage';
import UsuarioList from './components/UsuarioList';
import UsuarioForm from './components/UsuarioForm';
import VendedorPage from './pages/VendedorPage';
import VendedorList from './components/VendedorList';
import VendedorForm from './components/VendedorForm';
import EnderecoPage from './pages/EnderecoPage';
import EnderecoList from './components/EnderecoList';
import EnderecoForm from './components/EnderecoForm';
import CartaoPage from './pages/CartaoPage';
import CartaoList from './components/CartaoList';
import CartaoForm from './components/CartaoForm';
import CompradorPage from './pages/CompradorPage';
import CompradorList from './components/CompradorList';
import CompradorForm from './components/CompradorForm';
import ChatPage from './pages/ChatPage';
import ChatList from './components/ChatList';
import ChatForm from './components/ChatForm';
import ProdutoPage from './pages/ProdutoPage';
import ProdutoList from './components/ProdutoList';
import ProdutoForm from './components/ProdutoForm';
import FuncionarioPage from './pages/FuncionarioPage';
import FuncionarioList from './components/FuncionarioList';
import FuncionarioForm from './components/FuncionarioForm';
import CuradorPage from './pages/CuradorPage';
import CuradorList from './components/CuradorList';
import CuradorForm from './components/CuradorForm';
import CuradoriaPage from './pages/CuradoriaPage';
import CuradoriaList from './components/CuradoriaList';
import CuradoriaForm from './components/CuradoriaForm';
import AtendentePage from './pages/AtendentePage';
import AtendenteList from './components/AtendenteList';
import AtendenteForm from './components/AtendenteForm';
import AtendimentoPage from './pages/AtendimentoPage';
import AtendimentoList from './components/AtendimentoList';
import AtendimentoForm from './components/AtendimentoForm';
import BuscarProdutoPage from './pages/BuscarProdutoPage';
import BuscarProdutoList from './components/BuscarProdutoList';
import BuscarProdutoForm from './components/BuscarProdutoForm';
import FavoritarProdutoPage from './pages/FavoritarProdutoPage';
import FavoritarProdutoList from './components/FavoritarProdutoList';
import FavoritarProdutoForm from './components/FavoritarProdutoForm';
import CarrinhoPage from './pages/CarrinhoPage';
import CarrinhoList from './components/CarrinhoList';
import CarrinhoForm from './components/CarrinhoForm';
import AdicionarProdutoCarrinhoPage from './pages/AdicionarProdutoCarrinhoPage';
import AdicionarProdutoCarrinhoList from './components/AdicionarProdutoCarrinhoList';
import AdicionarProdutoCarrinhoForm from './components/AdicionarProdutoCarrinhoForm';
import VendaPage from './pages/VendaPage';
import VendaList from './components/VendaList';
import VendaForm from './components/VendaForm';
import AvaliacaoPage from './pages/AvaliacaoPage';
import AvaliacaoList from './components/AvaliacaoList';
import AvaliacaoForm from './components/AvaliacaoForm';

function App() {
    return (
        <Router>
            <div className="App">
                <Routes>
                    <Route path="/" element={<HomePage />} />

                    {/* Rotas de Usuário */}
                    <Route path="/usuarios" element={<UsuarioPage />} />
                    <Route path="/usuarios/list" element={<UsuarioList />} />
                    <Route path="/usuarios/new" element={<UsuarioForm />} />
                    <Route path="/usuarios/:cpf/edit" element={<UsuarioForm />} />

                    {/* Rotas de Vendedor */}
                    <Route path="/vendedores" element={<VendedorPage />} />
                    <Route path="/vendedores/list" element={<VendedorList />} />
                    <Route path="/vendedores/new" element={<VendedorForm />} />
                    <Route path="/vendedores/:cpf/edit" element={<VendedorForm />} />

                    {/* Rotas de Endereço */}
                    <Route path="/enderecos" element={<EnderecoPage />} />
                    <Route path="/enderecos/list" element={<EnderecoList />} />
                    <Route path="/enderecos/new" element={<EnderecoForm />} />
                    <Route path="/enderecos/:id/edit" element={<EnderecoForm />} />

                    {/* Rotas de Cartão */}
                    <Route path="/cartoes" element={<CartaoPage />} />
                    <Route path="/cartoes/list" element={<CartaoList />} />
                    <Route path="/cartoes/new" element={<CartaoForm />} />
                    <Route path="/cartoes/:id/edit" element={<CartaoForm />} />

                    {/* Rotas de Comprador */}
                    <Route path="/compradores" element={<CompradorPage />} />
                    <Route path="/compradores/list" element={<CompradorList />} />
                    <Route path="/compradores/new" element={<CompradorForm />} />
                    <Route path="/compradores/:cpf/edit" element={<CompradorForm />} />

                    {/* Rotas de Chat */}
                    <Route path="/chats" element={<ChatPage />} />
                    <Route path="/chats/list" element={<ChatList />} />
                    <Route path="/chats/new" element={<ChatForm />} />
                    <Route path="/chats/:idChat/edit" element={<ChatForm />} />

                    {/* Rotas de Produto */}
                    <Route path="/produtos" element={<ProdutoPage />} />
                    <Route path="/produtos/list" element={<ProdutoList />} />
                    <Route path="/produtos/new" element={<ProdutoForm />} />
                    <Route path="/produtos/:codigoProduto/edit" element={<ProdutoForm />} />

                    {/* Rotas de Funcionário */}
                    <Route path="/funcionarios" element={<FuncionarioPage />} />
                    <Route path="/funcionarios/list" element={<FuncionarioList />} />
                    <Route path="/funcionarios/new" element={<FuncionarioForm />} />
                    <Route path="/funcionarios/:cpfFuncionario/edit" element={<FuncionarioForm />} />

                    {/* Rotas de Curador */}
                    <Route path="/curadores" element={<CuradorPage />} />
                    <Route path="/curadores/list" element={<CuradorList />} />
                    <Route path="/curadores/new" element={<CuradorForm />} />
                    <Route path="/curadores/:idCurador/edit" element={<CuradorForm />} />

                    {/* Rotas de Curadoria */}
                    <Route path="/curadorias" element={<CuradoriaPage />} />
                    <Route path="/curadorias/list" element={<CuradoriaList />} />
                    <Route path="/curadorias/new" element={<CuradoriaForm />} />
                    <Route path="/curadorias/:codigoCuradoria/edit" element={<CuradoriaForm />} />

                    {/* Rotas de Atendente */}
                    <Route path="/atendentes" element={<AtendentePage />} />
                    <Route path="/atendentes/list" element={<AtendenteList />} />
                    <Route path="/atendentes/new" element={<AtendenteForm />} />
                    <Route path="/atendentes/:idAtendente/edit" element={<AtendenteForm />} />

                    {/* Rotas de Atendimento */}
                    <Route path="/atendimentos" element={<AtendimentoPage />} />
                    <Route path="/atendimentos/list" element={<AtendimentoList />} />
                    <Route path="/atendimentos/new" element={<AtendimentoForm />} />
                    <Route path="/atendimentos/:idAtendimento/edit" element={<AtendimentoForm />} />

                    {/* Rotas de BuscarProduto */}
                    <Route path="/buscar-produto" element={<BuscarProdutoPage />} />
                    <Route path="/buscar-produto/list" element={<BuscarProdutoList />} />
                    <Route path="/buscar-produto/new" element={<BuscarProdutoForm />} />
                    <Route path="/buscar-produto/:idBusca/edit" element={<BuscarProdutoForm />} />

                    {/* Rotas de FavoritarProduto */}
                    <Route path="/favoritar-produtos" element={<FavoritarProdutoPage />} />
                    <Route path="/favoritar-produtos/list" element={<FavoritarProdutoList />} />
                    <Route path="/favoritar-produtos/new" element={<FavoritarProdutoForm />} />
                    <Route path="/favoritar-produtos/:idLista/edit" element={<FavoritarProdutoForm />} />

                    {/* Rotas de Carrinho */}
                    <Route path="/carrinhos" element={<CarrinhoPage />} />
                    <Route path="/carrinhos/list" element={<CarrinhoList />} />
                    <Route path="/carrinhos/new" element={<CarrinhoForm />} />
                    <Route path="/carrinhos/:idCarrinho/edit" element={<CarrinhoForm />} />

                    {/* Rotas de AdicionarProdutoCarrinho */}
                    <Route path="/adicionar-produto-carrinho" element={<AdicionarProdutoCarrinhoPage />} />
                    <Route path="/adicionar-produto-carrinho/list" element={<AdicionarProdutoCarrinhoList />} />
                    <Route path="/adicionar-produto-carrinho/new" element={<AdicionarProdutoCarrinhoForm />} />
                    <Route path="/adicionar-produto-carrinho/:id/edit" element={<AdicionarProdutoCarrinhoForm />} />

                    {/* Rotas de Venda */}
                    <Route path="/vendas" element={<VendaPage />} />
                    <Route path="/vendas/list" element={<VendaList />} />
                    <Route path="/vendas/new" element={<VendaForm />} />
                    <Route path="/vendas/:idVenda/edit" element={<VendaForm />} />

                    {/* Rotas de Avaliacao */}
                    <Route path="/avaliacoes" element={<AvaliacaoPage />} />
                    <Route path="/avaliacoes/list" element={<AvaliacaoList />} />
                    <Route path="/avaliacoes/new" element={<AvaliacaoForm />} />
                    <Route path="/avaliacoes/:idAvaliacao/edit" element={<AvaliacaoForm />} />
                </Routes>
            </div>
        </Router>
    );
}

export default App;