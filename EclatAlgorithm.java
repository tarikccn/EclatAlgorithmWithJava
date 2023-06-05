/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package eclatalgorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 *
 * @author bhop
 */
public class EclatAlgorithm {

    static String[][] tidList = {{"Ekmek", "Sut", "Cerez", "Salça"}, {"Bira", "Bebek Bezi", "Ekmek", "Yumurta"},{"Bira", "Bebek Bezi", "Ekmek", "Yumurta"}, {"Bira", "Kola", "Bebek Bezi", "Sut", "Cerez"},
    {"Kola", "Ekmek", "Bebek Bezi", "Sut"}, {"Bira", "Ekmek", "Bebek Bezi", "Sut", "Cerez"}, {"Bira", "Ekmek", "Bebek Bezi", "Sut", "Salça"}, {"Kola", "Ekmek", "Bebek Bezi", "Sut"}};
    static String[] items = {"Ekmek", "Sut", "Cerez", "Salça", "Bira", "Bebek Bezi", "Yumurta", "Kola"};
    static String[][] newTidList = {};
    //static String[] newItems;
    static int support = 0;
    static int minsup = 4;
    static int count = 0;
    static HashMap<Integer, ArrayList<String>> tid = new HashMap<>();

    public static void main(String[] args) {

        //Support 
        System.out.println("--------------------Support Value----------------------");
        SupportCalculate(tidList, items);

        System.out.println("-------------------------------------------------------");
        //Kesişim alma

        System.out.println(count);
        //minSup desteklemeyeni ele (MIN SUPPORT = 4)
        String[] newItems = new String[count];

        System.out.println("-------------------------------------------------------");
        //Support degerini destekleyen elemanlar icin yeni tidList olusturma
        NewTidListItems(newItems, tidList, items);
        //minSup'a göre filtrelenen tablo
        System.out.println("-------------------------------------------------------");
        ArrayList<Integer> b = new ArrayList<>();
        //NewTidList(newItems, b);

        System.out.println(NewTidList(newItems, b));
        System.out.println(NewTidList(newItems, b).get(newItems[0]));
        System.out.println(NewTidList(newItems, b).get(newItems[1]).size());
        System.out.println(NewTidList(newItems, b).size());

        System.out.println(newItems.length);
        HashMap<String, ArrayList<Integer>> k2 = new HashMap<>();
      
        String[][] asd;
        String[] itemsasd = new String[Step2(newItems, b).size()];

        System.out.println(Step2(newItems, b));

        count = 0;
        for (int i = 0; i < Step2(newItems, b).size(); i++) {
            if (Step2(newItems, b).get(HashmapToArray(Step2(newItems, b), itemsasd)[i]).size() >= minsup) {
                count++;
                System.out.println(HashmapToArray(Step2(newItems, b), itemsasd)[i] + " = Support Degeri : " + Step2(newItems, b).get(HashmapToArray(Step2(newItems, b), itemsasd)[i]).size());

            }

        }

        String[] newItems1 = new String[count];
        count = 0;
        for (int i = 0; i < Step2(newItems, b).size(); i++) {
            if (Step2(newItems, b).get(HashmapToArray(Step2(newItems, b), itemsasd)[i]).size() >= minsup) {
                //count++;
                newItems1[count] = HashmapToArray(Step2(newItems, b), itemsasd)[i];
                count++;
            }

        }
        count = 0;
        HashMap<String, ArrayList<Integer>> newHashMap = new HashMap<>();
        ArrayList<Integer> sa ;
        for (int i = 0; i < Step2(newItems, b).size(); i++) {
            sa = new ArrayList<>();
            sa.clear();
            for (int j = 0; j < Step2(newItems, b).get(HashmapToArray(Step2(newItems, b), itemsasd)[i]).size(); j++) {
                if (Step2(newItems, b).get(HashmapToArray(Step2(newItems, b), itemsasd)[i]).size() >= minsup) {
                    
                    sa.add(Step2(newItems, b).get(HashmapToArray(Step2(newItems, b), itemsasd)[i]).get(j));
                }
            }
            if (Step2(newItems, b).get(HashmapToArray(Step2(newItems, b), itemsasd)[i]).size() >= minsup) {
                newHashMap.put(newItems1[count], sa);
                count++;
            }
            
            
            
        }
        System.out.println("-------------------------------------------------------");
        System.out.println("Array : " + newHashMap);
        
        System.out.println("");
        ArrayList<Integer> gg = new ArrayList<>();
        System.out.println(Step3(newItems1, gg, newHashMap));

       
    }
    public static HashMap<String, ArrayList<Integer>> Step3(String[] newItems, ArrayList<Integer> b, HashMap<String, ArrayList<Integer>> k2) {
        HashMap<String, ArrayList<Integer>> k4 = new HashMap<>();
        int a = 0;
        System.out.println(k2);
        while (a < k2.size() - 1) {

            for (int i = a; i < k2.size() - 1; i++) {
                ArrayList<Integer> k3 = new ArrayList<>();
                k3.clear();

                for (int j = 0; j < k2.get(newItems[a]).size(); j++) {

                    for (int k = 0; k < k2.get(newItems[i + 1]).size(); k++) {

                        if (k2.get(newItems[a]).get(j) == k2.get(newItems[i + 1]).get(k)) {
                            k3.add(k2.get(newItems[a]).get(j));
                            //System.out.println(newItems[a] + " ile karsilasti " + newItems[i + 1] + " : " + NewTidList(newItems, b).get(newItems[i + 1]).get(k));

                        }

                    }
                    k4.put(newItems[a] + "," + newItems[i + 1], k3);

                }

            }
            //System.out.println("-----");
            a++;
        }
        return k4;
    }

