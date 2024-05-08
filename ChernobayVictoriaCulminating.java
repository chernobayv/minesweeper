/*
Victoria Chernobay - Culminating, ICS3U1

Minesweeper Game - All controls are using mouse (right click, left click), This game is a minesweeper style game with a 6x6 grid and 6 mines, 
flag mines using the right click to remind yourself of mines. Once you have flagged all suspected mines, make sure to open the other squares. 
Use the left click to open squares.

*/

import java.util.*;
import java.awt.*;

import hsa_ufa.*;

public class ChernobayVictoriaCulminating extends Console {

ChernobayVictoriaCulminating() { 
      //create console
      super(700,500,"Minesweeper - Victoria Chernobay");

      //find max values
      int maxx = getDrawWidth();
      int maxy = getDrawHeight();
      
      //draw welcome image, last for 3 seconds and clear
      Image welcomeImage = loadImage("welcomeScreen.png");
      drawImage(welcomeImage, 0, 0, maxx, maxy);
      delay(3000);
      clear();
      
      //create menu
      mainMenu(this);
}
   
   
   public void mainMenu(Console c){//This method creates the main menu of the game, here you can quit, play, and view instructions
      //create background color for main menu
      Color backgroundColor = new Color(205, 204, 204);
      c.setBackgroundColor(backgroundColor);
      c.clear();
      
      //draw title for main menu
      c.setColor(Color.black);
      Font arcadeFont = new Font ("monospaced", Font.BOLD, 50);
      c.setFont(arcadeFont);
      c.drawString("MINESWEEPER", 170, 100);
      
      //draw play, instructions & quit buttons
      c.setColor(Color.red);
      //play
      c.fillRect(70, 140, 340, 80);
      //instructions
      c.fillRect(70, 240, 340, 80);
      //quit
      c.fillRect(70, 340, 340, 80);
   
      //button text
      Font arcadeFontSmall = new Font ("monospaced", Font.BOLD, 35);
      c.setFont(arcadeFontSmall);
      c.setColor(Color.white);
      c.drawString("START GAME", 130, 190);
      c.drawString("INSTRUCTIONS", 110, 290);
      c.drawString("QUIT", 190, 390);
   
      //draw smile face
      Image smileButton = loadImage("smileButton.jpg");
      c.drawImage(smileButton, 500, 230, 100, 100);
      
      boolean mainMenuClicked = false;
      
      //if buttons clicked
      while(mainMenuClicked == false){
            //create mouse variables
            int mouseClickedX = c.getMouseButtonPressedX();
            int mouseClickedY = c.getMouseButtonPressedY();
      
            //play button
            if ((mouseClickedX>=70 && mouseClickedX<=410)&&(mouseClickedY>=140 && mouseClickedY<=220)){
               drawMinesweeperGame(c);
               mainMenuClicked = true;
            }    
            //instruction menu
            else if ((mouseClickedX>=70 && mouseClickedX<=410)&&(mouseClickedY>=240 && mouseClickedY<=320)){
               instructionMenu(c, mainMenuClicked);
               mainMenuClicked = true;
            }
            //quit button
            else if ((mouseClickedX>=70 && mouseClickedX<=410)&&(mouseClickedY>=340 && mouseClickedY<=420)){
               quit(c);
               mainMenuClicked = true;
            }
      }
   }

