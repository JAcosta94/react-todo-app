import { TodoPage } from "../../pages/todo-page/todo-page";
import { CalendarPage } from "../../pages/calendar-page/calendar-page";
import ErrorPage from "../../pages/error-page/error-page";
import { ReactNode } from "react";
import ChecklistIcon from '@mui/icons-material/Checklist';
import CalendarMonthIcon from '@mui/icons-material/CalendarMonth';

export interface AppRoute {
  route: string,
  pageName: string,
  component: ReactNode,
  errorPage?: ReactNode,
  iconButton?: ReactNode,
  children?: AppRoute[]
}

export const routes: AppRoute[] = [
  {
    route: '/',
    pageName: 'Home',
    component: <div> Hello World!</div>,
    errorPage: <ErrorPage />    
  },    
  {
    route: 'calendar',
    pageName: 'Calendar',
    component: <CalendarPage />,
    iconButton: <CalendarMonthIcon />
  },
  {
    route: 'todo-list',
    pageName: 'To Do Tasks',
    component: <TodoPage />,
    iconButton: <ChecklistIcon />
  }
]
