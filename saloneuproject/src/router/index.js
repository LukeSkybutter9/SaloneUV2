import { createRouter, createWebHistory } from 'vue-router';
import Main from '../components/vue/LoginForm.vue'; // Asegúrate de que la ruta sea correcta
import Login from '../views/Login.vue'; // Importa otras vistas según sea necesario
import Reservas from '../views/Reservas.vue'; // Importa otras vistas según sea necesario

const routes = [
    {
        path: '/',
        name: 'Main',
        component: Main, // Establece Main.vue como la ruta principal
    },
    {
        path: '/login',
        name: 'Login',
        component: Login,
    },
    {
        path: '/reservas',
        name: 'Reservas',
        component: Reservas,
    },
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

export default router;