   public void instructionMenu(Console c, boolean mainMenuClicked){//this is the instruction menu, here you can read instructions and go back to the main menu
      //create background color for instruction menu
      Color backgroundColor = new Color(205, 204, 204);
      c.setBackgroundColor(backgroundColor);
      c.clear();
            
      //draw instruction titles
      c.setColor(Color.red);
      c.fillRect(100, 50, 500, 125);
      
      //home button
      c.fillRect(450, 380, 140, 50);

   
      //title text
      Font arcadeFont = new Font ("monospaced", Font.BOLD, 50);
      c.setFont(arcadeFont);
      c.setColor(Color.white);
      c.drawString("INSTRUCTIONS", 170, 125);
      
      //home button
      Font arcadeFontHome = new Font ("monospaced", Font.BOLD, 20);
      c.setFont(arcadeFontHome);
      c.setColor(Color.white);
      c.drawString("MAIN MENU", 465, 412);
      
      //instruction text
      Font arcadeFontSmall = new Font ("monospaced", Font.BOLD, 10);
      c.setFont(arcadeFontSmall);
      c.setColor(Color.black);
      c.drawString("In this game you will attempt to ", 100, 200);
      c.drawString("uncover all the squares with no hidden mines. When", 100, 220);
      c.drawString("you click on a square In the 6x6 grid, you", 100, 240);
      c.drawString("will see a number, this number Will tell", 100, 260);
      c.drawString("you how many mines are dIrectly adjacent", 100, 280);
      c.drawString("to it, straight or diagonally. You must try ", 100, 300);
      c.drawString("to uncover all squares wIthout mines. If ", 100, 320);
      c.drawString("you achieve that successfully you will ", 100, 340);
      c.drawString("win the game. Otherwise, If the player", 100, 360);
      c.drawString("uncovers a mine, they lose the game. In", 100, 380);
      c.drawString("order to keep track of suspected mines,", 100, 400);
      c.drawString("the player can place a flag on a square", 100, 420);
      c.drawString("with the spacebar.", 100, 440);
      
      //draw mouse photo
      Image mouse = loadImage("mousePhoto.png");
      c.drawImage(mouse, 450, 224, 100, 100);
      
      boolean instructionMenuClicked = false;

     
      while(instructionMenuClicked == false){
            //create mouse variables
            int mouseClickedX = c.getMouseButtonPressedX();
            int mouseClickedY = c.getMouseButtonPressedY();
      
            //home button
            if ((mouseClickedX>=450 && mouseClickedX<=590)&&(mouseClickedY>=380 && mouseClickedY<=430)){
               mainMenu(c);
               instructionMenuClicked = true;
               mainMenuClicked = false;
            }
      }

   }
   
   public void quit(Console c){//if you press the quit button, it will close the console
      //close console
      c.close();
   }
   
