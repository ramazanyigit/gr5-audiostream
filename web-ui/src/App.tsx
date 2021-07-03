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
          // axios
          //   .post(
          //     "/api/streaming/play",
          //     {
          //       songId: "d6867156-3f13-4eba-a0b7-9ddd8e7f6f52",
          //       playOffset: 0,
          //     },
          //     {
          //       headers: {
          //         Authorization: "Bearer " + keycloak.token,
          //       },
          //     }
          //   )
          //   .then(({ data }) => {
          //     console.log(data);
          //   });
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
