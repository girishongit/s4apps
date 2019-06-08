<template>
  <div id="popoveradd">
    <div id="portfoliolistaddition">
      <div id="fieldform">
        <div id="fieldblock">
          <input id="value" class="focus-input" type="text" placeholder=" " v-model="data.client" :readonly="button === 'Update'"/>
          <span class="focus-border"></span>
          <label class="focus-label"> Client </label>
        </div>
        <div id="fieldblock">
          <input id="value" class="focus-input" type="text" placeholder=" " v-model="data.countrycode" :readonly="button === 'Update'"/>
          <span class="focus-border"></span>
          <label class="focus-label"> Country </label>
        </div>
        <div id="fieldblock">
          <input id="value" class="focus-input" type="text" placeholder=" " v-model="data.sorg" />
          <span class="focus-border"></span>
          <label class="focus-label"> Sales Org </label>
        </div>
        <div id="fieldblock">
          <input id="value" class="focus-input" type="text" placeholder=" " v-model="data.delvch" />
          <span class="focus-border"></span>
          <label class="focus-label"> Dist. Ch </label>
        </div>
        <div id="fieldblock">
          <input id="value" class="focus-input" type="text" placeholder=" " v-model="data.div" />
          <span class="focus-border"></span>
          <label class="focus-label"> Division </label>
        </div>
      </div>
      <div id="fieldform_1">
        <label> Business Area </label>
        <div id="fieldblock">
          <input id="value" class="focus-input" type="text" placeholder=" " v-model="data.businessarea" />
          <span class="focus-border"></span>
          <label class="focus-label"> ID </label>
        </div>
        <div id="fieldblock">
          <input id="value" class="focus-input" type="text" placeholder=" " v-model="data.businessareatext" />
          <span class="focus-border"></span>
          <label class="focus-label"> Text </label>
        </div>
      </div>
      <div id="fieldform_1">
        <label> Profile </label>
        <div id="fieldblock">
          <input id="value" class="focus-input" type="text" placeholder=" " v-model="data.profile" />
          <span class="focus-border"></span>
          <label class="focus-label"> ID </label>
        </div>
        <div id="fieldblock">
          <input id="value" class="focus-input" type="text" placeholder=" " v-model="data.profiletext" />
          <span class="focus-border"></span>
          <label class="focus-label"> Text </label>
        </div>
      </div>
      <div id="fieldform_1">
        <label> Sub Profile </label>
        <div id="fieldblock">
          <input id="value" class="focus-input" type="text" placeholder=" " v-model="data.subprofile" />
          <span class="focus-border"></span>
          <label class="focus-label"> ID </label>
        </div>
        <div id="fieldblock">
          <input id="value" class="focus-input" type="text" placeholder=" " v-model="data.subprofiletext" />
          <span class="focus-border"></span>
          <label class="focus-label"> Text </label>
          <select class="focus-input focus-dropdown" style="margin-top: -15px;" v-model="data.subprofiledatatype" >
              <option v-for="(item,index) in datatype" :key="index" :value="item">{{item}}</option>
            </select>
        </div>
      </div>
      <div id="fieldbutton">
        <button @click="closepopup()">Cancel</button>
        <button @click="insertdata()">{{button}}</button>
      </div>
    </div>
  </div>
</template>

<script>
  //import https from "@/jslib/https";
  export default {
    props: {
      data: {},
      button: {}
    },
    data() {
      return {
        datatype: ["String", "Integer", "Boolean", "Date", "Decimal", "Free Text", "Drop Down"]
      };
    },
    methods: {
      closepopup() {
        this.$parent.closepopup();
      },
      insertdata() {
        this.$parent.insertdata();
        /* let profilepayload = [{
          client: this.data.client,
          countrycode: this.data.countrycode,
          sorg: this.data.sorg,
          delvchl: this.data.delvch,
          div: this.data.div,
          businessarea: this.data.businessarea,
          profile: this.data.profile,
          subprofile: this.data.subprofile,
          subprofiletype: this.data.subprofiledatatype
        }];
        let profiletextpayload = [];
        for (let i = this.insertlevel; i <= 8; i++) {
          let val = this.prepareProfileText(i);
          val ? profiletextpayload.push(val) : '';
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
          }); */
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
    background-color: rgba(200, 200, 200, 0.6);
    justify-content: center;
  }
  #portfoliolistaddition {
    width: 800px;
    height: 400px;
    border: 1px solid;
    margin-top: 20px;
    margin-left: 180px;
    border-radius: 20px;
    background-color: whitesmoke;
  }
  #fieldform {
    margin-left: 30px;
    margin-right: 30px;
    margin-top: 30px;
    display: flex;
    flex-direction: row;
    justify-content: space-between;
  }
  #fieldblock {
    display: block;
  }
  #fieldblock>.focus-label {
    font-family: "IBM Plex Sans";
    font-weight: bold;
    font-size: 1em;
    letter-spacing: 0.03em;
    color: grey;
    display: inline-block;
    transform: translate(15px, -28px) scale(1);
    transition: 0.4s;
    width: auto;
  }
  #fieldblock>.focus-input {
    display: block;
    width: 100px;
    height: 25px;
    outline: none;
    padding-left: 10px;
    padding-right: 10px;
    margin-top: 12px;
    border: 1px solid grey;
    border-radius: 5px;
    overflow: hidden;
    text-overflow: ellipsis;
    font-family: "IBM Plex Sans";
    font-weight: bold;
  }
  #fieldblock>.focus-border {
    display: block;
    width: 0px;
    min-height: 2px;
    background-color: blue;
    transition: 0.4s;
    border-radius: 5px;
  }
  #fieldblock>input:focus~.focus-label,
  #fieldblock>input:not(:placeholder-shown)~.focus-label {
    transform: translate(2px, -52px) scale(1);
    font-weight: normal;
    transition: 0.4s;
  }
  #fieldblock>input:focus {
    border-bottom-left-radius: 0px;
    border-bottom-right-radius: 0px;
  }
  #fieldform>#fieldblock>input:focus~.focus-border {
    width: 122px;
    transition: 0.4s;
  }
  #fieldform_1>#fieldblock>input:focus~.focus-border {
    width: 322px;
    transition: 0.4s;
  }
  #fieldform_1>#fieldblock:nth-child(2)>input:focus~.focus-border {
    width: 122px;
    transition: 0.4s;
  }
  #fieldform_1 {
    margin-left: 100px;
    margin-right: 100px;
    margin-top: 10px;
    display: flex;
    justify-content: space-between;
  }
  #fieldform_1>label {
    margin-top: 15px;
    font-family: "IBM Plex Sans";
    font-weight: bold;
    font-size: 1em;
    letter-spacing: 0.03em;
    min-width: 140px;
    max-width: 140px;
  }
  #fieldform_1>#fieldblock:last-child>input {
    width: 300px;
  }

  .focus-dropdown
  {
    min-width: 150px;
  }

 #fieldform>#fieldblock>input:read-only,
 #fieldform>#fieldblock>input:read-only~.focus-border {
    background-color: #DDDEE3;
  }

  #fieldbutton {
    display: flex;
    flex-direction: row;
    justify-content: center;
    margin-top: 25px;
  }
  #fieldbutton>button {
    width: 100px;
    margin-left: 5px;
    height: 30px;
    border-radius: 5px;
    outline: none;
  }
</style>
