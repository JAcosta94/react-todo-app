import { DateCalendar, LocalizationProvider } from "@mui/x-date-pickers";
import { AdapterDayjs } from "@mui/x-date-pickers/AdapterDayjs";
import dayjs from "dayjs";
import { useCalendarContext } from "../../context/calendar/calendar-context.utils";

export function Calendar() {
  const context = useCalendarContext();
 
  return (
    <LocalizationProvider dateAdapter={AdapterDayjs}>
      <DateCalendar defaultValue={dayjs()} onChange={(newValue) => context.setDate(newValue)} />
    </LocalizationProvider>
  );
}



