<template>
  <table class="tabla">
    <thead>
      <tr>
        <th>Fecha</th>
        <th>Hora de entrada</th>
        <th>Hora de salida</th>
        <th>Estado de la reserva</th>
      </tr>
    </thead>
    <tbody>
      <tr v-for="(reserva, index) in reservas" :key="index" :class="index % 2 === 0 ? 'fila_inpar' : 'fila_par'">
        <td>{{ reserva.fecha }}</td>
        <td>{{ reserva.horaInicio }}</td>
        <td>{{ reserva.horaFin }}</td>
        <td>{{ reserva.idEstado.nombre }}</td>
      </tr>
    </tbody>
  </table>
</template>

<script setup>
import { ref , onMounted} from 'vue';
import axios from 'axios';
import { jwtDecode } from "jwt-decode";

    const reservas = ref([]);

onMounted(async () => {
  try {
          const token = localStorage.getItem("token");
          const decodedToken = jwtDecode(token);
          const usuarioId = decodedToken.userId;
          console.log(usuarioId)
          const response = await axios.get(`https://salonesuservices-api-dhg9asefctasg4c0.eastus2-01.azurewebsites.net/api/reservas/usuario/${usuarioId}`);
          reservas.value = response.data.map((reserva) => {
          const date = new Date(reserva.fecha);
          const año = date.getFullYear();
          const mes = String(date.getMonth() + 1).padStart(2, '0'); 
          const dia = String(date.getDate()).padStart(2, '0');
          return {
            ...reserva,
            fecha: `${año}-${mes}-${dia}`,
            horaInicio: reserva.horaInicio.slice(0, 8),
            horaFin: reserva.horaFin.slice(0, 8),
          };
        });
    } catch (error) {
      console.error("Error al obtener las reservas realizadas:", error)
    }
})

</script>

<style scoped>
.tabla {
  width: 95%;
  border-collapse: collapse;
  border-radius: 8px;
  overflow: hidden;
}

th,
td {
  padding: 12px;
  text-align: center;
  vertical-align: middle;
  border-bottom: 1px solid #ddd;
  width: 25%;
}

th {
  background-color: #004a87;
  color: white;
  font-size: 1rem;
}

.fila_inpar {
  background-color: #f9f9f9;
}

.fila_par {
  background-color: #ffffff;
}

tr:hover {
  background-color: #f1f1f1;
}

.salones table {
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

@media (max-width: 768px) {
  table {
    font-size: 0.9rem;
  }

  th,
  td {
    padding: 8px;
  }
}
</style>