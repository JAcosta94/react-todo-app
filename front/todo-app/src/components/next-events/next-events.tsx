import {
  Button,
  Card,
  CardActions,
  CardContent,
  Typography,
} from "@mui/material";

export default function NextEvents() {
  return (
    <Card sx={{ minWidth: 275 }}>
      <CardContent>
        <Typography sx={{ fontSize: 14 }} color="text.secondary" gutterBottom>
          Next Events
        </Typography>
        <Typography variant="h5" component="div">
          I don't know
        </Typography>
        <Typography sx={{ mb: 1.5 }} color="text.secondary">
          Everyday
        </Typography>
        <Typography variant="body2">
          Anytime          
        </Typography>
      </CardContent>
      <CardActions>
        <Button size="small">Learn More</Button>
      </CardActions>
    </Card>
  );
}
