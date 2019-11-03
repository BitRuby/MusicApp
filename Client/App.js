import React from 'react';
import {Provider} from 'react-redux';
import {createStore} from 'redux';
import {Router, Stack, Scene} from 'react-native-router-flux';
import Dashboard from './components/dashboard/dashboard';
import Player from './components/player/player';
import Favorites from './components/favorites/favorites';
import Reducer from './storage/reducer';
const store = createStore(Reducer);

const App = () => {
  return (
    <Provider store={store}>
      <Router>
        <Stack key="root" hideNavBar={true}>
          <Scene key="dashboard" component={Dashboard} initial />
          <Scene key="player" component={Player} />
          <Scene key="favorites" component={Favorites} />
        </Stack>
      </Router>
    </Provider>
  );
};

export default App;
