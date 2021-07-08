import keycloak from "./util/keycloak";
import { ReactKeycloakProvider } from "@react-keycloak/web";
import APIUtil from "./util/APIUtil";
import Home from "./views/Home";
import TopMenu from "./views/TopMenu";

import "bootstrap/dist/css/bootstrap.min.css";
import "./App.css";
import Loader from "./views/Loader";

function App() {
  return (
    <ReactKeycloakProvider
      authClient={keycloak}
      initOptions={{ onLoad: "login-required" }}
      LoadingComponent={<Loader />}
      onEvent={(event, error) => {
        if (keycloak.authenticated) {
          APIUtil.setAuthToken(keycloak.token);
        } else {
          APIUtil.setAuthToken("");
        }
      }}
    >
      <div className="as-wrapper">
        <TopMenu />
        <Home />
      </div>
    </ReactKeycloakProvider>
  );
}

export default App;
