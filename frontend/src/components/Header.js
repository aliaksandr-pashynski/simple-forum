import { AppBar } from '@mui/material';
import { Container, Box } from '@mui/material';
import { useNavigate } from "react-router-dom";
import LoginSection from './LoginSection'
import SearchSection from './SearchSection';

export default function Header() {
    const navigate = useNavigate();
    return (
        <AppBar position="static" square={false} sx={{ bgcolor: '#ff9f1cff', margin: '1% 0' }}>
            <Container maxWidth="xl" sx={{ display: 'flex', justifyContent: 'space-between' }}>
                <Box
                    onClick={() => navigate('/categories')}
                    sx={{
                        textTransform: 'uppercase',
                        color: '#333333ff',
                        textDecorationLine: 'underline',
                        fontWeight: 700,
                        fontSize: '33px',
                        textShadow: 'rgb(0, 0, 0) -1px 0px 1px, rgb(255, 193, 109) 1px 0px 1px',
                        margin: '20px 0',
                        "&:hover": {
                            cursor: 'pointer'
                        }
                    }}>Simple forum</Box>
                <Box display="flex" alignItems="center">
                    <SearchSection />
                    <LoginSection />
                </Box>
            </Container>
        </AppBar >
    )
}