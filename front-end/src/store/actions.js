import meService from '@/services/me';

export const getMyData = ({ commit }) => {
  meService.getMyData().then((data) => {
    commit('updateMyData', data.response);
  });
};

export const addTeam = ({ commit }, team) => {
  commit('addTeam', team);
};

export const addBoard = ({ commit }, board) => {
  commit('addBoard', board);
};
