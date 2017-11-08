import React, { Component, PropTypes } from 'react';
import { requireNativeComponent, ViewPropTypes } from 'react-native';


export default class ChromeButton extends Component {
    render() {
        return  <NativeChromeCastButton {...this.props} />;
    }
}

ChromeButton.propTypes = {
    indeterminate: PropTypes.bool,
    ...ViewPropTypes
}

let NativeChromeCastButton = requireNativeComponent('RCTChromeCastButton', ChromeButton);
