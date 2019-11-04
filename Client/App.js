import React from 'react';
import {Provider} from 'react-redux';
import {createStore, applyMiddleware, compose} from 'redux';
import thunk from 'redux-thunk';
import {Router, Stack, Scene} from 'react-native-router-flux';
import Dashboard from './components/dashboard/dashboard';
import Player from './components/player/player';
import Favorites from './components/favorites/favorites';
import Reducer from './store/reducers/favorites';
const composeEnhancers = window.__REDUX_DEVTOOLS_EXTENSION_COMPOSE__ || compose;
const store = createStore(Reducer, composeEnhancers(applyMiddleware(thunk)));

const App = () => {
  return (
    <Provider store={store}>
      <Router>
        <Stack key="root" hideNavBar={true}>
          <Scene key="dashboard" component={Dashboard} />
          <Scene key="player" component={Player} />
          <Scene key="favorites" component={Favorites} initial />
        </Stack>
      </Router>
    </Provider>
  );
};

export default App;
