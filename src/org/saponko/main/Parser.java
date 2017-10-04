package org.saponko.main;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Parser {
    private static File file = new File("task2.html");

    public static void main(String[] args) {

        try {
            Document doc = Jsoup.parse(file, "UTF-8");
            List<Element> list = doc.body().children();
            List<Object> arrays = new ArrayList<>();
            for (Element el :
                    list) {
                if (el.tagName().equals("ul")) {
                    arrays.add(getArray(el.children()));
                }
            }
            //This is result!
            Object[] result = arrays.toArray();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object[] getArray(List<Element> el) {
        List<Object> arr = new ArrayList<>();
        for (Element e : el) {
            List<Object> arr2 = new ArrayList<>();
            Pair pair = new Pair(e.child(0).text(), e.child(0).attr("href"));
            arr2.add(pair);
            //I don`t now better way to visualize n-array...
            System.out.println(pair);

            if (e.children().size() > 1) {
                arr2.add(getArray(e.child(1).children()));
            }
            arr.add(arr2.toArray());
        }
        return arr.toArray();
    }

    private static class Pair {
        private String keyName;
        private String valueHref;

        public Pair(String keyName, String valueHref) {
            this.keyName = keyName;
            this.valueHref = valueHref;
        }

        public String getKeyName() {
            return keyName;
        }

        public void setKeyName(String keyName) {
            this.keyName = keyName;
        }

        public String getValueHref() {
            return valueHref;
        }

        public void setValueHref(String valueHref) {
            this.valueHref = valueHref;
        }

        @Override
        public String toString() {
            return "Pair{" +
                    "keyName='" + keyName + '\'' +
                    ", valueHref='" + valueHref + '\'' +
                    '}';
        }
    }
}
