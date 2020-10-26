export default {
  authenticate(detail) {
    return new Promise((resolve, reject) => {
      detail.emailAddress === 'test@test.com'
        ? resolve({result: 'success'})
        : reject(new Error('ID or Password invalid'));
    });
  }
};
