<template>
  <div id="about">
    <div id="content">
      <img src="@/assets/error.png">
      Some thing went wrong.!
      You are not able to access Customer Profiles api's
    </div>
  </div>
</template>


<style>
#about {
  margin-top: 75px;
  display: flex;
  flex-direction: row;
  justify-content: center;
}

#about > #content > img {
  width: 80px;
}

#about > #content {
  display: flex;
  flex-direction: column;
  width: 800px;
  font-family: "IBM Plex Sans";
  font-weight: bold;
  font-size: 1em;
  letter-spacing: 0.05em;
}
</style>
