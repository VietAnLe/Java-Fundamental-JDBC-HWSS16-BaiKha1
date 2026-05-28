import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static ProductManager manager = new ProductManager();

    public static void main(String[] args) {
        // Thêm dữ liệu mẫu
        manager.addProduct("Laptop Dell XPS", 1500.00);
        manager.addProduct("Chuột Logitech", 45.00);
        manager.addProduct("Bàn phím Keychron", 120.00);
        manager.addProduct("Màn hình LG 27", 350.00);
        manager.addProduct("USB Hub 7 cổng", 25.00);

        while (true) {
            printMenu();
            int choice = readInt("Nhập lựa chọn: ");

            System.out.println();
            switch (choice) {
                case 1 -> handleAdd();
                case 2 -> handleUpdate();
                case 3 -> handleDelete();
                case 4 -> {
                    System.out.println("=== DANH SÁCH SẢN PHẨM ===");
                    manager.showProducts();
                }
                case 5 -> {
                    System.out.println("=== LỌC SẢN PHẨM GIÁ > 100 ===");
                    manager.filterByPriceAbove(100);
                }
                case 6 -> {
                    System.out.println("=== TỔNG GIÁ TRỊ KHO ===");
                    manager.calculateTotal();
                }
                case 0 -> {
                    System.out.println("Tạm biệt!");
                    return;
                }
                default -> System.out.println("Lựa chọn không hợp lệ, vui lòng thử lại.");
            }
            System.out.println();
        }
    }

    // ==================== Handlers ====================

    static void handleAdd() {
        System.out.println("=== THÊM SẢN PHẨM MỚI ===");
        String name = readString("Tên sản phẩm: ");
        double price = readDouble("Giá sản phẩm: ");
        manager.addProduct(name, price);
    }

    static void handleUpdate() {
        System.out.println("=== SỬA SẢN PHẨM ===");
        manager.showProducts();
        int id = readInt("Nhập ID cần sửa: ");
        String name = readString("  Tên mới: ");
        double price = readDouble("  Giá mới: ");
        manager.updateProduct(id, name, price);
    }

    static void handleDelete() {
        System.out.println("=== XÓA SẢN PHẨM ===");
        manager.showProducts();
        int id = readInt("Nhập ID cần xóa: ");
        manager.deleteProduct(id);
    }

    // ==================== UI ====================

    static void printMenu() {
        System.out.println("╔══════════════════════════════╗");
        System.out.println("║    QUẢN LÝ SẢN PHẨM v1.0    ║");
        System.out.println("╠══════════════════════════════╣");
        System.out.println("║  1. Thêm sản phẩm            ║");
        System.out.println("║  2. Sửa sản phẩm             ║");
        System.out.println("║  3. Xóa sản phẩm             ║");
        System.out.println("║  4. Hiển thị tất cả          ║");
        System.out.println("║  5. Lọc giá > 100            ║");
        System.out.println("║  6. Tổng giá trị kho         ║");
        System.out.println("║  0. Thoát                    ║");
        System.out.println("╚══════════════════════════════╝");
    }

    // ==================== Input Helpers ====================

    static int readInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                int value = scanner.nextInt();
                scanner.nextLine(); // clear buffer
                return value;
            } catch (InputMismatchException e) {
                System.out.println("Vui lòng nhập số nguyên!");
                scanner.nextLine();
            }
        }
    }

    static double readDouble(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                double value = scanner.nextDouble();
                scanner.nextLine();
                return value;
            } catch (InputMismatchException e) {
                System.out.println("Vui lòng nhập số hợp lệ!");
                scanner.nextLine();
            }
        }
    }

    static String readString(String prompt) {
        System.out.print(prompt);
        String value = scanner.nextLine().trim();
        while (value.isEmpty()) {
            System.out.println("Không được để trống!");
            System.out.print(prompt);
            value = scanner.nextLine().trim();
        }
        return value;
    }
}