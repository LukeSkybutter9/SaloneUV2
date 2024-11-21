<template lang="">
    <div>
        <el-row >
            <el-col :sm="12" :lg="6">
            <el-result
                icon="success"
                title="Reserva Realizada"
                sub-title="¡Salon Reservado con Exito!"
            >
                <template #extra>
                <el-button type="primary" @click="cerrarSuccessAdvice">Continuar</el-button>
                </template>
            </el-result>
            </el-col>
        </el-row>
    </div>
</template>
<script setup>
    import { ElButton, ElRow, ElResult,ElCol } from 'element-plus';

</script>
<style lang="">
    
</style>