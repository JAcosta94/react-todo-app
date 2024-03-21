import {
  Divider,
  IconButton,
  List,
  ListItem,
  ListItemButton,
  ListItemIcon,
  ListItemText,
  SxProps,
  Theme,
  useTheme,
} from "@mui/material";
import ChevronLeftIcon from "@mui/icons-material/ChevronLeft";
import ChevronRightIcon from "@mui/icons-material/ChevronRight";
import { SideNavDrawer } from "./navigation-drawer.styles";
import { useNavigationDrawerContext } from "../../../context/navigation-drawer/navigation-drawer-context.utils";
import { NavigationDrawerHeader } from "../navigation-drawer-header/navigation-drawer-header";
import { Link, useLocation } from "react-router-dom";
import { routes } from "../../router/routes";

export function NavigationDrawer() {
  const theme = useTheme();
  const context = useNavigationDrawerContext();

  return (
    <SideNavDrawer variant="permanent" open={context?.isOpen}>
      <NavigationDrawerHeader>
        <IconButton onClick={context?.switchDrawer}>
          {theme.direction === "rtl" ? (
            <ChevronRightIcon />
          ) : (
            <ChevronLeftIcon />
          )}
        </IconButton>
      </NavigationDrawerHeader>

      <Divider />

      <List sx={listStyle}>
        {routes
          .filter((x) => x.pageName !== "Home")
          .map((routeElement) => (
            <ListItem
              component={Link}
              to={routeElement.route}
              key={routeElement.pageName}
              disablePadding
              sx={{ color: theme.palette.primary.light, display: "block" }}
            >
              <ListItemButton
                sx={styleLink(routeElement.route, context?.isOpen)}
              >
                <ListItemIcon sx={styleIcon(context?.isOpen)}>
                  {routeElement.iconButton}
                </ListItemIcon>
                <ListItemText
                  primary={routeElement.pageName}
                  sx={{ opacity: context?.isOpen ? 1 : 0 }}
                />
              </ListItemButton>
            </ListItem>
          ))}
      </List>
    </SideNavDrawer>
  );
}
const listStyle = { 
  paddingLeft: "10px", 
  paddingRight: "10px" 
};

function styleLink(route: string, isOpen?: boolean): SxProps<Theme> {
  const location = useLocation();

  let result: SxProps<Theme> = {
    minHeight: 48,
    justifyContent: isOpen ? "initial" : "center",
    px: 2.5,
    "&:hover": {
      borderRadius: "20px",
      padding: "5px",
      backgroundColor: "#3b3b3b",
    },
  };

  if (location.pathname === `/${route}`) {
    result = {
      ...result,
      backgroundColor: "#222222",
      borderRadius: "20px",
      padding: "10px",
    };
  }

  return result;
}

function styleIcon(isOpen?: boolean): SxProps<Theme> {
  return {
    minWidth: 0,
    mr: isOpen ? 3 : "auto",
    justifyContent: "center",
  };
}
