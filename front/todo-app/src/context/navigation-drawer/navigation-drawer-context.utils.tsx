import { useContext } from "react";
import { NavigationDrawerContext } from "./navigation-drawer-context";

export const useNavigationDrawerContext = () => {
    const context = useContext(NavigationDrawerContext);
  
    if (!context) {
      throw new Error(
        "useNavigationDrawerContext must be used inside the NavigationDrawerProvider"
      );
    }
  
    return context;
};