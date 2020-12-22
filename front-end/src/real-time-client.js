import Vue from 'vue';
import SockJS from 'sockjs-client';
import globalBus from '@/event-bus';

class RealTimeClient {
  constructor() {
    this.serverUrl = null;
    this.token = null;
    this.socket = null;
    this.authenticated = false;
    this.loggedOut = false;
    this.$bus = new Vue();
    this.subscribeQueue = {
      /* channel: [handler1, handler2] */
    };
    this.unsubscribeQueue = {
      /* channel: [handler1, handler2] */
    };
  }

  init(serverUrl, token) {
    if (this.authenticated) {
      console.warn('[RealTimeClient] WS connection already authenticated.');
      return;
    }

    this.serverUrl = serverUrl;
    this.token = token;
    this.connect();
  }

  logout() {
    this.subscribeQueue = {};
    this.unsubscribeQueue = {};
    this.authenticated = false;
    this.loggedOut = true;
    this.socket && this.socket.close();
  }

  connect() {
    this.socket = new SockJS(`${this.serverUrl}?token=${this.token}`);
    this.socket.onopen = () => {
      this.authenticated = true;
      this._onConnected();
    };
    this.socket.onmessage = (event) => {
      this._onMessageReceived(event);
    };
    this.socket.onerror = (error) => {
      this._onSocketError(error);
    };
    this.socket.onclose = (event) => {
      this._onClosed(event);
    };
  }

  subscribe(channel, handler) {
    if (!this._isConnected()) {
      this._addToSubscribeQueue(channel, handler);
      return;
    }
    const message = {
      action: 'subscribe',
      channel
    };
    this._send(message);
    this.$bus.$on(this._channelEvent(channel), handler);
  }

  unsubscribe(channel, handler) {
    if (this.loggedOut) {
      return;
    }

    if (!this._isConnected()) {
      this._addToUnsubscribeQueue(channel, handler);
      return;
    }

    const message = {
      action: 'unsubscribe',
      channel
    };

    this._send(message);
    this.$bus.$off(this._channelEvent(channel), handler);
  }

  _isConnected() {
    return this.socket && this.socket.readyState === SockJS.OPEN;
  }

  _onConnected() {
    globalBus.$emit('RealTimeClient.connected');
    this._processQueues();
  }

  _onMessageReceived(event) {
    const message = JSON.parse(event.data);

    if (message.channel) {
      this.$bus.$emit(this._channelEvent(message.channel), JSON.parse(message.payload));
    }
  }

  _send(message) {
    this.socket.send(JSON.stringify(message));
  }

  _onSocketError(error) {
    console.error('[RealTimeClient] Socket error', error);
  }

  _onClosed(event) {
    if (this.loggedOut) {
      globalBus.$emit('RealTimeClient.loggedOut');
      return;
    }

    globalBus.$emit('RealTimeClient.disconnected');

    setTimeout(() => {
      globalBus.$emit('RealTimeClient.reconnecting');
      this.connect();
    }, 1000);
  }

  _channelEvent(channel) {
    return `channel:${channel}`;
  }

  _processQueues() {
    const subscribeChannels = Object.keys(this.subscribeQueue);
    subscribeChannels.forEach(channel => {
      const handlers = this.subscribeQueue[channel];
      handlers.focus(handler => {
        this.subscribe(channel, handler);
        this._removeFromQueue(this.subscribeQueue, channel, handler);
      });
    });

    const unsubscribeChannels = Object.keys(this.unsubscribeQueue);
    unsubscribeChannels.forEach(channel => {
      const handlers = this.unsubscribeQueue[channel];
      handlers.focus(handler => {
        this.unsubscribe(channel, handler);
        this._removeFromQueue(this.unsubscribeQueue, channel, handler);
      });
    });
  }

  _addToSubscribeQueue(channel, handler) {
    this._removeFromQueue(this.subscribeQueue, channel, handler);
    const handlers = this.subscribeQueue[channel];
    if (!handlers) {
      this.subscribeQueue[channel] = [handler];
      return;
    }

    handlers.push(handler);
  }

  _addToUnsubscribeQueue(channel, handler) {
    this._removeFromQueue(this.unsubscribeQueue, channel, handler);
    const handlers = this.unsubscribeQueue[channel];
    if (!handlers) {
      this.unsubscribeQueue[channel] = [handler];
      return;
    }

    handlers.push(handler);
  }

  _removeFromQueue(queue, channel, handler) {
    const handlers = queue[channel];
    if (handlers) {
      let index = handlers.indexOf(handler);
      if (index > -1) {
        handlers.splice(index, 1);
      }
    }
  }
}

export default new RealTimeClient();
