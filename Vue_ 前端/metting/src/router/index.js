import Vue from 'vue'
import Router from 'vue-router'
import Home from '../components/Home'
import Login from '../components/Login'
import Signin from '../components/Signin'
import Tourist from '../components/Tourist'
import text from '../components/text'
import register from '../components/Signin'
import NoteList from '../components/Notifications';

Vue.use(Router)

export default new Router({
  routes: [
    { path: '/login', component: Login },
    // { path: '/', redirect: '/register' },
    { path:'/register',component:register},
    { path:'/noteList',component:NoteList},
    // { path: '/', redirect: '/home' },
    { path: '/home', component: Home },
    { path: '/tour', component: Tourist },
    { path: '/text', component: text },
    { path: '/', redirect: '/login' },
  ]
})
