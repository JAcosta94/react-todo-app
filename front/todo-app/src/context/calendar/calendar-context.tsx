import dayjs from "dayjs";
import { createContext } from "react";

export type CalendarContextType = {
  date: dayjs.Dayjs;
  setDate: (newDate: dayjs.Dayjs) => void;
};

export const CalendarContext =
  createContext<CalendarContextType | null>(null);
