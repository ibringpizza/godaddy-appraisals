package gdappraisals;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.HttpsURLConnection;
import org.codehaus.jackson.map.ObjectMapper;
import java.util.Map.Entry;

public class GDAppraisals {

    private static Comparable_Sales[] relatedSales = new Comparable_Sales[0];
    public static void main(String[] args) {
        LinkedHashMap<String, Integer> map = new LinkedHashMap<>();
        
        StringBuilder sb = new StringBuilder();
        sb.append("type domain to appraise and add to current list" + "\n")
            .append("bulk + domains seperated by commas" + "\n")
            .append("r DOMAIN - related sales" + "\n")
            .append("sort to sort list by appraisals" + "\n")
            .append("save NAME (alphanumeric) to save current list state to your Downloads folder" + "\n")
            .append("load FILE to load from file" + "\n")
            .append("clear to clear current list" + "\n")
            .append("help" + "\n");
        System.out.println(sb.toString());
        Scanner input = new Scanner(System.in);
        Comparator<Entry<String, Integer>> compare = (Entry<String, Integer> a1, Entry<String, Integer> a2) -> {
            Integer ap1 = a1.getValue();
            Integer ap2 = a2.getValue();
            return ap2.compareTo(ap1);
        };
        while(true){
            String cmd = input.nextLine();
            if(cmd.equals("sort")){
                Set values = map.entrySet();
                List<Entry<String, Integer>> list = new ArrayList<>(values);
                Collections.sort(list, compare);
                map.clear();
                for(Entry<String, Integer> entry : list){
                    System.out.println(entry.getKey() + " -> " + entry.getValue());
                    map.put(entry.getKey(), entry.getValue());
                }
            }else if(cmd.matches("save [a-zA-Z0-9]+")){
                File file = new File("C:\\Users\\" + System.getProperty("user.name") + "\\Downloads\\" + cmd.substring(5, cmd.length()) + ".txt");
                try {
                    final BufferedWriter bw = new BufferedWriter(new FileWriter(file));
                    map.entrySet().stream().forEach(entry -> {
                        try {
                            bw.write(entry.getKey() + "," + entry.getValue() + "\n");
                        } catch (IOException ex) {
                            Logger.getLogger(GDAppraisals.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    });
                    bw.flush();
                    bw.close();
                    System.out.println("current map state saved");
                } catch (IOException ex) {
                    Logger.getLogger(GDAppraisals.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else if(cmd.startsWith("load")){
                File file = new File(cmd.substring(5, cmd.length()).replaceAll("\"", ""));
                List<String> list = null;
                try {
                    list = Files.readAllLines(file.toPath());
                } catch (IOException ex) {
                    Logger.getLogger(GDAppraisals.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                for(String line : list){
                    line = line.replaceAll(" ", "");
                    if(line.matches("[a-zA-Z0-9][a-zA-Z0-9-]{1,61}[a-zA-Z0-9]\\.[a-zA-Z]{2,}")){
                        int appraisal = 0;
                        try {
                            appraisal = appraise(line);
                        } catch (IOException | InterruptedException ex) {
                            Logger.getLogger(GDAppraisals.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        map.put(line, appraisal);
                        System.out.println(line + " -> " + appraisal);
                    }else{
                        String[] info = line.split(",");
                        map.put(info[0], Integer.parseInt(info[1]));
                    }
                }
                System.out.println("loaded");
                
            }else if(cmd.startsWith("bulk")){
                String[] domains = cmd.substring(5, cmd.length()).split(",");
                for(String domain : domains){
                    domain = domain.replaceAll(" ", "");
                    if(!domain.matches("[a-zA-Z0-9][a-zA-Z0-9-]{1,61}[a-zA-Z0-9]\\.[a-zA-Z]{2,}")){
                        System.out.println(domain + " is not a valid domain");
                        continue;
                    }
                    try {
                        int appraisal = appraise(domain);
                        map.put(domain, appraisal);
                        System.out.println(domain + " -> " + appraisal);
                    } catch (MalformedURLException ex) {
                        Logger.getLogger(GDAppraisals.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (InterruptedException | IOException ex) {
                        Logger.getLogger(GDAppraisals.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                System.out.println("finished");
            }else if (cmd.matches("r [a-zA-Z0-9][a-zA-Z0-9-]{1,61}[a-zA-Z0-9]\\.[a-zA-Z]{2,}")){
                try {
                    int appraisal = appraise(cmd.substring(2, cmd.length()));
                    map.put(cmd.substring(2, cmd.length()), appraisal);
                    System.out.println(appraisal);
                    Arrays.asList(relatedSales).stream().forEach(r -> System.out.println(r.toString()));
                } catch (MalformedURLException ex) {
                    Logger.getLogger(GDAppraisals.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InterruptedException | IOException ex) {
                    Logger.getLogger(GDAppraisals.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else if (cmd.matches("[a-zA-Z0-9][a-zA-Z0-9-]{1,61}[a-zA-Z0-9]\\.[a-zA-Z]{2,}")){
                try {
                    int appraisal = appraise(cmd);
                    map.put(cmd, appraisal);
                    System.out.println(appraisal);
                } catch (MalformedURLException ex) {
                    Logger.getLogger(GDAppraisals.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InterruptedException | IOException ex) {
                    Logger.getLogger(GDAppraisals.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else if(cmd.matches("clear")){
                map.clear();
                System.out.println("list cleared");
            }else if(cmd.matches("help"))
                System.out.println(sb.toString());
        }
    }
    
    public static int appraise(String domain) throws MalformedURLException, IOException, InterruptedException{
        int appraisal = 0;
        int responseCode = 0;
        HttpsURLConnection connect = null;
        String link = "https://api.godaddy.com/v1/appraisal/" + domain;
        URL url = new URL(link);
        while(responseCode != 200){
            try{
                connect = (HttpsURLConnection) url.openConnection();
                connect.setRequestMethod("GET");
                connect.addRequestProperty("accept", "application/json");
                responseCode = connect.getResponseCode();
                Thread.sleep(2000);
            } catch(UnknownHostException ex){
                System.out.println(ex.toString());
                waitForConnection();
            } catch(Exception e){
                System.out.println(e.toString());
                waitForConnection();
            }
        }
        
        BufferedReader br = new BufferedReader(new InputStreamReader(connect.getInputStream()));
        ObjectMapper om = new ObjectMapper();
        MainParser mp = om.readValue(br.readLine(), MainParser.class);
        relatedSales = mp.getComparable_sales();
        return Integer.parseInt(mp.getGovalue());
    }
    
    public static boolean connected(){
        try {
            URL url = new URL("https://google.com/");
            HttpsURLConnection check = (HttpsURLConnection) url.openConnection();
        } catch (Exception ex) {
            return false;
        }
        return true;
    }
    
    static void waitForConnection(){
        boolean checkConnection = connected();
        while (!checkConnection) {
            System.out.println("Checking connection...");
            checkConnection = connected();
            try {
                Thread.sleep(25000);
            } catch (InterruptedException ex) {
                Logger.getLogger(GDAppraisals.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("connected");
    }
}