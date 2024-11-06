<template>
    <div class="login-container">
        <form @submit.prevent="login($event)">
            <CampoForm v-model="email" type="email" placeholder="Correo electrónico" name="email" />
            <CampoForm v-model="password" type="password" placeholder="Contraseña" name="password"/>
            <button type="submit" >Login</button>
        </form>
        <p v-if="errorMessage" class="error">{{ errorMessage }}</p>
        <p v-if="successMessage" class="success">{{ successMessage }}</p>
    </div>
</template>

<script setup>
import { ref } from 'vue'
import axios from 'axios'
//import { useRouter } from 'vue-router'
import CampoForm from './CampoForm.vue'

const email = ref('')
const password = ref('')
const errorMessage = ref(null)
const successMessage = ref(null)

//const router = useRouter(); 

    const login = async () => {
        event.preventDefault();  // Prevent form default submission
        console.log("Login function triggered"); 
        try {
            const response = await axios.post('http://localhost:8087/public/auth/login', {
            email: email.value,
            password: password.value,
            });

            console.log(email.value, password.value);
            
            if (response.status === 200) {
                successMessage.value = 'Login successful';
                //router.push('../reservas');
            }

        } catch (error) { 
            if (error.response && error.response.status === 401) {
                errorMessage.value = 'Usuario o contraseña incorrectos';
            } else {
                errorMessage.value = 'Error en el servidor';
            }
        }
    }
</script>

<style scoped>

.error {
    color: red;
}

.succes{
    color: green;
}
</style>
