<template>
  <div id="main">
    <div id="sideNav">
      <div id="key" :class=" keyIndex === index ? 'keyselected' : '' " v-for="(item, index) in keys" :key="index" @click=keychange(index)> {{item.client}} - {{item.countryCode}} </div>
      <div id="key" @click="newdata = {};showpopup = true; isUpdate= false"> Add New Record</div>
    </div>
    <div id="content">
      <table>
        <tr>
          <th> # </th>
          <th> Client </th>
          <th> Country </th>
          <th> Sales Org </th>
          <th> Dist. Ch </th>
          <th> Division </th>
          <th> Business Area </th>
          <th> Profile </th>
          <th> Sub Profile </th>
          <th> Data Type </th>
          <th> Action </th>
        </tr>
        <tr v-for="(item, index) in profile" :key="index">
          <td style="font-size: 0.5em"> {{index + 1}}</td>
          <td> {{item.client}}</td>
          <td> {{item.countrycode}}</td>
          <td> {{item.sorg}}</td>
          <td> {{item.delvch}}</td>
          <td> {{item.div}}</td>
          <td>
            <div class="text"> <span> {{item.businessarea}} </span>
              <td> {{item.businessareatext}} </td>
            </div>
          </td>
          <td>
            <div class="text"> <span> {{item.profile}} </span>
              <td> {{item.profiletext}} </td>
            </div>
          </td>
          <td>
            <div class="text"> <span> {{item.subprofile}} </span>
              <td> {{item.subprofiletext}} </td>
            </div>
          </td>
          <td> {{item.subprofiledatatype}}</td>
          <td>
            <button type="button" class="update btn btn-warning btn-sm" @click="update(index)"><span class="pencil"></span></button>
            <button type="button" class="delete btn btn-danger btn-sm" @click="deleteitem(index)"><span class="trash"></span></button>
          </td>
        </tr>
      </table>
    </div>
    <Popover v-if="showpopup" :data="newdata" :button="isUpdate ? 'Update' : 'Insert'" />
  </div>
</template>

<script>
  import https from "@/jslib/https";
  import Popover from "@/components/ProfileConfigPopOverNew";
  import {
    EventBus
  } from "@/jslib/event-bus.js";
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
        isUpdate: false,
        showpopup: false,
        keyIndex: 0,
        dataIndex: -1,
        keys: [],
        profile: [],
        currentdata: {},
        newdata: {},
      };
    },
    methods: {
      update(index) {
        this.dataIndex = index;
        Object.assign(this.currentdata, this.profile[index]);
        Object.assign(this.newdata, this.profile[index]);
        this.showpopup = true;
        this.isUpdate = true
      },
      closepopup() {
        this.showpopup = false;
      },
      insertdata() {
        if (this.isUpdate === false) {
          https.post(`/api/profilelist`, [this.newdata]).then(
            () => {
              if (this.newdata.client == this.keys[this.keyIndex].client && this.newdata.countrycode === this.keys[this.keyIndex].countryCode) {
                this.profile.push(this.newdata);
              }
            },
            error => EventBus.$emit("Popup", {
              popupType: "Information",
              title: "Insertion..!",
              message: error,
              button1: false
            })
          );
        } else {
          let payload = {
            "toChange": this.currentdata,
            "updated": this.newdata
          }
          https.put(`/api/profilelist`, payload).then(
            () => Object.assign(this.profile[this.dataIndex], this.newdata),
            error => EventBus.$emit("Popup", {
              popupType: "Information",
              title: "Updation..!",
              message: error,
              button1: false
            })
          );
        }
        this.closepopup();
      },
      deleteitem(index) {
        let self = this;
        let _delete = function() {
          https.delete(`/api/profilelist`, {
            data: [self.profile[index]]
          }).then(
            () => self.profile.splice(index, 1),
            error => EventBus.$emit("Popup", {
              popupType: "Information",
              title: "Deletion..!",
              message: error,
              button1: false
            })
          );
        }
        EventBus.$emit("Popup", {
          popupType: "Confirmation",
          title: "Deletion..!",
          message: "You are sure yo want to delete this record? ",
          confirmFunction: _delete
        });
      },
      keychange: function(index) {
        this.keyIndex = index;
        this.profile = [];
        this.loadData(this.keys[index].client, this.keys[index].countryCode);
      },
      loadKey: function() {
        https.get("/api/profilekey").then(
          resolve => {
            this.keys = resolve.data;
            this.loadData(resolve.data[this.keyIndex].client, resolve.data[this.keyIndex].countryCode);
          },
          error => alert(error)
        );
      },
      loadData: function(client, countrycode) {
        https.get(`/api/profilelist/${client}/${countrycode}`).then(
          resolve => {
            this.profile = resolve.data;
          },
          error => alert(error)
        );
      }
    },
    created() {
      this.loadKey();
    }
  };
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  #main {
    display: flex;
    flex-direction: row;
    justify-content: center;
    padding-bottom: 20px;
  }
  #sideNav {
    width: 200px;
    background-color: #7affd2;
    margin-right: 4px;
    padding-left: 20px;
    padding-top: 15px;
    padding-bottom: 15px;
    display: block;
    border-radius: 4px;
  }
  #sideNav>#key {
    border-radius: 5px;
    border: 1px solid;
    width: 90%;
    text-align: center;
    padding-top: 6px;
    padding-bottom: 6px;
    font-family: "Roboto";
    font-weight: bold;
    font-size: 1em;
    letter-spacing: 0.05em;
    margin-bottom: 6px;
    user-select: none;
  }
  #sideNav>#key:hover {
    background-color: #8EE8A1;
  }
  #sideNav>#key:active {
    background-color: #007C37;
  }
  #sideNav>.keyselected {
    background-color: #486475;
    color: white;
  }
  #sideNav>.keyselected:hover {
    background-color: #486475;
    color: white;
  }
  #content {
    width: 1050px;
    background-color: #D9D9D9;
    padding-left: 10px;
    border-radius: 4px;
    padding-bottom: 10px;
    overflow-y: auto;
  }
  #content>table {
    margin-top: 15px;
    border-collapse: collapse;
    border-spacing: 0;
    width: 98%;
    display: table;
    text-align: left;
    font-family: "IBM Plex Sans";
    font-size: 1em;
    letter-spacing: 0.05em;
  }
  #content>table>tr {
    font-weight: bold;
    border-bottom: 1px solid #ffffff;
    height: 30px;
  }
  #content>table>tr>td {
    font-weight: normal;
    height: 40px;
    padding-left: 4px;
  }
  #content>table>tr>td>button {
    width: 25px;
    height: 25px;
    padding-left: 0px;
    line-height: 20px;
    margin-right: 2px;
    border-radius: 5px;
    border: 1px solid #FF7C60;
    outline: none;
    background-color: #FF9B6D;
    color: #4E3629;
  }
  #content>table>tr>td>button:hover {
    background-color: #FF7858;
  }
  #content>table>tr>td>button:active {
    background-color: #FF9B6D;
  }
  .pencil:before {
    content: "\270e";
    font-size: 1.3em;
    padding-left: 3px;
  }
  .trash:before {
    content: "\2715";
    font-size: 1.3em;
    padding-left: 4px;
  }
  #content>table>tr>td>.text {
    display: flex;
    flex-direction: column;
  }
  #content>table>tr>td>.text>span {
    font-size: 0.6em;
  }
</style>
