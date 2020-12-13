/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package voice.recognizer;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import edu.cmu.sphinx.api.*;
import java.awt.AWTException;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javax.imageio.ImageIO;

/**
 *
 * @author Arafat Hossain Ar
 */
public class voiceRecognizer {

    //Voice Objects
    VoiceManager voicemanager;
    Voice voice;

    //object for Screenshot taking system
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    Rectangle screenRectangle = new Rectangle(screenSize);

    public void voiceDetect() throws IOException, URISyntaxException, AWTException {

        //Configuration for voice detection
        Configuration configuration = new Configuration();
        
        //Set the path
        configuration.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
        configuration.setDictionaryPath("src/voice/rec/sources/3800.dic");
        configuration.setLanguageModelPath("src/voice/rec/sources/3800.lm");
        

        LiveSpeechRecognizer recognize = new LiveSpeechRecognizer(configuration);
        recognize.startRecognition(true);
        
        //Robot object for controling pc
        Robot robot = new Robot();
        
        //Voice result object
        SpeechResult speechResult;
        //new LinaVoice().setVisible(true);

        System.setProperty("mbrola.base", "src/mbrola");
        voicemanager = VoiceManager.getInstance();

        // Simply change to MBROLA voice
        voice = voicemanager.getVoice("mbrola_us1");

        // Allocate your chosen voice
        voice.allocate();
        voice.speak("Voice Control Activated!");
                
        while ((speechResult = recognize.getResult()) != null) {
            String command = speechResult.getHypothesis();
            System.out.println("Input is: " + command);

            //HELLO LINA
            //HI LINA 
            if (command.contains("HELLO LINA")) {
                voice.speak("HELLO SIR!");
            } //HOW ARE YOU
            else if (command.contains("HOW ARE YOU")) {
                voice.speak("I AM FINE SIR!");
            } //WHATS UP
            else if (command.contains("WHATS UP")) {
                voice.speak("EVERYTHING FINE SIR");
            } //SORRY
            else if (command.contains("SORRY")) {
                voice.speak("IT'S OK SIR!");
            } //GREAT
            else if (command.contains("GREAT")) {
                voice.speak("THANK YOU SIR!");
            } //AWESOME
            else if (command.contains("AWESOME")) {
                voice.speak("THANK YOU SIR!");
            } //WHAT IS YOUR NAME
            else if (command.contains("YOUR NAME")) {
                voice.speak("MY NAME IS LINA");
            } //WHO IS YOUR CREATOR
            else if (command.contains("YOUR CREATOR")) {
                voice.speak("MY CREATOR IS AARAFAT HOSSAIN.");
            } //WOW THAT IS VERY GOOD
            else if (command.contains("VERY GOOD")) {
                voice.speak("THANK YOU SIR!");
            } //DO YOU WANT TO LEARN MORE
            else if (command.contains("LEARN MORE")) {
                voice.speak("YES SIR! I LOVE TO LEARN MORE.");
            } //WHAT CAN YOU DO FOR ME
            else if (command.contains("DO FOR ME")) {
                voice.speak("I CAN EXECUTE YOUR COMMAND.");
            } //CAN YOU TELL ME A JOKE
            else if (command.contains("JOKE")) {
                voice.speak("SORRY SIR! YOU DID'T TEACH ME THAT");
            } //OK TELL ME SOMETHING THAT I DO NOT KNOW
            else if (command.contains("SOMETHING")) {
                voice.speak("YOU DIDN'T KNOW WHAT IS LOVE.");
            } //OK WHAT IS ARTIFICIAL INTELLIGENCE
            else if (command.contains("ARTIFICIAL INTELLIGENCE")) {
                voice.speak("Artificial intelligence refers to the simulation of human intelligence in machines that are programmed to think like humans and mimic their actions. "
                        + "The term may also be applied to any machine that exhibits traits associated with a human mind such as learning and problem-solving.");
            } //DO YOU KNOW WHAT IS LOVE
            else if (command.contains("DO YOU KNOW WHAT IS LOVE")) {
                voice.speak("NO SIR! CAUSE I AM A BOT.");
            } //WHAT IS JAVA
            else if (command.contains("JAVA")) {
                voice.speak("Java is a powerful general-purpose programming language. "
                        + "It is used to develop desktop and mobile applications, big data processing, embedded systems, and so on. According to Oracle, the company that owns Java, "
                        + "Java runs on 3 billion devices worldwide, which makes Java one of the most popular programming languages.");
            } //WHAT IS VERSION CONTROL
            else if (command.contains("VERSION CONTROL")) {
                voice.speak("Version control enables teams to collaborate and streamline development to resolve conflicts and create a centralized location for code.");
            } //WHAT IS LOVE
            else if (command.contains("WHAT IS LOVE")) {
                voice.speak("I DON'T KNOW SIR!");
            } //WHAT IS COMPUTER
            else if (command.contains("COMPUTER")) {
                voice.speak("A computer is an electronic device that manipulates information, or data. "
                        + "It has the ability to store, retrieve, and process data.");
            } //WHAT IS MACHINE
            else if (command.contains("MACHINE")) {
                voice.speak("A machine (or mechanical device) is a mechanical structure that uses power to apply forces and control movement to perform an intended action. "
                        + "They can also include computers and sensors that monitor performance and plan movement, often called mechanical systems");
            } //WHAT TIME IS IT NOW
            else if (command.contains("TIME")) {
                String ss = DateTimeFormatter.ofPattern("hh:mm a").format(LocalTime.now());
                voice.speak("The time is: " + ss);

            } //TODAYS DATE
            else if (command.contains("DATE")) {

                String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
                LocalDate currentDate = LocalDate.parse(date);
                // Get day from date 
                int day = currentDate.getDayOfMonth();
                // Get month from date 
                Month month = currentDate.getMonth();
                // Get year from date 
                int year = currentDate.getYear();
                String speakdate = "Today's date: " + day + " " + month + " " + year + " ";
                voice.speak(speakdate);

            } //HOW IS THE WEATHER TODAY
            else if (command.contains("HOW IS THE WEATHER TODAY")) {
                voice.speak("A little chilly – you might wanna bring a jacket");
            } //CAPTURE SCREENSHOT
            else if (command.contains("SCREENSHOT")) {

                BufferedImage image = robot.createScreenCapture(screenRectangle);
                File file = new File("screenshot.png");
                ImageIO.write(image, "png", file);

                File u = new File("screenshot.png");

                Desktop d = Desktop.getDesktop();
                d.open(u);

                voice.speak("Screenshot captured");
            } //CLOSE WINDOW
            else if (command.contains("WINDOW")) {

                voice.speak("Closing window");
                robot.delay(500);
                robot.keyPress(KeyEvent.VK_ALT);
                robot.keyPress(KeyEvent.VK_F4);
                robot.keyRelease(KeyEvent.VK_ALT);
                robot.keyRelease(KeyEvent.VK_F4);

            } //OPEN BROWSER
            else if (command.contains("OPEN BROWSER")) {

                String chrome = "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe";
                Runtime run = Runtime.getRuntime();
                run.exec(chrome);
                voice.speak("Opening Browser");

            } //GO TO GOOGLE
            else if (command.contains("GOOGLE")) {

                URI google = new URI("www.google.com");
                Desktop d = Desktop.getDesktop();
                d.browse(google);
                voice.speak("Going to google dot com");

            } //GO TO YOUTUBE
            else if (command.contains("YOUTUBE")) {

                URI google = new URI("www.youtube.com");
                Desktop d = Desktop.getDesktop();
                d.browse(google);
                voice.speak("Going to youtube dot com");

            } //CLOSE BROWSER
            else if (command.contains("CLOSE BROWSER")) {

                voice.speak("Closing Browser");
                robot.delay(500);
                robot.keyPress(KeyEvent.VK_ALT);
                robot.keyPress(KeyEvent.VK_F4);
                robot.keyRelease(KeyEvent.VK_ALT);
                robot.keyRelease(KeyEvent.VK_F4);
            } //OPEN NOTEPAD
            else if (command.contains("OPEN NOTEPAD")) {

                voice.speak("Opening notepad");
                robot.delay(500);
                Runtime run = Runtime.getRuntime();
                run.exec("notepad.exe");

            } //CLOSE NOTEPAD
            else if (command.contains("CLOSE NOTEPAD")) {

                voice.speak("Closing notepad");
                robot.delay(500);
                robot.keyPress(KeyEvent.VK_ALT);
                robot.keyPress(KeyEvent.VK_F4);
                robot.keyRelease(KeyEvent.VK_ALT);
                robot.keyRelease(KeyEvent.VK_F4);

            } //OPEN CALCULATOR
            else if (command.contains("OPEN VS CODE")) {

                voice.speak("Opening VS Code");
                robot.delay(500);
                Runtime run = Runtime.getRuntime();
                run.exec("C:\\Program Files\\Microsoft VS Code\\Code.exe");

            } //CLOSE CALCULATOR
            else if (command.contains("CLOSE VS CODE")) {

                voice.speak("Closing VS Code");
                robot.delay(500);
                robot.keyPress(KeyEvent.VK_ALT);
                robot.keyPress(KeyEvent.VK_F4);
                robot.keyRelease(KeyEvent.VK_ALT);
                robot.keyRelease(KeyEvent.VK_F4);
            } //OPEN MUSIC PLAYER
            else if (command.contains("OPEN MUSIC PLAYER")) {

                voice.speak("Opening Music Player");
                robot.delay(500);
                Runtime run = Runtime.getRuntime();
                run.exec("C:\\Program Files (x86)\\Windows Media Player\\wmplayer.exe");
            } //CLOSE MUSIC PLAYE     
            else if (command.contains("CLOSE MUSIC PLAYER")) {

                voice.speak("Closing Music Player");
                robot.delay(500);
                robot.keyPress(KeyEvent.VK_ALT);
                robot.keyPress(KeyEvent.VK_F4);
                robot.keyRelease(KeyEvent.VK_ALT);
                robot.keyRelease(KeyEvent.VK_F4);

            } //SHUTDOWN
            else if (command.equalsIgnoreCase("SHUTDOWN")) {

                voice.speak("GoodBye Sir!");
                System.exit(0);
            }
        }
    }

}
