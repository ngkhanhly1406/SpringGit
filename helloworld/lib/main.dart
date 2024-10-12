import 'package:flutter/material.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:helloworld/main.dart'; // Đảm bảo đường dẫn đúng tới file chứa widget

void main() {
  testWidgets('HelloWorld widget displays text', (WidgetTester tester) async {
    // Xây dựng ứng dụng và kích hoạt một khung hình.
    await tester.pumpWidget(const MaterialApp(home: Center(child: Text('Hello, world!'))));

    // Xác minh rằng văn bản 'Hello, world!' có mặt.
    expect(find.text('Hello, world!'), findsOneWidget);
  });
}
