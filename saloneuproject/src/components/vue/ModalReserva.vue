<template>
    <el-dialog class=" text-gray-950" :visible="dialogTableVisible" v-model="dialogTableVisible" title="Disponibilidades" width="800px" @close="cerrarModal">
        <p class="pb-7" >Salón Seleccionado - {{ salon.nombreEdificio + " " + salon.nombre }}</p>
        <el-table :data="disponibilidades" style="width: 100%">
            <el-table-column prop="horaInicio" label="Hora de Inicio" width="150"></el-table-column>
            <el-table-column prop="horaFin" label="Hora de Fin" width="150"></el-table-column>
            <el-table-column label="Acciones" width="150">
                <template #default="scope">
                    <el-button 
                        type="primary" 
                        @click="reservar(scope.row)">
                        Reservar
                    </el-button>
                </template>
            </el-table-column>
        </el-table>
        <template #footer>
        <div class="dialog-footer">
            <el-button @click="cerrarModal">Cancelar</el-button>
        </div>
        </template>
    </el-dialog>
</template>

<script lang="ts" setup>
    import { ref , watch, defineEmits} from 'vue'
    import axios from 'axios';
    import { ElButton, ElDialog, ElTable, ElTableColumn, ElMessage } from 'element-plus';
    
    const dialogTableVisible = ref(false);
    const emit = defineEmits(['cerrar-modal']);

    const props = defineProps({
        abrirModal: Boolean,
        salon: {
            type: Object,
            required: true
        }
    });

    const disponibilidades = ref([]);

    function obtenerFechaHoy() {
    const hoy = new Date();
    
    const año = hoy.getFullYear();
    const mes = String(hoy.getMonth() + 1).padStart(2, '0');  // Mes en formato 2 dígitos
    const dia = String(hoy.getDate()).padStart(2, '0');  // Día en formato 2 dígitos
    
    return `${año}-${mes}-${dia}`;
    }

const obtenerDisponibilidad = async () => {
    try {
        if (props.salon && props.salon.id){
            const disponibilidad = {
                salonId: props.salon.id,
                fecha: obtenerFechaHoy()
            }
            console.log('disponibilidad ', disponibilidad); 
            const response = await axios.post("https://salonesuservices-api-dhg9asefctasg4c0.eastus2-01.azurewebsites.net/api/disponibilidad-salones/disponibles-para-reserva", disponibilidad);
            disponibilidades.value = Array.isArray(response.data) ? response.data : [response.data] //Asegurandonos que llegue como array
            //console.log('Disponibilidades recibidas:', disponibilidades.value); 
        }else{
            console.log('El id del salon no esta disponible');
        }        
    } catch (error) {
        console.error("Error al obtener las disponibilidades:", error)
    }
    
}

async function reservar(disponibilidad) {
    const reserva = {
        idUsuario: 1, 
        idSalon: props.salon.id,
        fecha: new Date().toISOString(),
        idDisponibilidad: disponibilidad.id
    }

    console.log("Datos de reserva:", reserva);
    try {
        const response = await axios.post('https://salonesuservices-api-dhg9asefctasg4c0.eastus2-01.azurewebsites.net/api/reservas', reserva)
        if(response){
            ElMessage({
                message: '¡Reserva realizada con éxito!',
                type: 'success',
            })
        }
        cerrarModal()
    } catch (error) {
        ElMessage.error('Oops, no se pudo realizar la reserva')
    if (error.response && error.response.data) {
        console.error("Detalles del error del servidor:", error.response.data);
    }
    }
}

watch(() => props.abrirModal, (newValue) => {
    dialogTableVisible.value = newValue;
    if (newValue){
        obtenerDisponibilidad(); // Realizar la solicitud cuando se abre el modal
    }
});

function cerrarModal() {
    dialogTableVisible.value = false;
    emit('cerrar-modal');
}

</script>

<style scoped>
.dialog-footer {
    display: flex;
    justify-content: flex-end;
}

</style>