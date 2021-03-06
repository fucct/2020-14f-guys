import React, { useState } from "react";
import {
  Alert,
  Modal,
  Platform,
  SafeAreaView,
  StyleSheet,
  TouchableOpacity,
  View,
} from "react-native";
import KakaoLoginWebView from "./KakaoLoginWebView";
import LoginTitle from "./LoginTitle";
import * as AppleAuthentication from "expo-apple-authentication";
import { animated, useSpring } from "react-spring";
import { AnimatedImage, COLOR, TOKEN_STORAGE } from "../../utils/constants";
import AsyncStorage from "@react-native-community/async-storage";
import { useRecoilState, useSetRecoilState } from "recoil";
import { useNavigation } from "@react-navigation/core";
import LoadingIndicator from "../../utils/LoadingIndicator";
import { loadingState } from "../../state/loading/LoadingState";
import { navigateWithoutHistory } from "../../utils/util";
import {
  memberInfoState,
  memberTokenState,
} from "../../state/member/MemberState";
import { MemberApi } from "../../utils/api/MemberApi";
import { logNav } from "../../utils/Analytics";
import { useRecoilValue } from "recoil/dist";

const AnimatedAppleButton =
  Platform.OS === "ios"
    ? animated(AppleAuthentication.AppleAuthenticationButton)
    : null;

const Login = () => {
  const [tokenInfo, setTokenInfo] = useRecoilState(memberTokenState);
  const setMemberInfo = useSetRecoilState(memberInfoState);
  const setIsLoading = useSetRecoilState(loadingState);
  const navigation = useNavigation();

  const navigationToAgreement = async () => {
    setIsLoading(true);
    logNav("Login", "Agreement");
    let token;
    if (tokenInfo) {
      token = tokenInfo;
    }
    if (!token) {
      token = await AsyncStorage.getItem(TOKEN_STORAGE);
    }
    if (token) {
      setTokenInfo(token);
      try {
        const memberResponse = await MemberApi.get(token);
        setMemberInfo(memberResponse);
        navigateWithoutHistory(navigation, "ApplicationNavigationRoot");
      } catch (error) {
        console.log(error.response.data.message);
        navigation.navigate("Agreement");
      }
    } else {
      navigation.navigate("Agreement");
    }
    setIsLoading(false);
  };

  const buttonOpacity = useSpring({
    config: {
      duration: 800,
    },
    delay: 1200,
    opacity: 1,
    from: {
      opacity: 0,
    },
  });

  return (
    <LoadingIndicator>
      <SafeAreaView style={styles.background}>
        <View style={styles.titleContainer}>
          <LoginTitle />
        </View>
        <View style={styles.loginButtonContainer}>
          <TouchableOpacity onPress={navigationToAgreement}>
            <AnimatedImage
              style={{
                ...styles.loginButton,
                opacity: buttonOpacity.opacity,
              }}
              source={require("../../assets/kakao_login_medium_wide.png")}
            />
          </TouchableOpacity>
          {Platform.OS === "ios" && (
            <AnimatedAppleButton
              buttonType={
                AppleAuthentication.AppleAuthenticationButtonType.SIGN_IN
              }
              buttonStyle={
                AppleAuthentication.AppleAuthenticationButtonStyle.BLACK
              }
              style={{ ...styles.loginButton, opacity: buttonOpacity.opacity }}
              onPress={() => Alert.alert("힝 속았지?")}
            />
          )}
        </View>
      </SafeAreaView>
    </LoadingIndicator>
  );
};

const styles = StyleSheet.create({
  background: {
    flex: 1,
    backgroundColor: COLOR.BLUE5,
  },
  titleContainer: {
    flex: 7,
    height: 286,
    justifyContent: "center",
    alignItems: "center",
  },
  loginButtonContainer: {
    flex: 3,
    justifyContent: "center",
    alignItems: "center",
  },
  loginButton: {
    marginVertical: 5,
    justifyContent: "center",
    alignItems: "center",
    width: 300,
    height: 45,
    resizeMode: "contain",
  },
});

export default Login;
