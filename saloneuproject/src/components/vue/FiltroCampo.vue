<template>
    <el-form :inline="true" :model="formInline" class="demo-form-inline">
        <el-form-item>
            <el-select 
                v-model="formInline.region" 
                placeholder="Edificio"  
                clearable
                style="width: 250px;
                margin-bottom: 1rem" 
            >
                <el-option 
                    v-for="option in uniqueEdificios" 
                    :key="option.idEdificio"  
                    :label="option.nombreEdificio" 
                    :value="option.idEdificio" 
                />
            </el-select>
        </el-form-item>
        <!-- Contenedor de las tarjetas de salones -->
        <div class="salones gap-16">
            <SalonCard 
                v-for="salon in filteredSalones" 
                :key="salon.id" 
                :salon="salon"
                @abrirModal="abrirModal"
            />
        </div>
    </el-form>

    <ModalReserva :abrirModal="mostrarModal" :salon="salonSeleccionado" @cerrar-modal="mostrarModal = false" />
</template>

<script setup>
import { ref, onMounted, computed } from 'vue'
import { ElForm,ElFormItem,ElSelect,ElOption } from 'element-plus'
import axios from 'axios'
import SalonCard from './SalonCard.vue'
import ModalReserva from './ModalReserva.vue'


const salonSeleccionado = ref([]);

const mostrarModal = ref(false);
function abrirModal(salon) {
    salonSeleccionado.value = salon;
    mostrarModal.value = true;
}

const salones = ref([])

const formInline = ref({
    region: ''
})

onMounted(async () => {
    try {
        const response = await axios.get('https://salonesuservices-api-dhg9asefctasg4c0.eastus2-01.azurewebsites.net/api/salones')
        salones.value = response.data.map(salon => {
            switch (salon.idEdificio) {
                case 1:
                    salon.nombreEdificio = "Sierra Nevada";
                    break;
                case 2:
                    salon.nombreEdificio =  "Cienaga Grande";
                    break;
                case 3:
                    salon.nombreEdificio = "Mar Caribe";
                    break;
                default:
                    salon.nombreEdificio = "Edificio Desconocido";
                    break;
            }
            return salon;
        });
    } catch (error) {
        console.error("Error al obtener los salones:", error)
    }
})

//Función async que espera la llamada desde componente salonCard con un emit que envía el salón seleccionado a reservar
async function reservarSalon(salon) {  // eslint-disable-line no-unused-vars
    const payload = {
        idSalon: salon.id,
        fecha: new Date().toISOString(), // Fecha actual en formato ISO
    };
    try {
        // Realiza la solicitud POST al backend para reservar el salón
        const response = await axios.post('https://salonesuservices-api-dhg9asefctasg4c0.eastus2-01.azurewebsites.net/api/reservas', payload);

        if(response){
            console.log("Reserva Realizada con Exito!");
        }

    } catch (error) {
        console.error('Error al realizar la reserva:', error);
    }
}


// Computed para obtener edificios únicos
const uniqueEdificios = computed(() => {
    const idEficiosMap = new Map()
    return salones.value.filter((salon) => {
        if (!idEficiosMap.has(salon.idEdificio)) {
            idEficiosMap.set(salon.idEdificio)
            return true
        }
        return false
    })
})

// Computed para filtrar los salones según la región seleccionada en el select
const filteredSalones = computed(() => {
    return formInline.value.region
        ? salones.value.filter(salon => salon.idEdificio === formInline.value.region)
        : salones.value
})
</script>

<style scoped>
.salones {
    display: flex;
    flex-wrap: wrap;
}
</style>