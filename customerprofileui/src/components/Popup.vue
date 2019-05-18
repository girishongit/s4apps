<template>
  <div id="popup" v-show="showpopup">
    <div id="model-popup" v-show="popupType != 'LOADER'">
      <img src="@/assets/warning.png" v-show="popupType === 'WARNING'">
      <img src="@/assets/confirmation.png" v-show="popupType === 'CONFIRMATION'">
      <img src="@/assets/information.png" v-show="popupType === 'INFORMATION'">
      <img src="@/assets/error.png" v-show="popupType === 'ERROR'">
      <label id="title">{{title}}</label>
      <label id="msg">{{message}}</label>
      <div>
        <button @click="confirm" v-show="button1exist">{{button1Name}}</button>
        <button @click="closepopup">{{closeButtonName}}</button>
      </div>
    </div>
    <div id="model-popup-loader" v-show="popupType === 'LOADER'">
      <div>
        <Loader/>
      </div>
      <div>
        <label>{{title}}</label>
      </div>
    </div>
  </div>
</template>

<script>
import { EventBus } from "@/jslib/event-bus.js";
import Loader from "@/components/LoadSpinner";
export default {
  name: "popup",
  components: {
    Loader
  },
  data() {
    return {
      showpopup: false,
      types: ["CONFIRMATION", "INFORMATION", "WARNING", "ERROR", "LOADER"],
      popupType: "",
      button1Name: "",
      closeButtonName: "",
      title: "",
      message: "",
      confirmFunction: "",
      button1exist: true
    };
  },
  methods: {
    validateValues(data) {
      const _type = this.types.includes(data.popupType.toUpperCase())
        ? data.popupType.toUpperCase()
        : this.types[0];
      this.popupType = _type;
      this.title = this.isEmpty(data.title)
        ? _type === "CONFIRMATION"
          ? "Confirmed...?"
          : _type === "WARNING"
          ? "Warning...!"
          : _type === "INFORMATION"
          ? "Information"
          : _type === "ERROR"
          ? "Error."
          : _type === "LOADER"
          ? "Processing..."
          : "Title goes here"
        : data.title;
      this.message = this.isEmpty(data.message)
        ? _type === "CONFIRMATION"
          ? "Are you sure you want to do this ?"
          : _type === "WARNING"
          ? "Are you sure you want to proceed ?"
          : _type === "INFORMATION"
          ? "This to for your information."
          : _type === "ERROR"
          ? "Busted... :("
          : "Message printed here"
        : typeof data.message === "object"
        ? data.message.response.status
          ? data.message.response.status +
            " : " +
            data.message.response.statusText
          : JSON.stringify(data.message)
        : data.message;
      this.button1Name = this.isEmpty(data.button1Name)
        ? _type === "CONFIRMATION"
          ? "Confirm"
          : _type === "WARNING"
          ? "Yes"
          : _type === "INFORMATION"
          ? "Ok"
          : "button1Name"
        : data.button1Name;
      this.closeButtonName = this.isEmpty(data.closeButtonName)
        ? "Close"
        : data.closeButtonName;

      if (typeof data.confirmFunction != "function") {
        this.confirmFunction = function() {
          alert("No Action specified");
        };
      } else {
        this.confirmFunction = data.confirmFunction;
      }

      this.button1exist =
        typeof data.button1 != "boolean"
          ? _type != "ERROR"
            ? true
            : false
          : data.button1;
    },
    closepopup: function() {
      this.showpopup = false;
    },
    confirm: function() {
      this.confirmFunction();
      this.closepopup();
    },
    isEmpty: function(value) {
      return (
        (value && (typeof value === "string" && value.trim() === "")) ||
        value === null ||
        value === undefined
      );
    }
  },
  created() {
    EventBus.$on("Popup", data => {
      if (data) this.validateValues(data);
      else this.validateValues(null);
      setTimeout(() => {
        this.showpopup = true;
      }, 5);
    });
    EventBus.$on("ClosePopup", () => {
      setTimeout(() => {
        this.showpopup = false;
      }, 5);
    });
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
@import "https://fonts.googleapis.com/css?family=Work+Sans|IBM+Plex+Sans|Signika|Bree+Serif|Roboto|Pacifico";
#popup {
  position: fixed;
  z-index: 100;
  width: 100%;
  height: 100%;
  display: flex;
  background-color: rgba(200, 200, 200, 0.5);
  justify-content: center;
}

#popup > #model-popup,
#popup > #model-popup-loader {
  width: 300px;
  height: 230px;
  background-color: whitesmoke;
  border-radius: 10px;
  margin-top: 15%;
  animation: fadeopen 0.2s;
  display: block;
  border: 1px solid #575757;
}

@keyframes fadeopen {
  0% {
    transform: scale(0);
  }
  20% {
    transform: scale(0.2);
  }
  40% {
    transform: scale(0.4);
  }
  60% {
    transform: scale(0.6);
  }
  80% {
    transform: scale(0.8);
  }
  100% {
    transform: scale(1);
  }
}

#popup > #model-popup > img {
  height: 60px;
  width: 60px;
  margin-top: 10px;
  margin-left: 38%;
  user-select: none;
}

#popup > #model-popup > #title,
#popup > #model-popup-loader > div:last-child > label {
  min-width: 280px;
  display: block;
  padding-left: 10px;
  padding-right: 10px;
  display: flex;
  justify-content: center;
  font-family: "IBM Plex Sans", Roboto;
  font-size: 1.2em;
  font-weight: bold;
  user-select: none;
}

#popup > #model-popup > #title {
  word-wrap: none;
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
}

#popup > #model-popup > #msg {
  min-width: 280px;
  min-height: 80px;
  max-width: 280px;
  max-height: 80px;

  display: flex;
  justify-content: center;
  flex-direction: column;
  padding-left: 10px;
  padding-right: 10px;
  font-family: "IBM Plex Sans", Roboto;
  font-size: 1em;
  text-align: center;
  user-select: none;
  overflow: hidden;
  text-overflow: ellipsis;
}

#popup > #model-popup > div {
  display: flex;
  justify-content: center;
}

#popup > #model-popup > div > button {
  margin-top: 10px;
  height: 30px;
  min-width: 80px;
  font-family: "IBM Plex Sans", Roboto;
  padding-left: 8px;
  padding-right: 8px;
  font-size: 1em;
  border-radius: 4px;
  outline: none;
  border: 1px solid;
  margin-left: 10px;
  user-select: none;
}

#popup > #model-popup > div > button:first-child:hover {
  background-color: #ffab0d;
  border-color: #e8820c;
  font-weight: bold;
}

#popup > #model-popup > div > button:last-child:hover {
  background-color: #656565;
  border-color: #333333;
  color: white;
}

#popup > #model-popup > div > button:first-child:active,
#popup > #model-popup > div > button:last-child:active {
  background-color: #a5a5a5;
  border-color: #333333;
  color: black;
}

#popup > #model-popup-loader > div:first-child {
  margin-top: 20%;
  margin-left: 40%;
}

#popup > #model-popup-loader > div:last-child {
  display: flex;
  flex-direction: row;
  justify-content: center;
  margin-top: 10px;
}
</style>
