import 'package:appoftheyear_front_end/controller/api_network.dart';
import 'package:appoftheyear_front_end/views/dashboard_view.dart';
import 'package:appoftheyear_front_end/widgets/text_input.dart';
import 'package:appoftheyear_front_end/util/string_util.dart';
import 'package:flutter/material.dart';
import 'package:shared_preferences/shared_preferences.dart';

class SignUp extends StatefulWidget {
  const SignUp({super.key});

  @override
  State<SignUp> createState() => _SignUpState();
}

class _SignUpState extends State<SignUp> {
  TextEditingController usernameController = TextEditingController();
  TextEditingController emailController = TextEditingController();
  TextEditingController passwordController = TextEditingController();
  TextEditingController passwordRepeatController = TextEditingController();

  APIController controller = APIController();
  String? errorText;

  @override
  Widget build(BuildContext context) {
    return Center(
      child: SingleChildScrollView(
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
              controller: emailController,
              prefixIcon: const Icon(Icons.email),
            ),
            const SizedBox(
              height: 12,
            ),
            TextInput(
              hint: "Enter password",
              controller: passwordController,
              prefixIcon: const Icon(Icons.password),
              obscureText: true,
            ),
            const SizedBox(
              height: 12,
            ),
            TextInput(
              hint: "Repeat password",
              controller: passwordRepeatController,
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
                    onPressed: () async {
                      String username = usernameController.text;
                      String email = emailController.text;
                      String pass = passwordController.text;

                      if (passwordRepeatController.text == pass) {
                        String? result = await controller.registerUser(
                            username, email, pass);
                        if (result != null) {
                          final prefs = await SharedPreferences.getInstance();
                          prefs.setString("user_id", result);

                          Navigator.push(
                            context,
                            MaterialPageRoute(
                                builder: (context) => const DashboardView()),
                          );
                        }
                      } else {
                        setState(() {
                          errorText = "Passwords do not match";
                        });
                      }
                    },
                    style: ElevatedButton.styleFrom(
                      backgroundColor: Colors.blue,
                      textStyle: TextStyle(
                        fontSize: StringUtils.BUTTON_TEXT_SIZE,
                      ),
                    ),
                    child: const Text("Register"),
                  ),
                ),
              ],
            ),
            Text(
              errorText ?? "",
              style: TextStyle(
                color: Colors.red,
                fontSize: StringUtils.INPUT_TEXT_SIZE,
              ),
            ),
          ],
        ),
      ),
    );
  }
}
