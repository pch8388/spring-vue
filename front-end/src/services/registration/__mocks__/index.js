export default {
  register(detail) {
    return new Promise((resolve, reject) => {
      detail.emailAddress === 'test@test.com'
        ? resolve({result: 'success'})
        : reject(new Error('User already exist'));
    });
  }
}
