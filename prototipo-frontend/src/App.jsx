// src/App.jsx
import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import {
  CssBaseline,
  Box,
  AppBar,
  Toolbar,
  Typography,
} from '@mui/material';

import Sidebar from './components/Sidebar';
import ProdutosPage from './pages/ProdutosPage';
import ProdutoForm from './pages/ProdutoForm'; // Certifique-se que está importado

const drawerWidth = 240;

function App() {
  return (
    <Router>
      <Box sx={{ display: 'flex' }}>
        <CssBaseline />

        {/* Top Bar (AppBar) */}
        <AppBar
          position="fixed" // Define a posição fixa na tela
          sx={{
            width: `calc(100% - ${drawerWidth}px)`, // Largura da AppBar ajustada pela largura da sidebar
            ml: `${drawerWidth}px`, // Margem esquerda igual à largura da sidebar
            zIndex: (theme) => theme.zIndex.drawer + 1, // Garante que a AppBar fique acima da Sidebar
          }}
        >
          <Toolbar>
            <Typography variant="h6" noWrap component="div">
              Prototipo ERP - Módulo de Produtos
            </Typography>
          </Toolbar>
        </AppBar>

        {/* Sidebar */}
        <Sidebar />

        {/* Área de Conteúdo Principal */}
        <Box
          component="main"
          sx={{
            flexGrow: 1, // Permite que o Box ocupe todo o espaço disponível
            p: 3, // Padding geral
            width: { sm: `calc(100% - ${drawerWidth}px)` }, // Largura ajustada para telas menores
            ml: `${drawerWidth}px`, // Margem esquerda para acomodar a sidebar
          }}
        >
          <Toolbar /> {/* Este Toolbar aqui serve para empurrar o conteúdo abaixo da AppBar fixa */}
          <Routes>
            <Route path="/" element={
              <Box sx={{ mt: 4, ml: 3 }}>
                <Typography variant="h4" gutterBottom>
                  Bem-vindo ao Prototipo ERP!
                </Typography>
                <Typography variant="body1">
                  Use a barra lateral para navegar pelos módulos.
                </Typography>
              </Box>
            } />
            <Route path="/produtos" element={<ProdutosPage />} />
            <Route path="/produtos/novo" element={<ProdutoForm />} />
            <Route path="/produtos/editar/:id" element={<ProdutoForm />} />
          </Routes>
        </Box>
      </Box>
    </Router>
  );
}

export default App;