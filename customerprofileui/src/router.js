import Vue from "vue";
import Router from "vue-router";
import Home from "./views/Home.vue";
import Contributers from "./views/Contributers.vue";
import Error from "./views/Error.vue";

Vue.use(Router);

export default new Router({
  mode: "abstract",
  base: process.env.BASE_URL,
  routes: [
    {
      path: "/",
      name: "home",
      component: Home
    },
    {
      path: "/contributers",
      name: "Contributers",
      component: Contributers
    },
    {
      path: "/error",
      name: "Error",
      component: Error
    },
    {
      path: "/about",
      name: "about",
      // route level code-splitting
      // this generates a separate chunk (about.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () =>
        import(/* webpackChunkName: "about" */ "./views/About.vue")
    }
  ]
});
