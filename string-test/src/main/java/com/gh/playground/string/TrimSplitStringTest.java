package com.gh.playground.string;

import java.util.ArrayList;
import java.util.List;

public class TrimSplitStringTest {
    protected static String test1 = "SIU, CBM, TTU, CDM, CIM, RPR, PPR, JPR, IDC, NFC, DPR, SPR, PIN, SCN, DEP, VDM, SEC, BCR";
    protected static String test2 = "SIU,CBM,TTU,CDM,CIM,RPR,PPR,JPR,IDC,NFC,DPR,SPR,PIN,SCN,DEP,VDM,SEC,BCR";
    protected static String test3 = "SIU; CBM; TTU; CDM; CIM; RPR; PPR; JPR; IDC; NFC; DPR; SPR; PIN; SCN; DEP; VDM; SEC; BCR";
    protected static String test4 = "SIU;CBM;TTU;CDM;CIM;RPR;PPR;JPR;IDC;NFC;DPR;SPR;PIN;SCN;DEP;VDM;SEC;BCR";

    public static void main(String[] args) {
        List<String> split1= new ArrayList<>(
                List.of(test1.trim().split("\\s*[,;]\\s*")));

        System.out.println(split1);
        System.out.println("<" + split1.get(0) +">");
        System.out.println("<" + split1.get(2) +">");
        System.out.println("<" + split1.get(5) +">");

        List<String> split2= new ArrayList<>(
                List.of(test2.trim().split("\\s*[,;]\\s*")));
        System.out.println(split2);
        System.out.println("<" + split2.get(0) + ">");
        System.out.println("<" + split2.get(2) + ">");
        System.out.println("<" + split2.get(5) + ">");

        List<String> split3= new ArrayList<>(
                List.of(test3.trim().split("\\s*[,;]\\s*")));
        System.out.println(split3);
        System.out.println("<" + split3.get(0) + ">");
        System.out.println("<" + split3.get(2) + ">");
        System.out.println("<" + split1.get(5) + ">");

        List<String> split4= new ArrayList<>(
                List.of(test4.trim().split("\\s*[,;]\\s*")));
        System.out.println(split4);
        System.out.println("<" + split4.get(0) + ">");
        System.out.println("<" + split4.get(2) + ">");
        System.out.println("<" + split4.get(5) + ">");

    }
}
