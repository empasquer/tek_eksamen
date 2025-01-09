<script setup lang="ts">
import axios from "axios";

async function fetchKommuner() {
  const response = await axios.get("http://localhost:8080/kommuner?regionskode=1084");
  console.log(response);
}
</script>

<template>
  <div>
    <button @click="fetchKommuner">Fetch Kommuner</button>
  </div>
</template>

<style scoped>
</style>
