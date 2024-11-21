<template>
    <div class="Tarjeta bg-white w-64 md:w-72 lg:w-80">
        <div class="img">
            <img src="https://www.unimagdalena.edu.co/Content/ArchivosPublicaciones/Publicacion_36919/documento_4_20190408161320.93.jpg" width="300">
        </div>
        <div class="nombreSalon mt-4 mb-16 md:mb-12 lg:mb-8">
            <h2 class="tituloCarta font-bold">{{ salon.nombreEdificio + '  ' + salon.nombre}}</h2>
            <p>{{ salon.descripcion }}</p>
        </div>
        <el-button class="boton" type="success" @click="abrirModal" plain>Reservar</el-button>
    </div>
</template>

<script setup>
import { defineProps } from 'vue'
import { ElButton } from 'element-plus'
import 'element-plus/dist/index.css'

const props = defineProps({
    salon: Object,
})

    const emit = defineEmits(['abrirModal']);
    function abrirModal() {
        emit('abrirModal', props.salon);
    }

</script>

<style scoped>
.Tarjeta{
    display: flex;
    flex-direction: column;
    overflow: hidden;
    box-shadow: 2px 4px 12px 5px rgba(0, 0, 0, 0.2);
    border-radius: 16px;
    padding: 1.2rem;
    height: 20rem;
    cursor: pointer;
}

.Tarjeta .img{
    display: flex;
    justify-content: center;
}

.img img{
    border-radius: 10px;
}

.tituloCarta{
    color: #0065b8;
}

.boton{
    background-color: #004a8742;
    border: 1px solid #0065b8;
    color: #0065b8;
    border-radius: 5px;
}

.boton:hover{
    background-color: #0065b8;
    border: 2px solid #0065b8;
    color: #ffffff;
}
</style>