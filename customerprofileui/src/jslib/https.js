import axios from "axios";

const https = axios.create({
  baseURL:
    "https://cg-pbs-customerprofile.cfapps.eu10.hana.ondemand.com/customerprofile",
  timeout: 100000,
  withCredentials: false
});

export default https;
