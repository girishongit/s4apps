<template>
  <div id="main">
    <div id="content">
      <div id="navigation">
        <label class="highlightnavigate">Client</label> ➜
        <label :class="clientIndex === -1 ? 'navigate' : 'highlightnavigate'">Country</label> ➜
        <label
          :class="clientIndex === -1  || countryIndex === -1 ? 'navigate' : 'highlightnavigate'"
        >Sales Org</label>
        ➜
        <label
          :class="clientIndex === -1  || countryIndex === -1 || sorgIndex === -1 ? 'navigate' : 'highlightnavigate'"
        >Delv. Channel</label> ➜
        <label
          :class="clientIndex === -1  || countryIndex === -1 || sorgIndex === -1 || delvIndex === -1 ? 'navigate' : 'highlightnavigate'"
        >Division</label> ➜
        <label
          :class="clientIndex === -1  || countryIndex === -1 || sorgIndex === -1 || delvIndex === -1 || divIndex === -1 ? 'navigate' : 'highlightnavigate'"
        >Business Area</label> ➜
        <label
          :class="clientIndex === -1  || countryIndex === -1 || sorgIndex === -1 || delvIndex === -1 || divIndex === -1 || baIndex === -1 ? 'navigate' : 'highlightnavigate'"
        >Profile</label> ➜
        <label
          :class="clientIndex === -1  || countryIndex === -1 || sorgIndex === -1 || delvIndex === -1 || divIndex === -1 || baIndex === -1 || profileIndex === -1 ? 'navigate' : 'highlightnavigate'"
        >Sub Profile</label>
      </div>
      <div class="profilelist">
        <div
          v-for="(client, clientindex) in client"
          :key="'client'+clientindex"
          @click.stop="clientIndex = clientIndex === clientindex ?  -1 : clientindex"
        >
          <div class="attr client" :class="clientIndex === clientindex ? 'selected' : ''">
            {{client.id}}
            <button @click.stop="deletehere(1,client.id)">✗</button>
          </div>

          <div
            v-for="(country, countryindex) in client.countryCode"
            :key="'country'+countryindex"
            @click.stop="countryIndex = countryIndex === countryindex ? -1 : countryindex"
            v-show="clientIndex === clientindex"
          >
            <div class="attr country" :class="countryIndex ===countryindex ? 'selected' : ''">
              {{country.text}}
              <button @click.stop="deletehere(2,country.id)">✗</button>
            </div>

            <div
              v-for="(sorg, soindex) in country.salesOrg"
              :key="'sorg'+soindex"
              @click.stop="sorgIndex = sorgIndex === soindex ? -1 : soindex "
              v-show="countryIndex ===countryindex"
            >
              <div class="attr sorg" :class="sorgIndex === soindex ? 'selected' : ''">
                {{sorg.text}}
                <button @click.stop="deletehere(3,sorg.id)">✗</button>
              </div>
              <div
                v-for="(delv, dlvindex) in sorg.delivCh"
                :key="'dlv'+dlvindex"
                @click.stop="delvIndex = delvIndex=== dlvindex ?-1 : dlvindex"
                v-show="sorgIndex === soindex"
              >
                <div class="attr delv" :class="delvIndex === dlvindex ? 'selected' : ''">
                  {{delv.text}}
                  <button @click.stop="deletehere(4,delv.id)">✗</button>
                </div>
                <div
                  v-for="(div, divindex) in delv.div"
                  :key="'div'+divindex"
                  @click.stop="divIndex = divIndex === dlvindex ? -1 : dlvindex"
                  v-show="delvIndex === dlvindex"
                >
                  <div class="attr div" :class="divIndex === divindex ? 'selected' : ''">
                    {{div.text}}
                    <button @click.stop="deletehere(5,div.id)">✗</button>
                  </div>
                  <div
                    v-for="(ba, baindex) in div.businessArea"
                    :key="'ba'+baindex"
                    @click.stop="baIndex = baIndex === baindex ? -1 : baindex"
                    v-show="divIndex === divindex"
                  >
                    <div class="attr businessarea" :class="baIndex === baindex ? 'selected' : ''">
                      {{ba.text}}
                      <button @click.stop="deletehere(6,ba.id)">✗</button>
                    </div>
                    <div
                      v-for="(profile, profileindex) in ba.profile"
                      :key="'profile'+profileindex"
                      @click.stop="profileIndex = profileIndex === profileindex ? -1 : profileindex"
                      v-show="baIndex === baindex"
                    >
                      <div
                        class="attr profile"
                        :class="profileIndex === profileindex ? 'selected' : ''"
                      >
                        {{profile.text}}
                        <button @click.stop="deletehere(7,profile.id)">✗</button>
                      </div>
                      <div
                        v-for="(subprofile, subprofileindex) in profile.subprofile"
                        :key="'subprofile'+subprofileindex"
                        v-show="profileIndex === profileindex"
                        @click.stop
                      >
                        <div class="attr subprofile">
                          {{subprofile.text}} : {{subprofile.dataType}}
                          <button
                            @click.stop="deletehere(8,subprofile.id)"
                          >✗</button>
                        </div>
                      </div>
                      <div
                        class="attr subprofile add"
                        @click.stop="addData('subprofile')"
                        v-show="profileIndex === profileindex"
                      >
                        <label>+</label> Sub Profile
                      </div>
                    </div>
                    <div
                      class="attr profile add"
                      @click.stop="addData('profile')"
                      v-show="baIndex === baindex"
                    >
                      <label>+</label> Profile
                    </div>
                  </div>
                  <div
                    class="attr businessarea add"
                    @click.stop="addData('businessarea')"
                    v-show="divIndex === divindex"
                  >
                    <label>+</label> Business Area
                  </div>
                </div>
                <div
                  class="attr div add"
                  @click.stop="addData('div')"
                  v-show="delvIndex === dlvindex"
                >
                  <label>+</label> Division
                </div>
              </div>
              <div
                class="attr delv add"
                @click.stop="addData('distchl')"
                v-show="sorgIndex === soindex"
              >
                <label>+</label> Disturbtion Channel
              </div>
            </div>
            <div
              class="attr sorg add"
              @click.stop="addData('sorg')"
              v-show="countryIndex === countryindex"
            >
              <label>+</label> Sales Org.
            </div>
          </div>
          <div
            class="attr country add"
            @click.stop="addData('country')"
            v-show="clientIndex === clientindex"
          >
            <label>+</label> Country
          </div>
        </div>
        <div class="attr client add" @click.stop="addData('client')">
          <label>+</label> Client
        </div>
      </div>
    </div>
    <Popover v-if="addnew" :data="currentselection" :insertlevel="level"/>
  </div>
