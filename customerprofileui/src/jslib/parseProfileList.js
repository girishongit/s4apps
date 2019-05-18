function parse(data) {
  let result = [];
  let uniqueData = [];
  data.forEach(x => uniqueData.push(x.client));
  uniqueData = Array.from(new Set(uniqueData));
  uniqueData.forEach(x => result.push(parseClient(x)));

  return result;

  function parseClient(clientid) {
    return {
      id: clientid,
      text: clientid,
      countryCode: getCountryCode(clientid)
    };
  }

  function getCountryCode(client) {
    let list = data.filter(x => x.client === client);
    let res = [];

    list.forEach(x =>
      res.push({
        id: x.countrycode,
        text: x.countrycodetext ? x.countrycodetext : x.countrycode,
        salesOrg: getSorg(x.client, x.countrycode)
      })
    );
    return removeDuplicates(res, "id");
  }

  function getSorg(client, cc) {
    let list = data.filter(x => x.client === client && x.countrycode === cc);
    let res = [];

    list.forEach(x =>
      res.push({
        id: x.sorg,
        text: x.sorgtext ? x.sorgtext : x.sorg,
        delivCh: getDlv(x.client, x.countrycode, x.sorg)
      })
    );
    return removeDuplicates(res, "id");
  }

  function getDlv(client, cc, sorg) {
    let list = data.filter(
      x => x.client === client && x.countrycode === cc && x.sorg === sorg
    );
    let res = [];

    list.forEach(x =>
      res.push({
        id: x.delvch,
        text: x.delvchtext ? x.delvchtext : x.delvch,
        div: getDiv(x.client, x.countrycode, x.sorg, x.delvch)
      })
    );
    return removeDuplicates(res, "id");
  }

  function getDiv(client, cc, sorg, dlv) {
    let list = data.filter(
      x =>
        x.client === client &&
        x.countrycode === cc &&
        x.sorg === sorg &&
        x.delvch === dlv
    );
    let res = [];

    list.forEach(x =>
      res.push({
        id: x.div,
        text: x.divtext ? x.divtext : x.div,
        businessArea: getBusinessArea(
          x.client,
          x.countrycode,
          x.sorg,
          x.delvch,
          x.div
        )
      })
    );
    return removeDuplicates(res, "id");
  }

  function getBusinessArea(client, cc, sorg, dlv, div) {
    let list = data.filter(
      x =>
        x.client === client &&
        x.countrycode === cc &&
        x.sorg === sorg &&
        x.delvch === dlv &&
        x.div === div
    );
    let res = [];

    list.forEach(x =>
      res.push({
        id: x.businessarea,
        text: x.businessareatext,
        profile: getProfileList(
          x.client,
          x.countrycode,
          x.sorg,
          x.delvch,
          x.div,
          x.businessarea
        )
      })
    );
    return removeDuplicates(res, "id");
  }

  function getProfileList(client, cc, sorg, dlv, div, ba) {
    let list = data.filter(
      x =>
        x.client === client &&
        x.countrycode === cc &&
        x.sorg === sorg &&
        x.delvch === dlv &&
        x.div === div &&
        x.businessarea === ba
    );
    let res = [];

    list.forEach(x =>
      res.push({
        id: x.profile,
        text: x.profiletext,
        subprofile: getsubprofileList(
          x.client,
          x.countrycode,
          x.sorg,
          x.delvch,
          x.div,
          x.businessarea,
          x.profile
        )
      })
    );
    return removeDuplicates(res, "id");
  }

  function getsubprofileList(client, cc, sorg, dlv, div, ba, pr) {
    let list = data.filter(
      x =>
        x.client === client &&
        x.countrycode === cc &&
        x.sorg === sorg &&
        x.delvch === dlv &&
        x.div === div &&
        x.businessarea === ba &&
        x.profile === pr
    );
    let res = [];
    list.forEach(x =>
      res.push({
        id: x.subprofile,
        text: x.subprofiletext,
        dataType: x.subprofiledatatype
      })
    );
    return removeDuplicates(res, "id");
  }

  function removeDuplicates(originalArray, prop) {
    var newArray = [];
    var lookupObject = {};

    for (var i in originalArray) {
      lookupObject[originalArray[i][prop]] = originalArray[i];
    }

    for (i in lookupObject) {
      newArray.push(lookupObject[i]);
    }
    return newArray;
  }
}

export default parse;
