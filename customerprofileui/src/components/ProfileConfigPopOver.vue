<template>
  <div id="popoveradd">
    <div id="portfoliolistaddition">
      <div id="fieldblock">
        <div id="fieldhead">
          <label>Client</label>
        </div>
        <div id="fieldvalues">
          <label>Id :</label>
          <input v-model="data.client" type="text" class="idfield" :readonly="insertlevel > 1">
          <label>Text :</label>
          <input class="valuefield" v-model="data.clienttext" :readonly="insertlevel > 1">
        </div>
      </div>
      <div id="fieldblock">
        <div id="fieldhead">
          <label>Country</label>
        </div>
        <div id="fieldvalues">
          <label>Id :</label>
          <input class="idfield" v-model="data.countrycode" :readonly="insertlevel > 2">
          <label>Text :</label>
          <input class="valuefield" v-model="data.countrycodetext" :readonly="insertlevel > 2">
        </div>
      </div>
      <div id="fieldblock">
        <div id="fieldhead">
          <label>Sales Org</label>
        </div>
        <div id="fieldvalues">
          <label>Id :</label>
          <input class="idfield" v-model="data.sorg" :readonly="insertlevel > 3">
          <label>Text :</label>
          <input class="valuefield" v-model="data.sorgtext" :readonly="insertlevel > 3">
        </div>
      </div>
      <div id="fieldblock">
        <div id="fieldhead">
          <label>Distribution Channel</label>
        </div>
        <div id="fieldvalues">
          <label>Id :</label>
          <input class="idfield" v-model="data.delvch" :readonly="insertlevel > 4">
          <label>Text :</label>
          <input class="valuefield" v-model="data.delvchtext" :readonly="insertlevel > 4">
        </div>
      </div>
      <div id="fieldblock">
        <div id="fieldhead">
          <label>Division</label>
        </div>
        <div id="fieldvalues">
          <label>Id :</label>
          <input class="idfield" v-model="data.div" :readonly="insertlevel > 5">
          <label>Text :</label>
          <input class="valuefield" v-model="data.divtext" :readonly="insertlevel > 5">
        </div>
      </div>
      <div id="fieldblock">
        <div id="fieldhead">
          <label>Business Area</label>
        </div>
        <div id="fieldvalues">
          <label>Id :</label>
          <input class="idfield" v-model="data.businessarea" :readonly="insertlevel > 6">
          <label>Text :</label>
          <input class="valuefield" v-model="data.businessareatext" :readonly="insertlevel > 6">
        </div>
      </div>
      <div id="fieldblock">
        <div id="fieldhead">
          <label>Profile</label>
        </div>
        <div id="fieldvalues">
          <label>Id :</label>
          <input class="idfield" v-model="data.profile" :readonly="insertlevel > 7">
          <label>Text :</label>
          <input class="valuefield" v-model="data.profiletext" :readonly="insertlevel > 7">
        </div>
      </div>
      <div id="fieldblock">
        <div id="fieldhead">
          <label>Subprofile</label>
        </div>
        <div id="fieldvalues">
          <label>Id :</label>
          <input class="idfield" v-model="data.subprofile" :readonly="insertlevel > 8">
          <label>Text :</label>
          <input class="valuefield" v-model="data.subprofiletext" :readonly="insertlevel > 8">
        </div>
        <div id="fieldvalues">
          <label>DataType</label>
          <select
            class="valuefield"
            name="datatype"
            v-model="data.subprofiledatatype"
            :disabled="insertlevel > 8"
            style="margin-top: 5px;"
          >
            <option v-for="(item,index) in datatype" :key="index" :value="item">{{item}}</option>
          </select>
        </div>
      </div>
      <div id="fieldbutton">
        <button @click="closepopup()">Cancel</button>
        <button @click="insertdata()">Insert</button>
      </div>
    </div>
  </div>
</template>

