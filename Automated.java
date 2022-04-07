package readability;

import java.util.Scanner;

public class Automated {
    private final int words;
    private final int sentences;
    private final int characters;
    private final int syllable;
    private final int poly;

    public Automated(int words, int sentences, int characters, int syllable, int poly) {
        this.words = words;
        this.sentences = sentences;
        this.characters = characters;
        this.syllable = syllable;
        this.poly = poly;
    }

    public int getLevel(double x){
        int level =  (int) Math.round(x);
        switch (level){
            case 1:
                return 6;
            case 2:
                return 7;
            case 3:
                return 9;
            case 4:
                return 10;
            case 5:
                return 11;
            case 6:
                return 12;
            case 7:
                return 13;
            case 8:
                return 14;
            case 9:
                return 15;
            case 10:
                return 16;
            case 11:
                return 17;
            case 12:
                return 18;
            case 13:
                return 24;
            default:
                return 0;
        }
    }

    public void printAll(){
        System.out.println();
        System.out.println("Words: "+this.words);
        System.out.println("Sentences: "+this.sentences);
        System.out.println("Characters: "+this.characters);
        System.out.println("Syllables: "+this.syllable);
        System.out.println("Polysyllables: "+this.poly);
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the score you want to calculate (ARI, FK, SMOG, CL, all):");
        printTest(scan.next());
    }

    public void printTest(String x){

        switch (x){
            case "ARI":
                Ari(); break;
            case "FK":
                Fk(); break;
            case "SMOG":
                Smog(); break;
            case "CL":
                Cl(); break;
            default:
                int count = 0;
                count += Ari();
                count += Fk();
                count += Smog();
                count += Cl();
                System.out.printf("\nThis text should be understood in average by %.2f-year-olds.",(double)count/4);
        }


    }

    public int Ari(){
        double ARI = ((4.71)*((double)characters/words))+((0.5)*((double)words/sentences))-(21.43);
        int level = getLevel(ARI);
        System.out.printf("Automated Readability Index: %.2f (about %d-year-olds).\n",ARI,level);
        return level;
    }

    public int Fk(){
        double FK = ((0.39)*((double)words/sentences))+((11.8)*((double)syllable/words))-(15.59);
        int level = getLevel(FK);
        System.out.printf("Flesch–Kincaid readability tests: %.2f (about %d-year-olds).\n",FK,level);
        return level;
    }

    public int Smog(){
        double SMOG = ((1.043)*(Math.sqrt(poly*(30/(double)sentences))))+(3.1291);
        int level = getLevel(SMOG);
        System.out.printf("Simple Measure of Gobbledygook: %.2f (about %d-year-olds).\n",SMOG,level);
        return level;
    }

    public int Cl(){
        double CL = ((0.0588)*((double)characters/words*100))-((0.296)*((double)sentences/words*100))-(15.8);
        int level = getLevel(CL);
        System.out.printf("Coleman–Liau index: %.2f (about %d-year-olds).\n",CL,level);
        return level;
    }


}