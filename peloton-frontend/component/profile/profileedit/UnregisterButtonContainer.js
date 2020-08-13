import React from "react";
import { Alert, StyleSheet, Text, TouchableOpacity, View } from "react-native";
import { COLOR } from "../../../utils/constants";
import { useRecoilValue } from "recoil";
import { memberTokenState } from "../../../state/member/MemberState";
import { MemberApi } from "../../../utils/api/MemberApi";

const UnregisterButtonContainer = ({ navigation }) => {
  const token = useRecoilValue(memberTokenState);

  const requestUnregister = async () => {
    await MemberApi.delete(token);
    navigation.navigate("Home");
  };

  const createTwoButtonAlert = () =>
    Alert.alert(
      "Unregister",
      "Why so serious",
      [
        {
          text: "Nope",
          onPress: () => console.log("someone tried to unregister"),
          style: "cancel",
        },
        { text: "Yes", onPress: requestUnregister },
      ],
      { cancelable: false },
    );

  return (
    <View style={styles.unregisterBtnContainer}>
      <TouchableOpacity style={styles.button} onPress={createTwoButtonAlert}>
        <Text style={styles.buttonText}>Unregister</Text>
      </TouchableOpacity>
    </View>
  );
};

const styles = StyleSheet.create({
  unregisterBtnContainer: {
    flex: 1,
    alignItems: "center",
    paddingBottom: 50,
  },
  button: {
    marginTop: 50,
    borderWidth: 1,
    width: 150,
    height: 50,
    borderColor: COLOR.WHITE3,
    borderRadius: 100,
    alignItems: "center",
    justifyContent: "center",
  },
  buttonText: {
    color: COLOR.GREEN2,
    fontSize: 14,
  },
});

export default UnregisterButtonContainer;
