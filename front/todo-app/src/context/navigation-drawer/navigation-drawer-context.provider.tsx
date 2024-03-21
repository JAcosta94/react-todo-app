import React from "react";
import { NavigationDrawerContext } from "./navigation-drawer-context";

export const NavigationDrawerProvider: React.FC<{
  children: React.ReactNode;
}> = ({ children }) => {
  const [isOpen, setOpen] = React.useState(true);

  const switchDrawer = () => {
    setOpen(!isOpen);
  };

  return (
    <NavigationDrawerContext.Provider
      value={{ isOpen, switchDrawer }}
    >
      {children}
    </NavigationDrawerContext.Provider>
  );
};