   public void drawMinesweeperGame(Console c){//In this method it draws all of the aspects of the game and will call other helper methods 
      //create background color for minesweeper game
      Color backgroundColor = new Color(205, 204, 204);
      c.setBackgroundColor(backgroundColor);
      c.clear();
      
      //draw background
      Image welcomeImage = loadImage("minesweeperBackground.png");
      c.drawImage(welcomeImage, 0, 0, c.getDrawWidth(), c.getDrawHeight());
      
      //draw squares on background
      Image squares = loadImage("Square.jpg");
      int squareSize=55;
      int squarex=178-squareSize;
      int squarey=108-squareSize;
      for (int i = 0; i<6; i++){//draw vertical
         squarey+=squareSize;
         squarex=178-squareSize;//181
         for (int x = 0;  x<6; x++){//draw horizontal
            squarex+=squareSize;
            c.drawImage(squares, squarex, squarey, squareSize, squareSize);
         }
      }
      
      int blockValues [] = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
      int [] chosenNums = new int[36];//create 6 chosen numbers


      //find 6 unique mine coordinates
      for (int i = 0; i <6; i++) {//run 6 times

         boolean uniqueNum = false;//if a unique number is found
         int randomNum = 0;//random num is currently 0

         while (!uniqueNum) {//if unique num is false
            randomNum = (int) ((Math.random() * 36) + 1); // generate random number between 1 and 6 to find only unique numbers

            if (chosenNums[randomNum - 1] == 0) {//because 36 numbers are chosen, if the number is not chosen, its spot will be labeled as 0 if not it will  be labeled as 1
               chosenNums[randomNum - 1] = 1; // show number as chosen
               uniqueNum = true; // exit loop if found
               blockValues[randomNum]--; //assign num
            }
         }
         
      }
      
      for (int i = 1; i<36; i++){//assign values for the number of adjacent mines for each square
         if (blockValues[i] == -1){

            if (i==1){
               if (blockValues[i+1]!=-1){
                  blockValues[i+1]++;
               }
               if (blockValues[i+7]!=-1){
                  blockValues[i+7]++;
               }
               if (blockValues[i+6]!=-1){
                  blockValues[i+6]++;
               }
            }
            if (i>=2 && i<=5){
               if (blockValues[i-1]!=-1){
                  blockValues[i-1]++;
               }
               if (blockValues[i+1]!=-1){
                  blockValues[i+1]++;
               }
               if (blockValues[i+5]!=-1){
                  blockValues[i+5]++;
               }
               if (blockValues[i+6]!=-1){
                  blockValues[i+6]++;
               }
               if (blockValues[i+7]!=-1){
                  blockValues[i+7]++;
               }
            }
            if (i==7 || i==13 || i==19 || i==25){
               if (blockValues[i-6]!=-1){
                  blockValues[i-6]++;
               }
               if (blockValues[i-5]!=-1){
                  blockValues[i-5]++;
               }
               if (blockValues[i+1]!=-1){
                  blockValues[i+1]++;
               }
               if (blockValues[i+6]!=-1){
                  blockValues[i+6]++;
               }
               if (blockValues[i+7]!=-1){
                  blockValues[i+7]++;
               }
            }
            if ((i>=8 && i<=11)|| (i>=14 && i<=17) || (i>=20 && i<=23)|| (i>=26 && i<=29)){
               if (blockValues[i-7]!=-1){
                  blockValues[i-7]++;
               }
               if (blockValues[i-6]!=-1){
                  blockValues[i-6]++;
               }
               if (blockValues[i-5]!=-1){
                  blockValues[i-5]++;
               }
               if (blockValues[i-1]!=-1){
                  blockValues[i-1]++;
               }
               if (blockValues[i+1]!=-1){
                  blockValues[i+1]++;
               }
               if (blockValues[i+5]!=-1){
                  blockValues[i+5]++;
               }
               if (blockValues[i+6]!=-1){
                  blockValues[i+6]++;
               }
               if (blockValues[i+7]!=-1){
                  blockValues[i+7]++;
               }
            }

            if (i==6){
               if (blockValues[i-1]!=-1){
                  blockValues[i-1]++;
               }
               if (blockValues[i+5]!=-1){
                  blockValues[i+5]++;
               }
               if (blockValues[i+6]!=-1){
                  blockValues[i+6]++;
               }
            }
            if (i==12 || i==18 || i==24 || i==30){
               if (blockValues[i-1]!=-1){
                  blockValues[i-1]++;
               }
               if (blockValues[i-6]!=-1){
                  blockValues[i-6]++;
               }
               if (blockValues[i-7]!=-1){
                  blockValues[i-7]++;
               }
               if (blockValues[i+5]!=-1){
                  blockValues[i+5]++;
               }
               if (blockValues[i+6]!=-1){
                  blockValues[i+6]++;
               }
            }
            if (i==31){
               if (blockValues[i+1]!=-1){
                  blockValues[i+1]++;
               }
               if (blockValues[i-5]!=-1){
                  blockValues[i-5]++;
               }
               if (blockValues[i-6]!=-1){
                  blockValues[i-6]++;
               }
            }
            
            if (i>=32 && i<=35){
               if (blockValues[i-1]!=-1){
                  blockValues[i-1]++;
               }
               if (blockValues[i+1]!=-1){
                  blockValues[i+1]++;
               }
               if (blockValues[i-5]!=-1){
                  blockValues[i-5]++;
               }
               if (blockValues[i-6]!=-1){
                  blockValues[i-6]++;
               }
               if (blockValues[i-7]!=-1){
                  blockValues[i-7]++;
               }
            }
            
            if (i==36){
               if (blockValues[i-1]!=-1){
                  blockValues[i-1]++;
               }
               if (blockValues[i-7]!=-1){
                  blockValues[i-7]++;
               }
               if (blockValues[i-6]!=-1){
                  blockValues[i-6]++;
               }
            }


      }
      }
      
      //assign the block as a mine block
      //0 = blank block
      //-1 = mine
      //1,2,3,4,5,6,7,8 = blocks with adgacent and how many
      //-2 = flag block
            
      //draw smileface
      Image smileFace = loadImage("smileButton.jpg");
      c.drawImage(smileFace, 327, 60, 30, 30);
      
      //draw home button
      Image homeButton = loadImage("homeButton.png");
      c.drawImage(homeButton, 60, 77, 50, 50);
      
      playMineSweeper(c, blockValues);
            
   }
      
