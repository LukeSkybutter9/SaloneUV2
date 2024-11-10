<template>
    <el-dialog :dialogTableVisible="dialogTableVisible" v-model="dialogTableVisible" title="Disponibilidades" width="800" @close="cerrarModal">
            <p>Salon Seleccionado - {{ salon?.nombreEdificio +" "+ salon?.nombre }}</p>
            
        <template #footer>  
            <div class="dialog-footer">
                <el-button @click="cerrarModal">Cancel</el-button>
                <el-button type="primary" @click="cerrarModal">
                    Reservar
                </el-button>
            </div>
        </template>
    </el-dialog>
</template>

<script lang="ts" setup>
    import { ref , watch, defineEmits, onUpdated} from 'vue'
    import axios from 'axios';
    import { ElButton, ElDialog} from 'element-plus';
    
    const dialogTableVisible = ref(false);
    const emit = defineEmits(['cerrar-modal']);

    const props = defineProps({
        abrirModal: Boolean,
        salon: {
            type: Object,
            required: true
        }
        
    });

    const salonId = props.salon.idSalon; 

    console.log("Salon "+salonId);

    const disponibilidades = ref([]);

onUpdated(async () => {
    try {
        const response = await axios.get('https://salonesuservices-api-dhg9asefctasg4c0.eastus2-01.azurewebsites.net/api/disponibilidad-salones/${salonId}');
        disponibilidades.value = response.data;
    } catch (error) {
        console.error("Error al obtener las disponibilidades:", error)
    }
    
})

watch(() => props.abrirModal, (newValue) => {
    dialogTableVisible.value = newValue;
});

// Emite el evento para indicar que la modal se ha cerrado
function cerrarModal() {
    dialogTableVisible.value = false;
    emit('cerrar-modal');
}

</script>