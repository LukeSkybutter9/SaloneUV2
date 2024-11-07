<template>
    <div class="login-container">
        <form @submit.prevent="handleLogin">
            <div class="campoForm">
            <label for="email">Nombre de usuario:</label>
            <input type="text" v-model="email" required />
            </div>
            <div class="campoForm">
            <label for="password">Contraseña:</label>
            <input type="password" v-model="password" required />
            </div>
            <button type="submit" >Login</button>
            <p v-if="errorMessage" class="error">{{ errorMessage }}</p>
            <p v-if="successMessage" class="success">{{ successMessage }}</p>
        </form>
    </div>
</template>

<script setup>
import { ref } from 'vue'
import axios from 'axios'

const email = ref('')
const password = ref('')
const errorMessage = ref(null)
const successMessage = ref(null)

//const router = useRouter(); 

    const handleLogin = async () => {
        try {
            const response = await axios.post('http://localhost:8087/public/auth/login', {
            email: email.value,
            password: password.value,
            });
            
            if (response) {
                const token = response.data; 
                localStorage.setItem('token', token);
                console.log(token);
                successMessage.value = 'Login successful';
                window.location.href= '/reservas';
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
.error {
    color: red;
}

.succes{
    color: green;
}
</style>
