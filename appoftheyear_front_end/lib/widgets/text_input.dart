import 'package:flutter/material.dart';

import '../util/string_util.dart';

class TextInput extends StatelessWidget {
  String? hint;
  bool? obscureText;
  Icon? prefixIcon;
  Icon? suffixIcon;
  final TextEditingController controller;
  TextInput({super.key, this.hint, this.obscureText, this.prefixIcon,
    this.suffixIcon, required this.controller});

  @override
  Widget build(BuildContext context) {
    return TextField(
      controller: controller,
      obscureText: obscureText?? false,
      decoration: InputDecoration(
        enabledBorder: OutlineInputBorder(
          borderRadius: BorderRadius.circular(7),
          borderSide: BorderSide(color: Colors.grey.shade800),
        ),
        focusedBorder: OutlineInputBorder(
          borderRadius: BorderRadius.circular(7),
          borderSide: const BorderSide(color: Colors.blue),
        ),
        fillColor: Colors.white30,
        filled: true,
        hintText: hint,
        hintStyle: TextStyle(
            color: Colors.grey,
            fontSize: StringUtils.HINT_TEXT_SIZE,
        ),
        prefixIcon: prefixIcon,
        prefixIconColor: Colors.blue,
        suffixIcon: suffixIcon,
        suffixIconColor: Colors.blue,
      ),
      style: TextStyle(
        fontSize: StringUtils.INPUT_TEXT_SIZE,
      ),
    );
  }
}
