import 'package:appoftheyear_front_end/widgets/text_input.dart';
import 'package:flutter/material.dart';

class SignUp extends StatefulWidget {
  const SignUp({super.key});

  @override
  State<SignUp> createState() => _SignUpState();
}

class _SignUpState extends State<SignUp> {
  TextEditingController usernameController = TextEditingController();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(),
      body: SingleChildScrollView(
        padding: const EdgeInsets.all(10),
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          crossAxisAlignment: CrossAxisAlignment.center,
          children: [
            const Text(
              "Sign up",
              style: TextStyle(
                fontSize: 28,
              ),
            ),
            const SizedBox(
              height: 12,
            ),
            TextInput(
              hint: "Enter username",
              controller: usernameController,
              prefixIcon: const Icon(Icons.person),
            ),
            const SizedBox(
              height: 12,
            ),
            TextInput(
              hint: "Enter email address",
              controller: usernameController,
              prefixIcon: const Icon(Icons.email),
            ),
            const SizedBox(
              height: 12,
            ),
            TextInput(
              hint: "Enter password",
              controller: usernameController,
              prefixIcon: const Icon(Icons.password),
              obscureText: true,
            ),
            const SizedBox(
              height: 12,
            ),
            TextInput(
              hint: "Repeat password",
              controller: usernameController,
              prefixIcon: const Icon(Icons.password),
              obscureText: true,
            ),
            const SizedBox(
              height: 12,
            ),
            Row(
              children: [
                Expanded(
                  child: ElevatedButton(
                    onPressed: () {},
                    style:  ElevatedButton.styleFrom(
                      backgroundColor: Colors.blue,
                      textStyle: const TextStyle(
                        fontSize: 14,
                      ),
                    ),
                    child: const Text("Register"),
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