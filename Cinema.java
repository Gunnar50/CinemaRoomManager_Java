import java.util.Scanner;

public class Cinema {

    int currentIncome = 0;
    int ticketsSold = 0;
    double totalSeats;
    int rows;
    int cols;
    char[][] cinema;
    boolean running = true;


    Scanner scanner = new Scanner(System.in);

    Cinema() {
        System.out.println("Enter the number of rows:");
        rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        cols = scanner.nextInt();
        totalSeats = rows * cols;
        cinema = new char[rows+1][cols+1];
    }

    public void menu() {
        System.out.println();
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
        int option = scanner.nextInt();
        switch (option) {
            case 0:
               running = false;
               break;
            case 1:
                displayLayout();
                break;
            case 2:
                buyTicket();
                break;
            case 3:
                statistics();
                break;
        }
    }

    public void statistics() {
        System.out.println();
        System.out.printf("Number of purchased tickets: %d\n", ticketsSold);
        System.out.printf("Percentage: %.2f%%\n", (100 * ticketsSold)/totalSeats);
        System.out.printf("Current income: $%d\n", currentIncome);
        System.out.printf("Total income: $%d\n", totalIncome());
    }

    public int totalIncome() {
        int total;
        if (rows * cols < 60) total = rows * cols * 10;
        else {
            total = 10 * (rows / 2) * cols;
            if (rows % 2 == 0) total += (rows / 2) * 8 * cols;
            else total += (1 + (rows / 2)) * 8 * cols;
        }
        return total;
    }

    public void buyTicket() {
        int ticketPrice;
        int row;
        int seat;

        while (true) {
            System.out.println("Enter a row number:");
            row = scanner.nextInt();

            System.out.println("Enter a seat number in that row:");
            seat = scanner.nextInt();

            // Check if row and seat are in bounds, and check if is already taken.
            if ((0 < row && row <= rows) && (0 < seat && seat <= cols)) {
                if (cinema[row][seat] == 'S') {
                    cinema[row][seat] = 'B';
                    break;
                } else System.out.println("That ticket has already been purchased!");
            } else {
                System.out.println("Wrong input!");
            }
        }

        // Check for the ticket price
        // If the total number of seats is less than 60, ticket price is $10
        if (rows * cols < 60) ticketPrice = 10;
        else {
            // Else the first half is $10, and second half is $8
            if (row <= rows / 2) ticketPrice = 10;
            else ticketPrice = 8;
        }
        currentIncome += ticketPrice;
        ticketsSold++;
        System.out.println();
        System.out.println("Ticket price $" + ticketPrice);
    }

    public static void main(String[] args) {
        Cinema cinema = new Cinema();
        cinema.createCinemaLayout();
        while (cinema.running) {
            cinema.menu();
        }
    }

    public void displayLayout() {
        System.out.println();
        System.out.println("Cinema:");
        for (char[] chars : cinema) {
            for (char seat : chars) {
                System.out.print(seat + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void createCinemaLayout() {
        for (int x = 0; x < cinema.length; x++) {
            for (int y = 0; y < cinema[x].length; y++) {
                if (x == 0 && y == 0) {
                    cinema[x][y] = ' ';
                }
                else if (x == 0) {
                    cinema[x][y] = (char) (y + '0');
                }
                else if (y == 0) {
                    cinema[x][y] = (char) (x + '0');
                }
                else {
                    cinema[x][y] = 'S';
                }
            }
        }
    }

}
