// prototipo-bootstrap/js/api.js

// URL base da sua API de produtos no backend Spring Boot
const API_BASE_URL = 'http://localhost:8080/api/produtos';

// Função para buscar todos os produtos
async function getProdutos() {
    try {
        const response = await axios.get(API_BASE_URL);
        return response.data; // Retorna a lista de produtos
    } catch (error) {
        console.error("Erro ao buscar produtos:", error);
        throw error; // Propaga o erro para ser tratado no componente chamador
    }
}

// Função para buscar um produto pelo ID
async function getProdutoById(id) {
    try {
        const response = await axios.get(`${API_BASE_URL}/${id}`);
        return response.data; // Retorna o objeto Produto
    } catch (error) {
        console.error(`Erro ao buscar produto com ID ${id}:`, error);
        throw error;
    }
}

// Função para cadastrar um novo produto
async function createProduto(produtoData) {
    try {
        const response = await axios.post(API_BASE_URL, produtoData);
        return response.data; // Retorna o produto criado
    } catch (error) {
        console.error("Erro ao cadastrar produto:", error.response ? error.response.data : error);
        throw error;
    }
}

// Função para atualizar um produto existente
async function updateProduto(id, produtoData) {
    try {
        const response = await axios.put(`${API_BASE_URL}/${id}`, produtoData);
        return response.data; // Retorna o produto atualizado
    } catch (error) {
        console.error(`Erro ao atualizar produto com ID ${id}:`, error.response ? error.response.data : error);
        throw error;
    }
}

// Função para excluir um produto
async function deleteProduto(id) {
    try {
        await axios.delete(`${API_BASE_URL}/${id}`);
        return true; // Retorna true em caso de sucesso
    } catch (error) {
        console.error(`Erro ao excluir produto com ID ${id}:`, error);
        throw error;
    }
}