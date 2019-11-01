import React from 'react';
import Searchbox from './components/searchbox/searchbox';
import LinearGradient from 'react-native-linear-gradient';
import Carousel from './components/carousel/carousel';

class App extends React.Component {
  render() {
    return (
      <LinearGradient colors={['#1A1A1A', '#3B3B3B']} style={{flex: 1}}>
        <Searchbox />
        <Carousel title="Favorites" />
        <Carousel title="Playlists" />
      </LinearGradient>
    );
  }
}

export default App;
