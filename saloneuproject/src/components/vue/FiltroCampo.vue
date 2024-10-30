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
                    :key="option.status"  
                    :label="option.status" 
                    :value="option.status" 
                />
            </el-select>
        </el-form-item>

        <!-- Contenedor de las tarjetas de salones -->
        <div class="salones gap-16">
            <SalonCard 
                v-for="salon in filteredSalones" 
                :key="salon.id" 
                :salon="salon" 
            />
        </div>
    </el-form>
</template>

<script setup>
import { ref, onMounted, computed } from 'vue'
import { ElForm,ElFormItem,ElSelect,ElOption } from 'element-plus'
import axios from 'axios'
import SalonCard from './SalonCard.vue'

const salones = ref([])

const formInline = ref({
    region: 'Alive'
})

onMounted(async () => {
    try {
        const response = await axios.get('https://rickandmortyapi.com/api/character/?page=21')
        salones.value = response.data.results
    } catch (error) {
        console.error("Error al obtener los salones:", error)
    }
})

// Computed para obtener edificios únicos
const uniqueEdificios = computed(() => {
    const statusSet = new Set()
    return salones.value.filter((salon) => {
        if (!statusSet.has(salon.status)) {
            statusSet.add(salon.status)
            return true
        }
        return false
    })
})

// Computed para filtrar los salones según la región seleccionada en el select
const filteredSalones = computed(() => {
    return formInline.value.region
        ? salones.value.filter(salon => salon.status === formInline.value.region)
        : salones.value
})
</script>

<style scoped>
.salones {
    display: flex;
    flex-wrap: wrap;
}


</style>