import 'package:flutter/material.dart';
import 'package:shared_preferences/shared_preferences.dart';

class DashboardView extends StatefulWidget {
  const DashboardView({super.key});

  @override
  State<DashboardView> createState() => _DashboardViewState();
}

class _DashboardViewState extends State<DashboardView> {
  String? _userId;

  Future<String?> loadUserId() async {
    final prefs = await SharedPreferences.getInstance();
    return prefs.getString("user_id");
  }

  @override
  void initState() {
    super.initState();
    loadUserId().then((value) {
      setState(() {
        _userId = value;
      });
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Center(
        child: SingleChildScrollView(
          child: Text(_userId?? "Nothing saved"),
        ),
      ),
    );
  }
}