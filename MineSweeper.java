import java.util.Scanner;

public class MineSweeper {
    int rowCount;
    int colCount;
    int mineCount;
    char[][] gameBoard;
    char[][] gameMap;
    int satir;
    int sutun;
    int nearStarCount;

    MineSweeper(int row, int col) {
        this.rowCount = row;
        this.colCount = col;
        this.gameBoard = new char[row][col];
        this.gameMap = new char[row][col];
        this.mineCount = ((col * row) / 4);


    }

    void minePosition() { // ilk olara yıldızlar nereye onu random ile gameMap e atadık
        for (int i = 0; i < mineCount; i++) {
            while (true) {
                int a = (int) (Math.random() * rowCount);
                int b = (int) (Math.random() * colCount);
                if (gameMap[a][b] != '*')
                    gameMap[a][b] = '*';
                break;
            }
        }
    }

    void printGameMap() {
        System.out.println("Mayın Tarlasının Konumu");
        minePosition(); // ile yıldızları bastığımız metodu çağırdık Asıl işlem GameMap Arrayinde
        for (int i = 0; i < gameMap.length; i++) {
            for (int j = 0; j < gameMap[0].length; j++) {
                if (gameMap[i][j] != '*') { // ile yıldız olmayan yerlere - bastık
                    gameMap[i][j] = '-';
                }
                System.out.print(gameMap[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("==============================");
    }

    void elementsGameBoard() {
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[i].length; j++) {
                gameBoard[i][j] = '-';
            }
        }
    }

    void printGameBoard() {
        for (int i = 0; i < this.gameBoard.length; i++) {
            for (int j = 0; j < this.gameBoard[i].length; j++) {
                System.out.print(this.gameBoard[i][j] + " ");
            }
            System.out.println();
        }
    }

    void getData() { // Kullanıcıdab bilgi alıyoruz
        boolean isTrue = false;
        Scanner scan = new Scanner(System.in);
        System.out.println("Seçmek istediğiniz noktanın satır ve sutun bilgilerini giriniz");
        System.out.print("Satır bilgisini giriniz:");
        satir = (scan.nextInt() - 1);
        System.out.print("Sutun bilgisini giriniz:");
        sutun = (scan.nextInt() - 1);
        while (!isTrue) { // isTrue == false ile aynı yanlış girerse tekrar istenir
            if (satir < 0 || satir > gameBoard.length - 1 || sutun < 0 || sutun > gameBoard[0].length - 1) {
                System.out.println("Hatalı bir giriş yaptınız tekrar giriniz.");
                System.out.print("Satır bilgisini giriniz:");
                satir = scan.nextInt() - 1;
                System.out.print("Sutun bilgisini giriniz:");
                sutun = scan.nextInt() - 1;
            } else
                isTrue = true;
        }
        System.out.println("=========================");
    }

    public int starCountonMap() { // Seçilen alan yakınında kaç tane yıldız var sayısını verir
        //Sağı - solu- yukarısı-aşağısı sağ,sol çapraz_üst - sağ,sol çapraz alt
        nearStarCount = 0;
        if (sutun + 1 < gameMap[0].length - 1) {
            if (gameMap[satir][sutun + 1] == '*')
                nearStarCount++;
        }

        if (sutun - 1 >= 0) {
            if (gameMap[satir][sutun - 1] == '*')
                nearStarCount++;
        }

        if (satir + 1 < gameMap.length - 1) {
            if (gameMap[satir + 1][sutun] == '*')
                nearStarCount++;
        }

        if (satir - 1 >= 0) {
            if (gameMap[satir - 1][sutun] == '*')
                nearStarCount++;
        }
        if (satir - 1 >= 0 && sutun - 1 >= 0) {
            if (gameMap[satir - 1][sutun - 1] == '*')
                nearStarCount++;
        }
        if (satir - 1 >= 0 && sutun + 1 < gameMap[0].length - 1)
            if (gameMap[satir - 1][sutun + 1] == '*')
                nearStarCount++;
        if (satir + 1 < gameMap.length - 1 && sutun - 1 >= 0)
            if (gameMap[satir + 1][sutun - 1] == '*')
                nearStarCount++;
        if (satir + 1 < gameMap.length - 1 && sutun + 1 < gameMap[0].length - 1)
            if (gameMap[satir + 1][sutun + 1] == '*')
                nearStarCount++;
        return nearStarCount;
    }

    public void changeBoardGame() {
        switch (starCountonMap()) { // Toplamda kaç tane yıldız varsa yakınında onun sayısını buluyoruz
            case 0:
                gameBoard[satir][sutun] = '0';
                gameMap[satir][sutun] = '0';
                break;
            case 1:
                gameBoard[satir][sutun] = '1';
                gameMap[satir][sutun] = '1';
                break;

            case 2:
                gameBoard[satir][sutun] = '2';
                gameMap[satir][sutun] = '2';
                break;

            case 3:
                gameBoard[satir][sutun] = '3';
                gameMap[satir][sutun] = '3';
                break;
            case 4:
                gameBoard[satir][sutun] = '4';
                gameMap[satir][sutun] = '4';
                break;

            case 5:
                gameBoard[satir][sutun] = '5';
                gameMap[satir][sutun] = '5';
                break;

            case 6:
                gameBoard[satir][sutun] = '6';
                gameMap[satir][sutun] = '6';
                break;
            case 7:
                gameBoard[satir][sutun] = '7';
                gameMap[satir][sutun] = '7';
                break;  // Daha fazla gidilebilirdi ben 7 ye kadar gittim
        }

    }

    boolean checkContinue() {
        for (int i = 0; i < gameMap.length; i++) {
            for (int j = 0; j < gameMap[0].length; j++) {
                if (gameMap[i][j] == '-')
                    return false;
            }
        }
        return true;
    }

    public void run() {
        printGameMap();// Yıldızlar gameMap arrayine eklendi ve yıldızlı hali ekrana bastık
        System.out.println("Mayın Tarlası Oyununa Hoşgeldiniz ! ");
        System.out.println("-----------------------------------");
        elementsGameBoard(); // İçerisinde yıldız görünmeyen kullanıcı oyun base i
        printGameBoard(); // ekrana bastık bunu
        while (true) { // Burada loop sayısı hesaplanıp for döngüsü de olabilirdi ama while(true) hayat kurtarır :)
            this.getData();
            if (gameMap[satir][sutun] == '*') {
                System.out.println("Mayına bastınız.");
                System.out.println("Game Over !!!");
                break;
            } else {
                changeBoardGame();
                printGameBoard();
                if ((checkContinue())) {
                    System.out.println("Tebrikler...Oyunu Kazandınız !");
                    break;
                }
            }
        }

    }
}
