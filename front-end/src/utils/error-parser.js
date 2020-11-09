import _ from 'lodash';

export default {
  parse(error) {
    const { response } = error;
    console.log(error);
    console.log(response);
    if (error) {
      const { status, message } = response.data.error;
      if (status === 400) {
        return message ? new Error(message) : new Error('Bad request.');
      }

      if (status === 401) {
        return new Error('Request not authorized.');
      }

      if (status === 403) {
        return new Error('Request forbidden.');
      }

      if (status === 404) {
        return new Error('Request failed. Request endpoint not found on the server.');
      }

      if (status === 500) {
        return message
          ? new Error(`${message} Please try again later.`)
          : new Error('There is an error on the server side. Please try again later.');
      }

      return new Error('Request failed. Please try again later.');
    }

    if (error.request) {
      return new Error('Request failed. No err from the server.');
    }

    return _.isError(error) ? error : new Error(error);
  },
};
