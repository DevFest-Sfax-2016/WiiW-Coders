package com.example.gaddour.flappybirddevfest2016;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    ImageView imagebird,GameOverImage,fond,cloud;
    ImageView imagecylindre;
    ImageView imagecylindre1;
    ImageView imageground;
    int pipe,pipe1;
    boolean startGame=false;
    boolean GameOver=false;
    Handler handler,handler1;
    float y;
    float maxY;
    Runnable runnable,runnable1;
    TextView textScore;
  //  SQLDatabaseManager sqlDatabaseManager;
    int birdChange=0;
    int time;

    int score=0;
    boolean passCylindre=true;
    boolean passCylindre1=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_play);
       // sqlDatabaseManager=new SQLDatabaseManager(this);
        imagebird = (ImageView) findViewById(R.id.imageView);
        imagecylindre= (ImageView) findViewById(R.id.imageView5);
        imagecylindre1= (ImageView) findViewById(R.id.imageView6);
        imageground = (ImageView) findViewById(R.id.imageView3);
        imageground.setY(imageground.getY()+20);
        GameOverImage=(ImageView) findViewById(R.id.imageView7);
        imagecylindre.setY(imagecylindre.getY()+40);
        imagecylindre1.setY(imagecylindre1.getY()+40);
        imagecylindre.setX(2000);
        imagecylindre1.setX(3000);
        handler1 = new Handler();
        handler =new Handler();
        fond= (ImageView) findViewById(R.id.imageView2);
        fond.setY(-50);
        cloud= (ImageView) findViewById(R.id.imageView8);
        textScore= (TextView) findViewById(R.id.textView);
        textScore.setText(String.valueOf(score));
        cloud.setY(-320);
        cloud.setX(0);
        GameOverImage.setY(-4000);
        initCylindre();

        // textScore.setText(sqlDatabaseManager.getScore());


    }








    void changeBird(){
        if(birdChange==0 && time>=80)
        {
            imagebird.setImageResource(R.drawable.bird3);
            birdChange=1;
            time =0;
        }
        else if(birdChange==1 && time>=80){
            imagebird.setImageResource(R.drawable.bird2);
            birdChange=2;
            time =0;
        }
        else if(birdChange==2 && time>=80){
            imagebird.setImageResource(R.drawable.bird3);
            birdChange=3;
            time =0;
        }
        else if(birdChange==3 && time>=80){
            imagebird.setImageResource(R.drawable.bird);
            birdChange=0;
            time =0;
        }



    }






    public void initCylindre()
    {
        pipe1 = (int) (Math.random()*5);

        switch (pipe1){
            case 0: imagecylindre1.setImageResource(R.drawable.pipe1);
                break;
            case 1:  imagecylindre1.setImageResource(R.drawable.pipe2);
                break;
            case 2:  imagecylindre1.setImageResource(R.drawable.pipe3);
                break;
            case 3:  imagecylindre1.setImageResource(R.drawable.pipe4);
                break;
            case 4:  imagecylindre1.setImageResource(R.drawable.pipe5);
                break;
            default: imagecylindre1.setImageResource(R.drawable.pipe5);
                break;
        }
        pipe = (int) (Math.random()*5);
        switch (pipe){
            case 0: imagecylindre.setImageResource(R.drawable.pipe1);
                break;
            case 1:  imagecylindre.setImageResource(R.drawable.pipe2);
                break;
            case 2:  imagecylindre.setImageResource(R.drawable.pipe3);
                break;
            case 3:  imagecylindre.setImageResource(R.drawable.pipe4);
                break;
            case 4:  imagecylindre.setImageResource(R.drawable.pipe5);
                break;
            default: imagecylindre.setImageResource(R.drawable.pipe5);
                break;
        }




    }














    public void retry(View view){

        if(GameOver)
        {

            GameOver=false;
            startGame=false;
            GameOverImage.setY(-4000);
            imagebird.setImageResource(R.drawable.bird);
            imagebird.setY(1000);
            imagecylindre.setX(2000);
            imagecylindre1.setX(3000);
            passCylindre=true;
            passCylindre1=true;
            score=0;
            textScore.setText(String.valueOf(score));
            initCylindre();


        }
    }


















    public void jump(View view)
    {


        if(!startGame)
        {

            startGame=true;
            animer();
        }


        maxY = imagebird.getY() - 165;
        y = imagebird.getY();

        runnable1 = new Runnable() {


            @Override
            public void run() {

                if (!GameOver && startGame) {
                    if (imagebird.getY() > maxY) {

                        time+=10;
                        changeBird();


                        if((y-imagebird.getY())<=70){
                            imagebird.setY(imagebird.getY() - 33);
                        }
                        else  if((y-imagebird.getY())<=100){
                            imagebird.setY(imagebird.getY() - 27);}
                        else  if((y-imagebird.getY())<=125){
                            imagebird.setY(imagebird.getY() - 25);}
                        else  if((y-imagebird.getY())<=143){
                            imagebird.setY(imagebird.getY() - 20);}
                        else
                            imagebird.setY(imagebird.getY() - 18);
                        handler1.postDelayed(this, 5);}




                }




            }};
        handler1.postDelayed(runnable1,5 );





//fall();

    }



    void testGameOver()
    {


        if (imagebird.getY()+140>=imageground.getY() )
        {


            GameOver=true;





        }
        else if((imagecylindre.getX()<=imagebird.getX()+130)&&(imagecylindre.getX()+320>=imagebird.getX()))
        {
            if (pipe==0) {
                if(imagebird.getY()<=780 || imagebird.getY()>=1060){


                    GameOver=true;


                }
            }
            else if(pipe==1){
                if(imagebird.getY()<=1260 || imagebird.getY()>=1525){


                    GameOver=true;



                }}
            else if(pipe==2){
                if(imagebird.getY()<=955 || imagebird.getY()>=1275){


                    GameOver=true;



                }}  else if(pipe==3){
                if(imagebird.getY()<=325 || imagebird.getY()>=610){


                    GameOver=true;



                }}
            else if(pipe==4 || pipe==5){
                if(imagebird.getY()<=675 || imagebird.getY()>=960){



                    GameOver=true;




                }}

        }
        else if((imagecylindre1.getX()<=imagebird.getX()+130)&&(imagecylindre1.getX()+320>=imagebird.getX()))
        {
            if (pipe1 == 0) {
                if(imagebird.getY()<=780 || imagebird.getY()>=1060) {

                    GameOver=true;




                }
            }
            else if(pipe1==1){
                if(imagebird.getY()<=1260 || imagebird.getY()>=1525){


                    GameOver=true;



                }}
            else if(pipe1==2){
                if(imagebird.getY()<=955 || imagebird.getY()>=1275){


                    GameOver=true;


                }}
            else if(pipe1==3){
                if(imagebird.getY()<=325 || imagebird.getY()>=610){



                    GameOver=true;


                }}
            else if(pipe1==4 || pipe1==5) {
                if(imagebird.getY()<=675 || imagebird.getY()>=960){



                    GameOver=true;


                }}
        }
        else  if(((imagecylindre.getX()+320)<=imagebird.getX())  && passCylindre){
            score++;
            textScore.setText(String.valueOf(score));
            passCylindre=false;
        }
        else  if(((imagecylindre1.getX()+320)<=imagebird.getX()) && passCylindre1){
            score++;
            textScore.setText(String.valueOf(score));
            passCylindre1=false;
        }
    }











    public void moveCylindre(){

        if (imagecylindre1.getX()<-500){
            imagecylindre1.setX(1500);
            passCylindre1=true;
            pipe1 = (int) (Math.random()*5);
            switch (pipe1){
                case 0: imagecylindre1.setImageResource(R.drawable.pipe1);
                    break;
                case 1:  imagecylindre1.setImageResource(R.drawable.pipe2);
                    break;
                case 2:  imagecylindre1.setImageResource(R.drawable.pipe3);
                    break;
                case 3:  imagecylindre1.setImageResource(R.drawable.pipe4);
                    break;
                case 4:  imagecylindre1.setImageResource(R.drawable.pipe5);
                    break;
                default: imagecylindre1.setImageResource(R.drawable.pipe5);
                    break;
            }}


        if (imagecylindre.getX()<-500)
        {
            imagecylindre.setX(1500);
            passCylindre=true;
            pipe = (int) (Math.random()*5);

            switch (pipe){
                case 0: imagecylindre.setImageResource(R.drawable.pipe1);
                    break;
                case 1:  imagecylindre.setImageResource(R.drawable.pipe2);
                    break;
                case 2:  imagecylindre.setImageResource(R.drawable.pipe3);
                    break;
                case 3:  imagecylindre.setImageResource(R.drawable.pipe4);
                    break;
                case 4:  imagecylindre.setImageResource(R.drawable.pipe5);
                    break;
                default: imagecylindre.setImageResource(R.drawable.pipe5);
                    break;
            }

        }

        else {
            imagecylindre.setX(imagecylindre.getX() - 8);
            imagecylindre1.setX(imagecylindre1.getX()-8);
        }
    }




    public void animer()
    {


        runnable =new Runnable() {
            @Override
            public void run() {







                if(!GameOver){
                    testGameOver();
                    moveCylindre();
                    time+=10;
                    changeBird();
                }
                else
                {
                    imagebird.setImageResource(R.drawable.deadbird);
                }








                if (imagebird.getY()+120<=imageground.getY()) {



                    if((imagebird.getY()-maxY)<20){
                        imagebird.setY(imagebird.getY() +4);}

                    else   if((imagebird.getY()-maxY)<50){
                        imagebird.setY(imagebird.getY() +9);}
                    else if((imagebird.getY()-maxY)<=100){
                        imagebird.setY(imagebird.getY() + 10);}
                    else  if((imagebird.getY()-maxY)<=150){
                        imagebird.setY(imagebird.getY() + 11);}
                    else  if((imagebird.getY()-maxY)<=200){
                        imagebird.setY(imagebird.getY() + 12);}
                    else  if((imagebird.getY()-maxY)<=250){
                        imagebird.setY(imagebird.getY() + 14);}
                    else  if((imagebird.getY()-maxY)<=300){
                        imagebird.setY(imagebird.getY() + 16);}
                    else  if((imagebird.getY()-maxY)<=350){
                        imagebird.setY(imagebird.getY() + 17);}
                    else  if((imagebird.getY()-maxY)<=400){
                        imagebird.setY(imagebird.getY() + 18);}
                    else  if((imagebird.getY()-maxY)<=450){
                        imagebird.setY(imagebird.getY() + 19);}
                    else  if((imagebird.getY()-maxY)<=500){
                        imagebird.setY(imagebird.getY() + 20);}
                    else  if((imagebird.getY()-maxY)<=550){
                        imagebird.setY(imagebird.getY() + 21);}
                    else  if((imagebird.getY()-maxY)<=600){
                        imagebird.setY(imagebird.getY() + 22);}
                    else  if((imagebird.getY()-maxY)<=650){
                        imagebird.setY(imagebird.getY() + 23);}
                    else {
                        imagebird.setY(imagebird.getY() + 24);}




                    handler.postDelayed(this,10);}
                else{
                    GameOverImage.setY(1000);
                  //  int x= (int)Integer.valueOf(sqlDatabaseManager.getScore());
                  //  if(x<score){
                   //     sqlDatabaseManager.updateScore(score);

                    }
                  //  textScore.setText("Best Score is : "+ sqlDatabaseManager.getScore());

                }



        };

//
        handler.postDelayed(runnable,20);

    }

}
