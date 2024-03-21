import { ThemeProvider, createTheme } from "@mui/material/styles";
import AppContainer from "./components/layout/app-container/app-container";
import { BrowserRouter } from "react-router-dom";

const darkTheme = createTheme({
  palette: {
    mode: "dark",
  },
});

function App() {
  return (
    <BrowserRouter>
      <ThemeProvider theme={darkTheme}>
        <AppContainer></AppContainer>
      </ThemeProvider>
    </BrowserRouter>
  );
}

export default App;
