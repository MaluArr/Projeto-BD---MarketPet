import React from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import HomePage from './pages/HomePage';
import UsuarioList from './components/UsuarioList';
import UsuarioDetails from './components/UsuarioDetails';
import UsuarioForm from './components/UsuarioForm';

const App = () => {
    return (
        <Router>
            <Switch>
                <Route path="/" exact component={HomePage} />
                <Route path="/usuarios" exact component={UsuarioList} />
                <Route path="/usuarios/:cpf" exact component={UsuarioDetails} />
                <Route path="/usuarios/new" component={UsuarioForm} />
                {/* Adicione rotas para outras entidades */}
            </Switch>
        </Router>
    );
};

export default App;