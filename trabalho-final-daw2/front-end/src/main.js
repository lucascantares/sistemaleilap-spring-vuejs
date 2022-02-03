import Vue from 'vue'
import App from './App.vue'
import router from './router'
import vSelect from 'vue-select'
import VeeValidate from 'vee-validate'
import "../node_modules/bulma/css/bulma.css"
import 'vue-select/dist/vue-select.css'

Vue.config.productionTip = false;
Vue.component('v-select', vSelect);
Vue.use(VeeValidate);

new Vue({
  router,
  render: h => h(App),
}).$mount('#app')
