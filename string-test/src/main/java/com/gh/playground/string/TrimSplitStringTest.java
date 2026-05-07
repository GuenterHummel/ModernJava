package com.gh.playground.string;

import java.util.ArrayList;
import java.util.List;

public class TrimSplitStringTest {
    final private static String TEST_1 = "SIU, CBM, TTU, CDM, CIM, RPR, PPR, JPR, IDC, NFC, DPR, SPR, PIN, SCN, DEP, VDM, SEC, BCR";
    final private static String TEST_2 = "SIU,CBM,TTU,CDM,CIM,RPR,PPR,JPR,IDC,NFC,DPR,SPR,PIN,SCN,DEP,VDM,SEC,BCR";
    final private static String TEST_3 = "SIU; CBM; TTU; CDM; CIM; RPR; PPR; JPR; IDC; NFC; DPR; SPR; PIN; SCN; DEP; VDM; SEC; BCR";
    final private static String TEST_4 = "SIU;CBM;TTU;CDM;CIM;RPR;PPR;JPR;IDC;NFC;DPR;SPR;PIN;SCN;DEP;VDM;SEC;BCR";

    public static void main(String[] args) {
        List<String> split1= new ArrayList<>(
                List.of(TEST_1.trim().split("\\s*[,;]\\s*")));

        System.out.println(split1);
        System.out.println("<" + split1.get(0) +">");
        System.out.println("<" + split1.get(2) +">");
        System.out.println("<" + split1.get(5) +">");

        List<String> split2= new ArrayList<>(
                List.of(TEST_2.trim().split("\\s*[,;]\\s*")));
        System.out.println(split2);
        System.out.println("<" + split2.get(0) + ">");
        System.out.println("<" + split2.get(2) + ">");
        System.out.println("<" + split2.get(5) + ">");

        List<String> split3= new ArrayList<>(
                List.of(TEST_3.trim().split("\\s*[,;]\\s*")));
        System.out.println(split3);
        System.out.println("<" + split3.get(0) + ">");
        System.out.println("<" + split3.get(2) + ">");
        System.out.println("<" + split1.get(5) + ">");

        List<String> split4= new ArrayList<>(
                List.of(TEST_4.trim().split("\\s*[,;]\\s*")));
        System.out.println(split4);
        System.out.println("<" + split4.get(0) + ">");
        System.out.println("<" + split4.get(2) + ">");
        System.out.println("<" + split4.get(5) + ">");

    }
}
