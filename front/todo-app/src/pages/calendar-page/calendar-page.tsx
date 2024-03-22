import { Box, Divider, Grid, useMediaQuery, useTheme } from "@mui/material";
import { Calendar } from "../../components/calendar/calendar";
import NextEvents from "../../components/next-events/next-events";
import { EventDetail } from "../../components/event-detail/event-detail";
import { CalendarProvider } from "../../context/calendar/calendar-context.provider";

export function CalendarPage() {
  const theme = useTheme();
  console.log("asd");
  
  return (
    <Box>
      <CalendarProvider>
        <Grid container spacing={2}>
          <Grid item xs={12} sm={6} md={5} xl={2}>
              <Calendar></Calendar>     
          </Grid>
          <Grid item xs={12} sm={1} md={1} xl={1}>    
              <Divider orientation={useMediaQuery(theme.breakpoints.down("sm")) ? "horizontal" : "vertical"} />       
          </Grid>
          <Grid item xs={12} sm={5} md={6} xl={9}>   
              <EventDetail></EventDetail>
          </Grid>
          <Grid item xs={12}>
            <NextEvents></NextEvents>
          </Grid>
        </Grid>
      </CalendarProvider>
    </Box>
  );
}
