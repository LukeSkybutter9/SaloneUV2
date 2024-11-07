<template>
    <div class="campoForm">
        <label :for="name">{{ label }}</label>
        <input 
            :type="type" 
            :placeholder="placeholder" 
            :id="name" 
            :value="modelValue" 
            />
    </div>
</template>

<script setup>
import { defineProps } from 'vue'

//@input="$emit('update:modelValue', $event.target.value)" 

const props = defineProps({  // eslint-disable-line no-unused-vars
    salon: Object,
    label:{
            type: String,
            required: true
        },
        type: {
            type: String,
            required: true
        },
        placeholder: {
            type: String,
            required: true
        },
        modelValue: {
            type: String,
            required: true
        },
        name: {
            type: String,
            required: true
        }
})
</script>

<style scoped>
.campoForm{
    display: flex;
    flex-direction: column;
    text-align: left;
    font: bold;
    gap:5px;
}

.campoForm input{
    margin-bottom: 15px;
    border: #F0F0F0;
    border-radius: 5px;
    box-shadow: 0px 4px 12px rgba(0, 0, 0, 0.1);
    padding: 10px;
    outline: none;
}

.campoForm input:focus {
    box-shadow: 0px 4px 12px rgba(0, 0, 0, 0.2);
}

button{
    padding: 10px 20px;
    width: 100%;
    border: none;
    background-color: #004a87;
    color: white;
    cursor: pointer;
    border-radius: 5px;
}

</style>
