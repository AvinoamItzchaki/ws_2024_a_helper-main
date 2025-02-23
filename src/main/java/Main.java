import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        System.out.println();

        attemptImage();

        attemptClientList();

        //System.out.println(readFile());
        List<Client>clientList = readFile();
        //System.out.println(clientList);

        //Write your code here!
    }

    private static void attemptClientList() {
        List<Client>clientList = readFile();
        //מהי שנת הלידה הממוצעת של נשים? (יש לשלוח מספר שלם כתשובה - לעגל כלפי מטה)
        double a =  clientList.stream()
                .filter(client -> client.getGender().equals("Female"))
                .mapToInt(Client::getYearBorn)
                .average()
                .orElse(0.0);
        int question1 = (int) Math.floor(a);
        System.out.println(question1);


        //מהו השם הפרטי הנפוץ ביותר אצל גברים?
        Map<String, Long> maleNameCount = clientList.stream()
                .filter(client -> client.getGender().equals("Male"))
                .collect(Collectors.groupingBy(Client::getFirstName, Collectors.counting()));

        String mostFrequentName = maleNameCount.entrySet()
                .stream()
                .max(Comparator.comparingLong(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .orElse(null);
        System.out.println(mostFrequentName);


        //מהו ממוצע החשבונית עבור לקוחות שיש להם בשם הפרטי a או A?
        double question3 =  clientList.stream()
                .filter(client -> client.getFirstName().contains("A") || client.getFirstName().contains("a"))
                .mapToInt(Client::getPurchaseAmount)
                .average()
                .orElse(0.0);
        System.out.println(question3);

        //כמה לקוחות מעיר שמתחילה במילה Lake שילמו יותר מ-400$?
        List<Client> question4List = clientList.stream()
                .filter(client -> client.getPurchaseAmount() > 400)
                .filter(client -> client.getHometown().startsWith("Lake"))
                .toList();
        System.out.println(question4List.size());

        //לקוחות של איזו עיר רכשו בסכום הכסף הכולל הגדול ביותר?

        // חישוב סכום הרכישות לכל עיר
        Map<String, Integer> cityTotalPurchases = clientList.stream()
                .collect(Collectors.groupingBy(Client::getHometown,
                        Collectors.summingInt(Client::getPurchaseAmount)));

        // מציאת העיר עם הסכום הגבוה ביותר
        String question5 = cityTotalPurchases.entrySet().stream()
                .max(Comparator.comparingInt(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .orElse("לא נמצאה עיר");

        System.out.println(question5);


        List<Client> lakeBoris =  clientList.stream()
                .filter(client -> client.getHometown().equals("Lake Boris"))
                .toList();
        System.out.println(lakeBoris);

        //מהו מספר תעודת הזהות שמופיע יותר מפעם אחת ברשימה?
        Map<String, Long> frequencyMap = clientList.stream()
                .collect(Collectors.groupingBy(Client::getId, Collectors.counting()));

        String question6 = frequencyMap.entrySet().stream()
                .filter(entry -> entry.getValue() > 1)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);
        System.out.println(question6);
    }

    private static void attemptImage() {
        BufferedImage image;
        try {
            File file = new File("C:\\Users\\Owner\\Downloads\\ws_2024_a_helper-main\\ws_2024_a_helper-main\\images\\67.png");
            image = ImageIO.read(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int colorCode = findMostCommonColorExceptWhite(image);
        System.out.println(colorCode);
        Color color = new Color(colorCode);
        int red = color.getRed(),green = color.getGreen(),blue = color.getBlue();
        System.out.println(red);
        System.out.println(green);
        System.out.println(blue);
        System.out.println();
    }

//    private static void API_requestForImageAndClients() {
//        try {
//            CloseableHttpClient client1 =  HttpClients.createDefault();
//            URI uri1 = new URIBuilder("https://app.seker.live/fm1/answer-file")
//                    .setParameter("magic", 23522151)
//                    .setParameter("question", 1)
//                    .setParameter("answer", 2)
//                    .build();
//            HttpPost request1 = new HttpPost(uri1);
//            CloseableHttpResponse response = client1.execute(request1);
//            String json1 = EntityUtils.toString(response.getEntity()); // מדפיס את הגייסון שנוצר מבקשות האפיאי
//            System.out.println(json1);
//        } catch (URISyntaxException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//        try {
//            CloseableHttpClient client2 =  HttpClients.createDefault();
//            URI uri2 = new URIBuilder("https://app.seker.live/fm1/answer-file")  //יצירת הקישור לפי הדרישות שיהיה בקובץ
//                    .setParameter("magic", 23522151) // נוסיף פרמטרים לפי הבקשות
//                    .setParameter("red", 1)
//                    .setParameter("green", 2)
//                    .setParameter("blue", 3)
//                    .build();
//            HttpPost request = new HttpPost(uri2);
//            CloseableHttpResponse response = client2.execute(request);
//            String json2 = EntityUtils.toString(response.getEntity()); // מדפיס את הגייסון שנוצר מבקשות האפיאי
//            System.out.println(json2);// זה חובה כדי שנראה את ההתקדמות שלנו במבחן והשלבים המתוכננים
//        } catch (URISyntaxException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }

    public static List<Client> readFile () {
        List<List<String>> lines = new ArrayList<>();
        List<Client>clientList = new ArrayList<>();

        try {
            File file = new File("C:\\Users\\Owner\\Downloads\\ws_2024_a_helper-main\\ws_2024_a_helper-main\\data.csv");
            if (file.exists()) {
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String[] tokens = line.split(",");

                    //[19999, 27866548, Nicolas, Ratke, 1992, Female, Renaldoside, 838]
                    int lineNumber = getIntFromString(tokens[0]);
                    String id = tokens[1];
                    String firstName = tokens[2];
                    String lastName = tokens[3];
                    int yearBorn = getIntFromString(tokens[4]);
                    String gender = tokens[5];
                    String hometown = tokens[6];
                    int purchaseAmount = getIntFromString(tokens[7]);
                    Client client = new Client( lineNumber,  id,  firstName, lastName, yearBorn,gender, hometown,purchaseAmount);
                    clientList.add(client);

                    List<String> tokensList = new ArrayList<>(Arrays.asList(tokens));
                    lines.add(tokensList);

                }

            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return clientList;
    }
    public static int findMostCommonColorExceptWhite(BufferedImage image) {
        // יצירת מפה לאחסון ספירת צבעים
        Map<Integer, Integer> colorCounts = new HashMap<>();

        // סריקה דרך כל פיקסל בתמונה
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                int rgb = image.getRGB(x, y);

                // חישוב מזהה הצבע
                int colorId = (rgb & 0x00ffffff);

                // דילוג על צבע לבן (0xffffff)
                if (colorId == 0xffffff) {
                    continue;
                }

                // עדכון ספירת הצבע במפה
                if (colorCounts.containsKey(colorId)) {
                    colorCounts.put(colorId, colorCounts.get(colorId) + 1);
                } else {
                    colorCounts.put(colorId, 1);
                }
            }
        }

        // מציאת הצבע השלישי הנפוץ ביותר (ללא לבן)
        int mostCommonColorId = 0;
        int secondMostCommonColorId = 0;
        int thirdMostCommonColorId = 0;
        int mostCommonCount = 0;
        int secondMostCommonCount = 0;
        int thirdMostCommonCount = 0;

        for (Map.Entry<Integer, Integer> entry : colorCounts.entrySet()) {
            int colorId = entry.getKey();
            int count = entry.getValue();

            if (count > mostCommonCount) {
                thirdMostCommonColorId = secondMostCommonColorId;
                secondMostCommonColorId = mostCommonColorId;
                mostCommonColorId = colorId;
                mostCommonCount = count;
            } else if (count > secondMostCommonCount) {
                thirdMostCommonColorId = secondMostCommonColorId;
                secondMostCommonColorId = colorId;
                secondMostCommonCount = count;
            } else if (count > thirdMostCommonCount) {
                thirdMostCommonColorId = colorId;
                thirdMostCommonCount = count;
            }
        }

        return thirdMostCommonColorId;
    }
    public static int getIntFromString(String string){
        if (string.isEmpty()||string.equals(",")||string.equals(" ")){
            return 0;
        }
        else
            return Integer.parseInt(string);
    }

}
