import { ApiContext } from '../context/Context'
import Button from '@mui/material/Button';
import { useState, useContext } from "react";
import { Box } from '@mui/material';
import { styled } from '@mui/material/styles';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogContentText from '@mui/material/DialogContentText';
import DialogTitle from '@mui/material/DialogTitle';
import CloudUploadIcon from '@mui/icons-material/CloudUpload';

export default function AvatarDialog({ open, setOpen, avatarUpdateCallback }) {
    const apiService = useContext(ApiContext);
    const AVATAR_IMAGE_WITH = 80;
    const [blob, setBlob] = useState(null);
    const [uploading, setUploading] = useState(false);

    const VisuallyHiddenInput = styled('input')({
        clip: 'rect(0 0 0 0)',
        clipPath: 'inset(80%)',
        height: 1,
        overflow: 'hidden',
        position: 'absolute',
        bottom: 0,
        left: 0,
        whiteSpace: 'nowrap',
        width: 1,
    });

    const handleClose = () => {
        setOpen(false);
        setBlob(null);
    };

    const handleSubmit = async (event) => {
        event.preventDefault();
        const form = new FormData();
        form.append("file", blob);
        try {
            setUploading(true);
            var resp = await apiService.uploadAvatar(form);
            console.log(resp);
            avatarUpdateCallback(resp.objectName);
        } catch (e) {
            console.error(e);
        } finally {
            setUploading(false);
            handleClose();
        }
    };

    async function handleChange(event) {
        const file = event.target.files[0];
        if (file) {

            const url = URL.createObjectURL(file);
            const img = new Image();
            img.onload = () => {
                URL.revokeObjectURL(url);

                var sWidth = Math.min(img.width, img.height);
                var sX = (img.width / 2) - (sWidth / 2);
                var sY = (img.height / 2) - (sWidth / 2);

                var canvas = document.createElement("canvas");
                canvas.width = AVATAR_IMAGE_WITH;
                canvas.height = AVATAR_IMAGE_WITH;

                var context = canvas.getContext("2d");
                context.imageSmoothingEnabled = true;
                context.imageSmoothingQuality = 'high';

                context.drawImage(img, sX, sY, sWidth, sWidth, 0, 0, AVATAR_IMAGE_WITH, AVATAR_IMAGE_WITH);

                canvas.toBlob((blob) => {
                    if (!blob) {
                        console.error('failed getting blob from canvas');
                    }
                    setBlob(blob);
                    canvas.remove();
                    canvas = null;
                }, 'image/jpeg', 1)


            };
            img.src = url;
        } else {
            console.error('loading file error');
        }
    };


    return <Dialog open={open} onClose={handleClose} >
        <DialogTitle>Update Profile Picture</DialogTitle>
        <DialogContent sx={{ minWidth: 400 }}>
            {
                !blob ? <DialogContentText>
                    Upload a new picture to personalize your profile.
                </DialogContentText>
                    :
                    <DialogContentText textAlign={'center'}>
                        Preview
                    </DialogContentText>
            }
            <Box textAlign={'center'}>
                {blob && <img src={URL.createObjectURL(blob)} alt="preview" style={{ margin: '20px 0', width: AVATAR_IMAGE_WITH * 2, height: AVATAR_IMAGE_WITH * 2 }} />}
            </Box>
            <Button
                component="label"
                role={undefined}
                variant="contained"
                tabIndex={-1}
                startIcon={<CloudUploadIcon />}
                sx={{
                    margin: '10px 0'
                }}
            >
                Select file
                <VisuallyHiddenInput type="file" accept="image/*" onChange={handleChange} multiple />
            </Button>
        </DialogContent>
        <DialogActions>
            <Button onClick={handleClose}>Cancel</Button>
            <Button disabled={!blob} loading={uploading} loadingPosition="start" onClick={handleSubmit}>Save</Button>
        </DialogActions>
    </Dialog>;
}