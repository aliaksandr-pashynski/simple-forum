import ReactDOM from 'react-dom/client';
import './index.css';
import Categories from './pages/Categories';
import Topics from './pages/Topics';
import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom';
import { Container } from '@mui/material';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import Keycloak from 'keycloak-js';
import { ApiContext, ApiSearchContext } from './context/Context'
import { ApiForumService } from './api/ApiForumService';
import AppLayout from './pages/AppLayout';
import Posts from './pages/Posts';

import '@fontsource/titillium-web/300.css';
import '@fontsource/titillium-web/400.css';
import '@fontsource/titillium-web/600.css';
import '@fontsource/titillium-web/700.css';
import { ApiSearchService } from './api/ApiSearchService';

const theme = createTheme({
  typography: {
    fontFamily: `'Titillium Web', sans-serif`,
  },
});

const keycloak = new Keycloak({
  url: "https://alex-pash.ddns.net/keycloak",
  realm: "forum-dev",
  clientId: "forum-frontend-dev"
});

keycloak.init({
  onLoad: 'check-sso'
}).then((auth) => {
  const root = ReactDOM.createRoot(document.getElementById('root'));
  root.render(
    <ApiContext.Provider value={new ApiForumService(keycloak)}>
      <ApiSearchContext.Provider value={new ApiSearchService()}>
        <ThemeProvider theme={theme}>
          <Container maxWidth="xl">
            <BrowserRouter basename="/simple-forum/ui">
              <Routes>
                <Route path="/" element={<Navigate to="categories" replace />} />
                <Route element={<AppLayout />}>
                  <Route path="categories" element={<Categories />} />
                  <Route path="topics" element={<Topics />} />
                  <Route path="posts" element={<Posts />} />
                </Route>
              </Routes>
            </BrowserRouter>
          </Container>
        </ThemeProvider>
      </ApiSearchContext.Provider>
    </ApiContext.Provider >
  )
});