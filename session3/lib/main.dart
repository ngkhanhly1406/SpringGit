import 'package:flutter/material.dart';
void main() => runApp(MyApp());
// This Widget is the main application widget.
class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: MyStatefulWidget(),
    );
  }
}

class MyStatefulWidget extends StatefulWidget {
  MyStatefulWidget({Key? key}) : super(key: key);
}

// Items inside navigation bar
BottomNavigationBarItem(
icon: Icon(Icons.home),
label: "Home",
),
BottomNavigationBarItem(
icon: Icon(Icons.search),
label: "Search",
),
BottomNavigationBarItem(
icon: Icon(Icons.camera),
label: "Take Photo",
),
floatingActionButton: FloatingActionButton(
onPressed: () => setState(() {}),
child: Icon(Icons.add),
),
floatingActionButtonLocation: FloatingActionButtonLocation.centerDocked,

drawer: Drawer(
elevation: 20.0,
child: Column(
children: <Widget>[
UserAccountsDrawerHeader(
accountName: Text("Flutter :"),
child: Text("Ross"),
),
ListTile(
title: new Text("Inbox"),
leading: new Icon(Icons.mail),
),
Divider(
height: 0.1,
),
ListTile(
title: new Text("Primary"),
leading: new Icon(Icons.inbox),
),
],
),
),
