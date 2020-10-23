<template>
  <div class="container">
    <div class="row justify-content-center">
      <div class="register-form">
        <div class="logo-wrapper">
          <img src="/static/images/logo.png" alt="logo" class="logo">
          <div class="tagline">Open source task management tool</div>
        </div>
        <form @submit.prevent="submitForm">
          <div v-show="errorMessage" class="alert alert-danger failed">
            {{ errorMessage }}
          </div>
          <div class="form-group">
            <label for="username">Username</label>
            <input type="text" class="form-control" id="username" v-model="form.username">
          </div>
          <div class="form-group">
            <label for="emailAddress">Email address</label>
            <input type="email" class="form-control" id="emailAddress" v-model="form.emailAddress">
          </div>
          <div class="form-group">
            <label for="password">Password</label>
            <input type="password" class="form-control" id="password" v-model="form.password">
          </div>
          <button type="submit" class="btn btn-primary btn-block">Create account</button>
          <p class="accept-terms text-muted">By clicking "Create account", you agree to our
            <a href="#">terms of service</a> and <a href="#">privacy policy</a>.
          </p>
          <p class="text-center text-muted">Already have an account? <a href="/login">Sign in</a></p>
        </form>
      </div>
    </div>
    <footer class="footer">
      <span class="copyright">&copy; 2020 Trello Clone Coding</span>
      <ul class="footer-links list-inline float-right">
        <li class="list-inline-item"><a href="#">About</a></li>
        <li class="list-inline-item"><a href="#">Terms of Service</a></li>
        <li class="list-inline-item"><a href="#">Privacy Policy</a></li>
        <li class="list-inline-item">
          <a href="https://github.com/pch8388/til/blob/master/docs/Frontend/Vue.md" target="_blank">GitHub</a>
        </li>
      </ul>
    </footer>
  </div>
</template>

<script>
import registrationService from '@/service/registration';

export default {
  name: "RegisterPage",
  data: function() {
    return {
      form: {
        username: '',
        emailAddress: '',
        password: ''
      }
    }
  },
  methods: {
    submitForm() {
      registrationService.register(this.form).then(() => {
        this.$router.push({name: 'LoginPage'})
      }).catch((error) => {
        this.errorMassage =
          `Failed to register user. Reason: ${error.message ? error.message : 'Unknown'}.`;
      });
    }
  }
}
</script>

<style lang="scss" scoped>
.container {max-width: 900px;}
.register-form {margin-top: 50px; max-width: 320px;}
.logo-wrapper {margin-bottom: 40px;}
.footer {width: 100%; line-height: 40px; margin-top: 50px;}
</style>
