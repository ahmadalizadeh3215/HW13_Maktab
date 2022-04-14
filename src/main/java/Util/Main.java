package Util;

public class Main {
    public static void main(String[] args) {
      App app = new App();
       FakeDB fakeDB= new FakeDB();
       fakeDB.fillDB();
        while (true) {
            switch (app.homeMenu()) {
                case 0:
                    System.out.println("Invalid Number !!!");
                    break;
                case 1:
                    app.loginMenuEmployee();
                    break;
                case 2:
                   app.menuLoginStudent();
                    break;
                case 3:
                    app.menuLoginTeacher();
                    break;
                case 4:
                    System.out.println("Good Luck!");
                    System.exit(0);
            }
        }
    }
}
