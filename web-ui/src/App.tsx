import keycloak from "./auth/keycloak";
import logo from "./logo.svg";
import "./App.css";
import { ReactKeycloakProvider } from "@react-keycloak/web";
import axios from "axios";

const initOptions = { onLoad: "login-required" };

function App() {
  return (
    <ReactKeycloakProvider
      authClient={keycloak}
      initOptions={initOptions}
      LoadingComponent={<>Loading...</>}
      onEvent={(event, error) => {
        console.log(event, keycloak.authenticated, keycloak.token);
        if (keycloak.authenticated) {
          axios
            .get("/api/streaming/ping", {
              headers: {
                Authorization: "Bearer " + keycloak.token,
              },
            })
            .then(({ data }) => {
              console.log(data);
            });
        }
      }}
    >
      {" "}
      <div className="App">
        <header className="App-header">
          <img src={logo} className="App-logo" alt="logo" />
          <p>
            Edit <code>src/App.tsx</code> and save to reload.
          </p>
          <a
            className="App-link"
            href="https://reactjs.org"
            target="_blank"
            rel="noopener noreferrer"
          >
            Learn React
          </a>
        </header>
      </div>
    </ReactKeycloakProvider>
  );
}

export default App;
