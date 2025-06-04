// src/pages/ProdutosPage.jsx
import React, { useState, useEffect, useCallback } from 'react';
import axios from 'axios';
import {
  Box,
  Typography,
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
  Paper,
  TextField,
  Button,
  Pagination,
  Stack,
  IconButton,
  CircularProgress,
  Alert,
  Dialog,
  DialogActions,
  DialogContent,
  DialogContentText,
  DialogTitle,
  Snackbar, // Adicionado Snackbar
} from '@mui/material';
import {
  Add as AddIcon,
  Edit as EditIcon,
  Delete as DeleteIcon,
  ContentCopy as ContentCopyIcon,
  Search as SearchIcon,
  Clear as ClearIcon, // Adicionado ícone para limpar filtro
} from '@mui/icons-material';
import { useNavigate } from 'react-router-dom';

const API_BASE_URL = 'http://localhost:8080/api/produtos';

function ProdutosPage() {
  const [produtos, setProdutos] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [page, setPage] = useState(1);
  const [rowsPerPage] = useState(10);
  // Unificado o termo de busca em um único estado
  const [globalSearchTerm, setGlobalSearchTerm] = useState('');
  const [confirmDeleteOpen, setConfirmDeleteOpen] = useState(false);
  const [produtoToDelete, setProdutoToDelete] = useState(null);
  const [snackbarOpen, setSnackbarOpen] = useState(false); // Estado para o Snackbar
  const [snackbarMessage, setSnackbarMessage] = useState('');
  const [snackbarSeverity, setSnackbarSeverity] = useState('success');

  const navigate = useNavigate();

  // Função para exibir Snackbar
  const showSnackbar = (message, severity) => {
    setSnackbarMessage(message);
    setSnackbarSeverity(severity);
    setSnackbarOpen(true);
  };

  const handleSnackbarClose = (event, reason) => {
    if (reason === 'clickaway') {
      return;
    }
    setSnackbarOpen(false);
  };

  // Função para buscar produtos da API
  const fetchProdutos = useCallback(async () => {
    setLoading(true);
    setError(null);
    try {
      const response = await axios.get(API_BASE_URL);

      // Filtragem no cliente (ainda temporário)
      let filteredProdutos = response.data;
      if (globalSearchTerm) {
        const lowerCaseSearchTerm = globalSearchTerm.toLowerCase();
        filteredProdutos = filteredProdutos.filter(p =>
          (p.nome && p.nome.toLowerCase().includes(lowerCaseSearchTerm)) ||
          (p.id && p.id.toString().includes(lowerCaseSearchTerm)) ||
          (p.codigo && p.codigo.toLowerCase().includes(lowerCaseSearchTerm))
        );
      }

      setProdutos(filteredProdutos);
    } catch (err) {
      console.error("Erro ao buscar produtos:", err);
      setError("Erro ao carregar produtos. Verifique sua conexão ou o servidor.");
      showSnackbar("Erro ao carregar produtos.", 'error');
    } finally {
      setLoading(false);
    }
  }, [globalSearchTerm]); // Dependência para useCallback

  useEffect(() => {
    fetchProdutos();
  }, [fetchProdutos]);

  // Lógica de paginação no cliente
  const startIndex = (page - 1) * rowsPerPage;
  const endIndex = startIndex + rowsPerPage;
  const currentProdutos = produtos.slice(startIndex, endIndex);
  const totalPages = Math.ceil(produtos.length / rowsPerPage);

  const handleChangePage = (event, value) => {
    setPage(value);
  };

  const handleSearch = () => {
    setPage(1); // Resetar para a primeira página ao fazer uma nova busca
    fetchProdutos();
  };

  const handleClearFilters = () => {
    setGlobalSearchTerm('');
    setPage(1);
    // fetchProdutos será chamado automaticamente pelo useEffect
  };

  const handleCreateNew = () => {
    navigate('/produtos/novo');
  };

  const handleEdit = (id) => {
    navigate(`/produtos/editar/${id}`);
  };

  const handleDeleteClick = (produto) => {
    setProdutoToDelete(produto);
    setConfirmDeleteOpen(true);
  };

  const handleConfirmDeleteClose = () => {
    setConfirmDeleteOpen(false);
    setProdutoToDelete(null);
  };

  const handleDeleteConfirm = async () => {
    if (produtoToDelete) {
      setLoading(true);
      setError(null);
      try {
        await axios.delete(`${API_BASE_URL}/${produtoToDelete.id}`);
        setProdutos(prev => prev.filter(p => p.id !== produtoToDelete.id));
        handleConfirmDeleteClose();
        showSnackbar(`Produto "${produtoToDelete.nome}" excluído com sucesso!`, 'success');
      } catch (err) {
        console.error("Erro ao excluir produto:", err);
        setError("Erro ao excluir produto. Tente novamente.");
        showSnackbar("Erro ao excluir produto. Tente novamente.", 'error');
      } finally {
        setLoading(false);
      }
    }
  };

  const handleDuplicate = (produto) => {
    // Ao duplicar, navegamos para o formulário de novo produto, passando o produto existente
    // para que os campos sejam pré-preenchidos. O ID será nulo para criar um novo registro.
    navigate('/produtos/novo', { state: { produtoOriginal: produto } });
  };

  return (
    <Box sx={{ mt: 4, ml: 3 }}>
      <Typography variant="h4" gutterBottom>
        Cadastro de Produtos
      </Typography>

      {/* Seção de Filtros (Unificada) */}
      <Paper elevation={3} sx={{ p: 2, mb: 3 }}>
        <Typography variant="h6" gutterBottom>Filtro de Busca</Typography>
        <Box sx={{ display: 'flex', gap: 2, flexWrap: 'wrap', alignItems: 'center' }}>
          <TextField
            label="Buscar por Nome, ID ou Código"
            variant="outlined"
            size="small"
            value={globalSearchTerm}
            onChange={(e) => setGlobalSearchTerm(e.target.value)}
            sx={{ flex: '1 1 300px' }}
            onKeyPress={(e) => { // Permite buscar ao pressionar Enter
              if (e.key === 'Enter') {
                handleSearch();
              }
            }}
          />
          <Button
            variant="contained"
            onClick={handleSearch}
            startIcon={<SearchIcon />}
            sx={{ flexShrink: 0 }}
          >
            Buscar
          </Button>
          <Button
            variant="outlined"
            onClick={handleClearFilters}
            startIcon={<ClearIcon />}
            sx={{ flexShrink: 0 }}
          >
            Limpar
          </Button>
        </Box>
      </Paper>

      {/* Botão de Novo Produto */}
      <Box sx={{ display: 'flex', justifyContent: 'flex-end', mb: 2 }}>
        <Button
          variant="contained"
          color="primary"
          startIcon={<AddIcon />}
          onClick={handleCreateNew}
        >
          Novo Produto
        </Button>
      </Box>

      {loading && <CircularProgress sx={{ display: 'block', margin: 'auto' }} />}
      {error && <Alert severity="error" sx={{ mb: 2 }}>{error}</Alert>}

      {/* Tabela de Produtos */}
      {!loading && !error && (
        <TableContainer component={Paper} elevation={3}>
          <Table sx={{ minWidth: 650 }} aria-label="tabela de produtos">
            <TableHead>
              <TableRow>
                <TableCell>ID</TableCell>
                <TableCell>Nome</TableCell>
                <TableCell>Código</TableCell>
                <TableCell>Unidade</TableCell>
                <TableCell align="right">Preço Venda</TableCell>
                <TableCell>Ativo</TableCell>
                <TableCell align="center">Ações</TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {currentProdutos.length === 0 ? (
                <TableRow>
                  <TableCell colSpan={7} align="center">Nenhum produto encontrado.</TableCell>
                </TableRow>
              ) : (
                currentProdutos.map((produto) => (
                  <TableRow
                    key={produto.id}
                    sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
                  >
                    <TableCell component="th" scope="row">
                      {produto.id}
                    </TableCell>
                    <TableCell>{produto.nome}</TableCell>
                    <TableCell>{produto.codigo}</TableCell>
                    <TableCell>{produto.unidade}</TableCell>
                    <TableCell align="right">R$ {produto.preco_venda ? produto.preco_venda.toFixed(2) : '0.00'}</TableCell>
                    <TableCell>{produto.ativo ? 'Sim' : 'Não'}</TableCell>
                    <TableCell align="center">
                      <IconButton color="info" onClick={() => handleEdit(produto.id)}>
                        <EditIcon />
                      </IconButton>
                      <IconButton color="secondary" onClick={() => handleDeleteClick(produto)}>
                        <DeleteIcon />
                      </IconButton>
                      <IconButton color="default" onClick={() => handleDuplicate(produto)}>
                        <ContentCopyIcon />
                      </IconButton>
                    </TableCell>
                  </TableRow>
                ))
              )}
            </TableBody>
          </Table>
        </TableContainer>
      )}

      {/* Paginação */}
      <Stack spacing={2} sx={{ mt: 3, alignItems: 'center' }}>
        <Pagination
          count={totalPages}
          page={page}
          onChange={handleChangePage}
          color="primary"
          showFirstButton
          showLastButton
        />
      </Stack>

      {/* Diálogo de Confirmação de Exclusão */}
      <Dialog
        open={confirmDeleteOpen}
        onClose={handleConfirmDeleteClose}
        aria-labelledby="alert-dialog-title"
        aria-describedby="alert-dialog-description"
      >
        <DialogTitle id="alert-dialog-title">{"Confirmar Exclusão?"}</DialogTitle>
        <DialogContent>
          <DialogContentText id="alert-dialog-description">
            Tem certeza de que deseja excluir o produto "
            **{produtoToDelete?.nome}**" (ID: {produtoToDelete?.id})?
            Essa ação é irreversível.
          </DialogContentText>
        </DialogContent>
        <DialogActions>
          <Button onClick={handleConfirmDeleteClose}>Cancelar</Button>
          <Button onClick={handleDeleteConfirm} color="error" autoFocus>
            Excluir
          </Button>
        </DialogActions>
      </Dialog>

      {/* Snackbar para mensagens de feedback */}
      <Snackbar
        open={snackbarOpen}
        autoHideDuration={6000}
        onClose={handleSnackbarClose}
        anchorOrigin={{ vertical: 'bottom', horizontal: 'right' }}
      >
        <Alert onClose={handleSnackbarClose} severity={snackbarSeverity} sx={{ width: '100%' }}>
          {snackbarMessage}
        </Alert>
      </Snackbar>
    </Box>
  );
}

export default ProdutosPage;