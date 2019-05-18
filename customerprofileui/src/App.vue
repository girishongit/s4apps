<template>
  <div id="app">
    <Popup/>
    <div id="banner">
      <label>SAP S4H POC on Cloud Foundry</label>
    </div>
    <div id="nav">
      <div>
        <router-link to="/">
          <div class="navbutton">Profile Config</div>
        </router-link>
        <div class="navbuttonseperator"/>
        <router-link to="/about">
          <div class="navbutton">About</div>
        </router-link>
        <div class="navbuttonseperator"/>
        <router-link to="/contributers">
          <div class="navbutton">Contributers</div>
        </router-link>
      </div>
    </div>
    <router-view/>
  </div>
</template>

<script>
import https from "@/jslib/https";
import Popup from "@/components/Popup.vue";
import { EventBus } from "@/jslib/event-bus.js";

export default {
  components: {
    Popup
  },

  created() {
    https.get("/api/getToken").then(
      () =>
        EventBus.$emit("Popup", {
          popupType: "Information",
          title: "Authentication",
          message:
            "Successfully Logged in.! You are now accessing Customer profile api's",
          button1: false
        }),
      () => this.$router.push("/error")
    );
  }
};
</script>


<style>
@import "https://fonts.googleapis.com/css?family=Work+Sans|IBM+Plex+Sans|Signika|Bree+Serif|Roboto|Pacifico";
#app {
  position: absolute;
  left: 0px;
  top: 0px;
  height: 100%;
  width: 100%;
  display: block;
  overflow-y: scroll;
}

#app::-webkit-scrollbar {
  width: 2px;
}

#banner {
  position: fixed;
  width: 100%;
  height: 75px;
  background-color: #001f3f;
  color: #339cc7;
  font-weight: bold;
  -webkit-box-shadow: 0px 3px 5px 0px #339cc7;
  -moz-box-shadow: 0px 3px 5px 0px#339cc7;
  box-shadow: 0px 2px 3px 0px #b7b9b9;
  display: flex;
  flex-direction: row;
  justify-content: center;
}

#banner > label {
  margin-top: 20px;
  font-family: "Work Sans";
  font-size: 1.8em;
}

#nav {
  margin-top: 75px;
  width: 100%;
  height: 50px;
  display: flex;
  flex-direction: row;
  justify-content: center;
}

#nav > div {
  margin-top: 20px;
  border-radius: 10px;
  height: 35px;
  width: 80%;
  background-color: #339cc7;
  text-align: center;
  display: flex;
  flex-direction: row;
  justify-content: center;
  color: #001f3f;
}

a {
  text-decoration: none;
}

.navbutton {
  font-family: "Roboto";
  letter-spacing: 0.05em;
  font-size: 1em;
  font-weight: bold;
  text-decoration: unset;
  margin-top: 2px;
  margin-left: 3px;
  margin-right: 3px;
  font-size: 1.1em;
  width: 200px;
  padding: 4px;
  border: 1px solid transparent;
  color: #001f3f;
}

.navbutton:hover {
  border-radius: 5px;
  border: 1px solid;
  background-color: #001f3f;
  color: #339cc7;
}

.navbuttonseperator {
  border: 1px solid;
  height: 24px;
  margin-top: 5px;
}

.navbutton:active {
  border-radius: 5px;
  border: 1px solid;
  color: #001f3f;
  background-color: #339cc7;
}
</style>
