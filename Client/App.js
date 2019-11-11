import React from 'react';
import {Provider} from 'react-redux';
import {createStore, applyMiddleware} from 'redux';
import thunk from 'redux-thunk';
import {Router, Stack, Scene} from 'react-native-router-flux';
import {composeWithDevTools} from 'redux-devtools-extension';
import Dashboard from './components/dashboard/dashboard';
import Player from './components/player/player';
import Favorites from './components/favorites/favorites';
import reducer from './store/reducers/reducer';
const store = createStore(reducer, composeWithDevTools(applyMiddleware(thunk)));

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
