import java.util.*;
import java.util.stream.*;

public class ProductManager {
    private HashMap<Integer, Product> products = new HashMap<>();
    private int nextId = 1;



    public void addProduct(String name, double price) {
        Product p = new Product(nextId, name, price);
        products.put(nextId, p);
        System.out.println("Đã thêm sản phẩm: " + p.getName() + " (ID=" + nextId + ")");
        nextId++;
    }

    public boolean updateProduct(int id, String newName, double newPrice) {
        Product p = products.get(id);
        if (p == null) {
            System.out.println("Không tìm thấy sản phẩm với ID = " + id);
            return false;
        }
        p.setName(newName);
        p.setPrice(newPrice);
        System.out.println("Đã cập nhật sản phẩm ID = " + id);
        return true;
    }

    public boolean deleteProduct(int id) {
        if (!products.containsKey(id)) {
            System.out.println("Không tìm thấy sản phẩm với ID = " + id);
            return false;
        }
        products.remove(id);
        System.out.println("Đã xóa sản phẩm ID = " + id);
        return true;
    }

    public void showProducts() {
        if (products.isEmpty()) {
            System.out.println(" (Danh sách trống)");
            return;
        }
        printHeader();
        products.values().stream()
                .sorted(Comparator.comparingInt(Product::getId))
                .forEach(System.out::println);
        printFooter();
    }



    public void filterByPriceAbove(double threshold) {
        List<Product> result = products.values().stream()
                .filter(p -> p.getPrice() > threshold)
                .sorted(Comparator.comparingDouble(Product::getPrice).reversed())
                .collect(Collectors.toList());

        System.out.println("Sản phẩm có giá > " + threshold + ":");
        if (result.isEmpty()) {
            System.out.println("(Không có sản phẩm nào)");
            return;
        }
        printHeader();
        result.forEach(System.out::println);
        printFooter();
    }

    public void calculateTotal() {
        double total = products.values().stream()
                .mapToDouble(Product::getPrice)
                .sum();
        System.out.printf("Tổng giá trị tất cả sản phẩm: %,.2f%n", total);
    }



    private void printHeader() {
        System.out.println("+------+----------------------+------------+");
        System.out.println("| ID   | Tên sản phẩm         |      Giá   |");
        System.out.println("+------+----------------------+------------+");
    }

    private void printFooter() {
        System.out.println("+------+----------------------+------------+");
    }

    public boolean isEmpty() {
        return products.isEmpty();
    }
}