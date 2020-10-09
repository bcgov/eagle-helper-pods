"use strict";

const KeyCloakClient = require("./keycloak");

const settings = {
  clientId: process.env.KC_CLIENT_ID,
  clientSecret: process.env.KC_CLIENT_SECRET,
  appClientId: process.env.APP_CLIENT_ID,
  realmId: process.env.KC_REALM_ID,
  ssoHost: process.env.SSO_HOST,
  appHost: process.env.APP_HOST
}

const kc = new KeyCloakClient(settings);

kc.remmoveUris();
