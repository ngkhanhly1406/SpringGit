import 'package:flutter/material.dart';

void main() {
  runApp(TascApp());
}

class TascApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: HomePage(),
    );
  }
}

class HomePage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Hi Guy!'),
      ),
      body: Column(
        children: [
          Padding(
            padding: const EdgeInsets.all(8.0),
            child: TextField(
              decoration: InputDecoration(
                hintText: 'Search your destination',
                prefixIcon: Icon(Icons.search),
                border: OutlineInputBorder(
                  borderRadius: BorderRadius.circular(8.0),
                ),
              ),
            ),
          ),
          Expanded(
            child: GridView.builder(
              itemCount: 4,  // Thay bằng số lượng nơi bạn tải về từ API
              gridDelegate: SliverGridDelegateWithFixedCrossAxisCount(
                crossAxisCount: 2,
              ),
              itemBuilder: (context, index) {
                return Card(
                  child: Column(
                    children: [
                      Image.network('https://via.placeholder.com/150'),  // Thay thế bằng ảnh thực
                      Text('Hoi An'),  // Tên địa điểm
                      Text('4.5'),  // Rating
                    ],
                  ),
                );
              },
            ),
          ),
        ],
      ),
    );
  }
}