</template>

<script>
import https from "@/jslib/https";
import ProfileList from "@/jslib/parseProfileList";
import Popover from "@/components/ProfileConfigPopOver";
import { EventBus } from "@/jslib/event-bus.js";

export default {
  name: "ProfileConfig",
  props: {
    msg: String
  },
  components: {
    Popover
  },
  data() {
    return {
      addnew: false,
      clientIndex: -1,
      countryIndex: -1,
      sorgIndex: -1,
      delvIndex: -1,
      divIndex: -1,
      baIndex: -1,
      profileIndex: -1,
      client: [],
      nextbaID: 0,
      nextprofileID: 0,
      nextsubprofileID: 0,
      level: 0,
      currentselection: {
        client: "",
        clienttext: "",
        sorg: "",
        sorgtext: "",
        delvch: "",
        delvchtext: "",
        div: "",
        divtext: "",
        countrycode: "",
        countrycodetext: "",
        businessarea: "",
        businessareatext: "",
        profile: "",
        profiletext: "",
        subprofile: "",
        subprofiletext: "",
        subprofiledatatype: ""
      }
    };
  },
  methods: {
    closepopup() {
      this.addnew = false;
    },
    resetcurrentdata() {
      this.currentselection.client = "";
      this.currentselection.clienttext = "";
      this.currentselection.countrycode = "";
      this.currentselection.countrycodetext = "";
      this.currentselection.sorg = "";
      this.currentselection.sorgtext = "";
      this.currentselection.delvch = "";
      this.currentselection.delvchtext = "";
      this.currentselection.div = "";
      this.currentselection.divtext = "";
      this.currentselection.businessarea = "";
      this.currentselection.businessareatext = "";
      this.currentselection.profile = "";
      this.currentselection.profiletext = "";
      this.currentselection.subprofile = "";
      this.currentselection.subprofiletext = "";
      this.currentselection.subprofiledatatype = "";
    },
    addData(from) {
      if (from === "client") {
        this.resetcurrentdata();
        this.level = 1;

        this.currentselection.businessarea = this.nextbaID;
        this.currentselection.profile = this.nextprofileID;
        this.currentselection.subprofile = this.nextsubprofileID;
      }

      if (from === "country") {
        this.resetcurrentdata();
        this.level = 2;
        this.currentselection.client = this.client[this.clientIndex].id;
        this.currentselection.clienttext = this.client[this.clientIndex].text;

        this.currentselection.businessarea = this.nextbaID;
        this.currentselection.profile = this.nextprofileID;
        this.currentselection.subprofile = this.nextsubprofileID;
      }
      if (from === "sorg") {
        this.resetcurrentdata();
        this.level = 3;
        this.currentselection.client = this.client[this.clientIndex].id;
        this.currentselection.clienttext = this.client[this.clientIndex].text;
        this.currentselection.countrycode = this.client[
          this.clientIndex
        ].countryCode[this.countryIndex].id;
        this.currentselection.countrycodetext = this.client[
          this.clientIndex
        ].countryCode[this.countryIndex].text;

        this.currentselection.businessarea = this.nextbaID;
        this.currentselection.profile = this.nextprofileID;
        this.currentselection.subprofile = this.nextsubprofileID;
      }

      if (from === "distchl") {
        this.resetcurrentdata();
        this.level = 4;
        this.currentselection.client = this.client[this.clientIndex].id;
        this.currentselection.clienttext = this.client[this.clientIndex].text;
        this.currentselection.countrycode = this.client[
          this.clientIndex
        ].countryCode[this.countryIndex].id;
        this.currentselection.countrycodetext = this.client[
          this.clientIndex
        ].countryCode[this.countryIndex].text;
        this.currentselection.sorg = this.client[this.clientIndex].countryCode[
          this.countryIndex
        ].salesOrg[this.sorgIndex].id;
        this.currentselection.sorgtext = this.client[
          this.clientIndex
        ].countryCode[this.countryIndex].salesOrg[this.sorgIndex].text;

        this.currentselection.businessarea = this.nextbaID;
        this.currentselection.profile = this.nextprofileID;
        this.currentselection.subprofile = this.nextsubprofileID;
      }

      if (from === "div") {
        this.resetcurrentdata();
        this.level = 5;
        this.currentselection.client = this.client[this.clientIndex].id;
        this.currentselection.clienttext = this.client[this.clientIndex].text;
        this.currentselection.countrycode = this.client[
          this.clientIndex
        ].countryCode[this.countryIndex].id;
        this.currentselection.countrycodetext = this.client[
          this.clientIndex
        ].countryCode[this.countryIndex].text;
        this.currentselection.sorg = this.client[this.clientIndex].countryCode[
          this.countryIndex
        ].salesOrg[this.sorgIndex].id;
        this.currentselection.sorgtext = this.client[
          this.clientIndex
        ].countryCode[this.countryIndex].salesOrg[this.sorgIndex].text;

        this.currentselection.delvch = this.client[
          this.clientIndex
        ].countryCode[this.countryIndex].salesOrg[this.sorgIndex].delivCh[
          this.delvIndex
        ].id;
        this.currentselection.delvchtext = this.client[
          this.clientIndex
        ].countryCode[this.countryIndex].salesOrg[this.sorgIndex].delivCh[
          this.delvIndex
        ].text;

        this.currentselection.businessarea = this.nextbaID;
        this.currentselection.profile = this.nextprofileID;
        this.currentselection.subprofile = this.nextsubprofileID;
      }

      if (from === "businessarea") {
        this.resetcurrentdata();
        this.level = 6;
        this.currentselection.client = this.client[this.clientIndex].id;
        this.currentselection.clienttext = this.client[this.clientIndex].text;
        this.currentselection.countrycode = this.client[
          this.clientIndex
        ].countryCode[this.countryIndex].id;
        this.currentselection.countrycodetext = this.client[
          this.clientIndex
        ].countryCode[this.countryIndex].text;
        this.currentselection.sorg = this.client[this.clientIndex].countryCode[
          this.countryIndex
        ].salesOrg[this.sorgIndex].id;
        this.currentselection.sorgtext = this.client[
          this.clientIndex
        ].countryCode[this.countryIndex].salesOrg[this.sorgIndex].text;

        this.currentselection.delvch = this.client[
          this.clientIndex
        ].countryCode[this.countryIndex].salesOrg[this.sorgIndex].delivCh[
          this.delvIndex
        ].id;
        this.currentselection.delvchtext = this.client[
          this.clientIndex
        ].countryCode[this.countryIndex].salesOrg[this.sorgIndex].delivCh[
          this.delvIndex
        ].text;

        this.currentselection.div = this.client[this.clientIndex].countryCode[
          this.countryIndex
        ].salesOrg[this.sorgIndex].delivCh[this.delvIndex].div[
          this.divIndex
        ].id;
        this.currentselection.divtext = this.client[
          this.clientIndex
        ].countryCode[this.countryIndex].salesOrg[this.sorgIndex].delivCh[
          this.delvIndex
        ].div[this.divIndex].text;

        this.currentselection.businessarea = this.nextbaID;
        this.currentselection.profile = this.nextprofileID;
        this.currentselection.subprofile = this.nextsubprofileID;
      }

      if (from === "profile") {
        this.resetcurrentdata();
        this.level = 7;
        this.currentselection.client = this.client[this.clientIndex].id;
        this.currentselection.clienttext = this.client[this.clientIndex].text;
        this.currentselection.countrycode = this.client[
          this.clientIndex
        ].countryCode[this.countryIndex].id;
        this.currentselection.countrycodetext = this.client[
          this.clientIndex
        ].countryCode[this.countryIndex].text;
        this.currentselection.sorg = this.client[this.clientIndex].countryCode[
          this.countryIndex
        ].salesOrg[this.sorgIndex].id;
        this.currentselection.sorgtext = this.client[
          this.clientIndex
        ].countryCode[this.countryIndex].salesOrg[this.sorgIndex].text;
        this.currentselection.delvch = this.client[
          this.clientIndex
        ].countryCode[this.countryIndex].salesOrg[this.sorgIndex].delivCh[
          this.delvIndex
        ].id;
        this.currentselection.delvchtext = this.client[
          this.clientIndex
        ].countryCode[this.countryIndex].salesOrg[this.sorgIndex].delivCh[
          this.delvIndex
        ].text;
        this.currentselection.div = this.client[this.clientIndex].countryCode[
          this.countryIndex
        ].salesOrg[this.sorgIndex].delivCh[this.delvIndex].div[
          this.divIndex
        ].id;
        this.currentselection.divtext = this.client[
          this.clientIndex
        ].countryCode[this.countryIndex].salesOrg[this.sorgIndex].delivCh[
          this.delvIndex
        ].div[this.divIndex].text;

        this.currentselection.businessarea = this.client[
          this.clientIndex
        ].countryCode[this.countryIndex].salesOrg[this.sorgIndex].delivCh[
          this.delvIndex
        ].div[this.divIndex].businessArea[this.baIndex].id;
        this.currentselection.businessareatext = this.client[
          this.clientIndex
        ].countryCode[this.countryIndex].salesOrg[this.sorgIndex].delivCh[
          this.delvIndex
        ].div[this.divIndex].businessArea[this.baIndex].text;
        this.currentselection.profile = this.nextprofileID;
        this.currentselection.subprofile = this.nextsubprofileID;
      }

      if (from === "subprofile") {
        this.resetcurrentdata();
        this.level = 8;
        this.currentselection.client = this.client[this.clientIndex].id;
        this.currentselection.clienttext = this.client[this.clientIndex].text;
        this.currentselection.countrycode = this.client[
          this.clientIndex
        ].countryCode[this.countryIndex].id;
        this.currentselection.countrycodetext = this.client[
          this.clientIndex
        ].countryCode[this.countryIndex].text;
        this.currentselection.sorg = this.client[this.clientIndex].countryCode[
          this.countryIndex
        ].salesOrg[this.sorgIndex].id;
        this.currentselection.sorgtext = this.client[
          this.clientIndex
        ].countryCode[this.countryIndex].salesOrg[this.sorgIndex].text;
        this.currentselection.delvch = this.client[
          this.clientIndex
        ].countryCode[this.countryIndex].salesOrg[this.sorgIndex].delivCh[
          this.delvIndex
        ].id;
        this.currentselection.delvchtext = this.client[
          this.clientIndex
        ].countryCode[this.countryIndex].salesOrg[this.sorgIndex].delivCh[
          this.delvIndex
        ].text;
        this.currentselection.div = this.client[this.clientIndex].countryCode[
          this.countryIndex
        ].salesOrg[this.sorgIndex].delivCh[this.delvIndex].div[
          this.divIndex
        ].id;
        this.currentselection.divtext = this.client[
          this.clientIndex
        ].countryCode[this.countryIndex].salesOrg[this.sorgIndex].delivCh[
          this.delvIndex
        ].div[this.divIndex].text;

        this.currentselection.businessarea = this.client[
          this.clientIndex
        ].countryCode[this.countryIndex].salesOrg[this.sorgIndex].delivCh[
          this.delvIndex
        ].div[this.divIndex].businessArea[this.baIndex].id;
        this.currentselection.businessareatext = this.client[
          this.clientIndex
        ].countryCode[this.countryIndex].salesOrg[this.sorgIndex].delivCh[
          this.delvIndex
        ].div[this.divIndex].businessArea[this.baIndex].text;

        this.currentselection.profile = this.client[
          this.clientIndex
        ].countryCode[this.countryIndex].salesOrg[this.sorgIndex].delivCh[
          this.delvIndex
        ].div[this.divIndex].businessArea[this.baIndex].profile[
          this.profileIndex
        ].id;
        this.currentselection.profiletext = this.client[
          this.clientIndex
        ].countryCode[this.countryIndex].salesOrg[this.sorgIndex].delivCh[
          this.delvIndex
        ].div[this.divIndex].businessArea[this.baIndex].profile[
          this.profileIndex
        ].text;
        this.currentselection.subprofile = this.nextsubprofileID;
      }

      this.addnew = true;
    },

    findMaxBusinessAreaID(data) {
      let ba = [-1];
      data.forEach(item => {
        ba.push(item.businessarea);
      });
      return Math.max(...ba);
    },

    findMaxProfileID(data) {
      let pr = [-1];
      data.forEach(item => {
        pr.push(item.profile);
      });
      return Math.max(...pr);
    },

    findMaxSubProfileID(data) {
      let spr = [-1];
      data.forEach(item => {
        spr.push(item.subprofile);
      });
      return Math.max(...spr);
    },

    deletehere(level, value) {
      let _deletehere = () => {
        https.delete(`/api/profile/${level}/${value}`).then(
          resolve =>
            resolve.data.code !== "200"
              ? EventBus.$emit("Popup", {
                  popupType: "Error",
                  title: "Deletion..!",
                  message: "Cannot delete! Internal Server Error"
                })
              : this.loadData(),
          error => alert(error)
        );
      };

      EventBus.$emit("Popup", {
        popupType: "Confirmation",
        title: "Deletion..!",
        message:
          "You are sure yo want to delete? All related child nodes will be deleted on this action",
        confirmFunction: _deletehere
      });
    },

    loadData() {
      https.get("/api/profilelist").then(
        resolve => {
          this.nextbaID = this.findMaxBusinessAreaID(resolve.data) + 1;
          this.nextprofileID = this.findMaxProfileID(resolve.data) + 1;
          this.nextsubprofileID = this.findMaxSubProfileID(resolve.data) + 1;
          this.client = ProfileList(resolve.data);
        },
        error => alert(error)
      );
    }
  },
  created() {
    //this.loadData();
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
#main {
  display: flex;
  flex-direction: row;
  justify-content: center;
}

#content {
  display: flex;
  flex-direction: column;
}