<script>
import https from "@/jslib/https";
export default {
  props: {
    data: {},
    insertlevel: null
  },
  data() {
    return {
      datatype: ["String", "Integer", "Boolean", "Date", "Decimal"]
    };
  },
  methods: {
    closepopup() {
      this.$parent.closepopup();
    },
    prepareProfileText(level) {
     /* if (level === 2) {
        return {
          client: this.data.client,
          id: this.data.countrycode,
          language: "EN",
          type: "COUNTRYCODE",
          text: this.data.countrycodetext
        };
      }  else if (level === 3) {
        return {
          client: this.data.client,
          id: this.data.sorg,
          language: "EN",
          type: "SORG",
          text: this.data.sorgtext
        };
      }  else if (
        level === 4
      ) {
        return {
          client: this.data.client,
          id: this.data.delvch,
          language: "EN",
          type: "DELVCHL",
          text: this.data.delvchtext
        };
      } else if (level === 5) {
        return {
          client: this.data.client,
          id: this.data.div,
          language: "EN",
          type: "DIV",
          text: this.data.divtext
        };
      } else */ if (level === 6) {
        return {
          client: this.data.client,
          id: this.data.businessarea,
          language: "EN",
          type: "BUSINESSAREA",
          text: this.data.businessareatext
        };
      } else if (level === 7) {
        return {
          client: this.data.client,
          id: this.data.profile,
          language: "EN",
          type: "PROFILE",
          text: this.data.profiletext
        };
      } else if (level === 8) {
        return {
          client: this.data.client,
          id: this.data.subprofile,
          language: "EN",
          type: "SUBPROFILE",
          text: this.data.subprofiletext
        };
      }
    },
    insertdata() {
      let profilepayload = [
        {
          client: this.data.client,
          countrycode: this.data.countrycode,
          sorg: this.data.sorg,
          delvchl: this.data.delvch,
          div: this.data.div,
          businessarea: this.data.businessarea,
          profile: this.data.profile,
          subprofile: this.data.subprofile,
          subprofiletype: this.data.subprofiledatatype
        }
      ];

      let profiletextpayload = [];
      for (let i = this.insertlevel; i <= 8; i++) {
        profiletextpayload.push(this.prepareProfileText(i));
      }

      https
        .post("/api/profile", profilepayload)
        .then(() => {
          https
            .post("/api/profiletext", profiletextpayload)
            .then(() => {
              this.$parent.closepopup();
              this.$parent.loadData();
            })
            .catch(err => {
              alert(err);
            });
        })
        .catch(err => {
          alert(err);
        });
    }
  },
  created() {}
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
#popoveradd {
  position: fixed;
  z-index: 100;
  width: 100%;
  height: 100%;
  display: flex;
  background-color: rgba(200, 200, 200, 0.1);
  justify-content: center;
}

#portfoliolistaddition {
  width: 400px;
  height: 700px;
  border: 1px solid;
  margin-top: 20px;
  border-radius: 20px;
  background-color: whitesmoke;
}

#fieldblock:first-child {
  margin-top: 15px;
}

#fieldblock {
  margin-top: 20px;
  margin-left: 10px;
  margin-right: 10px;
}

#fieldblock > #fieldhead {
  padding-left: 10px;
  font-family: "IBM Plex Sans";
  font-weight: bold;
  font-size: 1em;
  font-style: italic;
  color: #996e58;
}

#fieldblock > #fieldhead::after {
  content: ":";
}
#fieldblock > #fieldvalues {
  margin-top: 12px;
  padding-left: 30px;
}

#fieldblock > #fieldvalues > label {
  font-family: "Roboto";
  font-size: 0.9em;
  margin-right: 4px;
}

#fieldblock > #fieldvalues > .idfield {
  font-family: "Roboto";
  font-size: 0.9em;
  width: 60px;
  margin-right: 20px;
  overflow: hidden;
  text-overflow: ellipsis;
  outline: none;
  border: 0px;
  border-bottom: 2px solid rgb(75, 75, 185);
  background-color: transparent;
  padding-left: 4px;
}

#fieldblock > #fieldvalues > .idfield:read-only {
  border-bottom: 1px solid gray;
}

#fieldblock > #fieldvalues > .valuefield {
  font-family: "Roboto";
  font-size: 1em;
  width: 180px;
  overflow: hidden;
  text-overflow: ellipsis;
  outline: none;
  border: 0px;
  padding-left: 5px;
  background-color: transparent;
  border-bottom: 2px solid rgb(75, 75, 185);
  border-top-left-radius: 5px;
  border-top-right-radius: 5px;
}

#fieldblock > #fieldvalues > .valuefield:read-only {
  border-bottom: 1px solid gray;
}

#fieldblock > #fieldvalues > .valuefield:focus {
  border-bottom: 2px solid blue;
}

#fieldbutton {
  margin-left: 100px;
  margin-top: 25px;
}

#fieldbutton > button {
  width: 100px;
  margin-left: 5px;
  height: 30px;
  border-radius: 5px;
  outline: none;
}
</style>