    static String[] HashmapToArray(HashMap<String, ArrayList<Integer>> asd, String[] items) {
        ArrayList<String> keySet = new ArrayList<>(asd.keySet());
        //System.out.println(keySet.size());
        for (int i = 0; i < keySet.size(); i++) {
            items[i] = keySet.get(i);
        }
        return items;
    }

    public static int SupportCalculate(String[][] tidList, String[] items) {
        for (int x = 0; x < items.length; x++) {
            for (int i = 0; i < tidList.length; i++) {
                for (int j = 0; j < tidList[i].length; j++) {
                    if (tidList[i][j] == items[x]) {
                        support++;
                    }
                    //System.out.print(tidList[i][j] + ",");
                }
                //System.out.println("");
            }
            if (support >= minsup) {

                count++;

            }
            System.out.println(items[x] + " Support Degeri : " + support);
            support = 0;

        }
        return count;
    }

    public static String[] NewTidListItems(String[] newItems, String[][] tidList, String[] items) {
        int c = 0;
        for (int x = 0; x < items.length; x++) {
            for (int i = 0; i < tidList.length; i++) {
                for (int j = 0; j < tidList[i].length; j++) {
                    if (tidList[i][j] == items[x]) {
                        support++;

                    }
                    //System.out.print(tidList[i][j] + ",");
                }
                //System.out.println("");
            }
            if (support >= minsup) {

                newItems[c] = items[x];
                System.out.println(newItems[c]);
                c++;

            }

            support = 0;

        }
        return newItems;
    }

    public static HashMap<String, ArrayList<Integer>> NewTidList(String[] newItems, ArrayList<Integer> b) {
        HashMap<String, ArrayList<Integer>> NewTidListMap = new HashMap<>();
        int k = 0;
        while (newItems.length != k) {
            b = new ArrayList<>();
            b.clear();
            //System.out.print(newItems[k] + " :");
            for (int i = 0; i < tidList.length; i++) {
                for (int j = 0; j < tidList[i].length; j++) {
                    if (newItems[k] == tidList[i][j]) {
                        b.add(i + 1);
                        //System.out.print("   " + (i + 1) + ", ");

                    }

                }

            }
            NewTidListMap.put(newItems[k], b);

            //System.out.println("");
            //asd.put(k, b);
            k++;

        }

        //System.out.println("Yeni");
        //System.out.println(NewTidListMap);
        return NewTidListMap;
    }

    //Ikili sekilde kesisim alma
    public static HashMap<String, ArrayList<Integer>> Step2(String[] newItems, ArrayList<Integer> b) {
        HashMap<String, ArrayList<Integer>> k2 = new HashMap<>();
        int a = 0;
        while (a < NewTidList(newItems, b).size() - 1) {

            for (int i = a; i < NewTidList(newItems, b).size() - 1; i++) {
                ArrayList<Integer> k3 = new ArrayList<>();
                k3.clear();

                for (int j = 0; j < NewTidList(newItems, b).get(newItems[a]).size(); j++) {

                    for (int k = 0; k < NewTidList(newItems, b).get(newItems[i + 1]).size(); k++) {

                        if (NewTidList(newItems, b).get(newItems[a]).get(j) == NewTidList(newItems, b).get(newItems[i + 1]).get(k)) {
                            k3.add(NewTidList(newItems, b).get(newItems[a]).get(j));
                            //System.out.println(newItems[a] + " ile karsilasti " + newItems[i + 1] + " : " + NewTidList(newItems, b).get(newItems[i + 1]).get(k));

                        }

                    }
                    k2.put(newItems[a] + "," + newItems[i + 1], k3);

                }

            }
            //System.out.println("-----");
            a++;
        }
        return k2;
    }
}
