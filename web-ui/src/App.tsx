import { ReactKeycloakProvider } from "@react-keycloak/web";
import { view } from "@risingstack/react-easy-state";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import { Container } from "react-bootstrap";

import keycloak from "./util/keycloak";
import APIUtil from "./util/APIUtil";
import Home from "./views/Home";
import TopMenu from "./views/TopMenu";
import Loader from "./views/Loader";

import "bootstrap/dist/css/bootstrap.min.css";
import "./App.css";
import Player from "./views/Player";
import SearchSong from "./views/SearchSong";
import PlaylistDetail from "./views/PlaylistDetail";
import SongRecommendation from "./views/SongRecommendation";
import Top100Chart from "./views/Top100Chart";

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
        <Router>
          <TopMenu />
          <Container fluid>
            <div
              className="mb-4"
              style={{ marginLeft: "-15px", marginRight: "-15px" }}
            >
              <Player />
            </div>
            <div>
              <SongRecommendation />
            </div>
            <Switch>
              <Route path="/top-100">
                <Top100Chart />
              </Route>
              <Route path="/playlist/:id">
                <PlaylistDetail />
              </Route>
              <Route path="/search">
                <SearchSong />
              </Route>
              <Route path="/">
                <Home />
              </Route>
            </Switch>
          </Container>
        </Router>
      </div>
    </ReactKeycloakProvider>
  );
}

export default view(App);
