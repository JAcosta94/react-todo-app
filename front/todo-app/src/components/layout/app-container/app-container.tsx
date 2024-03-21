import Box from "@mui/material/Box";
import CssBaseline from "@mui/material/CssBaseline";
import { NavigationDrawerHeader } from "../navigation-drawer-header/navigation-drawer-header";
import { NavigationDrawer } from "../navigation-drawer/navigation-drawer";
import { NavigationDrawerProvider } from "../../../context/navigation-drawer/navigation-drawer-context.provider";
import { TopBar } from "../top-bar/top-bar";
import { Router } from "../../router/router";

export default function AppContainer() {
  return (
    <Box sx={{ display: "flex" }}>
      <CssBaseline />

      <NavigationDrawerProvider>
        <TopBar></TopBar>
        <NavigationDrawer></NavigationDrawer>
      </NavigationDrawerProvider>

      <Box component="main" sx={{ flexGrow: 1, p: 3 }}>
        <NavigationDrawerHeader />
        <Router></Router>
      </Box>
    </Box>
  );
}
