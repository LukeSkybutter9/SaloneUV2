<template>
    <div class="login-container">
        <form @submit.prevent="login">
            <CampoForm ulario v-model="email" tipo="email" placeholder="Correo electrónico"/>
            <CampoForm ulario v-model="password" tipo="password" placeholder="Contraseña"/>
            <button type="submit">Login</button>
        </form>
        <p v-if="errorMessage" class="error">{{ errorMessage }}</p>
    </div>
</template>

<script>
import axios from "axios";
import CampoForm from "./CampoForm.vue";

export default {
    components: {
    CampoForm
    },
    data() {
    return {
        email: "",
        password: "",
        errorMessage: null,
    };
},
methods: {
    async login() {
            try {
            const response = await axios.post("http://localhost:8081/api/auth/login", {
                email: this.email,
                password: this.password,
            });
            const token = response.data.token;

            // Guardar el token en localStorage
            localStorage.setItem("token", token);

            // Redireccionar al dashboard o página principal
            this.$router.push("/ReservaSalon");
            } catch (error) { // eslint-disable-line no-unused-vars
            this.errorMessage = "Credenciales incorrectas. Por favor, inténtalo de nuevo.";
            }
        },
    },
};
</script>

<style scoped>

.error {
    color: red;
}
</style>
