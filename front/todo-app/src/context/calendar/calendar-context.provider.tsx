import dayjs from "dayjs";
import React from "react";
import { CalendarContext } from "./calendar-context";

export const CalendarProvider: React.FC<{
  children: React.ReactNode;
}> = ({ children }) => {
  const [date, setDate] = React.useState(dayjs());


  return (
    <CalendarContext.Provider
      value={{ date, setDate }}
    >
      {children}
    </CalendarContext.Provider>
  );
};
