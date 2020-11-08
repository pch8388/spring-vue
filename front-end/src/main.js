import Vue from 'vue';
import axios from 'axios';
import Vuelidate from 'vuelidate';
import App from './App.vue';
import router from './router';
import store from './store';

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
  render: (h) => h(App),
}).$mount('#app');
