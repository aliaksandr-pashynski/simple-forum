import { ApiContext } from '../context/Context'
import Button from '@mui/material/Button';
import LoginIcon from '@mui/icons-material/Login';
import PersonIcon from '@mui/icons-material/Person';
import { useEffect, useState, useContext, Fragment } from "react";
import Skeleton from '@mui/material/Skeleton';
import { Menu, MenuItem, Box, Avatar, Divider } from '@mui/material';
import AvatarDialog from './AvatarDialog';

export default function LoginSection() {
    const apiService = useContext(ApiContext);
    const [userData, setUserData] = useState(null);
    const [userAvatar, setUserAvatar] = useState(null);
    const [loading, setLoading] = useState(true);
    const [avatarDialogOpen, setAvatarDialogOpen] = useState(false);
    const [anchorEl, setAnchorEl] = useState(null);

    const handleClick = (event) => {
        setAnchorEl(event.currentTarget);
    };
    const handleMenuClose = () => {
        setAnchorEl(null);
    };

    useEffect(() => {
        setLoading(true)
        if (apiService.keycloak.authenticated) {
            apiService
                .getInfoAboutMe()
                .then(resp => {
                    setUserData(resp);
                    setUserAvatar(resp.avatar);
                })
                .finally(() => setLoading(false));
        }
    }, []);

    if (!apiService.keycloak.authenticated) {
        return <Box alignItems="center" display="flex">
            <Button
                variant="contained"
                onClick={() => apiService.keycloak.login()}
                endIcon={<LoginIcon />}
                sx={{
                    backgroundColor: '#333333ff',
                    color: '#ff9f1cff',
                    '&:hover': {
                        backgroundColor: '#575a5aff'
                    }
                }}
            >
                Login
            </Button>
        </Box>;
    } else {
        return (
            <Box alignItems="center" display="flex">
                {
                    loading ?
                        <Box alignItems="center" display="flex">
                            <Skeleton variant="rectangular" width={50} height={20} sx={{ backgroundColor: '#333333ff' }} />
                            <Skeleton variant="circular" width={40} height={40} sx={{ backgroundColor: '#333333ff', margin: '0 10px' }} />
                        </Box>
                        :
                        <Fragment>
                            <Box sx={{
                                margin: '0 15px',
                                color: '#333333ff',
                                fontWeight: 700,
                                fontSize: '20px'
                            }}>
                                {userData?.username}
                            </Box>
                            <Avatar
                                onClick={handleClick}
                                sx={{
                                    width: 70, height: 70,
                                    backgroundColor: '#333333ff',
                                    marginRight: '10px',
                                    cursor: 'pointer'
                                }}
                                src={`https://alex-pash.ddns.net/minio/public/${userAvatar}`}
                            >

                                <PersonIcon sx={{ fontSize: 45 }} />
                            </Avatar>
                            <Menu
                                id="profile-menu"
                                anchorEl={anchorEl}
                                open={anchorEl}
                                onClose={handleMenuClose}
                                slotProps={{
                                    list: {
                                        'aria-labelledby': 'basic-button',
                                    },
                                }}
                            >
                                <MenuItem onClick={() => {
                                    handleMenuClose();
                                    setAvatarDialogOpen(true);
                                }}>Profile picture</MenuItem>
                                <Divider />
                                <MenuItem onClick={() => apiService.keycloak.logout()}>Logout</MenuItem>
                            </Menu>
                            <AvatarDialog open={avatarDialogOpen} setOpen={setAvatarDialogOpen} avatarUpdateCallback={setUserAvatar}></AvatarDialog>
                        </Fragment>
                }
            </Box >
        )
    }
}