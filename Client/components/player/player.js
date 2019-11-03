import React from 'react';
import styles from './player.style';
import {View, Text, Image, Slider, ScrollView} from 'react-native';
import Icon from 'react-native-vector-icons/FontAwesome5';
import LinearGradient from 'react-native-linear-gradient';

const Player = props => {
  const [play, setPlay] = React.useState({
    album: 'Shenanigans',
    title: 'You Lied',
    artist: 'Green Day',
    image: require('../../assets/images/1.jpg'),
  });

  const [value, onChange] = React.useState(60);
  return (
    <LinearGradient colors={['#1A1A1A', '#3B3B3B']} style={{flex: 1}}>
      <View style={styles.view}>
        <Text style={styles.header}>Odtwarzanie z albumu</Text>
        <Text style={styles.album}>{play.album}</Text>
        <ScrollView
          style={styles.imageSlider}
          horizontal={true}
          pagingEnabled={true}
          showsHorizontalScrollIndicator={false}
          decelerationRate={'fast'}>
          <View style={styles.imageContainer}>
            <Image source={play.image} style={styles.image}></Image>
          </View>
          <View style={styles.imageContainer}>
            <Image source={play.image} style={styles.image}></Image>
          </View>
          <View style={styles.imageContainer}>
            <Image source={play.image} style={styles.image}></Image>
          </View>
        </ScrollView>
        <View style={styles.description}>
          <View>
            <ScrollView style={styles.descriptionContent} horizontal={true}>
              <Text numberOfLines={1} ellipsizeMode="tail" style={styles.title}>
                {play.title}
              </Text>
            </ScrollView>
            <ScrollView style={styles.descriptionContent} horizontal={true}>
              <Text numberOfLines={1} style={styles.artist}>
                {play.artist}
              </Text>
            </ScrollView>
          </View>
          <View>
            <Icon
              name="heart"
              style={styles.heartIcon}
              size={18}
              color="#777"
              solid
            />
          </View>
        </View>
        <View style={styles.time}>
          <Text style={styles.timelapse}>1:56</Text>
          <Text style={styles.timelapse}>2:25</Text>
        </View>
        <Slider
          minimumTrackTintColor="#FB266E"
          maximumTrackTintColor="#777"
          thumbTintColor="#fff"
          style={styles.slider}
          step={1}
          maximumValue={100}
          onValueChange={() => onChange()}
          value={value}
        />
        <View style={styles.playerIcons}>
          <Icon name="sync" size={20} color="#fff" />
          <Icon name="step-backward" size={20} color="#fff" />
          <Icon name="play" style={styles.playIcon} size={20} color="#fff" />
          <Icon name="step-forward" size={20} color="#fff" />
          <Icon name="random" size={20} color="#fff" />
        </View>
      </View>
    </LinearGradient>
  );
};

//Add PropTypes, DefaultValues, Redux, StyleSheet

export default Player;
