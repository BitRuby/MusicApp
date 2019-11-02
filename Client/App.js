import React from 'react';
import LinearGradient from 'react-native-linear-gradient';
import Dashboard from './components/dashboard/dashboard';
import Player from './components/player/player';

class App extends React.Component {
  render() {
    return (
      <LinearGradient colors={['#1A1A1A', '#3B3B3B']} style={{flex: 1}}>
        {/* <Dashboard /> */}
        <Player />
      </LinearGradient>
    );
  }
}

export default App;
