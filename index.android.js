/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 * @flow
 */

import React, { Component } from 'react';
import {
  AppRegistry,
  StyleSheet,
  Text,
  Alert,
  Button,
  View
} from 'react-native';

import ChromeCastButton from './ChromeCastButton'
import RNCastControls from './CastControls';


export default class NativeTEs extends Component {
  _onPressCastButton() {
    RNCastControls.loadVideo("¿Qué es Inteligencia Artificial?", "Curso de Introducción a Machine Learning", "https://static.platzi.com/media/achievements/badge-INTRO-machine-learning-.png", "http://clips.vorwaerts-gmbh.de/VfE_html5.mp4");
  }
  _onPressCastPlayButton(){
    RNCastControls.playVideo();
  }
  _onPressCastPauseButton(){
    RNCastControls.pauseVideo();
  }
  _onPressCastStopButton(){
    RNCastControls.stopVideo();
  }
  _onPressCastSeekButton(){
    RNCastControls.seekToPosition(30000);
  }
  _onPressCastIsReadyButton(){
    RNCastControls.isChromecastReady(
      (available) => { alert(available);}
    )
  }
  _onPressCastCurrentPositionButton(){
    RNCastControls.getCurrentPosition(
      (position) => { alert(position);}
    )
  }

  _onPressCastIsPlayingButton(){
    RNCastControls.isPlaying(
      (available) => { alert(available);}
    )
  }

  _onPressCastIsPausedButton(){
    RNCastControls.isPaused(
      (available) => { alert(available);}
    )
  }


  render() {
    return (
      <View style={styles.container}>
        <ChromeCastButton style={{left: 10, width: 50 , height:50}}/>
        <Button
            onPress={this._onPressCastButton}
            title="Cast Video"
            color="#841584"
          />
          <Button
              onPress={this._onPressCastPlayButton}
              title="Play Video"
              color="#841584"
            />
            <Button
                onPress={this._onPressCastPauseButton}
                title="Pause"
                color="#841584"
              />
              <Button
                  onPress={this._onPressCastStopButton}
                  title="Stop"
                  color="#841584"
                />
                <Button
                    onPress={this._onPressCastSeekButton}
                    title="Seek"
                    color="#841584"
                  />
                  <Button
                      onPress={this._onPressCastIsReadyButton}
                      title="Chromecast Available"
                      color="#841584"
                    />
                    <Button
                        onPress={this._onPressCastCurrentPositionButton}
                        title="Chromecast position"
                        color="#841584"
                      />
                      <Button
                          onPress={this._onPressCastIsPlayingButton}
                          title="Chromecast Playing"
                          color="#841584"
                        />
                        <Button
                            onPress={this._onPressCastIsPausedButton}
                            title="Chromecast Paused"
                            color="#841584"
                          />

      </View>
    );
  }


}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#F5FCFF',
  },
  welcome: {
    fontSize: 20,
    textAlign: 'center',
    margin: 10,
  },
  instructions: {
    textAlign: 'center',
    color: '#333333',
    marginBottom: 5,
  },
});

AppRegistry.registerComponent('NativeCast', () => NativeTEs);
