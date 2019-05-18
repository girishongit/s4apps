<template>
  <div id="loadingspinner" class="loadingspinner"></div>
</template>

<script>
export default {
  name: "LoadSpinner",
  props: {
    msg: String
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
#loadingspinner {
  display: inline-block;
}

.loadingspinner:after {
  content: " ";
  display: block;
  width: 36px;
  height: 36px;
  margin: 1px;
  border-radius: 50%;
  border: 5px solid;
  border-color: rgb(17, 110, 255) rgb(17, 110, 255) rgb(17, 110, 255)
    transparent;
  animation: loadingspinner 1s linear infinite;
}

@keyframes loadingspinner {
  0% {
    transform: rotate(0deg);
  }
  100% {
    transform: rotate(360deg);
  }
}
</style>
