package tk.dalpiazsolutions.promichallenge;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {

    MainController mainController;
    LinkedList<Button> buttons = new LinkedList<>();
    ImageView imageCelebrity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageCelebrity = findViewById(R.id.imageProm);

        buttons.add((Button) findViewById(R.id.buttonAnswerOne));
        buttons.add((Button) findViewById(R.id.buttonAnswerTwo));
        buttons.add((Button) findViewById(R.id.buttonAnswerThree));
        buttons.add((Button) findViewById(R.id.buttonAnswerFour));

        mainController = new MainController(this);

        mainController.downloadSite();
        mainController.nextImage();
    }

    public void onClick(View view)
    {
        mainController.checkAnswer(view.getTag().toString());
    }

    public void setButtonTexts(int position, String text)
    {
        buttons.get(position).setText(text);
        buttons.get(position).setTag(text);
    }

    public void setImage(Bitmap bitmap)
    {
        imageCelebrity.setImageBitmap(bitmap);
    }
}
