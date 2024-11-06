import { createRouter, createWebHistory } from 'vue-router';
import Login from '../vue/LoginForm.vue';
//import Register from '../RegisterView.vue';

const routes = [
    { path: '/login', component: Login },
    //{ path: '/reserva', component: Register, meta: { requiresAuth: true } },
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

router.beforeEach((to, from, next) => {
    const isAuthenticated = !!localStorage.getItem('token');
    if (to.meta.requiresAuth && !isAuthenticated) {
        next('/login');
    } else {
        next();
    }
});

export default router;