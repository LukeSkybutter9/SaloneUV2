<template>
    <div class="login-container ">
        <form @submit.prevent="handleLogin">
            <div class="campoForm">
            <label for="email">Correo Institucional (@unimagdalena.edu.co)</label>
            <input type="text" v-model="email" required />
            </div>
            <div class="campoForm">
            <label for="password">Contraseña</label>
            <input type="password" v-model="password" required />
            </div>
            <button class="mb-4" type="submit" >Login</button>
        </form>
    </div>
</template>

<script setup>
import { ref } from 'vue'
import axios from 'axios'
import { jwtDecode } from "jwt-decode";
import { ElMessage } from 'element-plus';

const email = ref('')
const password = ref('')

    const handleLogin = async () => {
        try {
            const response = await axios.post('http://localhost:8087/public/auth/login', {
            email: email.value,
            password: password.value,
            });
            
            if (response) {
                
                const token = response.data; 
                const decodedToken = jwtDecode(token);
                ElMessage({
                message: '!Bienvenido '+ decodedToken.sub + '!',
                type: 'success',
                })
                localStorage.setItem('token', token);
                window.location.href= '/reservas';
            }

        } catch (error) { 
            if (error.response && error.response.status === 401) {
                ElMessage.error('Oops, error al iniciar sesión');
            } else {
                ElMessage.error('Oops, error al iniciar sesión');
            }
        }
    }
</script>

<style scoped>

.campoForm{
    display: flex;
    flex-direction: column;
    text-align: left;
    font: bold;
    gap:5px;
}

.campoForm input{
    margin-bottom: 15px;
    border: #F0F0F0;
    border-radius: 5px;
    box-shadow: 0px 4px 12px rgba(0, 0, 0, 0.1);
    padding: 10px;
    outline: none;
}

.campoForm input:focus {
    box-shadow: 0px 4px 12px rgba(0, 0, 0, 0.2);
}

button{
    padding: 10px 20px;
    width: 100%;
    border: none;
    background-color: #004a87;
    color: white;
    cursor: pointer;
    border-radius: 5px;
}
</style>
