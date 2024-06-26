package jogo;

import jplay.GameObject;
import jplay.Keyboard;
import jplay.Scene;
import jplay.TileInfo;
import jplay.URL;
import jplay.Window;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.Point;
import java.util.Vector;

public class Jogador extends Ator {

    static double energia = 600.0;

    ControleTiros tiros = new ControleTiros();
    Controle controle = new Controle();
    Font f = new Font("arial", Font.BOLD, 10);

    public Jogador(int x, int y) {
        super(URL.sprite("jogador.png"), 20);
        this.x = x;
        this.y = y;
        this.setTotalDuration(2000);
    }

    public void atirar(Window janela, Scene cena, Keyboard teclado, Ator inimigo) {
        if (teclado.keyDown(KeyEvent.VK_A)) {
            tiros.adicionaTiro(x + 13, y - 41, direcao, cena);
        }
        tiros.run(inimigo);
    }

    public void mover(Window janela, Keyboard teclado) {
        if (teclado.keyDown(Keyboard.LEFT_KEY)) {
            if (this.x > 0) {
                this.x -= velocidade;
            }
            if (direcao != 1) {
                setSequence(4, 8);
                direcao = 1;
            }
            movendo = true;
        } else if (teclado.keyDown(Keyboard.RIGHT_KEY)) {
            if (this.x < janela.getWidth() - 60) {
                this.x += velocidade;
            }
            if (direcao != 2) {
                setSequence(8, 12);
                direcao = 2;
            }
            movendo = true;
        } else if (teclado.keyDown(Keyboard.UP_KEY)) {
            if (this.y > 0) {
                this.y -= velocidade;
            }
            if (direcao != 4) {
                setSequence(12, 16);
                direcao = 4;
            }
            movendo = true;
        } else if (teclado.keyDown(Keyboard.DOWN_KEY)) {
            if (this.y < janela.getHeight() - 60) {
                this.y += velocidade;
            }
            if (direcao != 5) {
                setSequence(0, 4);
                direcao = 5;
            }
            movendo = true;
        }
        if (movendo) {
            update();
            movendo = false;
        }
    }

    public void energia(Window janela) {
        janela.drawText("VIDA: " + Jogador.energia, 10, 10, Color.RED, f);
        if (Jogador.energia <= 0) {
            reset(janela);
        }
    }

    public void caminho(Scene cena) {
        Point min = new Point((int) this.x, (int) this.y);
        Point max = new Point((int) this.x + this.width, (int) this.y + this.height);

        Vector<?> tiles = cena.getTilesFromPosition(min, max);

        for (int i = 0; i < tiles.size(); i++) {
            TileInfo tile = (TileInfo) tiles.elementAt(i);

            if (controle.colisao(this, tile)) {
                if (colisaoVertical(this, tile)) {
                    if (tile.y + tile.height - 2 < this.y) {
                        this.y = tile.y + tile.height;
                    } else if (tile.y > this.y + this.height - 2) {
                        this.y = tile.y - this.height;
                    }
                }
                if (colisaoHorizontal(this, tile)) {
                    if (tile.x > this.x + this.width - 2) {
                        this.x = tile.x - this.width;
                    } else {
                        this.x = tile.x + tile.width;
                    }
                }
            }
        }
    }

    private boolean colisaoVertical(GameObject obj, GameObject obj2) {
        if (obj2.x + obj2.width <= obj.x) return false;
        if (obj.x + obj.width <= obj2.x) return false;
        return true;
    }

    private boolean colisaoHorizontal(GameObject obj, GameObject obj2) {
        if (obj2.y + obj2.height <= obj.y) return false;
        if (obj.y + obj.height <= obj2.y) return false;
        return true;
    }

    private void reset(Window janela) {
        // Reset player position, energy, and any other necessary attributes
        this.x = 200; // Initial x position
        this.y = 500; // Initial y position
        Jogador.energia = 500.0;
        new Gameover(janela); // Assuming Gameover is a valid class and has a constructor that takes a Window
        janela.update();
    }
}
