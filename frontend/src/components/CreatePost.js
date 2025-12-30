import { Fragment, useContext, useState } from "react";
import { Dialog, DialogContent, Box, Button, DialogTitle, DialogContentText, TextField, DialogActions, CircularProgress } from "@mui/material";
import { ApiContext } from "../context/Context";
import AddIcon from '@mui/icons-material/Add';
import { useNavigate } from "react-router-dom";

export default function CreatePostForm({ topicId, onPostAdded }) {

    const apiService = useContext(ApiContext);
    const navigate = useNavigate();
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
            formJson.topicId = topicId;
           
            await apiService.createPost(formJson).then((post) => { onPostAdded(post) });
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
                            Add post
                        </Button>
                        <Dialog open={open} onClose={handleClose}>
                            <DialogTitle>Add new Post</DialogTitle>
                            <DialogContent sx={{ minWidth: '400px' }}>
                                <form onSubmit={handleSubmit} id="subscription-form">
                                    <TextField
                                        multiline               // turns it into a <textarea>
                                        minRows={3}             // visible rows before scroll
                                        maxRows={100}
                                        autoFocus
                                        required
                                        margin="dense"
                                        id="body"
                                        name="body"
                                        label="Share your thoughts"
                                        type="text"
                                        fullWidth
                                        variant="standard"
                                    />
                                </form>
                            </DialogContent>
                            <DialogActions>
                                <Button onClick={handleClose} disabled={loading}>Cancel</Button>
                                <Button type="submit" form="subscription-form" disabled={loading}>
                                    {loading ? <CircularProgress size={24} /> : "Add"}
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