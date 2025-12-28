import { Dialog, DialogContent, Box, Button, DialogTitle, DialogContentText, TextField, DialogActions, CircularProgress } from "@mui/material";
import { ApiContext } from '../context/Context'
import { useState, useContext, Fragment } from "react";
import AddIcon from '@mui/icons-material/Add';
import { useNavigate, useSearchParams } from "react-router-dom";

export default function CreateTopicForm() {

    const apiService = useContext(ApiContext);
    const navigate = useNavigate();
    const [searchParams] = useSearchParams();
    const categoryId = searchParams.get('categoryId');
    const [open, setOpen] = useState(false);
    const [loading, setLoading] = useState(false);

    const handleClickOpen = () => {
        setOpen(true);
    };

    const handleClose = () => {
        setOpen(false);
    };

    const handleSubmit = async (event) => {
        event.preventDefault();
        setLoading(true);
        try {
            const formData = new FormData(event.currentTarget);
            const formJson = Object.fromEntries(formData.entries());
            formJson.categoryId = categoryId;
            await apiService.createTopic(formJson).then((topic) => { navigate(`/posts?topicId=${topic.id}`) });
        } catch (error) {
            //todo
        } finally {
            setLoading(false);
        }
        handleClose();
    };

    return (
        <Fragment>
            {
                apiService.keycloak.authenticated ?
                    <Fragment>
                        <Button
                            variant="contained"
                            onClick={handleClickOpen}
                            endIcon={<AddIcon />}
                            sx={{
                                backgroundColor: '#ff9f1cff',
                                color: '#333333ff',
                                fontWeight: 700,
                                '&:hover': {
                                    backgroundColor: '#fdb858ff'
                                }
                            }}
                        >
                            Create topic
                        </Button>
                        <Dialog open={open} onClose={handleClose}>
                            <DialogTitle>Create a New Topic</DialogTitle>
                            <DialogContent>
                                <DialogContentText>
                                    Give your topic a clear and catchy title. Make it short and descriptive so others can easily find and join the discussion.
                                </DialogContentText>
                                <form onSubmit={handleSubmit} id="subscription-form">
                                    <TextField
                                        autoFocus
                                        required
                                        margin="dense"
                                        id="name"
                                        name="name"
                                        label="Topic title"
                                        type="text"
                                        fullWidth
                                        variant="standard"
                                    />
                                </form>
                            </DialogContent>
                            <DialogActions>
                                <Button onClick={handleClose} disabled={loading}>Cancel</Button>
                                <Button type="submit" form="subscription-form" disabled={loading}>
                                    {loading ? <CircularProgress size={24} /> : "Create"}
                                </Button>
                            </DialogActions>
                        </Dialog>
                    </Fragment>
                    :
                    <Box></Box>
            }
        </Fragment>
    );
}