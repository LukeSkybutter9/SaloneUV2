<template>
  <table class="tabla">
    <thead>
      <tr>
        <th>Salon</th>
        <th>Hora de entrada</th>
        <th>Hora de salida</th>
        <th>Estado de la reserva</th>
      </tr>
    </thead>
    <tbody>
      <tr v-for="(fila, index) in filas" :key="index" :class="index % 2 === 0 ? 'fila_inpar' : 'fila_par'">
        <td>{{ fila.salon }}</td>
        <td>{{ fila.horaEntrada }}</td>
        <td>{{ fila.horaSalida }}</td>
        <td>{{ fila.estadoReserva }}</td>
      </tr>
    </tbody>
  </table>
</template>

<script>
export default {
  name: "TablaHistorial",
  data() {
    return {
      filas: [
        { salon: 'Dato 1.1', horaEntrada: 'Dato 1.2', horaSalida: 'Dato 1.3', estadoReserva: 'Dato 1.4' },
        { salon: 'Dato 2.1', horaEntrada: 'Dato 2.2', horaSalida: 'Dato 2.3', estadoReserva: 'Dato 2.4' },
        { salon: 'Dato 3.1', horaEntrada: 'Dato 3.2', horaSalida: 'Dato 3.3', estadoReserva: 'Dato 3.4' },
        { salon: 'Dato 4.1', horaEntrada: 'Dato 4.2', horaSalida: 'Dato 4.3', estadoReserva: 'Dato 4.4' },
      ]
    };
  }
};
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