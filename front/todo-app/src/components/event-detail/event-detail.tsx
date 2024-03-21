import { Box } from "@mui/system";
import { useCalendarContext } from "../../context/calendar/calendar-context.utils";
import { Grid } from "@mui/material";

export function EventDetail() {
  const context = useCalendarContext();

  return (
    <Box>
      <Grid direction="row" container spacing={0.5}>
          <Grid item xs={1}>            
            <Grid direction="column" container spacing={0.5}>
              <Grid item xs={6}>
                {context.date.format('DD/MM')}   
              </Grid>
              <Grid item xs={6}>
                {context.date.format('dddd')}
              </Grid>
            </Grid>
          </Grid>

          <Grid item xs={11}>
            {context.date.format('YYYY')}
          </Grid>
      </Grid>      
    </Box>
  );
}
