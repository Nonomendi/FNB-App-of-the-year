import 'package:appoftheyear_front_end/widgets/text_input.dart';
import 'package:flutter/material.dart';

import '../util/string_util.dart';

class SignIn extends StatelessWidget {
  const SignIn({super.key});

  @override
  Widget build(BuildContext context) {
    TextEditingController usernameController = TextEditingController();

    return Center(
      child: SingleChildScrollView(
        padding: const EdgeInsets.all(10),
        child: Column(
          children: [
            const Text(
              "Sign In",
              style: TextStyle(
                fontSize: 28,
              ),
            ),
            TextInput(
              controller: usernameController,
              hint: "Username",
              prefixIcon: const Icon(Icons.person),
            ),
            const SizedBox(
              height: 12,
            ),
            TextInput(
              controller: usernameController,
              hint: "Password",
              obscureText: true,
              prefixIcon: const Icon(Icons.password),
            ),
            const SizedBox(
              height: 12,
            ),
            Row(
              children: [
                Expanded(
                  child: ElevatedButton(
                    onPressed: () {},
                    style: ElevatedButton.styleFrom(
                      backgroundColor: Colors.blue,
                      textStyle: TextStyle(
                        fontSize: StringUtils.BUTTON_TEXT_SIZE,
                      ),
                    ),
                    child: const Text("Login"),
                  ),
                ),
              ],
            )
          ],
        ),
      ),
    );
  }
}
