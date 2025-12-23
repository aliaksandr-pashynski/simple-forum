import { AppBar } from '@mui/material';
import { Container, Box } from '@mui/material';
import { useNavigate } from "react-router-dom";

export default function Header() {
    const navigate = useNavigate();
    return (
        <AppBar position="static" square={false} sx={{ bgcolor: 'rgb(255, 159, 28)', margin: '1% 0' }}>
            <Container maxWidth="xl" sx={{ display: 'flex' }}>
                <Box
                    onClick={() => navigate('/categories')}
                    sx={{
                        textTransform: 'uppercase',
                        color: 'rgb(51, 51, 51)',
                        textDecorationLine: 'underline',
                        fontWeight: 700,
                        fontSize: '33px',
                        textShadow: 'rgb(0, 0, 0) -1px 0px 1px, rgb(255, 193, 109) 1px 0px 1px',
                        margin: '20px 0',
                        "&:hover": {
                            cursor: 'pointer'
                        }
                    }}>Simple forum</Box>
            </Container>
        </AppBar >
    )
}