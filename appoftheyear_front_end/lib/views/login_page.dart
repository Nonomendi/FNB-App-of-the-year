import 'package:flutter/material.dart';
import 'package:google_nav_bar/google_nav_bar.dart';

import '../pages/sign_in.dart';
import '../pages/sign_up.dart';

class LoginPage extends StatefulWidget {
  const LoginPage({super.key});

  @override
  State<LoginPage> createState() => _LoginPageState();
}

class _LoginPageState extends State<LoginPage> {
  int currentIndex = 0;
  List<Widget> pages = const [SignUp(), SignIn()];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: pages[currentIndex],
      bottomNavigationBar: GNav(
        backgroundColor: Colors.white30,
        padding: const EdgeInsets.only(left: 50, right: 50, bottom: 25, top: 20),
        onTabChange: (index) {
          setState(() {
            currentIndex = index;
          });
        },
        tabs: const [
          GButton(
            icon: Icons.person_add,
            text: "Register",
          ),
          GButton(
            icon: Icons.lock,
            text: "Login",
          ),
        ],
        activeColor: Colors.blue,
        tabBackgroundColor: Colors.white60,
      ),
    );
  }
}
