import { createContext } from "react";

export type NavigationDrawerContextType = {
  isOpen: boolean;
  switchDrawer: () => void;
};

export const NavigationDrawerContext =
  createContext<NavigationDrawerContextType | null>(null);
