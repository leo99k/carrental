
import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router);


import ContractManager from "./components/ContractManager"

import PayManager from "./components/PayManager"

import ReservationManager from "./components/ReservationManager"


import MyPage from "./components/MyPage"
export default new Router({
    // mode: 'history',
    base: process.env.BASE_URL,
    routes: [
            {
                path: '/contracts',
                name: 'ContractManager',
                component: ContractManager
            },

            {
                path: '/pays',
                name: 'PayManager',
                component: PayManager
            },

            {
                path: '/reservations',
                name: 'ReservationManager',
                component: ReservationManager
            },


            {
                path: '/myPages',
                name: 'MyPage',
                component: MyPage
            },


    ]
})
