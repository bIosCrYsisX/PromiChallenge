package tk.dalpiazsolutions.promichallenge;

import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import java.util.Locale;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Christoph on 30.03.2018.
 */

public class MainController {
    private SiteDownloader siteDownloader;
    private ImageDownloader imageDownloader;
    private MainActivity mainActivity;
    private MainModel mainModel;
    private Pattern containerPattern;
    private Matcher matcher;
    private Random random = new Random();
    private String[] splitResult;
    private String result;
    private String cache;

    private Handler handler = new Handler();

    private Runnable runnableNextImage = new Runnable() {
        @Override
        public void run() {
            nextImage();
        }
    };

    public MainController(MainActivity mainActivity)
    {
        this.mainActivity = mainActivity;
        siteDownloader = new SiteDownloader();
        imageDownloader = new ImageDownloader();
        mainModel = new MainModel(mainActivity);
    }

    public void downloadSite()
    {
        try {
           result = siteDownloader.execute("http://cdn.posh24.com/kandisar").get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        mainModel.setCompleteSite(result);

        trimToLinkContainer();
        trimToSources();
        trimToLinks();
        trimToNames();
    }

    public void nextImage()
    {
        mainModel.setElementNumber(random.nextInt(mainModel.getSources().size()));
        setAnswers();
        try {
            imageDownloader = new ImageDownloader();
            mainActivity.setImage(imageDownloader.execute(mainModel.getLinks().get(mainModel.getElementNumber())).get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public void checkAnswer(String answer)
    {
        if(answer.equals(mainModel.getNames().get(mainModel.getElementNumber())))
        {
            Toast.makeText(mainActivity.getApplicationContext(), mainActivity.getString(R.string.correct), Toast.LENGTH_LONG).show();
        }

        else
        {
            Toast.makeText(mainActivity.getApplicationContext(), String.format(Locale.getDefault(), mainActivity.getString(R.string.wrong), mainModel.getNames().get(mainModel.getElementNumber())), Toast.LENGTH_LONG).show();
        }

        Log.i("correct", mainModel.getNames().get(mainModel.getElementNumber()));

        handler.postDelayed(runnableNextImage, 500);
    }

    public void setAnswers()
    {
        mainModel.setCorrectAnswer(random.nextInt(mainActivity.buttons.size()));

        mainActivity.setButtonTexts(mainModel.getCorrectAnswer(), mainModel.getNames().get(mainModel.getElementNumber()));

        for(int i = 0; i < mainActivity.buttons.size(); i++)
        {
            if(i != mainModel.getCorrectAnswer())
            {
                while((cache = mainModel.getNames().get(random.nextInt(mainModel.getNames().size()))).equals(mainModel.getNames().get(mainModel.getElementNumber())));

                mainActivity.setButtonTexts(i, cache);
            }
        }
    }

    public void trimToLinkContainer()
    {
        try {
            splitResult = mainModel.getCompleteSite().split("<div class=\"col-xs-12 col-sm-6 col-md-4\">");
        }

        catch (Exception e) {
            e.printStackTrace();
        }

        mainModel.setLinkContainer(splitResult[0]);
    }

    public void trimToSources()
    {
        containerPattern = Pattern.compile("<img(.*?)/>");
        matcher = containerPattern.matcher(mainModel.getLinkContainer());

        while (matcher.find())
        {
            mainModel.addSourcesElement(matcher.group(1));
        }
    }

    public void trimToLinks()
    {
        containerPattern = Pattern.compile("src=\"(.*?)\"");

        for(int i = 0; i < mainModel.getSources().size(); i++)
        {
            matcher = containerPattern.matcher(mainModel.getSources().get(i));
            matcher.find();
            mainModel.addLinksElement(matcher.group(1));
        }
    }

    public void trimToNames()
    {
        containerPattern = Pattern.compile("alt=\"(.*?)\"");

        for(int i = 0; i < mainModel.getSources().size(); i++)
        {
            matcher = containerPattern.matcher(mainModel.getSources().get(i));
            matcher.find();
            mainModel.addNamesElement(matcher.group(1));
        }
    }
}
