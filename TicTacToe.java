
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class TicTacToe
{  
    private JButton [][] button;
    private boolean gameEnded=false;
    private boolean isX =true;

    public void TicTacToeGame() 
    {
        JFrame frame = new JFrame();
        JLabel label = new JLabel("Tic Tac Toe");
        label.setBounds(120,50,320,80);
        label.setFont(new Font("Serif",Font.BOLD,30));
        label.setForeground(new Color(135,206,235));
        frame.add(label);
        //adds label named Start with (x or o)
        JLabel l1 = new JLabel("Your Choice ");
        l1.setBounds(120,160,140,30);
        l1.setFont(new Font("Serif",Font.BOLD,18));
        l1.setForeground(new Color(135,206,235));
        frame.add(l1);
        String choice[] = {"SELECT","X","O"};

        JComboBox<String> source = new JComboBox<>(choice);
        source.setBounds(260,160,100,30);
        source.setBackground(new Color(204, 204, 255));
        frame.add(source);
        
        JButton startbtn = new JButton("Start");
        startbtn.setBounds(195,265,80,40);
        startbtn.setBackground(new Color(0, 255, 0));
        frame.add(startbtn);
        
        JLabel errormsg = new JLabel();
        errormsg.setBounds(100,350,320,40);
        errormsg.setFont(new Font("Serif",Font.ITALIC,20));
        errormsg.setForeground(new Color(255, 0, 0));
        frame.add(errormsg);

        startbtn.addActionListener((ActionListener) new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {   
                String src = (String) source.getSelectedItem();
                if(src == "SELECT"){
                    errormsg.setText("Please Select X or O to Start the Game ");
                    errormsg.setVisible(true);
                }
                else{
                    errormsg.setVisible(false); 
                  
                    JFrame gameFrame = new JFrame();
                    gameFrame.setTitle("Tic-Tac-Toe");
                    gameFrame.setSize(300,300);
                    gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    gameFrame.setLayout(new GridLayout(3,3));
                    gameFrame.setVisible(true);
                    
                    button = new JButton[3][3];
                    for(int i=0;i<3;i++){
                        for(int j=0;j<3;j++){
                            button[i][j]=new JButton("");
                            button[i][j].setFont(new Font("Arial", Font.PLAIN,40));          
                            button[i][j].addActionListener(new ButtonClickListener(i,j));
                            gameFrame.add(button[i][j]);
                            }
                    }
                    
                    gameFrame.getRootPane().setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.CYAN)); //adds Border to App
                    gameFrame.setSize(250,250);
                    gameFrame.getContentPane();
                    gameFrame.setVisible(true);
                    }
                }
        });
        frame.getRootPane().setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.RED)); //adds Border to App
        frame.setSize(500,580);
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.WHITE);
        frame.setVisible(true);
    }   
    
    private void highlightWinningbutton(int x1, int y1, int x2, int y2, int x3, int y3) {
        
        button[x1][y1].setBackground(Color.GREEN);
        button[x2][y2].setBackground(Color.GREEN);
        button[x3][y3].setBackground(Color.GREEN);
    }
        
    private void resetGame() {

            for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                button[i][j].setText("");
                button[i][j].setBackground(null);
            }
        }
        gameEnded=false;
        isX=true;
        }
        
    public void showWinMessage(String symbol) {

        gameEnded=true;
        JOptionPane.showMessageDialog(null,"Congrats...!!\nPlayer "+ symbol + " Wins!");
        resetGame();
        }

    private void checkResult(){

        String symbol= isX ? "X":"O";

        for(int i=0; i<3; i++){

            if(button[i][0].getText().equals(symbol) && button[i][1].getText().equals(symbol)&& button[i][2].getText().equals(symbol)){
                    highlightWinningbutton(i, 0, i, 1, i, 2);
                    showWinMessage(symbol); 
                    return;
                }

                if(button[0][i].getText().equals(symbol) && button[1][i].getText().equals(symbol)&& button[2][i].getText().equals(symbol)){
                    highlightWinningbutton(0, i, 1, i, 2, i); 
                    showWinMessage(symbol); 
                    return;
                }
        }
        //Win by Diagonal
        if(button[0][0].getText().equals(symbol) && button[1][1].getText().equals(symbol) && button[2][2].getText().equals(symbol) ){
            highlightWinningbutton(0, 0, 1, 1, 2, 2); 
                    showWinMessage(symbol); 
                    return;
        }
        if(button[2][0].getText().equals(symbol) && button[1][1].getText().equals(symbol) && button[0][2].getText().equals(symbol) ){
            highlightWinningbutton(2, 0, 1, 1, 2, 0);
                    showWinMessage(symbol); 
                    return;
        }
        
        boolean isDraw =true;
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(button[i][j].getText().isEmpty()){
                    isDraw=false;
                    break;
                }
            }
        }
        if(isDraw){
           gameEnded=true;
           JOptionPane.showMessageDialog(null, "It's Draw...!\n Play Again");
           resetGame();
        }   
    }

    private class ButtonClickListener implements ActionListener {
        private int x, y;

        public ButtonClickListener(int x, int y) {
            this.x = x;
            this.y = y;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            if(!button[x][y].getText().isEmpty()|| gameEnded){
                return;
            }
            String symbol= isX ? "X" : "O";
            button[x][y].setText(symbol);
            checkResult();
            isX = !isX;
        }
        
    }
    public static void main(String[] args) {

        TicTacToe gg = new TicTacToe();
         gg.TicTacToeGame();

        
    }
}