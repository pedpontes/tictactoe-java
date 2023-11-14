package tictactoe;

import java.util.*;

public class Game {

    private Player players[];
    private char tab[][];

    public Game() {
        this.players = new Player[2];
        this.tab = new char[3][3];
    }

    public void resetTab() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.tab[i][j] = ' ';
            }
        }
    }

    private Boolean addPos(Player pl) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Vez do jogador: " + pl.getName());

        System.out.println("Digite a posicao na linha 0-2: ");
        int posx = scan.nextInt();

        System.out.println("Digite a posicao na coluna 0-2: ");
        int posy = scan.nextInt();

        if (posx >= 0 && posx < 3 && posy >= 0 && posy < 3) {
            if (this.tab[posx][posy] != ' ') {
                return false;
            }
            this.tab[posx][posy] = pl.getTag();
            return true;
        }
        return false;
    }

    private Boolean winner(Player pl) {
        for (int i = 0; i < 3; i++) {
            if (this.tab[i][0] == pl.getTag() && this.tab[i][1] == pl.getTag() && this.tab[i][2] == pl.getTag()) {
                return true;
            }
        }
        for (int j = 0; j < 3; j++) {
            if (this.tab[0][j] == pl.getTag() && this.tab[1][j] == pl.getTag() && this.tab[2][j] == pl.getTag()) {
                return true;
            }
        }
        if (this.tab[0][0] == pl.getTag() && this.tab[1][1] == pl.getTag() && this.tab[2][2] == pl.getTag()) {
            return true;
        }
        if (this.tab[0][2] == pl.getTag() && this.tab[1][1] == pl.getTag() && this.tab[2][0] == pl.getTag()) {
            return true;
        }
        return false;
    }

    private void addNames() {

        Scanner scan = new Scanner(System.in);
        String names[] = new String[2];
        char tag[] = new char[2];

        System.out.println("Wellcome to the Tic Tac Toe!");

        for (int i = 0; i < 2; i++) {

            System.out.println("Name player " + (i + 1) + ":");
            names[i] = scan.next();

            System.out.println("Tag player " + (i + 1) + ":");
            tag[i] = scan.next().charAt(0);

            if (names[i].length() == 0)
                names[i] = "User " + i;

            if (tag[i] == '\n')
                tag[i] = 'z';

            this.players[i] = new Player();

            this.players[i].setName(names[i]);
            this.players[i].setTag(tag[i]);
        }
        return;
    }

    private void printTab() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(tab[i][j]);
                if (j < 2)
                    System.out.print("\t|\t");
                else
                    System.out.println();
            }
        }
    }

    public void startGame() {
        Boolean index = false, validPos;
        resetTab();
        Player pl = null;
        addNames();

        do {
            pl = !index ? players[0] : players[1];
            index = !index;

            do {
                validPos = addPos(pl);
            } while (!validPos);
            printTab();

        } while (!winner(pl));

        System.out.println("O jogador " + pl.getName() + "venceu!");
    }
}