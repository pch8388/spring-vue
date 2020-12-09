import Vue from 'vue';
import axios from 'axios';
import Vuelidate from 'vuelidate';
import App from './App.vue';
import router from './router';
import store from './store';
import { i18n } from "@/i18n";

axios.defaults.baseURL = '/api';
axios.defaults.headers.common.Accept = 'application/json';
axios.interceptors.response.use(
  (response) => response,
  (error) => Promise.reject(error),
);

Vue.use(Vuelidate);
Vue.config.productionTip = false;

new Vue({
  router,
  store,
  i18n,
  render: (h) => h(App),
}).$mount('#app');
