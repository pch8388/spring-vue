import { mount, createLocalVue } from '@vue/test-utils';
import RegisterPage from '@/views/RegisterPage';
import VueRouter from 'vue-router';
import Vuelidate from 'vuelidate';
import registrationService from '@/services/registration';

const localVue = createLocalVue();
localVue.use(VueRouter);
localVue.use(Vuelidate);
const router = new VueRouter();

jest.mock('@/services/registration')

describe('RegisterPage.vue', () => {
  let wrapper;
  let fieldUsername;
  let fieldEmailAddress;
  let fieldPassword;
  let buttonSubmit;
  let registerSpy;

  beforeEach(() => {
    wrapper = mount(RegisterPage, {
      localVue,
      router
    });

    fieldUsername = wrapper.find('#username');
    fieldEmailAddress = wrapper.find('#emailAddress');
    fieldPassword = wrapper.find('#password');
    buttonSubmit = wrapper.find('form button[type="submit"]');
    registerSpy = jest.spyOn(registrationService, 'register');
  });

  afterEach(() => {
    registerSpy.mockReset();
    registerSpy.mockRestore();
  });

  afterAll(() => {
    jest.restoreAllMocks();
  });

  it('should render correct contents', () => {
    expect(wrapper.find('.logo').attributes().src)
      .toEqual('/static/images/logo.png');
    expect(wrapper.find('.tagline').text())
      .toEqual('Open source task management tool');
    expect(fieldUsername.element.value).toEqual('');
    expect(fieldEmailAddress.element.value).toEqual('');
    expect(fieldPassword.element.value).toEqual('');
    expect(buttonSubmit.text()).toEqual('Create account');
  });

  it('should contain data model with initial values', () => {
    expect(wrapper.vm.form.username).toEqual('');
    expect(wrapper.vm.form.emailAddress).toEqual('');
    expect(wrapper.vm.form.password).toEqual('');
  });

  it('should have form inputs bound with data model', () => {
    const username = 'sunny';
    const emailAddress = 'test@test.com';
    const password = 'VueTest!';

    wrapper.vm.form.username = username;
    wrapper.vm.form.emailAddress = emailAddress;
    wrapper.vm.form.password = password;
    expect(fieldUsername.element.value).toEqual(username);
    expect(fieldEmailAddress.element.value).toEqual(emailAddress);
    expect(fieldPassword.element.value).toEqual(password);
  });

  it('should have form submit event handler `submitForm`', () => {
    const stub = jest.fn();
    wrapper.setMethods({submitForm: stub});
    buttonSubmit.trigger('submit');
    expect(stub).toBeCalled();
  });

  it('should register when it is a new user', async () => {
    expect.assertions(2);
    const stub = jest.fn();
    wrapper.vm.$router.push = stub;
    wrapper.vm.form.username = 'sunny';
    wrapper.vm.form.emailAddress = 'test@test.com';
    wrapper.vm.form.password = 'Jest!';
    wrapper.vm.submitForm();
    expect(registerSpy).toBeCalled();
    await wrapper.vm.$nextTick();
    expect(stub).toHaveBeenCalledWith({name: 'LoginPage'});
  });

  it('should fail it is not a new user', async () => {
    expect.assertions(3);
    wrapper.vm.form.emailAddress = 'ted@taskagile.com';
    expect(wrapper.find('.failed').isVisible()).toBe(false);
    wrapper.vm.submitForm();
    expect(registerSpy).toBeCalled();
    await wrapper.vm.$nextTick();
    expect(wrapper.find('.failed').isVisible()).toBe(true);
  });

  it('should fail when the email address is invalid', () => {
    wrapper.vm.form.emailAddress = 'bad-email-address';
    wrapper.vm.submitForm();
    expect(registerSpy).not.toHaveBeenCalled();
  });
});