#navigation {
  display: block;
  margin-top: 10px;
  margin-bottom: 15px;
  user-select: none;
  outline: none;
}

.head {
  width: 210px;
}

.highlightnavigate,
.navigate {
  padding-left: 10px;
  padding-right: 10px;
  padding-top: 5px;
  padding-bottom: 5px;
  border-radius: 10px;
  background-color: #7affd2;
  font-family: "IBM Plex Sans";
  font-weight: bold;
  font-size: 1em;
  letter-spacing: 0.05em;
}
.navigate {
  background-color: #d7dadd;
}

.highlightnavigate {
  background-color: #7affd2;
}

.profilelist {
  display: block;
  margin-top: 10px;
}

.attr {
  font-family: "Roboto";
  display: block;
  border: 1px solid;
  width: 250px;
  height: 25px;
  margin-top: 5px;
  border-radius: 10px;
  padding-top: 10px;
  text-align: center;
  user-select: none;
  outline: none;
}

.attr > button {
  float: right;
  border-radius: 5px;
  outline: none;
  border: 1px solid;
  height: 18px;
  line-height: 8px;
  width: 22px;
  margin-right: 2px;
}

.attr > button:hover {
  color: red;
  background-color: transparent;
}

.attr > button:active {
  color: blue;
  background-color: transparent;
}

.country {
  margin-left: 100px;
}
.sorg {
  margin-left: 200px;
}

.delv {
  margin-left: 300px;
}

.div {
  margin-left: 400px;
}

.businessarea {
  margin-left: 500px;
}

.profile {
  margin-left: 600px;
}

.subprofile {
  margin-left: 700px;
  width: 350px;
}

.add > label {
  color: white;
  font-size: 1.6em;
  font-weight: bold;
  line-height: 1px;
}

.add {
  letter-spacing: 0.05em;
  background-color: #00a437;
  border: 1px solid #009037;
}

.add:hover {
  background-color: #00cc37;
  border: 1px solid #00a437;
  user-select: none;
}

.add:active {
  background-color: #d2d6d9;
  color: #00a437;
  font-weight: bold;
  border: 1px solid #00a437;
  user-select: none;
  outline: none;
}

.selected {
  background-color: #d2d6d9;
}
</style>
