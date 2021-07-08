import Keycloak from "keycloak-js";
import { config } from "../config";

const keycloak = Keycloak({
  url: `${config.url.KEYCLOAK_BASE_URL}/auth`,
  realm: "audiostream",
  clientId: "audiostream-ui",
});

export default keycloak;
