<template>
    <el-breadcrumb :separator-icon="ArrowRight">
        <el-breadcrumb-item :to="{ path: '/' }">Reservas</el-breadcrumb-item>
        <el-breadcrumb-item>Formulario Reserva</el-breadcrumb-item>
        <el-breadcrumb-item>Finalizar Reserva</el-breadcrumb-item>
    </el-breadcrumb>
    <div class="cerrarSesion mr-8">
        <button @click="logout" class="cerrarSesion font-semibold" >Cerrar Sesión</button>
    </div>
</template>

<script lang="ts" setup>
    import { ArrowRight } from '@element-plus/icons-vue'
    import { ElBreadcrumb, ElBreadcrumbItem } from 'element-plus';
    import 'element-plus/dist/index.css'


const logout = () => {
    localStorage.removeItem('token') 
    window.location.href= '/login';
}
</script>

<style scoped>

.cerrarSesion{
    display: flex;
    justify-content: center;
    align-items: center;
    font-size: 0.8rem;
    width: 8rem;
    height: 1.7rem;
    background-color: #004a8731;
    border: 1px solid #3c72dd;
    color: #3c72dd;
    border-radius: 3px;
}

.cerrarSesion:hover{
    background-color: #0065b8;
    border: 2px solid #0065b8;
    color: #ffffff;
}


</style>