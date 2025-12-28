import { Box, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Paper, styled } from '@mui/material';
import ArticleIcon from '@mui/icons-material/Article';
import CircularProgress from '@mui/material/CircularProgress';

const StyledTableRow = styled(TableRow)(({ theme }) => ({
    backgroundColor: '#575a5aff',
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

export default function TopicsTable(props) {
    const topics = props.topics;
    const isLoading = props.isLoading;


    if (isLoading) {
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
                <Table sx={{ minWidth: 650, color: "rgb(204, 204, 204)" }} aria-label="simple table">
                    <TableHead sx={{ backgroundColor: 'rgb(64, 64, 64)', color: "inherit" }}>
                        <TableRow>
                            <StyledTableCell>Topics</StyledTableCell>
                            <StyledTableCell sx={{ width: "10%" }} align="center">Posts</StyledTableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody >
                        {topics.map((topic) => (
                            <StyledTableRow hover key={topic.id}>
                                <StyledTableCell>
                                    <Box sx={{ display: 'flex', alignItems: 'center' }}>
                                        <ArticleIcon sx={{ fontSize: 40, marginRight: '20px' }} />
                                        <Box>
                                            <Box
                                                sx={{
                                                    display: 'inline-block',
                                                    fontSize: '16px',
                                                    fontWeight: 700,
                                                    margin: 0,
                                                    "&:hover": {
                                                        textDecoration: 'underline',
                                                        cursor: 'pointer'
                                                    }
                                                }}
                                            >
                                                {topic.name}
                                            </Box>
                                            <Box>
                                                by <b>{topic.createdBy.username}</b> at {new Date(topic.createdAt).toLocaleString()}
                                            </Box>
                                        </Box>
                                    </Box>
                                </StyledTableCell>

                                <StyledTableCell align="center">{topic.postsCount}</StyledTableCell>
                            </StyledTableRow>
                        ))}
                    </TableBody>
                </Table>
            </TableContainer>
        )
    }
}