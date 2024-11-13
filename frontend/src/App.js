import React from 'react';
import Header from './components/header';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import ProductsPage from './pages/ProductsPage';

const App = () => (
    <div>
      <Header />
      <p>Bem-vindo ao MarketPet!</p>
    </div>
);

function App() {
    return (
        <Router>
            <Switch>
                <Route path="/produtos" component={ProductsPage} />
                {/* Outras rotas */}
            </Switch>
        </Router>
    );
}

export default App;