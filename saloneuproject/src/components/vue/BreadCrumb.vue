<template>
    <el-breadcrumb :separator-icon="ArrowRight">
        <el-breadcrumb-item :to="{ path: '/' }">Reservas</el-breadcrumb-item>
        <el-breadcrumb-item>Formulario Reserva</el-breadcrumb-item>
        <el-breadcrumb-item>Finalizar Reserva</el-breadcrumb-item>
    </el-breadcrumb>
</template>

<script lang="ts" setup>
    import { ArrowRight } from '@element-plus/icons-vue'
    import { ElBreadcrumb, ElBreadcrumbItem } from 'element-plus';
    import 'element-plus/dist/index.css'
</script>

<style scoped>

</style>