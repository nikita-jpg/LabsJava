
import org.json.JSONObject;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;


import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;


public class Bot extends TelegramLongPollingBot {

    public static void main(String[] args) {
        ApiContextInitializer.init();

        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new Bot());
        }catch (TelegramApiRequestException e){

        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message message=update.getMessage();

        if(message!=null && message.hasText()){
            if (message.getText().equals("/start")) sendMsg(message,"Напишите название любого города и бот расскажет вам о погоде в нём");
            else {
                try {
                    String weather = getWeather(message.getText());
                    sendMsg(message,weather);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public void sendMsg(Message message,String text){
        SendMessage sendMessage =new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);

        try {
            sendMessage(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }


    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }
    public String getWeather(String location) throws MalformedURLException {
        String otvet = "Weather in "+location+":\n";
        String adress = "https://api.openweathermap.org/data/2.5/weather?q="+location+"&appid=6259ffe1794b6a5975a137a614092d70";


        try {
            InputStream in = new URL(adress).openStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in, Charset.forName("UTF-8")));
            String jsonText= readAll(bufferedReader);

            JSONObject jsonObject = new JSONObject(jsonText);
            String temperature =  String.valueOf(jsonObject.getJSONObject("main").getDouble("temp")-273);
            String humidity = "Humidity: " + String.valueOf(jsonObject.getJSONObject("main").getDouble("humidity"))+" %";
            String wind = "Wind: " + String.valueOf(jsonObject.getJSONObject("wind").getInt("speed"))+" m/c";
            String clouds = String.valueOf(jsonObject.getJSONObject("clouds").getInt("all"));

            int cloudsInt = Integer.parseInt(clouds);
            if(cloudsInt<=10)clouds= "Clouds: "+ "Cloudless sky";
            else if(cloudsInt<=50) clouds = "Clouds: "+ "Few clouds";
            else if(cloudsInt<=90) clouds= "Clouds: "+ "Significant clouds";
            else clouds = "Clouds: "+  "Solid clouds";

            temperature="Temperature: " +String.valueOf((double)Math.round(Double.parseDouble(temperature) * 100000d) / 100000d)+" °C";

            otvet+=temperature+"\n"+clouds+"\n"+wind+"\n"+humidity;
            String ar = "da";
            return otvet;

        } catch (IOException e) {
            e.printStackTrace();
            return "Either you entered the name incorrectly, or there is no such city.\nPlease try again";
        }
    }



    @Override
    public String getBotUsername() {
        return "ficussbot";
    }

    @Override
    public String getBotToken() {
        return "1193171437:AAHt-RrTaw2_Xjmw_jTfDxBMxNttkK8mgcY";
    }
}
