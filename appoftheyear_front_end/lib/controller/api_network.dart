import 'dart:convert';

import 'package:flutter/cupertino.dart';
import 'package:http/http.dart' as http;

class APIController {
  final String host = "http://10.0.2.2:8080";

  Future<void> fetchUsers() async {
    final response = await http.get(Uri.parse("${host}/api/v1/users"));

    if (response.statusCode == 200) {
      debugPrint(response.body);
    } else {
      debugPrint("Failed to load data");
    }
  }

  Future<String?> registerUser(String username, String email, String password) async {
    final response = await http.post(
      Uri.parse("${host}/api/v1/users"),
      headers: <String, String> {
        'Content-Type': 'application/json; charset=UTF-8',
      },
      body: jsonEncode(
        <String, String>{
          "username": username,
          "email": email,
          "password": password
        }
      ),
    );

    if (response.statusCode == 201) {
      return response.body;
    }
    return null;
  }
}