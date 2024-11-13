import React, { useState, useEffect } from 'react';
import api from '../services/apiService';

const ProductsPage = () => {
    const [products, setProducts] = useState([]);
    const [newProduct, setNewProduct] = useState({ nome: '', preco: 0 });
    const [editingProduct, setEditingProduct] = useState(null);

    useEffect(() => {
        fetchProducts();
    }, []);

    const fetchProducts = async () => {
        try {
            const response = await api.get('/produtos');
            setProducts(response.data);
        } catch (error) {
            console.error(error);
        }
    };

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setNewProduct({ ...newProduct, [name]: value });
    };

    const addProduct = async () => {
        try {
            await api.post('/produtos', newProduct);
            fetchProducts();
            setNewProduct({ nome: '', preco: 0 });
        } catch (error) {
            console.error(error);
        }
    };

    const deleteProduct = async (id) => {
        try {
            await api.delete(`/produtos/${id}`);
            fetchProducts();
        } catch (error) {
            console.error(error);
        }
    };

    const updateProduct = async () => {
        try {
            await api.put(`/produtos/${editingProduct.id}`, editingProduct);
            fetchProducts();
            setEditingProduct(null);
        } catch (error) {
            console.error(error);
        }
    };

    return (
        <div>
            <h1>Produtos</h1>
            <ul>
                {products.map(product => (
                    <li key={product.id}>
                        {product.nome} - ${product.preco}
                        <button onClick={() => deleteProduct(product.id)}>Deletar</button>
                        <button onClick={() => setEditingProduct(product)}>Editar</button>
                    </li>
                ))}
            </ul>

            <h2>Adicionar Produto</h2>
            <input
                type="text"
                name="nome"
                placeholder="Nome"
                value={newProduct.nome}
                onChange={handleInputChange}
            />
            <input
                type="number"
                name="preco"
                placeholder="Preço"
                value={newProduct.preco}
                onChange={handleInputChange}
            />
            <button onClick={addProduct}>Adicionar</button>

            {editingProduct && (
                <div>
                    <h2>Editar Produto</h2>
                    <input
                        type="text"
                        name="nome"
                        value={editingProduct.nome}
                        onChange={(e) =>
                            setEditingProduct({ ...editingProduct, nome: e.target.value })
                        }
                    />
                    <input
                        type="number"
                        name="preco"
                        value={editingProduct.preco}
                        onChange={(e) =>
                            setEditingProduct({ ...editingProduct, preco: e.target.value })
                        }
                    />
                    <button onClick={updateProduct}>Salvar</button>
                </div>
            )}
        </div>
    );
};

export default ProductsPage;