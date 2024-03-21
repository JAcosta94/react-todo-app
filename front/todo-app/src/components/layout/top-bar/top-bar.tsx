import { IconButton, Toolbar, Typography } from "@mui/material";
import MenuIcon from '@mui/icons-material/Menu';
import { AppTopBar } from './top-bar.styles';
import { useNavigationDrawerContext } from "../../../context/navigation-drawer/navigation-drawer-context.utils";

export function TopBar() {
    const context = useNavigationDrawerContext();

    return (        
        <AppTopBar position="fixed" open={context?.isOpen}>
            <Toolbar>
                <IconButton
                    color="inherit"
                    aria-label="open drawer"
                    onClick={context?.switchDrawer}
                    edge="start"
                    sx={{
                        marginRight: 5,
                        ...(context?.isOpen && { display: 'none' }),
                    }}
                >
                <MenuIcon />
                </IconButton>
                <Typography variant="h6" noWrap component="div">
                    My To Do App
                </Typography>
            </Toolbar>
        </AppTopBar>
    );
}