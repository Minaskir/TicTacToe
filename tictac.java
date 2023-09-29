import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class tictac extends JComponent {
    public static final int Field_empty = 0;
    public static final int Field_X = 10;
    public static final int Field_0 = 200;
    int[][] field;
    boolean isXturn;

    public tictac() {
        enableEvents(AWTEvent.MOUSE_EVENT_MASK);
        field = new int[3][3];
        initGame();
    }

    public void initGame() {
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                field[i][j] = Field_empty;
            }
        }
        isXturn = true;
    }

    @Override
    protected void processMouseEvent(MouseEvent mouseEvent) {
        super.processMouseEvent(mouseEvent);
        if (mouseEvent.getButton() == MouseEvent.BUTTON1) {
            int x = mouseEvent.getX();
            int y = mouseEvent.getY();
            int i = (int) ((float) x / getHeight() * 3);
            int j = (int) ((float) y / getWidth() * 3);
            if (field[i][j] == Field_empty) {
                field[i][j] = isXturn ? Field_X : Field_0;
                isXturn = !isXturn;
                repaint();
                int res = checkState();
                if(res !=0){
                    if(res == Field_0*3){
                        JOptionPane.showMessageDialog(this, "нолики выиграли!", "Победа!", JOptionPane.INFORMATION_MESSAGE);

                    }
                    else if(res == Field_X*3){
                        JOptionPane.showMessageDialog(this, "крестики выиграли!", "Победа!", JOptionPane.INFORMATION_MESSAGE);
                    }
                    else {
                        JOptionPane.showMessageDialog(this, "ничья!", "Ничья!", JOptionPane.INFORMATION_MESSAGE);
                    }
                    initGame();
                    repaint();
                }
            }
        }
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.clearRect(0, 0, getWidth(), getHeight());
        drawGrid(graphics);
        drawX0(graphics);
    }

    void drawGrid(Graphics graphics) {
        int w = getWidth();
        int h = getHeight();
        int dw = w / 3;
        int dh = h / 3;
        graphics.setColor(Color.BLACK);
        for (int i = 1; i < 3; i++) {
            graphics.drawLine(0, dh * i, w, dh * i);
            graphics.drawLine(dw * i, 0, dw * i, h);
        }
    }

    void drawX(int i, int j, Graphics graphics) {
        graphics.setColor(Color.RED);
        int dw = getWidth() / 3;
        int dh = getHeight() / 3;
        int x = i * dw;
        int y = j * dh;
        graphics.drawLine(x, y, x + dw, y + dh);
        graphics.drawLine(x, y + dh, x + dw, y);
    }

    void draw0(int i, int j, Graphics graphics) {
        graphics.setColor(Color.RED);
        int dw = getWidth() / 3;
        int dh = getHeight() / 3;
        int x = i * dw;
        int y = j * dh;
        graphics.drawOval(x + 3 * dw / 100, y, dw * 9 / 10, dh);
    }

    void drawX0(Graphics graphics) {
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                if (field[i][j] == Field_X) {
                    drawX(i, j, graphics);
                } else if (field[i][j] == Field_0) {
                    draw0(i, j, graphics);
                }
            }
        }
    }
    int checkState() {
        int diag = 0;
        int diag2=0;
        for (int i=0; i<3; i++) {
            diag += field[i][i];
            diag2 += field[i][2-i];
        }
        if (diag == Field_0 *3 || diag == Field_X *3){return diag;}
        if (diag2 == Field_0*3 || diag2 == Field_X*3){return diag2;}
        int check_i, check_j;
        boolean hasEmpty = false;
        for (int i=0; i<3; i++){
            check_i = 0;
            check_j =0;
            for(int j=0; j<3;j++) {
                if(field[i][j]==0){
                    hasEmpty = true;
                }
                check_i += field[i][j];
                check_j += field[j][i];
            }
            if(check_i == Field_0 *3 || check_i == Field_X * 3){
                return check_i;
            }
            if(check_j == Field_0*3|| check_j == Field_X *3){
                return check_j;
            }
        }
        if (hasEmpty) return 0;
        else return -1;


    }

}
