<template>
<div>
  <div id="backg">
    <stulogin></stulogin>
  </div>
</div>
</template>

<script>
import stulogin from "@/views/stulogin";
import router from "@/router";
export default {
name: "homepage",
  components:{stulogin},
  mounted() {
    if (screen.width>600) {
      router.push('/pcstulogin')
    }
  }
}
</script>

<style scoped>
#backg{
  /*background: url("../../public/background.png");*/
  overflow:auto;
  /*background-color: red;*/
  background-size: 100% 100%;
  height: 100%;
  position: fixed;
  width: 100%;
}
</style>
