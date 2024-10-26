<template>
    <div class="Tarjeta">
        <div class="img">
            <img src="../../assets/img/sddefault.jpg" width="300" height="150" alt="">
        </div>
        <div class="descripcion">
            <h2>{{ salon.ubicacion }}</h2>
            <p>{{ salon.descripcion }}</p>
        </div>
    </div>
</template>

<script>
    export default {
            name: "SalonCard",
            props: {
                salon: Object,
            },
    };
</script>

<style scoped>

.Tarjeta{
    display: flex;
    flex-direction: column;
    overflow: hidden;
    box-shadow: 2px 4px 12px 5px rgba(0, 0, 0, 0.2);
    border-radius: 16px;
    padding: 1.2rem;
    width: 20rem;
    height: 20rem;
}

.Tarjeta .img{
    display: flex;
    justify-content: center;
}

.descripcion{
    margin-top: 1rem;
}

</style>