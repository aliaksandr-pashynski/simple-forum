import { Box, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Paper, styled } from '@mui/material';
import { useEffect, useState, useContext } from "react";
import TextSnippetIcon from '@mui/icons-material/TextSnippet';
import CircularProgress from '@mui/material/CircularProgress';
import { useNavigate } from "react-router-dom";
import { ApiContext } from '../context/Context'


const StyledTableRow = styled(TableRow)(({ theme }) => ({
    backgroundColor: 'rgb(87, 90, 90)',
    color: "inherit",
    '&.MuiTableRow-hover:hover': {
        backgroundColor: '#ff9f1cff',
        color: 'rgb(51, 51, 51)'
    }
}));

const StyledTableCell = styled(TableCell)(({ theme }) => ({
    color: "inherit",
    borderColor: '#333532ff'
}));

export default function Categories() {
    const navigate = useNavigate();
    const [categories, setCategories] = useState([]);
    const [loading, setLoading] = useState(true);
    const apiService = useContext(ApiContext);


    useEffect(() => {
        setLoading(true);
        apiService
            .getCategories()
            .then(setCategories)
            .finally(() => setLoading(false));
    }, []);

    if (loading) {
        return <Box
            sx={{
                display: 'flex',
                alignItems: 'center',
                justifyContent: 'center',
                height: '80vh',
            }}>
            <CircularProgress size={70} />
        </Box>
    } else {
        return (

            <TableContainer component={Paper} sx={{ marginBottom: '20px' }}>
                <Table sx={{ minWidth: 650, color: "#ccccccff" }} aria-label="simple table">
                    <TableHead sx={{ backgroundColor: '#404040ff', color: "inherit" }}>
                        <TableRow>
                            <StyledTableCell>Categories</StyledTableCell>
                            <StyledTableCell sx={{ width: "10%" }} align="center">Topics</StyledTableCell>
                            <StyledTableCell sx={{ width: "10%" }} align="center">Posts</StyledTableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody >
                        {categories.map((category) => (
                            <StyledTableRow hover key={category.name}>
                                <StyledTableCell>
                                    <Box sx={{ display: 'flex', alignItems: 'center' }}>
                                        <TextSnippetIcon sx={{ fontSize: 50, marginRight: '20px' }}></TextSnippetIcon>
                                        <Box>
                                            <Box onClick={() => navigate(`/topics?categoryId=${category.id}`)}
                                                sx={{
                                                    display: 'inline-block',
                                                    fontSize: '20px',
                                                    fontWeight: 700,
                                                    margin: 0,
                                                    "&:hover": {
                                                        textDecoration: 'underline',
                                                        cursor: 'pointer'
                                                    }
                                                }}
                                            >
                                                {category.name}
                                            </Box>
                                            <Box sx={{ fontSize: '14px' }}>{category.description}</Box>
                                        </Box>
                                    </Box>
                                </StyledTableCell>
                                <StyledTableCell align="center">{category.topics}</StyledTableCell>
                                <StyledTableCell align="center">{category.posts}</StyledTableCell>
                            </StyledTableRow>
                        ))}
                    </TableBody>
                </Table>
            </TableContainer>
        )
    }
}