import { Box, Button, CircularProgress, DialogActions, DialogContent, IconButton, InputBase, Paper } from "@mui/material";
import { useState, useContext } from "react";
import SearchIcon from '@mui/icons-material/Search';
import DialogTitle from '@mui/material/DialogTitle';
import Dialog from '@mui/material/Dialog';
import { ApiSearchContext } from '../context/Context'
import { useNavigate } from "react-router-dom";

export default function SearchSection() {
    const apiSearchService = useContext(ApiSearchContext);
    const [open, setOpen] = useState(false);
    const [loading, setLoading] = useState(false);
    const [searchResult, setSearchResult] = useState([]);
    const navigate = useNavigate();

    const handleOpen = () => {
        setOpen(true);
    };

    const handleClose = () => {
        setOpen(false);
        setSearchResult([]);
    };

    const handleNavigate = (topicId) => {
        handleClose();
        navigate(`/posts?topicId=${topicId}`)
    };

    const handleSubmit = async (event) => {
        event.preventDefault();
        const formData = new FormData(event.currentTarget);
        const formJson = Object.fromEntries(formData.entries());
        console.log('Searching for: ', formJson);
        try {
            setLoading(true);
            let data = await apiSearchService.searchTopics(formJson);
            setSearchResult(data.content);
            console.log(data);
        } catch (e) {
            console.log(e);
        } finally {
            setLoading(false);
        }
    };

    return <Box sx={{ margin: '10px' }}>
        <Button
            onClick={handleOpen}
            variant='outlined'
            sx={{
                borderColor: '#333333ff',
                color: '#333333ff',
                '&:hover': {
                    backgroundColor: 'rgb(219, 134, 15)'
                }
            }}
        >
            <SearchIcon />
            Search topics
        </Button>
        <Dialog onClose={handleClose} open={open} maxWidth='md' fullWidth={true} >
            <DialogTitle>Search topics</DialogTitle>
            <DialogContent>
                <Paper
                    onSubmit={handleSubmit}
                    variant="outlined"
                    component="form"
                    sx={{ p: '2px 0px', display: 'flex', alignItems: 'center', mb: 1 }}
                >
                    <InputBase
                        disabled={loading}
                        name="query"
                        sx={{ ml: 1, flex: 1 }}
                        placeholder="Find a topic"
                    />
                    <IconButton type="submit" sx={{ p: '10px' }} aria-label="search" disabled={loading}>
                        <SearchIcon />
                    </IconButton>
                </Paper>
                {
                    loading ? <Box
                        sx={{
                            display: 'flex',
                            alignItems: 'center',
                            justifyContent: 'center'
                        }}>
                        <CircularProgress size={40} />
                    </Box>
                        :
                        null
                }

                <Box display="flex" flexDirection={'column'}>
                    {searchResult.map((value) => (
                        <Box>
                            <Button key={value.id} onClick={() => handleNavigate(value.id)}>{value.name}</Button>
                        </Box>
                    ))}
                </Box>
            </DialogContent>
            <DialogActions sx={{ margin: '0 20px 10px 0' }}>
                <Button variant="outlined" onClick={handleClose}>Cancel</Button>
            </DialogActions>
        </Dialog>
    </Box >
}