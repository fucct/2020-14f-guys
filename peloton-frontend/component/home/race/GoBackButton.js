import React from "react";
import { StyleSheet, TouchableOpacity, View } from "react-native";
import { COLOR } from "../../../utils/constants";
import { useNavigation } from "@react-navigation/core";
import { AntDesign } from "@expo/vector-icons";

const GoBackButton = () => {
  const navigation = useNavigation();

  const goBack = async () => {
    if (navigation.canGoBack()) {
      navigation.goBack();
    }
  };

  return (
    <View style={styles.buttonContainer}>
      <TouchableOpacity style={styles.goBackButton} onPress={goBack}>
        <AntDesign name="left" size={30} color={COLOR.BLACK} />
      </TouchableOpacity>
    </View>
  );
};

const styles = StyleSheet.create({
  goBackButton: {
    width: 50,
    height: 50,
    padding: 10,
  },
});

export default GoBackButton;
