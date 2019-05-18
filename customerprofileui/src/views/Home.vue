<template>
  <div class="home">
    <ProfileConfig/>
  </div>
</template>

<script>
// @ is an alias to /src
import ProfileConfig from "@/components/ProfileConfig.vue";

export default {
  name: "home",
  components: {
    ProfileConfig
  }
};
</script>

<style>
.home {
  margin-top: 25px;
}
</style>
