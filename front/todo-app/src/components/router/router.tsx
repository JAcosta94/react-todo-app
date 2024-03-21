import { Route, Routes } from "react-router-dom";
import { routes } from "./routes";

export function Router() {
  return (
    <Routes>
      {routes.map((element) => {
        return <Route path={element.route} key={element.pageName}  element={ element.component } />;
      })}
    </Routes>
  );
}
