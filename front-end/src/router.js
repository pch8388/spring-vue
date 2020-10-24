import Vue from 'vue';
import VueRouter from 'vue-router';
import LoginPage from './views/LoginPage.vue';
import RegisterPage from './views/RegisterPage.vue';

Vue.use(VueRouter);

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [{
    path: '/login',
    name: 'LoginPage',
    component: LoginPage,
  },
  {
    path: '/register',
    name: 'RegisterPage',
    component: RegisterPage,
  }],
});

export default router;
