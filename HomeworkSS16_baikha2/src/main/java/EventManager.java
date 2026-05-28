import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class EventManager {
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    private List<Event> events = new ArrayList<>();
    private Scanner scanner;

    public EventManager(Scanner scanner) {
        this.scanner = scanner;
    }



    public void addEvent() {
        System.out.println("=== THÊM SỰ KIỆN MỚI ===");

        String name = readNonEmpty("Tên sự kiện: ");

        LocalDateTime start = readDateTime("Thời gian bắt đầu (dd/MM/yyyy HH:mm): ");
        LocalDateTime end   = null;

        // Đảm bảo ngày kết thúc sau ngày bắt đầu
        while (true) {
            end = readDateTime("Thời gian kết thúc (dd/MM/yyyy HH:mm): ");
            if (end.isAfter(start)) break;
            System.out.println("Thời gian kết thúc phải sau thời gian bắt đầu. Vui lòng nhập lại.");
        }

        events.add(new Event(name, start, end));
        System.out.println("Đã thêm sự kiện: \"" + name + "\"");
    }

    // ===================== Hiển thị =====================

    public void showAll() {
        System.out.println("=== DANH SÁCH TẤT CẢ SỰ KIỆN ===");
        if (events.isEmpty()) {
            System.out.println("Chưa có sự kiện nào");
            return;
        }
        printDivider();
        for (int i = 0; i < events.size(); i++) {
            System.out.printf("  [%d]%n", i + 1);
            System.out.println(events.get(i));
            printDivider();
        }
    }

    public void showByStatus() {
        System.out.println("=== LỌC THEO TRẠNG THÁI ===");
        System.out.println("1. Sắp diễn ra");
        System.out.println("2. Đang diễn ra");
        System.out.println("3. Đã kết thúc");
        System.out.print("Chọn: ");
        String choice = scanner.nextLine().trim();

        LocalDateTime now = LocalDateTime.now();
        List<Event> filtered = switch (choice) {
            case "1" -> events.stream()
                    .filter(e -> now.isBefore(e.getStartDate()))
                    .sorted(Comparator.comparing(Event::getStartDate))
                    .toList();
            case "2" -> events.stream()
                    .filter(e -> !now.isBefore(e.getStartDate()) && !now.isAfter(e.getEndDate()))
                    .toList();
            case "3" -> events.stream()
                    .filter(e -> now.isAfter(e.getEndDate()))
                    .sorted(Comparator.comparing(Event::getEndDate).reversed())
                    .toList();
            default -> { System.out.println("Lựa chọn không hợp lệ."); yield List.of(); }
        };

        if (filtered.isEmpty()) {
            System.out.println("Không có sự kiện nào");
            return;
        }
        printDivider();
        filtered.forEach(e -> { System.out.println(e); printDivider(); });
    }

    public void showUpcomingNext7Days() {
        LocalDateTime now  = LocalDateTime.now();
        LocalDateTime next7 = now.plusDays(7);

        System.out.println("=== SỰ KIỆN TRONG 7 NGÀY TỚI ===");
        List<Event> result = events.stream()
                .filter(e -> !e.getStartDate().isBefore(now) && e.getStartDate().isBefore(next7))
                .sorted(Comparator.comparing(Event::getStartDate))
                .toList();

        if (result.isEmpty()) {
            System.out.println("Không có sự kiện nào trong 7 ngày tới");
            return;
        }
        printDivider();
        result.forEach(e -> { System.out.println(e); printDivider(); });
    }



    /**
     * Đọc LocalDateTime với xử lý ngoại lệ DateTimeParseException,
     * cho phép thử lại không giới hạn.
     */
    private LocalDateTime readDateTime(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return LocalDateTime.parse(input, FORMATTER);
            } catch (DateTimeParseException e) {
                System.out.println("Sai định dạng! Vui lòng nhập theo mẫu: dd/MM/yyyy HH:mm");
            }
        }
    }

    private String readNonEmpty(String prompt) {
        while (true) {
            System.out.print(prompt);
            String val = scanner.nextLine().trim();
            if (!val.isEmpty()) return val;
            System.out.println("Không được để trống!");
        }
    }

    private void printDivider() {
        System.out.println("  " + "-".repeat(44));
    }

    public void addDirect(Event e) { events.add(e); }

    public boolean isEmpty() { return events.isEmpty(); }
}