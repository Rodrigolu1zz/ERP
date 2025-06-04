// src/components/Sidebar.jsx
import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import {
  Drawer,
  List,
  ListItem,
  ListItemButton,
  ListItemIcon,
  ListItemText,
  Toolbar,
  Collapse,
} from '@mui/material';
import {
  Dashboard as DashboardIcon,
  Category as CategoryIcon,
  ExpandLess,
  ExpandMore,
  Menu as MenuIcon // Para o ícone de abrir/fechar a sidebar
} from '@mui/icons-material';

const drawerWidth = 240; // Largura da sidebar

function Sidebar() {
  const [openCadastros, setOpenCadastros] = useState(false); // Estado para controlar a expansão do menu "Cadastros"
  const [drawerOpen, setDrawerOpen] = useState(true); // Estado para controlar se a sidebar está aberta/fechada

  const handleClickCadastros = () => {
    setOpenCadastros(!openCadastros);
  };

  const handleDrawerToggle = () => {
    setDrawerOpen(!drawerOpen);
  };

  return (
    // Drawer é o componente Material-UI para a barra lateral
    <Drawer
      sx={{
        width: drawerWidth,
        flexShrink: 0,
        '& .MuiDrawer-paper': {
          width: drawerWidth,
          boxSizing: 'border-box',
          position: 'fixed', // Fixa a sidebar na tela
        },
      }}
      variant="persistent" // Ou "temporary" para mobile
      anchor="left"
      open={drawerOpen} // Controla se a sidebar está aberta
    >
      <Toolbar>
        {/* Aqui você pode colocar um logo ou título do ERP */}
        <ListItemText primary="Prototipo ERP" />
        {/* Botão para recolher/expandir a sidebar, se desejar */}
        {/* <IconButton onClick={handleDrawerToggle}>
          <MenuIcon />
        </IconButton> */}
      </Toolbar>
      <List>
        {/* Link para o Dashboard (exemplo) */}
        <ListItem disablePadding>
          <ListItemButton component={Link} to="/">
            <ListItemIcon>
              <DashboardIcon />
            </ListItemIcon>
            <ListItemText primary="Dashboard" />
          </ListItemButton>
        </ListItem>

        {/* Módulo de Cadastros */}
        <ListItemButton onClick={handleClickCadastros}>
          <ListItemIcon>
            <CategoryIcon />
          </ListItemIcon>
          <ListItemText primary="Cadastros" />
          {openCadastros ? <ExpandLess /> : <ExpandMore />}
        </ListItemButton>
        <Collapse in={openCadastros} timeout="auto" unmountOnExit>
          <List component="div" disablePadding>
            {/* Sub-item: Produtos */}
            <ListItem disablePadding sx={{ pl: 4 }}> {/* pl para padding-left, indentação */}
              <ListItemButton component={Link} to="/produtos">
                <ListItemText primary="Produtos" />
              </ListItemButton>
            </ListItem>
            {/* Outros itens de cadastro aqui, como Grupos, Fabricantes, Fornecedores */}
          </List>
        </Collapse>

        {/* Outros módulos */}
      </List>
    </Drawer>
  );
}

export default Sidebar;