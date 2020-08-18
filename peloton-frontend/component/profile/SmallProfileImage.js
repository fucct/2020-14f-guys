import React from "react";
import { Text, View, StyleSheet, Image } from "react-native";

const SmallProfileImage = ({ member }) => {
  return (
    <Image
      style={styles.memberProfileImage}
      source={
        member
          ? { uri: member.profile }
          : require("../../assets/default-profile.jpg")
      }
      defaultSource={require("../../assets/default-profile.jpg")}
    />
  );
};

const styles = StyleSheet.create({
  memberProfileImage: {
    width: 50,
    height: 50,
    borderRadius: 25,
    marginRight: 10,
  },
});

export default SmallProfileImage;
