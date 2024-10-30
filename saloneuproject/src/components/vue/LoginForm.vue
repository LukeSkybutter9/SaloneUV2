<template>
    <div class="login-container">
        <form @submit.prevent="login">
            <CampoForm v-model="email" tipo="email" placeholder="Correo electrónico"/>
            <CampoForm v-model="password" tipo="password" placeholder="Contraseña"/>
            <button @click="login">Login</button>
        </form>
        <p v-if="errorMessage" class="error">{{ errorMessage }}</p>
    </div>
</template>

<script setup lang="js">
import { ref } from 'vue'
import axios from 'axios'
import { useRouter } from 'vue-router'
import CampoForm from './CampoForm.vue'

// Definiendo las propiedades reactivas
const email = ref('')
const password = ref('')
const errorMessage = ref(null)

const login = async () => {
    try {
        const response = await axios.post('http://localhost:8081/api/auth/login', {
        email: email.value,
        password: password.value,
        });
        const token = response.data.token;

        // Guardar el token en localStorage
        localStorage.setItem('token', token);

        // Redireccionar al dashboard o página principal
        // Se puede usar el router directamente si está disponible
        const router = useRouter(); 
        router.push('/reservas');
    } catch (error) {// eslint-disable-line no-unused-vars
        errorMessage.value = 'Credenciales incorrectas. Por favor, inténtalo de nuevo.';
    }
}
</script>

<style scoped>

.error {
    color: red;
}
</style>
