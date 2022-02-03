import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import Participantes from '../views/Participantes.vue'
import Leiloes from '../views/Leiloes.vue'
import Lances from '../views/Lances.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '*',
    redirect: 'home',
  },
  {
    path: '/',
    redirect: 'home'
  },
  {
    path: '/participantes',
    name: 'participantes',
    component: Participantes
  },
  {
    path: '/leiloes',
    name: 'leiloes',
    component: Leiloes
  },
  {
    path: '/leiloes/:status',
    name: 'leiloesStatus',
    component: Leiloes
  },
  {
    path: '/lances/:id',
    name: 'lances',
    component: Lances
  },
  {
    path: '/home',
    name: 'home',
    component: Home
  },
  {
    path: '/about',
    name: 'About',
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue'),
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
});

export default router
