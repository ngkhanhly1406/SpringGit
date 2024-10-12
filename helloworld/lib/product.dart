import 'package:flutter/material.dart';

// Định nghĩa lớp Product
class Product {
  const Product({required this.name});
  final String name;
}

typedef CartChangedCallback = Function(Product product, bool inCart);

// Định nghĩa widget ShoppingListItem
class ShoppingListItem extends StatelessWidget {
   ShoppingListItem({
    required this.product,
    required this.inCart,
    required this.onCartChanged,
   }) :super(key:ObjectKey(product));

  final Product product;
  final bool inCart;
  final CartChangedCallback onCartChanged;

  Color _getColor(BuildContext context) {
    // Trả về màu sắc tùy thuộc vào việc sản phẩm có trong giỏ hàng hay không
    return inCart
        ? Colors.black54
        : Theme.of(context).primaryColor;
  }

  TextStyle? _getTextStyle(BuildContext context) {
    if (!inCart) return null;

    return const TextStyle(
      color: Colors.black54,
      decoration: TextDecoration.lineThrough,
    );
  }

  @override
  Widget build(BuildContext context) {
    return ListTile(
      onTap: () {
        onCartChanged(product, inCart); // Đảo ngược trạng thái inCart khi nhấn
      },
      leading: CircleAvatar(
        backgroundColor: _getColor(context),
        child: Text(product.name[0]), // Hiển thị chữ cái đầu tiên của tên sản phẩm
      ),
      title: Text(product.name, style: _getTextStyle(context)),
    );
  }
}

// Hàm main để chạy ứng dụng
void main() {
  runApp(
     MaterialApp(
      home: Scaffold(
        body: Center(
          child: ShoppingListItem(
            product:const Product(name: 'Chips'),
            inCart: false,
            onCartChanged: (product, inCart) {},
          ),
        ),
      ),
    ),
  );
}
