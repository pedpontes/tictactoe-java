package tictactoe;

import java.util.*;

public class Game {

    private Player players[];
    private char tab[][];

    public Game() { //construtor da classe
        this.players = new Player[2];
        this.tab = new char[3][3];

        for (int i = 0; i < 2; i++) {
            this.players[i] = new Player();
        }
    }

    public void resetTab() {    //reinicia o tabuleiro
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.tab[i][j] = ' ';
            }
        }
    }

    private Boolean addPos(Player pl) { //adiciona a tag à posicao no tabuleiro
        Scanner scan = new Scanner(System.in);

        System.out.println("Vez do jogador: " + pl.getName());

        System.out.print("Digite qual a linha 0-2: ");
        int posx = scan.nextInt();

        System.out.print("Digite qual a coluna 0-2: ");
        int posy = scan.nextInt();

        if (posx >= 0 && posx < 3 && posy >= 0 && posy < 3) {
            if (this.tab[posx][posy] != ' ') {
                System.out.println("Posicao ja ocupada, tente novamente!");
                return false;
            }
            this.tab[posx][posy] = pl.getTag();
            return true;
        }
        return false;
    }

    private Boolean winner(Player pl) { //verifica se algum player venceu
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

    private void addNames() {   //adiciona nomes e tags ao player respectivo

        Scanner scan = new Scanner(System.in);
        String names[] = new String[2];
        char tag[] = new char[2];

        System.out.println("Wellcome to the Tic Tac Toe!");

        for (int i = 0; i < 2; i++) {

            System.out.print("Name player " + (i + 1) + ": ");
            names[i] = scan.next();

            System.out.print("Tag player " + (i + 1) + ": ");
            tag[i] = scan.next().charAt(0);

            if (names[i].length() == 0)
                names[i] = "User " + i;

            if (tag[i] == '\n')
                tag[i] = 'z';

            this.players[i].setName(names[i]);
            this.players[i].setTag(tag[i]);
        }
        return;
    }

    private void printTab() {   //imprime o tabuleiro no console
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

    private Boolean velha() {   //verifica se houve empate
        int posOcup = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                if(this.tab[i][j] != ' ') 
                    posOcup++;
            }
        }
        return (posOcup == 9) ? true : false;
    }

    public void startGame() {   //inicia o jogo
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

        } while (!winner(pl) || velha());

        if (!velha())
            System.out.println("O jogador " + pl.getName() + " venceu!");
        else
            System.out.println("Empate, jogo deu velha! Reiniciando o tabuleiro...");

        startGame();
    }
}