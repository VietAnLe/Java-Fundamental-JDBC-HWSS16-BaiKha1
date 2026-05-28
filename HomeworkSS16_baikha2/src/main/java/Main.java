import java.time.LocalDateTime;
import java.util.Scanner;

public class Main {
    static Scanner scanner     = new Scanner(System.in);
    static EventManager manager = new EventManager(scanner);

    public static void main(String[] args) {

        LocalDateTime now = LocalDateTime.now();
        manager.addDirect(new Event("Hội thảo Java Spring",
                now.minusDays(3), now.minusDays(2)));
        manager.addDirect(new Event("Hackathon SV 2025",
                now.minusHours(2), now.plusHours(4)));
        manager.addDirect(new Event("Khóa học Docker",
                now.plusDays(3), now.plusDays(5)));


        while (true) {
            printMenu();
            System.out.print("Nhập lựa chọn: ");
            String choice = scanner.nextLine().trim();
            System.out.println();

            switch (choice) {
                case "1" -> manager.addEvent();
                case "2" -> manager.showAll();
                case "3" -> manager.showByStatus();
                case "4" -> manager.showUpcomingNext7Days();
                case "0" -> { System.out.println("Tạm biệt!"); return; }
                default  -> System.out.println("Lựa chọn không hợp lệ, vui lòng thử lại.");
            }
            System.out.println();
        }
    }

    static void printMenu() {
        System.out.println("╔══════════════════════════════════╗");
        System.out.println("║  QUẢN LÝ SỰ KIỆN - DateTime API  ║");
        System.out.println("╠══════════════════════════════════╣");
        System.out.println("║  1. Thêm sự kiện mới             ║");
        System.out.println("║  2. Hiển thị tất cả sự kiện      ║");
        System.out.println("║  3. Lọc theo trạng thái          ║");
        System.out.println("║  4. Sự kiện trong 7 ngày tới     ║");
        System.out.println("║  0. Thoát                        ║");
        System.out.println("╚══════════════════════════════════╝");
    }
}