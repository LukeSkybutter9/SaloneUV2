<template>
    <div class="slider grid grid-cols-6 gap-x-64 gap-y-12 md:gap-x-96">
        <SalonCard 
        v-for="salon in salones"
        :key="salon.id"
        :salon="salon"
        />
    </div>
</template>

<script>
import axios from 'axios';
import SalonCard from  './SalonCard.vue';

export default {
    components: {
        SalonCard
    },
    data() {
    return {
        salones:[],
    };
},
created() {
    this.obtenerSalones();
},
methods: {
    async obtenerSalones() {
            try {
                const response = await axios.get('http://localhost:8085/salones');
                this.salones = response.data; // Almacena los datos recibidos en la propiedad salones
            } catch (error) {
                console.error("Error al obtener los salones:", error);
            }
        },
    },
};
</script>

<style scoped>


</style>