   public void playMineSweeper(Console c, int[] blockValues){ // this is the main method where the game is played, it watches the mouse clicks, and calls appropriate methods, there is also a home button that leads to the main menu
   
      Image empty = loadImage("emptySquare.png");
      Image mine = loadImage("mineClicked.png");
      Image flag = loadImage("squareWithFlag.jpg");
      Image squares = loadImage("Square.jpg");
      
      int blockClicked [] = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};      
      
      boolean isGameOver=false;
      boolean isGameWon=false;
      int flagsPlaced = 0;
       
      while(true){
            //set variables for pressed blocks
            int squareSize=55;
            int squarex=178-squareSize;
            int squarey=108-squareSize;
            //create mouse variables
            int mouseClickedX = c.getMouseButtonPressedX();
            int mouseClickedY = c.getMouseButtonPressedY();
            
            //find button on mouse that was clicked
            //1 - Left
            //2 - right
            int mouseButtonClicked = c.getMouseButton();

            
            //find row and column that was clicked
            int rowClicked = 0;
            int columnClicked = 0;
            int blockNum = 0;
                                  
            for (int i = 0; i<6; i++){//find vertical
                  squarey+=squareSize;
                  squarex=178-squareSize;
                  rowClicked++;
                  columnClicked=0;
               for (int x = 0;  x<6; x++){//find horizontal
                  squarex+=squareSize;
                  columnClicked++;
                  blockNum = ((rowClicked-1)*6)+columnClicked;
                  //if the vertical and horizontal is clicked, replace square with blank square
                  if ((mouseClickedX>=(squarex+1) && mouseClickedX<=(squarex+squareSize-1))&&(mouseClickedY>=(squarey+1) && mouseClickedY<=(squarey+squareSize-1))  && blockClicked[blockNum]!=1){
                     
                                          
                     if (blockValues[blockNum] >= 0 && mouseButtonClicked ==1 && blockClicked[blockNum]!=-2 && isGameOver==false){//if the block clicked has nothing
                        checkValues(c, blockValues, blockNum, squarex, squarey, squareSize);
                        nearbyBlocks(c, blockValues,blockNum,rowClicked,columnClicked, empty, blockClicked, flagsPlaced);
                        blockClicked[blockNum]=1; 

                     }
                     else if (blockValues[blockNum] == -1 && mouseButtonClicked ==1 && blockClicked[blockNum]!=-2 && isGameOver==false){//if the block clicked is a mine
                        c.drawImage(mine, squarex, squarey, squareSize, squareSize); 
                        blockClicked[blockNum]=1; 
                        gameOver(c, blockClicked, blockValues, blockNum, isGameWon);
                        isGameOver = true;
                        break;  
                                     
                     }
                     else if (mouseButtonClicked ==2 && blockClicked[blockNum]!=1 && isGameOver==false){//draw flag if square is not clicked
                       if (blockClicked[blockNum] != -2 && flagsPlaced <=6){
                           c.drawImage(flag, squarex, squarey, squareSize, squareSize);
                           blockClicked[blockNum] = -2; // flag block
                           delay(500);
                           flagsPlaced++;
                       }
                       else{
                           c.drawImage(squares, squarex, squarey, squareSize, squareSize);
                           delay(500);
                           if (blockClicked[blockNum]==-2){
                              flagsPlaced--;
                              blockClicked[blockNum] = 0;
                           }
                       }
                        
                     }
                     
                  }
                  
                  
               if (isGameOver) break; 
            }
            int clickCount = 0;
            for (int y = 1; y<37; y++){//check which squares are unclicked
               if (blockClicked[y]==1){
                  clickCount++;
                  
               }

            }
            
            if (clickCount==30){//When 30 squares are unclicked, this means that there are 6 squares remaining, these must be the remaining mines
               isGameWon = true;
               gameOver(c, blockClicked, blockValues, blockNum, isGameWon);
               isGameOver = true;
               
            }
                        
            //when smile face clicked, reset playing field
            if ((mouseClickedX>=327 && mouseClickedX<=357)&&(mouseClickedY>=60 && mouseClickedY<=90)){
               drawMinesweeperGame(c);
            }
            
            if ((mouseClickedX>=60 && mouseClickedX<=110)&&(mouseClickedY>=77 && mouseClickedY<=127)){
               mainMenu(c);
            }
         }
      }

   }
   
   private void checkValues (Console c, int [] blockValues, int blockNum, int squarex, int squarey, int squareSize){//checks the values of the numbber of adjecaent mines, shows number conforming to number listed
      
      Image empty = loadImage("emptySquare.png");
      Image num1 = loadImage("numOne.png");
      Image num2 = loadImage("numTwo.png");
      Image num3 = loadImage("numThree.png");
      Image num4 = loadImage("numFour.png");
      Image num5 = loadImage("numFive.png");
      Image num6 = loadImage("numSix.png");
      Image num7 = loadImage("numSeven.png");
      Image num8 = loadImage("numEight.png");
      
      if (blockValues[blockNum] == 0){
         c.drawImage(empty, squarex, squarey, squareSize, squareSize);
      }
      if (blockValues[blockNum] == 1){
         c.drawImage(num1, squarex, squarey, squareSize, squareSize);
      }
      if (blockValues[blockNum] == 2){
         c.drawImage(num2, squarex, squarey, squareSize, squareSize);
      }
      if (blockValues[blockNum] == 3){
         c.drawImage(num3, squarex, squarey, squareSize, squareSize);
      }
      if (blockValues[blockNum] == 4){
         c.drawImage(num4, squarex, squarey, squareSize, squareSize);
      }
      if (blockValues[blockNum] == 5){
         c.drawImage(num5, squarex, squarey, squareSize, squareSize);
      }
      if (blockValues[blockNum] == 6){
         c.drawImage(num6, squarex, squarey, squareSize, squareSize);
      }
      if (blockValues[blockNum] == 7){
         c.drawImage(num7, squarex, squarey, squareSize, squareSize);
      }
      if (blockValues[blockNum] == 8){
         c.drawImage(num8, squarex, squarey, squareSize, squareSize);
      }
      
   }
  
   /**
   * When game is over, display all unclicked mines, and game over message, depending on if the player lost or won the game,
   **/
   private void gameOver (Console c, int [] blockClicked, int [] blockValues, int blockNum, boolean isGameWon){
      //load images
      Image mine = loadImage("mine.png");
          
          
      for (int rows = 0; rows<6; rows++){//find row number
         for (int columns = 1; columns<=6; columns++) {//find column number
            if (blockClicked[((rows)*6)+columns] != 1) {//if the block is not clicked
               if (blockValues[((rows)*6)+columns]==-1) {//if it is a mine
                  c.drawImage(mine, (columns*55)+123, (rows*55)+108, 55, 55);  //show the mine
               }
            }
         }
      }   
      
      delay(1500);//delay for user to see where the mines are
      
         //draw pop-up
         c.setColor(Color.white);
         c.fillRect(85, 155, 530, 214);
         c.setColor(Color.gray);
         c.fillRect(85, 155, 530, 44);
         c.setColor(Color.red);
         c.fillRect(288, 290, 140, 40);
         c.fillRect(453, 290, 140, 40);
         c.setColor(Color.black);
         c.drawRect(85, 155, 530, 214);
         c.drawRect(85, 155, 530, 44);
         c.drawRect(288, 290, 140, 40);
         c.drawRect(453, 290, 140, 40);
         
      Image youWin = loadImage("youWin.jpg");//load photos
      Image youLose = loadImage("youLose.jpg");
      Font arcadeFontSmall = new Font ("monospaced", Font.BOLD, 20);//load fonts
      c.setFont(arcadeFontSmall);
      
      c.drawString("RETRY", 330, 315);//draw text
      c.drawString("MAIN MENU", 470, 315);
      
         
         
      if (isGameWon == false) {//if they lost show winnning image and say that they lost
         c.drawImage(youLose, 130, 230, 100, 100);
         Font arcadeFont = new Font ("monospaced", Font.BOLD, 30);
         c.setFont(arcadeFont);
         c.drawString("You Lose :(", 350, 250);
      }
      if (isGameWon == true) {//show winnning image and tell user they won
         c.drawImage(youWin, 130, 230, 100, 100);
         Font arcadeFont = new Font ("monospaced", Font.BOLD, 30);
         c.setFont(arcadeFont);
         c.drawString("You WIN! :)", 350, 250);
      }
      
      while(true){//if buttons are pressed to return to menu or retry
         int mouseClickedX = c.getMouseButtonPressedX();
         int mouseClickedY = c.getMouseButtonPressedY();
         
         if (mouseClickedX>=288 && mouseClickedX<=428 && mouseClickedY>= 290 && mouseClickedY<=330){
            drawMinesweeperGame(c);
         }
         if (mouseClickedX>=453 && mouseClickedX<=743 && mouseClickedY>= 290 && mouseClickedY<=330){
            mainMenu(c);
         }
      }
      
   }

      
   private int nearbyBlocks (Console c, int [] blockValues, int blockNum, int rowClicked, int columnClicked, Image mine, int [] blockClicked, int flagsPlaced){//this method will check if any nearby blocks (up, down, left, right) to the one clicked, have mines. If they do not, they will be revealed to show an empty square or blocks
      
      //check nearby blocks, only the ones above, below, beside, not diagonal!!
      //row = y, column = x
      //C
      if(rowClicked == 1 && columnClicked == 1){
         if (blockValues[blockNum+6]>=0){//below
            blockClicked[blockNum+6]=1;   
            checkValues (c, blockValues, blockNum+6, ((columnClicked+2)*55)+12, ((rowClicked+1)*55)+53, 55);
               
         }
         if (blockValues[blockNum+1]>=0){//beside - right  
            checkValues (c, blockValues, blockNum+1, ((columnClicked+3)*55)+12, ((rowClicked)*55)+53, 55);
            blockClicked[blockNum+1]=1;                  
         }
      }
      //D
      else if(rowClicked == 1 && columnClicked == 6){
         if (blockValues[blockNum+6]>=0){//below
            checkValues (c, blockValues, blockNum+6, ((columnClicked+2)*55)+12, ((rowClicked+1)*55)+53, 55);
            blockClicked[blockNum+6]=1;                    
         }
        if (blockValues[blockNum-1]>=0){//beside - left
            checkValues (c, blockValues, blockNum-1, ((columnClicked+1)*55)+12, ((rowClicked)*55)+53, 55);
               blockClicked[blockNum-1]=1;                     
        }
      }
      //E
      else if(rowClicked == 6 && columnClicked == 1){
         if (blockValues[blockNum-6]>=0){//above
            checkValues (c, blockValues, blockNum-6, ((columnClicked+2)*55)+12, ((rowClicked-1)*55)+53, 55);
            blockClicked[blockNum-6]=1;                     
         }
         if (blockValues[blockNum+1]>=0){//beside - right  
            checkValues (c, blockValues, blockNum+1, ((columnClicked+3)*55)+12, ((rowClicked)*55)+53, 55);
            blockClicked[blockNum+1]=1;                    
         }
      }
      //F
      else if(rowClicked == 6 && columnClicked == 6){
         if (blockValues[blockNum-6]>=0){//above
            checkValues (c, blockValues, blockNum-6, ((columnClicked+2)*55)+12, ((rowClicked-1)*55)+53, 55);
            blockClicked[blockNum-6]=1;                               
         }
         if (blockValues[blockNum-1]>=0){//beside - left  
            checkValues (c, blockValues, blockNum-1, ((columnClicked+1)*55)+12, ((rowClicked)*55)+53, 55);
            blockClicked[blockNum-1]=1;                     
         }
      }
      //a
      else if ((columnClicked==1||columnClicked==6)){//column clicked 
         if (blockValues[blockNum-6]>=0){//above
            checkValues (c, blockValues, blockNum-6, ((columnClicked+2)*55)+12, ((rowClicked-1)*55)+53, 55);
            blockClicked[blockNum-6]=1;                     
         }
         if (blockValues[blockNum+6]>=0){//below
            checkValues (c, blockValues, blockNum+6, ((columnClicked+2)*55)+12, ((rowClicked+1)*55)+53, 55);
            blockClicked[blockNum+6]=1;                               
         }
         if (columnClicked == 6){
            if (blockValues[blockNum-1]>=0){//beside - left  
            checkValues (c, blockValues, blockNum-1, ((columnClicked+1)*55)+12, ((rowClicked)*55)+53, 55);
               blockClicked[blockNum-1]=1;                     
            }
         }
         else{
            if (blockValues[blockNum+1]>=0){//beside - right
               checkValues (c, blockValues, blockNum+1, ((columnClicked+3)*55)+12, ((rowClicked)*55)+53, 55);
               blockClicked[blockNum+1]=1;                     
            }
         }
      }
      
      //b
      else if ((rowClicked==1||rowClicked==6)){
         if (blockValues[blockNum-1]>=0){//beside - left  
            checkValues (c, blockValues, blockNum-1, ((columnClicked+1)*55)+12, ((rowClicked)*55)+53, 55);
            blockClicked[blockNum-1]=1;                     
         }
         if (blockValues[blockNum+1]>=0){//beside - right
            checkValues (c, blockValues, blockNum+1, ((columnClicked+3)*55)+12, ((rowClicked)*55)+53, 55);
            blockClicked[blockNum+1]=1;                     
         }
         if (rowClicked ==1){
            if (blockValues[blockNum+6]>=0){//below
               checkValues (c, blockValues, blockNum+6, ((columnClicked+2)*55)+12, ((rowClicked+1)*55)+53, 55);
               blockClicked[blockNum+6]=1;                             
            }
         }
         if (rowClicked ==6){
            if (blockValues[blockNum-6]>=0){//above
               checkValues (c, blockValues, blockNum-6, ((columnClicked+2)*55)+12, ((rowClicked-1)*55)+53, 55);
               blockClicked[blockNum-6]=1;                             
            }
         }

      }
      
      //c
      else{
         if (blockValues[blockNum-1]>=0){//beside - left  
            checkValues (c, blockValues, blockNum-1, ((columnClicked+1)*55)+12, ((rowClicked)*55)+53, 55);
            blockClicked[blockNum-1]=1;                
         }
         if (blockValues[blockNum-6]>=0){//above
            checkValues (c, blockValues, blockNum-6, ((columnClicked+2)*55)+12, ((rowClicked-1)*55)+53, 55);
            blockClicked[blockNum-6]=1;                  
         }
         if (blockValues[blockNum+1]>=0){//beside - right
            checkValues (c, blockValues, blockNum+1, ((columnClicked+3)*55)+12, ((rowClicked)*55)+53, 55);
            blockClicked[blockNum+1]=1;                   
         }
         if (blockValues[blockNum+6]>=0){//below
            checkValues (c, blockValues, blockNum+6, ((columnClicked+2)*55)+12, ((rowClicked+1)*55)+53, 55);
            blockClicked[blockNum+6]=1;                              
         }

      }
      return flagsPlaced;
   }
  
   private Image loadImage(String path){//load image
      Image img = null; 
      try{
         img = javax.imageio.ImageIO.read(new java.io.File(path)); 
      } 
      catch (Exception e) 
      {
         e.printStackTrace();
      }
      return img;
   }
      
   public void delay(int z){//create delay
      try
      {
         Thread.sleep(z);
      }
      catch (Exception e) {}
   }
   

   
      
   public static void main(String[] args) {
      //create console
      new ChernobayVictoriaCulminating();
      
   }


}